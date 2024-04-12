package org.iesalandalus.programacion.tallermecanico;


import org.iesalandalus.programacion.tallermecanico.modelo.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.controlador.IControlador;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.vista.FabricaVista;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;

public class Main {
    public static void main(String[] args) {
        // Crea una nueva fuente de datos con la memoria
        IControlador controlador = new Controlador(FabricaModelo.CASCADA.crear(FabricaFuenteDatos.MEMORIA),FabricaVista.TEXTO.crear());
        controlador.comenzar();
    }
}

