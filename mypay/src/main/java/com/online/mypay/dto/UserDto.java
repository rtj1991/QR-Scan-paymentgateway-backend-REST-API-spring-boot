package com.online.mypay.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserDto implements Serializable {
    private String userName;
    private String fullName;
    private String lastName;
    private String nic;
    private String telephone;
    private String address;
    private String password;
    private String holderName;
    private String cardName;
    private int cardNo;
    private int cvv;
}
