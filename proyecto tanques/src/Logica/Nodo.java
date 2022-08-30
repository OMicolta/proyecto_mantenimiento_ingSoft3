package Logica;
import Objetos.Barrio;
import Objetos.Tanque;
public class Nodo {
    public Barrio barrio;
    public Tanque tanque;
    public boolean tan;
    private  String ID;

    public Nodo(Barrio barrio) {
        this.barrio = barrio;
        tan=false;
    }
    public Nodo(Barrio barrio, String ID) {
        this.barrio = barrio;
        tan=false;
        this.ID=ID;
    }

    public Nodo(Tanque tanque) {
        this.tanque = tanque;
        tan=true;
    }
    public Nodo(Tanque tanque, String ID) {
        this.tanque = tanque;
        tan=true;
        this.ID=ID;
    }

    /**
     * @return the barrio
     */
    public Barrio getBarrio() {
        return barrio;
    }

    /**
     * @param barrio the barrio to set
     */
    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    /**
     * @return the tanque
     */
    public Tanque getTanque() {
        return tanque;
    }

    /**
     * @param tanque the tanque to set
     */
    public void setTanque(Tanque tanque) {
        this.tanque = tanque;
    }

    /**
     * @return the tan
     */
    public boolean isTan() {
        return tan;
    }

    /**
     * @param tan the tan to set
     */
    public void setTan(boolean tan) {
        this.tan = tan;
    }

    /**
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(String ID) {
        this.ID = ID;
    }

}