package com.online.mypay.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MerchantDto implements Serializable {
    private String name;
    private String description;
    private int merchant;
    private int franchise;
    private int counter;
}
