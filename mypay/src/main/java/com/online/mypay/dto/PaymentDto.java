package com.online.mypay.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaymentDto implements Serializable {
    private int merchant;
    private int franchise;
    private int counter;
    private String counterUser;
    private double totalAmount;
    private String invoiceNo;
    private String cardNo;
}
