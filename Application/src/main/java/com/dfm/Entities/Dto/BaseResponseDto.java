package com.dfm.Entities.Dto;

import com.dfm.Entities.ErrorEntity;

import jakarta.annotation.Nonnull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseResponseDto
{
    @Nonnull
    private Boolean processed;
    private ErrorEntity error;
}
