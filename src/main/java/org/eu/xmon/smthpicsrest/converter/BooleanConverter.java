package org.eu.xmon.smthpicsrest.converter;

import javax.persistence.AttributeConverter;
import java.math.BigInteger;

public class BooleanConverter  implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean aBoolean) {
        if (aBoolean) return "1";
        return "0";
    }

    @Override
    public Boolean convertToEntityAttribute(String integer) {
        if (integer.equals("1")) return Boolean.TRUE;
        return Boolean.FALSE;
    }
}
