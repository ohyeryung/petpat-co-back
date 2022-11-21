package com.smile.petpat.image.repository;

import com.smile.petpat.image.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Photo,Long> {
}
