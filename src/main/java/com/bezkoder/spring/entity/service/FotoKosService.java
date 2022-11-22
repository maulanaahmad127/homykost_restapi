package com.bezkoder.spring.entity.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.bezkoder.spring.entity.model.FotoKos;
import com.bezkoder.spring.entity.repo.FotoKosRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Transactional
public class FotoKosService {
    
    @Autowired
    private FotoKosRepo fotoKosRepo;

    public FotoKos save(FotoKos fotokos){
        return fotoKosRepo.save(fotokos);
    }

    public FotoKos findOne(Long id){
            Optional<FotoKos> fotoKos = fotoKosRepo.findById(id);
            if(!fotoKos.isPresent()){
                return null;
            }
            return fotoKos.get();
    }
    
    public Iterable<FotoKos> findAll(){
        return fotoKosRepo.findAll();
    }

    public void removeOne(Long id){
        fotoKosRepo.deleteById(id);
    }

    public Optional<FotoKos> findBySrc(String src){
         return fotoKosRepo.findBySrc(src);
    }

}
