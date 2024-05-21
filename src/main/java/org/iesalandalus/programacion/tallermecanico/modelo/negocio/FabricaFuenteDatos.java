package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.FuenteDatosFicheros;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.mariadb.FuenteDatosMariadb;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.mongodb.FuenteDatosMongodb;


public enum FabricaFuenteDatos {
    FICHEROS {
        @Override
        public IFuenteDatos crear() {
            return new FuenteDatosFicheros();
        }
    },MARIADB {
        @Override
        public IFuenteDatos crear() {
            return new FuenteDatosMariadb();
        }
    },MONGODB {
        @Override
        public  IFuenteDatos crear() {
            return new FuenteDatosMongodb();
        }
    };



    public abstract IFuenteDatos crear();
}
