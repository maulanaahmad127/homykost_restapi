package com.bezkoder.spring.entity.controller;

import javax.validation.Valid;

import com.bezkoder.spring.entity.dto.FasilitasKosData;
import com.bezkoder.spring.entity.dto.ResponseData;
import com.bezkoder.spring.entity.model.FasilitasKos;
import com.bezkoder.spring.entity.service.FasilitasKosService;

import org.springframework.validation.Errors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("api/fasilitas")
public class FasilitasKosController {
    
    @Autowired
    private FasilitasKosService faslitasKosService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<ResponseData<FasilitasKos>> create (@Valid @RequestBody FasilitasKosData fasilitasKosData, Errors errors){
        ResponseData<FasilitasKos> responseData = new ResponseData<FasilitasKos>();
        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        FasilitasKos faslitasKos = modelMapper.map(fasilitasKosData, FasilitasKos.class);
        responseData.setStatus(true);
        responseData.setPayload(faslitasKosService.save(faslitasKos));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('OWNER')")
    public FasilitasKos findOne(@PathVariable("id") Long id){
        return faslitasKosService.findOne(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('OWNER')")
    public Iterable<FasilitasKos> findAll(){
        return faslitasKosService.findAll();
    }
    @PutMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<ResponseData<FasilitasKos>> update(@Valid @RequestBody FasilitasKos fasilitasKos, Errors errors){
        ResponseData<FasilitasKos> responseData = new ResponseData<FasilitasKos>();
        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(faslitasKosService.save(fasilitasKos));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public void delete(@PathVariable("id") Long id){
        faslitasKosService.removeOne(id);
    }
}
