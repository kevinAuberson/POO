
import cours.*;
import personnes.*;

import java.util.ArrayList;

public class Ecole {
    public static void main(String[] args) {

        // 1. Professeurs
        Professeur dre = new Professeur("Daniel", "Rossier", "DRE");
        Professeur pdo = new Professeur("Pier", "Donini", "PDO");

        // 2. Leçons
        Lecon poo1 = new Lecon("POO", 2, 5, 2, "H02", pdo);
        Lecon poo2 = new Lecon("POO", 3, 5, 2, "H02", pdo);
        Lecon poo3 = new Lecon("POO", 3, 7, 2, "H02", pdo);
        Lecon sye1 = new Lecon("SYE", 0, 0, 2, "G01", dre);
        Lecon sye2 = new Lecon("SYE", 3, 2, 2, "A09", dre);
        Lecon tic = new Lecon("TIC", 1, 9, 1, "F06");

        // 3. Élèves
        Etudiant john = new Etudiant("John", "Lennon", 1234);
        Etudiant paul = new Etudiant("Paul", "Mc Cartney", 2341);
        Etudiant ringo = new Etudiant("Ringo", "Starr", 3241);
        Etudiant george = new Etudiant("George", "Harisson", 4321);
        Etudiant roger = new Etudiant("Roger", "Waters", 1324);
        Etudiant david = new Etudiant("David", "Gilmour", 4312);

        // 4. Groupes
        Groupe grp1 = new Groupe(1, "IL", 6,
                new Etudiant[]{john, paul, ringo, george});
        Groupe grp2 = new Groupe(1, "SI", 6,
                new Etudiant[]{roger, david});

        // 5. Affecter les leçons aux groupes
        grp1.setLeconList(new Lecon[]{poo1,poo2,poo3,sye1,sye2,tic});
        grp2.setLeconList(new Lecon[]{poo1,poo2,poo3});


        // 6. Personnes
        Personne[] personnes = new Personne[]{pdo, dre, john, paul, ringo,
                george, roger, david};

        System.out.println("-- Membres de l'ecole\n");
        for(Personne i : personnes) {
            System.out.println(i);
        }
        System.out.println();

        // 7. Infos du groupe IL6-1
        System.out.println(grp1.horaire());

        // 8. Afficher l’horaire du professeur PDO.
        System.out.println(pdo.horaire());
    }
}
