package umg.edu.progra.arboles;

/**
 * Clase principal que demuestra el uso del Arbol Binario de Busqueda (BST)
 * implementado manualmente, sin usar librerias como java.util.
 *
 * Ejecucion sugerida:
 *   1. mvn compile
 *   2. mvn exec:java -Dexec.mainClass="umg.edu.progra.arboles.Principal"
 *
 * @author Walter Cordova
 */
public class Principal {

    public static void main(String[] args) {

        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        /*
         * Insertamos estos valores para formar el siguiente BST:
         *
         *               50
         *              /  \
         *            30    70
         *           /  \   / \
         *          20  40 60  80
         *         /
         *        10
         */
        int[] valores = { 50, 30, 70, 20, 40, 60, 80, 10 };
        for (int v : valores) {
            arbol.insertar(v);
        }

        System.out.println("===== Arbol Binario de Busqueda =====");
        System.out.println("Tamanio: " + arbol.tamanio());
        System.out.println("Altura:  " + arbol.altura());
        System.out.println("Minimo:  " + arbol.minimo());
        System.out.println("Maximo:  " + arbol.maximo());
        System.out.println("Hojas:   " + arbol.contarHojas());
        
        System.out.println("contar NODOS(): " + arbol.contarNoddos());


        System.out.println("\n--- Representacion visual (rotada 90 grados) ---");
        arbol.imprimirArbol();

        System.out.println("\n--- Recorridos ---");
        System.out.print("InOrden    (ascendente): ");
        arbol.inOrden();

        System.out.print("PreOrden   (raiz primero): ");
        arbol.preOrden();

        System.out.print("PostOrden  (raiz al final): ");
        arbol.postOrden();

        System.out.print("Por niveles (BFS):         ");
        arbol.recorridoPorNiveles();

        System.out.println("\n--- Busquedas ---");
        System.out.println("Contiene 40? " + arbol.contiene(40));
        System.out.println("Contiene 99? " + arbol.contiene(99));

        System.out.println("\n--- Eliminacion ---");
        System.out.println("Eliminando 20 (nodo con 1 hijo)...");
        arbol.eliminar(20);
        System.out.print("InOrden tras eliminar 20: ");
        arbol.inOrden();

        System.out.println("Eliminando 30 (nodo con 2 hijos)...");
        arbol.eliminar(30);
        System.out.print("InOrden tras eliminar 30: ");
        arbol.inOrden();

        System.out.println("Eliminando 50 (raiz)...");
        arbol.eliminar(50);
        System.out.print("InOrden tras eliminar la raiz: ");
        arbol.inOrden();

        System.out.println("\n--- Estado final ---");
        arbol.imprimirArbol();
        System.out.println("Tamanio final: " + arbol.tamanio());
        System.out.println("Altura final:  " + arbol.altura());
        
        
       //´print problema 1
        System.out.println("contar NODOS(): " + arbol.contarNoddos());
        
        
        //print probleba 2
        System.out.println("\n-- Prueba esBalanceado --");
        System.out.println("Arbol actual balanceado: " + arbol.esBalanceado()); // true

        // Arbol desbalanceado: insertar 1,2,3,4,5 en orden
        ArbolBinarioBusqueda desbalanceado = new ArbolBinarioBusqueda();
        desbalanceado.insertar(1);
        desbalanceado.insertar(2);
        desbalanceado.insertar(3);
        desbalanceado.insertar(4);
        desbalanceado.insertar(5);
        System.out.println("Arbol 1-2-3-4-5 balanceado: " + desbalanceado.esBalanceado()); // false
        desbalanceado.imprimirArbol(); // verás que queda como una línea recta
        

        //problema 3 
        System.out.println("\n-- Prueba esBSTValido --");
        System.out.println("Arbol normal es BST valido: " + arbol.esBSTValido()); // true

        // Romper el arbol manualmente
        ArbolBinarioBusqueda roto = new ArbolBinarioBusqueda();
        roto.insertar(50);
        roto.insertar(30);
        roto.insertar(70);
        //  el nodo roto 
        
        roto.getRaiz().izquierdo.derecho = new Nodo(80); 
        System.out.println("Arbol roto es BST valido: " + roto.esBSTValido()); // false

        
      //sysos de problema NO 4
        ArbolBinarioBusqueda arbolLCA = new ArbolBinarioBusqueda();
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80, 10}) {
            arbolLCA.insertar(v);
        }
        System.out.println("\n-- Prueba ancestroComunMasBajo (LCA) --");
        System.out.println("LCA(10, 40) = " + arbolLCA.ancestroComunMasBajo(10, 40)); // 30
        System.out.println("LCA(10, 80) = " + arbolLCA.ancestroComunMasBajo(10, 80)); // 50
        System.out.println("LCA(60, 80) = " + arbolLCA.ancestroComunMasBajo(60, 80)); // 70

        try {
            arbolLCA.ancestroComunMasBajo(10, 99);
        } catch (IllegalArgumentException e) {
            System.out.println("Excepcion correcta: " + e.getMessage());
        }
        
        //sysos del problma 5
        
        System.out.println("\n-- Prueba invertir (espejo) --");
        ArbolBinarioBusqueda arbolEspejo = new ArbolBinarioBusqueda();
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80, 10}) {
            arbolEspejo.insertar(v);
        }

        System.out.println("ANTES de invertir:");
        arbolEspejo.imprimirArbol();
        System.out.print("InOrden antes: ");
        arbolEspejo.inOrden();

        arbolEspejo.invertir();

        System.out.println("\nDESPUES de invertir:");
        arbolEspejo.imprimirArbol();
        System.out.print("InOrden despues: ");
        arbolEspejo.inOrden();
        
        //sysos de problemas extras E1 probelam 6
        System.out.println("\n-- Extra E1: kEsimoMenor --");
        ArbolBinarioBusqueda arbolK = new ArbolBinarioBusqueda();
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80, 10}) {
            arbolK.insertar(v);
        }
        System.out.println("1er menor: " + arbolK.kEsimoMenor(1)); // 10
        System.out.println("3er menor: " + arbolK.kEsimoMenor(3)); // 30
        System.out.println("5to menor: " + arbolK.kEsimoMenor(5)); // 50
        
        System.out.println("\n-- Extra E2: imprimirRangoOrdenado --");
        ArbolBinarioBusqueda arbolR = new ArbolBinarioBusqueda();
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80, 10}) {
            arbolR.insertar(v);
        }
        System.out.print("Rango [20, 60]: ");
        arbolR.imprimirRangoOrdenado(20, 60); // 20 30 40 50 60
        System.out.print("Rango [40, 80]: ");
        arbolR.imprimirRangoOrdenado(40, 80); // 40 50 60 70 80
        /*
         * Ejercicios
         *
         *  1. Implementar un metodo que devuelva la cantidad TOTAL de nodos
         *     usando recursividad (sin usar el campo 'tamanio').
         *     
         *  2. Implementar un metodo 'esBalanceado()' que indique si el arbol
         *     esta balanceado (diferencia de alturas <= 1 en cada nodo).
         *  3. Implementar 'esBSTValido()' que verifique que el arbol cumple
         *     la propiedad de BST recorriendo los nodos.
         *  4. Implementar un metodo para encontrar el ancestro comun mas
         *     bajo (LCA) entre dos valores.
         *  5. Implementar la inversion del arbol (espejo).
         */
    }
}
