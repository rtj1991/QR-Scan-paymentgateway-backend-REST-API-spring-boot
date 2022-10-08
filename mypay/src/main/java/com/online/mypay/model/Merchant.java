package com.online.mypay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "merchant")
@EntityListeners(AuditingEntityListener.class)
public class Merchant implements Serializable {
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

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "merchant",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Franchise> franchiseList;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "payment",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Payment> merchants;

}
