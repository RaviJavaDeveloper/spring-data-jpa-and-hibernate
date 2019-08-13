package org.ravi.service;

import org.ravi.dao.UserDao;
import org.ravi.models.User;
import org.ravi.models.UserProfile;
import org.ravi.repository.UserProfileRepository;
import org.ravi.repository.UserRepository;
import org.ravi.vo.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserDao userDao;

    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = Exception.class)
    public String saveUser(UserForm userForm){
        UserProfile userProfile = populateUserProfile(userForm);
        userProfile = userProfileRepository.save(userProfile);
        User user = populateUser(userForm, userProfile);
        user = userRepository.save(user);
        return "User created successfully";
    }


    public String saveUser2(UserForm userForm){
        UserProfile userProfile = populateUserProfile(userForm);
        userProfile.setId(userDao.saveUserProfile(userProfile));
        User user = populateUser(userForm, userProfile);
        user.setId(userDao.saveUser(user));
        return "User created successfully";
    }

    public UserProfile populateUserProfile(UserForm userForm){
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName(userForm.getFirstName());
        userProfile.setLastname(userForm.getLastName());
        userProfile.setMiddleName(userForm.getMiddleName());
        userProfile.setEmail(userForm.getEmail());
        return userProfile;
    }

    public User populateUser(UserForm userForm, UserProfile userProfile){
        User user = new User();
        user.setUsername(userProfile.getFirstName()+userProfile.getLastname());
        user.setPassword(UUID.randomUUID().toString());
        user.setUserProfile(userProfile);
        return  user;
    }
}
