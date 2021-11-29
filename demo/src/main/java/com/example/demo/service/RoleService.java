package com.example.demo.service;

import com.example.demo.model.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface RoleService {
    void addRole(Role role);
    List<Role> roleList();
    Role getRoleById(int id);
    void removeRole(int id);
    void updateRole(Role role);
    Role findByRoleName(String name);
    HashSet<Role> getSetOfRoles(String[] roleSet);
}
