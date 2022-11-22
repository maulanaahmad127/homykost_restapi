package com.bezkoder.spring.entity.controller;



import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import com.bezkoder.spring.entity.dto.BuktiPembayaranData;
import com.bezkoder.spring.entity.dto.PesananData;
import com.bezkoder.spring.entity.dto.ResponseData;
import com.bezkoder.spring.entity.model.Kos;
import com.bezkoder.spring.entity.model.Pesanan;
import com.bezkoder.spring.entity.service.KosService;
import com.bezkoder.spring.entity.service.PesananService;
import com.bezkoder.spring.entity.util.GetUsernameToken;
import com.bezkoder.spring.login.models.User;
import com.bezkoder.spring.login.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/pesanan")
public class PesananController {
    
    @Autowired
    private PesananService service;
    @Autowired
    private KosService KosService;
    @Autowired
    private GetUsernameToken getUsername;
    @Autowired
    private UserRepository userRepository;

    

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ResponseData<Pesanan>> create(@Valid @RequestBody PesananData pesananData, Errors errors)throws Exception {
        ResponseData<Pesanan> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        
        Pesanan pesanan = new Pesanan(
        );

        
        Kos kos = KosService.findOne(pesananData.getKos_id());
        pesanan.setKos(kos);
        pesanan.setLama_sewa(pesananData.getLama_sewa());
        pesanan.setTotal_harga(kos.getHarga_per_bulan() * pesananData.getLama_sewa());

        String tanggal_masuk = pesananData.getTanggal_masuk();  
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(tanggal_masuk);

        pesanan.setTanggal_masuk(date1);

        pesanan.setCustomer(getUsername.getUserNameFromToken());
        pesanan.setOwner(kos.getOwner());
        
        
        responseData.setStatus(true);
        responseData.setPayload(service.save(pesanan));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public Pesanan findOne(@PathVariable("id") Long id){
        
        return service.findOne(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public Iterable<Pesanan> findByCustomer(){
        User customer = userRepository.findByUsername(getUsername.getUsernameStringFromToken()).get();
        return service.findByCustomer(customer);
    }

    @GetMapping("/owner")
    @PreAuthorize("hasRole('OWNER')")
    public Iterable<Pesanan> findByOwner(){
        User owner = userRepository.findByUsername(getUsername.getUsernameStringFromToken()).get();
        System.out.println(owner.getUsername());
        return service.findByOwner(owner);
    }
    @PutMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ResponseData<Pesanan>> update( @Valid @RequestBody Pesanan pesanan, Errors errors){
        ResponseData<Pesanan> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(service.save(pesanan));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public void delete(@PathVariable("id") Long id){
        service.removeOne(id);
    }

    @PutMapping("/addMoney/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public void addBuktiPembayaran(@PathVariable("id") Long id, @RequestBody BuktiPembayaranData data){
        service.addBuktiPembayaran(data.getSrc(), id);
    }

    @PutMapping("/changeStatus/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public void updateStatusPembayaran(@PathVariable("id") Long id, Pesanan pesanan){
        service.updateStatusPembayaran(true, id);
    }

}


