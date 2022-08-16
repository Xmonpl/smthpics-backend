package org.eu.xmon.smthpicsrest.converter;

import com.google.gson.Gson;
import org.eu.xmon.smthpicsrest.object.Embed;

import javax.persistence.AttributeConverter;

public class EmbedConverter implements AttributeConverter<Embed, String> {
    @Override
    public String convertToDatabaseColumn(Embed embed) {
        return new Gson().toJson(embed);
    }

    @Override
    public Embed convertToEntityAttribute(String s) {
        return new Gson().fromJson(s, Embed.class);
    }
}
