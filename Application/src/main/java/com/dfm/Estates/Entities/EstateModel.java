package com.dfm.Estates.Entities;

import com.dfm.Users.Entities.UserModel;

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

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "Estates",
    uniqueConstraints = {@UniqueConstraint(name = "unique_estate", columnNames = {"description"})})
public class EstateModel
{
    @Id
    @GeneratedValue
    private int id;
    private String description;
    private double price;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private UserModel seller;
}
