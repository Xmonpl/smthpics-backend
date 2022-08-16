package org.eu.xmon.smthpicsrest.object;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UploadSetting {
    private Boolean rawUrl;
    private Boolean invisibleUrl;
    private Boolean emojiUrl;
    private Boolean destroyImage;
    private Boolean fakeDomain;
    private Boolean path;
    private String pathType;
    private List<Domain> domains;
}
