package com.online.mypay.service.otpRequest;

import com.online.mypay.dto.OtpRequestDto;
import com.online.mypay.model.OtpRequest;
import com.online.mypay.repository.OtpRequestRepository;
import com.online.mypay.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class OtpRequestServiceImpl implements OtpRequestService {
    @Autowired
    private OtpRequestRepository otpRequestRepository;

    @Override
    public OtpRequestDto createOtp(String phoneNumber) {
        int randomPin = (int) (Math.random() * 9000) + 1000;
        String otp = String.valueOf(randomPin);

        UUID randomUUID = UUID.randomUUID();
        String cacheKey = randomUUID.toString().replaceAll("_", "");
        OtpRequestDto otpRequestDto = new OtpRequestDto();

        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setOtpCode(otp);
        otpRequest.setCacheKey(cacheKey);
        otpRequest.setMessage("Register for User");
        otpRequest.setPhoneNumber(phoneNumber);
        otpRequest.setStatus(Const.OTP_SEND);
        OtpRequest request = otpRequestRepository.save(otpRequest);
        if (request != null) {
            otpRequestDto.setOtpCode(otpRequest.getOtpCode());
            otpRequestDto.setCacheKey(otpRequest.getCacheKey());
        }
        return otpRequestDto;
    }

    @Override
    public OtpRequest findByOtpAndCacheKey(OtpRequestDto otpRequestDto) {
        OtpRequest response = otpRequestRepository.findByOtpCodeAndCacheKey(otpRequestDto.getOtpCode(), otpRequestDto.getCacheKey());
        if (response != null) {
            response.setStatus(Const.OTP_CONFIRM);
            response.setTimestampConfirm(new Date());
            otpRequestRepository.save(response);
            return response;
        }
        return new OtpRequest();
    }

    @Override
    public boolean requestFailed(OtpRequestDto otpRequestDto) {
        OtpRequest response = otpRequestRepository.findByOtpCodeAndCacheKey(otpRequestDto.getOtpCode(), otpRequestDto.getCacheKey());
        response.setStatus(Const.OTP_FAILED);
        OtpRequest request = otpRequestRepository.save(response);
        if (request != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public OtpRequest findByPhoneNumber(String phoneNumber) {
        return otpRequestRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public OtpRequestDto resendOtp(String telephone) {
        OtpRequest otpRequest = findByPhoneNumber(telephone);

        Date timestampSend = otpRequest.getTimestampSend();
        long sendTime = timestampSend.getTime();
        Date currentDate = new Date(sendTime);

        long datediff = new Date().getTime() - currentDate.getTime();

        if (otpRequest.getStatus() == Const.OTP_FAILED || datediff > 120000) {
            int randomPin = (int) (Math.random() * 9000) + 1000;
            String otp = String.valueOf(randomPin);

            UUID randomUUID = UUID.randomUUID();
            String cacheKey = randomUUID.toString().replaceAll("_", "");
            OtpRequestDto otpRequestDto = new OtpRequestDto();

            otpRequest.setOtpCode(otp);
            otpRequest.setCacheKey(cacheKey);
            otpRequest.setMessage("Register for User Resend OTP");
            otpRequest.setPhoneNumber(telephone);
            otpRequest.setTimestampSend(new Date());
            otpRequest.setStatus(Const.OTP_SEND);
            OtpRequest request = otpRequestRepository.save(otpRequest);
            if (request != null) {
                otpRequestDto.setOtpCode(otpRequest.getOtpCode());
                otpRequestDto.setCacheKey(otpRequest.getCacheKey());
            }
            return otpRequestDto;
        }
        return new OtpRequestDto();
    }
}
