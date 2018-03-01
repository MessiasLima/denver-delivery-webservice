package br.com.cajumobile.delivery.service;

import br.com.cajumobile.delivery.model.enun.FileType;
import br.com.cajumobile.delivery.util.Utils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileService {

    private static final String FILE_FOLDER = "files/";

    public String storeFile(MultipartFile multipartFile, FileType fileType, Integer identifier) throws IOException {
        String fileName = Utils.md5(fileType.toString() + identifier) + "." + getExtension(multipartFile);
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
}
