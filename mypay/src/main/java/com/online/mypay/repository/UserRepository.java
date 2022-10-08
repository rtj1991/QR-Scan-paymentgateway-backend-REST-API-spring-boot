package com.online.mypay.repository;

import com.online.mypay.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    User findByUserName(String email);
    User findByRecoveryPhone(String phoneNumber);
}