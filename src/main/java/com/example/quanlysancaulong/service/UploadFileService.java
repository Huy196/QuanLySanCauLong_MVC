package com.example.quanlysancaulong.service;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
@Service
public class UploadFileService {
    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        String sourceFolder = "D:\\IdeaProjects\\QuanLySanCauLong\\src\\main\\resources\\static\\uploadFile\\";
        File sourceFile = new File(sourceFolder + fileName);


        if (!sourceFile.exists()){
            FileCopyUtils.copy(file.getBytes(), sourceFile);
        }
        return fileName;
    }
}
