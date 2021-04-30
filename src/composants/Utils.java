package composants;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * Classe contenant quelques fonctions utilitaires.
 *
 */
public class Utils {

    private static Random generateur=new Random((new Date().getTime()));

    /**
     * A Faire (Quand Qui Statut)
     *
     * MÃ©thode permettant de gÃ©nÃ©rer alÃ©atoirement un nombre entier.
     *
     * @param max Le nombre entier maximal pouvant Ãªtre retournÃ©.
     * @return Un nombre entier compris entre 0 et max (inclus).
     */
    public static int genererEntier(int max){
        int min=0;
        Random rand = new Random(); int nombreAleatoire = rand.nextInt(max - min + 1) + min;
        return -1;
    }
    /**
     * A Faire (30/04/21 I.l Finalisée)
     *
     * MÃ©thode permettant de gÃ©nÃ©rer un tableau d'entiers dont la longueur longTab est donnÃ©e en paramÃ¨tre.
     * Le tableau gÃ©nÃ©rÃ© doit contenir chaque entier compris entre 0 et longTab-1. La position de ces entiers
     * dans le tableau doit Ãªtre alÃ©atoire.
     *
     * @param longTab La longueur du tableau.
     * @return Un tableau contenant les entiers 0,...,longTab-1 placÃ©s alÃ©atoirement dans le tableau.
     */
    public static int[] genereTabIntAleatoirement(int longTab){

        int tab[]= null;
        ArrayList intTab= new ArrayList();
        for(int i=0;i<=longTab;i++){
            intTab.add(i);

        }
        int[] randomValues = new int[intTab.size()];
        Random random = new Random();
        int pos = 0;

        while (intTab.size() > 0) {
            pos = random.nextInt(intTab.size());
            randomValues[intTab.size() - 1] = (int) intTab.get(pos);
            intTab.remove(pos);
        }

        return randomValues;
    }


    /**
     * Programme testant la mÃ©thode genereTabIntAleatoirement.
     * @param args arguments du programme
     */
    public static void main(String[] args) {
        // Un petit test ...
        int tab[]=genereTabIntAleatoirement(17);
        for (int i=0;i<tab.length;i++)
            System.out.print(tab[i]+" ");

    }

}