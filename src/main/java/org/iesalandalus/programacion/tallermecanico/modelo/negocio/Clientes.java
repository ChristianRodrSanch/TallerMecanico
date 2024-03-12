package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes implements IClientes {
    private final List<Cliente> coleccionClientes;
    public Clientes() {
        coleccionClientes = new ArrayList<>();
    }
    @Override
    public List<Cliente> get(){
        return new ArrayList<>(coleccionClientes);
    }
    @Override
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        Cliente nuevo = buscar(cliente);
        if (nuevo != null) {
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        coleccionClientes.add(cliente);
    }

    @Override
    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        boolean hecho = false;
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        Cliente nuevo = buscar(cliente);
        if (nuevo == null) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        if (!(nombre == null || nombre.isBlank())) {
            nuevo.setNombre(nombre);
            hecho = true;
        }
        if (!(telefono == null || telefono.isBlank())) {
            nuevo.setTelefono(telefono);
            hecho = true;
        }
        return hecho;
    }

    @Override
    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        int indice = coleccionClientes.indexOf(cliente);
        return (indice == -1) ? null : coleccionClientes.get(indice);
    }

    @Override
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        Cliente nuevo = buscar(cliente);
        if (nuevo == null) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        coleccionClientes.remove(cliente);
    }
}
