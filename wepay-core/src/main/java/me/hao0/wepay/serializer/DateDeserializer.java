package me.hao0.wepay.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import me.hao0.common.date.Dates;

import java.io.IOException;
import java.util.Date;

/**
 * 日期反序列化器
 */
public class DateDeserializer extends JsonDeserializer<Date> {
 
    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context)
      throws IOException {
        return Dates.toDate(parser.getValueAsString(), "yyyyMMddHHmmss");
    }
}