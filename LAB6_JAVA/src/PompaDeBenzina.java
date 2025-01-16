public class PompaDeBenzina {
    private static PompaDeBenzina instance;

    private int cantitateDisponibila;

    private PompaDeBenzina() {
        this.cantitateDisponibila = 166;
    }

    public static PompaDeBenzina getInstance() {
        if (instance == null) {
            instance = new PompaDeBenzina();
        }
        return instance;
    }

    public void scoateCombustibil(double cantitate) throws Exception {
        if (cantitate > cantitateDisponibila) {
            throw new Exception("Combustibil insuficient in pompa.");
        }
        cantitateDisponibila -= cantitate;
        System.out.println("Cantitatea ramasa in pompa: " + cantitateDisponibila + " litri.");
    }


    public int getCantitateDisponibila() {
        return cantitateDisponibila;
    }
}
