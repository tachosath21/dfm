package com.dfm.Transfers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dfm.Transfers.Entities.Dto.TransferRequestDto;
import com.dfm.Transfers.Entities.Dto.TransferResponseDto;
import com.dfm.Transfers.Entities.Dto.TransfersResponseDto;
import com.dfm.Transfers.Services.ITransfersService;

@RestController
@RequestMapping("/api/Transfers")
@RequiredArgsConstructor
public class TransfersController
{
    private final ITransfersService transfers_service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TransferResponseDto create(@RequestBody TransferRequestDto transfer_request_dto)
    {
        var transfer_response_dto = this.transfers_service.create(transfer_request_dto.getTransfer());

        return transfer_response_dto;
    }

    @GetMapping(value = "getId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int getId(@RequestParam String buyer_email, @RequestParam String estate_description)
    {
        var id = this.transfers_service.getId(buyer_email, estate_description);

        return id;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TransferResponseDto readById(@PathVariable int id)
    {
        var transfer_response_dto = this.transfers_service.readById(id);

        return transfer_response_dto;
    }
    
   

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TransfersResponseDto readAll()
    {
        var transfers_response_dto = this.transfers_service.readAll();

        return transfers_response_dto;
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TransferResponseDto update(@PathVariable int id, @RequestBody TransferRequestDto transfer_request_dto)
    {
        var transfer_response_dto = this.transfers_service.update(id, transfer_request_dto.getTransfer());

        return transfer_response_dto;
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TransferResponseDto delete(@PathVariable int id)
    {
        var transfer_response_dto = this.transfers_service.delete(id);

        return transfer_response_dto;
    }
}
