package umg.edu.progra.arboles;

/**
 * Arbol Binario de Busqueda (BST) implementado manualmente,
 * sin utilizar java.util ni librerias externas.
 *
 * Reglas del BST:
 *  - Para cada nodo N, todos los valores del subarbol izquierdo
 *    son MENORES que N.dato.
 *  - Para cada nodo N, todos los valores del subarbol derecho
 *    son MAYORES que N.dato.
 *  - No se permiten duplicados (se ignoran al insertar).
 *
 * @author Walter Cordova
 */
public class ArbolBinarioBusqueda {

    private Nodo raiz;
    private int tamanio;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
        this.tamanio = 0;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int tamanio() {
        return tamanio;
    }
    
    //contar el total de nodos del arbol
    public int contarNoddos() {
    	return contarNodosRecursivos(raiz);
    }
    private int contarNodosRecursivos(Nodo nodo) {
    	if(nodo ==null) {
    		return 0  ;
    	}
    	return 1 + contarNodosRecursivos(nodo.izquierdo) + contarNodosRecursivos(nodo.derecho);
    
    }
    
    //metodo para verificar si el arbol esta balanceado problema 2 
    
  public boolean esBalanceado () {
    	return alturaBalanceada(raiz) != -1;
    }
    
    private  int alturaBalanceada(Nodo nodo) {
    	if (nodo == null) {
    		return 0 ;
    	}
    	int left = alturaBalanceada(nodo.izquierdo);
    	if(left ==-1) return -1;
    	int right= alturaBalanceada(nodo.derecho);
    	if(right ==-1) return -1;
    	
    	if (right -left >1 || left -right > 1 ) return -1;
    	return 1+ (left > right ? left :right);
    	
    	
    }
    
    
    
    //problema 3 ver si el arbol cumblple la propiedad BSt
    
 
    public boolean esBSTValido() {
        return esBSTRecursivo(raiz, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean esBSTRecursivo(Nodo nodo, int min, int max) {
        if (nodo == null) {
            return true; 
        }
        if (nodo.dato <= min || nodo.dato >= max) {
            return false; 
        }
        return esBSTRecursivo(nodo.izquierdo, min, nodo.dato)
            && esBSTRecursivo(nodo.derecho, nodo.dato, max);
    }

    
    // problema NO 4 de ancestros
    public int ancestroComunMasBajo(int a, int b) {
        if (!contiene(a) || !contiene(b)) {
            throw new IllegalArgumentException("Uno o ambos valores no existen en el arbol");
        }
        return lcaRecursivo(raiz, a, b);
    }

    private int lcaRecursivo(Nodo nodo, int a, int b) {
        if (a < nodo.dato && b < nodo.dato) {
            return lcaRecursivo(nodo.izquierdo, a, b); // ambos a la izquierda
        }
        if (a > nodo.dato && b > nodo.dato) {
            return lcaRecursivo(nodo.derecho, a, b);   // ambos a la derecha
        }
        return nodo.dato; // se separan aqui, este es el LCA
    }
    
    
    //metodo problema 5
    
    public void invertir() {
        invertirRecursivo(raiz);
    }

    private void invertirRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        // Intercambiar hijos
        Nodo temp = nodo.izquierdo;
        nodo.izquierdo = nodo.derecho;
        nodo.derecho = temp;

        // Repetir en ambos subarboles
        invertirRecursivo(nodo.izquierdo);
        invertirRecursivo(nodo.derecho);
    }

    //METODOS SOBRE PROBLEMAS EXTRAS PROBLEMAS 6
    
    public int kEsimoMenor(int k) {
        if (k < 1 || k > tamanio) {
            throw new IllegalArgumentException("k fuera de rango");
        }
        int[] contador = {0};      // contador compartido entre llamadas recursivas
        int[] resultado = {-1};    // aqui guardamos el resultado cuando lo encontremos
        kEsimoRecursivo(raiz, k, contador, resultado);
        return resultado[0];
    }

    private void kEsimoRecursivo(Nodo nodo, int k, int[] contador, int[] resultado) {
        if (nodo == null || contador[0] >= k) {
            return;
        }
        kEsimoRecursivo(nodo.izquierdo, k, contador, resultado);
        contador[0]++;
        if (contador[0] == k) {
            resultado[0] = nodo.dato;
            return;
        }
        kEsimoRecursivo(nodo.derecho, k, contador, resultado);
    }
    
    public void imprimirRangoOrdenado(int min, int max) {
        rangoRecursivo(raiz, min, max);
        System.out.println();
    }

    private void rangoRecursivo(Nodo nodo, int min, int max) {
        if (nodo == null) {
            return;
        }
        if (nodo.dato > min) {
            rangoRecursivo(nodo.izquierdo, min, max); // puede haber valores validos a la izquierda
        }
        if (nodo.dato >= min && nodo.dato <= max) {
            System.out.print(nodo.dato + " "); // este nodo esta en el rango
        }
        if (nodo.dato < max) {
            rangoRecursivo(nodo.derecho, min, max); // puede haber valores validos a la derecha
        }
    }
    /**
     * Inserta un valor en el arbol respetando la propiedad del BST.
     * Si el valor ya existe se ignora (no se insertan duplicados).
     */
    public void insertar(int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            tamanio++;
            return;
        }
        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            tamanio++;
            return new Nodo(valor);
        }
        if (valor < actual.dato) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.dato) {
            actual.derecho = insertarRecursivo(actual.derecho, valor);
        }
        return actual;
    }

    /**
     * Busca un valor dentro del arbol. Devuelve el Nodo si existe
     * o null si no se encuentra.
     */
    public Nodo buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private Nodo buscarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor == actual.dato) {
            return actual;
        }
        if (valor < actual.dato) {
            return buscarRecursivo(actual.izquierdo, valor);
        }
        return buscarRecursivo(actual.derecho, valor);
    }

    public boolean contiene(int valor) {
        return buscar(valor) != null;
    }

    /**
     * Elimina un valor del arbol. Cubre los 3 casos clasicos:
     *  1. Nodo hoja (sin hijos)
     *  2. Nodo con un solo hijo
     *  3. Nodo con dos hijos (se reemplaza por el sucesor inorden:
     *     el menor del subarbol derecho).
     */
    public boolean eliminar(int valor) {
        int tamanioPrevio = tamanio;
        raiz = eliminarRecursivo(raiz, valor);
        return tamanio < tamanioPrevio;
    }

    private Nodo eliminarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor < actual.dato) {
            actual.izquierdo = eliminarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.dato) {
            actual.derecho = eliminarRecursivo(actual.derecho, valor);
        } else {
            // Nodo encontrado
            if (actual.izquierdo == null && actual.derecho == null) {
                tamanio--;
                return null;
            }
            if (actual.izquierdo == null) {
                tamanio--;
                return actual.derecho;
            }
            if (actual.derecho == null) {
                tamanio--;
                return actual.izquierdo;
            }
            // Nodo con dos hijos: se reemplaza con el sucesor inorden
            int sucesor = minimo(actual.derecho);
            actual.dato = sucesor;
            actual.derecho = eliminarRecursivo(actual.derecho, sucesor);
        }
        return actual;
    }

    /**
     * Devuelve el valor minimo del arbol (el nodo mas a la izquierda).
     */
    public int minimo() {
        if (raiz == null) {
            throw new IllegalStateException("El arbol esta vacio");
        }
        return minimo(raiz);
    }

    private int minimo(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual.dato;
    }

    /**
     * Devuelve el valor maximo del arbol (el nodo mas a la derecha).
     */
    public int maximo() {
        if (raiz == null) {
            throw new IllegalStateException("El arbol esta vacio");
        }
        Nodo actual = raiz;
        while (actual.derecho != null) {
            actual = actual.derecho;
        }
        return actual.dato;
    }

    /**
     * Altura del arbol: cantidad de aristas del camino mas largo
     * desde la raiz hasta una hoja. Un arbol vacio tiene altura -1.
     * Un arbol con solo raiz tiene altura 0.
     */
    public int altura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        int izq = alturaRecursiva(nodo.izquierdo);
        int der = alturaRecursiva(nodo.derecho);
        return 1 + (izq > der ? izq : der);
    }

    /**
     * Cuenta cuantos nodos hoja (sin hijos) tiene el arbol.
     */
    public int contarHojas() {
        return contarHojasRecursivo(raiz);
    }

    private int contarHojasRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.izquierdo == null && nodo.derecho == null) {
            return 1;
        }
        return contarHojasRecursivo(nodo.izquierdo) + contarHojasRecursivo(nodo.derecho);
    }

    // ============================================================
    // RECORRIDOS DEL ARBOL
    // ============================================================

    /**
     * Recorrido InOrden: Izquierdo -> Raiz -> Derecho.
     * En un BST imprime los valores ordenados de menor a mayor.
     */
    public void inOrden() {
        inOrdenRecursivo(raiz);
        System.out.println();
    }

    private void inOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        inOrdenRecursivo(nodo.izquierdo);
        System.out.print(nodo.dato + " ");
        inOrdenRecursivo(nodo.derecho);
    }

    /**
     * Recorrido PreOrden: Raiz -> Izquierdo -> Derecho.
     * Util para clonar el arbol.
     */
    public void preOrden() {
        preOrdenRecursivo(raiz);
        System.out.println();
    }

    private void preOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        System.out.print(nodo.dato + " ");
        preOrdenRecursivo(nodo.izquierdo);
        preOrdenRecursivo(nodo.derecho);
    }

    /**
     * Recorrido PostOrden: Izquierdo -> Derecho -> Raiz.
     * Util para liberar/eliminar el arbol.
     */
    public void postOrden() {
        postOrdenRecursivo(raiz);
        System.out.println();
    }

    private void postOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        postOrdenRecursivo(nodo.izquierdo);
        postOrdenRecursivo(nodo.derecho);
        System.out.print(nodo.dato + " ");
    }

    /**
     * Recorrido por niveles (BFS) implementado con una cola casera
     * (sin usar java.util). Imprime el arbol por anchura.
     */
    public void recorridoPorNiveles() {
        if (raiz == null) {
            System.out.println();
            return;
        }
        ColaNodos cola = new ColaNodos();
        cola.encolar(raiz);
        while (!cola.estaVacia()) {
            Nodo actual = cola.desencolar();
            System.out.print(actual.dato + " ");
            if (actual.izquierdo != null) {
                cola.encolar(actual.izquierdo);
            }
            if (actual.derecho != null) {
                cola.encolar(actual.derecho);
            }
        }
        System.out.println();
    }

    /**
     * Imprime el arbol de forma jerarquica y visual en consola
     * (rotado 90 grados: la raiz queda a la izquierda).
     */
    public void imprimirArbol() {
        if (raiz == null) {
            System.out.println("(arbol vacio)");
            return;
        }
        imprimirArbolRecursivo(raiz, 0);
    }

    private void imprimirArbolRecursivo(Nodo nodo, int nivel) {
        if (nodo == null) {
            return;
        }
        imprimirArbolRecursivo(nodo.derecho, nivel + 1);
        for (int i = 0; i < nivel; i++) {
            System.out.print("     ");
        }
        System.out.println("-> " + nodo.dato);
        imprimirArbolRecursivo(nodo.izquierdo, nivel + 1);
    }

    // ============================================================
    // COLA INTERNA (lista enlazada simple) usada para BFS.
    // Se implementa aqui para NO depender de java.util.
    // ============================================================

    private static class NodoCola {
        Nodo valor;
        NodoCola siguiente;

        NodoCola(Nodo valor) {
            this.valor = valor;
        }
    }

    private static class ColaNodos {
        private NodoCola frente;
        private NodoCola fondo;

        boolean estaVacia() {
            return frente == null;
        }

        void encolar(Nodo n) {
            NodoCola nuevo = new NodoCola(n);
            if (frente == null) {
                frente = fondo = nuevo;
            } else {
                fondo.siguiente = nuevo;
                fondo = nuevo;
            }
        }

        Nodo desencolar() {
            if (frente == null) {
                throw new IllegalStateException("Cola vacia");
            }
            Nodo valor = frente.valor;
            frente = frente.siguiente;
            if (frente == null) {
                fondo = null;
            }
            return valor;
        }
    }
}
