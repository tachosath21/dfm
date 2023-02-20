package com.dfm.Transfers.Entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import com.dfm.Estates.Entities.EstateModel;
import com.dfm.Users.Entities.UserModel;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "Transfers",
    uniqueConstraints = {@UniqueConstraint(name = "unique_transfer", columnNames = {"buyer_id", "estate_id", "is_finalized"})})
public class TransferModel
{
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "notary_id")
    private UserModel notary;
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private UserModel buyer;
    @ManyToOne
    @JoinColumn(name = "estate_id")
    private EstateModel estate;
    private Date date_created;
    private boolean has_seller_signed;
    private boolean has_buyer_signed;
    private boolean is_finalized;
    private Date date_finalized;
    private boolean is_payed;
    private boolean is_posted;
}
