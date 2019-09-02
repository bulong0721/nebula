package io.nebula.session;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.redisson.RedissonScript;
import org.redisson.api.RKeys;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>A {@link SessionDAO} implementation backed by Redisson Objects.</p>
 *
 * @author streamone
 */
public class RedissonSessionDao extends AbstractSessionDAO {

    private final String SESSION_INFO_KEY_PREFIX;
    private final String SESSION_ATTR_KEY_PREFIX;

    private RedissonClient redisson;
    private Codec codec;

    public RedissonSessionDao(String namespace) {
        SESSION_INFO_KEY_PREFIX = String.format("session:%s:info:", namespace);
        SESSION_ATTR_KEY_PREFIX = String.format("session:%s:attr:", namespace);
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        String infoKey = getSessionInfoKey(sessionId.toString());
        String attrKey = getSessionAttrKey(sessionId.toString());
        new RedissonSession(this.redisson, this.codec, infoKey, attrKey, session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        String infoKey = getSessionInfoKey(sessionId.toString());
        String attrKey = getSessionAttrKey(sessionId.toString());
        List<Object> keys = new ArrayList<>(1);
        keys.add(infoKey);

        RedissonScript script = (RedissonScript) this.redisson.getScript();
        Long remainTimeToLive = script.eval(infoKey, RScript.Mode.READ_ONLY,
                RedissonSessionScript.READ_SCRIPT,
                RScript.ReturnType.INTEGER, keys);

        if (remainTimeToLive > 0) {
            return new RedissonSession(this.redisson, this.codec, infoKey, attrKey, sessionId);
        } else {
            return null;
        }
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        //do nothing, the RedissonSession will update the session in redis directly
    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null || "".equals(session.getId())) {
            return;
        }
        Serializable sessionId = session.getId();
        String infoKey = getSessionInfoKey(sessionId.toString());
        String attrKey = getSessionAttrKey(sessionId.toString());
        List<Object> keys = new ArrayList<>(2);
        keys.add(infoKey);
        keys.add(attrKey);

        RedissonScript script = (RedissonScript) this.redisson.getScript();
        script.eval(infoKey, RScript.Mode.READ_WRITE, RedissonSessionScript.DELETE_SCRIPT, RScript.ReturnType.VALUE, keys);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        RKeys keys = this.redisson.getKeys();
        Iterable<String> keysByPattern = keys.getKeysByPattern(SESSION_ATTR_KEY_PREFIX + "*");
        Iterable<Session> transform = Iterables.transform(keysByPattern, new Function<String, Session>() {
            @Override
            public Session apply(String input) {
                String attrKey = input;
                String infoKey = input.replace(SESSION_ATTR_KEY_PREFIX, SESSION_INFO_KEY_PREFIX);
                String sessionId = input.substring(SESSION_ATTR_KEY_PREFIX.length() + 1, input.length() - 1);
                return new RedissonSession(redisson, codec, infoKey, attrKey, sessionId);
            }
        });
        return Lists.newArrayList(transform);
    }

    /**
     * <p>Get key name by session id for binding a RMap to store basic informations of the session.</p>
     * <p>
     * In redis cluster, key hash tags ensure that the binding RMaps of a session are allocated in the
     * same hash slot.
     * <a href="https://redis.io/topics/cluster-spec#keys-hash-tags">https://redis.io/topics/cluster-spec#keys-hash-tags</a>
     * </p>
     *
     * @param sessionId the session id
     * @return key name
     */
    protected String getSessionInfoKey(String sessionId) {
        StringBuilder name = new StringBuilder(SESSION_INFO_KEY_PREFIX);
        name.append("{").append(sessionId).append("}");
        return name.toString();
    }

    /**
     * <p>Get key name by session id for binding a RMap to store attributes of the session.</p>
     * <p>
     * In redis cluster, key hash tags ensure that the binding RMaps of a session are allocated in the
     * same hash slot.
     * <a href="https://redis.io/topics/cluster-spec#keys-hash-tags">https://redis.io/topics/cluster-spec#keys-hash-tags</a>
     * </p>
     *
     * @param sessionId the session id
     * @return key name
     */
    protected String getSessionAttrKey(String sessionId) {
        StringBuilder name = new StringBuilder(SESSION_ATTR_KEY_PREFIX);
        name.append("{").append(sessionId).append("}");
        return name.toString();
    }

    public RedissonClient getRedisson() {
        return redisson;
    }

    public void setRedisson(RedissonClient redisson) {
        this.redisson = redisson;
    }

    public Codec getCodec() {
        return codec;
    }

    public void setCodec(Codec codec) {
        this.codec = codec;
    }
}
