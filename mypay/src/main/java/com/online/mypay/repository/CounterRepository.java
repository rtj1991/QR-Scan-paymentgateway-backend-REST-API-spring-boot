package com.online.mypay.repository;

import com.online.mypay.model.Counter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends CrudRepository<Counter,Integer> {
}
