public class CamionThread extends Thread {
    private Camion camion;
    private PompaDeBenzina pompa;

    public CamionThread(Camion camion, PompaDeBenzina pompa) {
        this.camion = camion;
        this.pompa = pompa;
    }

    @Override
    public void run() {
        try {
            // Alimentare camion cu benzina
            camion.alimenteaza(pompa, 100); // Alimentam cu 100 litri
            // Pornire camion
            camion.pornesteAutovehicul(camion);
        } catch (Exception e) {
            System.out.println("Eroare in firul de executie CamionThread: " + e.getMessage());
        }
    }
}