package com.aghanim.bootproject.service.implemenatation;

import com.aghanim.bootproject.dao.RoleDao;
import com.aghanim.bootproject.dao.UserDao;
import com.aghanim.bootproject.model.Role;
import com.aghanim.bootproject.model.User;
import com.aghanim.bootproject.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    @Override
    public User addUser(User user) {
        user.setRoles(attachRolesToUser());
        userDao.saveAndFlush(user);
        return user;
    }

    @Override
    public User getUserById(long id) {
        return userDao.getOne(id);
    }

    @Override
    public User updateUser(User user) {
        user.setRoles(attachRolesToUser());
        userDao.saveAndFlush(user);
        return user;
    }

    @Override
    public void deleteUser(long id) {
        userDao.delete(getUserById(id));
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    private Set<Role> attachRolesToUser(){
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(2L));
        return roles;
    }
}
