package model.logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.opencsv.CSVReader;

import model.data_structures.ArbolRojoNegro;
import model.data_structures.MaxPQ;
import model.data_structures.tablaDeHashLinearProbing;


/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private ArbolRojoNegro tree;
	
	private MaxPQ queue;
	
	private tablaDeHashLinearProbing table;
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		tree = new ArbolRojoNegro();
		//queue = new MaxPQ();
		table = new tablaDeHashLinearProbing();
	}

	//----------------------------------------------------------------------------------------
	//CSV READER
	
	public void cargaDatos()
	{
			int totalViajes = 0;
			
			//-----------------------------------
			//Lectura para hora
			
			CSVReader readerHour = null;
			try {
				readerHour = new CSVReader(new FileReader("./data/bogota-cadastral-2018-1-All-HourlyAggregate.csv"));			
				String[] nextline = readerHour.readNext();
				nextline = readerHour.readNext();
				int h = 0;
				while(nextline != null)
				{					
					int   sourceid = Integer.parseInt(nextline[0]);
					int   dstid = Integer.parseInt(nextline[1]);
					int   dayHourMonth = Integer.parseInt(nextline[2]);
					float mean_travel_time = Float.parseFloat(nextline[3]);
					float standard_deviation_travel_time = Float.parseFloat(nextline[4]);
					float geometric_mean_travel_time = Float.parseFloat(nextline[5]);
					float geometric_standard_deviation_travel_time = Float.parseFloat(nextline[6]);
					h++;
					Viaje nuevo = new Viaje(sourceid, dstid, dayHourMonth, mean_travel_time, standard_deviation_travel_time, geometric_mean_travel_time, geometric_standard_deviation_travel_time);
					
					nextline = readerHour.readNext();					
				}
				readerHour.close();
				
				totalViajes += h;
				
				System.out.println("");
				System.out.println("El número de viajes por hora fue: " + h);

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//-----------------------------------
			//Lectura para semana
			
			CSVReader readerWeek = null;
			try {
				readerWeek = new CSVReader(new FileReader("./data/bogota-cadastral-2018-1-WeeklyAggregate.csv"));			
				String[] nextline = readerWeek.readNext();
				nextline = readerWeek.readNext();
				int w = 0;
				while(nextline != null)
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

					nextline = readerWeek.readNext();					
				}
				readerWeek.close();
				
				totalViajes += w;
				

				System.out.println("El número de viajes por semana fue: " + w);

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//-----------------------------------
			//Lectura para mes
			
			CSVReader readerMonth = null;
			try {
				readerMonth = new CSVReader(new FileReader("./data/bogota-cadastral-2018-1-All-MonthlyAggregate.csv"));			
				String[] nextline = readerMonth.readNext();
				nextline = readerMonth.readNext();
				int m = 0;
				while(nextline != null)
				{					
					int   sourceid = Integer.parseInt(nextline[0]);
					int   dstid = Integer.parseInt(nextline[1]);
					int   dayHourMonth = Integer.parseInt(nextline[2]);
					float mean_travel_time = Float.parseFloat(nextline[3]);
					float standard_deviation_travel_time = Float.parseFloat(nextline[4]);
					float geometric_mean_travel_time = Float.parseFloat(nextline[5]);
					float geometric_standard_deviation_travel_time = Float.parseFloat(nextline[6]);
					m++;
					Viaje nuevo = new Viaje(sourceid, dstid, dayHourMonth, mean_travel_time, standard_deviation_travel_time, geometric_mean_travel_time, geometric_standard_deviation_travel_time);

					nextline = readerMonth.readNext();					
				}
				readerWeek.close();
				
				totalViajes += m;
				
				System.out.println("El número de viajes por mes fue: " + m);
				System.out.println("El número de viajes totales fue: " + totalViajes);

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//.--------------------------------------
			//lectura malla vial
				CSVReader readerMalla = null;
				try {
					readerMalla = new CSVReader(new FileReader("./data/Malla-Vial-Bogota/Nodes_of_red_vial-wgs84_shp.txt"));			
					String[] nextline1 = readerMalla.readNext();
					nextline1 = readerMalla.readNext();
					int v = 0;
					while(nextline1 != null)
					{					
						int   idNodo = Integer.parseInt(nextline1[0]);
						double   longitud = Double.parseDouble(nextline1[1]);
						double   latitud = Double.parseDouble(nextline1[2]);
						v++;
						nextline1 = readerMalla.readNext();		
						
						String longyLat = longitud + "," + latitud;
						
						table.put(idNodo, longyLat);
					}
					System.out.println("El número de nodos de la malla vial fue: " + v);
					String llave = (String) table.get(1);
					readerMalla.close();

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		

	
		//--------------------------------------------------------------------------------------
		//JSON READER
	
	    public  void JSONReader() throws Exception 
	    {
	        FileInputStream inputStream = new FileInputStream("./data/bogota_cadastral.json");
	        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


	        FCollection g = new Gson().fromJson(bufferedReader, FCollection.class);
	        System.out.println(g);
	    }

	    
	    class Properties 
	    {
	    	long cartodb_id;
	        String scacodigo;
	        long scatipo;
	        String scanombre;
	        double shape_leng;
	        double shape_area;
	        String MOVEMENT_ID;
	        String DISPLAY_NAME;
	    }

	    class GeometryData {
	        String type;
	        double[][][][] coordinates;

	    }

	    class Feature {
	        String type;
	        GeometryData geometry;
	        Properties properties;
	    }

	    class FCollection 
	    {
	        String type;
	        Feature[] features;
	    }
	    
	}
	

