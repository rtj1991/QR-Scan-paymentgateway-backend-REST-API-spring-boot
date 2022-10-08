package com.online.mypay.repository;

import com.online.mypay.model.OtpRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRequestRepository extends CrudRepository<OtpRequest,Integer> {
    OtpRequest findByOtpCodeAndCacheKey(String otp,String cacheKey);
    OtpRequest findByPhoneNumber(String phoneNumber);
}
