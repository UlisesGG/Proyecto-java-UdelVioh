/*
    CREADOR JAVIER GÓMEZ GALLEGOS
*/
package sistPrestamoRecursos.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import javax.swing.JOptionPane;


public abstract class Recurso implements java.io.Serializable{
    private int codigo;
    private String nombre;
    private String descripcion;
    private boolean activo;
    ArrayList<Prestamo> prestamos;

    public Recurso(int codigo, String nombre, String descripcion, boolean activo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
        prestamos=new ArrayList<>();
    }

    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    @Override
    public String toString() {
        return "(codigo: "+this.getCodigo()+") "+this.getNombre();
    }
    public void addPrestamo(Prestamo prestamo){
        if(!prestamos.contains(prestamo)){
            prestamos.add(prestamo);
        }
    }
    public boolean tienePrestamo(){
        return prestamos==null || prestamos.size()==0; 
    }
    public Prestamo getUltimoPrestamo(){
        if(this.tienePrestamo()){
            return prestamos.get(prestamos.size());
        }
        return null;
    }
    public boolean estaPrestado(){
        for(int i=0; i<prestamos.size(); i++){
            if(prestamos.get(i).estaPendiente()){
                return true;
            }
        }
        return false;
    }
    public Prestamo getPrestamo(int pos){
        return prestamos.get(pos);
    }
    public int getNroPrestamos(){
        return prestamos.size();
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
        final Recurso other = (Recurso) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (this.activo != other.activo) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    public abstract long getValor();
}
