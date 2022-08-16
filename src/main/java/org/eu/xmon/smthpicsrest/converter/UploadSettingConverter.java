package org.eu.xmon.smthpicsrest.converter;

import com.google.gson.Gson;
import org.eu.xmon.smthpicsrest.object.Embed;
import org.eu.xmon.smthpicsrest.object.UploadSetting;

import javax.persistence.AttributeConverter;

public class UploadSettingConverter implements AttributeConverter<UploadSetting, String> {
    @Override
    public String convertToDatabaseColumn(UploadSetting uploadSetting) {
        return new Gson().toJson(uploadSetting);
    }

    @Override
    public UploadSetting convertToEntityAttribute(String s) {
        return new Gson().fromJson(s, UploadSetting.class);
    }
}
