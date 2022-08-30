package Objetos;

public class Barrio {

    private int X;
    private int Y;
    private int necesidad;
    private String Nombre;
    private int ancho;
    private int alto;
    private int actual;
    private boolean desbordo;
    private boolean insatisfecho;
    public Barrio() {
    }

    public Barrio(int X, int Y, int necesidad, String Nombre) {
        this.X = X;
        this.Y = Y;
        this.actual=0;
        this.necesidad = necesidad;
        this.Nombre = Nombre;
        this.ancho=90;
        this.alto=90;
        desbordo=false;
        insatisfecho=true;
        System.out.println("nombre de ciudad "+Nombre+"   necesidad de la ciudad "+necesidad);
        
        System.out.println("comentario: para calcular el tiempo\n" +
                           " de vaciado del tanque \n" +
                           "utilizamos el teorema de toricelli,\n" +
                           " la rapidez de salidad del agua\n" +
                           "es principalmente una funcion de p\n" +
                           "si el tanque esta abierto a la atmosfera, \n" +
                           "en tal caso \n" +
                           "p=p0  y  v1= √2gh\n" +
                           "donde gravedad=9.8m/s^2\n" +
                           "");
    }

    /**
     * @return the necesidad
     */
    public int getNecesidad() {
        return necesidad;
    }

    /**
     * @param necesidad the necesidad to set
     */
    public void setNecesidad(int necesidad) {
        this.necesidad = necesidad;
    }
    
    /**
     * @return the X
     */
    public int getX() {
        return X;
    }

    /**
     * @param X the X to set
     */
    public void setX(int X) {
        this.X = X;
    }

    /**
     * @return the Y
     */
    public int getY() {
        return Y;
    }

    /**
     * @param Y the Y to set
     */
    public void setY(int Y) {
        this.Y = Y;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     * @return the alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * @param alto the alto to set
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

    /**
     * @return the actual
     */
    public int getActual() {
        return actual;
    }

    /**
     * @param actual the actual to set
     */
    public void setActual(int actual) {
        this.actual = actual;
    }

    /**
     * @return the desbordo
     */
    public boolean isDesbordo() {
        return desbordo;
    }

    /**
     * @param desbordo the desbordo to set
     */
    public void setDesbordo(boolean desbordo) {
        this.desbordo = desbordo;
    }

    /**
     * @return the insatisfecho
     */
    public boolean isInsatisfecho() {
        return insatisfecho;
    }

    /**
     * @param insatisfecho the insatisfecho to set
     */
    public void setInsatisfecho(boolean insatisfecho) {
        this.insatisfecho = insatisfecho;
    }
    /**
     * @return the Tanque
     */
}
