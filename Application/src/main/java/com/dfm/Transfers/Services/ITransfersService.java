package com.dfm.Transfers.Services;

import com.dfm.Transfers.Entities.TransferEntity;
import com.dfm.Transfers.Entities.Dto.TransferResponseDto;
import com.dfm.Transfers.Entities.Dto.TransfersResponseDto;

public interface ITransfersService
{
    public TransferResponseDto create(TransferEntity transfer_entity);
    public int getId(String buyer_email, String estate_description);
    public TransferResponseDto readById(int id);
    public TransfersResponseDto readAll();
    public TransferResponseDto update(int id, TransferEntity transfer_entity);
    public TransferResponseDto delete(int id);
}
