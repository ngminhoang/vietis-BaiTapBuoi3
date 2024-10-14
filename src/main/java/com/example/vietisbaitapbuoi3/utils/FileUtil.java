package com.example.vietisbaitapbuoi3.utils;

import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    public static String saveImageFile(Account user, MultipartFile file) throws IOException {
        String projectDir = System.getProperty("user.dir");

        String folderPath = projectDir + "/src/main/resources/static/uploads/";

        File uploadDir = new File(folderPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String fileName = user.getId() + "_" + file.getOriginalFilename();
        String filePath = folderPath + fileName;

        File destinationFile = new File(filePath);
        file.transferTo(destinationFile);

        return fileName;
    }
}
