package com.online.mypay;

import com.online.mypay.controller.LoginController;
import com.online.mypay.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginControllerTest {
    @Autowired
    private LoginController loginController;

    @Test
    void loginTest() {
        UserDto userDto = new UserDto();
        userDto.setUserName("thara");
        userDto.setPassword("123");
        loginController.login(userDto);
    }
}
