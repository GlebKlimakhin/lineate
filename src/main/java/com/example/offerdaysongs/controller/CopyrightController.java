package com.example.offerdaysongs.controller;

import com.example.offerdaysongs.dto.CopyrightDto;
import com.example.offerdaysongs.dto.requests.CreateCopyrightRequest;
import com.example.offerdaysongs.dto.requests.UpdateCopyrightRequest;
import com.example.offerdaysongs.model.Copyright;
import com.example.offerdaysongs.service.CopyrightService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/copyrights")
@RequiredArgsConstructor
public class CopyrightController {

    private final CopyrightService service;

    @GetMapping
    public ResponseEntity<List<CopyrightDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/id={id}")
    public ResponseEntity<CopyrightDto> findById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id).orElseThrow(RuntimeException::new));
    }

    @SneakyThrows
    @GetMapping("/start={start}&&end={end}")
    public ResponseEntity<List<CopyrightDto>> findAllByPeriod(@PathVariable(name = "start")
                                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String start,
                                                                     @PathVariable(name = "end")
                                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String end) {
        return ResponseEntity.ok(service.findAllByPeriod(start, end));
    }

    @GetMapping("/companyName={companyName}")
    public ResponseEntity<List<CopyrightDto>> findAllByCompany(@PathVariable String companyName) {
        return ResponseEntity.ok(service.findAllCopyrightsOfCompany(companyName));
    }

    @PostMapping("/")
    public ResponseEntity<Copyright> create(@RequestBody CreateCopyrightRequest createCopyrightRequest) {
        Copyright copyright = service.create(createCopyrightRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(copyright.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(copyright);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody UpdateCopyrightRequest updateCopyrightRequest) {
        service.update(updateCopyrightRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }


    
}
