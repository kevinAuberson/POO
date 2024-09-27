package personnes;
import cours.Lecon;
import java.util.*;

public class Professeur extends Personne {

    private String abreviation;
    private Lecon[] listLecon;
    
    public Professeur(String nom, String prenom, String abreviation) {
        super(nom, prenom);
        this.abreviation = abreviation;
        this.listLecon = new Lecon[0];
    }

    public Professeur(String nom, String prenom, String abreviation,
               Lecon[] listLecon) {
        super(nom, prenom);
        this.abreviation = abreviation;
        this.listLecon = Arrays.copyOf(listLecon, listLecon.length);
    }



    public String abreviation() {
        return abreviation;
    }

    public String horaire() {
        return "-- Horaire " + this + "\n\n" + Lecon.horaire(this.listLecon);
    }

    public void addLecon(Lecon lesson) {
        this.listLecon = Arrays.copyOf(this.listLecon, this.listLecon.length + 1);
        this.listLecon[this.listLecon.length - 1] = lesson;
    }
    public String toString(){
        return "Prof. " + super.toString() + " (" + abreviation + ") ";
    }
}
