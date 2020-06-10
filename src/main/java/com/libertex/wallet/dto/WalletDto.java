package com.libertex.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Validated
public class WalletDto {
    @NotNull
    Long idClient;

    @Digits(integer=20, fraction=2)
    @PositiveOrZero
    BigDecimal money;
}
