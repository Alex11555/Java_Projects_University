package tomSiJerry;

// Clasa Soricel care extinde Soricelul
public class Soricel extends Soricelul {
    // Constructor pentru Soricel
    public Soricel(String nume) {
        super(nume); // Apelarea constructorului clasei părinte Soricelul
    }

    // Implementarea metodei abstracte scrieNumele
    @Override
    public void scrieNumele() {
        System.out.println("Soricel: " + super.nume); // Folosește numele din Soarece
    }
}
