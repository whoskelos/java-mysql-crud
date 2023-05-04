package net.openwebinars.java.mysql.model;

import java.time.LocalDate;
import java.util.Objects;

public class Empleado {
    private int id_empleado;
    private String nombre,apellidos,puesto, email;
    private LocalDate fehcaNacimiento;

    public Empleado() {
    }

    public Empleado(String nombre, String apellidos, String puesto, String email, LocalDate fehcaNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.puesto = puesto;
        this.email = email;
        this.fehcaNacimiento = fehcaNacimiento;
    }

    public Empleado(int id_empleado, String nombre, String apellidos, String puesto, String email, LocalDate fehcaNacimiento) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.puesto = puesto;
        this.email = email;
        this.fehcaNacimiento = fehcaNacimiento;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFehcaNacimiento() {
        return fehcaNacimiento;
    }

    public void setFehcaNacimiento(LocalDate fehcaNacimiento) {
        this.fehcaNacimiento = fehcaNacimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return id_empleado == empleado.id_empleado && Objects.equals(nombre, empleado.nombre) && Objects.equals(apellidos, empleado.apellidos) && Objects.equals(puesto, empleado.puesto) && Objects.equals(email, empleado.email) && Objects.equals(fehcaNacimiento, empleado.fehcaNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_empleado, nombre, apellidos, puesto, email, fehcaNacimiento);
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id_empleado=" + id_empleado +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", puesto='" + puesto + '\'' +
                ", email='" + email + '\'' +
                ", fehcaNacimiento=" + fehcaNacimiento +
                '}';
    }
}
