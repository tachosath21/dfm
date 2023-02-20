package com.dfm.Transfers.Entities;

import java.util.Date;

import com.dfm.Estates.Entities.EstateEntity;
import com.dfm.Users.Entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TransferEntity
{
    private UserEntity notary;
    private UserEntity buyer;
    private EstateEntity estate;
    private Date date_created;
    private boolean has_seller_signed;
    private boolean has_buyer_signed;
    private boolean is_finalized;
    private Date date_finalized;
    private boolean is_payed;
    private boolean is_posted;
}
