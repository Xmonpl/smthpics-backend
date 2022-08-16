package org.eu.xmon.smthpicsrest.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.eu.xmon.smthpicsrest.converter.EmbedConverter;
import org.eu.xmon.smthpicsrest.converter.ProfileConverter;
import org.eu.xmon.smthpicsrest.converter.UUIDConverter;
import org.eu.xmon.smthpicsrest.converter.UploadSettingConverter;

import javax.persistence.*;
import java.util.UUID;

@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "users_settings")
public class UserSetting {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @Convert(converter = UUIDConverter.class)
    public UUID uuid;

    @Convert(converter = EmbedConverter.class)
    public Embed embed;

    @Convert(converter = ProfileConverter.class)
    public Profile profile;

    @Convert(converter = UploadSettingConverter.class)
    public UploadSetting uploadSetting;
}
