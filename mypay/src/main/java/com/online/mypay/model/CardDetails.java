package com.online.mypay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.mypay.service.encryptdecrypt.EncryptDecryptService;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "card_details")
@EntityListeners(AuditingEntityListener.class)
public class CardDetails implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "holder_name")
    private String holderName;

    @Convert(converter = EncryptDecryptService.class)
    @Column(name = "cvv")
    private int cvv;

    @Column(name = "card_name")
    private String cardName;

    @Convert(converter = EncryptDecryptService.class)
    @Column(name = "card_no")
    private int cardNo;

    @ManyToOne
    @JoinColumn(name = "cardUser", referencedColumnName = "id")
    private User cardUser;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_created")
    private Date timestampCreated;
}
