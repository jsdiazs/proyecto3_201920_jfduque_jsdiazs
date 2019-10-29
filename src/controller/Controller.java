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

	
	public void cargaDatos()
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin )
		{
			view.cargarDatos();

			int option = lector.nextInt();
			switch(option){
			case 1:
				modelo.cargaDatos();
				try {
					modelo.JSONReader();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	
}

