public class Main {

    public static void main(String[] args) {

        ArbolBinarioBusqueda<Integer> arbol =
                new ArbolBinarioBusqueda<>(Integer::compare);

        Integer[] valores = {
                10, 8, 5, 4, 20, 50, 4000, 3, 15, 7,
                25, 30, 60, 1, 2, 9, 11, 13, 17, 100
        };


        for (Integer valor : valores) {
            arbol.insertar(valor);
        }

        System.out.println("InOrden inicial:");
        arbol.inOrden(arbol.raiz);


        for (int i = 0; i < valores.length / 2; i++) {
            Nodo<Integer> eliminado = arbol.eliminar(valores[i]);

            if (eliminado != null)
                System.out.println("\nEliminado: " + eliminado.dato);
            else
                System.out.println("\nNo se encontró: " + valores[i]);
        }

        System.out.println("\n\nInOrden después de eliminar 10 valores:");
        arbol.inOrden(arbol.raiz);
    }
    /*

PARTE 3 – ANÁLISIS DE COMPLEJIDAD


1) Complejidad temporal

buscar(valor):
- Mejor caso: O(1)
  Cuando el valor está en la raíz.
- Caso promedio: O(log n)
  Cuando el árbol está balanceado.
- Peor caso: O(n)
  Cuando el árbol está degenerado (forma de lista enlazada).

eliminar(valor):
- Mejor caso: O(1)
  Cuando el nodo a eliminar es la raíz y no tiene hijos.
- Caso promedio: O(log n)
  Cuando el árbol está balanceado.
- Peor caso: O(n)
  Cuando el árbol está degenerado.

La complejidad depende directamente de la altura del árbol.



2) ¿Qué sucede si los datos se insertan en orden ascendente?


Si los datos se insertan en orden ascendente, el árbol
se convierte en una estructura lineal hacia la derecha,
similar a una lista enlazada:

1
 \
  2
   \
    3
     \
      4

En este caso:
- La altura del árbol se vuelve n.
- La búsqueda pasa de O(log n) a O(n).
- Eliminar también pasa a O(n).

Posible mejora:
Utilizar un árbol auto-balanceado como:
- AVL
- Red-Black Tree

Estos árboles mantienen la altura en O(log n)
y garantizan búsquedas y eliminaciones eficientes.



3) Permitir duplicados


Actualmente el código no permite duplicados porque
si el valor ya existe, simplemente no se inserta.

Para permitir duplicados se podrían hacer dos cambios:

OPCIÓN 1 (más recomendada):
Agregar un atributo "frecuencia" en la clase Nodo:

int frecuencia;

Cuando se inserta un valor repetido:
- En lugar de ignorarlo, se incrementa frecuencia.

El método eliminar cambiaría así:
- Si frecuencia > 1, solo se decrementa frecuencia.
- Si frecuencia == 1, se elimina el nodo físicamente.

OPCIÓN 2:
Permitir que los duplicados siempre se inserten
por ejemplo, en el subárbol derecho.

En ese caso:
- El árbol podría crecer más hacia un lado.
- El método eliminar tendría que decidir si elimina
  solo una ocurrencia o todas las ocurrencias.

La opción con frecuencia es más eficiente y mantiene
mejor la estructura del árbol.
*/



}

