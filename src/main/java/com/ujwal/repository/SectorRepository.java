package com.ujwal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.model.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

}
