package com.dfm.Transfers.Entities.Dto;

import com.dfm.Entities.Dto.BaseResponseDto;
import com.dfm.Transfers.Entities.TransferEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TransfersResponseDto extends BaseResponseDto
{
    public List<TransferEntity> transfers;
}
