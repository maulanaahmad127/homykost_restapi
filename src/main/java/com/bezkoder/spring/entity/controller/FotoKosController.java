package com.bezkoder.spring.entity.controller;

import javax.validation.Valid;

import com.bezkoder.spring.entity.dto.FotoKosData;
import com.bezkoder.spring.entity.dto.ResponseData;
import com.bezkoder.spring.entity.model.FotoKos;
import com.bezkoder.spring.entity.model.Kos;
import com.bezkoder.spring.entity.service.FotoKosService;
import com.bezkoder.spring.entity.service.KosService;

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
@RequestMapping("api/fotokos")
public class FotoKosController {
    

    @Autowired
    private FotoKosService fotoKosService;

    @Autowired
    private KosService kosService;



    @PostMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<ResponseData<FotoKos>> create (@Valid @RequestBody FotoKosData fotoKosData, Errors errors){
        ResponseData<FotoKos> responseData = new ResponseData<FotoKos>();
        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        FotoKos fotokos = new FotoKos(
            fotoKosData.getSrc()
        );

        long kos_id = fotoKosData.getKos_id();

        Kos kos = kosService.findOne(kos_id);

        fotokos.setKos(kos);

        responseData.setStatus(true);
        responseData.setPayload(fotoKosService.save(fotokos));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public FotoKos findOne(@PathVariable("id") Long id){
        return fotoKosService.findOne(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('OWNER')")
    public Iterable<FotoKos> findAll(){
        return fotoKosService.findAll();
    }
    @PutMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<ResponseData<FotoKos>> update(@Valid @RequestBody FotoKos fotokos, Errors errors){
        ResponseData<FotoKos> responseData = new ResponseData<FotoKos>();
        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(fotoKosService.save(fotokos));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public void delete(@PathVariable("id") Long id){
        fotoKosService.removeOne(id);
    }
}
