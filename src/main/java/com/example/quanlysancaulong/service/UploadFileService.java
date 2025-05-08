package com.example.quanlysancaulong.service;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
@Service
public class UploadFileService {
    public String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();

        String sourceFolder = "D:\\IdeaProjects\\QuanLySanCauLong\\src\\main\\webapp\\uploadFile\\";
        File sourceFile = new File(sourceFolder + fileName);


        if (!sourceFile.exists()){
            FileCopyUtils.copy(file.getBytes(), sourceFile);
        }

        String runtimeFolder = request.getServletContext().getRealPath("/uploadFile/");
        File runtimDir = new File(runtimeFolder);
        if (!runtimDir.exists()){
            runtimDir.mkdirs();
        }

        FileCopyUtils.copy(file.getBytes(), new File(runtimeFolder + File.separator + fileName));

        return fileName;
    }
}
