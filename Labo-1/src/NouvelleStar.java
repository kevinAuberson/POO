import java.util.*;
import java.lang.*;
import java.io.*;
public class NouvelleStar {
    public static void main(String[] args)
    {
        String x = "Il n'existe pas de candidats";
        final double MAX_VALUE_RANDOM = 150;
        int[] vote = new int[args.length];
        if(args.length == 0) {
            System.out.println(x);
        }
        else {
            System.out.println("Candidat : " );
            for(int i = 0; i < args.length; ++i) {
                System.out.println("#" + (i + 1) + " " + args[i]);
            }
        }
        System.out.println();
        System.out.println((int)MAX_VALUE_RANDOM + " vote:");
        //random
        java.util.Random random = new java.util.Random();
        for(int i = 0; i < MAX_VALUE_RANDOM; ++i) {
            System.out.print(".");
            int value = random.nextInt(args.length); // génère une valeur dans l’intervalle [0, maximum[
            vote[value]++;
        }
        System.out.println();
        System.out.println("Resultat : ");

        for(int i = 0; i < args.length; ++i) {
            double pourcentage = Math.round((vote[i]* 100) / MAX_VALUE_RANDOM);
            System.out.println((int) pourcentage + "% " + args[i]);
        }
    }
}
