package com.bezkoder.spring.entity.dto;

import java.util.Set;





public class KosData {
    private String nama;

    private String jenis;
    
    private String alamat;

    private String keluarahan;

    private String kecamatan;

    private String kota;

    private float harga_per_bulan;

    private Set<Long> fasilitasKos;

    private Set<String> fotoKos;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public float getHarga_per_bulan() {
        return harga_per_bulan;
    }

    public void setHarga_per_bulan(float harga_per_bulan) {
        this.harga_per_bulan = harga_per_bulan;
    }

    public Set<Long> getFasilitasKos() {
        return fasilitasKos;
    }

    public void setFasilitasKos(Set<Long> fasilitasKos) {
        this.fasilitasKos = fasilitasKos;
    }

    public String getKeluarahan() {
        return keluarahan;
    }

    public void setKeluarahan(String keluarahan) {
        this.keluarahan = keluarahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public Set<String> getFotoKos() {
        return fotoKos;
    }

    public void setFotoKos(Set<String> fotoKos) {
        this.fotoKos = fotoKos;
    }

  

  
   

    

    
}
