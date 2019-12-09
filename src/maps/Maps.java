package maps;

import java.awt.BorderLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import com.teamdev.jxmaps.swing.MapView;


import com.teamdev.jxmaps.*;
import model.data_structures.DinamicArray;
import model.data_structures.tablaDeHashLinearProbing;
import model.logic.Vertices;

public class Maps extends MapView {

    private static Map mapa;

    private CircleOptions circuloConf;

    private Vertices grafo;

    private PolylineOptions lineaConf;


    public CircleOptions getcirculoConf() {
        return circuloConf;
    }

    public void setcirculoConf(CircleOptions circuloConf) {
        this.circuloConf = circuloConf;
    }

    public void generateSimplePath(LatLng[] path) {
        Polyline polyline = new Polyline(mapa);
        polyline.setPath(path);
        polyline.setOptions(lineaConf);
    }

    public Map darMap() {
        return mapa;
    }

    public void generateCircle(LatLng center) {
        Circle circle = new Circle(mapa);
        circle.setCenter(center);
        circle.setRadius(20);
        circle.setVisible(true);
        circle.setOptions(circuloConf);

    }


    public Maps(LatLng[] a, DinamicArray<LatLng[]> lista) {
        JFrame frame = new JFrame("Washington-Data: ");

        // hash tables to keep track of what has already added to the graph
        tablaDeHashLinearProbing<String, Integer> vertexMap = new tablaDeHashLinearProbing<>();
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                // Check if the map is loaded correctly
                if (status == MapStatus.MAP_STATUS_OK) {
                    // Getting the associated map object
                    mapa = getMap();
                    MapOptions mapOptions = new MapOptions();
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                    controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
                    mapOptions.setMapTypeControlOptions(controlOptions);

                    mapa.setOptions(mapOptions);
                    mapa.setCenter(new LatLng(4.6328903475179715, -74.09488677978516));
                    mapa.setZoom(14);

                }
            }
        });

        try {
            for (int i = 0; i < 8; i++) {
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        frame.add(this, BorderLayout.CENTER);
        frame.setSize(1500, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        circuloConf = new CircleOptions();
        circuloConf.setStrokeColor("#FF0000");
        circuloConf.setRadius(1.5);
        circuloConf.setFillColor("#000000");
        circuloConf.setFillOpacity(0.7);

        lineaConf = new PolylineOptions();
        lineaConf.setGeodesic(true);
        lineaConf.setStrokeColor("#4400cc");
        lineaConf.setStrokeOpacity(1.0);
        lineaConf.setStrokeWeight(2.0);

        for (int i = 0; i < 3000; i++) {

            double lat1 = lista.darElemento(i)[0].getLat();
            double long1 = lista.darElemento(i)[0].getLng();

            double lat2 = lista.darElemento(i)[1].getLat();
            double long2 = lista.darElemento(i)[1].getLng();

            if (!vertexMap.containsKey(lat1 + "$" + long1)) {
                vertexMap.putNode(lat1 + "$" + long1, 0);
                generateCircle(lista.darElemento(i)[0]);
            }
            generateSimplePath(lista.darElemento(i));
            if (!vertexMap.containsKey(lat2 + "$" + long2)) {
                vertexMap.putNode(lat2 + "$" + long2, 1);
                generateCircle(lista.darElemento(i)[1]);

            }


        }


        // print vertices
        for (int i = 0; i < a.length; i++) {
            circuloConf = new CircleOptions();
            circuloConf.setStrokeColor("#000000");
            circuloConf.setRadius(37);
            circuloConf.setFillOpacity(0.7);

            generateCircle(a[i]);
        }



    }

    // Draw MST
    public void Mapa(DinamicArray<LatLng[]> lista) {
        JFrame frame = new JFrame("Washington-Data: ");

        // hash tables to keep track of what has already added to the graph
        tablaDeHashLinearProbing<String, Integer> vertexMap = new tablaDeHashLinearProbing<>();
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                // Check if the map is loaded correctly
                if (status == MapStatus.MAP_STATUS_OK) {
                    // Getting the associated map object
                    mapa = getMap();
                    MapOptions mapOptions = new MapOptions();
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                    controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
                    mapOptions.setMapTypeControlOptions(controlOptions);

                    mapa.setOptions(mapOptions);
                    mapa.setCenter(new LatLng(4.6328903475179715, -74.09488677978516));
                    mapa.setZoom(14);

                }
            }
        });

        try {
            for (int i = 0; i < 8; i++) {
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        frame.add(this, BorderLayout.CENTER);
        frame.setSize(1500, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        circuloConf = new CircleOptions();
        circuloConf.setStrokeColor("#FF0000");
        circuloConf.setRadius(1.5);
        circuloConf.setFillColor("#000000");
        circuloConf.setFillOpacity(0.7);

        lineaConf = new PolylineOptions();
        lineaConf.setGeodesic(true);
        lineaConf.setStrokeColor("#4400cc");
        lineaConf.setStrokeOpacity(1.0);
        lineaConf.setStrokeWeight(2.0);

        // print path
        for (int i = 0; i < 2000; i++) {

            double lat1 = lista.darElemento(i)[0].getLat();
            double long1 = lista.darElemento(i)[0].getLng();

            double lat2 = lista.darElemento(i)[1].getLat();
            double long2 = lista.darElemento(i)[1].getLng();

            if (!vertexMap.containsKey(lat1 + "$" + long1)) {
                vertexMap.putNode(lat1 + "$" + long1, 0);
                generateCircle(lista.darElemento(i)[0]);
            }
            generateSimplePath(lista.darElemento(i));
            if (!vertexMap.containsKey(lat2 + "$" + long2)) {
                vertexMap.putNode(lat2 + "$" + long2, 1);
                generateCircle(lista.darElemento(i)[1]);

            }
        }
    }

    // DRaw zones graph
    public void Mapa(String zones, DinamicArray<LatLng[]> lista) {
        JFrame frame = new JFrame("Washington-Data: ");

        // hash tables to keep track of what has already added to the graph
        tablaDeHashLinearProbing<String, Integer> vertexMap = new tablaDeHashLinearProbing<>();
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                // Check if the map is loaded correctly
                if (status == MapStatus.MAP_STATUS_OK) {
                    // Getting the associated map object
                    mapa = getMap();
                    MapOptions mapOptions = new MapOptions();
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                    controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
                    mapOptions.setMapTypeControlOptions(controlOptions);

                    mapa.setOptions(mapOptions);
                    mapa.setCenter(new LatLng(4.6328903475179715, -74.09488677978516));
                    mapa.setZoom(14);

                }
            }
        });

        try {
            for (int i = 0; i < 8; i++) {
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        frame.add(this, BorderLayout.CENTER);
        frame.setSize(1500, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        circuloConf = new CircleOptions();
        circuloConf.setStrokeColor("#FF0000");
        circuloConf.setRadius(50);
        circuloConf.setFillColor("#FF0000");
        circuloConf.setFillOpacity(0.7);

        lineaConf = new PolylineOptions();
        lineaConf.setGeodesic(true);
        lineaConf.setStrokeColor("#4400cc");
        lineaConf.setStrokeOpacity(1.0);
        lineaConf.setStrokeWeight(2.0);

        // print path
        for (int i = 0; i < lista.size(); i++) {

            double lat1 = lista.darElemento(i)[0].getLat();
            double long1 = lista.darElemento(i)[0].getLng();

            double lat2 = lista.darElemento(i)[1].getLat();
            double long2 = lista.darElemento(i)[1].getLng();

            if (!vertexMap.containsKey(lat1 + "$" + long1)) {
                vertexMap.putNode(lat1 + "$" + long1, 0);
                generateCircle(lista.darElemento(i)[0]);
            }
            generateSimplePath(lista.darElemento(i));
            if (!vertexMap.containsKey(lat2 + "$" + long2)) {
                vertexMap.putNode(lat2 + "$" + long2, 1);
                generateCircle(lista.darElemento(i)[1]);

            }
        }
    }
}