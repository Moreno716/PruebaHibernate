package clases;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

import com.moreno.dao.PersonaDao;
import com.moreno.entidades.Mascota;
import com.moreno.entidades.Nacimiento;
import com.moreno.entidades.Persona;

public class GestionPersonas {
	
	PersonaDao miPersonaDao=new PersonaDao();
	
	public GestionPersonas() {
		
		String menu="MENU DE OPCIONES - GESTION PERSONAS\n\n";
		menu+="1. Registrar Persona\n";
		menu+="2. Consultar Persona\n";
		menu+="3. Consultar Lista de Persona\n";
		menu+="5. Actualizar Persona\n";
		menu+="6. Eliminar Persona\n";
		menu+="7. Salir\n";
		
		int opc=0;
		
		while(opc!=7) {
			opc=Integer.parseInt(JOptionPane.showInputDialog(menu));
			
			switch(opc) {
			case 1: registrar(); break;
			case 2: consultar(); break;
			case 3: consultarLista(); break;
			case 5: actualizarNombre(); break;
			case 6: eliminar(); break;
			case 7: miPersonaDao.close(); break;

			}
		}
	}

	private void registrar() {
		Persona miPersona=new Persona();
		miPersona.setIdPersona(Long.parseLong(JOptionPane.showInputDialog("Ingrese el documento de la persona")));
		miPersona.setNombre(JOptionPane.showInputDialog("Ingrese el nombre de la persona"));
		miPersona.setTelefono(JOptionPane.showInputDialog("Ingrese la telefono de la persona"));
		miPersona.setProfesion(JOptionPane.showInputDialog("Ingrese el profesi�n de la persona"));
		miPersona.setTipo(JOptionPane.showInputDialog("Ingrese el tipo"));
		
		miPersona.setNacimiento(obtenerDatosNacimiento());
		
		System.out.println(miPersonaDao.registrarPersona(miPersona));
		System.out.println();
		
		miPersona.setNacimiento(obtenerDatosNacimiento());
		
		int opc=0;
		do {
			opc=Integer.parseInt(JOptionPane.showInputDialog("Desea registrar una mascota?"+"\n1.SI\n2.NO"));
			
			if(opc==1) {
				Mascota miMascota=new Mascota();
				miMascota.setNombre(JOptionPane.showInputDialog("Ingrese el nombre de la mascota"));
				miMascota.setRaza(JOptionPane.showInputDialog("Ingrese la raza de la mascota"));
				miMascota.setColorMascota(JOptionPane.showInputDialog("Ingrese el color de la mascota"));
				miMascota.setSexo(JOptionPane.showInputDialog("Ingrese el sexo de su mascota"));
				miMascota.setDuenio(miPersona);
				
				miPersona.getListaMascotas().add(miMascota);
				}
		}while (opc!=2);
		System.out.println(miPersonaDao.registrarPersona(miPersona));
	}

	private Nacimiento obtenerDatosNacimiento() {
		int dia=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el DIA de nacimiento"));
		int mes=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el MES de nacimiento"));
		int anio=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el A�O de nacimiento"));
		
		Nacimiento datosNacimiento=new Nacimiento();
		datosNacimiento.setIdNacimiento(null);
		datosNacimiento.setFechaNacimiento(LocalDate.of(anio, mes, dia));
		datosNacimiento.setCiudadNacimiento(JOptionPane.showInputDialog("Ingrese la ciudad de Nacimiento"));
		datosNacimiento.setDepartamentoNacimiento(JOptionPane.showInputDialog("Ingrese el departamento de Nacimiento"));
		datosNacimiento.setPaisNacimiento(JOptionPane.showInputDialog("Ingrese el pais de Nacimiento"));
		
		return datosNacimiento;
	}

	private void consultar() {
		Long idPersona=Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la persona"));
		
		Persona miPersona=miPersonaDao.consultarPersona(idPersona);
		
		if(miPersona!=null) {
			System.out.println(miPersona);
			System.out.println();
		} else {
			System.out.println();
			System.out.println("No se encontr� la mascota");
		}
		System.out.println();
	}

	private void consultarLista() {
		System.out.println("Lista de personas");
		List<Persona> listaPersonas=miPersonaDao.consultarListaPersona();
		
		for(Persona persona:listaPersonas) {
			System.out.println(persona);
		}
	}

	private void actualizarNombre() {
		Long idPersona=Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la persona para"+" actualizar su nombre"));
		Persona miPersona=miPersonaDao.consultarPersona(idPersona);
		
		if(miPersona!=null) {
			System.out.println(miPersona);
			System.out.println();
			miPersona.setNombre(JOptionPane.showInputDialog("Ingrese el nombre de la persona"));
			
			System.out.println(miPersonaDao.actualizarPersona(miPersona));
			System.out.println();
		}else {
			System.out.println();
			System.out.println("No se encontr� la persona");
		}
		System.out.println();
	}

	private void eliminar() {
		Long idPersona=Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la persona para eliminar"));
		Persona miPersona=miPersonaDao.consultarPersona(idPersona);
		
		if(miPersona!=null) {
			System.out.println(miPersona);
			System.out.println();
			
			System.out.println(miPersonaDao.eliminarPersona(miPersona));
			System.out.println();
		}else {
			System.out.println();
			System.out.println("No se encontr� la persona");
		}
		System.out.println();
	}
	
}
