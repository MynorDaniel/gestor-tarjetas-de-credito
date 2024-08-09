package backend;

/**
 * Clase que gestiona las instrucciones realizadas por el usuario y las devuelve para
 * que la clase Gestion se encargue de ellas.
 * Cuando las instrucciones ingresan por un archivo de texto, esta clase se encarga de
 * leer el archivo y devolver las instrucciones.
 * Cuando las instrucciones ingresan por la interfaz gráfica, esta clase se encarga de
 * simular las instrucciones que vendrian en un archivo y realizar el mismo proceso.
 */
public class Archivo {
    private String pathEntrada;
    private String contenido;
    private String[] instrucciones;

    private void separarContenido() {
        // Se separa el contenido del archivo en instrucciones
    }

    public String[] instrucciones() {
        return instrucciones;
    }

    public void setPathEntrada(String pathEntrada) {
        this.pathEntrada = pathEntrada;
    }
}
