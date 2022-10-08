package com.online.mypay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@Table(name = "franchise")
@EntityListeners(AuditingEntityListener.class)
public class Franchise implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "available")
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "merchant",referencedColumnName = "id")
    private Merchant merchant;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "franchise_payment",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Payment> payments;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "franchise",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Counter> counters;
}
