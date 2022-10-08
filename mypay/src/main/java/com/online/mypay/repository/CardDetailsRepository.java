package com.online.mypay.repository;

import com.online.mypay.model.CardDetails;
import com.online.mypay.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardDetailsRepository extends CrudRepository<CardDetails,Integer> {
    List<CardDetails> findByCardUser(User user);
}
