package pr4.cambiosImagen.modeloLista;

public class Nodo<E> {
    private E valor;
    private Nodo<E> siguiente;

    public Nodo(E valor) {
        this.valor = valor;
    }

    public E getValor() {
        return valor;
    }

    public void setValor(E valor) {
        this.valor = valor;
    }

    public Nodo<E> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<E> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return valor + " -> ";
    }
}
