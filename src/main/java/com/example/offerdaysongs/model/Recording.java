package com.example.offerdaysongs.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "recording")
@NoArgsConstructor
@AllArgsConstructor
public class Recording {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "title")
    String title;

    @Column(name = "version")
    String version;

    @Column(name = "release_time")
    ZonedDateTime releaseTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "singer_id")
    Singer singer;

    @OneToMany(mappedBy = "recording", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Copyright> copyrights;

}
