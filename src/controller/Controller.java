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
				
				funciones();
				break;
			case 2:
				modelo.function2();
				
				funciones();
				break;
			case 3:
				view.printFuncion31();
				Scanner lectorN = new Scanner(System.in);
				int n = lectorN.nextInt();
				view.printFuncion32();
				Scanner lectorlb = new Scanner(System.in);
				int lb = lectorlb.nextInt();
				view.printFuncion33();
				Scanner lectorla = new Scanner(System.in);
				int la = lectorla.nextInt();
				modelo.function3(n, lb, la);
				
				funciones();
				break;
			case 4:
				modelo.function4();
				
				funciones();
				break;
			case 5:
				modelo.function5();
				
				funciones();
				break;
			case 6:
				modelo.function6();
				
				funciones();
				break;
			case 7:
				modelo.function7();
				
				funciones();
				break;
			case 8:
				modelo.function8();
				
				funciones();
				break;
			case 9:
				modelo.function9();
				
				funciones();
				break;
			case 10:
				System.out.println("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	
			}
			fin=true;
		}
	}
	
}

