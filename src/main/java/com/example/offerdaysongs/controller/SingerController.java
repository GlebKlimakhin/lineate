package com.example.offerdaysongs.controller;

import com.example.offerdaysongs.dto.SingerDto;
import com.example.offerdaysongs.dto.requests.CreateSingerRequest;
import com.example.offerdaysongs.model.Singer;
import com.example.offerdaysongs.service.SingerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/singers")
public class SingerController {
    private static final String ID = "id";
    private final SingerService signerService;

    public SingerController(SingerService signerService)
    {
        this.signerService = signerService;
    }

    @GetMapping("/{id:[\\d]+}")
    public SingerDto get(@PathVariable(ID) long id) {
        var singer = signerService.getById(id);
        return convertToDto(singer);
    }

    @GetMapping("/")
    public List<SingerDto> getAll() {
        var singers = signerService.getAllSingers();
        return singers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public SingerDto create(@RequestBody CreateSingerRequest request) {
        return convertToDto(signerService.create(request));
    }



    private SingerDto convertToDto(Singer singer)
    {
        return new SingerDto(singer.getId(), singer.getName());
    }
}
