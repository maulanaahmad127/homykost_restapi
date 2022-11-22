package com.bezkoder.spring.entity.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.bezkoder.spring.entity.model.FasilitasKos;
import com.bezkoder.spring.entity.repo.FasilitasKosRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FasilitasKosService {

    @Autowired
    private FasilitasKosRepo fasilitasKosRepo;

    public FasilitasKos save(FasilitasKos fasilitasKos){
        return fasilitasKosRepo.save(fasilitasKos);
    }

    public FasilitasKos findOne(Long id){
            Optional<FasilitasKos> fasilitasKos = fasilitasKosRepo.findById(id);
            if(!fasilitasKos.isPresent()){
                return null;
            }
            return fasilitasKos.get();
    }
    
    public Iterable<FasilitasKos> findAll(){
        return fasilitasKosRepo.findAll();
    }

    public void removeOne(Long id){
        fasilitasKosRepo.deleteById(id);
    }

    public Optional<FasilitasKos> findByFasilitas(String fasilitas){
        return fasilitasKosRepo.findByFasilitas(fasilitas);
    }
    
}
