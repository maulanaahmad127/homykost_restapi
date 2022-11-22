package com.bezkoder.spring.login.payload.response;

import java.util.List;

public class UserInfoResponse {
	private Long id;
	private String username;
	private String nama;
	private String no_handphone;
	private String foto_profil;
	private String jenis_kelamin;
	private String email;
	private String no_rekening;
	private List<String> roles;

	public UserInfoResponse(Long id, String username, String nama, String no_handphone, String foto_profil, String jenis_kelamin, String email, String no_rekening, List<String> roles) {
		this.id = id;
		this.nama = nama;
		this.no_handphone = no_handphone;
		this.foto_profil = foto_profil;
		this.jenis_kelamin = jenis_kelamin;
		this.username = username;
		this.email = email;
		this.no_rekening = no_rekening;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
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
