/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author usuario
 */
public class Tanque {
    private int capacidad_maxima;
    private int agua;
    private int X;
    private int Y;
    private int ancho;
    private int alto;
    private boolean desbordo;
   private int  precioBruto ;
   private int manoObra;
   private int costoTotal;
    public Tanque() {
    }

    public Tanque( int capacidad_maxima,  int X, int Y) {
        this.capacidad_maxima = capacidad_maxima;
        this.agua=capacidad_maxima;
        this.X = X;
        this.Y = Y;
        this.ancho=70;
        this.alto=70;
        this.precioBruto=20*capacidad_maxima;
        this.manoObra=30*capacidad_maxima;
        this.costoTotal=this.precioBruto+this.manoObra;
        desbordo=false;
        
        System.out.println("capacidad maxima del tanque "+capacidad_maxima);
             
    }

    /**
     * @return the capacidad_maxima
     */
    public int getCapacidad_maxima() {
        return capacidad_maxima;
    }

    /**
     * @param capacidad_maxima the capacidad_maxima to set
     */
    public void setCapacidad_maxima(int capacidad_maxima) {
        this.capacidad_maxima = capacidad_maxima;
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
     * @return the agua
     */
    public int getAgua() {
        return agua;
    }

    /**
     * @param agua the agua to set
     */
    public void setAgua(int agua) {
        this.agua = agua;
    }

    /**
     * @return the precioBruto
     */
    public int getPrecioBruto() {
        return precioBruto;
    }

    /**
     * @param precioBruto the precioBruto to set
     */
    public void setPrecioBruto(int precioBruto) {
        this.precioBruto = precioBruto;
    }

    /**
     * @return the manoObra
     */
    public int getManoObra() {
        return manoObra;
    }

    /**
     * @param manoObra the manoObra to set
     */
    public void setManoObra(int manoObra) {
        this.manoObra = manoObra;
    }

    /**
     * @return the costoTotal
     */
    public int getCostoTotal() {
        return costoTotal;
    }

    /**
     * @param costoTotal the costoTotal to set
     */
    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
    }
}
