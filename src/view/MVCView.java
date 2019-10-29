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
	    
	    public void printMenu()
	    {
	    	System.out.println("Eliga la función que desea realizar:");
	    	System.out.println("1. Obtener la N letras mas frecuentes por la que comienza el nombre de una zona.");
	    	System.out.println("2. Buscar los nodos que delimitan las zonas por localización georáfica.");
	    	System.out.println("3. Buscar tiempo promedio de viaje que están en un rango.");
	    	System.out.println("4. Buscar las N zonas mas al norte");
	    	System.out.println("5. BUscar nodos de la malla vial por localización geográfica.");
	    	System.out.println("6. Buscar los nodos de espera que tienen una desviación estantdar en un rango.");
	    	System.out.println("7. Retornar todos los tiempos promedios de una zona en una hora especifica.");
	    	System.out.println("8. Obtener las N zonas priorizadas por nodos que definen su frontera.");
	    	System.out.println("9. Grafica ASCII.");
	    	
	    }
	    
		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(MVCModelo modelo)
		{
			// TODO implementar
		}
}
