package com.moreno.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="personas_productos")
public class PersonasProductos implements Serializable{

	private static final long serialVersionUID=1L;
	
	@Id
	@Column(name="persona_id")
	private Long personaId;
	
	@Id
	@Column(name="producto_id")
	private Long productoId;
	
	public PersonasProductos() {
	}

	public PersonasProductos(Long personaId, Long productoId) {
		super();
		this.personaId = personaId;
		this.productoId = productoId;
	}

	public void setProductoId(Long productoId2) {
	}

	public void setPersonaId(Long personaId2) {
	}
}
