package com.libertex.wallet.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(
            value = "Customer Id")
    private Long id;

    @Column(name="name")
    @ApiModelProperty(value = "Customer name")
    private String name;

    @Digits(integer=20, fraction=2)
    @PositiveOrZero
    @Column(name="wallet", nullable = false, precision = 20, scale = 2)
    @ApiModelProperty(value = "Customer wallet")
    private BigDecimal wallet;
}
