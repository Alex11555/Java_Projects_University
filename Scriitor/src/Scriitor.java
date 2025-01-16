import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.IOException;


public class Scriitor {
    private String nume;
    private String prenume;
    private LocalDate dataDebutuluiLiterar;
    private String titlu;
    private int numarVolume;
    private String[] capitole;
    public Scriitor(String nume,String prenume,int an, int luna, int zi)
    {
        this.nume=nume;
        this.prenume=prenume;
        this.dataDebutuluiLiterar=LocalDate.of(an,luna,zi);
    }
    public Scriitor(){
        this.nume="John";
        this.prenume="Doe";
        this.dataDebutuluiLiterar=LocalDate.now();
    }
    public void cautaScriitori(String caleFisier) {
        String numeComplet = prenume + nume;
        boolean gasit = false;
        try (BufferedReader br = new BufferedReader(new FileReader(caleFisier))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] parti = linie.split(" ");
                if (parti[0].equals(numeComplet)) {
                    gasit = true;
                    System.out.println("Scriitorul " + this.nume + " " + this.prenume + " a fost gasit in fisier");
                    titlu = parti[1];
                    numarVolume = Integer.parseInt(parti[2]);
                    capitole = parti[3].split(",");
                    if (numarVolume == 1)
                        System.out.println("Scriitorul " + this.nume + " " + this.prenume + " a scris opera " + titlu + " cu un singur volum");
                    else
                        System.out.println("Scriitorul " + this.nume + " " + this.prenume + " a scris opera \"" + titlu + "\" cu " + numarVolume + " volume!");
                    break;
                }
            }
            if (!gasit)
                System.out.println("Scriitorul " + nume + " " + prenume + " nu a fost gasit in fisier!");
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului: " + e.getMessage());
        }
    }

    public void creeazaFisier() {
        File fisier = new File("./" + titlu);
        if (fisier.mkdir()) {
            System.out.println("Folderul a fost creat: " + fisier.getAbsolutePath());
        } else
            System.out.println("Folderul nu a fost creat (poate există deja).");

        for (int i = 1; i <= numarVolume; i++) {

            String numeVolum = "Volum" + i;
            File folderVolum = new File(fisier, numeVolum);

            if (folderVolum.mkdir()) {
                System.out.println("Folderul pentru " + numeVolum + " a fost creat: " + folderVolum.getAbsolutePath());
            } else {
                System.out.println("Folderul pentru " + numeVolum + " nu a fost creat (poate există deja).");
            }
            for (int j = 1; j <= Integer.parseInt(capitole[i - 1]); j++) {
                String numeCapitol = "Capitol" + j + ".txt";
                File capitolul = new File(folderVolum, numeCapitol);

                try {
                    if (capitolul.createNewFile()) {
                        System.out.println("Fisierul pentru " + numeCapitol + " a fost creat: " + capitolul.getAbsolutePath());
                    } else {
                        System.out.println("Fisierul pentru " + numeCapitol + " nu a fost creat (poate există deja).");
                    }
                } catch (IOException e) {
                    System.out.println("Eroare la crearea fisierului: " + e.getMessage());
                }
        }
        }
    }
    public void afiseazaInformatii() {
        System.out.println("Nume: " + nume);
        System.out.println("Prenume: " + prenume);
        System.out.println("Data debutului literar: " + dataDebutuluiLiterar);
    }
}
