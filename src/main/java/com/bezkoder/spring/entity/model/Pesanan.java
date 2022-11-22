package com.bezkoder.spring.entity.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bezkoder.spring.login.models.User;

@Entity
public class Pesanan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 

    @ManyToOne
    @JoinColumn(name = "kos_id")
    private Kos kos;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;


    @Temporal(TemporalType.DATE)
    private Date tanggal_masuk;

    private int lama_sewa;

    private float total_harga;

    private String bukti_pembayaran;

    @Column(columnDefinition="BOOLEAN DEFAULT false")
    private boolean status_pembayaran;
    

    public Pesanan() {
    }

    

    public Pesanan(float total_harga) {
        this.total_harga = total_harga;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Kos getKos() {
        return kos;
    }

    public void setKos(Kos kos) {
        this.kos = kos;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public float getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(float total_harga) {
        this.total_harga = total_harga;
    }



    public String getBukti_pembayaran() {
        return bukti_pembayaran;
    }



    public void setBukti_pembayaran(String bukti_pembayaran) {
        this.bukti_pembayaran = bukti_pembayaran;
    }



    public User getOwner() {
        return owner;
    }



    public void setOwner(User owner) {
        this.owner = owner;
    }



    public boolean isStatus_pembayaran() {
        return status_pembayaran;
    }



    public void setStatus_pembayaran(boolean status_pembayaran) {
        this.status_pembayaran = status_pembayaran;
    }



    public Date getTanggal_masuk() {
        return tanggal_masuk;
    }



    public void setTanggal_masuk(Date tanggal_masuk) {
        this.tanggal_masuk = tanggal_masuk;
    }



    public int getLama_sewa() {
        return lama_sewa;
    }



    public void setLama_sewa(int lama_sewa) {
        this.lama_sewa = lama_sewa;
    }

    
    

}
