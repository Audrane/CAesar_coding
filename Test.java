package pis.hue1;

public class Test {
    /**
     * main zum testen
     * @param args
     */
    public static void main(String[] args) {
        Wuerfel we =new Wuerfel("Vergnuegen");
       Wuerfel w = new Wuerfel("Programmierung");
        Caesar c=new Caesar();
        //System.out.println(w.dekodiere("stfbetidneanuuunesihlehgwnerntzlteitrrgdewzreukhsueijohecibesuenhmtiendheaaeltnslonacdilmibuzmvcgnszesrigevennge"));
        String s = w.dekodiere("stfbetidneanuuunesihlehgwnerntzlteitrrgdewzreukhsueijohecibesuenhmtiendheaaeltnslonacdilmibuzmvcgnszesrigevennge");
        System.out.println(s);
        String se = we.dekodiere(s);
        System.out.println(se);
        new CodecGUI(w,c);
        //System.out.println(c.kodiere("audrane"));
        //System.out.print("sadasd");


    }
}
