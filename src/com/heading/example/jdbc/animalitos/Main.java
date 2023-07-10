package com.heading.example.jdbc.animalitos;

import com.heading.example.jdbc.animalitos.model.Animalito;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static Logger logger = Logger.getLogger(Main.class);

    static String URL = "jdbc:h2:~/test";
    static String USER = "sa";
    static String PASS = "";

    static String SQL_CREAR_TABLA = "CREATE TABLE IF NOT EXISTS animales (" +
            "id INT," +
            "nombre VARCHAR(255)," +
            "especie VARCHAR(255)," +
            "raza VARCHAR(255));";

    static String SQL_INSERT = "INSERT INTO animales(id, nombre, especie, raza) VALUES(1, 'Pompilia', 'perro', 'bulldog inglés');";
    static String SQL_INSERT2 = "INSERT INTO animales(id, nombre, especie, raza) VALUES(11, 'Yuma', 'perro', 'Chandoso');";
    static String SQL_INSERT3 = "INSERT INTO animales(id, nombre, especie, raza) VALUES(3, 'Tolo', 'loro', 'cabeza amarilla');";


    public static void main(String[] args){
        // [1] Registrar el Controlador
        try {
            Class.forName("org.h2.Driver");
        }
        catch(ClassNotFoundException ex) {
            logger.error("Error: no se pudo cargar el controlador! " + ex.getMessage());
            System.exit(1);
        }

        List<Animalito> animalitos = new ArrayList<>();

        // [2] - Establecer la conexión utilizando la URL y los datos de autenticación (opciones)
        // [3] - Obtener una instancia de Statement para poder ejecutar sentencias nativas de la base de datos

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()){

            //[4a] - Utilizar el statement creado para insertar datos en la base de datos

            logger.info("Creando la tabla en la base de datos...");
            stmt.execute(SQL_CREAR_TABLA);
            int filasAfectadas = stmt.executeUpdate(SQL_INSERT2);

            logger.info("Filas afectadas: " + filasAfectadas);


            //[4b] - Utilizar ResultSet para obtener los datos provenientes de una consulta SQL
//            ResultSet rs = stmt.executeQuery("SELECT * FROM animales");
//
//            while (rs.next()){
//                Animalito a = new Animalito();
//
//                a.setId(rs.getInt("id"));
//                a.setNombre(rs.getString("nombre"));
//                a.setEspecie(rs.getString("especie"));
//                a.setRaza(rs.getString("raza"));
//
//                animalitos.add(a);
//            }
//
//            animalitos.forEach(a -> System.out.println(a));

            logger.info("Terminamos....");


        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

    }
}
