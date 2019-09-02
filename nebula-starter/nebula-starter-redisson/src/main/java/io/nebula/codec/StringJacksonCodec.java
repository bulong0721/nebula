package io.nebula.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.util.CharsetUtil;
import org.redisson.client.codec.BaseCodec;
import org.redisson.client.handler.State;
import org.redisson.client.protocol.Decoder;
import org.redisson.client.protocol.Encoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/17
 */
public class StringJacksonCodec<T> extends BaseCodec {

    private final ObjectMapper objectMapper;
    private final Class<T> elementClass;

    private Encoder stringEncoder = new Encoder() {
        @Override
        public ByteBuf encode(Object in) throws IOException {
            ByteBuf out = ByteBufAllocator.DEFAULT.buffer();
            out.writeCharSequence(in.toString(), CharsetUtil.UTF_8);
            return out;
        }
    };

    private Decoder<Object> stringDecoder = new Decoder<Object>() {
        @Override
        public String decode(ByteBuf buf, State state) throws IOException {
            String str = buf.toString(CharsetUtil.UTF_8);
            buf.readerIndex(buf.readableBytes());
            return str;
        }
    };

    private Encoder jacksonEncoder = new Encoder() {
        @Override
        public ByteBuf encode(Object in) throws IOException {
            ByteBuf out = ByteBufAllocator.DEFAULT.buffer();
            try {
                ByteBufOutputStream os = new ByteBufOutputStream(out);
                objectMapper.writeValue((OutputStream) os, in);
                return os.buffer();
            } catch (IOException e) {
                out.release();
                throw e;
            } catch (Exception e) {
                out.release();
                throw new IOException(e);
            }
        }
    };

    private Decoder<T> jacksonDecoder = new Decoder<T>() {
        @Override
        public T decode(ByteBuf buf, State state) throws IOException {
            return objectMapper.readValue((InputStream) new ByteBufInputStream(buf), elementClass);
        }
    };

    public StringJacksonCodec(ObjectMapper objectMapper, Class<T> elementClass) {
        this.elementClass = elementClass;
        this.objectMapper = objectMapper;
    }

    @Override
    public Decoder<Object> getMapValueDecoder() {
        return (Decoder<Object>) jacksonDecoder;
    }

    @Override
    public Decoder<Object> getMapKeyDecoder() {
        return (Decoder<Object>) stringDecoder;
    }

    @Override
    public Decoder<Object> getValueDecoder() {
        return (Decoder<Object>) jacksonDecoder;
    }

    @Override
    public Encoder getMapValueEncoder() {
        return jacksonEncoder;
    }

    @Override
    public Encoder getMapKeyEncoder() {
        return stringEncoder;
    }

    @Override
    public Encoder getValueEncoder() {
        return jacksonEncoder;
    }
}
