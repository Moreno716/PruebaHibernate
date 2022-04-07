package clases;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.swing.JOptionPane;

import com.moreno.dao.MascotaDao;
import com.moreno.dao.PersonaDao;
import com.moreno.entidades.Mascota;
import com.moreno.entidades.Persona;

public class GestionMascotas {

	MascotaDao miMascotaDao=new MascotaDao();
	
	public GestionMascotas() {
		
		String menu="MENU DE OPCIONES - GESTION MASCOTAS\n\n";
		menu+="1. Registrar Mascota\n";
		menu+="2. Consultar Mascota\n";
		menu+="3. Consultar Lista de Mascotas\n";
		menu+="4. Consultar Lista de Mascotas por sexo\n";
		menu+="5. Actualizar Mascota\n";
		menu+="6. Eliminar Mascota\n";
		menu+="7. Salir\n";
		
		int opc=0;
		
		while(opc!=7) {
			opc=Integer.parseInt(JOptionPane.showInputDialog(menu));
			
			switch(opc) {
			case 1: registrar(); break;
			case 2: consultar(); break;
			case 3: consultarListaMascotas(); break;
			case 4: consultarListaPorSexo(); break;
			case 5: actualizar(); break;
			case 6: eliminar(); break;
			case 7: miMascotaDao.close(); break;
			}
		}
	}

	private void eliminar() {
		Long idPersona=Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la persona para eliminar"));
		Mascota miMascota=miMascotaDao.consultarMascota(idPersona);
		
		if(miMascota!=null) {
			System.out.println(miMascota);
			System.out.println();
			
			System.out.println(miMascotaDao.eliminarMascota(miMascota));
			System.out.println();
		}else {
			System.out.println();
			System.out.println("No se encontró la mascota");
		}
		System.out.println();
	}

	private void actualizar() {
		Long idPersona=Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la persona para"+" actualizar el nombre de la mascota"));
		Mascota miMascota=miMascotaDao.consultarMascota(idPersona);
		
		if(miMascota!=null) {
			System.out.println(miMascota);
			System.out.println();
			miMascota.setNombre(JOptionPane.showInputDialog("Ingrese el nombre de la mascota"));
			
			System.out.println(miMascotaDao.actualizarMascota(miMascota));
			System.out.println();
		}else {
			System.out.println();
			System.out.println("No se encontró la mascota");
		}
		System.out.println();
	}

	private void consultarListaPorSexo() {
		// TODO Auto-generated method stub
		
	}

	private void consultarListaMascotas() {
		System.out.println("Lista de personas");
		List<Mascota> listaMascotas=miMascotaDao.consultarListaMascotas();
		
		for(Mascota mascota:listaMascotas) {
			System.out.println(mascota);
		}		
	}

	private void consultar() {
		Long idPersona=Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la persona"));
		
		Mascota miMascota=miMascotaDao.consultarMascota(idPersona);
		
		if(miMascota!=null) {
			System.out.println(miMascota);
			System.out.println();
		} else {
			System.out.println();
			System.out.println("No se encontró la mascota");
		}
		System.out.println();
	}

	private void registrar() {
		Mascota miMascota=new Mascota();
		
		miMascota.setNombre(JOptionPane.showInputDialog("Ingrese el nombre de la mascota"));
		miMascota.setRaza(JOptionPane.showInputDialog("Ingrese la raza de la mascota"));
		miMascota.setColorMascota(JOptionPane.showInputDialog("Ingrese el color de la mascota"));
		miMascota.setSexo(JOptionPane.showInputDialog("Ingrese el sexo de su mascota"));
		
		Long idDuenio=Long.parseLong(JOptionPane.showInputDialog("Ingrese el documento del dueño"));
		Persona duenio=new Persona();
		duenio.setIdPersona(idDuenio);
		miMascota.setDuenio(duenio);
		
		System.out.println(miMascotaDao.registrarMascota(miMascota));
		System.out.println();
		
	}


}
