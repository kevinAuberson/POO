package cours;
import personnes.Professeur;


public class Lecon {
    private String matiere;
    private int jourSemaine;
    private int periodeDebut;
    private int duree;
    private String salle;
    private Professeur prof;

    private static final int JOURSPARSEMAINE = 5;
    private static final int PERIODEPARJOUR = 11;

    private static final int LARGEURCASE = 13;
    private static final int PADDING_GAUCHE = 5;

    private static final String[] JOURS = new String[] {"Lun", "Mar", "Mer",
            "Jeu", "Ven"};

    private static final String[] HEURES = new String[] {"8:30", "9:15", "10" +
            ":25", "11:15", "12:00", "13:15", "14:00", "14:55", "16:35", "17" +
            ":20"};

    public Lecon(String matiere, int jourSemaine, int periodeDebut, int duree,
                 String salle) {
        this.matiere = matiere;
        this.jourSemaine = jourSemaine;
        this.periodeDebut = periodeDebut;
        this.duree = duree;
        this.salle = salle;
        this.prof = null;

    }

    public Lecon(String matiere, int jourSemaine, int periodeDebut, int duree,
                 String salle, Professeur prof) {
        this.matiere = matiere;
        this.jourSemaine = jourSemaine;
        this.periodeDebut = periodeDebut;
        this.duree = duree;
        this.salle = salle;
        this.prof= prof;

        // Ajout de la leçon au professeur pour avoir la liaison dans les 2 sens
        if (prof != null)
            this.prof.addLecon(this);
    }

    static public String horaire(Lecon[] list) {
        int[][] etatLecons = new int[PERIODEPARJOUR][JOURSPARSEMAINE];
        String[][] nomLecons = new String[PERIODEPARJOUR][JOURSPARSEMAINE];

        for(Lecon lecon : list) {
            nomLecons[lecon.periodeDebut][lecon.jourSemaine] = String.format(
                    "%-5s %3s %3s", lecon.matiere, lecon.salle,
                    (lecon.prof != null ? lecon.prof.abreviation() : ""));
            for(int i = 0; i < lecon.duree; i++) {
                etatLecons[lecon.periodeDebut + i][lecon.jourSemaine] = lecon.duree - i;
            }
        }

        // permet l'affichage complet de l'horaire
        StringBuilder affichage = new StringBuilder();
        affichage.append(" ".repeat(PADDING_GAUCHE));
        for(String jour : JOURS) {
            affichage.append(String.format("| %-" + (LARGEURCASE - 1) + "s",
                    jour));
        }
        // manque un dernier "|" ????????
        String ligneDelimitation = "|" + "-".repeat(LARGEURCASE);
        // ajout du \n au dernier jour de la semaine
        affichage.append("|\n").append(" ".repeat(PADDING_GAUCHE))
                .append(ligneDelimitation.repeat(JOURSPARSEMAINE)).append("|\n");

        // Fin des 2 premières lignes
        // Boucler sur chaque ligne
        for(int i = 0; i < PERIODEPARJOUR; i++) {
            affichage.append(String.format("%" + PADDING_GAUCHE + "s|",
                    HEURES[i]));
            StringBuilder separateur = new StringBuilder();
            separateur.append("\n").append(" ".repeat(PADDING_GAUCHE)).append("|");

            //boucle sur chaque colonne
            for(int j = 0; j < JOURSPARSEMAINE; j++) {
                affichage.append(nomLecons[i][j] != null ? nomLecons[i][j] :
                        " ".repeat(LARGEURCASE));

                separateur.append((etatLecons[i][j] <= 1 ? "-" : " ")
                        .repeat(LARGEURCASE));

                separateur.append("|");
                affichage.append("|");
                }

            affichage.append(separateur).append("\n");
            }
        return affichage.toString();
        }
    }


