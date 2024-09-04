package com.techlabs.bankapp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.bankapp.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
}
