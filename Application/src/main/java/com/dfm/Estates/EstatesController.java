package com.dfm.Estates;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dfm.Estates.Entities.Dto.EstateRequestDto;
import com.dfm.Estates.Entities.Dto.EstateResponseDto;
import com.dfm.Estates.Entities.Dto.EstatesResponseDto;
import com.dfm.Estates.Services.IEstatesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/Estates")
@RequiredArgsConstructor
public class EstatesController
{
    private final IEstatesService estates_service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EstateResponseDto create(@RequestBody EstateRequestDto estate_request_dto)
    {
        var estate_response_dto = this.estates_service.create(estate_request_dto.getEstate());

        return estate_response_dto;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EstateResponseDto readById(@PathVariable int id)
    {
        var estate_response_dto = this.estates_service.readById(id);

        return estate_response_dto;
    }

    @GetMapping(value = "getId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int getId(@RequestParam String description)
    {
        var id = this.estates_service.getId(description);

        return id;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EstatesResponseDto readAll()
    {
        var estates_response_dto = this.estates_service.readAll();

        return estates_response_dto;
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EstateResponseDto update(@PathVariable int id, @RequestBody EstateRequestDto estate_request_dto)
    {
        var estate_response_dto = this.estates_service.update(id, estate_request_dto.getEstate());

        return estate_response_dto;
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EstateResponseDto delete(@PathVariable int id)
    {
        var estate_response_dto = this.estates_service.delete(id);

        return estate_response_dto;
    }
}
