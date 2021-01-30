package pis.hue1;

/**
 * Codec ist die schnittstelle die vorgegeben wurde.
 * Die verfügt vier Methoden(kodiere,dekodiere,setzelosung und gibtlosung) ,die in der KLasse Wuerfel und Ceaser implementieren werden muessen
 * @author  Audrane Carelle Ngnindjeu
 */
public interface Codec {
    /**
     *
     * @param klartext wir ubergeben den Klartext als parameter der Methode Kodiere
     * @return der verschlüsselte Text also geheimtext
     */
    public String kodiere(String klartext);

    /**
     *
     * @param geheimtext ist der parameter ,den wir entschlüsseln müssen
     * @return der klartext wird zurückgegeben
     */
    public String dekodiere(String geheimtext);

    /**
     *
     * @return losung
     */
    public String gibLosung();

    /**
     *
     * @param schluessel die losung wird gesetzt
     * @throws IllegalArgumentException bei  ungeeignette schlüssel die
     * meldung wird geworfen
     */
    public void setzeLosung(String schluessel)throws IllegalArgumentException; // bei ungeeignetem Schlüssel!
}
