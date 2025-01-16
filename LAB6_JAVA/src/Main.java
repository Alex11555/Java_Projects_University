public class Main {
    public static void main(String[] args) {
        PompaDeBenzina pompa=PompaDeBenzina.getInstance();
        Random random=new Random();

        while (pompa.getCantitateDisponibila() > 0) {
            // Creare Autoturism cu numar aleator de roti intre 1 si 4
            Autoturism autoturism = new Autoturism();
            autoturism.setRoti(random.nextInt(4) + 1);  // Număr random de roți între 1 și 4

            // Creare Camion cu numar aleator de roti intre 1 si 6
            Camion camion = new Camion();
            camion.setRoti(random.nextInt(6) + 1);  // Număr random de roți între 1 și 6

            // Creare fire de executie pentru autoturism si camion
            AutoturismThread autoturismThread = new AutoturismThread(autoturism, pompa);
            CamionThread camionThread = new CamionThread(camion, pompa);

            // Pornire fire de executie
            autoturismThread.start();
            camionThread.start();

            try {
                // Așteptăm ca ambele fire să termine înainte de a porni următoarele
                autoturismThread.join();
                camionThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}