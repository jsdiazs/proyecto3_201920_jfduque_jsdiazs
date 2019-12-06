package model.logic;

import java.awt.Taskbar.Feature;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

import model.data_structures.tablaDeHashLinearProbing;


/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */

	
	
	private tablaDeHashLinearProbing table;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		table = new tablaDeHashLinearProbing();
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
					table.put(w, nuevo);
					nextline = readerWeek.readNext();					
				}
				readerWeek.close();
				
				totalViajes += w;
				

				System.out.println("El n�mero de viajes por semana fue: " + w);

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
					table.put(f, nuevo);
					nextline1 = readerVertices.readNext();
				}
				readerVertices.close();
				
				totalVertices+=f;
				System.out.println("El numero de vertices es: " + f);
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
				String[] nextline1 = readerArcos.readNext();		
				int x=0;
				while(nextline1 !=null)
				{
					int id = Integer.parseInt(nextline1[0]);
					nextline1 = readerArcos.readNext();
				}
				System.out.println("El numero de vertices es: " + x);
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
	

