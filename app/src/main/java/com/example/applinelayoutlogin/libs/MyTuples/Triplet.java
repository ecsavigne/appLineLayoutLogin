package com.example.applinelayoutlogin.libs.MyTuples;

public class Triplet<F, S, T> {
    public final F first;       // el primer campo de un triplete
    public final S second;      // el segundo campo de un triplete
    public final T third;       // el tercer campo de un triplete

    public Triplet(F first, S second, T third)
    {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    /**
     * Compara 2 Triplet
     * @param o Triplet a comparar
     * @return True si son iguales los objetos triplets sino return falso
     */
    @Override
    public boolean equals(Object o)
    {
        /* Comprueba que el objeto especificado es "igual" al objeto actual o no */
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Triplet triplet = (Triplet) o;
        // llamar al método `equals()` de los objetos subyacentes
        if (!first.equals(triplet.first) ||
                !second.equals(triplet.second) ||
                !third.equals(triplet.third)) {
            return false;
        }
        return true;
    }

    /**
     * Calcula el Codigo Has de un Triplets
     * @return
     */
    @Override
    public int hashCode()
    {
        /* Calcula el código hash de un objeto mediante el uso de códigos hash de
        los objetos subyacentes */

        int result = first.hashCode();
        result = 31 * result + second.hashCode();
        result = 31 * result + third.hashCode();
        return result;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public T getThird() {
        return third;
    }

    /**
     * Devuelve el índice de la subcadena solicitada en una cadena determinada.
     * @param c cadena a buscar
     * @return -1 si no pertenece, de lo contrario retorna el indice de su posicion
     */

    public int indexOf(String c ) {
     if(c == first ) return 0;
     if(c == second ) return 1;
     if(c == third ) return 2;
     return -1;
    }

    /**
     * Convierte el triplet a String (F, S, T)
     * @return Devuelve un String representativo de Triplets
     */
    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }

    // Método de fábrica para crear una instancia inmutable tipificada de triplete
    public static <F, S, T> Triplet <F, S, T> of(F a, S b, T c) {
        return new Triplet <>(a, b, c);
    }
}
