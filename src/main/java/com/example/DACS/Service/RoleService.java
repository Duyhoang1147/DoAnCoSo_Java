package com.example.DACS.Service;

import com.example.DACS.Another.RoleValue;
import com.example.DACS.Model.Role;
import com.example.DACS.Model.User;
import com.example.DACS.Repository.RoleRepository;
import com.example.DACS.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public void addRoleToUser(Role role, User user) {
        user.getRoles().add(role);
    }
    public void addRoleToUser(Role role, String userName) {
        userRepository.findByUsername(userName).ifPresent(user -> user.getRoles().add(role));
    }
    public void addRoleToUser(String roleValue, String userName) {
        Role r = roleRepository.findRoleById(roleValue);
        userRepository.findByUsername(userName).ifPresent(user -> user.getRoles().add(r));
    }
}
