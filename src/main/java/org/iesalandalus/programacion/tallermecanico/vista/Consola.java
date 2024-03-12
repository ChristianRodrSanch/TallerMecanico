package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;


public class Consola {

    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola() {

    }

    public static void mostrarCabecera(String mensaje) {
        System.out.println(mensaje);
        System.out.println("-".repeat(mensaje.length()));
    }

    public static void mostrarMenu() {
        mostrarCabecera("Programa que simula la gestión de un taller mecánico");
        for (int i = 0; i < Opcion.values().length; i++) {
            System.out.println(Opcion.values()[i]);
        }
    }

    public static Opcion elegirOpcion() {
        boolean esOpcionValida = false;
        Opcion opcion = null;
        do {
            try {
                opcion = Opcion.get(leerEntero("Introduce la opción a ejecutar: "));
                esOpcionValida = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!esOpcionValida);
        return opcion;
    }

    private static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    public static LocalDate leerFecha(String mensaje) {
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

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Entrada.entero();
    }

    public static Cliente leerCliente() {
        String nombre = Consola.leerCadena("Introduce un nombre válido: ");
        String dni = Consola.leerCadena("Introduce un dni válido: ");
        String telefono = Consola.leerCadena("Introduce un teléfono válido: ");
        return new Cliente(nombre, dni, telefono);
    }

    public static Cliente leerClienteDni() {
        String dni = Consola.leerCadena("Introduce un dni válido para hacer operaciones: ");
        return Cliente.get(dni);
    }

    public static String leerNuevoNombre() {
        return leerCadena("Introduce el nuevo nombre del cliente: ");
    }

    public static String leerNuevoTelefono() {
        return leerCadena("Introduce el nuevo teléfono del cliente: ");
    }

    public static Vehiculo leerVehiculo() {
        String marca = leerCadena("Introduce una marca válida: ");
        String modelo = leerCadena("Introduce un modelo válido: ");
        String matricula = leerCadena("Introduce una matrícula válida: ");
        return new Vehiculo(marca, modelo, matricula);
    }

    public static Vehiculo leerVehiculoMatricula() {
        String matricula = Consola.leerCadena("Introduce una matrícula válida para hacer operaciones: ");
        return Vehiculo.get(matricula);
    }

    public static Revision leerRevision() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio: ");
        return new Revision(cliente, vehiculo, fechaInicio);
    }

    public static int leerHoras() {
        return leerEntero("Introduce su correspondiente número de horas: ");
    }

    public static float leerPrecioMaterial() {
        System.out.print("Introduce su correspondiente precio material: ");
        return Entrada.real();
    }

    public static LocalDate leerFechaCierre() {
        return leerFecha("Introduce la fecha de cierre de la revisión: ");
    }

}
