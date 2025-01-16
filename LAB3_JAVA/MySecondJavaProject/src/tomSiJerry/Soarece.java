package tomSiJerry;


public class Soarece extends Personaj {
    private String nume;

    public Soarece(String nume) {
        this.nume = nume;
    }

    @Override
    public void scrieNumele() {
        System.out.println("Soarece: " + nume);
    }
}

