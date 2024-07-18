package com.vw.service;

import com.vw.dto.UserInfoDTO;
import com.vw.entities.UserInfo;
import com.vw.entities.UserRole;
import com.vw.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public UserInfo saveUsers(UserInfoDTO user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<UserRole> roles = new HashSet<>();
        UserRole role = new UserRole(1,"USER");
        roles.add(role);
        UserInfo userInfo = new UserInfo();
        userInfo = UserInfo.builder().username(user.getUsername()).email(user.getEmail())
                .password(user.getPassword())
                .roles(roles).build();
        return userRepository.save(userInfo);
    }

    public UserInfo saveAdmins(UserInfoDTO admin){
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        Set<UserRole> roles = new HashSet<>();
        UserRole role = new UserRole(2,"ADMIN");
        roles.add(role);
        UserInfo userInfo = new UserInfo();
        userInfo = UserInfo.builder().username(admin.getUsername())
                .email(admin.getEmail())
                .password(admin.getPassword())
                .roles(roles).build();
        return userRepository.save(userInfo);
    }

    public void deleteById(long id){
        userRepository.deleteById(id);
    }

    public List<UserInfo> fetchUser(){
        return userRepository.findAll();
    }

    public UserInfo fetchUserbyId(long id){
        return userRepository.findById(id).get();
    }
    public UserInfo fetchUserbyUsername(String name){
        return userRepository.findByUsername(name).get();
    }
}
