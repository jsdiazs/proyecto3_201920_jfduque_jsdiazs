package model.logic;

import model.data_structures.LinkedQueue;

public class Vertices 
{
	//Identificador del vertice
	private int id;

	//Longitud del vertice
	private double longitud;
	
	//Latitud del vertice
	private double latitud;
	
	//Zona del vertice
	private double MOVEMENT_ID;
	
	//lista de adyacencia
	private LinkedQueue<Vertices> listaAdyacencia;
	
	
	public Vertices(int pId, double pLongitud, double pLatitud, double pMOVEMENT_ID)
	{
		id=pId;
		longitud=pLongitud;
		latitud=pLatitud;
		MOVEMENT_ID=pMOVEMENT_ID;
		listaAdyacencia = new LinkedQueue<Vertices>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getMOVEMENT_ID() {
		return MOVEMENT_ID;
	}

	public void setMOVEMENT_ID(double mOVEMENT_ID) {
		MOVEMENT_ID = mOVEMENT_ID;
	}
	
	public LinkedQueue darListaAdyacencia()
	{
		return listaAdyacencia;
	}
	
}
