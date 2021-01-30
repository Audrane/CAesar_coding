package pis.hue1;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>
 * Damit haben wir das Aussehen aller in der Wuerfel Klasse vorhandenen Methoden über ein Interface.
 * Die Variablen "wuerfel" und "caeser" sind Klassenattributen mit denen man die Hauptmethoden
 * "Coder"(kodiere) und "decoder"(dekodiere) bedienbar machen werden.
 * </p>
 * <strong>
 *
 * @author AUdrane Carelle Ngnindjeu
 * </strong>
 */

public class CodecGUI {
    //objekte
    private Codec wuerfel;
    private Codec caesar;


    private final JFrame myJFrame;
    private final JPanel panel;
    //eingabefenster bzw area fuer klartext geheimtext losungsword
    private final JTextArea plainTextArea;
    private final JTextArea secretTextArea;
    private final JTextField losungswort1Field;
    private final JTextField losungswort2Field;
    //die entsprechende name des area
    private final JLabel labelClearText;
    private final JLabel labelPasswordClearText;
    private final JLabel labelSecretText;
    private final JLabel labelPasswordSecretText;
    //radio fuer den auswahl caeserr or wuerfel
    private final JRadioButton optionWuerfel;
    private final JRadioButton optionCaesar;
    private ButtonGroup group;
    //radio fue auswahl code or decode
    private final JButton buttonEncode;
    private final JButton buttonDecode;

    /**
     * Konstruktor der klasse CodecGUI
     *
     * @param w codec Objekt
     * @param c codec Objekt
     */
    public CodecGUI(Codec w, Codec c) {

        wuerfel = w;
        caesar = c;
//
        myJFrame = new JFrame();
        myJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myJFrame.setTitle("Hausuebung 1");
        myJFrame.setSize(200, 300);
        panel = new JPanel();

        //1-zeiliges und 20-spaltiges Textfeld wird erzeugt
        plainTextArea = new JTextArea(2, 15);
        secretTextArea = new JTextArea(2, 15);
        losungswort1Field = new JTextField("", 15);
        losungswort2Field = new JTextField("", 15);

        labelClearText = new JLabel("Klarttext eingeben");
        labelPasswordClearText = new JLabel("Losungswort 1");
        labelSecretText = new JLabel("geheimtext eingeben");
        labelPasswordSecretText = new JLabel("losungswort 2");
//ya les 2 option jles regroupe et ajoute dans le groupe
        optionWuerfel = new JRadioButton("Wuerfel", true);
        optionWuerfel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                losungswort2Field.enable(true);
            }
        });

        optionCaesar = new JRadioButton("Caesar");
        optionCaesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                losungswort2Field.setText("");
                losungswort2Field.enable(false);
            }
        });

        group = new ButtonGroup();
        group.add(optionWuerfel);
        group.add(optionCaesar);

        buttonEncode = new JButton("Encode");
        buttonEncode.addActionListener((e) -> {
            try {
                onEncodeButtonClick();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        buttonDecode = new JButton("Decode");
        buttonDecode.addActionListener((e) -> {
            try {
                onDecodeButtonClick();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });


//JPanel, a part of Java Swing package, is a container that can store a group of components.
        //in jpanel kann man ++ layout setzen
        panel.add(labelClearText);
        panel.add(plainTextArea);

        panel.add(labelPasswordClearText);
        panel.add(losungswort1Field);

        panel.add(labelPasswordSecretText);
        panel.add(losungswort2Field);

        panel.add(labelSecretText);
        panel.add(secretTextArea);

        panel.add(optionCaesar);
        panel.add(optionWuerfel);

        panel.add(buttonEncode);
        panel.add(buttonDecode);


        myJFrame.add(panel);
        myJFrame.setVisible(true);

    }


    private void setzeLosung(String losung) {
        caesar.setzeLosung(losung);
        wuerfel.setzeLosung(losung);
    }

    //
    private void onDecodeButtonClick() {

        boolean checkResult = checkLosung("Losungswort1", losungswort1Field.getText());
        if (!checkResult) return;
        checkResult = checkLosung("Sercret", secretTextArea.getText());
        if (!checkResult) return;

        if (optionWuerfel.isSelected()) {

            checkResult = checkLosung("Losungswort2", losungswort2Field.getText());
            if (!checkResult) return;
            wuerfel.setzeLosung(losungswort2Field.getText());
            String decodedString = wuerfel.dekodiere(secretTextArea.getText());
            wuerfel.setzeLosung(losungswort1Field.getText());
            decodedString = wuerfel.dekodiere(decodedString);
            plainTextArea.setText(decodedString);
            //  alertForDecode(decodedString, optionWuerfel.getText());
            alertForEncode(decodedString, optionCaesar.getText());

        } else if (optionCaesar.isSelected()) {
            caesar.setzeLosung(losungswort1Field.getText());
            String decodedString = caesar.dekodiere(secretTextArea.getText());
            plainTextArea.setText(decodedString);
            //  alertForDecode(decodedString, optio
            alertForEncode(decodedString, optionCaesar.getText());

        }
    }


    private void onEncodeButtonClick() {
        boolean checkResult = checkLosung("Losungswort1", losungswort1Field.getText());
        if (!checkResult) return;
        checkResult = checkLosung("Plaintext", plainTextArea.getText());
        if (!checkResult) return;

        setzeLosung(losungswort1Field.getText());

        if (optionWuerfel.isSelected()) {

            checkResult = checkLosung("Losungswort2", losungswort2Field.getText());
            if (!checkResult) return;

            String encodedString = wuerfel.kodiere(plainTextArea.getText());
            wuerfel.setzeLosung(losungswort2Field.getText());

            encodedString = wuerfel.kodiere(encodedString);
            secretTextArea.setText(encodedString);
            alertForEncode(encodedString, optionWuerfel.getText());

        } else if (optionCaesar.isSelected()) {
            caesar.setzeLosung(losungswort1Field.getText());
            String encodedString = caesar.kodiere(plainTextArea.getText());
            secretTextArea.setText(encodedString);
            alertForEncode(encodedString, optionCaesar.getText());

        }
    }


    private void alertForEncode(String encodedString, String optionText) {
        JOptionPane.showMessageDialog(null, "Encoded String with \"" + optionText + "\": " + encodedString
                + ". The secret Text fiel would be updated!");
    }

    private void alertForDecode(String decodedString, String optionText) {
        JOptionPane.showMessageDialog(null, "Decoded String with \"" + optionText + "\": " + decodedString
                + ". The clear Text fiel would be updated!");
    }


    private void showDialog(String messaage) {
        JOptionPane.showMessageDialog(null, messaage);
    }

    private boolean checkLosung(String eltName, String elt) {
        if (elt == null || elt.equals("")) {
            showDialog(eltName + " must have a value");
        }
        return true;
    }
}




