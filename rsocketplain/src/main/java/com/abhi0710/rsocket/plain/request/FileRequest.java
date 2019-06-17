package com.abhi0710.rsocket.plain.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class FileRequest {
    String fileName;
    String priority;
    int block;
    int clientId;

    @JsonCreator
    public FileRequest(@JsonProperty("fileName") String fileName, @JsonProperty("priority") String priority,
                       @JsonProperty("block") int block, @JsonProperty("clientid") int clientId) {
        this.fileName = fileName;
        this.priority = priority;
        this.block = block;
        this.clientId = clientId;
    }
}
