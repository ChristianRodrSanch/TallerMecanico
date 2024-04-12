package org.iesalandalus.programacion.tallermecanico.modelo.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.vista.FabricaVista;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.ReceptorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.texto.Consola;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Controlador implements IControlador {

    private final Vista vista;
    private final Modelo modelo;

    public Controlador(FabricaModelo fabricaModelo, FabricaFuenteDatos fabricaFuenteDatos, FabricaVista fabricaVista) {
        this.modelo = fabricaModelo.crear(fabricaFuenteDatos);
        this.vista = fabricaVista.crear();
        this.vista.getGestorEventos().suscribir(this,Evento.values());

    }



    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }


    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }


    public void actualizar(Evento evento) {
        String resultado = "";
        try {
            switch (evento) {

                case INSERTAR_CLIENTE -> { modelo.insertar(vista.leerCliente()); resultado = "Cliente insertado correctamente."; }
                case INSERTAR_VEHICULO -> { modelo.insertar(vista.leerVehiculo()); resultado = "Vehículo insertado correctamente"; }
                case INSERTAR_REVISION -> { modelo.insertar(vista.leerRevision()); resultado = "Trabajo de revisión insertado correctamente."; }
                case INSERTAR_MECANICO -> { modelo.insertar(vista.leerMecanico()); resultado = "Trabajo mecánico insertado correctamente."; }
                case BUSCAR_CLIENTE -> vista.mostrarCliente(modelo.buscar(vista.leerClienteDni()));
                case BUSCAR_VEHICULO -> vista.mostrarVehiculo(modelo.buscar(vista.leerVehiculoMatricula()));
                case BUSCAR_TRABAJO -> vista.mostrarTrabajo(modelo.buscar(vista.leerRevision()));
                case MODIFICAR_CLIENTE -> resultado = (modelo.modificar(vista.leerClienteDni(), vista.leerNuevoNombre(), vista.leerNuevoTelefono())) ? "El cliente se ha modificado correctamente." : "El cliente no se ha modificado.";
                case ANADIR_HORAS_TRABAJO -> { modelo.anadirHoras(vista.leerTrabajoVehiculo(), vista.leerHoras()); resultado = "Horas añadidas correctamente."; }
                case ANADIR_PRECIO_MATERIAL_TRABAJO -> { modelo.anadirPrecioMaterial(vista.leerTrabajoVehiculo(), vista.leerPrecioMaterial()); resultado = "Precio del material añadido correctamente."; }
                case CERRAR_TRABAJO -> { modelo.cerrar(vista.leerTrabajoVehiculo(), vista.leerFechaCierre()); resultado = "Trabajo cerrado correctamente."; }
                case BORRAR_CLIENTE -> { modelo.borrar(vista.leerClienteDni()); resultado = "Cliente eliminado correctamente."; }
                case BORRAR_VEHICULO -> { modelo.borrar(vista.leerVehiculoMatricula()); resultado = "Vehículo eliminado correctamente."; }
                case BORRAR_TRABAJO -> { modelo.borrar(vista.leerRevision()); resultado = "Trabajo eliminado correctamente."; }
                case LISTAR_CLIENTES -> vista.mostrarClientes(modelo.getClientes());
                case LISTAR_VEHICULOS -> vista.mostrarVehiculos(modelo.getVehiculos());
                case LISTAR_TRABAJOS -> vista.mostrarTrabajos(modelo.getTrabajos());
                case LISTAR_TRABAJOS_CLIENTE -> vista.mostrarTrabajos(modelo.getTrabajos(vista.leerClienteDni()));
                case LISTAR_TRABAJOS_VEHICULO -> vista.mostrarTrabajos(modelo.getTrabajos(vista.leerVehiculoMatricula()));
                case SALIR -> terminar();
                }
                if (!resultado.isBlank()) {
                    vista.notificarResultado(evento, resultado, true);
                }
            } catch (Exception e) {
                vista.notificarResultado(evento, e.getMessage(), false);
            }
        }

    }