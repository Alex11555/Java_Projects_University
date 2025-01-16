public class AutoturismThread extends Thread {
    private Autoturism autoturism;
    private PompaDeBenzina pompa;
    public AutoturismThread(Autoturism autoturism,PompaDeBenzina pompa){
        this.autoturism=autoturism;
        this.pompa=pompa;
    }

    @Override
    public void run(){
        try{
            autoturism.alimenteaza(pompa,50);
            autoturism.pornesteAutovehicul(autoturism);
        } catch (Exception e){
            System.out.println("Eroare in firul de executie AutoturismThread "+e.getMessage());
        }
    }
}
