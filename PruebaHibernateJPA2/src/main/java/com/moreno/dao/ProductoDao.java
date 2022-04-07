package com.moreno.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import com.moreno.aplicacion.JPAUtil;
import com.moreno.entidades.Persona;
import com.moreno.entidades.PersonasProductos;
import com.moreno.entidades.Producto;

public class ProductoDao {
	EntityManager entityManager=JPAUtil.getEntityManagerFactory().createEntityManager();

	public String registrarProducto(Producto miProducto) {
		String resp="";
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(miProducto);
			entityManager.getTransaction().commit();
			
			resp="Producto Registrado!";
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se puede registrar el producto", "ERROR",JOptionPane.ERROR_MESSAGE);
		}
		return resp;
	}
	
	public void close() {
		entityManager.close();
		JPAUtil.shutdown();
	}

	public String registrarCompra(PersonasProductos producto) {
		String resp="";
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(producto);
			entityManager.getTransaction().commit();
			
			resp="Se realizó la compra del producto!";
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "No se puede registrar la compra del producto","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		return resp;
	}

	public Producto consultarProducto(Long idProducto) {
		
		Producto miProducto = entityManager.find(Producto.class, idProducto);

		if (miProducto != null) {
			return miProducto;
		} else {
			return null;
		}
	}

	public List<Producto> consultarListaProductos() {
		
		List<Producto> listaProducto = new ArrayList<Producto>();
		Query query = entityManager.createQuery("SELECT p FROM Producto p");
		listaProducto = query.getResultList();

		return listaProducto;
	}

	public String eliminarProducto(Producto miProducto) {
		String resp = "";
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(miProducto);
			entityManager.getTransaction().commit();

			resp = "Producto Eliminado!";
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"No se puede eliminar el producto", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
		return resp;
	}

	public String actualizarProducto(Producto miProducto) {
		
		entityManager.getTransaction().begin();
		entityManager.merge(miProducto);
		entityManager.getTransaction().commit();

		String resp = "Producto Actualizado!";

		return resp;
	}

}
