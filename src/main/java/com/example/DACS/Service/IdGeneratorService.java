package com.example.DACS.Service;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdGeneratorService {

    // Method to generate unique ID
    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
