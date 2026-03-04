public class Nodo<T> {

    public  T dato;
    Nodo<T> izquierdo;
    Nodo<T> derecho;

    public Nodo(T dato) {
        this.dato = dato;
    }

    public T obtenerDato() {
        return dato;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "dato=" + dato +
                ", izquierdo=" + (izquierdo != null ? izquierdo.dato : "null") +
                ", derecho=" + (derecho != null ? derecho.dato : "null") +
                '}';
    }
}


