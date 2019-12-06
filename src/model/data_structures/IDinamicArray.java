
package model.data_structures;
public interface IDinamicArray<T extends Comparable<T>> extends Iterable 

{
	/**
	 * Tama�o del arreglo
	 * @return tama�o del arreglo
	 */
    int darTamano();
   
    /**
     * Cambiar un item con relacion al index
     * @param pIndex Posicion del arreglo
     * @param pValue Item que sera contenido en la posicion.
     */
    void set(int pIndex, T pValue);
    
    /**
     * Retorna el item en la respectiva posici�n del arreglo
     * @param pIndex Posicion del arreglo
     * @return elemento en el arreglo
     */
    
    T darElemento(int pIndex);

    /**
     * Agrega un item al finanl del arreglo.
     * @param pItem item que sera agregado al arreglo.
     */
    void agregar(T pItem);

    /**
     * Elimina todos los items del array.
     */
    void limpiarTodo();

    /**
     * Elimina un Item segun el Index dado por parametro.
     * @param pIndex Index in the array.
     */
    void eliminar(int pIndex);

    /**
     * Verifica si el arreglo esta vacio.
     * @return True si esta vacio, false en caso contrario.
     */
    boolean isEmpty();


    /**
     * Iterador usado para navegar atra vez del arreglo.
     * @return retorna el iterador.
     */

}
