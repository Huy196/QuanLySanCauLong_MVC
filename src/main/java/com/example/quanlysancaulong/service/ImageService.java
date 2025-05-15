package com.example.quanlysancaulong.service;

import com.example.quanlysancaulong.model.Image;
import com.example.quanlysancaulong.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImageService implements IImageService{
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public List<Image> findAllImage(int court_id) {
        return imageRepository.findAllByCourtId(court_id);
    }
}
