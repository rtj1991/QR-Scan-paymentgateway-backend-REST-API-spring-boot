package com.online.mypay;

import com.online.mypay.controller.MerchantController;
import com.online.mypay.dto.MerchantDto;
import com.online.mypay.model.CardDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MerchantControllerTest {
    @Autowired
    private MerchantController merchantController;

    @Test
    void createMerchantTest() {
        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setName("test merchant");
        merchantDto.setDescription("test description");
        merchantController.createMerchant(merchantDto);
    }

    @Test
    void franchiseRegisterTest() {
        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setName("test franchise");
        merchantDto.setDescription("test description");
        merchantDto.setMerchant(8);
        merchantController.franchiseRegister(merchantDto);
    }
    @Test
    void counterRegisterTest() {
        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setName("test counter");
        merchantDto.setFranchise(9);
        merchantController.franchiseRegister(merchantDto);
    }
}
