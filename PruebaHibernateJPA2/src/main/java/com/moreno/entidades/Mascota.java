package com.moreno.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="mascotas")//se indica ya que el nombre de la clase es diferente al nombre de la tabla
public class Mascota implements Serializable{
	
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//el id de la mascota es autoincrementable
	@Column//indicamos el nombre de campo al que la variable hace referencia ya que son diferentes
	private Long idMascota;
	
	@Column(nullable=false, length=45)//el nombre no puede ser null en BD, asi como el tamaño de varchar 45
	private String nombre;
	
	@Column(length=45)//solo indicamos el tamaño ya que corresponde la variable al nombre del campo en la tabla
	private String raza;
	
	@Column(name="color", length=45)//indicamos el nombre de campo al que la variable hace referencia ya que son diferente
	private String colorMascota;
	
	@Column(length=45)
	private String sexo;
	
	@ManyToOne
	@JoinColumn(name="persona_id",referencedColumnName="id_persona")
	private Persona duenio;

	@OneToMany(mappedBy="duenio",cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Mascota> listaMascotas;
	
	//creamos el constructo vacio
	public Mascota() {
		this.listaMascotas=new ArrayList<Mascota>();
	}
	
	//creamos el constructos con parametros sin el id
	public Mascota(String nombre, String raza,String colorMascota, String sexo, Persona duenio) {
		super();
		this.nombre = nombre;
		this.raza = raza;
		this.colorMascota=colorMascota;
		this.sexo = sexo;
		this.duenio=duenio;
	}
	
	public Persona getDuenio() {
		return duenio;
	}

	public void setDuenio(Persona duenio) {
		this.duenio = duenio;
	}

	//generamos los setters y getters correspondientes
	public Long getIdMascota() {
		return idMascota;
	}

	public String getColorMascota() {
		return colorMascota;
	}

	public void setColorMascota(String colorMascota) {
		this.colorMascota = colorMascota;
	}

	public void setIdMascota(Long idMascota) {
		this.idMascota = idMascota;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Mascota [idMascota=" + idMascota + ", nombre=" + nombre + ", raza=" + raza + ", colorMascota="
				+ colorMascota + ", sexo=" + sexo + "]";
	}
	
}
