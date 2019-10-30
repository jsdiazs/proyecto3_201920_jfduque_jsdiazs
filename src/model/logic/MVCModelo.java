package model.logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import model.data_structures.ArbolRojoNegro;
import model.data_structures.DinamicArray;
import model.data_structures.LinkedQueue;
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
	private ArbolRojoNegro arbolHora;
	private ArbolRojoNegro arbolSemana;
	private ArbolRojoNegro arbolMes;
	
	private MaxPQ queue;
	
	private tablaDeHashLinearProbing table;
	
	private FCollection g;

	private tablaDeHashLinearProbing<String, Feature> TablaHashZn;
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		arbolHora = new ArbolRojoNegro();
		arbolSemana = new ArbolRojoNegro();
		arbolMes = new ArbolRojoNegro();
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
				while(nextline != null && h < 10000)
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
					arbolHora.put(h, nuevo);
					nextline = readerHour.readNext();					
				}
				readerHour.close();
				
				totalViajes += h;
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
				while(nextline != null && w < 10000)
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
					arbolSemana.put(w, nuevo);
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
				while(nextline != null && m < 10000)
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
					arbolMes.put(m, nuevo);
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
					readerMalla = new CSVReader(new FileReader("./data/Nodes_of_red_vial-wgs84_shp.txt"));			
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
				
				try {
					JSONReader();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
		

	
		//--------------------------------------------------------------------------------------
		//JSON READER
	
	    public  FCollection JSONReader() throws Exception 
	    {

	    	FileInputStream inputStream = new FileInputStream("data/bogota_cadastral.json");

	        inputStream  = new FileInputStream("./data/bogota_cadastral.json");
	        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


	         g = new Gson().fromJson(bufferedReader, FCollection.class);
	        
	        System.out.println("Zonas cargadas por JSON fueron: " +g.features[g.features.length-1].properties.MOVEMENT_ID);
	        return g;

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
	    
	    
	    //----------------------------------------------------------
	    //METODOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOS
	    
	    public void function1()
	    {
	    	tablaDeHashLinearProbing tablaNombres = new tablaDeHashLinearProbing();
	    	tablaDeHashLinearProbing tablaCantidad = new tablaDeHashLinearProbing();
	    	
	    	FCollection g = null;
			try {
				g = JSONReader();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	for(int i = 0; i < g.features.length; i++)
	    	{
	    		String nombre = g.features[i].properties.scanombre;
	    		char comienza = nombre.charAt(0);
	    		tablaNombres.put(i, nombre);
	    		
	    		if(!tablaCantidad.contains(comienza))
	    		{
	    			tablaCantidad.put(comienza, 1);
	    		}
	    		else
	    		{
	    			int cant = (int) tablaCantidad.get(comienza) + 1;
	    			tablaCantidad.put(comienza, cant);
	    		}
	    		
	    	}
	    	
	    	Iterable iterable = tablaCantidad.keys();
	    	Iterator iter = iterable.iterator();
	    	int cantMayor = 0;
	    	char letra = 1;
	    	while(iter.hasNext())
	    	{
	    		char actual = (char) iter.next();
	    		int cant = (int) tablaCantidad.get(actual);
	    		if(cant > cantMayor)
	    		{
	    			cantMayor = cant;
	    			letra = actual;
	    		}
	    	}
	    	System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
	    	System.out.println("La letra que mas se repite al comienzo del nombre de una zona es: " + letra);
	    	System.out.println("Se repite " + cantMayor + " veces y las zonas son:");
	    	
	    	Iterable iterable2 = tablaNombres.keys();
	    	Iterator iter2 = iterable2.iterator();
	    	while(iter2.hasNext())
	    	{
	    		int actual = (int) iter2.next();
	    		String nomb = (String) tablaNombres.get(actual);
	    		if(nomb.charAt(0) == letra)
	    		{
	    			System.out.println(nomb);
	    		}
	    	}
	
	    }
	   
	
	    public void function2(double pLong, double pLat)
	    {
	    	Iterable iterable2 = table.keys();
	    	Iterator iter2 = iterable2.iterator();
	    	while(iter2.hasNext())
	    	{
	    		int actual = (int) iter2.next();
	    		String longyLat = (String) table.get(actual);
	    		
	    		String[] longyLati = longyLat.split(",");
	    		double longitud = Double.parseDouble(longyLati[0]);
	    		double latitud = Double.parseDouble(longyLati[1]);
	    		if(longitud == pLong && latitud == pLat)
	    		{
	    			System.out.println(longitud + "---" + latitud);
	    		}
	    	}
	    }
	    
	    public void function3(int pN, int pLb, int pLa)
	    {
	    	Iterable iterable = arbolMes.keys();
	    	Iterator iter = iterable.iterator();
	    	int n = 0;
	    	while(iter.hasNext() && n < pN)
	    	{
	    		int keyActual = (int) iter.next();
	    		Viaje actual = (Viaje) arbolMes.get(keyActual);
	    		if((actual.getMean_travel_time() >= pLb) && (actual.getMean_travel_time() <= pLa))
	    		{
	    			System.out.println("Origen: " + actual.getSourceid() + " --- Destino: " + actual.getDstid() + " --- Mes: " + actual.getHourDayMonth() + " --- Tiempo: " + actual.getMean_travel_time());
	    			n++;
	    		}
	    	}
	    	
	    }
	    
	    /**
	     * Retorna las N zonas mas al norte. 
	     * @param nZonas cantidad de zonas que se retornaran.
	     * @return 
	     */
	    public tablaDeHashLinearProbing<String, double[]> function4(int nZonas)
	    {
	    	tablaDeHashLinearProbing<String, double[]> retorno = new tablaDeHashLinearProbing<>();
	        tablaDeHashLinearProbing<String, Feature> copia = table;  

	        double LatitudMas = 0.0;
	        String zonaId = "";
	        String keyLatitudMas = "";
	        String keyMas = "";
	        
	        double latTemp = 0.0; 
	        String idTemp = ""; 
	        double longTemp = 0.0;
	        String KeyTemp = "";
	        Feature zonaTemp; 

	        double[] arrTempCoord = new double[2];

	        while(nZonas>0)
	        {
	            Iterable iterable = copia.keys();
		    	 Iterator iter = iterable.iterator();
	            while(iter.hasNext()) 
	            {
	                String keyActual= (String) iter.next(); 
	                Feature zonaActual = copia.get(keyActual); 

	                double [][][][] Cords= zonaActual.geometry.coordinates; 
	                int i=0;
	                while(i<Cords.length)
	                {
	                    int j=0;
	                    while(j<Cords[i].length)
	                    {
	                        int x=0;
	                        while(x<Cords[i][j].length)
	                        {
	                            int y=0;
	                            while(y<Cords[i][j][x].length)
	                            {
	                                double[] Cord = Cords[i][j][x]; 
	                                double lat = Cord[1];
	                                double lon = Cord[0];

	                                Object[] nodo = new Object[3];

	                                String llave= zonaActual.properties.MOVEMENT_ID;

	                                nodo[0]= zonaActual.properties.scanombre;
	                                nodo[1]= lat;
	                                nodo[2]=lon;
	                                		
	                                latTemp = (double) nodo[1];
	                                idTemp = (String)nodo[0];
	                                longTemp = (double)nodo[2];
	                                llave = KeyTemp;

	                                y++;
	                            }
	                            x++;
	                        }
	                        j++;
	                    }
	                    i++;
	                }


	                if( latTemp > LatitudMas ) //Si la latitud de la pos. i,j,z es mayor a la actual, se cambia la mayor
	                {
	                    latTemp = LatitudMas;
	                    idTemp = zonaId;
	                    keyMas = KeyTemp; 

	                    arrTempCoord[0] = latTemp; 
	                    arrTempCoord[1] = longTemp; 

	                }

	            }

	            copia.delete(keyMas);

	            retorno.put(KeyTemp, arrTempCoord );

	            nZonas--;
	        }

	        return retorno;
	    }

	    
	    public ArbolRojoNegro<String, Object[]> function5(double latitud, double longitud)
	    {
	    	 ArbolRojoNegro<String,Object[]>retornar= new ArbolRojoNegro();

	         DinamicArray dn = (DinamicArray) TablaHashZn.keys(); 
	         Iterator iter = dn.iterator(); 

	         while(iter.hasNext())
	         {
	             String key= (String) iter.next(); 

	             Feature zonaActual = TablaHashZn.get(key); 

	             double [][][][] Cord= zonaActual.geometry.coordinates;

	             int i=0;
	             while(i < Cord.length)
	             {
	                 int j=0;
	                 while(j < Cord[i].length)
	                 {
	                     int x = 0;
	                     while(x < Cord[i][j].length)
	                     {
	                         int y = 0;
	                         while(y < Cord[i][j][x].length)
	                         {
	                             double[] coordenada= Cord[i][j][y];

	                             double lat = coordenada[1];
	                             double lon = coordenada[0];

	                             if( lat == latitud && lon == longitud) 
	                             {
	                                 if(Math.floor(lat*100) == Math.floor(latitud*100) && Math.floor(lon*100) == Math.floor(longitud*100))
	                                 {
	                                     Object[] nodo= new Object[3];
	                                     nodo[0]=zonaActual.properties.scanombre;
	                                     nodo[1]= lat;
	                                     nodo[2]= lon;
	                                     String llave= zonaActual.properties.MOVEMENT_ID;
	                                     retornar.put(llave, nodo);
	                                 }
	                             }
	                             y++;
	                         }
	                         x++;
	                     }
	                     j++;
	                 }
	                 i++;
	             }   
	         }

	         return retornar;
	     }
	    
	    public void function6(int NViajes, int limite_bajo, int limite_alto)
	    {
	    	 Iterable iterable = arbolMes.keys();
	    	 Iterator iter = iterable.iterator();
	    	 int n=0;
	    	 while(iter.hasNext() && n<NViajes)
	    	 {
	    		 int KeyCurrent =(int)iter.next();
	    		 Viaje current = (Viaje) arbolMes.get(KeyCurrent);
	    		 if((current.getStandard_deviation_travel_time() >= limite_bajo)&&(current.getStandard_deviation_travel_time()<=limite_alto))
	    		 {
	    			 System.out.println("Zona Origen: " + current.getSourceid() + "Zona destino: " + current.getDstid() + "Mes: " + current.getHourDayMonth() + "Desviación estandar: " + current.getStandard_deviation_travel_time());
	    			 n++;
	    		 }
	    		 
	    	 }
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
	    
	}
	

