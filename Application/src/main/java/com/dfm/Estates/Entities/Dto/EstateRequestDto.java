package com.dfm.Estates.Entities.Dto;

import com.dfm.Estates.Entities.EstateEntity;

import jakarta.annotation.Nonnull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EstateRequestDto
{
    @Nonnull
    private EstateEntity estate;
}
