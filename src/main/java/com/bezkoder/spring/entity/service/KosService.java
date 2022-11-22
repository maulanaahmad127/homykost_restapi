package com.bezkoder.spring.entity.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.bezkoder.spring.entity.model.Kos;
import com.bezkoder.spring.entity.repo.KosRepo;
import com.bezkoder.spring.login.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class KosService {
    
    @Autowired
    private KosRepo kosRepo;

    public Kos save(Kos kos){
        return kosRepo.save(kos);
    }

    public Kos findOne(Long id){
            Optional<Kos> kos = kosRepo.findById(id);
            if(!kos.isPresent()){
                return null;
            }
            return kos.get();
    }
    
    public Iterable<Kos> findAll(){
        return kosRepo.findAll();
    }

    public Iterable<Kos> findByOwner(User owner){
        return kosRepo.findByOwner(owner);
    }

    public void removeOne(Long id){
        kosRepo.deleteById(id);
    }
    

    


}

