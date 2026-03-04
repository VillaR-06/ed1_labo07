import java.util.Comparator;
import java.util.Stack;

public class ArbolBinarioBusqueda<T> {

    Nodo<T> raiz;
    private Comparator<T> comparador;

    public ArbolBinarioBusqueda(Comparator<T> comparador) {
        this.comparador = comparador;
    }


    public Nodo<T> buscar(T valor) {
        return buscar(this.raiz, valor);
    }

    private Nodo<T> buscar(Nodo<T> nodo, T valor) {
        if (nodo == null)
            return null;

        int comp = comparador.compare(valor, nodo.dato);

        if (comp == 0)
            return nodo;

        if (comp < 0)
            return buscar(nodo.izquierdo, valor);

        return buscar(nodo.derecho, valor);
    }


    public void insertar(T valor) {
        raiz = insertar(raiz, valor);
    }

    private Nodo<T> insertar(Nodo<T> nodo, T valor) {

        if (nodo == null)
            return new Nodo<>(valor);

        int comp = comparador.compare(valor, nodo.dato);

        if (comp == 0)
            return nodo; // no permite duplicados

        if (comp < 0)
            nodo.izquierdo = insertar(nodo.izquierdo, valor);
        else
            nodo.derecho = insertar(nodo.derecho, valor);

        return nodo;
    }



    public Nodo<T> eliminar(T valor) {

        Nodo<T> nodoAEliminar = buscar(valor);

        if (nodoAEliminar == null)
            return null;

        raiz = eliminar(raiz, valor);

        return nodoAEliminar;
    }

    private Nodo<T> eliminar(Nodo<T> raiz, T valor) {

        if (raiz == null)
            return null;

        int comp = comparador.compare(valor, raiz.dato);

        if (comp < 0) {
            raiz.izquierdo = eliminar(raiz.izquierdo, valor);
        }
        else if (comp > 0) {
            raiz.derecho = eliminar(raiz.derecho, valor);
        }
        else {

            // Caso 1: sin hijos
            if (raiz.izquierdo == null && raiz.derecho == null)
                return null;

            // Caso 2: un hijo
            if (raiz.izquierdo == null)
                return raiz.derecho;

            if (raiz.derecho == null)
                return raiz.izquierdo;

            // Caso 3: dos hijos
            Nodo<T> sucesor = min(raiz.derecho);
            raiz.dato = sucesor.dato;
            raiz.derecho = eliminar(raiz.derecho, sucesor.dato);
        }

        return raiz;
    }

    private Nodo<T> min(Nodo<T> nodo) {
        while (nodo.izquierdo != null)
            nodo = nodo.izquierdo;
        return nodo;
    }



    public void inOrden(Nodo<T> nodo) {
        Stack<Nodo<T>> stack = new Stack<>();
        Nodo<T> actual = nodo;

        while (actual != null || !stack.isEmpty()) {
            while (actual != null) {
                stack.push(actual);
                actual = actual.izquierdo;
            }

            actual = stack.pop();
            System.out.print(actual.dato + " ");
            actual = actual.derecho;
        }
    }

    public void preOrden(Nodo<T> nodo) {
        if (nodo == null) return;

        Stack<Nodo<T>> stack = new Stack<>();
        stack.push(nodo);

        while (!stack.isEmpty()) {
            Nodo<T> actual = stack.pop();
            System.out.print(actual.dato + " ");

            if (actual.derecho != null)
                stack.push(actual.derecho);
            if (actual.izquierdo != null)
                stack.push(actual.izquierdo);
        }
    }

    public void postOrden(Nodo<T> nodo) {
        if (nodo == null) return;

        Stack<Nodo<T>> stack1 = new Stack<>();
        Stack<Nodo<T>> stack2 = new Stack<>();

        stack1.push(nodo);

        while (!stack1.isEmpty()) {
            Nodo<T> actual = stack1.pop();
            stack2.push(actual);

            if (actual.izquierdo != null)
                stack1.push(actual.izquierdo);
            if (actual.derecho != null)
                stack1.push(actual.derecho);
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().dato + " ");
        }
    }
}
