package com.bezkoder.spring.entity.repo;

import java.util.Optional;

import com.bezkoder.spring.entity.model.FasilitasKos;

import org.springframework.data.repository.CrudRepository;

public interface FasilitasKosRepo extends CrudRepository<FasilitasKos, Long> {
    Optional<FasilitasKos> findByFasilitas (String fasilitas);
}
