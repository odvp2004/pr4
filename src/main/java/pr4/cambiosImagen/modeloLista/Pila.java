package pr4.cambiosImagen.modeloLista;

public class Pila<E> extends Lista<E>{
    public void push(E valor){
        insertar(valor);
    }

    public E pop(){
        E ultimoValor = obtener(0);
        eliminar(0);
        return ultimoValor;
    }

    public boolean isEmpty(){
        return tam==0;
    }
}
