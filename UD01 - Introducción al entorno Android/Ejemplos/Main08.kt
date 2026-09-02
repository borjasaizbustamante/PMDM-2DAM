package main.kotlin

import java.util.*
import java.util.concurrent.TimeUnit

// This is the Program 1st eval Challenge but made in kotlin (Cines Elorrieta)
class Main08{

    // El teclado
    private val teclado = Scanner(System.`in`)

    // Horas del sabado y domingo en las que hay que proyectar
    private val numeroHorasSabado = 3
    private val numeroHorasDomingo = 6
    private var tiempoRestanteSabado = numeroHorasSabado
    private var tiempoRestanteDomingo = numeroHorasDomingo

    // Claves del usuario
    private var usuarioDelPrograma = "admin"
    private var claveDelPrograma = "admin"

    // Peliculas disponibles con sus generos y su duracion
    private var peliculasString = "Drama, Memorias de Ana Frank, 2 - Drama, Su mejor historia, 1 - " +
            "Comedia, Historias lamentables, 1 - Comedia, Kung Fu Yoga, 1 - Comedia, El milagro de P. Tinto, 2 - " +
            "Terror, El monstruo, 2 - Terror, Ouija, 1 - Terror, Las brujas de Zugarramurdi, 2 - Ciencia, Arcadia, 2 - " +
            "Ciencia, Los ultimos dias en Marte, 1 - Ciencia, El Septimo Hijo, 1"

    // Peliculas seleccionadas por el usuario
    private var peliculasSeleccionadasSabado = ""
    private var peliculasSeleccionadasDomingo = ""

    // Pinta el cartel de bienvenida
    private fun mostrarBienvenida() {
        try {
            println("--------------------------------")
            println("- Bienvenido a Cines Elorrieta -")
            println("--------------------------------")
            println(" ")
            TimeUnit.SECONDS.sleep(3)
        } catch (e: Exception) {
            // No hace falta poner nada aqui
        }
    }

    // Pedimos el login hasta que lo acierte
    private fun pedirElLogin() {
        var usuarioIntroducido: String?
        var claveIntroducida: String?
        var claveCorrecta = false
        do {
            print("Usuario: ")
            usuarioIntroducido = teclado.nextLine().trim { it <= ' ' }

            print("Clave: ")
            claveIntroducida = teclado.nextLine().trim { it <= ' ' }

            if ((usuarioDelPrograma.equals(usuarioIntroducido, ignoreCase = true))
                && (claveDelPrograma == claveIntroducida)
            ) {
                claveCorrecta = true
            } else {
                println("Error!!! Usuario o Clave incorrectos")
            }
        } while (!claveCorrecta)
    }

    // Muestra las peliculas seleccionadas
    private fun mostrarPeliculasSeleccionadas() {
        print("Peliculas del Sabado: ")
        val peliculasSabado = obtenerPeliculas(peliculasSeleccionadasSabado)
        for (i in peliculasSabado.indices) {
            if ((peliculasSabado[i] != null) && (peliculasSabado[i]!!.isNotEmpty())) {
                print(obtenerTituloPelicula(peliculasSabado[i]) + ", ")
            }
        }
        println(" ")
        print("Peliculas del Domingo: ")
        val peliculasDomingo = obtenerPeliculas(peliculasSeleccionadasDomingo)
        for (i in peliculasDomingo.indices) {
            if ((peliculasDomingo[i] != null) && (peliculasDomingo[i]!!.isNotEmpty())) {
                print(obtenerTituloPelicula(peliculasDomingo[i]) + ", ")
            }
        }
    }

    // Muestra el tiempo restante
    private fun mostrarTiempoRestante() {
        println(" ")
        println("Tiempo Restante del Sabado: $tiempoRestanteSabado")
        println("Tiempo Restante del Domingo: $tiempoRestanteDomingo")
        println(" ")
    }

    // Pinta el menu inicial y permite escoger una opcion
    private fun mostrarMenuInicial(): Int {
        var ret: Int
        do {
            try {
                println("- Menu Inicial -")
                println("----------------")
                println("1. Drama")
                println("2. Comedia")
                println("3. Terror")
                println("4. Ciencia Ficcion")
                println("5. Ver Resumen")
                println("0. Salir")
                println(" ")
                print("Escoge una opcion: ")
                ret = teclado.nextInt()
                teclado.nextLine()
            } catch (e: Exception) {
                println("Error!!! Opcion incorrecta")
                teclado.nextLine()
                ret = -1
            }
        } while ((ret < 0) || (ret > 5))
        return ret
    }

    // Pinta el menu inicial y permite escoger una opcion
    private fun estaDeAcuerdo(): Boolean {
        val ret: Boolean
        println("Se perderan los datos guardados... ")
        print("Estas de acuerdo? [s, n]: ")
        val opcion = teclado.nextLine().trim { it <= ' ' }[0].toString() + ""
        ret = opcion.equals("S", ignoreCase = true)
        return ret
    }

    // Pinta el cartel de fin
    private fun mostrarFin() {
        try {
            println(" ")
            println("- Cambios confirmados -")
            println(" ")
            TimeUnit.SECONDS.sleep(2)
        } catch (e: Exception) {
            // No hace falta poner nada aqui
        }
    }

    // Limpiamos...
    private fun resetear() {
        tiempoRestanteSabado = numeroHorasSabado
        tiempoRestanteDomingo = numeroHorasDomingo
        peliculasSeleccionadasSabado = ""
        peliculasSeleccionadasDomingo = ""
        println("- Cambios eliminados -")
    }

    // Muestra las peliculas de un genero
    private fun mostrarPeliculas(peliculas: Array<String?>, genero: String): String? {
        var ret: String? = null
        var opcionMenuPeliculas: Int
        var contadorMenu: Int
        do {
            contadorMenu = 1
            try {
                println(" ")
                println("- Peliculas de $genero - ")
                println("----------------------------")

                for (i in peliculas.indices) {
                    if ((null != peliculas[i]) && (esDelGenero(peliculas[i], genero))) {
                        println(
                            contadorMenu.toString() + ". " + obtenerTituloPelicula(peliculas[i]) + " - "
                                    + obtenerDuracionPelicula(peliculas[i]) + "h"
                        )
                        contadorMenu++
                    }
                }
                println("0. Atras")
                println(" ")
                print("Escoge una opcion: ")
                opcionMenuPeliculas = teclado.nextInt()
                teclado.nextLine()
            } catch (e: Exception) {
                println("Error!!! Opcion incorrecta")
                teclado.nextLine()
                opcionMenuPeliculas = -1
            }
        } while ((opcionMenuPeliculas < 0) || (opcionMenuPeliculas >= contadorMenu))

        if (opcionMenuPeliculas != 0) {
            ret = peliculas[opcionMenuPeliculas - 1]
        }

        return ret
    }


    private fun obtenerPeliculas(peliculasString: String): Array<String?> {
        val parts: Array<String?> = peliculasString.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return parts
    }

    // Busca las peliculas de un genero y su duracion es inferior al tiempo
    // restante, las devuelve.
    private fun buscarPeliculas(genero: String?, tiempoRestante: Int): Array<String?> {
        val ret = arrayOfNulls<String>(peliculasString.length)
        val peliculas = obtenerPeliculas(peliculasString)

        var cont = 0
        for (i in peliculas.indices) {
            val duracionPelicula = obtenerDuracionPelicula(peliculas[i]).toInt()

            if ((esDelGenero(peliculas[i], genero) && (duracionPelicula <= tiempoRestante))) {
                ret[cont] = peliculas[i]
                cont++
            }
        }
        return ret
    }

    // Dice si una pelicula es de un genero o no
    private fun esDelGenero(pelicula: String?, genero: String?): Boolean {
        var ret = false
        if ((pelicula != null) && (genero != null) && (pelicula.indexOf(genero) != -1)) {
            ret = true
        }
        return ret
    }

    private fun yaHayPeliculasDelGeneroElSabado(genero: String?): Boolean {
        return yaHayPeliculasDelGenero(genero, peliculasSeleccionadasSabado)
    }

    private fun yaHayPeliculasDelGeneroElDomingo(genero: String?): Boolean {
       return yaHayPeliculasDelGenero(genero, peliculasSeleccionadasDomingo)
    }

    private fun yaHayPeliculasDelGenero(genero: String?, peliculaSeleccionada : String): Boolean {
        var ret = false
        val peliculasDomingo = obtenerPeliculas(peliculaSeleccionada)
        for (i in peliculasDomingo.indices) {
            val pelicula = peliculasDomingo[i]
            if ((null != pelicula) && (esDelGenero(pelicula, genero))) {
                ret = true
                break
            }
        }
        return ret
    }

    private fun hayHuecoParaPeliculasElSabado(): Boolean {
        return hayHuecoParaPeliculas(tiempoRestanteSabado)
    }

    private fun hayHuecoParaPeliculasElDomingo(): Boolean {
        return hayHuecoParaPeliculas(tiempoRestanteDomingo)
    }

    private fun hayHuecoParaPeliculas(tiempo : Int): Boolean {
        var ret = false
        val peliculas = obtenerPeliculas(peliculasString)
        for (i in peliculas.indices) {
            val pelicula = peliculas[i]
            if (((null != pelicula)
                        && ((obtenerDuracionPelicula(pelicula)).toInt() <= tiempo))
            ) {
                ret = true
                break
            }
        }
        return ret
    }

    private fun obtenerTituloPelicula(pelicula: String?): String {
        val parts = pelicula!!.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return parts[1].trim { it <= ' ' }
    }

    private fun obtenerDuracionPelicula(pelicula: String?): String {
        val parts = pelicula!!.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return parts[2].trim { it <= ' ' }
    }


    // ---------------------------------------------------------------------------------------------------------------//
    // El bucle principal de la clase
    fun buclePrincipal(){
        var seleccionMenuInicial: Int
        val deAcuerdo = false
        do {
            mostrarBienvenida()
            pedirElLogin()
            println(" ")

            // Bucle principal - Se sale con seleccionMenuInicial == 0
            do {
                mostrarPeliculasSeleccionadas()
                mostrarTiempoRestante()

                seleccionMenuInicial = mostrarMenuInicial()

                var peliculaSeleccionada: String?
                var peliculas: Array<String?>
                when (seleccionMenuInicial) {
                    1 -> if ((tiempoRestanteSabado >= 0) && (hayHuecoParaPeliculasElSabado())) {
                        println("Espacio libre para peliculas el sabado")
                        peliculas = buscarPeliculas("Drama", tiempoRestanteSabado)
                        peliculaSeleccionada = mostrarPeliculas(peliculas, "Drama")

                        // Pueden haber seleccionado 0 -> Volver...
                        if (null != peliculaSeleccionada) {
                            // Tenemos que añadir la pelicula al Sabado

                            val duracionPelicula = obtenerDuracionPelicula(peliculaSeleccionada).toInt()

                            // ¿Hay ya peliculas del mismo genero?
                            if (yaHayPeliculasDelGeneroElSabado("Drama")) {
                                // No podemos añadirla...
                                println("Ya hay peliculas de Drama asignadas al Sabado")
                            } else {
                                // Si podemos añadirla...
                                println("La pelicula se asigna al sabado")
                                tiempoRestanteSabado -= duracionPelicula
                                peliculasSeleccionadasSabado =
                                    if (peliculasSeleccionadasSabado.isEmpty()) peliculasSeleccionadasSabado + peliculaSeleccionada
                                    else (peliculasSeleccionadasSabado + " - "
                                            + peliculaSeleccionada)
                            }
                        }
                    } else if ((tiempoRestanteDomingo >= 0) && (hayHuecoParaPeliculasElDomingo())) {
                        println("No hay espacio para peliculas el sabado")
                        peliculas = buscarPeliculas("Drama", tiempoRestanteDomingo)
                        peliculaSeleccionada = mostrarPeliculas(peliculas, "Drama")

                        // Pueden haber seleccionado 0 -> Volver...
                        if (null != peliculaSeleccionada) {
                            // Tenemos que añadir la pelicula al Domingo

                            val duracionPelicula = obtenerDuracionPelicula(peliculaSeleccionada).toInt()

                            // ¿Hay ya peliculas del mismo genero?
                            if (yaHayPeliculasDelGeneroElDomingo("Drama")) {
                                // No podemos añadirla...
                                println("Ya hay peliculas de Drama asignadas al Domingo")
                            } else {
                                // Si podemos añadirla...
                                println("La pelicula se asigna al domingo")
                                tiempoRestanteDomingo -= duracionPelicula
                                peliculasSeleccionadasDomingo = if (peliculasSeleccionadasDomingo.isEmpty()) (peliculasSeleccionadasDomingo
                                        + peliculaSeleccionada)
                                else (peliculasSeleccionadasDomingo + " - "
                                        + peliculaSeleccionada)
                            }
                        }
                    } else {
                        println("No hay espacio para peliculas ni el sabado ni el domingo")
                    }

                    2 -> if ((tiempoRestanteSabado >= 0) && (hayHuecoParaPeliculasElSabado())) {
                        println("Espacio libre para peliculas el sabado")
                        peliculas = buscarPeliculas("Comedia", tiempoRestanteSabado)
                        peliculaSeleccionada = mostrarPeliculas(peliculas, "Comedia")

                        // Pueden haber seleccionado 0 -> Volver...
                        if (null != peliculaSeleccionada) {
                            // Tenemos que añadir la pelicula al Sabado

                            val duracionPelicula = obtenerDuracionPelicula(peliculaSeleccionada).toInt()

                            // ¿Hay ya peliculas del mismo genero?
                            if (yaHayPeliculasDelGeneroElSabado("Comedia")) {
                                // No podemos añadirla...
                                println("Ya hay peliculas de Comedia asignadas al Sabado")
                            } else {
                                // Si podemos añadirla...
                                println("La pelicula se asigna al sabado")
                                tiempoRestanteSabado -= duracionPelicula
                                peliculasSeleccionadasSabado =
                                    if (peliculasSeleccionadasSabado.isEmpty()) peliculasSeleccionadasSabado + peliculaSeleccionada
                                    else (peliculasSeleccionadasSabado + " - "
                                            + peliculaSeleccionada)
                            }
                        }
                    } else if ((tiempoRestanteDomingo >= 0) && (hayHuecoParaPeliculasElDomingo())) {
                        println("No hay espacio para peliculas el sabado")
                        peliculas = buscarPeliculas("Drama", tiempoRestanteDomingo)
                        peliculaSeleccionada = mostrarPeliculas(peliculas, "Comedia")

                        // Pueden haber seleccionado 0 -> Volver...
                        if (null != peliculaSeleccionada) {
                            // Tenemos que añadir la pelicula al Domingo

                            val duracionPelicula = obtenerDuracionPelicula(peliculaSeleccionada).toInt()

                            // ¿Hay ya peliculas del mismo genero?
                            if (yaHayPeliculasDelGeneroElDomingo("Comedia")) {
                                // No podemos añadirla...
                                println("Ya hay peliculas de Comedia asignadas al Domingo")
                            } else {
                                // Si podemos añadirla...
                                println("La pelicula se asigna al domingo")
                                tiempoRestanteDomingo -= duracionPelicula
                                peliculasSeleccionadasDomingo = if (peliculasSeleccionadasDomingo.isEmpty()) (peliculasSeleccionadasDomingo
                                        + peliculaSeleccionada)
                                else (peliculasSeleccionadasDomingo + " - "
                                        + peliculaSeleccionada)
                            }
                        }
                    } else {
                        println("No hay espacio para peliculas ni el sabado ni el domingo")
                    }

                    3 -> if ((tiempoRestanteSabado >= 0) && (hayHuecoParaPeliculasElSabado())) {
                        println("Espacio libre para peliculas el sabado")
                        peliculas = buscarPeliculas("Terror", tiempoRestanteSabado)
                        peliculaSeleccionada = mostrarPeliculas(peliculas, "Terror")

                        // Pueden haber seleccionado 0 -> Volver...
                        if (null != peliculaSeleccionada) {
                            // Tenemos que añadir la pelicula al Sabado

                            val duracionPelicula = obtenerDuracionPelicula(peliculaSeleccionada).toInt()

                            // ¿Hay ya peliculas del mismo genero?
                            if (yaHayPeliculasDelGeneroElSabado("Terror")) {
                                // No podemos añadirla...
                                println("Ya hay peliculas de Terror asignadas al Sabado")
                            } else {
                                // Si podemos añadirla...
                                println("La pelicula se asigna al sabado")
                                tiempoRestanteSabado -= duracionPelicula
                                if (peliculasSeleccionadasSabado.isEmpty()) peliculasSeleccionadasSabado += peliculaSeleccionada
                                else peliculasSeleccionadasSabado = (peliculasSeleccionadasSabado + " - "
                                        + peliculaSeleccionada)
                            }
                        }
                    } else if ((tiempoRestanteDomingo >= 0) && (hayHuecoParaPeliculasElDomingo())) {
                        println("No hay espacio para peliculas el sabado")
                        peliculas = buscarPeliculas("Terror", tiempoRestanteDomingo)
                        peliculaSeleccionada = mostrarPeliculas(peliculas, "Terror")

                        // Pueden haber seleccionado 0 -> Volver...
                        if (null != peliculaSeleccionada) {
                            // Tenemos que añadir la pelicula al Domingo

                            val duracionPelicula = obtenerDuracionPelicula(peliculaSeleccionada).toInt()

                            // ¿Hay ya peliculas del mismo genero?
                            if (yaHayPeliculasDelGeneroElDomingo("Terror")) {
                                // No podemos añadirla...
                                println("Ya hay peliculas de Terror asignadas al Domingo")
                            } else {
                                // Si podemos añadirla...
                                println("La pelicula se asigna al domingo")
                                tiempoRestanteDomingo -= duracionPelicula
                                peliculasSeleccionadasDomingo = if (peliculasSeleccionadasDomingo.isEmpty()) (peliculasSeleccionadasDomingo
                                        + peliculaSeleccionada)
                                else (peliculasSeleccionadasDomingo + " - "
                                        + peliculaSeleccionada)
                            }
                        }
                    } else {
                        println("No hay espacio para peliculas ni el sabado ni el domingo")
                    }

                    4 -> if ((tiempoRestanteSabado >= 0) && (hayHuecoParaPeliculasElSabado())) {
                        println("Espacio libre para peliculas el sabado")
                        peliculas = buscarPeliculas("Ciencia", tiempoRestanteSabado)
                        peliculaSeleccionada = mostrarPeliculas(peliculas, "Ciencia")

                        // Pueden haber seleccionado 0 -> Volver...
                        if (null != peliculaSeleccionada) {
                            // Tenemos que añadir la pelicula al Sabado

                            val duracionPelicula = obtenerDuracionPelicula(peliculaSeleccionada).toInt()

                            // ¿Hay ya peliculas del mismo genero?
                            if (yaHayPeliculasDelGeneroElSabado("Ciencia")) {
                                // No podemos añadirla...
                                println("Ya hay peliculas de Ciencia asignadas al Sabado")
                            } else {
                                // Si podemos añadirla...
                                println("La pelicula se asigna al sabado")
                                tiempoRestanteSabado -= duracionPelicula
                                peliculasSeleccionadasSabado =
                                    if (peliculasSeleccionadasSabado.isEmpty()) peliculasSeleccionadasSabado + peliculaSeleccionada
                                    else (peliculasSeleccionadasSabado + " - "
                                            + peliculaSeleccionada)
                            }
                        }
                    } else if ((tiempoRestanteDomingo >= 0) && (hayHuecoParaPeliculasElDomingo())) {
                        println("No hay espacio para peliculas el sabado")
                        peliculas = buscarPeliculas("Ciencia", tiempoRestanteDomingo)
                        peliculaSeleccionada = mostrarPeliculas(peliculas, "Ciencia")

                        // Pueden haber seleccionado 0 -> Volver...
                        if (null != peliculaSeleccionada) {
                            // Tenemos que añadir la pelicula al Domingo

                            val duracionPelicula = obtenerDuracionPelicula(peliculaSeleccionada).toInt()

                            // ¿Hay ya peliculas del mismo genero?
                            if (yaHayPeliculasDelGeneroElDomingo("Ciencia")) {
                                // No podemos añadirla...
                                println("Ya hay peliculas de Ciencia asignadas al Domingo")
                            } else {
                                // Si podemos añadirla...
                                println("La pelicula se asigna al domingo")
                                tiempoRestanteDomingo -= duracionPelicula
                                peliculasSeleccionadasDomingo = if (peliculasSeleccionadasDomingo.isEmpty()) (peliculasSeleccionadasDomingo
                                        + peliculaSeleccionada)
                                else (peliculasSeleccionadasDomingo + " - "
                                        + peliculaSeleccionada)
                            }
                        }
                    } else {
                        println("No hay espacio para peliculas ni el sabado ni el domingo")
                    }

                    5 ->
                        /**
                         * --------- - FALTA - ---------
                         */
                        if (estaDeAcuerdo()) {
                            mostrarFin()
                        } else {
                            resetear()
                        }

                    0 -> println("Adios!!")
                    else -> println("Este mensaje no deberia mostrarse")
                }
            } while (seleccionMenuInicial != 0)
        } while ((seleccionMenuInicial != 0) && (!deAcuerdo))
        teclado.close()
    }
}

fun main (){
    val reto = Main08()
    reto.buclePrincipal()
}