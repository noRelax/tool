package zjol.virtual;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;


/**
 * Timestamp转LocalDate转换器
 */
public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    private static final long serialVersionUID = -4691264176228208503L;

    public LocalDateDeserializer() {
        this(null);
    }

    public LocalDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctx)
            throws IOException {
        Date date = new Date(jp.getLongValue());
        return LocalDateTime.ofInstant(date.toInstant(), TimeUtils.DEFAULT_TIMEZONE).toLocalDate();
    }
}
