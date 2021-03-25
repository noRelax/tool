package zjol.virtual;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Timestamp转LocalDateTime转换器
 * Created by LinShen on 2017/7/6.
 */
public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    public LocalDateTimeDeserializer() {
        this(null);
    }

    public LocalDateTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctx)
            throws IOException {
        Date date = new Date(jp.getLongValue());
        return LocalDateTime.ofInstant(date.toInstant(), TimeUtils.DEFAULT_TIMEZONE);
    }
}
