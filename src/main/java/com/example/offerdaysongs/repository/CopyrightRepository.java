package com.example.offerdaysongs.repository;

import com.example.offerdaysongs.model.Company;
import com.example.offerdaysongs.model.Copyright;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CopyrightRepository extends JpaRepository<Copyright, Long>, JpaSpecificationExecutor<Copyright> {
    @Modifying
    @Query("update Copyright c set c.price = ?1, c.startDate = ?2, c.expirationDate = ?3 where c.id = ?4")
    void update(int price, Date startDate, Date expirationDate, long id);

    List<Copyright> findAllByCompany(Company company);

}
