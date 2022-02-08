package com.example.offerdaysongs.dto;

import com.example.offerdaysongs.model.Company;
import com.example.offerdaysongs.model.Recording;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class CopyrightDto {

    long id;
    int price;
    Date startDate;
    Date expirationDate;
    CompanyDto company;
    RecordingDto recording;
}
