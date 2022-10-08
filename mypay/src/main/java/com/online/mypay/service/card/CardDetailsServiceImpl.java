package com.online.mypay.service.card;

import com.online.mypay.dto.UserDto;
import com.online.mypay.model.CardDetails;
import com.online.mypay.model.User;
import com.online.mypay.repository.CardDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardDetailsServiceImpl implements CardDetailsService{
    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    @Override
    public CardDetails createCard(UserDto userDto, User user) {
        CardDetails cardDetails = new CardDetails();
        cardDetails.setHolderName(userDto.getHolderName());
        cardDetails.setCvv(userDto.getCvv());
        cardDetails.setCardName(userDto.getCardName());
        cardDetails.setCardNo(userDto.getCardNo());
        cardDetails.setCardUser(user);
        CardDetails details = cardDetailsRepository.save(cardDetails);
        return details;
    }

    @Override
    public List<CardDetails> findByUser(User user) {
        return cardDetailsRepository.findByCardUser(user);
    }
}
