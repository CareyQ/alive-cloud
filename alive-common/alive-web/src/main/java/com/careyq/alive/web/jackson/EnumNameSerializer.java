package com.careyq.alive.web.jackson;

import cn.hutool.core.util.StrUtil;
import com.careyq.alive.core.enums.IEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;

/**
 * 枚举名称序列化
 *
 * @author CareyQ
 */
@NoArgsConstructor
@AllArgsConstructor
public class EnumNameSerializer extends JsonSerializer<Object> implements ContextualSerializer {

    private String alias;
    private Class<? extends IEnum> enumType;

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeObject(value);
        if (StrUtil.isEmpty(alias)) {
            alias = gen.getOutputContext().getCurrentName() + "Name";
        }
        if (IEnum.noCode(enumType, value)) {
            gen.writeStringField(alias, "");
            return;
        }
        IEnum iEnum = IEnum.codeOf(enumType, value);
        gen.writeStringField(alias, iEnum.getDesc());
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        if (property != null) {
            EnumName enumName = property.getAnnotation(EnumName.class);
            if (enumName == null) {
                enumName = property.getContextAnnotation(EnumName.class);
            }
            if (enumName != null) {
                return new EnumNameSerializer(enumName.alias(), enumName.value());
            }
            return prov.findValueSerializer(property.getType(), property);
        }
        return prov.findNullValueSerializer(null);
    }
}
