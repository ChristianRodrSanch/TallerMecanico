package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Objects;

public class Mecanico extends Trabajo{

    private static final float FACTOR_HORA = 30f;
    private static final float FACTOR_PRECIO_MATERIAL = 1.5f;



    protected Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
    }

    protected Mecanico(Mecanico mecanico) {
        super(mecanico);
        Objects.requireNonNull(mecanico, "La revisión no puede ser nula.");
        cliente = new Cliente(mecanico.cliente);
        vehiculo = mecanico.vehiculo;
        fechaInicio = mecanico.fechaInicio;
        horas = mecanico.horas;
        precioMaterial = mecanico.precioMaterial;
        fechaFin = mecanico.fechaFin;
    }

    @Override
    public float getPrecioMaterial() {
        return super.getPrecioMaterial();
    }

    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if (precioMaterial <= 0f) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        if (getFechaFin() != null) {
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que el trabajo mecánico está cerrado.");
        }
        this.precioMaterial += precioMaterial;
    }

    @Override
    public float getPrecioEspecifico() {
        return (getHoras() * FACTOR_HORA) + (precioMaterial * FACTOR_PRECIO_MATERIAL );
    }

    @Override
    public String toString() {
        return "Mecánico -> " + super.toString();
    }
}
