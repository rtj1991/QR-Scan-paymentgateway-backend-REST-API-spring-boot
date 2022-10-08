package com.online.mypay;

import com.online.mypay.controller.PaymentController;
import com.online.mypay.dto.MerchantDto;
import com.online.mypay.dto.PaymentDto;
import com.online.mypay.model.Franchise;
import com.online.mypay.model.Merchant;
import com.online.mypay.model.Payment;
import com.online.mypay.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentControllerTest {
    @Autowired
    private PaymentController paymentController;

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    void createMerchantTest() {
        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setMerchant(8);
        merchantDto.setFranchise(9);
        merchantDto.setCounter(10);
        paymentController.viewMerchant(merchantDto);
    }

    @Test
    void confirmPaymentTest() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setMerchant(8);
        paymentDto.setFranchise(9);
        paymentDto.setCounterUser("2");
        paymentDto.setTotalAmount(100.0);
        paymentDto.setInvoiceNo("2");
        paymentDto.setCardNo("2222222");
        paymentController.confirmPayment(paymentDto);
    }
    @Test
    void getAllPaymentTest() {
        paymentController.getAllPayment();
    }

    @Test
    void insertPaymentTest() {
        Payment paymentDto = new Payment();
        Merchant merchant = new Merchant();
        merchant.setId(8);

        Franchise franchise = new Franchise();
        franchise.setId(9);

        paymentDto.setPayment(merchant);
        paymentDto.setFranchise_payment(franchise);
        paymentDto.setCounterUser("2");
        paymentDto.setPaidAmount(100.0);
        paymentDto.setInvoiceNo("2");
        paymentDto.setCardNo(2222222);
        paymentRepository.save(paymentDto);
    }

}
