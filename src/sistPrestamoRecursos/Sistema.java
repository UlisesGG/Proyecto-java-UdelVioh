/*
    CREADOR JAVIER GÓMEZ GALLEGOS
*/
package sistprestamoRecursos;
import sistPrestamoRecursos.controlador.ControladorAmigos;
import sistPrestamoRecursos.controlador.ControladorPrestamos;
import sistPrestamoRecursos.vista.Main;

public class Sistema {
    public static void main(String[] args){
        // TODO code application logic here
        ControladorPrestamos controlador = ControladorPrestamos.getInstancia();
        controlador.leerDatosSistema();
        Main.main(args);
    }
}
