package com.example.product_service.service;

import com.example.product_service.exception.ImageNotFoundException;
import com.example.product_service.model.Image;
import com.example.product_service.repository.ImageRepository;
import com.example.product_service.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Image create(MultipartFile file) {
        Image image;
        try {
            image = imageRepository.save(Image.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .file(ImageUtil.compressImage(file.getBytes()).toString()).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    public Image update(Long id, MultipartFile file) throws IOException {
        Image image = imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException());
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setFile(ImageUtil.compressImage(file.getBytes()).toString());
        imageRepository.save(image);
        return image;
    }
}
