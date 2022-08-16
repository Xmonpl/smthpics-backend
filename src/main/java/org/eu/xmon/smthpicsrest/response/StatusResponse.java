package org.eu.xmon.smthpicsrest.response;

public enum StatusResponse {
    //@TODO zrobić to
    OK ("200"),
    Error("Error");

    private String status;

    StatusResponse(String success) {
    }
}
