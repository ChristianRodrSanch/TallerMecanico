package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.HashMap;
import java.util.Map;

public enum Evento {
    INSERTAR_CLIENTE(0, "Insertar cliente"),
    BUSCAR_CLIENTE(1, "Buscar cliente"),
    BORRAR_CLIENTE(2, "Borrar cliente"),
    LISTAR_CLIENTES(3, "Listar clientes"),
    MODIFICAR_CLIENTE(4, "Modificar cliente"),
    INSERTAR_VEHICULO(5, "Insertar veh�culo"),
    BUSCAR_VEHICULO(6, "Buscar veh�culo"),
    BORRAR_VEHICULO(7, "Borrar veh�culo"),
    LISTAR_VEHICULOS(8, "Listar veh�culos"),
    INSERTAR_REVISION(9, "Insertar revisi�n"),
    INSERTAR_MECANICO(10,"Insertar mecanico"),
    BUSCAR_TRABAJO(11, "Buscar trabajo"),
    BORRAR_TRABAJO(12, "Borrar trabajo"),
    LISTAR_TRABAJOS(13, "Listar trabajos"),
    LISTAR_TRABAJOS_CLIENTE(14, "Listar trabajos de un cliente"),
    LISTAR_TRABAJOS_VEHICULO(15, "Listar trabajos de un veh�culo"),
    ANADIR_HORAS_TRABAJO(16, "A�adir horas a un trabajo"),
    ANADIR_PRECIO_MATERIAL_TRABAJO(17, "A�adir precio material a un trabajo"),
    CERRAR_TRABAJO(18, "Cerrar trabajo"),
    MOSTRAR_ESTADISTICAS_MENSUALES (19,"Mostrar estadisticas mensauales"),
    SALIR(20, "Salir");

    private final int codigo;
    private final String texto;
    private static final Map<Integer, Evento> eventos = new HashMap<>();

    static {
        for (Evento evento : values()) {
            eventos.put(evento.codigo, evento);
        }
    }

    private Evento(int codigo, String texto) {
        this.codigo = codigo;
        this.texto = texto;
    }

    public int getCodigo() {
        return codigo;
    }

    public static boolean esValido(int codigo) {
        return eventos.containsKey(codigo);
    }

    public static Evento get(int codigo) {
        if (!esValido(codigo)) {
            throw new IllegalArgumentException("El c�digo no es correcto.");
        }
        return eventos.get(codigo);
    }

    @Override
    public String toString() {
        return texto;
    }
}
