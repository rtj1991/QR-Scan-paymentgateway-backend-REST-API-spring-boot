package com.online.mypay.service.user;

import com.online.mypay.dto.UserDto;
import com.online.mypay.model.User;
import com.online.mypay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setNic(userDto.getNic());
        user.setFullName(userDto.getFullName());
        user.setLastName(userDto.getLastName());
        user.setRecoveryPhone(userDto.getTelephone());
        user.setAddress(userDto.getAddress());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userRepository.findByRecoveryPhone(phoneNumber);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User editUser(User user, UserDto userDto) {
        if (userDto.getFullName() != null) {
            user.setUserName(userDto.getFullName());
        } else {
            user.setUserName(user.getFullName());
        }
        if (userDto.getLastName() != null) {
            user.setUserName(userDto.getLastName());
        } else {
            user.setUserName(user.getLastName());
        }
        if (userDto.getNic() != null) {
            user.setUserName(userDto.getNic());
        } else {
            user.setUserName(user.getNic());
        }
        if (userDto.getAddress() != null) {
            user.setUserName(userDto.getAddress());
        } else {
            user.setUserName(user.getAddress());
        }
        if (userDto.getTelephone() != null) {
            user.setUserName(userDto.getTelephone());
        } else {
            user.setUserName(user.getRecoveryPhone());
        }

        User edituser = userRepository.save(user);

        return edituser;
    }

    @Override
    public User findByUsername(String name) {
        User userName = userRepository.findByUserName(name);
        return userName;
    }
}
