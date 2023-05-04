package net.openwebinars.java.mysql;

import net.openwebinars.java.mysql.dao.EmpleadoDao;
import net.openwebinars.java.mysql.dao.EmpleadoDaoImpl;
import net.openwebinars.java.mysql.model.Empleado;
import net.openwebinars.java.mysql.pool.MyDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        Menu menu = new Menu();
        menu.init();

    }

    public static void testDao() {
        EmpleadoDao dao = EmpleadoDaoImpl.getInstance();

        Empleado emp = new Empleado("Adrian","Garzon",
                "Developer","adriangarzonp@gmail.com",LocalDate.of(2001,5,6));

        try {
            int n = dao.add(emp);
            System.out.println("El numero de registros insertados es:" + n);

            List<Empleado> empleados = dao.getAll();

            if (empleados.isEmpty()) {
                System.out.println("No hay empleados registrados");
            } else {
                empleados.forEach(System.out::println);
            }

            Empleado emp1 = dao.getById(1);
            System.out.println(" ");
            System.out.println(emp1);
            System.out.println(" ");

            emp1.setFechaNacimiento(LocalDate.of(1999,1,21));

            n = dao.update(emp1);

            emp = dao.getById(1);

            dao.delete(1);

            empleados = dao.getAll();
            if (empleados.isEmpty()) {
                System.out.println("No hay empleados registrados");
            } else {
                empleados.forEach(System.out::println);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testPool() {
        try (Connection conn = MyDataSource.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            String[] types = {"TABLE"};
            ResultSet tables = metaData.getTables(null, null, "%", types);
            while (tables.next()) {
                System.out.println(tables.getString("TABLE_NAME"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
