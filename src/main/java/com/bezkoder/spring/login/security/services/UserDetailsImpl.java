package com.bezkoder.spring.login.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bezkoder.spring.login.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private Long id;

  private String username;

  private String nama;

  private String no_handphone;

  private String foto_profil;

  private String jenis_kelamin;

  private String email;

  private String no_rekening;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(Long id, String username,String nama, String no_handphone,String foto_profil, String jenis_kelamin, String email, String password, String no_rekening,
      Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.nama = nama;
    this.no_handphone =no_handphone;
    this.foto_profil = foto_profil;
    this.jenis_kelamin = jenis_kelamin;
    this.email = email;
    this.password = password;
    this.no_rekening = no_rekening;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());

    return new UserDetailsImpl(
        user.getId(), 
        user.getUsername(), 
        user.getNama(),
        user.getNo_handphone(),
        user.getFoto_profil(),
        user.getJenis_kelamin(),
        user.getEmail(),
        user.getPassword(), 
        user.getNo_rekening(),
        authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  

  public String getNama() {
    return nama;
  }

  public String getJenis_kelamin() {
    return jenis_kelamin;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }

  public String getNo_handphone() {
    return no_handphone;
  }

  public String getFoto_profil() {
    return foto_profil;
  }

  public String getNo_rekening() {
    return no_rekening;
  }


}
