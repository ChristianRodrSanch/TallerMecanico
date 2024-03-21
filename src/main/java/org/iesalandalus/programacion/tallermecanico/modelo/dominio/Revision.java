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
        return  (FACTOR_HORA * getHoras());
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

    public String toString() {
        String mensaje;
        if (estaCerrado()) {
            mensaje = String.format("Revisión -> %s - %s (%s - %s): %s horas, %.2f € total", this.cliente, this.vehiculo, FORMATO_FECHA.format(this.fechaInicio), FORMATO_FECHA.format(this.fechaFin), this.horas,getPrecio());
        } else {
            mensaje = String.format("Revisión -> %s - %s (%s - ): %s horas", this.cliente, this.vehiculo, FORMATO_FECHA.format(this.fechaInicio), this.horas);
        }
        return mensaje;

    }
}
