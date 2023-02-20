package com.dfm.Estates.Entities.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

import com.dfm.Entities.Dto.BaseResponseDto;
import com.dfm.Estates.Entities.EstateEntity;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EstatesResponseDto extends BaseResponseDto
{
    private List<EstateEntity> estates;
}
