package org.eu.xmon.smthpicsrest.object;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Profile {
    private Boolean isPublic;
    private Boolean isAnonymousUploads;
    private Boolean isDiscordNotification;
    private Boolean isNsfwDomains;
    private String description;
    private String language;
    private CommentType commentType;
    private String country;
    private String timeZone;
    private String dateFormat;
    private String avatar;
}
