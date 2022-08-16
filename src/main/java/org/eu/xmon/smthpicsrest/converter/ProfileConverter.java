package org.eu.xmon.smthpicsrest.converter;

import com.google.gson.Gson;
import org.eu.xmon.smthpicsrest.object.Embed;
import org.eu.xmon.smthpicsrest.object.Profile;

import javax.persistence.AttributeConverter;

public class ProfileConverter implements AttributeConverter<Profile, String> {
    @Override
    public String convertToDatabaseColumn(Profile profile) {
        return new Gson().toJson(profile);
    }

    @Override
    public Profile convertToEntityAttribute(String s) {
        return new Gson().fromJson(s, Profile.class);
    }
}
