package com.example.offerdaysongs.dto.requests;

import com.example.offerdaysongs.model.Company;
import com.example.offerdaysongs.model.Recording;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCopyrightRequest {
    int price;
    Date startDate;
    Date expirationDate;
    Company company;
    Recording recording;
}
