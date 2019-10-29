package model.data_structures;
public class tablaDeHashLinearProbing<Key, Value> extends DinamicArray
{
    /*
     * Tamaño de la tabla en caso de no ser inicializada con un valor especifico.
     */
	private static final int CAPACIDAD_INICIAL = 44;

    /**
     * Numero de parejas Key-value
     */
    private int n;     
    /**
     * Tamaño de la tabla
     */
    private int m;         
    /**
     * Numero de Keys
     */
    private Key[] keys;
    /**
     * Numero de values
     */
    private Value[] vals;    

    public tablaDeHashLinearProbing()
    {
        this(CAPACIDAD_INICIAL);
    }

    public tablaDeHashLinearProbing(int capacidad) 
    {
        m = capacidad;
        n = 0;
        keys = (Key[])   new Object[m];
        vals = (Value[]) new Object[m];
    }

    public int size() 
    {
        return n;
    }
    
    public boolean isEmpty() 
    {
        return size() == 0;
    }

    public boolean contains(Key key) 
    {
        if (key == null) throw new IllegalArgumentException("su argumento es contenido por null");
        return get(key) != null;
    }

    private int hash(Key key) 
    {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int capacity) 
    {
        tablaDeHashLinearProbing<Key, Value> temp = new tablaDeHashLinearProbing<Key, Value>(capacity);
        for (int i = 0; i < m; i++) 
        {
            if (keys[i] != null) 
            {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m    = temp.m;
    }

    
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("El argumento a colocar es null");

        if (val == null) 
        {
            delete(key);
            return;
        }


        if (n >= m/2) resize(2*m);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) 
        {
            if (keys[i].equals(key)) 
            {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }


    public Value get(Key key) 
    {
        if (key == null) throw new IllegalArgumentException("argumento a dar es null");
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    public void delete(Key key) 
    {
        if (key == null) throw new IllegalArgumentException("Argumento a eliminar es null");
        if (!contains(key)) return;

        int i = hash(key);
        while (!key.equals(keys[i])) 
        {
            i = (i + 1) % m;
        }

        keys[i] = null;
        vals[i] = null;

        i = (i + 1) % m;
        while (keys[i] != null) 
        {
            Key   keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }

        n--;

        if (n > 0 && n <= m/8) resize(m/2);

        assert verificar();
    }


    public Iterable<Key> keys() {
        DinamicArray array = new DinamicArray();
        for (int i = 0; i < m; i++)
            if (keys[i] != null) array.agregar((Comparable) keys[i]);;
        return array;
    }

    private boolean verificar() 
    {

        if (m < 2*n) {
            System.err.println("Hash table size m = " + m + "; array size n = " + n);
            return false;
        }

   
        for (int i = 0; i < m; i++) {
            if (keys[i] == null) continue;
            else if (get(keys[i]) != vals[i]) {
                System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
                return false;
            }
        }
        return true;
    }
}

/******************************************************************************
 *  Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
