package com.sinaukoding.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinaukoding.library.common.RestResult;
import com.sinaukoding.library.common.StatusCode;
import com.sinaukoding.library.entity.Loan;
import com.sinaukoding.library.service.LoanService;
import com.sinaukoding.library.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("loans")
@PreAuthorize("permitAll()")
public class LoanController extends BaseController {
    @Autowired
    private LoanService service;

    @GetMapping
    public RestResult get(@RequestParam(value = "param", required = false) String param,
                          @RequestParam(value = "offset") int offset,
                          @RequestParam(value = "limit") int limit) throws JsonProcessingException {

        Loan loan = param != null ? new ObjectMapper().readValue(param, Loan.class) : null;

        long rows = service.count(loan);

        return new RestResult(rows > 0 ? service.find(loan, offset, limit) : new ArrayList<>(), rows);
    }

    @GetMapping(value = "by-date")
    public RestResult findByDate(@RequestParam(value = "param", required = false) String param,
                                 @RequestParam(value = "start-date") String startDate,
                                 @RequestParam(value = "end-date") String endDate) throws JsonProcessingException {

        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        Loan loan = param != null ? new ObjectMapper().readValue(param, Loan.class) : new Loan();

        result.setData(service.findByDate(loan, DateUtils.fromString(startDate), DateUtils.fromString(endDate)));
        result.setRows((long) service.findByDate(loan, DateUtils.fromString(startDate), DateUtils.fromString(endDate)).size());

        return result;
    }

    @PostMapping
    public RestResult save(@RequestBody Loan entity){
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if (entity != null){
            result.setData(service.save(entity));
            result.setStatus(StatusCode.SAVE_SUCCESS);
        }
        return result;
    }

    @PutMapping
    public RestResult update(@RequestBody Loan entity){
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if(entity != null){
            result.setData(service.update(entity));
            result.setStatus(service.update(entity) != null ? StatusCode.UPDATE_SUCCESS : StatusCode.UPDATE_FAILED);
        }
        return result;
    }

    @DeleteMapping(value = "{id}")
    public RestResult delete(@PathVariable Long id){
        return new RestResult(service.delete(id) ? StatusCode.DELETE_SUCCESS : StatusCode.DELETE_FAILED);
    }
}