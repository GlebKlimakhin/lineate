package com.example.offerdaysongs.dto.requests;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UpdateCopyrightRequest {

    long id;
    int price;
    Date startDate;
    Date expirationDate;
}
