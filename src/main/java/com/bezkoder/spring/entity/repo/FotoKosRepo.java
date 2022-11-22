package com.bezkoder.spring.entity.repo;

import java.util.Optional;

import com.bezkoder.spring.entity.model.FotoKos;

import org.springframework.data.repository.CrudRepository;

public interface FotoKosRepo extends CrudRepository<FotoKos, Long> {
    Optional<FotoKos> findBySrc(String src);
}
