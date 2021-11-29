package com.example.demo.dao;

import com.example.demo.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> roleList() {
        return entityManager.createQuery("SELECT role FROM Role role", Role.class).getResultList();
    }

    @Override
    public Role getRoleById(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void updateRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    public void removeRole(int id) {
        entityManager.remove(getRoleById(id));
    }

    @Override
    public Role findByRoleName(String name) {
        TypedQuery<Role> query = entityManager.createQuery(
                "SELECT role FROM Role role WHERE role.name = :role", Role.class);
        return query
                .setParameter("role", name)
                .getSingleResult();
    }

    @Override
    public HashSet getSetOfRoles(String[] nameRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : nameRoles) {
            roleSet.add(findByRoleName(role));
        }
        return (HashSet) roleSet;
    }

}
