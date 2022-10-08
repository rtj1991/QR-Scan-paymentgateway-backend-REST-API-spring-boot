package com.online.mypay.service.merchant;

import com.online.mypay.dto.MerchantDto;
import com.online.mypay.dto.UserDto;
import com.online.mypay.model.Merchant;
import com.online.mypay.model.User;

public interface MerchantService {
    public Merchant createMerchant(MerchantDto merchantDto);
    public Merchant findById(int id);
}
