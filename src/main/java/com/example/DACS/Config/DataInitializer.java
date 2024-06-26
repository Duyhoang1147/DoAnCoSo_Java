package com.example.DACS.Config;

import com.example.DACS.Another.RoleValue;
import com.example.DACS.Model.Role;
import com.example.DACS.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public CommandLineRunner initRoles() {
        return args -> {
            // Thêm các vai trò vào cơ sở dữ liệu
            for (RoleValue role : RoleValue.values()) {
                if (roleRepository.findRoleByName(role.name()) == null) {
                    Role addingRole = new Role();
                    addingRole.setName(role.name());
                    addingRole.setId(role.value);
                    roleRepository.save(addingRole);
                }
            }
        };
    }
}

