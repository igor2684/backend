package com.backend.controller;

import com.backend.entity.Role;
import com.backend.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/roles")
    public List<Role> findAllRoles() {
        return roleService.findAll();
    }
}
