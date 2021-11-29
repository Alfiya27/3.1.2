package com.example.demo.dao;

import com.example.demo.model.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface RoleDao {
    void addRole(Role role);
    List<Role> roleList();
    Role getRoleById(int id);
    void updateRole(Role role);
    void removeRole(int id);
    Role findByRoleName(String name);
    HashSet<Role> getSetOfRoles(String[] nameRoles);
}
