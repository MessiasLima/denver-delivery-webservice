package br.com.cajumobile.delivery.service;

import br.com.cajumobile.delivery.exception.FileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private ServletContext servletContext;

    private static final String FILE_FOLDER = "files/";

    public String storeFile(MultipartFile multipartFile) throws IOException {
        String fileName = UUID.randomUUID().toString() + "." + getExtension(multipartFile);
        File file = new File(FILE_FOLDER);
        if (!file.exists()) {
            file.mkdirs();
        }
        byte data[] = multipartFile.getBytes();
        FileOutputStream out = new FileOutputStream(file.getAbsolutePath() + "/" + fileName);
        out.write(data);
        out.close();
        return fileName;
    }

    public String getExtension(MultipartFile file) {
        String[] split = file.getOriginalFilename().split("\\.");
        return split[split.length - 1];
    }

    public InputStream getFile(String fileName, String fileExtension) throws FileNotFoundException, java.io.FileNotFoundException {
        File file = new File(FILE_FOLDER + fileName + "." + fileExtension);
        if (!file.exists()) {
            throw new FileNotFoundException(fileName);
        }
        return new FileInputStream(file);
    }

    private String getExtension(String fileName) {
        String[] split = fileName.split("\\.");
        return split[split.length - 1];
    }

    public void deleteFileIfExists(String fileName) {
        File file = new File(FILE_FOLDER + "/" + fileName);
        if (file.exists()){
            file.delete();
        }
    }
}
