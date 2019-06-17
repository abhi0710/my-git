package com.abhi0710.rsocket.plain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.ByteBuffer;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileResponse {
    int block;
}
