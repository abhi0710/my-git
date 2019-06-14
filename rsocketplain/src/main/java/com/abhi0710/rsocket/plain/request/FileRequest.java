package com.abhi0710.rsocket.plain.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class FileRequest {
    String fileName;
    String priority;

    @JsonCreator
    public FileRequest(@JsonProperty("fileName") String fileName, @JsonProperty("priority") String priority) {
        this.fileName = fileName;
        this.priority = priority;
    }
}
