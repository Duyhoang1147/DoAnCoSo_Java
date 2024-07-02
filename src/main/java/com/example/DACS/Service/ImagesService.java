package com.example.DACS.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImagesService {
    private final String imageDirectory = "src/main/images/";

    public String saveImage(MultipartFile file) throws IOException {
        if (file.isEmpty() || file.getOriginalFilename() == null) {
            throw new IOException("Không thể lưu vì không có ảnh hoặc không có tên ảnh");
        }

        // File name cho ảnh vào
        String uniqueFileName = IdGeneratorService.generateUniqueId() + getFileExtension(file.getOriginalFilename());

        //Tạo Path đích
        Path destinationPath = Paths.get(imageDirectory).resolve(uniqueFileName).normalize().toAbsolutePath();

        // Kiểm tra tồn tại của images
        if (!Files.exists(destinationPath.getParent())) {
            Files.createDirectories(destinationPath.getParent());
        }
        // Save
        Files.copy(file.getInputStream(), destinationPath);
        return "/images/" + uniqueFileName;
    }
    public void deleteImage(String imagePath) throws IOException {
        if (imagePath != null && !imagePath.isEmpty()) {
            // Tạo đường dẫn đầy đủ đến file ảnh
            Path path = Paths.get(imageDirectory).resolve(imagePath.replace("/images/", "")).normalize().toAbsolutePath();

            // Xóa ảnh nếu tồn tại
            Files.deleteIfExists(path);
        } else {
            throw new IOException("Đường dẫn ảnh không hợp lệ");
        }
    }
    private static String getFileExtension(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i >= 0) {
            extension = fileName.substring(i);
        }
        return extension;
    }
}
