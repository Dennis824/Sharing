package com.example.Sharing.services;

import com.example.Sharing.model.Role;
import com.example.Sharing.model.User;
import com.example.Sharing.repositories.RoleRepository;
import com.example.Sharing.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }



    @Transactional
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void create(User user) throws Exception{
        if (emailExist(user.getEmail()))
            throw new Exception("Email already used");
        user.setActive(1);

        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    @Transactional
    public void update(User user) throws Exception{
        userRepository.save(user);
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmailIgnoreCase(email);
        if (user != null) {
            return true;
        }
        return false;
    }
}
