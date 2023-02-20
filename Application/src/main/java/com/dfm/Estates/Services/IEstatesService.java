package com.dfm.Estates.Services;

import com.dfm.Estates.Entities.EstateEntity;
import com.dfm.Estates.Entities.Dto.EstateResponseDto;
import com.dfm.Estates.Entities.Dto.EstatesResponseDto;

public interface IEstatesService
{
    public EstateResponseDto create(EstateEntity estate_entity);
    public int getId(String description);
    public EstateResponseDto readById(int id);
    public EstatesResponseDto readAll();
    public EstateResponseDto update(int id, EstateEntity estate_entity);
    public EstateResponseDto delete(int id);
}
