package io.nebula.wechat.mp;

import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.mp.enums.TicketType;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/29
 */
public class WxMpRedissonConfigStorage extends WxMpDefaultConfigImpl {
    protected final RedissonClient redissonClient;
    protected final WxMpApplication application;
    protected final RBucket<String> accessToken;

    public WxMpRedissonConfigStorage(WxMpApplication application, RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
        this.application = application;
        this.setAppId(application.getAppId());
        this.setSecret(application.getAppSecret());
        this.accessToken = redissonClient.getBucket("wx:access_token:" + appId);
    }

    @Override
    public String getAccessToken() {
        return accessToken.get();
    }

    @Override
    public boolean isAccessTokenExpired() {
        return accessToken.remainTimeToLive() < 2;
    }

    @Override
    public synchronized void updateAccessToken(String value, int expiresInSeconds) {
        accessToken.set(value, expiresInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public void expireAccessToken() {
        accessToken.expire(1, TimeUnit.SECONDS);
    }

    private RBucket<String> ticketBucket(TicketType type) {
        String key = String.format("wx:ticket:key:%s:%s", this.appId, type.getCode());
        return redissonClient.getBucket(key);
    }

    @Override
    public String getTicket(TicketType type) {
        return ticketBucket(type).get();
    }

    @Override
    public boolean isTicketExpired(TicketType type) {
        return ticketBucket(type).remainTimeToLive() < 2;
    }

    @Override
    public void expireTicket(TicketType type) {
        ticketBucket(type).expire(1, TimeUnit.SECONDS);
    }

    @Override
    public synchronized void updateTicket(TicketType type, String ticket, int expiresInSeconds) {
        ticketBucket(type).set(ticket, expiresInSeconds, TimeUnit.SECONDS);
    }
}

