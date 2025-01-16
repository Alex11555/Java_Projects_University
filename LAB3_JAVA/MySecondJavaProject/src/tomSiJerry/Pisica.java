package tomSiJerry;

public class Pisica extends Animal{
    private String nume;
    private String culoare;

    public Pisica(String name,String color, String Specie){
        super(specie);
        nume=name;
        culoare=color;
    }
    public String getNume() {
        return nume;
    }

    public String getCuloare() {
        return culoare;
    }
}
