package com.online.mypay;

import com.online.mypay.controller.LoginController;
import com.online.mypay.controller.UserController;
import com.online.mypay.dto.OtpRequestDto;
import com.online.mypay.dto.UserDto;
import com.online.mypay.model.CardDetails;
import com.online.mypay.repository.CardDetailsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MypayApplicationTests {

    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    @Test
    void insertCardTest() {
        CardDetails cardDetail = new CardDetails();
        cardDetail.setHolderName("Thara");
        cardDetail.setCvv(234);
        cardDetail.setCardName("master");
        cardDetail.setCardNo(23232323);

        cardDetailsRepository.save(cardDetail);
    }

    @Test
    void retrieveCardTest() {
        CardDetails cardDetail = cardDetailsRepository.findById(5).get();
        System.out.println(cardDetail);
    }

}
