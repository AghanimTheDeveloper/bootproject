package com.aghanim.bootproject.dao;

import com.aghanim.bootproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
    Role getRoleById(long id);
}
