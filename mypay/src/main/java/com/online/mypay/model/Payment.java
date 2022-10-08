package com.online.mypay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.mypay.service.encryptdecrypt.EncryptDecryptService;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "payment")
@EntityListeners(AuditingEntityListener.class)
public class Payment implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "invoice_no")
    private String invoiceNo;

    @Column(name = "paid_amount")
    private double paidAmount;

    @Column(name = "paid_status")
    private double paidStatus;

    @Column(name = "counter_user")
    private String counterUser;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_created")
    private Date timestampCreated;

    @ManyToOne
    @JoinColumn(name = "payment",referencedColumnName = "id")
    private Merchant payment;

    @ManyToOne
    @JoinColumn(name = "franchise_payment",referencedColumnName = "id")
    private Franchise franchise_payment;

    @Convert(converter = EncryptDecryptService.class)
    @Column(name = "card_no")
    private int cardNo;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_user")
    @JsonIgnore
    private User paymentUser;
}
