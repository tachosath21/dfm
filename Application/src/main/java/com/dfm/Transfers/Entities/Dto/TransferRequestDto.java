package com.dfm.Transfers.Entities.Dto;

import com.dfm.Transfers.Entities.TransferEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequestDto
{
    public TransferEntity transfer;
}
