package Logica;

import Objetos.Barrio;
import Objetos.Tanque;
import Objetos.Tubo;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

public class Acueducto {

    public LinkedList<Nodo> Nodos;
    public Tubo[][] conexiones;

    public Acueducto() {
        Nodos = new LinkedList<>();
        conexiones = new Tubo[0][0];
//        crearcostos();


        System.out.println("coneciones del tubo "+conexiones);
    }

    //redimensiona la matriz de acuerdo a un nuevo dato agregado o eliminado
    public void redimention() {
        Tubo[][] Aux = new Tubo[Nodos.size()][Nodos.size()];
        for (int i = 0; i < Nodos.size() - 1; i++) {
            System.arraycopy(conexiones[i], 0, Aux[i], 0, Nodos.size() - 1);
        }
        conexiones = Aux;
    }

    //Adiciona un barrio
    public boolean add(Nodo Vertex) {
        if (Nodos.contains(Vertex)) {
            return false;
        }
        Nodos.add(Vertex);
        redimention();
        return true;
    }

    //Adicionar un Camino
    public boolean add(Nodo vertex1, Nodo vertex2, Tubo road) {
        if (Nodos.contains(vertex1) && Nodos.contains(vertex2)) {
            conexiones[Nodos.indexOf(vertex1)][Nodos.indexOf(vertex2)] = road;
            return true;
        }
        return false;
    }

    // retorna el recorrido en profundidad
    public LinkedList profundidad(Nodo vertex) {
        LinkedList<Nodo> recorrido = new LinkedList<>();
        boolean[] marcados = new boolean[Nodos.size()];
        profundidad(vertex, recorrido, marcados);
        System.out.println("recorrido tubo "+recorrido);
        return recorrido;
        
      
    }
//privado profundidad

    public LinkedList<Nodo> Restriccion(int restriccion){
        LinkedList<Nodo> tanques=new LinkedList<Nodo> ();
        for (int i = 0; i < Nodos.size(); i++) {
            if (Nodos.get(i).tan) {
                if (Nodos.get(i).tanque.getCapacidad_maxima()<=restriccion) {
                    tanques.add(Nodos.get(i));
                }
            }
        }
        for (int i = 0; i < tanques.size(); i++) {
            System.out.println(""+tanques.get(i).tanque.getCapacidad_maxima());
        }
        return tanques;
    }
    private void profundidad(Nodo vertex, LinkedList<Nodo> re, boolean[] marcados) {
        re.add(vertex);
        marcados[Nodos.indexOf(vertex)] = true;
        if (!marcados(marcados)) {
            for (int i = 0; i < conexiones[Nodos.indexOf(vertex)].length; i++) {
                if (conexiones[Nodos.indexOf(vertex)][i] != null && !marcados[i]) {
                    profundidad((Nodo) Nodos.get(i), re, marcados);
                }
            }

            if (!marcados(marcados)) {
                for (int i = 0; i < marcados.length; i++) {
                    if (!marcados[i]) {
                        profundidad((Nodo) Nodos.get(i), re, marcados);
                    }

                }
            }
        }
    }
    //mira si todos los enlaces de un nodo estan marcados

    public boolean marcados(boolean[] ma) {
        for (int i = 0; i < ma.length; i++) {
            if (!ma[i]) {
                return false;
            }
        }
        return true;
    }

//muestra las rutas alternas cuando hay tubos obstruidos
    public boolean RutasAlternas(Nodo N1, Nodo N2) {
        if (N1 != null) {
            if (N1.equals(N2)) {
                return true;
            }
            boolean Mar[] = new boolean[conexiones.length];
            for (int i = 0; i < conexiones.length; i++) {
                if (conexiones[Nodos.indexOf(N1)][i] != null) {
                    if (!conexiones[Nodos.indexOf(N1)][i].isObst()) {
                        Mar[i] = RutasAlternas(Nodos.get(i), N2);
                    }
                }
            }
            if (marc(Mar)) {
                for (int i = 0; i < Mar.length; i++) {
                    if (Mar[i]) {
                        conexiones[Nodos.indexOf(N1)][i].setAlterna(true);
                    }
                }
                return true;
            }
        }
        return false;

    }
    //dice si hay almenos un tubo marcado como ruta alterna

    private boolean marc(boolean[] Mar) {
        for (int i = 0; i < Mar.length; i++) {
            if (Mar[i]) {
                return true;
            }
        }
        return false;
    }

    //retorna posibles tubos para mejor cobertura de acueducto
    public LinkedList<Tubo> ProponerConexiones(Nodo N) {
        if (N == null) {
            N = EncontrarCasa();
        }
        LinkedList<Tubo> L = new LinkedList<Tubo>();
        for (int i = 0; i < conexiones.length; i++) {
            if (Nodos.get(i).isTan()) {
                if (conexiones[i][Nodos.indexOf(N)] == null) {
                    if (Nodos.get(i).getTanque().getAgua() >= N.getBarrio().getNecesidad()) {
                        L.add(new Tubo(N.getBarrio().getNecesidad(), N.getBarrio().getNecesidad(), Nodos.get(i).getTanque().getX() + 45, N.getBarrio().getX() + 75, Nodos.get(i).getTanque().getY() + 45, N.getBarrio().getY() + 75));
                    }
                }
            }
        }
        return L;
    }

    private Nodo EncontrarCasa() {
        if (TubosObstruidos()) {
            for (int i = 0; i < conexiones.length; i++) {
                for (int j = 0; j < conexiones.length; j++) {
                    if (conexiones[i][j] != null) {
                        if (conexiones[i][j].isObst()) {
                            if (!Nodos.get(j).isTan()) {
                                return Nodos.get(j);
                            }
                        }
                    }
                }
            }
        } else {
            Nodo n = obtenerprimerbarrio();
            int conn = 0;
            for (int i = 0; i < conexiones.length; i++) {
                if (conexiones[i][Nodos.indexOf(n)] != null) {
                    conn = conn + 1;
                }
            }
            for (int i = 0; i < Nodos.size(); i++) {
                if (!Nodos.get(i).isTan()) {
                    int cont = 0;
                    for (int j = 0; j < conexiones.length; j++) {
                        if (conexiones[j][i] != null) {
                            cont++;
                        }
                    }
                    if (cont < conn) {
                        conn = cont;
                        n = Nodos.get(i);
                    }
                }
            }
            return n;
        }
        return Nodos.getLast();
    }

    private boolean TubosObstruidos() {
        for (int i = 0; i < conexiones.length; i++) {
            for (int j = 0; j < conexiones.length; j++) {
                if (conexiones[i][j] != null) {
                    if (conexiones[i][j].isObst()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Nodo obtenerprimerbarrio() {
        for (int i = 0; i < Nodos.size(); i++) {
            if (!Nodos.get(i).isTan()) {
                return Nodos.get(i);
            }
        }
        return null;
    }

    

    public void TransportarAgua() {
        LinkedList<Nodo> tanques = new LinkedList<Nodo>();
        for (int i = 0; i < Nodos.size(); i++) {
            if (Nodos.get(i).isTan()) {
                tanques.add(Nodos.get(i));
            }
        }
        for (int i = 0; i < tanques.size(); i++) {
            TransportarAgua(tanques.get(i));
        }
    }

    private void TransportarAgua(Nodo get) {
        for (int i = 0; i < conexiones.length; i++) {
            if (conexiones[Nodos.indexOf(get)][i] != null) {
                if (!conexiones[Nodos.indexOf(get)][i].isObst()) {
                    if (get.isTan()) {
                        if (get.getTanque().getAgua() >= conexiones[Nodos.indexOf(get)][i].getCapacidad()) {
                            conexiones[Nodos.indexOf(get)][i].setTransportando(conexiones[Nodos.indexOf(get)][i].getCapacidad());
                            get.getTanque().setAgua(get.getTanque().getAgua() - conexiones[Nodos.indexOf(get)][i].getCapacidad());
                        } else {
                            if (get.getTanque().getAgua() > 0) {
                                conexiones[Nodos.indexOf(get)][i].setTransportando(get.getTanque().getAgua());
                                get.getTanque().setAgua(get.getTanque().getAgua() - conexiones[Nodos.indexOf(get)][i].getTransportando());
                            }
                        }
                    } else {
                        if (get.getBarrio().getActual() > get.getBarrio().getNecesidad()) {
                            if (get.getBarrio().getActual() - get.getBarrio().getNecesidad() >= conexiones[Nodos.indexOf(get)][i].getCapacidad()) {
                                conexiones[Nodos.indexOf(get)][i].setTransportando(conexiones[Nodos.indexOf(get)][i].getCapacidad());
                                get.getBarrio().setActual(get.getBarrio().getActual() - conexiones[Nodos.indexOf(get)][i].getTransportando());
                            } else {
                                conexiones[Nodos.indexOf(get)][i].setTransportando(get.getBarrio().getActual() - (get.getBarrio().getNecesidad()));
                                get.getBarrio().setActual(get.getBarrio().getActual() - conexiones[Nodos.indexOf(get)][i].getTransportando());
                            }
                        }
                    }
                    if (Nodos.get(i).isTan()) {
                        Nodos.get(i).getTanque().setAgua(Nodos.get(i).getTanque().getAgua() + conexiones[Nodos.indexOf(get)][i].getTransportando());
                    } else {
                        Nodos.get(i).getBarrio().setActual(Nodos.get(i).getBarrio().getActual() + conexiones[Nodos.indexOf(get)][i].getTransportando());
                    }
                    TransportarAgua(Nodos.get(i));
                }
            }
        }
    }

    public void leerMapa() {
        //cargar mapa
        Document Mapa = new Document();

        SAXBuilder builder = new SAXBuilder();
        try {
            Mapa = builder.build(new FileInputStream("src/Graficos/mapa1.xml"));
        } catch (JDOMException | IOException ex) {
        }

        Element raiz = Mapa.getRootElement();
        Element ciudad = raiz.getChild("Acueducto");
        List<Element> la = ciudad.getChildren("Nodo");

        for (Element e : la) {
            String ID = e.getChildText("ID");
            boolean tan = Boolean.parseBoolean(e.getChildText("tan"));
            if (tan) {
                Element t = e.getChild("tanque");

                int capacidad_maxima = Integer.parseInt(t.getChildText("capacidad_maxima"));
                int x = Integer.parseInt(t.getChildText("X"));
                int y = Integer.parseInt(t.getChildText("Y"));
                Tanque tanq = new Tanque(capacidad_maxima, x, y);
                Nodo n = new Nodo(tanq, ID);
                Nodos.add(n);
            } else {
                Element b = e.getChild("barrio");
                int X = Integer.parseInt(b.getChildText("X"));
                int Y = Integer.parseInt(b.getChildText("Y"));
                int necesidad = Integer.parseInt(b.getChildText("necesidad"));
                String nombre = b.getChildText("nombre");
                Barrio ba = new Barrio(X, Y, necesidad, nombre);
                Nodo n = new Nodo(ba, ID);
                Nodos.add(n);
            }

            redimention();
        }
        la = ciudad.getChildren("tubo");
        for (Element e : la) {
            String ID1 = e.getChildText("ID1");
            String ID2 = e.getChildText("ID2");
            int capacidad=Integer.parseInt(e.getChildText("capacidad"));
            Nodo seleccionado1 = buscarNodo(ID1);
            Nodo seleccionado2 = buscarNodo(ID2);
            int xini = 0, yini = 0, xfin = 0, yfin = 0;
            if (seleccionado1.isTan()) {
                xini = seleccionado1.getTanque().getX() + 45;
                yini = seleccionado1.getTanque().getY() + 45;
            } else {
                xini = seleccionado1.getBarrio().getX() + 75;
                yini = seleccionado1.getBarrio().getY() + 75;
            }
            if (seleccionado2.isTan()) {
                xfin = seleccionado2.getTanque().getX() + 45;
                yfin = seleccionado2.getTanque().getY() + 45;
            } else {
                xfin = seleccionado2.getBarrio().getX() + 75;
                yfin = seleccionado2.getBarrio().getY() + 75;
            }
            Tubo t=new Tubo(capacidad,  0, xini, xfin, yini, yfin);
            conexiones[Nodos.indexOf(seleccionado1)][Nodos.indexOf(seleccionado2)]=t;
        }
    }

    private Nodo buscarNodo(String ID) {
        for (int i = 0; i < Nodos.size(); i++) {
            if (Nodos.get(i).getID().equals(ID)) {
                return Nodos.get(i);
            }
        }
        return null;
    }
}
