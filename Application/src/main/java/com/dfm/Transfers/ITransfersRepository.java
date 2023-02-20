package com.dfm.Transfers;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dfm.Transfers.Entities.TransferModel;

public interface ITransfersRepository extends JpaRepository<TransferModel, Integer>
{
    @Query("""
        select      Transfers
        from        TransferModel Transfers
        inner join  UserModel Buyers
        on          Buyers.id = Transfers.buyer
        inner join  EstateModel Estates
        on          Estates.id = Transfers.estate
        where       Buyers.email = :buyer_email
        and         Estates.description = :estate_description""")
    public Optional<TransferModel> findByBuyerEstate(@Param("buyer_email") String buyer_email, @Param("estate_description") String estate_description);
}
