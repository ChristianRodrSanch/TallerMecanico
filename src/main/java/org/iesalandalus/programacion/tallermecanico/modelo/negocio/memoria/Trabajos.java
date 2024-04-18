package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;

import javax.naming.OperationNotSupportedException;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Trabajos implements ITrabajos {

    private static final String FICHEROS_TRABAJOS =  String.format("%s%s%s","ficheros", File.separator,"trabajos.xml");

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private  static final String RAIZ = "trabajos";

    private static final String TRABAJO =  "trabajo";

    private static final String CLIENTE =  "cliente";

    private static final String VEHICULO =  "vehiculo";

    private static final String FECHA_INICIO = "fechaInicio";

    private static final String FECHA_FIN = "fechaFin";

    private static final String HORAS = "horas";

    private static final String PRECIO_MATERIAL = "precioMaterial";

    private static final String TIPO = "tipo";

    private static final String REVISION = "revision";

    private static final String MECANICO = "mecanico";

    private final List<Trabajo> coleccionTrabajos;

    public Trabajos() {
        coleccionTrabajos = new ArrayList<>();
    }

    @Override
    public List<Trabajo> get() {
        return new ArrayList<>(coleccionTrabajos);
    }

    @Override
    public List<Trabajo> get(Cliente cliente) { // Los trabajos para ese cliente
        List<Trabajo> revisionTrabajo = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getCliente().equals(cliente)) {
                revisionTrabajo.add(trabajo);
            }
        }
        return revisionTrabajo;
    }

    public Map<TipoTrabajo,Integer> getEstadisticasMensuales(LocalDate mes) {
        Map<TipoTrabajo, Integer> estadisticas = inicializarEstadisticas(); // declaro el mapa y le digo que es igual al metodo iniciazliar estadisticas

        for(Trabajo trabajo : coleccionTrabajos) {
            LocalDate fechaTrabajo = trabajo.getFechaFin();
            if (fechaTrabajo.getMonth().equals(mes.getMonth()) && fechaTrabajo.getYear() == mes.getYear()) {
                TipoTrabajo tipoTrabajo = TipoTrabajo.get(trabajo);
                estadisticas.put(tipoTrabajo,estadisticas.get(tipoTrabajo) + 1); // el +1 es  basicamente contar .

            }
        }

        return estadisticas;
    }

    private Map<TipoTrabajo,Integer> inicializarEstadisticas() { // esto inicializa el mapa y pone sus valores a 0
        Map<TipoTrabajo,Integer> estadisticas = new HashMap<>();
        for (TipoTrabajo trabajo : TipoTrabajo.values()) {
            estadisticas.put(trabajo,0);
        }
        return  estadisticas;
    }


    @Override
    public List<Trabajo> get(Vehiculo vehiculo) { // Los trabajos para vehículo
        List<Trabajo> revisionTrabajo = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getVehiculo().equals(vehiculo)) {
                revisionTrabajo.add(trabajo);
            }
        }
        return revisionTrabajo;
    }

    @Override
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede insertar un trabajo nulo.");
        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        coleccionTrabajos.add(trabajo);
    }



    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void comprobarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws OperationNotSupportedException {
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getCliente().equals(cliente) && !trabajo.estaCerrado()) {
                throw new OperationNotSupportedException("El cliente tiene otro trabajo en curso.");
            }
            if (trabajo.getVehiculo().equals(vehiculo) && !trabajo.estaCerrado()) {
                throw new OperationNotSupportedException("El vehículo está actualmente en el taller.");
            }
            if (trabajo.estaCerrado() && trabajo.getCliente().equals(cliente) && !fechaRevision.isAfter(trabajo.getFechaFin())) {
                throw new OperationNotSupportedException("El cliente tiene otro trabajo posterior.");
            }
            if (trabajo.estaCerrado() && trabajo.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(trabajo.getFechaFin())) {
                throw new OperationNotSupportedException("El vehículo tiene otro trabajo posterior.");
            }
        }
    }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo añadir horas a un trabajo nulo.");
        if (getTrabajoAbierto(trabajo.getVehiculo()) == null) {
            throw new OperationNotSupportedException("No existe ningún trabajo abierto para dicho vehículo.");
        }
        trabajo.anadirHoras(horas);
    }

    private Trabajo getTrabajoAbierto(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        boolean trabajoEncontrado = false;
        Trabajo trabajoADevolver = null;
        for (int i = 0; i < coleccionTrabajos.size() && !trabajoEncontrado; i++) {
            if (!coleccionTrabajos.get(i).estaCerrado() && coleccionTrabajos.get(i).getVehiculo().equals(vehiculo)) {
                trabajoADevolver = coleccionTrabajos.get(i);
                trabajoEncontrado = true;
            }
        }
        return trabajoADevolver;
    }

    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo añadir precio del material a un trabajo nulo.");
        if (getTrabajoAbierto(trabajo.getVehiculo()) == null) {
            throw new OperationNotSupportedException("No existe ningún trabajo abierto para dicho vehículo.");
        }
        if (trabajo instanceof Revision) {
            throw new OperationNotSupportedException("No se puede añadir precio al material para este tipo de trabajos.");
        } else if (trabajo instanceof Mecanico mecanico) {
            mecanico.anadirPrecioMaterial(precioMaterial); // Convierte el trabajo que me pasan en uno mecánico
        }
    }

    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo cerrar un trabajo nulo.");
        if (getTrabajoAbierto(trabajo.getVehiculo()) == null) {
            throw new OperationNotSupportedException("No existe ningún trabajo abierto para dicho vehículo.");
        }
        trabajo.cerrar(fechaFin);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "No se puede buscar un trabajo nulo.");
        int indice = coleccionTrabajos.indexOf(trabajo);
        return (indice == -1) ? null : coleccionTrabajos.get(indice);
    }

    @Override
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede borrar un trabajo nulo.");
        if (buscar(trabajo) == null) {
            throw new OperationNotSupportedException("No existe ningún trabajo igual.");
        }
        coleccionTrabajos.remove(trabajo);
    }
}