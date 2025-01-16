package tomSiJerry;

public class Animal extends Personaj {
    public String specie;

    public Animal(String specie) {
        this.specie = specie;
    }

    public Animal() {
        specie = "Nedefinit";
    }

    public String getSpecie() {
        return specie;
    }

    @Override
    public void scrieNumele() {
        System.out.println("Acesta este un animal de specie: " + getSpecie());
    }
}
