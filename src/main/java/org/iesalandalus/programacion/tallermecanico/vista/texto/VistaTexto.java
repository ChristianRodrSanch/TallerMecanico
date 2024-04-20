package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import java.time.LocalDate;
import java.util.*;

import static org.iesalandalus.programacion.tallermecanico.vista.texto.Consola.*;

public class VistaTexto implements Vista {

    private final GestorEventos gestorEventos = new GestorEventos(Evento.values());

    @Override
    public GestorEventos getGestorEventos(){

        return gestorEventos;

    }

    private void ejecutar(Evento opcion) {
        Consola.mostrarCabecera(opcion.toString());
        gestorEventos.notificar(opcion);
    }



    @Override
    public void comenzar() {
        Evento opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Evento.SALIR);
    }


    @Override
    public void terminar() {
        System.out.println("El programa ha finalizado.");
    }






    @Override
    public  Cliente leerCliente() {
        String nombre = Consola.leerCadena("Introduce un nombre válido: ");
        String dni = Consola.leerCadena("Introduce un dni válido: ");
        String telefono = Consola.leerCadena("Introduce un teléfono válido: ");
        return new Cliente(nombre, dni, telefono);
    }

    @Override
    public  Cliente leerClienteDni() {
        String dni = Consola.leerCadena("Introduce un dni válido para hacer operaciones: ");
        return Cliente.get(dni);
    }

    @Override
    public  String leerNuevoNombre() {
        return Consola.leerCadena("Introduce el nuevo nombre del cliente: ");
    }

    @Override
    public  String leerNuevoTelefono() {
        return Consola.leerCadena("Introduce el nuevo teléfono del cliente: ");
    }

    @Override
    public  Vehiculo leerVehiculo() {
        String marca = leerCadena("Introduce una marca válida: ");
        String modelo = leerCadena("Introduce un modelo válido: ");
        String matricula = leerCadena("Introduce una matrícula válida: ");
        return new Vehiculo(marca, modelo, matricula);
    }

    @Override
    public  Vehiculo leerVehiculoMatricula() {
        String matricula = Consola.leerCadena("Introduce una matrícula válida para hacer operaciones: ");
        return Vehiculo.get(matricula);
    }

    @Override
    public  Trabajo leerRevision() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio: ");
        return new Revision(cliente, vehiculo, fechaInicio);
    }

    @Override
    public Trabajo leerMecanico() {
        Cliente cliente = leerCliente();
        Vehiculo vehiculo = leerVehiculo();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio");
        return  new Mecanico(cliente,vehiculo,fechaInicio);

    }


    @Override
    public Trabajo leerTrabajoVehiculo(){

        return Trabajo.get(leerVehiculoMatricula());

    }



    @Override
    public  int leerHoras() {
        return Consola.leerEntero("Introduce su correspondiente número de horas: ");
    }

    @Override
    public  float leerPrecioMaterial() {
        return Consola.leerReal("Introduce el precio del material a añadir: ");
    }


    @Override
    public  LocalDate leerFechaCierre() {
        return Consola.leerFecha("Introduce la fecha de cierre");
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) {
        Objects.requireNonNull(evento, "No puedo notificar un evento nulo.");
        Objects.requireNonNull(texto, "No puedo mostrar un texto nulo.");
        if (exito) {
            System.out.printf(texto);
        } else {
            System.out.printf("ERROR: %s%n", texto);
        }
    }

    public LocalDate leerMes() {
        return Consola.leerFecha("Introduce la fecha que desea leer el mes. ");
    }


    @Override
    public void mostrarCliente(Cliente cliente) {

        System.out.println((cliente != null) ? cliente : "No existe ningún cliente con dicho DNI.");

    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {
        System.out.println((vehiculo != null) ? vehiculo : "No existe ningún vehículo con dicha matrícula.");
    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {

        System.out.println((trabajo != null) ? trabajo : "No existe ningún trabajo para ese cliente, vehículo y fecha.");

    }

    @Override
    public void mostrarClientes(List<Cliente> clientes) {
        clientes.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));
        if(!clientes.isEmpty()){
            for (Cliente cliente : clientes) {
                System.out.println(cliente.toString());

            }

        } else {
            System.out.println("No hay cliente que mostrar");
        }
    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {
        if (!vehiculos.isEmpty()) {
            vehiculos.sort(Comparator.comparing(Vehiculo::marca).thenComparing(Vehiculo::modelo).thenComparing(Vehiculo::matricula));

            for (Vehiculo vehiculo : vehiculos) {
                System.out.println(vehiculo.toString());


            }

        } else {
            System.out.println("No hay vehiculo para mostrar");
        }
    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {
        Comparator<Cliente> comparadorCliente = Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);
        if (!trabajos.isEmpty()) {
            trabajos.sort(Comparator.comparing(Trabajo::getFechaInicio).thenComparing(Trabajo::getCliente,comparadorCliente));

            for (Trabajo trabajo : trabajos) {
                System.out.println(trabajo.toString());

            }
        } else {
            System.out.println("No hay trabajo para mostrar");
        }
    }

    @Override
    public void mostrarEstadisticasMensuales(Map<TipoTrabajo, Integer> estadisticas) {
        for (Map.Entry<TipoTrabajo,Integer> estadistica : estadisticas.entrySet()) { // Coge las entradas del mapa
            System.out.println("Tipo trabajo" + estadistica.getKey()); // Aquí asigna su clave en este caso que trabajo sería
            System.out.println("Valores de las Estadisticas" + estadistica.getValue()); // Y aqui le asigna su valor

        }
    }


}



