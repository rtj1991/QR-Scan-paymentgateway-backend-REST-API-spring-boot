package com.online.mypay.service.payment;

import com.online.mypay.dto.PaymentDto;
import com.online.mypay.model.Payment;
import com.online.mypay.model.User;

import java.util.List;

public interface PaymentService {
    Payment createPayment(PaymentDto paymentDto);

    public List<Payment> getPaymentByUser(User byUsername);
}
