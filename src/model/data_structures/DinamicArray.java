package model.data_structures;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
@SuppressWarnings("unchecked")
public class DinamicArray <T extends Comparable <T>>implements IDinamicArray<T> 
{
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T elementos[ ];

	  /**
     * Tamaño por defecto del arreglo
     */
    private static final int TAMAÑO_PROMEDIO = 5;
	/**
	 * Construir un arreglo con la capacidad dada por TAMAÑO_PROMEDIO.
	 */
	public DinamicArray()
	{
		elementos = (T[]) new Comparable[TAMAÑO_PROMEDIO];
		tamanoMax = TAMAÑO_PROMEDIO;
		tamanoAct = 0;
	}
	
	/**
	 * Construye un arreglo de acuerdo al tamaño maximo dado por parametro
	 * @param pMax Tamaño maximo inicial
	 */
	public DinamicArray(int pMax)
	{
        elementos = (T[]) new Comparable[pMax];
        tamanoMax = pMax;
        tamanoAct = pMax;
    }

	/**
	 * Retornar el numero de elementos presentes en el arreglo
	 * @return
	 */
	public int darTamano( ) 
	{
		return tamanoAct;
	}

	/**
	 * Retornar el elemento en la posicion i
	 * @param i posicion de consulta
	 * @return elemento de consulta. null si no hay elemento en posicion.
	 */
	public T darElemento( int i ) 
	{
		return elementos[i];
	}

	/**
	 * Agregar un vaje de forma compacta (en la primera casilla disponible) 
	 * Caso Especial: Si el arreglo esta lleno debe aumentarse su capacidad, agregar el nuevo dato y deben quedar multiples casillas disponibles para futuros nuevos datos.
	 * @param keys nuevo elemento
	 */
	public void agregar( T keys )
	{
		if (tamanoAct == tamanoMax) 
		{
			tamanoMax = (tamanoMax * 3) / 2 + 1; 
	        T[] copy = elementos;
	        elementos = (T[]) new Comparable[tamanoMax];
	        System.arraycopy(copy, 0, elementos, 0, tamanoAct);
        }
        elementos[tamanoAct] = keys;
        tamanoAct++;
    }

	/**
	 * Eliminar un viaje del arreglo.
	 * Los datos restantes deben quedar "compactos" desde la posicion 0.
	 * @param i posicion del dato a eliminar
	 */
	public void eliminar( int i) 
	{
		if (i < 0 || i > tamanoAct)
            throw new IndexOutOfBoundsException(i + " se encuentra fuera de los limites");

        if (i == tamanoAct)
            elementos[i] = null;
        else {

            for (int j = i; j < tamanoAct - 1; j++) {
                elementos[j] = elementos[j + 1];
            }

            elementos[tamanoAct - 1] = null;
        }
        tamanoAct--;
	}

	/**
	 * Cambia viaje en la respectiva posicion ingresada por el usuario por otro viaje
	 * @param i posicion del viaje del arreglo que sera cambiado
	 * @param  dato que reemplazara al anterior
	 */
	public void set(int i, T dato)
	{
		if (i < 0 || i >= tamanoMax)
            throw new ArrayIndexOutOfBoundsException();
        elementos[i] = dato;
	}

	/**
	 * Consulta si el arreglo esta vacio
	 * @return true o false dependiendo si el arreglo esta vacio
	 */
	public boolean isEmpty()
	{
		return tamanoAct==0;
	}

	public void limpiarTodo() 
	{
		if (isEmpty())
            return;

        for (int i = 0; i < tamanoAct; i++) {
            elementos[i] = null;
        }
        tamanoAct = 0;
	}

	public DinamicArrayIterator<T> iterator() 
	{
		return new DinamicArrayIterator<>(this);
	}

}
