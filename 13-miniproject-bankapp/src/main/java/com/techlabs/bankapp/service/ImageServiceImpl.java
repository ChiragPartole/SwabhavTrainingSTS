package com.techlabs.bankapp.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.techlabs.bankapp.dto.ImageModel;
import com.techlabs.bankapp.entity.Customer;
import com.techlabs.bankapp.entity.Image;
import com.techlabs.bankapp.repository.CustomerRepository;
import com.techlabs.bankapp.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CustomerRepository customerRepo;

    @Override
    public ResponseEntity<Map> uploadImage(ImageModel imageModel) {
        try {
            if (imageModel.getName().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (imageModel.getFile().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Image image = new Image();
            image.setName(imageModel.getName());
            image.setUrl(cloudinaryService.uploadFile(imageModel.getFile(), "folder_1"));
            if(image.getUrl() == null) {
                return ResponseEntity.badRequest().build();
            }
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            Customer customer = customerRepo.findByEmail(name)
            		.orElseThrow(()->new NullPointerException("no customer found"));
            image.setCustomer(customer);
            imageRepository.save(image);
            return ResponseEntity.ok().body(Map.of("url", image.getUrl()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
