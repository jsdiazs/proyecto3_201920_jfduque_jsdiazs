
package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DinamicArrayIterator<T extends Comparable<T>> implements Iterator {
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------

	private DinamicArray<T> array;

	/**
	 * Apuntador para el item actual.
	 */
	private int ApuntadorActual;

	/**
	 * Apuntador del item previo.
	 */
	private int ApuntadorPrevio;

	 public DinamicArrayIterator(DinamicArray<T> pArray) 
	{
		array = pArray;
		ApuntadorActual = 0;
		ApuntadorPrevio = ApuntadorActual;
	}

	/**
	 * Verifica si el arreglo contiene siguiente.
	 * @return retorna true si el arreglo contiene siguiente Item y false en caso contrario.
	 */
	@Override
	public boolean hasNext() {
		return ApuntadorActual < array.darTamano();
	}

	/**
	 * Retorna el siguiente del arreglo.
	 * @return retorna el item siguiente.
	 */
	@Override
	public T next() 
	{
		if (!hasNext())
			throw new NoSuchElementException("Elemento no existe ");
		T valor = array.darElemento(ApuntadorActual);
		ApuntadorPrevio = ApuntadorActual;
		ApuntadorActual++;
		return valor;
	}
	
	/**
	 * Verifica si el arreglo tiene un item previo.
	 * @return retorna True si el arreglo tiene un item previo y false en caso contrario.
	 */
	public boolean hasPrevious() 
	{
		return ApuntadorActual != 0;
	}


	/**
	 * Retorna el item previo in el arreglo sin cambiar los apuntadores.
	 * @return Retorna el item previo.
	 */
	public T getPrevious() 
	{
		T previo;
		if (!hasPrevious())
			previo = null;
		else
			previo = array.darElemento(ApuntadorPrevio);
		return previo;
	}
}
