package controller;

import java.util.Scanner;

import model.logic.MVCModelo;
import model.logic.Viaje;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;

	/* Instancia de la Vista*/
	private MVCView view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new MVCView();
		modelo = new MVCModelo();
		modelo.cargaDatos();
	}

	
	public void funciones()
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";
		
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
		view.printMenu();
		
		while( !fin )
		{

			int option = lector.nextInt();
			switch(option){
			case 1:
				modelo.function1();
				break;
			case 2:
				modelo.function2();
				break;
			case 3:
				modelo.function3();
				break;
			case 4:
				modelo.function4();
				break;
			case 5:
				modelo.function5();			
				break;
			case 6:
				modelo.function5();
				break;
			case 7:
				modelo.function7();
				break;
			case 8:
				modelo.function8();
				break;
			case 9:
				modelo.function9();
				break;
			case 10:
				modelo.function10();
				break;	
			
			case 11:
				modelo.function11();
				break;
				
			case 12:
				modelo.function12();
			break;  
			
			case 13:
				System.out.println("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;
			}
			fin=true;
		}
	}
	
}

