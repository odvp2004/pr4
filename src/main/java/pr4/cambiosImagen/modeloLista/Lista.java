package pr4.cambiosImagen.modeloLista;

public class Lista<E>{
    protected Nodo<E> raiz;
    protected int tam = 0;

    public void insertar(E valor) {

        Nodo<E> nuevo = new Nodo<>(valor);
        nuevo.setSiguiente(raiz);
        raiz = nuevo;
        tam++;
    }

    public void adicionar(E valor) {
        if (raiz == null) {
            raiz = new Nodo<>(valor);
            tam++;
            return;
        }
        Nodo<E> actual = raiz;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        Nodo<E> nuevo = new Nodo<>(valor);
        actual.setSiguiente(nuevo);
        tam++;
    }

    public Nodo<E> getNodo(int posicion) {
        if (posicion >= tam) {
            throw new IndexOutOfBoundsException(
                    "La posición " + posicion + " está fuera del tamaño de la lista (" + tam + ")"
            );
        }
        int count = 0;
        Nodo<E> actual = raiz;
        while (actual.getSiguiente() != null && count != posicion) {
            count++;
            actual = actual.getSiguiente();
        }
        return actual;
    }

    public E obtener(int posicion) {
        if(posicion>= tam){
            return null;
        }
        return getNodo(posicion).getValor();
    }

    public void eliminar(int posicion) {
        if (posicion >= tam) {
            throw new IndexOutOfBoundsException(
                    "La posición " + posicion + " está fuera del tamaño de la lista (" + tam + ")"
            );
        }
        if (posicion == 0) {
            raiz = raiz.getSiguiente();
            tam--;
            return;
        }
        int count = 0;
        Nodo<E> actual = raiz;
        Nodo<E> anterior = null;
        Nodo<E> siguiente = raiz.getSiguiente();
        while (actual.getSiguiente() != null && count != posicion) {
            count++;
            anterior = actual;
            actual = actual.getSiguiente();
            siguiente = actual.getSiguiente();
        }
        anterior.setSiguiente(siguiente);
        tam--;
    }

    @Override
    public String toString() {
        Nodo<E> actual = raiz;
        if (actual == null) {
            return "Null";
        }
        StringBuilder builder = new StringBuilder();
        while (actual.getSiguiente() != null) {
            builder.append(actual);
            actual = actual.getSiguiente();
        }
        builder.append(actual);

        return builder.toString();
    }

    public Nodo<E> getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo<E> raiz) {
        this.raiz = raiz;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }
}
