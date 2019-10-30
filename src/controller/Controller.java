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
				view.printFuncion21();
				Scanner lector21 = new Scanner(System.in);
				int n21 = lector21.nextInt();
				modelo.function4(n21);
				funciones();
				break;
			case 5:
				view.printFuncion22();
				Scanner lector22 = new Scanner(System.in);
				double n22 = lector22.nextInt();
				view.printFuncion22l();
				Scanner lector22l = new Scanner(System.in);
				double n22l = lector22l.nextInt();
				modelo.function5(n22, n22l);
				
				funciones();
				break;
			case 6:

				view.printFuncion61();
				Scanner lectorNv = new Scanner(System.in);
				int nv = lectorNv.nextInt();
				view.printFuncion62();
				Scanner lectorlim_b = new Scanner(System.in);
				int lim_b = lectorlim_b.nextInt();
				view.printFuncion63();
				Scanner lectorlim_a= new Scanner(System.in);
				int lim_a = lectorlim_a.nextInt();
				modelo.function3(nv, lim_b, lim_a);
				
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

