package clases;

import java.util.List;

import javax.swing.JOptionPane;

import com.moreno.dao.ProductoDao;
import com.moreno.entidades.Persona;
import com.moreno.entidades.PersonasProductos;
import com.moreno.entidades.Producto;

public class GestionProductos {
	ProductoDao miProductoDao=new  ProductoDao();
	
	public GestionProductos() {
		
		String menu="MENU DE OPCIONES - GESTION PRODUCTOS\n\n";
		menu+="1. Registrar Productos\n";
		menu+="2. Consultar Productos\n";
		menu+="3. Consultar Lista de Productos\n";
		menu+="4. Comprar Productos\n";
		menu+="5. Actualizar Productos\n";
		menu+="6. Eliminar Productos\n";
		menu+="7. Salir\n";
		
		int opc=0;
		
		while(opc!=7) {
			opc=Integer.parseInt(JOptionPane.showInputDialog(menu));
			
			switch(opc){
				case 1: registrar(); break;
				case 2: consultar(); break;
				case 3: consultarLista(); break;
				case 4: comprarProductos(); break;
				case 5: actualizar(); break;
				case 6: eliminar(); break;
				case 7: miProductoDao.close(); break;
			}
		}
	}

	private void registrar() {
		Producto miProducto=new Producto();
		miProducto.setidProducto(Long.parseLong(JOptionPane.showInputDialog("Ingrese el id del producto")));
		miProducto.setnombreProducto(JOptionPane.showInputDialog("Ingrese el nombre del producto"));
		miProducto.setprecioProducto(JOptionPane.showInputDialog("Ingrese el precio del producto"));
		
	}

	private void consultar() {
		Long idProducto=Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la persona"));
		
		Producto miProducto=miProductoDao.consultarProducto(idProducto);
		
		if(miProducto!=null) {
			System.out.println(miProducto);
			System.out.println();
		} else {
			System.out.println();
			System.out.println("No se encontró el producto");
		}
		System.out.println();
	}

	private void consultarLista() {
		System.out.println("Lista de productos");
		List<Producto> listaProductos=miProductoDao.consultarListaProductos();
		
		for(Producto producto:listaProductos) {
			System.out.println(producto);
		}
	}

	private void comprarProductos() {

		PersonasProductos producto;
		
		Long personaId;
		Long productoId;
		int opc=0;
		do {
			producto=new PersonasProductos();
			personaId=Long.parseLong(JOptionPane.showInputDialog("Ingres el documento de la persona"));
			productoId=Long.parseLong(JOptionPane.showInputDialog("Ingrese el codigo del producto"));
			
			producto.setPersonaId(personaId);
			producto.setProductoId(productoId);
			
			System.out.println(miProductoDao.registrarCompra(producto));
			System.out.println();
			opc=Integer.parseInt(JOptionPane.showInputDialog("Ingrese 1 si desea agregar otro producto"));
		}while(opc==1);
	}

	private void actualizar() {
		Long idProducto=Long.parseLong(JOptionPane.showInputDialog("Ingrese el id del producto para"+" actualizar su nombre"));
		Producto miProducto=miProductoDao.consultarProducto(idProducto);
		
		if(miProducto!=null) {
			System.out.println(miProducto);
			System.out.println();
			miProducto.setNombre(JOptionPane.showInputDialog("Ingrese el nombre del producto"));
			
			System.out.println(miProductoDao.actualizarProducto(miProducto));
			System.out.println();
		}else {
			System.out.println();
			System.out.println("No se encontró el producto");
		}
		System.out.println();
	}

	private void eliminar() {
		Long idProducto=Long.parseLong(JOptionPane.showInputDialog("Ingrese el id del producto para eliminar"));
		Producto miProducto=miProductoDao.consultarProducto(idProducto);
		
		if(miProducto!=null) {
			System.out.println(miProducto);
			System.out.println();
			
			System.out.println(miProductoDao.eliminarProducto(miProducto));
			System.out.println();
		}else {
			System.out.println();
			System.out.println("No se encontró el producto");
		}
		System.out.println();
	}
}
