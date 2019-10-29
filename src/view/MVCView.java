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
	    public void cargarDatos()
	    {
	    	System.out.println("1. Cargar datos");
	    }
		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(MVCModelo modelo)
		{
			// TODO implementar
		}
}
