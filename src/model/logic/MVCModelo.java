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
	}
		

	
		//--------------------------------------------------------------------------------------
		//JSON READER
	
	    public  void JSONReader() throws Exception 
	    {

	    	FileInputStream inputStream = new FileInputStream("data/bogota_cadastral.json");

	        inputStream  = new FileInputStream("./data/bogota_cadastral.json");
	        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


	         g = new Gson().fromJson(bufferedReader, FCollection.class);
	        
	        System.out.println("Zonas cargadas por JSON fueron: " +g.features[g.features.length-1].properties.MOVEMENT_ID);
System.out.println(g.features[0].geometry.coordinates);
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
	    
<<<<<<< HEAD
	    public void function1() {
	    	String prueba;
	       prueba= g.features[0].properties.DISPLAY_NAME;
=======
	    public void function1()
	    {
	    	tablaDeHashLinearProbing tablaNombres = new tablaDeHashLinearProbing();
	    	
<<<<<<< HEAD

>>>>>>> ccbee580dbc8e0e822491ba7d967d11dd8908ebd
=======
>>>>>>> a13130867995cbe3a1e7bca0805f89869bc1690f
	    }
	    
	
	    public void function2()
	    {
	    	
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
	     */
	    public void function4(int nZonas)
	    {
	    	tablaDeHashLinearProbing<String, double[]> retorno = new tablaDeHashLinearProbing<>();
	        tablaDeHashLinearProbing<String, Feature> copia = TablaHashZn;  

	        double LatitudMas = 0.0;
	        String zonaId = "";
	        String keyLatitudMas = "";
	        String keyMas = "";
	        
	        double latTemp = 0.0; 
	        String nomTemp = ""; 
	        double longTemp = 0.0;
	        String llaveTemp = "";
	        Feature zonaTemp; 

	        double[] arrTempCoord = new double[2];

	        while(n>0)
	        {
	            LinkedQueue que = (LinkedQueue) copia.keys(); 
	            Iterator iter = que.iterator();

	            while(iter.hasNext()) //Iterar sobre la copia de Keys
	            {
	                String keyActual= (String) iter.next(); //Key actual

	                Feature zona = copia.get(keyActual);  //Valor de la Key actual, es decir la zona. 

	                double [][][][] coordenadas= zona.getGeometrias().getCoordinates(); //Arreglo de coordenadas de la zona




	                int i=0;
	                while(i<coordenadas.length)
	                {
	                    int j=0;
	                    while(j<coordenadas[i].length)
	                    {
	                        int z=0;
	                        while(z<coordenadas[i][j].length)
	                        {
	                            int w=0;
	                            while(w<coordenadas[i][j][z].length)
	                            {
	                                double[] coordenada = coordenadas[i][j][z]; //Coordenadas en la posicion i, j & z 

	                                double lat = coordenada[1];
	                                double lon = coordenada[0];

	                                Object[] nodo = new Object[3];

	                                String llave= zona.getPropiedades().getMOVEMENT_ID();

	                                nodo[0]= zona.getPropiedades().getScanombre();
	                                nodo[1]= lat;
	                                nodo[2]=lon;




	                                //Asignar a las variables temporales AFUERA del ciclo
	                                latTemp = (double) nodo[1];
	                                nomTemp = (String)nodo[0];
	                                longTemp = (double)nodo[2];
	                                llave = llaveTemp;

	                                w++;
	                            }
	                            z++;
	                        }
	                        j++;
	                    }
	                    i++;
	                }


	                if( latTemp > latitudMax ) //Si la latitud de la pos. i,j,z es mayor a la actual, se cambia la mayor
	                {
	                    latTemp = latitudMax;

	                    nomTemp = nombreZona;
	                    keyMax = llaveTemp; 

	                    arrTempCoord[0] = latTemp; 
	                    arrTempCoord[1] = longTemp; 

	                }

	            }

	            copia.delete(keyMax);

	            retorno.put(llaveTemp, arrTempCoord );

	            n--;
	        }







	        return retorno;
	    }


	    }
	    
	    public ArbolRojoNegro<String, Object[]> function5(double latitud, double longitud)
	    {
	    	 ArbolRojoNegro<String,Object[]>retornar= new ArbolRojoNegro();

	         LinkedQueue lq = (LinkedQueue) TablaHashZn.keys(); 
	         Iterator iter = lq.iterator(); 

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
	    
	    public void function6()
	    {
	        MaxPQ<TravelTime> copia= heapMes;   
	        MaxHeapCP<TravelTime> retorno= new MaxHeapCP();

	        while(!copia.isEmpty()&& (N)>0) //Recorrer la copia
	        {
	            TravelTime actual = (TravelTime) copia.delMax(); //Borrar el TravelTime actual para despues agregarlo al retorno
	            double desviacionEst = actual.getStandardDeviationTravelTime();

	            if( desviacionEst > limiteBajo && desviacionEst < limiteAlto ) //Rango de desviación estandar
	            {
	                retorno.insert(actual); //Se inserta en el retorno con la condicion de que esté en el rango
	                N--;
	            }
	        }
	        return retorno;
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
	

