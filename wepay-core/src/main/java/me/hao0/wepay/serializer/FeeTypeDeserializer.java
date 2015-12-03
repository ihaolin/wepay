package me.hao0.wepay.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import me.hao0.wepay.model.enums.FeeType;
import java.io.IOException;

/**
 * 交易类型反序列化器
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 27/11/15
 * @since 1.0.0
 */
public class FeeTypeDeserializer extends JsonDeserializer<FeeType> {

    @Override
    public FeeType deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return FeeType.from(jp.getValueAsString());
    }
}
