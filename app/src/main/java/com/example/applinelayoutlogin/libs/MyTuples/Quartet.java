package com.example.applinelayoutlogin.libs.MyTuples;

public class Quartet<F, S, T, C> {
    public final F first;       // el primer campo de un triplete
    public final S second;      // el segundo campo de un triplete
    public final T third;       // el tercer campo de un triplete
    public final C fourth;      // el Cuarto campo de un triplete

    public Quartet(F first, S second, T third, C Fourth)
    {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = Fourth;
    }

    /**
     * Compara 2 Quartet
     * @param o Quintet a comparar
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
        Quartet quartet = (Quartet) o;
        // llamar al método `equals()` de los objetos subyacentes
        if (!first.equals(quartet.first) ||
                !second.equals(quartet.second) ||
                !third.equals(quartet.third) ||
                !fourth.equals(quartet.fourth)) {
            return false;
        }
        return true;
    }

    /**
     * Calcula el Codigo Has de un Quartet
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
        result = 31 * result + fourth.hashCode();
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

    public C getFourth() { return fourth; }

    public int indexOf(String c ) {
        if(c == first ) return 0;
        if(c == second ) return 1;
        if(c == third ) return 2;
        if(c == fourth ) return 3;
        return -1;
    }

    /**
     * Convierte el Quartet a String (F, S, T, C, Q)
     * @return Devuelve un String representativo de Quartet
     */
    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ", " + fourth + ")";
    }

    // Método de fábrica para crear una instancia inmutable tipificada de triplete
    public static <F, S, T, C> Quartet <F, S, T, C> of(F a, S b, T c, C d) {
        return new Quartet <>(a, b, c, d);
    }
}
