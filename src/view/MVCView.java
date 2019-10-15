package view;

import model.logic.MVCModelo;

public class MVCView 
{
	    /**
	     * Metodo constructor
	     */
	    public MVCView()
	    {
	    	
	    }
	    
		/**
		 * El usuario seleccionara en que tipo de rango quiere recibir la información
		 */
	    public void HourWeekMonth()
		{
			System.out.println("Elegir cómo quiere trabajar los datos:");
			System.out.println("1. por Hora");
			System.out.println("2. por Dia");
			System.out.println("3. por Mes");
			System.out.println("4. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}
		
	    public void printTrimestre()
		{
			System.out.println("Elegir el trimestre del año:");
			System.out.println("1. 2018-1");
			System.out.println("2. 2018-2");
			System.out.println("3. 2018-3");
			System.out.println("4. 2018-4");
			System.out.println("5. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}
		
		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(MVCModelo modelo)
		{
			// TODO implementar
		}
}
