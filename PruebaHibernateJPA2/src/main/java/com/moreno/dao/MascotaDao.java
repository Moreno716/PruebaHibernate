package com.moreno.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import com.moreno.aplicacion.JPAUtil;
import com.moreno.entidades.Mascota;
import com.moreno.entidades.Persona;

public class MascotaDao {
	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	public void close() {
		entityManager.close();
		JPAUtil.shutdown();
	}

	public String registrarMascota(Mascota miMascota) {
		
		String resp="";
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(miMascota);
			entityManager.getTransaction().commit();
			
			resp="Mascota Registrada!";
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se puede registrar la mascota, verifique que el dueño exista", "ERROR",JOptionPane.ERROR_MESSAGE);
		}
		
		return resp;
	}

	public Mascota consultarMascota(Long idPersona) {
		
		Mascota miMascota = entityManager.find(Mascota.class, idPersona);

		if (miMascota != null) {
			return miMascota;
		} else {
			return null;
		}
	}

	public List<Mascota> consultarListaMascotas() {
		
		List<Mascota> listaMascota = new ArrayList<Mascota>();
		Query query = entityManager.createQuery("SELECT p FROM Mascota p");
		listaMascota = query.getResultList();

		return listaMascota;
	}

	public String eliminarMascota(Mascota miMascota) {
		String resp = "";
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(miMascota);
			entityManager.getTransaction().commit();

			resp = "Mascota Eliminada!";
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"No se puede eliminar la mascota", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
		return resp;
	}

	public String actualizarMascota(Mascota miPersona) {
		
		entityManager.getTransaction().begin();
		entityManager.merge(miPersona);
		entityManager.getTransaction().commit();

		String resp = "Mascota Actualizada!";

		return resp;
	}

}
