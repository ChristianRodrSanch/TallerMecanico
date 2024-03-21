package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;

public interface ITrabajos {

    public List<Trabajo> get();

    public List<Trabajo> get(Cliente cliente);

    public List<Trabajo> get(Vehiculo vehiculo);

    public void insertar (Trabajo trabajo) throws OperationNotSupportedException;

    public void añadirHoras(int horas,Trabajo trabajo);

    public void añadirPrecioMaterial(Trabajo trabajo,float precioMaterial);

    public void cerrar (LocalDate fechaFin, Trabajo trabajo);

    void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException;

    void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException;

    void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException;

    public Trabajo buscar(Trabajo trabajo);

    public void borrar(Trabajo trabajo) throws OperationNotSupportedException;
}
