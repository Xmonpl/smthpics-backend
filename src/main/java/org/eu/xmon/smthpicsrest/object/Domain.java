package org.eu.xmon.smthpicsrest.object;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Domain {
    private String domain;
    private String subDomain;
}
