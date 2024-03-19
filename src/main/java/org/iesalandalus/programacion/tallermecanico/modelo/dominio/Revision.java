package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Revision extends Trabajo {

    private static final float FACTOR_HORA = 35f;


    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente,vehiculo,fechaInicio);
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
    }

    @Override
    public float getPrecioEspecifico() {
        return (FACTOR_HORA * getHoras());
    }

    public Revision(Revision revision) {
        super(revision);
        Objects.requireNonNull(revision, "La revisión no puede ser nula.");
        cliente = new Cliente(revision.cliente);
        vehiculo = revision.vehiculo;
        fechaInicio = revision.fechaInicio;
        horas = revision.horas;
        precioMaterial = revision.precioMaterial;
        fechaFin = revision.fechaFin;
    }

    @Override
    public String toString() {
        return "Revisión -> " + super.toString();
    }
}
