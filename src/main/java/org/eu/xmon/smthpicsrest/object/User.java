package org.eu.xmon.smthpicsrest.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.eu.xmon.smthpicsrest.converter.BooleanConverter;
import org.eu.xmon.smthpicsrest.converter.UUIDConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "users")
@Embeddable
public class User {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @Convert(converter = UUIDConverter.class)
    public UUID uuid;

    public String discordId;

    @Convert(converter = UUIDConverter.class)
    public UUID token;

    //must
    public String nickname;

    @Enumerated
    public Role role;

    //must
    public String email;

    //must
    public String password;

    public Long createdTime;

    @Convert(converter = BooleanConverter.class)
    public Boolean actived;

    public String reasonUnactived;

    public Long uploads;

    public Long uploadsSize;

}
