package tomSiJerry;

// Extinderea clasei abstracte Personaj
public class Caine extends Animal {
    // Atributele
    private String nume;
    private String culoare;

    // Constructor 1: fără parametri
    public Caine() {
        this.nume = "Necunoscut";
        this.culoare = "Necunoscut";
    }

    // Constructor 2: cu nume
    public Caine(String nume) {
        this.nume = nume;
        this.culoare = "Necunoscut"; // culoare implicită
    }

    // Constructor 3: cu nume și culoare
    public Caine(String nume, String culoare) {
        this.nume = nume;
        this.culoare = culoare;
    }

    // Constructor 4: cu nume, culoare și un alt parametru (ex: o descriere)
    public Caine(String nume, String culoare, String descriere) {
        this.nume = nume;
        this.culoare = culoare;
        System.out.println("Descriere: " + descriere);
    }

    // Implementarea metodei abstracte scrieNumele
    @Override
    public void scrieNumele() {
        System.out.println("Caine: " + nume + ", Culoare: " + culoare);
    }
}
