package com.online.mypay.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OtpRequestDto implements Serializable {
    private String otpCode;
    private String cacheKey;

}
