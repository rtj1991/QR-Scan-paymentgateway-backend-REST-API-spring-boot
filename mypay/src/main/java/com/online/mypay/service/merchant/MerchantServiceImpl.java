package com.online.mypay.service.merchant;

import com.online.mypay.dto.MerchantDto;
import com.online.mypay.model.Merchant;
import com.online.mypay.repository.MerchantRepository;
import com.online.mypay.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public Merchant createMerchant(MerchantDto merchantDto) {
        Merchant merchant = new Merchant();
        merchant.setName(merchantDto.getName());
        merchant.setDescription(merchantDto.getDescription());
        merchant.setAvailable(Const.AVAILABLE);
        return merchantRepository.save(merchant);
    }

    @Override
    public Merchant findById(int id) {
        return merchantRepository.findById(id).get();
    }
}
