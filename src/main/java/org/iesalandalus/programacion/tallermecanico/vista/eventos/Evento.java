 package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.HashMap;

public enum Evento {
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
    INSERTAR_MECANICO(10,"Insertar mecanico"),
    BUSCAR_TRABAJO(11, "Buscar trabajo"),
    BORRAR_TRABAJO(12, "Borrar trabajo"),
    LISTAR_TRABAJOS(13, "Listar trabajos"),
    LISTAR_TRABAJOS_CLIENTE(14, "Listar trabajos de un cliente"),
    LISTAR_TRABAJOS_VEHICULO(15, "Listar trabajos de un vehículo"),
    ANADIR_HORAS_TRABAJO(16, "Añadir horas a un trabajo"),
    ANADIR_PRECIO_MATERIAL_TRABAJO(17, "Añadir precio material a un trabajo"),
    CERRAR_TRABAJO(18, "Cerrar trabajo"),
    SALIR(19, "Salir");

    private final int codigo;
    private final String texto;
    private static final HashMap<Integer, Evento> evento;

    static {
        evento = new HashMap<>();
        for (int i = 0; i < Evento.values().length; i++) {
            evento.put(i, Evento.values()[i]);
        }
    }

    private Evento(int codigo, String texto) {
        this.codigo = codigo;
        this.texto = texto;
    }

    public void esValida(int codigo) {

        evento.containsKey(codigo);

    }

    public  Evento get(int codigo) {
        return evento.get(codigo);
    }

    @Override
    public String toString() {
        return String.format("%d. %s", codigo, texto);
    }
}
