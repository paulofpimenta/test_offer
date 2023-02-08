package com.api.demo.services;

import com.api.demo.annotations.TrackTime;
import com.api.demo.model.UserApi;
import com.api.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
