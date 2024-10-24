/* LABORATORIO_2 ESTRUCTURA DE DATOS */
/* BIENVENIDOS A PUPUSERÍA EL COMALITO */

/* Importamos las clases necesarias para manejar colas (Queue), listas enlazadas y pilas (Stack) */
import java.util.LinkedList
import java.util.Queue
import java.util.Stack

/* Colores ANSI para darle un poco de vida a la consola */
const val RESET = "\u001B[0m"
const val RED = "\u001B[31m"
const val GREEN = "\u001B[32m"
const val YELLOW = "\u001B[33m"
const val BLUE = "\u001B[34m"
const val PURPLE = "\u001B[35m"
const val CYAN = "\u001B[36m"

/* Creamos una clase para representar una pupusa con su tipo y cantidad */
data class Pupusa(val tipo: String, val cantidad: Int)

/* Clase que representa una orden, la cual contiene el nombre del cliente y una lista de pupusas */
data class Orden(val cliente: String, val pupusas: List<Pupusa>)

/* Función principal que ejecuta todo el sistema */
fun main() {
    val colaOrdenesPendientes: Queue<Orden> = LinkedList()
    val pilaOrdenesDespachadas: Stack<Orden> = Stack()

    /* Mensaje de bienvenida con color cian */
    println("${CYAN}Bienvenido a la Pupusería El Comalito$RESET")
    var continuar = true 
    while (continuar) { 
        println("${BLUE}Seleccione una opción:$RESET")
        println("1. Registrar una nueva orden")
        println("2. Ver órdenes pendientes")
        println("3. Despachar orden")
        println("4. Ver órdenes despachadas")
        println("5. Salir")

        /* Capturamos la opción del usuario */
        when (readLine()?.toIntOrNull()) {
            1 -> registrarOrden(colaOrdenesPendientes) 
            2 -> verOrdenesPendientes(colaOrdenesPendientes) 
            3 -> despacharOrden(colaOrdenesPendientes, pilaOrdenesDespachadas) 
            4 -> verOrdenesDespachadas(pilaOrdenesDespachadas) 
            5 -> {
                println("${GREEN}Saliendo del sistema. ¡Gracias!$RESET")
                continuar = false 
            }
            else -> println("${RED}Opción no válida. Intente de nuevo.$RESET") 
        }
    }
}

/* Función para registrar una nueva orden en la cola de órdenes pendientes */
fun registrarOrden(colaOrdenesPendientes: Queue<Orden>) {
    println("${YELLOW}Ingrese el nombre del cliente:$RESET")
    val nombreCliente = readLine() ?: return
    
    /* Pedimos cuántos tipos de pupusas queremos */
    println("${YELLOW}¿Cuántos tipos de pupusas desea ordenar?$RESET")
    val cantidadTipos = readLine()?.toIntOrNull() ?: return

    val pupusas = mutableListOf<Pupusa>() 
    for (i in 1..cantidadTipos) {
        println("${YELLOW}Ingrese el tipo de pupusa #$i:$RESET")
        val tipo = readLine() ?: return
        println("${YELLOW}Ingrese la cantidad de pupusas de $tipo:$RESET")
        val cantidad = readLine()?.toIntOrNull() ?: return
        pupusas.add(Pupusa(tipo, cantidad))

    /* Creamos una nueva orden con el nombre del cliente y su lista de pupusas */
    val nuevaOrden = Orden(nombreCliente, pupusas)
    colaOrdenesPendientes.add(nuevaOrden)
    println("${GREEN}Orden registrada para $nombreCliente: ${pupusas.joinToString { "${it.cantidad} pupusas de ${it.tipo}" }}$RESET")
}

/* Función para ver las órdenes pendientes */
fun verOrdenesPendientes(colaOrdenesPendientes: Queue<Orden>) {
    if (colaOrdenesPendientes.isEmpty()) {
        println("${RED}No hay órdenes pendientes.$RESET") 
    } else {
        println("${PURPLE}Órdenes pendientes:$RESET")
        colaOrdenesPendientes.forEachIndexed { index, orden ->
            println("${index + 1}. ${orden.cliente}: ${orden.pupusas.joinToString { "${it.cantidad} pupusas de ${it.tipo}" }}")
        }
    }
}

/* Función para despachar la primera orden de la cola y moverla a la pila de órdenes despachadas */
fun despacharOrden(colaOrdenesPendientes: Queue<Orden>, pilaOrdenesDespachadas: Stack<Orden>) {
    val ordenDespachada = colaOrdenesPendientes.poll() 
    if (ordenDespachada != null) {
        pilaOrdenesDespachadas.push(ordenDespachada) 
        println("${GREEN}Despachando la orden de ${ordenDespachada.cliente}: ${ordenDespachada.pupusas.joinToString { "${it.cantidad} pupusas de ${it.tipo}" }}$RESET")
    } else {
        println("${RED}No hay órdenes para despachar.$RESET") 
}

/* Función para ver las órdenes ya despachadas */
fun verOrdenesDespachadas(pilaOrdenesDespachadas: Stack<Orden>) {
    if (pilaOrdenesDespachadas.isEmpty()) {
        println("${RED}No hay órdenes despachadas.$RESET") 
    } else {
        println("${PURPLE}Órdenes despachadas:$RESET")
        pilaOrdenesDespachadas.forEachIndexed { index, orden ->
            println("${index + 1}. ${orden.cliente}: ${orden.pupusas.joinToString { "${it.cantidad} pupusas de ${it.tipo}" }}")
        }
    }
}
/* ELABORADO POR WILFREDO PORTILLO VILLALTA U20230820 */
