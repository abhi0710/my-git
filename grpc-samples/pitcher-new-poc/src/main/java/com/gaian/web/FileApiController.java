package com.gaian.web;

import com.gaian.exception.FileStorageException;
import com.gaian.service.FileSplitService;
import com.gaian.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by IntelliJ IDEA.
 * User: Naresh.P (GSIHYD-1298)
 * Date: 30/5/19
 * Time: 3:02 PM
 */
@RequestMapping("/v1/api")
@RestController
@Slf4j
public class FileApiController {

    @Autowired
    private FileSplitService fileSplitService;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/split")
    public ResponseEntity splitFile(@RequestParam("file")MultipartFile file,
                                    @RequestParam("path")String path) throws IOException {

        Path convFile = Paths.get(fileStorageService.getFileStorageLocation().toString(),path, file.getOriginalFilename());

        try {
            Files.createDirectories(Paths.get(fileStorageService.getFileStorageLocation().toString(),path));
        } catch (Exception ex) {
            log.error("{}", ex);
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }

        file.transferTo(convFile);

        fileSplitService.splitFile(convFile.toFile());

        return ResponseEntity.ok(fileStorageService.listFiles(path));

    }



    @GetMapping("/download")
    public ResponseEntity<byte[]> getContent(@RequestParam("file") String file,
                                             HttpServletRequest request) throws IOException {

        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(Paths.get(file).normalize());

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }
        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .contentLength(resource.getFile().length())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(IOUtils.toByteArray(resource.getInputStream()));
    }


}
