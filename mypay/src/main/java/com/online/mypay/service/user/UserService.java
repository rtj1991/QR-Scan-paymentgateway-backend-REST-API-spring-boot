package com.online.mypay.service.user;

import com.online.mypay.dto.UserDto;
import com.online.mypay.model.User;

public interface UserService {

    public User createUser(UserDto userDto);
    public User editUser(User user, UserDto userDto);
    public User findByPhoneNumber(String phoneNumber);
    public User findById(int id);

    public User findByUsername(String name);

}
