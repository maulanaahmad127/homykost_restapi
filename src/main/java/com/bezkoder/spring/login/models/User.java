package com.bezkoder.spring.login.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "username"),
           @UniqueConstraint(columnNames = "email")
       })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  private String nama;

  private String no_handphone;

  private String foto_profil;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  @JsonIgnore
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", 
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  private String jenis_kelamin;

  private String no_rekening;

  public User() {
  }

  public User(String username, String nama, String no_handphone, String foto_profil, String jenis_kelamin, String email, String password , String no_rekening) {
    this.username = username;
    this.nama = nama;
    this.no_handphone = no_handphone;
    this.foto_profil = foto_profil;
    this.jenis_kelamin = jenis_kelamin;
    this.email = email;
    this.password = password;
    this.no_rekening = no_rekening;
  }

  

  

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getJenis_kelamin() {
    return jenis_kelamin;
  }

  public void setJenis_kelamin(String jenis_kelamin) {
    this.jenis_kelamin = jenis_kelamin;
  }

  public String getNo_handphone() {
    return no_handphone;
  }

  public void setNo_handphone(String no_handphone) {
    this.no_handphone = no_handphone;
  }

  public String getFoto_profil() {
    return foto_profil;
  }

  public void setFoto_profil(String foto_profil) {
    this.foto_profil = foto_profil;
  }

  public String getNo_rekening() {
    return no_rekening;
  }

  public void setNo_rekening(String no_rekening) {
    this.no_rekening = no_rekening;
  }

  
  
}
