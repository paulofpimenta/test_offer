package com.api.demo.services;

import com.api.demo.model.UserApi;
import com.api.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserApi save(UserApi userApi) {
        return userRepository.save(userApi);
    }
    @Override
    public List<UserApi> showAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserApi> getUserApiDetails(String id)  {
            return userRepository.findById(id);
    }

    @Override
    public void createUserApi(UserApi userApi)  {
    }

    @Override
    public void deleteUserApiById(String id)  {

    }


}
