package ui;

import java.util.ArrayList;
import java.util.Scanner;
import dao.DAOAvion;
import datos.DB;
import modelo.Avion;

public class Principal {

	private static Scanner teclado=new Scanner(System.in);

	public static void main(String[] args) {
			
		DB.init();
			
		boolean continuar=true;
		while(continuar){
			imprimirMenu();
			System.out.println("\nIntroduzca la opción");
			String s=teclado.nextLine();
		
			s=s.trim();
			if(s.length()>0){
				
				char o=s.charAt(0);
				switch(o){
				case '1':
					insertarAvion();
					break;
				case '2':
					modificarAvion();
					break;
				case '3':
					borrarAvion();
					break;
				case '4':
					listarAeronaves();
					break;
				case '5':
					buscarAvion();
					break;
				case '6':
					System.out.println("Adiós. Gracias por utilizar nuestra aplicación");
					continuar=false;
					break;
				default:
					System.out.println("Introduzca una opción válida por favor");
				}
			}
				
		}

	}
	
		
		private static void imprimirMenu(){
			System.out.println("MENU");
			System.out.println();
			System.out.println("1.- Insertar avión");
			System.out.println("2.- Modificar avión");
			System.out.println("3.- Borrar avión");
			System.out.println("4.- Listar todas las aeronaves");
			System.out.println("5.- Buscar avión");
			System.out.println();
			System.out.println("6.- Salir");
		}
		
	
		private static void insertarAvion(){			
			System.out.print("Descripción: ");
			String descripcion=teclado.nextLine();
			System.out.println("Peso: ");
			double peso=teclado.nextDouble();
			
			Avion a=new Avion(-1,descripcion,peso);
			DAOAvion dao=new DAOAvion();
			if(!dao.insertar(a)){
				System.out.println("Fallo en la inserción. ¿El avión ya está insertado?");
			}
		}
		
		private static void buscarAvion(){
			System.out.print("IdAvion: ");
			int idavion=teclado.nextInt();
			
			DAOAvion dao=new DAOAvion();
			Avion a=dao.buscar(idavion);
			if(a!=null){
				System.out.println(a);
			}else{
				System.out.println("El número de identificación introducido no consta en la base de datos.");
			}
		}
		
		private static void modificarAvion(){
			System.out.print("IdAvion: ");
			int idavion=teclado.nextInt();
			teclado.nextLine();
			System.out.print("Nueva descripción: ");
			String descripcion=teclado.nextLine();
			System.out.println("Nuevo peso: ");
			double peso=teclado.nextDouble();
			
			Avion a=new Avion(idavion,descripcion,peso);
			DAOAvion dao=new DAOAvion();
			if(!dao.modificar(a)){
				System.out.println("Fallo en la modificación.");
			}
			dao.modificar(a);
		}
		
		private static void borrarAvion(){
			System.out.print("IdAvion: ");
			int idavion=teclado.nextInt();
			
			Avion a=new Avion(idavion,null,0);
			DAOAvion dao=new DAOAvion();
			
			if(!dao.borrar(a)){
				System.out.println("Fallo al borrar el avión.");
			}
		}
		
		private static void listarAeronaves(){
			System.out.println("LISTADO DE TODAS LAS AERONAVES DE LA BASE DE DATOS");
			System.out.println();
			DAOAvion dao=new DAOAvion();
			ArrayList<Avion> a=dao.listar();
			
			for(Avion a1 : a){
				System.out.println(a1);
			}
		}

}
