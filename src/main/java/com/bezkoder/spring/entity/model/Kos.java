package com.bezkoder.spring.entity.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.bezkoder.spring.login.models.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class, property = "id"
)
public class Kos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;

    private String jenis;
    
    private String alamat;

    private String keluarahan;

    private String kecamatan;

    private String kota;

    @OneToMany(mappedBy = "kos")
    // @JsonManagedReference
    private Set<FotoKos> fotokos;

    private float harga_per_bulan;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "fasilitas_kost", 
             joinColumns = @JoinColumn(name = "kos_id"),
             inverseJoinColumns = @JoinColumn(name = "fasilitas_id"))
    private Set<FasilitasKos> faslitaskos = new HashSet<>();

    @ManyToOne
    private User owner;
    


    public Kos() {
    }

    public Kos(String nama, String jenis, String alamat, String keluarahan, String kecamatan, String kota,
            float harga_per_bulan) {
        this.nama = nama;
        this.jenis = jenis;
        this.alamat = alamat;
        this.keluarahan = keluarahan;
        this.kecamatan = kecamatan;
        this.kota = kota;
        this.harga_per_bulan = harga_per_bulan;
    }

    public Kos(String nama, String jenis, String alamat, float harga_per_bulan) {
        this.nama = nama;
        this.jenis = jenis;
        this.alamat = alamat;
        this.harga_per_bulan = harga_per_bulan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public float getHarga_per_bulan() {
        return harga_per_bulan;
    }

    public void setHarga_per_bulan(float harga_per_bulan) {
        this.harga_per_bulan = harga_per_bulan;
    }

    public Set<FasilitasKos> getFaslitaskos() {
        return faslitaskos;
    }

    public void setFaslitaskos(Set<FasilitasKos> faslitaskos) {
        this.faslitaskos = faslitaskos;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<FotoKos> getFotokos() {
        return fotokos;
    }

    public void setFotokos(Set<FotoKos> fotokos) {
        this.fotokos = fotokos;
    }

   

    
}
