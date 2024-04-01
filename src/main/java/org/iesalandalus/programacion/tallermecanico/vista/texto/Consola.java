package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Consola {

        private static final String  CADENA_FORMATO_FECHA = "dd/MM/yyyy";

        private Consola() {

        }

        public static void mostrarCabecera(String mensaje) {
                System.out.println(mensaje);
                System.out.println("-".repeat(mensaje.length()));
        }

      public   static void mostrarMenu() {
                mostrarCabecera("Programa que simula la gestión de un taller mecánico");
                for (int i = 0; i < Evento.values().length; i++) {
                        System.out.println(Evento.values()[i]);
                }
        }

      public  static Evento elegirOpcion() {
                boolean esOpcionValida = false;
                Evento opcion = null;
                do {
                        try {
                                opcion = Evento.(leerEntero("Introduce la opción a ejecutar: "));
                                esOpcionValida = true;
                        } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                        }
                } while (!esOpcionValida);
                return opcion;
        }

        public static String leerCadena(String mensaje) {
                System.out.print(mensaje);
                return Entrada.cadena();
        }

        public   static LocalDate leerFecha(String mensaje) {
                Pattern patron = Pattern.compile(CADENA_FORMATO_FECHA);
                DateTimeFormatter comparador = DateTimeFormatter.ofPattern(String.valueOf(patron));
                LocalDate fecha = null;
                try {
                        String cadenaFecha = leerCadena(mensaje);
                        fecha = LocalDate.parse(cadenaFecha, comparador);
                } catch (DateTimeParseException e) {
                        System.out.println("La fecha introducida no es válida, inténtelo de nuevo.");
                }
                return fecha;
        }

        public static int leerEntero(String mensaje) {
                System.out.print(mensaje);
                return Entrada.entero();
        }


}
