package com.online.mypay.repository;

import com.online.mypay.model.Franchise;
import com.online.mypay.model.Merchant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends CrudRepository<Franchise,Integer> {
}
