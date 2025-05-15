package com.example.quanlysancaulong.service;

import com.example.quanlysancaulong.model.Image;

import java.util.List;

public interface IImageService {
    List<Image> findAllImage(int court_id);
}
