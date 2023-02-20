package com.dfm.Estates.Entities.Dto;

import com.dfm.Entities.Dto.BaseResponseDto;
import com.dfm.Estates.Entities.EstateEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EstateResponseDto extends BaseResponseDto
{
    private EstateEntity estate;
}
