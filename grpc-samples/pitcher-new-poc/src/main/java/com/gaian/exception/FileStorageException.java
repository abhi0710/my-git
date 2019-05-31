package com.gaian.exception;

/**
 * Created by IntelliJ IDEA.
 * User: Naresh Patchipulusu(GSIHYD-1298)
 * Date: 3/19/19
 * Time: 12:28 PM
 */
public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
