package com.example.DACS.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class RandomIdService {
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
