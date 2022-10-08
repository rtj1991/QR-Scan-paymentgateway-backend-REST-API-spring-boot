package com.online.mypay.service.counter;

import com.online.mypay.dto.MerchantDto;
import com.online.mypay.model.Counter;
import com.online.mypay.model.Franchise;

public interface CounterService {
    public Counter createCounter(MerchantDto merchantDto);
    public Counter findById(int id);

}
