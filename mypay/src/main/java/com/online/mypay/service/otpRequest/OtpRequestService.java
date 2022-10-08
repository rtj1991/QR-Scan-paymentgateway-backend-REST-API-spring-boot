package com.online.mypay.service.otpRequest;

import com.online.mypay.dto.OtpRequestDto;
import com.online.mypay.model.OtpRequest;

public interface OtpRequestService {
    public OtpRequestDto createOtp(String phoneNumber);
    public OtpRequest findByOtpAndCacheKey(OtpRequestDto otpRequestDto);
    public boolean requestFailed(OtpRequestDto requestDto);
    public OtpRequest findByPhoneNumber(String phoneNumber);

    OtpRequestDto resendOtp(String telephone);
}
