package cours;

import personnes.Etudiant;

import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Groupe {
    private int numero;
    private String orientation;
    private int trimestre;
    private Lecon[] leconList;
    private Etudiant[] listEtudiants;


    public Groupe(int numero, String orientation, int trimestre,
                Etudiant[] list) {
        this.numero = numero;
        this.orientation = orientation;
        this.trimestre = trimestre;
        this.leconList = new Lecon[0];
        this.listEtudiants = new Etudiant[list.length];
        for(int i = 0; i < list.length; i++) {
            list[i].setGrp(this);
            listEtudiants[i] = list[i];
        }
    }

    public String horaire (){
        return "-- Horaire du group " + nom() + " (" + nombreEtudiants() + " " +
                "etudiants)\n\n" + Lecon.horaire(this.leconList);
    }

    public String nom(){
        return orientation + trimestre + "-"+ numero;
    }

    public void setLeconList(Lecon[] l){
        this.leconList = Arrays.copyOf(l,l.length);
    }

    public int nombreEtudiants() {
        return listEtudiants.length;
    }
}