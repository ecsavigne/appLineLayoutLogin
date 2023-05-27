package com.example.applinelayoutlogin.libs.MyTuples;

public class Quintet<F, S, T, C, Q> {
    public final F first;       // el primer campo de un triplete
    public final S second;      // el segundo campo de un triplete
    public final T third;       // el tercer campo de un triplete
    public final C fourth;      // el Cuarto campo de un triplete
    public final Q fifth;       // el Quinto campo de un triplete

    public Quintet(F first, S second, T third, C Fourth,Q Fifth)
    {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = Fourth;
        this.fifth = Fifth;
    }

    /**
     * Compara 2 Quintet
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
        Quintet quintet = (Quintet) o;
        // llamar al método `equals()` de los objetos subyacentes
        if (!first.equals(quintet.first) ||
                !second.equals(quintet.second) ||
                !third.equals(quintet.third) ||
                !fourth.equals(quintet.fourth) ||
                !fifth.equals(quintet.fifth)) {
            return false;
        }
        return true;
    }

    /**
     * Calcula el Codigo Has de un Quintets
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
        result = 31 * result + fifth.hashCode();
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

    public Q getFifth() { return fifth; }

    public int indexOf(String c ) {
        if(c == first ) return 0;
        if(c == second ) return 1;
        if(c == third ) return 2;
        if(c == fourth ) return 3;
        if(c == fifth ) return 4;
        return -1;
    }

    /**
     * Convierte el Quintets a String (F, S, T, C, Q)
     * @return Devuelve un String representativo de Quintets
     */
    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ", " + fourth + ", " + fifth + ")";
    }

    // Método de fábrica para crear una instancia inmutable tipificada de triplete
    public static <F, S, T, C, Q> Quintet <F, S, T, C, Q> of(F a, S b, T c, C d, Q e) {
        return new Quintet <>(a, b, c, d, e);
    }
}
