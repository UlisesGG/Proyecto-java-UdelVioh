/*
    CREADOR JAVIER GÓMEZ GALLEGOS
*/
package sistPrestamoRecursos.modelo;


public class RecursoSimple extends Recurso{
    private long valor;

    public RecursoSimple(long valor, int codigo, String nombre, String descripcion, boolean activo) {
        super(codigo, nombre, descripcion, activo);
        this.valor = valor;
    }
    
    @Override
    public long getValor() {
        return this.valor;
    }
    
}
