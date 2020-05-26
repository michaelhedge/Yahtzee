/*

Volgorde van doelstellingen:
[x] 1. Maak een loop in de public static void main die loopt zolang je op enter drukt, en stopt zodra je op q drukt.
[x] 2. Maak een losse klasse YahtzeeSpel met een methode spelen().
    [x] Verhuis de loop van 1. naar de methode spelen().
    [x] Instantieer een YahtzeeSpel in de psvm en start de methode spelen.
[x] 3. Maak een losse methode werpen() in het YahtzeeSPel.
    [x] Deze methode heeft int als return type.
    [x] Zorg dat de aanroep van deze methode er voor zorgt dat er een getal tussen 1 en 6 terug komt.

Echte dobbelstenen:
[x] 4. Maak de methode een onderdeel van de klasse Dobbelsteen.
    [x] Maak in de klasse YahtzeeSpel een Arraylist van Dobbelstenen.
    [x] In de constructor van het YahtzeeSpel voeg je 5 dobbelstenen toe aan deze ArrayList.
    [x] In de loop van spelen wordt over de Arraylist heengegaan met een enhanced for-loop en werp je iedere dobbelsteen.
    [x] Hou in je dobbelsteen het resultaat van de worp vast.
[x] 5. Maak een losse methode vasthouden().
    [x] Via een Scanner vraagt het programma om invoer.
    [x] De invoer wordt via Integer.parseInt() omgezet tot een blokkeerarray van 0 en 1 bijvoorbeeld 11001.
    [x] Dit blokkeerarray is een field van het YahtzeeSpel.
[x] 6. Loop in de tweede worp over de arraylist heen en alleen als in de blokkeerarray een 0 staat wordt de dobbelsteen opnieuw geworpen.

Nog meer objecten:
[x] 7. Maak een klasse Worp, met een veld van het type int array met lengte 5.
    [x] Een methode die de worp uitslag weergeeft.
[ ] 8. Maak een klasse Speler, met een veld worp-geschiedenis, dit is een array met een flinke lengte of een arraylist.
[ ] 9. Zorg dat aan het einde van een worp-cyclus zoals gemaakt tot stap zes, opgeslagen wordt in een Worp-object.
    [ ] Voeg deze toe aan de worp-geschiedenis van de speler.

Meerdere spelers:
[ ] 10. Maak het spel geschikt voor het spelen met twee spelers

*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

class Yahtzee {
    public static void main(String[] args) {
        YahtzeeSpel spelen = new YahtzeeSpel();
        spelen.spelen();
    }
}

class YahtzeeSpel {
    static ArrayList<Dobbelsteen> dobbelstenen = new ArrayList<>();
    ArrayList<Integer> blokkeer = new ArrayList<>();
    static boolean spelen = true;
    Scanner input = new Scanner(System.in);

    YahtzeeSpel() {
        for (int i = 0; i < 5; i++) {
            Dobbelsteen dobbelsteen = new Dobbelsteen();
            dobbelstenen.add(dobbelsteen);
            blokkeer.add(0);
        }
    }

    void spelen() {
        System.out.println("Worp:");
        for (Dobbelsteen ds : dobbelstenen) {
            ds.waarde = ds.werpen();
            System.out.print(ds.waarde + " ");
        }
        while (spelen) {
            String keuze = input.nextLine();
            if (keuze.equals("q")) {
                System.out.println("Spel afgesloten.");
                break;
            } else {
                vasthouden();
                System.out.println("Worp:");
                Worp.uitslag();
            }
        }
    }

    void vasthouden() {
        System.out.println("Welke dobbelstenen wil je vasthouden?");
        Scanner input = new Scanner(System.in);
        String keuze = input.nextLine();
        for (int a = 0; a < 5; a++) {
            blokkeer.set(a, 0);
        }
        if (keuze.equals("")) {
            for (int i = 0; i < 5; i++) {
                blokkeer.set(i, 0);
            }
            for (int x = 0; x < 5; x++) {
                if (blokkeer.get(x).equals(0)) {
                    dobbelstenen.get(x).waarde = dobbelstenen.get(x).werpen();
                }
            }
        } else {
            for (int i = 0; i < keuze.length(); i++) {
                char c = keuze.charAt(i);
                String s = Character.toString(c);
                int y = Integer.parseInt(s) - 1;
                for (int a = 0; a < blokkeer.size(); a++) {
                    if (y == a) {
                        blokkeer.set(a, 1);
                    }
                }
            }
            for (int x = 0; x < 5; x++) {
                if (blokkeer.get(x).equals(0)) {
                    dobbelstenen.get(x).waarde = dobbelstenen.get(x).werpen();
                }
            }
        }
    }
}

class Dobbelsteen {
    int waarde;

    int werpen() {
        Random random = new Random();
        waarde = random.nextInt(6) + 1;
        return waarde;
    }
}

class Worp {
    int[] worp = new int[5];

    static void uitslag() {
        for (Dobbelsteen ds : YahtzeeSpel.dobbelstenen) {
            System.out.print(ds.waarde + " ");
        }
    }
}