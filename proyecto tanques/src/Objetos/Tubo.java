
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author usuario
 */
public class Tubo {

    private int capacidad;
    private int transportando;
    private int Xini;
    private int Xfin;
    private int Yini;
    private int Yfin;
    private LinkedList<Point> agua;
    private int distX;
    private int distY;
    private double m;
    private Point a;
    private boolean obst;
    private boolean alterna;
    private int precioBruto;
    private int manoObra;
    private int costoTotal;

    public Tubo(int capacidad, int transportando, int Xini, int Xfin, int Yini, int Yfin) {
        this.capacidad = capacidad;
        this.transportando = transportando;
        this.Xini = Xini;
        this.Xfin = Xfin;
        this.Yini = Yini;
        this.Yfin = Yfin;
        this.distX = this.Xfin - this.Xini;
        this.distY = this.Yfin - this.Yini;
        this.m = (double) distY / distX;
        agua = new LinkedList<>();
        a = new Point();
        obst = false;
        this.precioBruto = 10 * capacidad;
        this.manoObra = 15 * capacidad;
        this.costoTotal = this.precioBruto + this.manoObra;
        transportar();
        System.out.println("precio bruto "+this.precioBruto+" capacidad "+capacidad+"  mano de obra "+this.manoObra+"  costo total "+this.costoTotal);
    }

    /**
     * @return the capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * @return the transportando
     */
    public int getTransportando() {
        return transportando;
    }

    /**
     * @param transportando the transportando to set
     */
    public void setTransportando(int transportando) {
        this.transportando = transportando;
    }

    /**
     * @return the Xini
     */
    public int getXini() {
        return Xini;
    }

    /**
     * @param Xini the Xini to set
     */
    public void setXini(int Xini) {
        this.Xini = Xini;
    }

    /**
     * @return the Xfin
     */
    public int getXfin() {
        return Xfin;
    }

    /**
     * @param Xfin the Xfin to set
     */
    public void setXfin(int Xfin) {
        this.Xfin = Xfin;
    }

    /**
     * @return the Yini
     */
    public int getYini() {
        return Yini;
    }

    /**
     * @param Yini the Yini to set
     */
    public void setYini(int Yini) {
        this.Yini = Yini;
    }

    /**
     * @return the Yfin
     */
    public int getYfin() {
        return Yfin;
    }

    /**
     * @param Yfin the Yfin to set
     */
    public void setYfin(int Yfin) {
        this.Yfin = Yfin;
    }

    /**
     * @return the agua
     */
    public LinkedList<Point> getAgua() {
        return agua;
    }

    private void transportar() {
        Thread h = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean fir = true;
                int x = getXini();

                while (true) {
                    try {
                        if (!isObst()) {
                            if (getTransportando() > 0) {
                                if (getXfin() > getXini()) {
                                    while (x <= getXfin()) {
                                        if (isObst()) {
                                            break;
                                        }
                                        int y = (int) ((getM()) * (x - getXini())) + getYini();
                                        Point a1 = new Point(x - 5, y - 5);
                                        getAgua().add(a1);
                                        x += 1;
                                        Thread.sleep(70);
                                    }
                                    if (x >= getXfin()) {
                                        x = getXini();
                                        setAgua(new LinkedList<Point>());
                                    }
                                } else {
                                    while (x >= getXfin()) {
                                        if (isObst()) {
                                            break;
                                        }
                                        int y = (int) ((getM()) * (x - getXini())) + getYini();
                                        Point a1 = new Point(x - 5, y - 5);
                                        getAgua().add(a1);
                                        x -= 1;
                                        Thread.sleep(70);
                                    }
                                    if (x <= getXfin()) {
                                        x = getXini();
                                        setAgua(new LinkedList<Point>());
                                    }
                                }
                            } else {
                                setAgua(new LinkedList<Point>());
                            }

                        } else {
                            setAgua(new LinkedList<Point>());
                        }

                        Thread.sleep(200);
                    } catch (Exception e) {
                        System.out.println("no se pudo crear la bolita");
                    }
                }
            }
        });
        h.start();
    }

    /**
     * @return the distX
     */
    public int getDistX() {
        return distX;
    }

    /**
     * @param distX the distX to set
     */
    public void setDistX(int distX) {
        this.distX = distX;
    }

    /**
     * @return the distY
     */
    public int getDistY() {
        return distY;
    }

    /**
     * @param distY the distY to set
     */
    public void setDistY(int distY) {
        this.distY = distY;
    }

    /**
     * @return the a
     */
    public Point getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(Point a) {
        this.a = a;
    }

    /**
     * @return the obst
     */
    public boolean isObst() {
        return obst;
    }

    /**
     * @param obst the obst to set
     */
    public void setObst(boolean obst) {
        this.obst = obst;
    }

    /**
     * @return the m
     */
    public double getM() {
        return m;
    }

    /**
     * @param m the m to set
     */
    public void setM(double m) {
        this.m = m;
    }

    /**
     * @return the alterna
     */
    public boolean isAlterna() {
        return alterna;
    }

    /**
     * @param alterna the alterna to set
     */
    public void setAlterna(boolean alterna) {
        this.alterna = alterna;
    }

    /**
     * @param agua the agua to set
     */
    public void setAgua(LinkedList<Point> agua) {
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
