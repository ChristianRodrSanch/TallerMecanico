/*package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Objects;

public class Vista {

    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "El controlador no puede ser nulo.");
        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("El programa ha finalizado.");
    }

    private void ejecutar(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_CLIENTE -> insertarCliente();
            case BUSCAR_CLIENTE -> buscarCliente();
            case BORRAR_CLIENTE -> borrarCliente();
            case LISTAR_CLIENTES -> listarClientes();
            case MODIFICAR_CLIENTE -> modificarCliente();
            case INSERTAR_VEHICULO -> insertarVehiculo();
            case BUSCAR_VEHICULO -> buscarVehiculo();
            case BORRAR_VEHICULO -> borrarVehiculo();
            case LISTAR_VEHICULOS -> listarVehiculos();
            case INSERTAR_REVISION -> insertarRevision();
            case BUSCAR_RESIVION -> buscarRevision();
            case BORRAR_REVISION -> borrarRevision();
            case LISTAR_REVISIONES -> listarRevisiones();
            case LISTAR_REVISIONES_CLIENTE -> listarRevisionesCliente();
            case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculo();
            case ANADIR_HORAS_REVISION -> anadirHoras();
            case ANADIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterial();
            case CERRAR_REVISION -> cerrar();
            case SALIR -> salir();
            default -> throw new IllegalArgumentException("La opción introducida no es válida.");
        }
    }

    private void insertarCliente() {
        Consola.mostrarCabecera("INSERTAR CLIENTE");
        try {
            controlador.insertar(Consola.leerCliente());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertarVehiculo() {
        Consola.mostrarCabecera("INSERTAR VEHÍCULO");
        try {
            controlador.insertar(Consola.leerVehiculo());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertarRevision() { //
        Consola.mostrarCabecera("INSERTAR REVISIÓN");
        try {
            controlador.insertar(new Revision(Consola.leerClienteDni(), Consola.leerVehiculoMatricula(), Consola.leerFecha("Introduce la fecha de inicio:"))); // Ya busca el dni solo
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarCliente() {
        Consola.mostrarCabecera("BUSCAR CLIENTE");
        try {
            System.out.println(controlador.buscar(Consola.leerClienteDni()));
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarVehiculo() {
        Consola.mostrarCabecera("BUSCAR VEHÍCULO");
        try {
            System.out.println(controlador.buscar(Consola.leerVehiculoMatricula()));
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarRevision() {
        Consola.mostrarCabecera("BUSCAR REVISIÓN");
        try {
            System.out.println(controlador.buscar(Consola.leerRevision()));
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void modificarCliente() {
        Consola.mostrarCabecera("MODIFICAR CLIENTE");
        boolean esModificado = false;
        try {
            esModificado = controlador.modificar(Consola.leerClienteDni(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono());
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
            controlador.anadirHoras(new Revision(cliente, vehiculo, Consola.leerFecha("Introduce la fecha de inicio:")), Consola.leerHoras());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void anadirPrecioMaterial() {
        Consola.mostrarCabecera("AÑADIR PRECIO MATERIAL");
        try {
            controlador.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerPrecioMaterial());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void cerrar() {
        Consola.mostrarCabecera("CERRAR REVISIÓN");
        try {
            controlador.cerrar(new Revision(Consola.leerRevision()), Consola.leerFechaCierre());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarCliente() {
        Consola.mostrarCabecera("BORRAR CLIENTE");
        try {
            controlador.borrar(Consola.leerClienteDni());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarVehiculo() {
        Consola.mostrarCabecera("BORRAR VEHÍCULO");
        try {
            controlador.borrar(Consola.leerVehiculo());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarRevision() {
        Consola.mostrarCabecera("BORRAR REVISIÓN");
        try {
            controlador.borrar(Consola.leerRevision());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarClientes() {
        Consola.mostrarCabecera("LISTAR CLIENTES");
        List<Cliente> clientesList = controlador.getClientes();
        if (clientesList.isEmpty()) {
            System.out.println("No se ha encontrado ningún cliente.");
        } else {
            System.out.println(clientesList);
        }
    }

    private void listarVehiculos() {
        Consola.mostrarCabecera("LISTAR VEHÍCULOS");
        List<Vehiculo> vehiculoList = controlador.getVehiculos();
        if (vehiculoList.isEmpty()) {
            System.out.println("No se ha encontrado ningún vehículo.");
        } else {
            System.out.println(vehiculoList);
        }
    }

    private void listarRevisiones() {
        Consola.mostrarCabecera("LISTAR REVISIONES");
        List<Revision> revisionList = controlador.getRevision();
        if (revisionList.isEmpty()) {
            System.out.println("No se ha encontrado ninguna revisión.");
        } else {
            System.out.println(revisionList);
        }
    }

    private void listarRevisionesCliente() {
        Consola.mostrarCabecera("LISTAR REVISIONES DEL CLIENTE");
        List<Revision> clienteList = controlador.getRevisiones(Consola.leerClienteDni());
        if (clienteList.isEmpty()) {
            System.out.println("No se ha encontrado ninguna revisión para ese cliente.");
        } else {
            System.out.println(clienteList);
        }
    }

    private void listarRevisionesVehiculo() {
        List<Revision> vehiculoList = controlador.getRevisiones(Consola.leerVehiculoMatricula());
        if (vehiculoList.isEmpty()) {
            System.out.println("No se ha encontrado ninguna revisión para ese vehículo.");
        } else {
            System.out.println(vehiculoList);
        }
    }

    private void salir() {
        Consola.mostrarCabecera("SALIR");
        controlador.terminar();
    }
}
*/