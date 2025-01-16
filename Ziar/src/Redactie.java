import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Redactie {

    ArrayList<Observator> observatoriAbonati = new ArrayList<>();

    public void adaugaObservator(Observator departament) {
        this.observatoriAbonati.add(departament);
    }

    public void stergeObservator(Observator departament) {
        this.observatoriAbonati.remove(departament);
    }

    public void adaugaStire(Stire stire) {
        try {
            File stirileZilei = new File("stirileZilei.txt");
            if (!stirileZilei.exists()) {
                stirileZilei.createNewFile();
            }
            FileWriter scrieStirileZilei = new FileWriter(stirileZilei, true);
            scrieStirileZilei.append(stire.getTitlu() + ": " + stire.getContinut() + "\n");
            scrieStirileZilei.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Observator departament : this.observatoriAbonati) {
            departament.primesteStire(stire);
        }
    }

    public void stergereObservator(Observator observator) {
        observatoriAbonati.remove(observator);
    }

}
