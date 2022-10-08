package com.online.mypay;

import com.online.mypay.controller.UserController;
import com.online.mypay.dto.OtpRequestDto;
import com.online.mypay.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @Test
    void registerTest() {
        UserDto userDto = new UserDto();
        userDto.setUserName("test");
        userDto.setNic("11111");
        userDto.setFullName("test fullName");
        userDto.setLastName("test lastName");
        userDto.setTelephone("12121212");
        userDto.setAddress("test Address 2");
        userDto.setPassword("1111");
        userController.createUser(userDto);
    }

    @Test
    void editUserTest() {
        UserDto userDto = new UserDto();
        userDto.setNic("12221");
        userDto.setFullName("edit test fullName");
        userDto.setLastName("test lastName");
        userDto.setTelephone("333333333");
        userDto.setAddress("edit test Address");
        userController.editUser(2, userDto);
    }

    @Test
    void getUserTest() {
        int userId = 2;
        userController.getUser(userId);
    }

    @Test
    void otpRequestTest() {
        UserDto userDto = new UserDto();
        userDto.setTelephone("0112123123");
        userController.otpRequest(userDto);
    }

    @Test
    void otpResponseTest() {
        OtpRequestDto requestDto = new OtpRequestDto();
        requestDto.setOtpCode("9139");
        requestDto.setCacheKey("bf8ac9b6-4497-4729-af15-3f72d876c022");
        userController.otpResponse(requestDto);
    }

    @Test
    void otpResendTest() {
        UserDto userDto = new UserDto();
        userDto.setTelephone("0112123123");
        userController.otpResend(userDto);
    }
}
