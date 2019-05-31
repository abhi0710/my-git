package com.gaian.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by IntelliJ IDEA.
 * User: Naresh Patchipulusu(GSIHYD-1298)
 * Date: 3/19/19
 * Time: 12:28 PM
 */
@ConfigurationProperties(prefix = "files")
@Setter
@Getter
public class FileStorageProperties {

    private String uploadDir;

}
