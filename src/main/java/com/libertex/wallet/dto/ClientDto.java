package com.libertex.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class ClientDto {
    Long id;
    @NotEmpty
    String name;
}
