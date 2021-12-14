package com.sinaukoding.library.service;

import com.sinaukoding.library.dao.BaseDAO;
import com.sinaukoding.library.dao.BookDAO;
import com.sinaukoding.library.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService extends BaseService<Book> {

    @Autowired
    private BookDAO dao;

    @Override
    protected BaseDAO<Book> getDAO() {
        return dao;
    }
}