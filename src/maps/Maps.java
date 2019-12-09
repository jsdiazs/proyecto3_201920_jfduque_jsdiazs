package view;
import java.awt.BorderLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.swing.MapView;

import model.data_structures.Arco;
import model.data_structures.Graph;
import model.data_structures.Queue;
import model.data_structures.Stack;
import model.logic.Vertices;
import model.vo.Vertice;
import model.vo.Interseccion;
import sun.text.resources.cldr.chr.FormatData_chr;

import com.teamdev.jxmaps.*;

public class Maps extends MapView{

	private static Map map;

	private CircleOptions settingsCircle;

	private Vertices grafo;

	private  PolylineOptions settingsLine;


	public CircleOptions getSettingsCircle(){
		return settingsCircle;
	}

	public void setSettingsCircle(CircleOptions settingsCircle){
		this.settingsCircle = settingsCircle;
	}

	public void generateSimplePath(LatLng start,LatLng end){
		LatLng[] path = {start,end};
		Polyline polyline = new Polyline(map);
		polyline.setPath(path);
		polyline.setOptions(settingsLine);
	}

	public Map darMap(){
		return map;
	}

	public void generateArea(LatLng center){
		Circle circle = new Circle(map);
		circle.setCenter(center);
		circle.setRadius(20);
		circle.setVisible(true);
		circle.setOptions(settingsCircle);
	}

	public void GenerateLine(LatLng... path){
		Polyline polyline = new Polyline(map);
		polyline.setPath(path);
	}

	public Maps(String pString, Vertices grafo, Interseccion inter1, Interseccion inter2){
		this.grafo = grafo;

		JFrame frame = new JFrame("Washington-Data: "+pString);

		settingsCircle=new CircleOptions();
		settingsCircle.setFillColor("#FF0000");
		settingsCircle.setRadius(10);
		settingsCircle.setFillOpacity(0.35);

		settingsLine=new PolylineOptions();
		settingsLine.setGeodesic(true);
		settingsLine.setStrokeColor("#FF0000");
		settingsLine.setStrokeOpacity(1.0);
		settingsLine.setStrokeWeight(2.0);

		// Setting of a ready handler to MapView object. onMapReady will be called when map initialization is done and
		// the map object is ready to use. Current implementation of onMapReady customizes the map object.
		setOnMapReadyHandler(new MapReadyHandler() {
			@Override
			public void onMapReady(MapStatus status) {
				// Check if the map is loaded correctly
				if (status == MapStatus.MAP_STATUS_OK) {
					// Getting the associated map object
					map = getMap();
					MapOptions mapOptions = new MapOptions();
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
					mapOptions.setMapTypeControlOptions(controlOptions);

					map.setOptions(mapOptions);
					map.setCenter(new LatLng(38.9041, -77.0171));
					map.setZoom(10);

				}
			}
		});

		System.out.println("Cargando ...");
		try {
			for(int i=0;i<10;i++){
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e1){
			e1.printStackTrace();
		}
		frame.add(this, BorderLayout.CENTER);
		frame.setSize(1500, 1000);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		//Vertice #1
		Marker nuevoMarcador = new Marker(map);	//Pone el marcador en el mapa						
		LatLng coordenadas = new LatLng(inter1.getLat(), inter1.getLon()); //Coordenadas Latitud-longitud
		nuevoMarcador.setPosition(coordenadas); //Configura y guarda la posicion											

		//Vertice #2
		Marker nuevoMarcador2 = new Marker(map);	//Pone el marcador en el mapa						
		LatLng coordenadas2 = new LatLng(inter2.getLat(), inter2.getLon()); //Coordenadas Latitud-longitud
		nuevoMarcador2.setPosition(coordenadas2); //Configura y guarda la posicion

		generarArcos();
	}

	public void generarArcos(){
		Iterable<String> llaves = grafo.getVertices().keys();
		for (String string : llaves){
			Queue<Arco> cola = (Queue<Arco>) grafo.getAdyacentes().get(string);
			if(cola!=null){
				for (Arco arco : cola){
					Interseccion infoVer1 = (Interseccion) arco.getExtremo2().getInfo();
					Interseccion infoVer2 = (Interseccion) arco.getExtremo1().getInfo();
					/**if(infoVer1.getLat()>38.8847 && infoVer1.getLat()< 38.9135 &&
							infoVer2.getLat()>38.8847 && infoVer2.getLat()< 38.9135
							&& infoVer1.getLon()>-77.0542 && infoVer1.getLon()<-76.9976
							&& infoVer2.getLon()>-77.0542 && infoVer2.getLon()<-76.9976)*/
					generateSimplePath(new LatLng(infoVer1.getLat(), infoVer1.getLon()), new LatLng(infoVer2.getLat(), infoVer2.getLon()));
				}
				/**Vertice ver = (Vertice) grafo.getVertices().get(string);
				Interseccion infoVer3 = (Interseccion) ver.getInfo();
				if(infoVer3.getLat()>38.8847 && infoVer3.getLat()< 38.9135
						&& infoVer3.getLon()>-77.0542 && infoVer3.getLon()<-76.9976)
					generateArea(new LatLng(infoVer3.getLat(), infoVer3.getLon()));*/
			}
		}
	}

	public Mapa(LatLng[] a)
	{
		JFrame frame = new JFrame("Washington-Data: ");

		setOnMapReadyHandler(new MapReadyHandler() {
			@Override
			public void onMapReady(MapStatus status) {
				// Check if the map is loaded correctly
				if (status == MapStatus.MAP_STATUS_OK) {
					// Getting the associated map object
					map = getMap();
					MapOptions mapOptions = new MapOptions();
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
					mapOptions.setMapTypeControlOptions(controlOptions);

					map.setOptions(mapOptions);
					map.setCenter(new LatLng(38.9041, -77.0171));
					map.setZoom(10);

				}
			}
		});

		System.out.println("Cargando ...");
		try {
			for(int i=0;i<10;i++){
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e1){
			e1.printStackTrace();
		}
		frame.add(this, BorderLayout.CENTER);
		frame.setSize(1500, 1000);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		settingsCircle=new CircleOptions();
		settingsCircle.setFillColor("#FF0000");
		settingsCircle.setStrokeColor("#FFFFFF");
		settingsCircle.setRadius(100);
		settingsCircle.setFillOpacity(0.7);

		for (LatLng latLng : a) {
			generateArea(latLng);
		}
	}

	public Mapa(String pString, Graph grafo, Queue<Interseccion> vertices){
		this.grafo = grafo;

		JFrame frame = new JFrame("Washington-Data: "+pString);

		setOnMapReadyHandler(new MapReadyHandler() {
			@Override
			public void onMapReady(MapStatus status) {
				// Check if the map is loaded correctly
				if (status == MapStatus.MAP_STATUS_OK) {
					// Getting the associated map object
					map = getMap();
					MapOptions mapOptions = new MapOptions();
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
					mapOptions.setMapTypeControlOptions(controlOptions);

					map.setOptions(mapOptions);
					map.setCenter(new LatLng(38.9041, -77.0171));
					map.setZoom(10);

				}
			}
		});

		System.out.println("Cargando ...");
		try {
			for(int i=0;i<10;i++){
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e1){
			e1.printStackTrace();
		}
		frame.add(this, BorderLayout.CENTER);
		frame.setSize(1500, 1000);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		generarArcos(grafo);
		generarVertices2(vertices);

	}

	public void generarVertices2(Queue<Interseccion> vertices){
		settingsCircle=new CircleOptions();
		settingsCircle.setFillColor("#4682B4");
		settingsCircle.setStrokeColor("#4682B4");
		settingsCircle.setRadius(20);
		settingsCircle.setFillOpacity(0.7);

		for (Interseccion inter: vertices){
			generateArea(new LatLng(inter.getLat(), inter.getLon()));
		}
	}

	public void generarArcos(Graph pGrafo){
		settingsCircle = new CircleOptions();
		settingsCircle.setFillColor("#FF4500");
		settingsCircle.setStrokeColor("#FF4500");
		settingsCircle.setRadius(10);
		settingsCircle.setFillOpacity(0.7);
		settingsCircle.setStrokeWeight(3.0);

		settingsLine = new PolylineOptions();
		settingsLine.setGeodesic(true);
		settingsLine.setStrokeColor("#FF4500");
		settingsLine.setStrokeOpacity(1.0);
		settingsLine.setStrokeWeight(2.0);

		Iterable<String> llaves = pGrafo.getVertices().keys();
		for (String string : llaves){
			Queue<Arco> cola = (Queue<Arco>) grafo.getAdyacentes().get(string);
			if(cola!=null){
				for (Arco arco : cola){
					Interseccion infoVer1 = (Interseccion) arco.getExtremo2().getInfo();
					Interseccion infoVer2 = (Interseccion) arco.getExtremo1().getInfo();
					generateSimplePath(new LatLng(infoVer1.getLat(), infoVer1.getLon()), new LatLng(infoVer2.getLat(), infoVer2.getLon()));
				}
				Vertice ver = (Vertice) grafo.getVertices().get(string);
				Interseccion infoVer3 = (Interseccion) ver.getInfo();
				generateArea(new LatLng(infoVer3.getLat(), infoVer3.getLon()));
			}
		}
	}


	public Mapa(Stack<String> a, Graph<String, Interseccion, Double> grafo)
	{
		JFrame frame = new JFrame("Washington-Data: ");

		setOnMapReadyHandler(new MapReadyHandler() {
			@Override
			public void onMapReady(MapStatus status) {
				// Check if the map is loaded correctly
				if (status == MapStatus.MAP_STATUS_OK) {
					// Getting the associated map object
					map = getMap();
					MapOptions mapOptions = new MapOptions();
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
					mapOptions.setMapTypeControlOptions(controlOptions);

					map.setOptions(mapOptions);
					map.setCenter(new LatLng(38.9041, -77.0171));
					map.setZoom(10);

				}
			}
		});

		System.out.println("Cargando ...");
		try {
			for(int i=0; i < 10;i++){
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e1){
			e1.printStackTrace();
		}
		frame.add(this, BorderLayout.CENTER);
		frame.setSize(1500, 1000);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		settingsCircle=new CircleOptions();
		settingsCircle.setFillColor("#FF0000");
		settingsCircle.setRadius(10);
		settingsCircle.setFillOpacity(0.35);

		settingsLine=new PolylineOptions();
		settingsLine.setGeodesic(true);
		settingsLine.setStrokeColor("#FF0000");
		settingsLine.setStrokeOpacity(1.0);
		settingsLine.setStrokeWeight(2.0);

		boolean inicio= true;
		String ant = "";
		for (String string : a) {
			if(inicio)
			{
				ant = string;
				inicio = false;
				continue;
			}
			LatLng primerNodo = new LatLng(grafo.getInfoVertex(ant).getLat(), grafo.getInfoVertex(ant).getLon());
			LatLng segundoNodo = new LatLng(grafo.getInfoVertex(string).getLat(), grafo.getInfoVertex(string).getLon());
			generateSimplePath(primerNodo, segundoNodo);
			generateArea(primerNodo);
			generateArea(segundoNodo);
			ant = string;

		}
	}

	public Mapa(String pString, Iterable<Arco> arcos){
		JFrame frame = new JFrame("Washington-Data: "+pString);

		settingsCircle=new CircleOptions();
		settingsCircle.setFillColor("#FF0000");
		settingsCircle.setStrokeColor("#4682B4");
		settingsCircle.setRadius(50);
		settingsCircle.setFillOpacity(0.35);

		settingsLine=new PolylineOptions();
		settingsLine.setGeodesic(true);
		settingsLine.setStrokeColor("#FF0000");
		settingsLine.setStrokeOpacity(1.0);
		settingsLine.setStrokeWeight(2.0);

		// Setting of a ready handler to MapView object. onMapReady will be called when map initialization is done and
		// the map object is ready to use. Current implementation of onMapReady customizes the map object.
		setOnMapReadyHandler(new MapReadyHandler() {
			@Override
			public void onMapReady(MapStatus status) {
				// Check if the map is loaded correctly
				if (status == MapStatus.MAP_STATUS_OK) {
					// Getting the associated map object
					map = getMap();
					MapOptions mapOptions = new MapOptions();
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
					mapOptions.setMapTypeControlOptions(controlOptions);

					map.setOptions(mapOptions);
					map.setCenter(new LatLng(38.9041, -77.0171));
					map.setZoom(10);

				}
			}
		});

		System.out.println("Cargando ...");
		try {
			for(int i=0;i<10;i++){
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e1){
			e1.printStackTrace();
		}
		frame.add(this, BorderLayout.CENTER);
		frame.setSize(1500, 1000);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		generarArcosReq1C(arcos);
	}

	public void generarArcosReq1C(Iterable<Arco> arcos){
		Iterable<Arco> arquitos = arcos;
		for (Arco arcotes : arquitos){
			Interseccion infoVer1 = (Interseccion) arcotes.getExtremo2().getInfo();
			Interseccion infoVer2 = (Interseccion) arcotes.getExtremo1().getInfo();
			generateSimplePath(new LatLng(infoVer1.getLat(), infoVer1.getLon()), new LatLng(infoVer2.getLat(), infoVer2.getLon()));
			generateArea(new LatLng(infoVer1.getLat(), infoVer1.getLon()));
			generateArea(new LatLng(infoVer2.getLat(), infoVer2.getLon()));
		}
	}

	public Mapa( Graph<String, Interseccion, Double> grafito){
		JFrame frame = new JFrame("Washington-Data: ");

		settingsCircle=new CircleOptions();
		settingsCircle.setFillColor("#FF0000");
		settingsCircle.setRadius(50);
		settingsCircle.setFillOpacity(0.35);

		settingsLine=new PolylineOptions();
		settingsLine.setGeodesic(true);
		settingsLine.setStrokeColor("#FF0000");
		settingsLine.setStrokeOpacity(1.0);
		settingsLine.setStrokeWeight(2.0);

		// Setting of a ready handler to MapView object. onMapReady will be called when map initialization is done and
		// the map object is ready to use. Current implementation of onMapReady customizes the map object.
		setOnMapReadyHandler(new MapReadyHandler() {
			@Override
			public void onMapReady(MapStatus status) {
				// Check if the map is loaded correctly
				if (status == MapStatus.MAP_STATUS_OK) {
					// Getting the associated map object
					map = getMap();
					MapOptions mapOptions = new MapOptions();
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
					mapOptions.setMapTypeControlOptions(controlOptions);

					map.setOptions(mapOptions);
					map.setCenter(new LatLng(38.9041, -77.0171));
					map.setZoom(10);

				}
			}
		});

		System.out.println("Cargando ...");
		try {
			for(int i=0;i<10;i++){
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e1){
			e1.printStackTrace();
		}
		frame.add(this, BorderLayout.CENTER);
		frame.setSize(1500, 1000);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		String anterior = "";
		boolean inicio = true;
		for (String element : grafito.getVertices().keys()) {
			if(inicio){
				anterior =  element;
				inicio = false;
				continue;
			}
			Interseccion int1 = grafito.getInfoVertex(anterior);
			Interseccion int2 =  grafito.getInfoVertex(element);
			generateSimplePath(new LatLng(int1.getLat(), int1.getLon()), new LatLng(int2.getLat(), int2.getLon()));
			generateArea(new LatLng(int1.getLat(), int1.getLon()));
			generateArea(new LatLng(int2.getLat(), int2.getLon()));


		}
	}

	public Mapa(String pString, Graph grafo, double pDistancia){
		this.grafo = grafo;

		JFrame frame = new JFrame("Washington-Data: "+pString);

		settingsCircle=new CircleOptions();
		settingsCircle.setFillColor("#FF0000");
		settingsCircle.setStrokeColor("#4682B4");
		settingsCircle.setRadius(50);
		settingsCircle.setFillOpacity(0.35);

		// Setting of a ready handler to MapView object. onMapReady will be called when map initialization is done and
		// the map object is ready to use. Current implementation of onMapReady customizes the map object.
		setOnMapReadyHandler(new MapReadyHandler() {
			@Override
			public void onMapReady(MapStatus status) {
				// Check if the map is loaded correctly
				if (status == MapStatus.MAP_STATUS_OK) {
					// Getting the associated map object
					map = getMap();
					MapOptions mapOptions = new MapOptions();
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
					mapOptions.setMapTypeControlOptions(controlOptions);

					map.setOptions(mapOptions);
					map.setCenter(new LatLng(38.9041, -77.0171));
					map.setZoom(10);

				}
			}
		});

		System.out.println("Cargando ...");
		try {
			for(int i=0;i<10;i++){
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e1){
			e1.printStackTrace();
		}
		frame.add(this, BorderLayout.CENTER);
		frame.setSize(1500, 1000);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		generarArcosReqC3(pDistancia);
	}

	public void generarArcosReqC3(double pDistancia){
		Iterable<String> llaves = grafo.getVertices().keys();
		for (String string : llaves){
			Queue<Arco> cola = (Queue<Arco>) grafo.getAdyacentes().get(string);
			if(cola != null){
				for (Arco arco : cola){

					settingsLine=new PolylineOptions();
					settingsLine.setGeodesic(true);
					settingsLine.setStrokeColor("#FF0000");
					settingsLine.setStrokeOpacity(1.0);
					settingsLine.setStrokeWeight(2.0);

					if((Double) arco.getInfo() == pDistancia){
						settingsLine=new PolylineOptions();
						settingsLine.setGeodesic(true);
						settingsLine.setStrokeColor("#00DA7C");
						settingsLine.setStrokeOpacity(1.0);
						settingsLine.setStrokeWeight(3.0);
					}

					Interseccion infoVer1 = (Interseccion) arco.getExtremo2().getInfo();
					Interseccion infoVer2 = (Interseccion) arco.getExtremo1().getInfo();
					generateSimplePath(new LatLng(infoVer1.getLat(), infoVer1.getLon()), new LatLng(infoVer2.getLat(), infoVer2.getLon()));
				}
				Vertice ver = (Vertice) grafo.getVertices().get(string);
				Interseccion infoVer3 = (Interseccion) ver.getInfo();
				generateArea(new LatLng(infoVer3.getLat(), infoVer3.getLon()));
			}
		}
	}
}