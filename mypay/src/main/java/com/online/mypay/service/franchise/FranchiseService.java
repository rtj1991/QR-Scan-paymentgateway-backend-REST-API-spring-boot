package com.online.mypay.service.franchise;

import com.online.mypay.dto.MerchantDto;
import com.online.mypay.model.Franchise;

public interface FranchiseService {
    public Franchise createFranchise(MerchantDto merchantDto);
    public Franchise findById(int id);
}
