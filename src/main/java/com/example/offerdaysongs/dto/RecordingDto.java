package com.example.offerdaysongs.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class RecordingDto {
    long id;
    String title;
    String version;
    ZonedDateTime releaseTime;
    SingerDto singer;
}
