package com.sinaukoding.library.service;

import com.sinaukoding.library.dao.BaseDAO;
import com.sinaukoding.library.dao.LoanDAO;
import com.sinaukoding.library.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService extends BaseService<Loan> {

    @Autowired
    private LoanDAO dao;

    @Override
    protected BaseDAO<Loan> getDAO() {
        return dao;
    }
}