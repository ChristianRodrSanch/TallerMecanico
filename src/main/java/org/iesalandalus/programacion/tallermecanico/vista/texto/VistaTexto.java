package org.iesalandalus.programacion.tallermecanico.vista.texto;

import com.sun.source.tree.TryTree;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import javax.sound.midi.SysexMessage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static org.iesalandalus.programacion.tallermecanico.vista.texto.Consola.*;

public class VistaTexto implements Vista {

    private GestorEventos gestorEventos;

    @Override
    public GestorEventos getGestorEventos(){

        return gestorEventos;

    }



    @Override
    public void comenzar() {

        System.out.println("La VistaTexto acaba de iniciarse.");
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
        return leerCadena("Introduce el nuevo nombre del cliente: ");
    }

    @Override
    public  String leerNuevoTelefono() {
        return leerCadena("Introduce el nuevo teléfono del cliente: ");
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

        Vehiculo vehiculo = leerVehiculoMatricula();

      return new Revision(Cliente.get("77697729A"),vehiculo,LocalDate.now());

    }



    @Override
    public  int leerHoras() {
        return leerEntero("Introduce su correspondiente número de horas: ");
    }

    @Override
    public  float leerPrecioMaterial() {
        System.out.print("Introduce su correspondiente precio material: ");
        return Entrada.real();
    }

    @Override
    public  LocalDate leerFechaCierre() {
        return leerFecha("Introduce la fecha de cierre de la revisión: ");
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) {
        Objects.requireNonNull(evento, "No puedo notificar un evento nulo.");
        Objects.requireNonNull(texto, "No puedo mostrar un texto nulo.");
        if (exito) {
            System.out.printf("%s%n", texto);
        } else {
            System.out.printf("ERROR: %s%n", texto);
        }
    }

    @Override
    public void mostrarCliente(Cliente cliente) {

        System.out.println(cliente.toString());


    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {
        System.out.println(vehiculo.toString());
    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {

        System.out.println(trabajo.toString());

    }

    @Override
    public void mostrarClientes(List<Cliente> clientes) {

        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());


        }
    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {

        for (Vehiculo vehiculo : vehiculos) {
            System.out.println(vehiculo.toString());


        }

    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {
        for (Trabajo trabajo : trabajos) {
            System.out.println(trabajo.toString());


        }
    }




}



   /* private void insertarCliente() {
        Consola.mostrarCabecera("INSERTAR CLIENTE");
        try {
            gestorEventos.insertar(Consola.leerCliente());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertarVehiculo() {
        Consola.mostrarCabecera("INSERTAR VEHÍCULO");
        try {
            gestorEventos.insertar(Consola.leerVehiculo());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertarRevision() { //
        Consola.mostrarCabecera("INSERTAR REVISIÓN");
        try {
            gestorEventos.insertar(new Revision(Consola.leerClienteDni(), Consola.leerVehiculoMatricula(), Consola.leerFecha("Introduce la fecha de inicio:"))); // Ya busca el dni solo
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarCliente() {
        Consola.mostrarCabecera("BUSCAR CLIENTE");
        try {
            System.out.println(gestorEventos.buscar(Consola.leerClienteDni()));
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarVehiculo() {
        Consola.mostrarCabecera("BUSCAR VEHÍCULO");
        try {
            System.out.println(gestorEventos.buscar(Consola.leerVehiculoMatricula()));
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarRevision() {
        Consola.mostrarCabecera("BUSCAR REVISIÓN");
        try {
            System.out.println(gestorEventos.buscar(Consola.leerRevision()));
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void modificarCliente() {
        Consola.mostrarCabecera("MODIFICAR CLIENTE");
        boolean esModificado = false;
        try {
            esModificado = gestorEventos.modificar(Consola.leerClienteDni(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        if (esModificado) {
            System.out.println("El cliente se ha podido modificar correctamente.");
        }
    }

    private void anadirHoras() {
        Consola.mostrarCabecera("AÑADIR HORAS");
        try {
            Cliente cliente = Consola.leerClienteDni();
            Vehiculo vehiculo = Consola.leerVehiculoMatricula();
            gestorEventos.anadirHoras(new Revision(cliente, vehiculo, Consola.leerFecha("Introduce la fecha de inicio:")), Consola.leerHoras());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void anadirPrecioMaterial() {
        Consola.mostrarCabecera("AÑADIR PRECIO MATERIAL");
        try {
            gestorEventos.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerPrecioMaterial());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void cerrar() {
        Consola.mostrarCabecera("CERRAR REVISIÓN");
        try {
            gestorEventos.cerrar(new Revision(Consola.leerRevision()), Consola.leerFechaCierre());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarCliente() {
        Consola.mostrarCabecera("BORRAR CLIENTE");
        try {
            gestorEventos.borrar(Consola.leerClienteDni());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarVehiculo() {
        Consola.mostrarCabecera("BORRAR VEHÍCULO");
        try {
            gestorEventos.borrar(Consola.leerVehiculo());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarRevision() {
        Consola.mostrarCabecera("BORRAR REVISIÓN");
        try {
            gestorEventos.borrar(Consola.leerRevision());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }



    private void listarVehiculos() {
        Consola.mostrarCabecera("LISTAR VEHÍCULOS");
        List<Vehiculo> vehiculoList = gestorEventos.getVehiculos();
        if (vehiculoList.isEmpty()) {
            System.out.println("No se ha encontrado ningún vehículo.");
        } else {
            System.out.println(vehiculoList);
        }
    }

    private void listarRevisiones() {
        Consola.mostrarCabecera("LISTAR REVISIONES");
        List<Revision> revisionList = gestorEventos.getRevision();
        if (revisionList.isEmpty()) {
            System.out.println("No se ha encontrado ninguna revisión.");
        } else {
            System.out.println(revisionList);
        }
    }

    private void listarRevisionesCliente() {
        Consola.mostrarCabecera("LISTAR REVISIONES DEL CLIENTE");
        List<Revision> clienteList = gestorEventos.getRevisiones(Consola.leerClienteDni());
        if (clienteList.isEmpty()) {
            System.out.println("No se ha encontrado ninguna revisión para ese cliente.");
        } else {
            System.out.println(clienteList);
        }
    }

    private void listarRevisionesVehiculo() {
        List<Revision> vehiculoList = gestorEventos.getRevisiones(Consola.leerVehiculoMatricula());
        if (vehiculoList.isEmpty()) {
            System.out.println("No se ha encontrado ninguna revisión para ese vehículo.");
        } else {
            System.out.println(vehiculoList);
        }
    }

    private void salir() {
        Consola.mostrarCabecera("SALIR");
        gestorEventos.terminar();
    }
} */
