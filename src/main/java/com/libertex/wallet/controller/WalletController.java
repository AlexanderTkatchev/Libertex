package com.libertex.wallet.controller;

import com.libertex.wallet.dto.WalletDto;
import com.libertex.wallet.service.WalletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Log4j2
@RestController
@Api(value = "WalletController", tags = {"Operations with wallet"})
@RequestMapping("/wallet")
@Validated
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get client wallet value by client ID", response = BigDecimal.class)
    public BigDecimal getClientWalletStatusById(@PathVariable final Long id) {
        log.debug("Get client wallet value by client ID = " + id);
        return walletService.getWalletMoneyByClientId(id);
    }

    @PutMapping("/add")
    @ApiOperation(value = "Add money to client wallet", response = WalletDto.class)
    public WalletDto addToWalletByClientId(@RequestBody @Validated final WalletDto wallet) {
        return walletService.addToWallet(wallet);
    }

    @PutMapping("/sub")
    @ApiOperation(value = "Sub money from client wallet", response = WalletDto.class)
    public WalletDto subFromWalletByClientId(@RequestBody @Validated final WalletDto wallet) {
        return walletService.subFromWallet(wallet);
    }
}
