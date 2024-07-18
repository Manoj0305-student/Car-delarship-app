package com.vw.service;

import com.vw.entities.UserRole;
import com.vw.repo.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    public List<UserRole> showRoles()
    {
        return rolesRepository.findAll();
    }

    public UserRole saveRoles(UserRole role){
        return rolesRepository.save(role);
    }
}
