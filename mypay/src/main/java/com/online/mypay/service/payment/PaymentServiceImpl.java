package com.online.mypay.service.payment;

import com.online.mypay.dto.PaymentDto;
import com.online.mypay.model.Franchise;
import com.online.mypay.model.Merchant;
import com.online.mypay.model.Payment;
import com.online.mypay.model.User;
import com.online.mypay.repository.FranchiseRepository;
import com.online.mypay.repository.MerchantRepository;
import com.online.mypay.repository.PaymentRepository;
import com.online.mypay.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private FranchiseRepository franchiseRepository;

    @Override
    public Payment createPayment(PaymentDto paymentDto) {
        Merchant merchant = merchantRepository.findById(paymentDto.getMerchant()).get();
        Franchise franchise = franchiseRepository.findById(paymentDto.getFranchise()).get();
        Payment payment = new Payment();
        payment.setInvoiceNo(payment.getInvoiceNo());
        payment.setPaidAmount(paymentDto.getTotalAmount());
        payment.setPaidStatus(Const.SUCCESS);
        payment.setCounterUser(paymentDto.getCounterUser());
        payment.setPayment(merchant);
        payment.setFranchise_payment(franchise);
        payment.setCardNo(Integer.parseInt(paymentDto.getCardNo()));
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPaymentByUser(User byUsername) {
        return paymentRepository.findByPaymentUser(byUsername);
    }
}
