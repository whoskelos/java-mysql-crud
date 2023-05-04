package net.openwebinars.java.mysql;

import net.openwebinars.java.mysql.dao.EmpleadoDao;
import net.openwebinars.java.mysql.dao.EmpleadoDaoImpl;
import net.openwebinars.java.mysql.model.Empleado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Menu {
    private KeyboardReader reader;
    private EmpleadoDao dao;

    public Menu() {
        reader = new KeyboardReader();
        dao = EmpleadoDaoImpl.getInstance();
    }

    public void init() {

        int opcion;

        do {
            menu();
            opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    listAll();
                    break;
                case 2:
                    break;
                case 3:
                    insert();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.err.println("\nEl numero introducido no se corresponde con una opcion valida.");

            }


        } while (opcion != 0);


    }

    public void menu() {

        System.out.println("SISTEMA DE GESTION DE EMPLEADOS");
        System.out.println("================================================================\n");
        System.out.println("-> Introduzca una opcion de entre las siguientes");
        System.out.println("0: Salir");
        System.out.println("1: Listar todos los empleados");
        System.out.println("2: Listar un empleado por su ID");
        System.out.println("3: Insertar un nuevo empleado");
        System.out.println("4: Actualizar un empleado");
        System.out.println("5: Eliminar un empleado");
        System.out.print("\nOpcion: ");

    }

    public void insert() {
        System.out.println("\nINSERCION DE UN NUEVO EMPLEADO");
        System.out.println("-------------------------");

        System.out.print("Introduzca el nombre (sin apellidos) del empleado: ");
        String nombre = reader.nextLine();

        System.out.print("Introduzca los apellidos del empleado: ");
        String apellidos = reader.nextLine();

        System.out.print("Introduzca la fecha de nacimiento del empleado (formato dd/MM//aaaa): ");
        LocalDate fechaNacimiento = reader.nextLocalDate();

        System.out.print("Introduzca el puesto del empleado: ");
        String puesto = reader.nextLine();

        System.out.print("Introduzca el email del empleado: ");
        String email = reader.nextLine();

        try {
            dao.add(new Empleado(nombre, apellidos, puesto, email, fechaNacimiento));
            System.out.println("Nuevo empleado registrado");
        } catch (SQLException e) {
            System.err.println("Error insertando el nuevo registro en la base de datos. Vuelva a intentarlo de nuevo o contacte al admin.");
        }

        System.out.println("");
    }

    public void listAll() {
        System.out.println("\nLISTADO DE TODOS LOS EMPLEADOS");
        System.out.println("--------------------------------");

        printCabeceraTablaEmpleado();


        try {
            List<Empleado> result =  dao.getAll();

            if (result.isEmpty()) {
                System.out.println("No hay empleados registrados en la base de datos.");
            } else {
                printCabeceraTablaEmpleado();
                result.forEach(this::printEmpleado);

            }
        } catch (SQLException e) {
            System.out.println("Error al consultar en la base de datos. Vuelva a intentarlo o contacte con el admin.");
        }

        System.out.println("\n");

    }

    private void printCabeceraTablaEmpleado() {
        System.out.printf("%2s %30s %8s %10s %25s", "ID", "NOMBRE", "FEC. NAC.", "PUESTO", "EMAIL");
        System.out.println("");
        IntStream.range(1, 100).forEach(x -> System.out.print("-"));
        System.out.println("\n");
    }

    private void printEmpleado(Empleado emp) {
        System.out.printf("%2s %30s %9s %20s %25s\n",
                emp.getId_empleado(),
                emp.getNombre() + " " + emp.getApellidos(),
                emp.getFehcaNacimiento().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                emp.getPuesto(),
                emp.getEmail()
        );
    }


    static class KeyboardReader {
        BufferedReader br;
        StringTokenizer st;

        public KeyboardReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException ex) {
                    System.err.println("Error leyendo teclado");
                    ex.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        LocalDate nextLocalDate() {
            return LocalDate.parse(next(),
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        String nextLine() {
            String str = "";
            try {
                if (st.hasMoreElements()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException ex) {
                System.err.println("Error leyendo teclado");
                ex.printStackTrace();
            }

            return str;
        }
    }

}
