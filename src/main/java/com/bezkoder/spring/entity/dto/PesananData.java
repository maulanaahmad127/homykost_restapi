package com.bezkoder.spring.entity.dto;

public class PesananData {
    private long kos_id;

    private int lama_sewa;

    private String tanggal_masuk;

    public long getKos_id() {
        return kos_id;
    }
    public void setKos_id(long kos_id) {
        this.kos_id = kos_id;
    }
    public int getLama_sewa() {
        return lama_sewa;
    }
    public void setLama_sewa(int lama_sewa) {
        this.lama_sewa = lama_sewa;
    }
    public String getTanggal_masuk() {
        return tanggal_masuk;
    }
    public void setTanggal_masuk(String tanggal_masuk) {
        this.tanggal_masuk = tanggal_masuk;
    }


    
}
