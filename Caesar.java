package pis.hue1;

/**
 * in der Klasse Caeser die ver und entschluesseln klar und geheim texte
 *
 */
public class Caesar implements Codec {
    private int schift;
    String losungString;

    /**
     * @param a das character die wir verschluesseln mussen
     * @param i anzahl von stelle die wir verschieben wollen
     * @return return das char die verschluesselt ist
     */
    private static char encode(char a, int i) {
        // Großbuchstaben
        if (a >= 65 && a <= 90)
            a = (char) (65 + (a - 65 + i) % 26);
            // Kleinbuchstaben
        else if (a >= 97 && a <= 122)
            a = (char) (97 + (a - 97 + i) % 26);
        return a;
    }

    /**
     * @param klartext der text die wir verschluesseln wollen
     *
     * @return der geheimtext
     */
    @Override

    public String kodiere(String klartext) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < klartext.length(); i++)
        {
            result.append(encode(klartext.charAt(i), schift));
        }
        return result.toString();
    }

    /**laut ascii zeichen durlaufen alle grosse und klein Buchstaben
     *
     * @param a das character die wir verschluesseln mussen
     * @param i anzahl von stelle die wir verschieben wollen
     *  zb char a ->3 stele verschieben  dann return d
     * @return return das char die verschluesselt ist
     */

    private static char decode(char a, int i) {

        // Großbuchstaben
        if (a >= 65 && a <= 90){
            // a = (char) (65 + (a - 65 - i) % 26);
            if (a - (i % 26) < 'A') {
                a = (char) (a - (i % 26) + 26);
            } else a = (char) (a - i);
        }
        // Kleinbuchstaben
        else if (a >= 'a' && a <= 'z') {
            if (a - (i % 26) < 'a') {
                a = (char) (a - (i % 26) + 26);
            } else a = (char) (a - i);
        }
        return a;
    }


    /**
     * @param geheimtext der zu entschluesselnde text
     *
     * @return der klartext als string
     */
    @Override
    public String dekodiere(String geheimtext) {
        StringBuilder resultat = new StringBuilder();
        for (int i = 0; i < geheimtext.length(); i++) {
            resultat.append(decode(geheimtext.charAt(i), schift));
        }
        return resultat.toString();
    }

    /**
     * @return losung
     */
    @Override
    public String gibLosung() {
        return losungString;
    }

    /**
     * @param schluessel die wir setzen
     * @throws IllegalArgumentException bei ungeeignete Schlüssel
     */
    @Override
    public void setzeLosung(String schluessel) throws IllegalArgumentException {
        if (schluessel.equals("")) throw new IllegalArgumentException("password should not be empty");
        schift = schluessel.length();
        losungString = schluessel;
    }

}
