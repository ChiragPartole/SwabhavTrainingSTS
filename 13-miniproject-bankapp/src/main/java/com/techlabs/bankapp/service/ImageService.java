package com.techlabs.bankapp.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.techlabs.bankapp.dto.ImageModel;

public interface ImageService {

    public ResponseEntity<Map> uploadImage(ImageModel imageModel);
}
