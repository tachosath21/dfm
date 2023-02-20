package com.dfm.Estates.Entities;

import com.dfm.Users.Entities.UserEntity;

import jakarta.annotation.Nonnull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EstateEntity
{
    @Nonnull
    private String description;
    private double price;
    private UserEntity seller;
}
