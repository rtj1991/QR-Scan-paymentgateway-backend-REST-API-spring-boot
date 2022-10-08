package com.online.mypay.repository;

import com.online.mypay.model.Payment;
import com.online.mypay.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {
    List<Payment>  findByPaymentUser(User user);
}
