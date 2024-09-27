package personnes;
import cours.Groupe;


public class Etudiant extends Personne {
    private int matricule;

    private Groupe grp;

    public Etudiant(String nom, String prenom, int matricule) {
        super(nom, prenom);
        this.matricule = matricule;
    }

    public Etudiant(String nom, String prenom, int matricule,Groupe grp) {
        super(nom, prenom);
        this.matricule = matricule;
        this.grp = grp;
    }

    public String toString() {
            return "Etud. " + super.toString() + " (#" + matricule + ")" + ((grp != null) ? " - " + grp.nom() : "");
    }

    public void setGrp(Groupe grp) {
        this.grp = grp;
    }


}
