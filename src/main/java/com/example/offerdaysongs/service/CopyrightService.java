package com.example.offerdaysongs.service;

import ch.qos.logback.classic.pattern.DateConverter;
import com.example.offerdaysongs.dto.CopyrightDto;
import com.example.offerdaysongs.dto.requests.CreateCopyrightRequest;
import com.example.offerdaysongs.dto.requests.UpdateCopyrightRequest;
import com.example.offerdaysongs.model.Company;
import com.example.offerdaysongs.model.Copyright;
import com.example.offerdaysongs.model.Recording;
import com.example.offerdaysongs.model.Singer;
import com.example.offerdaysongs.repository.CompanyRepository;
import com.example.offerdaysongs.repository.CopyrightRepository;
import com.example.offerdaysongs.repository.RecordingRepository;
import com.example.offerdaysongs.repository.SingerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CopyrightService {

    private final CopyrightRepository copyrightRepository;
    private final CompanyRepository companyRepository;
    private final RecordingRepository recordingRepository;
    private final SingerRepository singerRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Copyright create(CreateCopyrightRequest request) {
        Copyright copyright = new Copyright();
        copyright.setExpirationDate(request.getExpirationDate());
        copyright.setStartDate(request.getStartDate());
        copyright.setPrice(request.getPrice());
        Company companyDto = request.getCompany();
        Recording recordingDto = request.getRecording();
        Singer singerDto = request.getRecording().getSinger();
        if (companyDto != null) {
            Company foundCompany = companyRepository.findById(companyDto.getId()).orElseGet(() -> {
                Company temp = new Company();
                temp.setName(companyDto.getName());
                return companyRepository.save(temp);
            });
            copyright.setCompany(foundCompany);
        }
        if (recordingDto != null) {
            Recording foundRecording = recordingRepository.findById(recordingDto.getId()).orElseGet(() -> {
                Recording temp = new Recording();
                temp.setTitle(recordingDto.getTitle());
                temp.setSinger(recordingDto.getSinger());
                temp.setVersion(recordingDto.getVersion());
                temp.setReleaseTime(recordingDto.getReleaseTime());
                return recordingRepository.save(temp);
            });
            copyright.setRecording(foundRecording);
        }
            return copyrightRepository.save(copyright);
    }

    @Transactional
    public void update(UpdateCopyrightRequest request) {
        copyrightRepository.update(request.getPrice(),
                request.getStartDate(),
                request.getExpirationDate(),
                request.getId());
    }

    public List<CopyrightDto> findAll() {
        return copyrightRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Optional<CopyrightDto> findById(long id) {
        return copyrightRepository.findById(id)
                .map(this::toDto);
    }

    public List<CopyrightDto> findAllByPeriod(String start, String end) throws ParseException {
        Date beginningDate = DateUtils.parseDateStrictly(end, "yyyy-MM-dd");
        Date endingDate = DateUtils.parseDateStrictly(end, "yyyy-MM-dd");
        List<CopyrightDto> copyrightDateList = new ArrayList<>();
        if(start.compareTo(end) >= 0) {
            throw new IllegalArgumentException("Start date is later than end date");
        }
        copyrightRepository.findAll().forEach(c -> {
            if(beginningDate.compareTo(c.getExpirationDate()) >= 0 || endingDate.compareTo(c.getStartDate()) <= 0) {
                copyrightDateList.add(toDto(c));
            }
        });
        return copyrightDateList;
    }

    public List<CopyrightDto> findAllCopyrightsOfCompany(String companyName) {
        return copyrightRepository.findAllByCompany(companyRepository.findByName(companyName))
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(long id) {
        copyrightRepository.deleteById(id);
    }

    private CopyrightDto toDto(Copyright copyright) {
        return modelMapper.map(copyright, CopyrightDto.class);
    }
}
