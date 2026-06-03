# Tarea: Árbol Binario de Búsqueda (BST) en Java

**Estudiante:** Josue David Paniagua Olivares  
**Curso:** Programación 3  
**Universidad:** UMG  
**Tema:** Estructuras de datos no lineales — Árboles  

---

## ¿Cómo compilar y ejecutar?

Desde la carpeta `arboles/`:

```bash
mvn compile
java -cp target/classes umg.edu.progra.arboles.Principal
```

Para el ejercicio extra E4 (BST desde consola):

```bash
java -cp target/classes umg.edu.progra.arboles.Principal 40 20 60 10 30 50 70
```

---

## Métodos implementados

### Problema 1 — `contarNodos()`
Cuenta el total de nodos del árbol de forma **recursiva**, sin usar el campo `tamanio` ya existente.  
Recorre cada nodo: si es `null` devuelve 0, si no devuelve `1 + izquierdo + derecho`.

**Ejemplo:**
```
Entrada: árbol con 50, 30, 70, 20, 40, 60, 80, 10
contarNodos() → 8
tamanio()     → 8  ✅ coinciden
```

---

### Problema 2 — `esBalanceado()`
Verifica si el árbol está balanceado: para cada nodo, la diferencia de altura entre su subárbol izquierdo y derecho es `<= 1`.  
Usa un helper que devuelve `-1` si detecta desequilibrio, o la altura real si todo está bien.

**Ejemplo:**
```
Árbol balanceado (50,30,70,20,40,60,80,10) → true
Árbol desbalanceado (1,2,3,4,5)            → false
```

---

### Problema 3 — `esBSTValido()`
Verifica que el árbol cumple la propiedad de BST en todos sus nodos.  
Usa un rango `(min, max)` que se va ajustando en cada llamada recursiva para detectar nodos fuera de lugar.

**Ejemplo:**
```
Árbol normal                          → true
Árbol con nodo 80 a la izquierda de 50 → false
```

---

### Problema 4 — `ancestroComunMasBajo(int a, int b)`
Devuelve el ancestro común más bajo (LCA) de dos valores.  
Aprovecha la propiedad del BST: si ambos valores son menores → ir a la izquierda; si ambos son mayores → ir a la derecha; si se separan → ese nodo es el LCA.  
Lanza `IllegalArgumentException` si alguno de los valores no existe.

**Ejemplo:**
```
LCA(10, 40) → 30
LCA(10, 80) → 50
LCA(60, 80) → 70
```

---

### Problema 5 — `invertir()`
Invierte el árbol como un espejo: intercambia `izquierdo` y `derecho` en todos los nodos recursivamente.  
El InOrden que antes era ascendente pasa a ser descendente.

**Ejemplo:**
```
InOrden antes:  10 20 30 40 50 60 70 80
InOrden después: 80 70 60 50 40 30 20 10
```

---

## Ejercicios Extra

### E1 — `kEsimoMenor(int k)`
Devuelve el k-ésimo valor más pequeño usando el recorrido InOrden.  
Cuenta los nodos visitados y para cuando llega al k-ésimo.

**Ejemplo:**
```
kEsimoMenor(1) → 10
kEsimoMenor(3) → 30
kEsimoMenor(5) → 50
```

---

### E2 — `imprimirRangoOrdenado(int min, int max)`
Imprime en orden todos los valores en el rango `[min, max]`.  
Aprovecha la propiedad del BST para no recorrer ramas innecesarias.

**Ejemplo:**
```
Rango [20, 60]: 20 30 40 50 60
Rango [40, 80]: 40 50 60 70 80
```

---

### E3 — `diametro()`
Devuelve el camino más largo en aristas entre dos nodos cualesquiera del árbol.  
Para cada nodo calcula `altura(izquierdo) + altura(derecho) + 2` y guarda el máximo.

**Ejemplo:**
```
Árbol (50,30,70,20,40,60,80,10)
Diametro: 5  →  camino: 10→20→30→50→70→80
```

---

### E4 — BST desde consola (`args`)
Construye un BST a partir de valores recibidos como argumentos al ejecutar el programa.

**Ejemplo:**
```bash
java -cp target/classes umg.edu.progra.arboles.Principal 40 20 60 10 30 50 70
```
```
InOrden: 10 20 30 40 50 60 70
Tamanio: 7
```

---

## Reglas seguidas
- No se usó `java.util.*` en ningún momento.
- Las estructuras auxiliares (cola para BFS) fueron implementadas manualmente.
- Toda la lógica nueva está en `ArbolBinarioBusqueda.java`.
- Cada método fue probado desde `Principal.java`.
