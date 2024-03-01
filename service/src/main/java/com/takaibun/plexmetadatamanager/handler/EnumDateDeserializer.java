package com.takaibun.plexmetadatamanager.handler;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.takaibun.plexmetadatamanager.enums.BaseEnum;

/**
 * EnumDateDeserializer
 *
 * @author takaibun
 * @since 2024/3/2
 */
public class EnumDateDeserializer extends JsonDeserializer<BaseEnum> {
    @Override
    public BaseEnum deserialize(com.fasterxml.jackson.core.JsonParser p, DeserializationContext ctxt)
        throws IOException, com.fasterxml.jackson.core.JacksonException {
        return null;
    }
}
