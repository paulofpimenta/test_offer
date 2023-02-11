package com.api.demo.services;

import com.api.demo.model.UserApi;
import com.api.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserApi addUser(UserApi userApi) {
        return userRepository.save(userApi);
    }
    @Override
    public List<UserApi> getUsers() {
        return userRepository.findAll();

    }
    @Override
    public Optional<UserApi> getUser(String id)  {
            return userRepository.findById(id);
    }


}
