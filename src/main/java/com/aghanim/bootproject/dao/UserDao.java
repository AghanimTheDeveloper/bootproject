package com.aghanim.bootproject.dao;

import com.aghanim.bootproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User getUserByLogin(String login);
}
