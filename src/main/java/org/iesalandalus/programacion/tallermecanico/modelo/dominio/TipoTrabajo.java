package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

public enum TipoTrabajo {
    MECANICO("Mecanico"),
    REVISION("Revision");

    final String nombre;

    TipoTrabajo (String nombre){
        this.nombre = nombre;

    }

    public static TipoTrabajo get(Trabajo trabajo) {
        if (trabajo instanceof Mecanico) {
            return MECANICO;
        } else if (trabajo instanceof  Revision){
            return REVISION;

        }else {
            throw new IllegalArgumentException("El trabajo pasado no es valido ");
        }
    }
}
