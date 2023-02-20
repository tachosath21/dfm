package com.dfm.Transfers.Services;

import java.util.ArrayList;
import java.util.Date;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import com.dfm.Users.IUsersRepository;

import lombok.RequiredArgsConstructor;

import com.dfm.Entities.ErrorEntity;
import com.dfm.Estates.IEstatesRepository;
import com.dfm.Transfers.ITransfersRepository;
import com.dfm.Transfers.Entities.TransferEntity;
import com.dfm.Transfers.Entities.TransferModel;
import com.dfm.Transfers.Entities.Dto.TransferResponseDto;
import com.dfm.Transfers.Entities.Dto.TransfersResponseDto;

@Service
@RequiredArgsConstructor
public class TransfersService implements ITransfersService
{
    private final ITransfersRepository transfers_repository;
    private final IUsersRepository users_repository;
    private final IEstatesRepository estates_repository;
    private final ModelMapper model_mapper;
    
    @Override
    public TransferResponseDto create(TransferEntity transfer_entity)
    {
        try
        {
            var new_transfer_model = this.model_mapper.map(transfer_entity, TransferModel.class);
            
            var notary_model_optional = this.users_repository.findByEmail(transfer_entity.getNotary().getEmail());

            if (notary_model_optional.isEmpty())
                throw new Exception("Estate not found");

            new_transfer_model.setNotary(notary_model_optional.get());

            var estate_model_optional = this.estates_repository.findByDescription(transfer_entity.getEstate().getDescription());

            if (estate_model_optional.isEmpty())
                throw new Exception("Estate not found");

            new_transfer_model.setEstate(estate_model_optional.get());

            var buyer_model_optional = this.users_repository.findByEmail(transfer_entity.getBuyer().getEmail());

            if (buyer_model_optional.isEmpty())
                throw new Exception("Buyer not found");

            new_transfer_model.setBuyer(buyer_model_optional.get());

            new_transfer_model.setDate_created(new Date());

            var saved_transfer_model = this.transfers_repository.save(new_transfer_model);
            
            var transfer_response_dto = TransferResponseDto
                .builder()
                .processed(true)
                .transfer(this.model_mapper.map(saved_transfer_model, TransferEntity.class))
                .build();

            return transfer_response_dto;
        }
        catch (Exception e)
        {
            var transfer_response_dto = TransferResponseDto
                .builder()
                .processed(false)
                .error(ErrorEntity
                    .builder()
                    .severity(1)
                    .message("Problem creating new Transfer")
                    .build())
                .build();

            return transfer_response_dto;
        }
    }

    @Override
    public int getId(String buyer_email, String estate_description)
    {
        try
        {
            var transfer_model_optional = this.transfers_repository.findByBuyerEstate(buyer_email, estate_description);
            if (transfer_model_optional.isEmpty())
                return -1;
            var transfer_model = transfer_model_optional.get();

            return transfer_model.getId();
        }
        catch (Exception e)
        {
            return -1;
        }
    }
    
    @Override
    public TransferResponseDto readById(int id)
    {
        try
        {
            var transfer_model = this.transfers_repository.getReferenceById(id);
            
            var transfer_response_dto = TransferResponseDto
                .builder()
                .processed(true)
                .transfer(this.model_mapper.map(transfer_model, TransferEntity.class))
                .build();

            return transfer_response_dto;
        }
        catch (Exception e)
        {
            var transfer_response_dto = TransferResponseDto
                .builder()
                .processed(false)
                .error(ErrorEntity
                    .builder()
                    .severity(1)
                    .message("Problem getting Transfer")
                    .build())
                .build();

            return transfer_response_dto;
        }
    }

    @Override
    public TransfersResponseDto readAll()
    {
        try
        {
            var transfer_models = this.transfers_repository.findAll();
            
            var transfer_entitys = new ArrayList<TransferEntity>();
            
            transfer_models.forEach(transfer_model ->
                transfer_entitys.add(this.model_mapper.map(transfer_model, TransferEntity.class)));
            
            var transfers_response_dto = TransfersResponseDto
                .builder()
                .processed(true)
                .transfers(transfer_entitys)
                .build();

            return transfers_response_dto;
        }
        catch (Exception e)
        {
            var transfers_response_dto = TransfersResponseDto
                .builder()
                .processed(false)
                .error(ErrorEntity
                    .builder()
                    .severity(1)
                    .message("Problem getting Transfers")
                    .build())
                .build();

            return transfers_response_dto;
        }
    }

    @Override
    public TransferResponseDto update(int id, TransferEntity transfer_entity)
    {
        try
        {
            var transfer_model_optional = this.transfers_repository.findById(id);

            if (transfer_model_optional.isEmpty())
                throw new Exception("Transfer not found");
            
            var transfer_model = this.model_mapper.map(transfer_entity, TransferModel.class);
            transfer_model.setId(id);

            var notary_model_optional = this.users_repository.findByEmail(transfer_entity.getNotary().getEmail());

            if (notary_model_optional.isEmpty())
                throw new Exception("Estate not found");

            transfer_model.setNotary(notary_model_optional.get());

            var estate_model_optional = this.estates_repository.findByDescription(transfer_entity.getEstate().getDescription());

            if (estate_model_optional.isEmpty())
                throw new Exception("Estate not found");

            transfer_model.setEstate(estate_model_optional.get());

            var buyer_model_optional = this.users_repository.findByEmail(transfer_entity.getBuyer().getEmail());

            if (buyer_model_optional.isEmpty())
                throw new Exception("Buyer not found");

            transfer_model.setBuyer(buyer_model_optional.get());

            var saved_transfer_model = this.transfers_repository.save(transfer_model);
            
            var transfer_response_dto = TransferResponseDto
                .builder()
                .processed(true)
                .transfer(this.model_mapper.map(saved_transfer_model, TransferEntity.class))
                .build();

            return transfer_response_dto;
        }
        catch (Exception e)
        {
            var transfer_response_dto = TransferResponseDto
                .builder()
                .processed(false)
                .error(ErrorEntity
                    .builder()
                    .severity(1)
                    .message("Problem updating Transfer")
                    .build())
                .build();

            return transfer_response_dto;
        }
    }

    @Override
    public TransferResponseDto delete(int id)
    {
        try
        {
            var transfer_model_optional = this.transfers_repository.findById(id);

            if (transfer_model_optional.isEmpty())
                throw new Exception();
            
            var transfer_model = transfer_model_optional.get();

            this.transfers_repository.delete(transfer_model);

            var transfer_response_dto = TransferResponseDto
                .builder()
                .processed(true)
                .transfer(this.model_mapper.map(transfer_model, TransferEntity.class))
                .build();

            return transfer_response_dto;
        }
        catch (Exception e)
        {
            var transfer_response_dto = TransferResponseDto
                .builder()
                .processed(false)
                .error(ErrorEntity
                    .builder()
                    .severity(1)
                    .message("Problem deleting Transfer")
                    .build())
                .build();

            return transfer_response_dto;
        }
    }    
}
