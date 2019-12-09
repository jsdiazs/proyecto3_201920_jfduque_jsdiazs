package model.logic;

import java.awt.Taskbar.Feature;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import com.opencsv.CSVReader;

import model.data_structures.LinkedQueue;
import model.data_structures.tablaDeHashLinearProbing;


/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */

	
	
	private tablaDeHashLinearProbing<Integer, Viaje> tableViajes;
	
	private tablaDeHashLinearProbing<Integer, Vertices> tableVertices;
	

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		tableViajes = new tablaDeHashLinearProbing<Integer, Viaje>();
		tableVertices = new tablaDeHashLinearProbing<Integer, Vertices>();
	}

	//----------------------------------------------------------------------------------------
	//CSV READER
	
	public void cargaDatos()
	{
			int totalViajes = 0;
			
			//-----------------------------------
			//Lectura para semana
			
			CSVReader readerWeek = null;
			try {
				readerWeek = new CSVReader(new FileReader("./data/bogota-cadastral-2018-1-WeeklyAggregate.csv"));			
				String[] nextline = readerWeek.readNext();
				nextline = readerWeek.readNext();
				int w = 0;
				while(nextline != null )
				{					
					int   sourceid = Integer.parseInt(nextline[0]);
					int   dstid = Integer.parseInt(nextline[1]);
					int   dayHourMonth = Integer.parseInt(nextline[2]);
					float mean_travel_time = Float.parseFloat(nextline[3]);
					float standard_deviation_travel_time = Float.parseFloat(nextline[4]);
					float geometric_mean_travel_time = Float.parseFloat(nextline[5]);
					float geometric_standard_deviation_travel_time = Float.parseFloat(nextline[6]);
					w++;
					Viaje nuevo = new Viaje(sourceid, dstid, dayHourMonth, mean_travel_time, standard_deviation_travel_time, geometric_mean_travel_time, geometric_standard_deviation_travel_time);
					tableViajes.put(sourceid, nuevo);
					nextline = readerWeek.readNext();					
				}
				readerWeek.close();

				System.out.println("El numero de viajes por semana fue: " + tableViajes.size());

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//---------------------------------------
			//lectura vertices 
			CSVReader readerVertices=null;
			int totalVertices=0;
			try 
			{
				readerVertices = new CSVReader(new FileReader("./data/bogota_vertices.txt"));
				String[] nextline1 = readerVertices.readNext();
				nextline1= readerVertices.readNext();
				int f=0;
				while(nextline1 !=null)
				{
					String linea = nextline1[0];
					String[] datos = linea.split(";");
					int id = Integer.parseInt(datos[0]);
					double longitud = Double.parseDouble(datos[1]);
					double latitud = Double.parseDouble(datos[2]);
					double MOV_ID = Double.parseDouble(datos[3]);
					f++;
					Vertices nuevo = new Vertices(id, longitud, latitud, MOV_ID);
					tableVertices.put(id, nuevo);
					nextline1 = readerVertices.readNext();
				}
				readerVertices.close();

				System.out.println("El numero de vertices es: " + tableVertices.size());
			}catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//---------------------------------------
			//lectura arcos 
			CSVReader readerArcos=null;
			try 
			{
				readerArcos = new CSVReader(new FileReader("./data/bogota_arcos.txt"));
				String[] nextline2 = readerArcos.readNext();		
				int x=0;
				while(nextline2 !=null)
				{
					String linea = nextline2[0];
					String[] datos = linea.split(" ");
					int idNodo = Integer.parseInt(datos[0]);  
					Vertices nodo = (Vertices) tableVertices.get(idNodo);
					if(nodo != null)
					{
						LinkedQueue listaAd = nodo.darListaAdyacencia();
						for(int i = 1; i < datos.length; i++)
						{
							int idNodoConecetado = Integer.parseInt(datos[i]);
							Vertices nodoConectado = (Vertices) tableVertices.get(idNodoConecetado);
							listaAd.enqueue(nodoConectado);
						}

						
					}
					nextline2 = readerArcos.readNext();
					x++;
				}
				Vertices prueba=tableVertices.get(175144);
				LinkedQueue listaprueba=prueba.darListaAdyacencia();
						Iterator iter=listaprueba.iterator();
						
						while(iter.hasNext()) {
							Vertices actual=(Vertices) iter.next();
							System.out.println(actual.getId());
						}
				System.out.println("El numero de arcos es: " + x);
				readerArcos.close();
				
			}catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		    
	    //----------------------------------------------------------
	    //METODOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOS
	    
	    public void function1()
	    {
	    }
	
	    public void function2()
	    {
	    }
	    
	    public void function3()
	    {
	    }
	    
	    public void function4()
	    {
	    }
	    
	    public void function5()
	    {
	    }
	    
	    public void function6()
	    {
	    }
	    
	    public void function7()
	    {	
	    }
	    
	    public void function8()
	    {	
	    }
	    
	    public void function9()
	    {
	    }
	    
	    public void function10()
	    {
	    }
	    
	    public void function11()
	    {
	    }
	    
	    public void function12()
	    {
	    }
	}
	

