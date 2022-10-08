package com.online.mypay.service.counter;

import com.online.mypay.dto.MerchantDto;
import com.online.mypay.model.Counter;
import com.online.mypay.model.Franchise;
import com.online.mypay.repository.CounterRepository;
import com.online.mypay.repository.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    private CounterRepository counterRepository;
    @Autowired
    private FranchiseRepository franchiseRepository;

    @Override
    public Counter createCounter(MerchantDto merchantDto) {
        Franchise franchise = franchiseRepository.findById(merchantDto.getFranchise()).get();
        Counter counter = new Counter();
        counter.setName(merchantDto.getName());
        counter.setFranchise(franchise);
        return counterRepository.save(counter);
    }

    @Override
    public Counter findById(int id) {
        return counterRepository.findById(id).get();
    }
}
