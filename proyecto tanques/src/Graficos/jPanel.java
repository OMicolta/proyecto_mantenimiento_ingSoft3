package Graficos;

import Logica.Acueducto;
import Logica.Nodo;
import Objetos.Tubo;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.ImageIcon;

public class jPanel extends javax.swing.JPanel {

    private Acueducto ciudad;
    private int saldo;
    private LinkedList<Tubo> L;
    private LinkedList<Nodo> tanques;

    public jPanel() {
        initComponents();
        ciudad = new Acueducto();
        L = new LinkedList<Tubo>();
        tanques=new LinkedList<Nodo>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon(getClass().getResource("/Pasto.png")).getImage(), 0, 0, null);
        try {
           
            if (getCiudad() != null) {
                if (!L.isEmpty()) {
                    for (int i = 0; i < L.size(); i++) {
                        int posxi = L.get(i).getXini() - 5;
                        int posxf = L.get(i).getXfin() - 5;
                        int posyi = L.get(i).getYini() - 5;
                        int posyf = L.get(i).getYfin() - 5;
                        for (int k = 0; k < 10; k++) {
                            g.setColor(Color.MAGENTA);
                            g.drawLine(posxi, posyi, posxf, posyf);
                            posxi++;
                            posxf++;
                            posyi++;
                            posyf++;
                        }
                    }
                    for (int i = 0; i < L.size(); i++) {
                        for (int k = 0; k < L.get(i).getAgua().size(); k++) {
                            g.setColor(new Color(153, 204, 255));
                            g.drawOval((int) L.get(i).getAgua().get(k).getX(), (int) L.get(i).getAgua().get(k).getY(), 10, 10);
                            g.fillOval((int) L.get(i).getAgua().get(k).getX(), (int) L.get(i).getAgua().get(k).getY(), 11, 11);

                        }
                    }
                }
                for (int i = 0; i < getCiudad().conexiones.length; i++) {
                    for (int j = 0; j < getCiudad().conexiones.length; j++) {
                        if (getCiudad().conexiones[i][j] != null) {
                            int posxi = getCiudad().conexiones[i][j].getXini() - 5;
                            int posxf = getCiudad().conexiones[i][j].getXfin() - 5;
                            int posyi = getCiudad().conexiones[i][j].getYini() - 5;
                            int posyf = getCiudad().conexiones[i][j].getYfin() - 5;
                            for (int k = 0; k < 10; k++) {
                                if (!getCiudad().conexiones[i][j].isObst()) {
                                    if (getCiudad().conexiones[i][j].isAlterna()) {
                                        g.setColor(Color.white);
                                    } else {
                                        g.setColor(Color.gray);
                                    }
                                    g.drawLine(posxi, posyi, posxf, posyf);
                                } else {
                                    g.setColor(Color.red);
                                    g.drawLine(posxi, posyi, posxf, posyf);
                                }
                                posxi++;
                                posxf++;
                                posyi++;
                                posyf++;
                            }
                        }
                    }
                }
                for (int i = 0; i < getCiudad().conexiones.length; i++) {
                    for (int j = 0; j < getCiudad().conexiones.length; j++) {
                        if (getCiudad().conexiones[i][j] != null) {
                            for (int k = 0; k < getCiudad().conexiones[i][j].getAgua().size(); k++) {
                                g.setColor(Color.blue);
                                g.drawOval((int) getCiudad().conexiones[i][j].getAgua().get(k).getX(), (int) getCiudad().conexiones[i][j].getAgua().get(k).getY(), 10, 10);
                                g.fillOval((int) getCiudad().conexiones[i][j].getAgua().get(k).getX(), (int) getCiudad().conexiones[i][j].getAgua().get(k).getY(), 11, 11);

                            }
                        }
                    }
                }
                 if (!tanques.isEmpty()) {
                    for (int i = 0; i < tanques.size(); i++) {
                        Nodo aux = tanques.get(i);
                        g.drawImage(new ImageIcon("Tanque.png").getImage(), aux.getTanque().getX(), aux.getTanque().getY(), aux.getTanque().getAncho(), aux.getTanque().getAlto(), null);
                    }
                }
                for (int i = 0; i < getCiudad().Nodos.size(); i++) {
                    Nodo aux = getCiudad().Nodos.get(i);
                    if (aux.isTan()) {
                        if (aux.getTanque().isDesbordo()) {
                            g.setColor(Color.blue);
                            g.drawOval(aux.getTanque().getX(), aux.getTanque().getY(), aux.getTanque().getAncho(), aux.getTanque().getAlto());
                            g.fillOval(aux.getTanque().getX(), aux.getTanque().getY(), aux.getTanque().getAncho(), aux.getTanque().getAlto());
                        }
                        g.drawImage(new ImageIcon("Tanque.png").getImage(), aux.getTanque().getX(), aux.getTanque().getY(), aux.getTanque().getAncho(), aux.getTanque().getAlto(), null);
                    } else {
                        if (aux.getBarrio().isDesbordo()) {
                            g.setColor(Color.blue);
                            g.drawOval(aux.getBarrio().getX(), aux.getBarrio().getY(), aux.getBarrio().getAncho(), aux.getBarrio().getAlto());
                            g.fillOval(aux.getBarrio().getX(), aux.getBarrio().getY(), aux.getBarrio().getAncho(), aux.getBarrio().getAlto());
                        } else if (aux.getBarrio().isInsatisfecho()) {
                            g.setColor(Color.red);
                            g.drawOval(aux.getBarrio().getX(), aux.getBarrio().getY(), aux.getBarrio().getAncho(), aux.getBarrio().getAlto());
                            g.fillOval(aux.getBarrio().getX(), aux.getBarrio().getY(), aux.getBarrio().getAncho(), aux.getBarrio().getAlto());
                        }
                        g.drawImage(new ImageIcon("Barrio.png").getImage(), aux.getBarrio().getX(), aux.getBarrio().getY(), aux.getBarrio().getAncho(), aux.getBarrio().getAlto(), null);
                    }
                }
            }
        } catch (Exception e) {
        }

        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMaximumSize(new java.awt.Dimension(1097, 700));
        setMinimumSize(new java.awt.Dimension(1097, 700));
        setPreferredSize(new java.awt.Dimension(1097, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1222, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /**
     * @return the ciudad
     */
    public Acueducto getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(Acueducto ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the saldo
     */
    public int getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the L
     */
    public LinkedList<Tubo> getL() {
        return L;
    }

    /**
     * @param L the L to set
     */
    public void setL(LinkedList<Tubo> L) {
        this.L = L;
    }

    void setTanques(LinkedList<Nodo> tanques) {
        this.tanques = tanques;
    }
}
