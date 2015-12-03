package me.hao0.wepay.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import me.hao0.wepay.model.enums.TradeState;

import java.io.IOException;

/**
 * 交易状态反序列化器
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 27/11/15
 * @since 1.0.0
 */
public class TradeStateDeserializer extends JsonDeserializer<TradeState> {

    @Override
    public TradeState deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return TradeState.from(jp.getValueAsString());
    }
}
