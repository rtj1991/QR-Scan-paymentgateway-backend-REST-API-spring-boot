package com.online.mypay.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "otp_request")
@EntityListeners(AuditingEntityListener.class)
public class OtpRequest implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "otp_code")
    private String otpCode;

    @Column(name = "message")
    private String message;

    @Column(name = "cache_key")
    private String cacheKey;

    @Column(name = "phone_number")
    private String phoneNumber;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_send")
    private Date timestampSend;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_confirm")
    private Date timestampConfirm;

    @Column(name = "status")
    private int status;

}
