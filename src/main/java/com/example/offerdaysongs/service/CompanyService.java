package com.example.offerdaysongs.service;

import com.example.offerdaysongs.dto.requests.CreateCompanyRequest;
import com.example.offerdaysongs.model.Company;
import com.example.offerdaysongs.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getAll()
    {
        return companyRepository.findAll();
    }

    public Company getById(long id)
    {
        return companyRepository.getById(id);
    }

    public Company create(CreateCompanyRequest request) {
        Company company = new Company();
        company.setName(request.getName());
        return companyRepository.save(company);
    }
}
