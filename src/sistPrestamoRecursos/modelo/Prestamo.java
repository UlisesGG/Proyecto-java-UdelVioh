/*
    CREADOR JAVIER GÓMEZ GALLEGOS
*/
package sistPrestamoRecursos.modelo;

import java.time.LocalDate;
import javax.swing.JOptionPane;

public class Prestamo implements java.io.Serializable{
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private LocalDate fechaDevolucionReal=null;
    private Recurso recurso;
    private Amigo prestatario;

    public Prestamo(LocalDate fechaPrestamo, LocalDate fechaDevolucion, Recurso recurso, Amigo prestatario) {
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.recurso = recurso;
        this.prestatario = prestatario;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public LocalDate getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public Amigo getPrestatario() {
        return prestatario;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public void setPrestatario(Amigo prestatario) {
        this.prestatario = prestatario;
    }
    
    public boolean estaPendiente(){
        return this.getFechaDevolucionReal()==null;
    }
    public void devolucion(LocalDate fDevReal){
        this.fechaDevolucionReal=fDevReal;
        JOptionPane.showMessageDialog(null, "Se ha devuelto el recurso");
    }
    public int getDiaRetrasoPorNoDevolucion(){
        return 0;
    }
}
