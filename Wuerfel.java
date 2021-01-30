package pis.hue1;

/**
 * <p>
 *   Die Klasse Wuerfel  impelementiert das Interface Codec
 *   dh in Wuerfel muessen alle Methode der Interface implementiert werden
 *   </p>
 *   <p>
 *  Die Variablen "losung" und "perm" sind die KLassenInvariante
 * Die Variable "losung" ist fuer die Verschluesselung bzw entschluessekung eines Geheim-/Klar
 * Die Variable "perm" hat die Position jedes Zeichens
 * </p>
 * @author Audrane CArelle Ngnindjeu
 */
public class Wuerfel implements Codec {

    private String losung;
    private int[] perm;
    int[] revPerm;


    /**konstruktor der klasse wuerfel
     * @param losung
     */
    public Wuerfel(String losung) {

        setzeLosung(losung);
    }


    /**
     * @param klartext wir ubergeben den Klartext als parameter der Methode Kodiere
     * @return
     */
    public String kodiere(String klartext) {
        String resultat = "";
        int i = 0;
        while (i < perm.length) {
            for (int j = 0; j < perm.length; j++) {
                if (perm[j] == i) {
                    for (int k = j; k < klartext.length(); k += this.losung.length()) {
                        resultat += klartext.charAt(k);
                    }
                    break;
                }
            }
            i++;

        }
        return resultat;
    }
    /**
     *permutation der losungswort indexeweise
     */
    private void getpermutation() {
        perm = new int[this.losung.length()];
        revPerm = new int[this.losung.length()];
        int pos = 0;
        char i = 'a';
        while (i <= 'z') {
            for (int j = 0; j < losung.length(); j++) {
                if (i == this.losung.toLowerCase().charAt(j) || i == this.losung.toUpperCase().charAt(j)) {
                    perm[j] = pos;
                    revPerm[pos] = j;
                    pos++;
                }
            }
            i++;
        }
    }

    /**
     * @return losung
     */
    @Override
    public String gibLosung() {

        return this.losung;
    }

    /**
     * @param schluessel
     * @throws IllegalArgumentException wenn der schluessel leer ist
     *
     */
    public void setzeLosung(String schluessel) throws IllegalArgumentException {
        if(schluessel.equals("")) throw new IllegalArgumentException("password should not be empty");
        this.losung = schluessel;
        getpermutation();
    }

    /**
     * @param geheimtext ist der parameter unser methode und stellt der zu entschluesselte Text dar
     * @return der klartext nach der entscluesselung
     */
    @Override
    public String dekodiere(String geheimtext) {
        int size = geheimtext.length() / losung.length(); // size  ist die Anzahl der Zeilen.
        int mod = geheimtext.length() % losung.length(); // anzahl von stellen die size+ 1 bekommen müssen
        char[][] ar = new char[size + 1][losung.length()]; // char wird aus sicherheitsgrunden immer mit size+1 initialisiert
        int counter = 0; // counter ist einen Pointer auf dem Geheimtext
        for (int i = 0; i < revPerm.length; i++) { //durchlaufen unsere inversepermArray
            if (revPerm[i] < mod) { // reversperm <5 zb dan wird anzahl spalte incremetiert
                for (int j = 0; j < size + 1; j++) {
                    ar[j][revPerm[i]] = geheimtext.charAt(counter++);
                }
            } else {//else nur die 3 zeilen fuellen
                for (int j = 0; j < size; j++) {
                    ar[j][revPerm[i]] = geheimtext.charAt(counter++);
                }
            }
        }
        String contain = "";
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {
                if (ar[i][j] != '\000')
                    contain += ar[i][j];
            }
        }
        return contain;
    }

}