package com.example.offerdaysongs.dto.requests;

import com.example.offerdaysongs.model.Singer;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateRecordingRequest {
    String title;
    String version;
    ZonedDateTime releaseTime;
    Singer singer;
}
