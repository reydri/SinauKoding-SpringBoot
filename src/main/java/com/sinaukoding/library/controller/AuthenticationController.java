package com.sinaukoding.library.controller;

import com.sinaukoding.library.common.RestResult;
import com.sinaukoding.library.entity.User;
import com.sinaukoding.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@PreAuthorize("permitAll()")
public class AuthenticationController extends BaseController {
    @Autowired
    private UserService service;

    @PostMapping(value = "do-login")
    public RestResult doLogin(@RequestBody User user){
        return service.login(user);
    }

    @PostMapping(value = "do-register")
    public RestResult register(@RequestBody User param){

        return new RestResult(service.register(param, User.Role.ROLE_USER));
    }
}
