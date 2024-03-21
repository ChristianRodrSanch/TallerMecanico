/* package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.HashMap;

public enum Opcion {
    INSERTAR_CLIENTE(0, "Insertar cliente"),
    BUSCAR_CLIENTE(1, "Buscar cliente"),
    BORRAR_CLIENTE(2, "Borrar cliente"),
    LISTAR_CLIENTES(3, "Listar clientes"),
    MODIFICAR_CLIENTE(4, "Modificar cliente"),
    INSERTAR_VEHICULO(5, "Insertar vehículo"),
    BUSCAR_VEHICULO(6, "Buscar vehículo"),
    BORRAR_VEHICULO(7, "Borrar vehículo"),
    LISTAR_VEHICULOS(8, "Listar vehículos"),
    INSERTAR_REVISION(9, "Insertar revisión"),
    BUSCAR_RESIVION(10, "Buscar revisión"),
    BORRAR_REVISION(11, "Borrar revisión"),
    LISTAR_REVISIONES(12, "Listar revisiones"),
    LISTAR_REVISIONES_CLIENTE(13, "Listar revisiones de un cliente"),
    LISTAR_REVISIONES_VEHICULO(14, "Listar revisiones de un vehículo"),
    ANADIR_HORAS_REVISION(15, "Añadir horas a una revisión"),
    ANADIR_PRECIO_MATERIAL_REVISION(16, "Añadir precio material a una revisión"),
    CERRAR_REVISION(17, "Cerrar revisión"),
    SALIR(18, "Salir");

    private final int numeroOpcion;
    private final String mensaje;
    private static final HashMap<Integer, Opcion> opciones;

    static {
        opciones = new HashMap<>();
        for (int i = 0; i < Opcion.values().length; i++) {
            opciones.put(i, Opcion.values()[i]);
        }
    }

    Opcion(int numeroOpcion, String mensaje) {
        this.numeroOpcion = numeroOpcion;
        this.mensaje = mensaje;
    }

    public static boolean esValida(int numeroOpcion) {
        return (opciones.containsKey(numeroOpcion));
    }

    public static Opcion get(int numeroOpcion) {
        if (!esValida(numeroOpcion)) {
            throw new IllegalArgumentException("La opción introducida no es válida, inténtelo de nuevo.");
        }
        return opciones.get(numeroOpcion);
    }

    @Override
    public String toString() {
        return String.format("%d. %s", numeroOpcion, mensaje);
    }
}
*/