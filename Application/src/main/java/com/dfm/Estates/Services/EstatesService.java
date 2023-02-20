package com.dfm.Estates.Services;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import com.dfm.Users.IUsersRepository;

import lombok.RequiredArgsConstructor;

import com.dfm.Entities.ErrorEntity;
import com.dfm.Estates.IEstatesRepository;
import com.dfm.Estates.Entities.EstateEntity;
import com.dfm.Estates.Entities.EstateModel;
import com.dfm.Estates.Entities.Dto.EstateResponseDto;
import com.dfm.Estates.Entities.Dto.EstatesResponseDto;

@Service
@RequiredArgsConstructor
public class EstatesService implements IEstatesService
{
    private final IEstatesRepository estates_repository;
    private final IUsersRepository users_repository;
    private final ModelMapper model_mapper;
    
    @Override
    public EstateResponseDto create(EstateEntity estate_entity)
    {
        try
        {
            var new_estate_model = this.model_mapper.map(estate_entity, EstateModel.class);

            var seller_model_optional = this.users_repository.findByEmail(estate_entity.getSeller().getEmail());

            if (seller_model_optional.isEmpty())
                throw new Exception("Seller not found");

            new_estate_model.setSeller(seller_model_optional.get());

            var saved_estate_model = this.estates_repository.save(new_estate_model);
            
            var estate_response_dto = EstateResponseDto
                .builder()
                .processed(true)
                .estate(this.model_mapper.map(saved_estate_model, EstateEntity.class))
                .build();

            return estate_response_dto;
        }
        catch (Exception e)
        {
            var estate_response_dto = EstateResponseDto
                .builder()
                .processed(false)
                .error(ErrorEntity
                    .builder()
                    .severity(1)
                    .message("Problem creating new Estate")
                    .build())
                .build();

            return estate_response_dto;
        }
    }

    @Override
    public int getId(String description)
    {
        try
        {
            var estate_model = this.estates_repository.findByDescription(description);

            if (estate_model.isPresent())
                return estate_model.get().getId();
            else
                return -1;
        }
        catch (Exception e)
        {
            return -1;
        }
    }
    
    @Override
    public EstateResponseDto readById(int id)
    {
        try
        {
            var estate_model = this.estates_repository.getReferenceById(id);
            
            var estate_response_dto = EstateResponseDto
                .builder()
                .processed(true)
                .estate(this.model_mapper.map(estate_model, EstateEntity.class))
                .build();

            return estate_response_dto;
        }
        catch (Exception e)
        {
            var estate_response_dto = EstateResponseDto
                .builder()
                .processed(false)
                .error(ErrorEntity
                    .builder()
                    .severity(1)
                    .message("Problem getting Estate")
                    .build())
                .build();

            return estate_response_dto;
        }
    }

    @Override
    public EstatesResponseDto readAll()
    {
        try
        {
            var estate_models = this.estates_repository.findAll();
            
            var estate_entities = new ArrayList<EstateEntity>();
            
            estate_models.forEach(estate_model ->
                estate_entities.add(this.model_mapper.map(estate_model, EstateEntity.class)));
            
            var estates_response_dto = EstatesResponseDto
                .builder()
                .processed(true)
                .estates(estate_entities)
                .build();

            return estates_response_dto;
        }
        catch (Exception e)
        {
            var estates_response_dto = EstatesResponseDto
                .builder()
                .processed(false)
                .error(ErrorEntity
                    .builder()
                    .severity(1)
                    .message("Problem getting Estates")
                    .build())
                .build();

            return estates_response_dto;
        }
    }

    @Override
    public EstateResponseDto update(int id, EstateEntity estate_request_dto)
    {
        try
        {
            var estate_model_optional = this.estates_repository.findById(id);

            if (estate_model_optional.isEmpty())
                throw new Exception("Estate not found");
            
            var estate_model = this.model_mapper.map(estate_request_dto, EstateModel.class);
            estate_model.setId(id);

            if (estate_request_dto.getSeller() != null)
            {
                var seller_model_optional = this.users_repository.findByEmail(estate_request_dto.getSeller().getEmail());

                if (seller_model_optional.isPresent())
                    estate_model.setSeller(seller_model_optional.get());
                else
                    estate_model.setSeller(null);
            }

            var saved_estate_model = this.estates_repository.save(estate_model);
            
            var estate_response_dto = EstateResponseDto
                .builder()
                .processed(true)
                .estate(this.model_mapper.map(saved_estate_model, EstateEntity.class))
                .build();

            return estate_response_dto;
        }
        catch (Exception e)
        {
            var estate_response_dto = EstateResponseDto
                .builder()
                .processed(false)
                .error(ErrorEntity
                    .builder()
                    .severity(1)
                    .message("Problem updating Estate")
                    .build())
                .build();

            return estate_response_dto;
        }
    }

    @Override
    public EstateResponseDto delete(int id)
    {
        try
        {
            var estate_model_optional = this.estates_repository.findById(id);

            if (estate_model_optional.isEmpty())
                throw new Exception();
            
            var estate_model = estate_model_optional.get();

            this.estates_repository.delete(estate_model);

            var estate_response_dto = EstateResponseDto
                .builder()
                .processed(true)
                .estate(this.model_mapper.map(estate_model, EstateEntity.class))
                .build();

            return estate_response_dto;
        }
        catch (Exception e)
        {
            var estate_response_dto = EstateResponseDto
                .builder()
                .processed(false)
                .error(ErrorEntity
                    .builder()
                    .severity(1)
                    .message("Problem deleting Estate")
                    .build())
                .build();

            return estate_response_dto;
        }
    }    
}
