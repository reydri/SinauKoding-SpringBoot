package com.sinaukoding.library.service;

import com.sinaukoding.library.dao.BaseDAO;
import com.sinaukoding.library.dao.UserDAO;
import com.sinaukoding.library.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserDAO dao;

    @Override
    protected BaseDAO<User> getDAO() {
        return dao;
    }
}