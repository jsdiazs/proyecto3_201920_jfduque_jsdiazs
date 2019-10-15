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
	}

	public void runHourWeekMonth()
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin )
		{
			view.HourWeekMonth();

			int option = lector.nextInt();
			switch(option){
			case 1:
				runHour();
				break;
			case 2:
				runWeek();
				break;
			case 3:
				runMonth();
				break;
			case 4:
				System.out.println("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	
			}
			fin=true;
		}
	}
	
	public void runHour()
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";
		
		view.printTrimestre();
		int option = lector.nextInt();
		modelo.CSVreaderHour(option);
		printMenu3();
	}

	public void runWeek() 
	{

		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";
		
		view.printTrimestre();
		int option = lector.nextInt();
		modelo.CSVreaderWeek(option);
		printMenu1();
	}	

	public void runMonth()	
	{
		
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";
		
		view.printTrimestre();
		int option = lector.nextInt();
		modelo.CSVreaderMonth(option);
		printMenu2();
	}	
	
	public void printMenu1()
	{

	}
	
	public void printMenu2()
	{
		
	}
	
	public void printMenu3()
	{
		
	}
	
	
}

