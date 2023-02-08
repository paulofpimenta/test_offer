package com.api.demo.services;

import com.api.demo.model.UserApi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {


    public List<UserApi> getUsers();

    public Optional<UserApi> getUser(String id);

    public UserApi addUser(UserApi userApi);


}
