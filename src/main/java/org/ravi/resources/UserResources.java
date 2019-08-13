package org.ravi.resources;

import org.ravi.service.UserServices;
import org.ravi.vo.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserResources {

    @Autowired
    private UserServices userServices;

    @PostMapping("/save")
    public String saveUser(@RequestBody @Valid UserForm userForm){
        return userServices.saveUser(userForm);
    }

    @PostMapping("/save2")
    public String saveUser2(@RequestBody @Valid UserForm userForm){
        return userServices.saveUser2(userForm);
    }
}
