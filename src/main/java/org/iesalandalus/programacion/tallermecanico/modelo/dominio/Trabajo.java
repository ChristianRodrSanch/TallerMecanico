package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public abstract class Trabajo {
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final float FACTOR_DIA = 10f;

    protected Cliente cliente;
    protected Vehiculo vehiculo;
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;
    protected int horas;
    protected float precioMaterial;


    protected Trabajo(Cliente cliente,Vehiculo vehiculo,LocalDate fechaInicio) {

        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        fechaFin = null;
        horas = 0;

    }

    protected Trabajo(Trabajo trabajo) {
        Objects.requireNonNull(trabajo,"El trabajo no puede ser nulo.");
        cliente = trabajo.cliente;
        vehiculo = trabajo.vehiculo;
        fechaInicio = trabajo.fechaInicio;
        fechaFin = trabajo.fechaFin;
        horas = trabajo.horas;
    }

    public  static Trabajo copiar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        Trabajo trabajoCopiado = null;
        if (trabajo instanceof Revision revision) {
            trabajoCopiado = new Revision(revision);
        } else if (trabajo instanceof Mecanico mecanico) {
            trabajoCopiado = new Mecanico(mecanico);
        }
        return trabajoCopiado;
    }

    public static Trabajo get(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        return new Revision(Cliente.get("11111111H"), vehiculo, LocalDate.now());
    }



    public Cliente getCliente() {
        return cliente;
    }

    protected void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    protected void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    protected void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        this.fechaFin = fechaFin;
    }

    public int getHoras() {
        return horas;
    }




    public void anadirHoras(int horas) throws OperationNotSupportedException {
        Objects.requireNonNull(horas, "Las horas añadidas no pueden ser nulas.");
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        } else if (estaCerrado()) {
            throw new OperationNotSupportedException("No se puede añadir horas, ya que el trabajo está cerrado.");
        }
        this.horas += horas;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }



    public boolean estaCerrado() {
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        } else if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        } else if (estaCerrado()) {
            throw new OperationNotSupportedException("El trabajo ya está cerrado.");
        }
        setFechaFin(fechaFin);
    }

    public float getPrecio() {
            return estaCerrado() ? (getPrecioFijo() + getPrecioEspecifico()) : 0;
    }

    private int getDias() {

            return estaCerrado() ? (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin) : 0;
    }

    private float getPrecioFijo() {
            return estaCerrado() ? (float) (getDias() * FACTOR_DIA) : 0;
    }

    public abstract float getPrecioEspecifico();
    @Override
    public boolean equals(Object o) {
        boolean resultado = false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof Mecanico) {
            Mecanico mecanico = (Mecanico) o;
             resultado = Objects.equals(cliente, mecanico.cliente) && Objects.equals(vehiculo, mecanico.vehiculo) && Objects.equals(fechaInicio, mecanico.fechaInicio);
        } else if (o instanceof Revision) {
            Revision revision = (Revision) o;

            resultado = Objects.equals(cliente, revision.cliente) && Objects.equals(vehiculo, revision.vehiculo) && Objects.equals(fechaInicio, revision.fechaInicio);

        }
        return resultado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, vehiculo, fechaInicio);
    }


}
