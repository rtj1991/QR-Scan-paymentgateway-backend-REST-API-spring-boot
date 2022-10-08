package com.online.mypay.service.franchise;

import com.online.mypay.dto.MerchantDto;
import com.online.mypay.model.Franchise;
import com.online.mypay.model.Merchant;
import com.online.mypay.repository.FranchiseRepository;
import com.online.mypay.repository.MerchantRepository;
import com.online.mypay.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FranchiseServiceImpl implements FranchiseService {
    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private FranchiseRepository franchiseRepository;

    @Override
    public Franchise createFranchise(MerchantDto merchantDto) {
        Merchant merchant = merchantRepository.findById(merchantDto.getMerchant()).get();
        Franchise franchise = new Franchise();
        franchise.setName(merchantDto.getName());
        franchise.setDescription(merchantDto.getDescription());
        franchise.setAvailable(Const.AVAILABLE);
        franchise.setMerchant(merchant);
        return franchiseRepository.save(franchise);

    }

    @Override
    public Franchise findById(int id) {
        return franchiseRepository.findById(id).get();
    }
}
