package com.api.demo.services;

import com.api.demo.model.UserApi;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<UserApi> showAll();

    public Optional<UserApi> getUserApiDetails(String id);

    public void createUserApi(UserApi userApi);

    public UserApi save(UserApi userApi);

    public void deleteUserApiById(String id);

}
