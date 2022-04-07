package com.moreno.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="productos")
public class Producto implements Serializable{
	
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_producto")
	private Long idProducto;
	
	@Column(name="nombre_producto",nullable=false,length=45)
	private String nombreProducto;
	
	@Column(name="precio_producto")
	private Double precioProducto;
	
	@ManyToMany(mappedBy="listaProductos")
	private List<Persona> listaPersona;
	
	public void setNombre(String showInputDialog) {
	}

	public void setidProducto(long parseLong) {
	}

	public void setnombreProducto(String showInputDialog) {
	}

	public void setprecioProducto(String showInputDialog) {
	}
}
