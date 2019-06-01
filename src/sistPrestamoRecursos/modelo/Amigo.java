/*
    CREADOR JAVIER GÓMEZ GALLEGOS
*/
package sistPrestamoRecursos.modelo;

import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JOptionPane;


public class Amigo implements java.io.Serializable {
    private String rut;
    private String nombre;
    private String email;
    private String fono;
    ArrayList<Prestamo> prestamos;

    public Amigo(String rut, String nombre, String email, String fono) {
        this.rut = rut;
        this.nombre = nombre;
        this.email = email;
        this.fono = fono;
        prestamos=new ArrayList<>();
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getFono() {
        return fono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }
    public int getNroPrestamos(){
        return prestamos.size();
    }
    public boolean tienePrestamos(){
        return prestamos.size()>0;
    }
    public Prestamo getPrestamo(int pos){
        return prestamos.get(pos);
    }
    public Prestamo getUltimoPrestamo(){
        return prestamos.get(prestamos.size()-1);
    }
    public boolean tienePrestamoPendiente(){
        for(Prestamo prestamos:prestamos){
            if(prestamos.getFechaDevolucion()!=null){
                return true;
            }
        }
        return false;
    }
    public void addPrestamo(Prestamo prestamo){
        if(!prestamos.contains(prestamo)){
            prestamos.add(prestamo);
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Amigo other = (Amigo) obj;
        if (!Objects.equals(this.rut, other.rut)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.fono, other.fono);
    }
    
}
