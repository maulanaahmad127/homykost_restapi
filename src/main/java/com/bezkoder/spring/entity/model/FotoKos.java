package com.bezkoder.spring.entity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity

public class FotoKos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String src;
    @ManyToOne
    @JoinColumn(name="kos_id", nullable=false)
    @JsonManagedReference
    private Kos kos;


    
    public FotoKos() {
    }
    public FotoKos(String src) {
        this.src = src;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getSrc() {
        return src;
    }
    public void setSrc(String src) {
        this.src = src;
    }
    public Kos getKos() {
        return kos;
    }
    public void setKos(Kos kos) {
        this.kos = kos;
    }

    
}
