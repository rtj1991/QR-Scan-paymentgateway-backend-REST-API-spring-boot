package com.online.mypay.service.card;

import com.online.mypay.dto.UserDto;
import com.online.mypay.model.CardDetails;
import com.online.mypay.model.User;

import java.util.List;

public interface CardDetailsService {
    public CardDetails createCard(UserDto userDto, User user);
    public List<CardDetails> findByUser(User user);
}
