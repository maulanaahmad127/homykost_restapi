package com.bezkoder.spring.entity.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.bezkoder.spring.entity.model.Pesanan;
import com.bezkoder.spring.entity.repo.PesananRepo;
import com.bezkoder.spring.login.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PesananService {

    @Autowired
    private PesananRepo pesananRepo;

    public Pesanan save(Pesanan pesanan){
       return pesananRepo.save(pesanan);
    }

    public Pesanan findOne(Long id){
        Optional<Pesanan> kos = pesananRepo.findById(id);
        if(!kos.isPresent()){
            return null;
        }
        return kos.get();
}

    public Iterable<Pesanan> findAll(){
        return pesananRepo.findAll();
    }

    public Iterable<Pesanan> findByCustomer(User customer){
        return pesananRepo.findByCustomer(customer);
    }

    public Iterable<Pesanan> findByOwner(User owner){
        return pesananRepo.findByOwner(owner);
    }

    public void removeOne(Long id){
        pesananRepo.deleteById(id);
    }

    public void addBuktiPembayaran(String src, long id){
        pesananRepo.addBuktiPembayaran(src, id);
    }

    public void updateStatusPembayaran(boolean status, long id){
        pesananRepo.updateStatusPembayaran(status, id);
    }
    
}
