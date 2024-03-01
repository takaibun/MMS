package com.takaibun.plexmetadatamanager.handler;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.takaibun.plexmetadatamanager.enums.BaseEnum;

/**
 * EnumDateSerialize
 *
 * @author takaibun
 * @since 2024/3/2
 */
public class EnumDateSerializer extends JsonSerializer<BaseEnum> {
    @Override
    public void serialize(BaseEnum value, com.fasterxml.jackson.core.JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
        gen.writeNumber(value.getCode());
    }
}
