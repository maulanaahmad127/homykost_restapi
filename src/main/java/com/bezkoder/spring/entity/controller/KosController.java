package com.bezkoder.spring.entity.controller;

import java.util.HashSet;
import java.util.Set;


import javax.validation.Valid;

import com.bezkoder.spring.entity.dto.KosData;
import com.bezkoder.spring.entity.dto.ResponseData;
import com.bezkoder.spring.entity.model.FasilitasKos;
import com.bezkoder.spring.entity.model.FotoKos;
import com.bezkoder.spring.entity.model.Kos;
import com.bezkoder.spring.entity.service.FasilitasKosService;
import com.bezkoder.spring.entity.service.FotoKosService;
import com.bezkoder.spring.entity.service.KosService;
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
@RequestMapping("api/kos")
public class KosController {
    
    @Autowired
    private KosService service;
    @Autowired
    private FasilitasKosService faslitasKosService;
    @Autowired
    private FotoKosService fotoKosService;
    @Autowired
    private GetUsernameToken getUsername;
    @Autowired
    private UserRepository userRepository;
    

    @PostMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<ResponseData<Kos>> create(@Valid @RequestBody KosData kosData, Errors errors){
        ResponseData<Kos> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        
        Kos kos = new Kos(
            kosData.getNama(),
            kosData.getKeluarahan(),
            kosData.getKecamatan(),
            kosData.getKota(),
            kosData.getJenis(),
            kosData.getAlamat(),
            kosData.getHarga_per_bulan()
        );

        Set<FasilitasKos> newFasilitasKos = new HashSet<>();
        Set<Long> fasilitasId = kosData.getFasilitasKos();
        

        if(fasilitasId == null){
            new RuntimeException("Error: Fasilitas is not found.");
        }else{
            fasilitasId.forEach(data -> {
                FasilitasKos fasilitas = faslitasKosService.findOne(data);
                newFasilitasKos.add(fasilitas);
            });
        }

       
        kos.setFaslitaskos(newFasilitasKos);
        kos.setOwner(getUsername.getUserNameFromToken());
        responseData.setStatus(true);
        responseData.setPayload(service.save(kos));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public Kos findOne(@PathVariable("id") Long id){
        
        return service.findOne(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public Iterable<Kos> findAll(){
        User user = getUsername.getUserNameFromToken();
        String username = user.getUsername();
        String role = user.getRoles().iterator().next().toString();
        System.out.printf("username: %s\nrole: %s", username, role);
        return service.findAll();
    }

    @GetMapping("/owner")
    @PreAuthorize("hasRole('OWNER')")
    public Iterable<Kos> findByOwner(){
        User owner = userRepository.findByUsername(getUsername.getUsernameStringFromToken()).get();
        System.out.println(owner.getUsername());
        return service.findByOwner(owner);
    }

    @PutMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<ResponseData<Kos>> update( @Valid @RequestBody Kos kos, Errors errors){
        ResponseData<Kos> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(service.save(kos));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public void delete(@PathVariable("id") Long id){
        service.removeOne(id);
    }

}

