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
	    	System.out.println("Eliga la funci�n que desea realizar:");
	    	System.out.println("1. Obtener la N letras mas frecuentes por la que comienza el nombre de una zona.");
	    	System.out.println("2. Buscar los nodos que delimitan las zonas por localizaci�n geor�fica.");
	    	System.out.println("3. Buscar tiempo promedio de viaje que est�n en un rango.");
	    	System.out.println("4. Buscar las N zonas mas al norte");
	    	System.out.println("5. BUscar nodos de la malla vial por localizaci�n geogr�fica.");
	    	System.out.println("6. Buscar los nodos de espera que tienen una desviaci�n estantdar en un rango.");
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

		public void printFuncion21() 
		{
			System.out.println("Ingrese la cantidad de zonas que desea retornar: ");
		}
		public void printFuncion22() 
		{
			System.out.println("Ingrese Una latitud");
		}
		public void printFuncion22l() 
		{
			System.out.println("Ingrese Una longitud");
		}
		public void printFuncion31() {
			System.out.println("Ingrese el numero de viajes que desea consultar: ");
		}
		
		public void printFuncion32() {
			System.out.println("Ingrese el timpo de incio: ");
		}
		public void printFuncion33() {
			System.out.println("Ingrese el tiempo final: ");
		}
		public void printFuncion61() {
			System.out.println("Ingrese el numero de viajes a consultar: ");
		}
		
		public void printFuncion62() {
			System.out.println("Ingrese el tiempo de limite_bajo: ");
		}
		public void printFuncion63() {
			System.out.println("Ingrese el tiempo de limite_alto: ");
		}
		
}
