package com.online.mypay.controller;

import com.online.mypay.dto.OtpRequestDto;
import com.online.mypay.dto.UserDto;
import com.online.mypay.model.CardDetails;
import com.online.mypay.model.OtpRequest;
import com.online.mypay.model.User;
import com.online.mypay.service.card.CardDetailsService;
import com.online.mypay.service.encryptdecrypt.EncryptDecryptService;
import com.online.mypay.service.otpRequest.OtpRequestService;
import com.online.mypay.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/register")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());
    @Autowired
    private UserService userService;
    @Autowired
    private OtpRequestService otpRequestService;
    @Autowired
    private CardDetailsService cardDetailsService;

    @Autowired
    private EncryptDecryptService encryptDecryptService;

    @RequestMapping(value = "/otpRequest", method = RequestMethod.POST)
    public ResponseEntity<String> otpRequest(@RequestBody UserDto userDto) {
        try {
            LOGGER.info("Otp Request /Otp/request {} {}", "/Otp/request", userDto.getTelephone());
            User phoneNumber = userService.findByPhoneNumber(userDto.getTelephone());

            if (phoneNumber == null || phoneNumber.equals(null)) {
                OtpRequestDto otpRequestDto = otpRequestService.createOtp(userDto.getTelephone());
//                use the SMS Gateway and need to send OTP

                return new ResponseEntity<>(otpRequestDto.getCacheKey(), HttpStatus.OK);
            } else {
                LOGGER.info("user creation failed {}", userDto.getTelephone());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("user creation failed!");
            }
        } catch (Exception e) {
            LOGGER.info("user creation failed {}", userDto.getTelephone());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("user creation failed!");
        }
    }

    @RequestMapping(value = "/otpResponse", method = RequestMethod.POST)
    public ResponseEntity<String> otpResponse(@RequestBody OtpRequestDto requestDto) {
        try {
            LOGGER.info("Otp Response /Otp/Response {} {}", "/Otp/Response", requestDto);
            OtpRequest response = otpRequestService.findByOtpAndCacheKey(requestDto);
            Date timestampSend = response.getTimestampSend();
            long sendTime = timestampSend.getTime();
            Date currentDate = new Date(sendTime);

            long datediff = new Date().getTime() - currentDate.getTime();

            if (response != null) {
                if (datediff < 120000) {
                    LOGGER.info("user Otp Response success {}", requestDto);
                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    otpRequestService.requestFailed(requestDto);
                    LOGGER.info("user Otp Response Timeout {}", requestDto);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("otp response Timeout!");
                }

            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("otp response failed!");
            }
        } catch (Exception e) {
            LOGGER.info("user Otp Response failed {}", requestDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("otp response failed!");
        }
    }

    @RequestMapping(value = "/otpResend", method = RequestMethod.POST)
    public ResponseEntity<String> otpResend(@RequestBody UserDto userDto) {
        try {
            LOGGER.info("Otp Request /Otp/request {} {}", "/Otp/request", userDto.getTelephone());
            User phoneNumber = userService.findByPhoneNumber(userDto.getTelephone());

            if (phoneNumber != null || !phoneNumber.equals(null)) {
                OtpRequestDto otpRequestDto = otpRequestService.resendOtp(userDto.getTelephone());
//                use the SMS Gateway and need to send OTP

                return new ResponseEntity<>(otpRequestDto.getCacheKey(), HttpStatus.OK);
            } else {
                LOGGER.info("user creation failed {}", userDto.getTelephone());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("user creation failed!");
            }
        } catch (Exception e) {
            LOGGER.info("user creation failed {}", userDto.getTelephone());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("user creation failed!");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {

        try {
            LOGGER.info("Add User /user/add {} {}", "/user/add", userDto);

            if (userDto != null) {
                User phoneNumber = userService.findByPhoneNumber(userDto.getTelephone());
                User byUsername = userService.findByUsername(userDto.getUserName());
                if (byUsername == null) {
                    if (phoneNumber == null) {
                        User user = userService.createUser(userDto);
                        CardDetails card = cardDetailsService.createCard(userDto, user);
                        if (user != null && card != null) {
                            return new ResponseEntity(HttpStatus.CREATED);
                        }
                    } else {
                        LOGGER.info("user creation failed {}", userDto);
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("user creation failed!");
                    }
                } else {
                    LOGGER.info("user creation failed {}", userDto);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("username already exists!");
                }

            } else {
                LOGGER.info("userDto cant be empty {}", userDto);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("userDto cant be empty!");
            }

        } catch (Exception e) {
            LOGGER.info("user creation failed {}", userDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("user creation failed!");
        }
        LOGGER.info("user creation failed {}", userDto);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("user creation failed!");
    }

    @RequestMapping(value = "/editUser/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> editUser(@PathVariable int id, @RequestBody UserDto userDto) {
        try {
            LOGGER.info("Edit User /user/edit {} {}", "/user/edit", userDto);
            if (userDto != null) {
                User userDetails = userService.findById(id);
                if (userDetails != null) {
                    User user = userService.editUser(userDetails, userDto);
                    if (user != null) {
                        return new ResponseEntity(HttpStatus.CREATED);
                    }
                } else {
                    LOGGER.info("user update failed {}", userDto);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("user update failed!");
                }
            } else {
                LOGGER.info("userDto cant be empty {}", userDto);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("userDto cant be empty!");
            }

        } catch (Exception e) {
            LOGGER.info("user update failed {}", userDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("user update failed!");
        }
        LOGGER.info("user update failed {}", userDto);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("user update failed!");
    }

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.POST)
    public ResponseEntity<User> getUser(@PathVariable int id) {
        try {
            LOGGER.info("Get User /user/get {} {}", "/user/get", id);
            if (id != 0) {
                User userDetails = userService.findById(id);
                if (userDetails != null) {
                    return new ResponseEntity<>(userDetails, HttpStatus.CREATED);
                } else {
                    LOGGER.info("user not existed {}", id);
                }
            } else {
                LOGGER.info("id cant be empty {}", id);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            LOGGER.info("user not existed {}", id);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("user not existed {}", id);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
