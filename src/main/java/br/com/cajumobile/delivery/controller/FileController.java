package br.com.cajumobile.delivery.controller;

import br.com.cajumobile.delivery.exception.FileNotFoundException;
import br.com.cajumobile.delivery.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
@RequestMapping("/download")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/{fileName}.{fileExtension}")
    public ResponseEntity<?> download(@PathVariable("fileName") String fileName, @PathVariable("fileExtension") String fileExtension) {
        try {
            InputStream inputStream = fileService.getFile(fileName, fileExtension);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("image/" + fileExtension))
                    .header("Content-disposition", "attachment; filename=" + fileName + "." + fileExtension)
                    .body(new InputStreamResource(inputStream));
        } catch (java.io.FileNotFoundException | FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
