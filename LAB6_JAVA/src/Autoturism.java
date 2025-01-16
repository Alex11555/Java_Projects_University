public class Autoturism extends Vehicul{
    private int roti=4;

    public void setRoti(int roti) {
        this.roti = roti;
    }

    @Override
    public void verificaMotor() throws ProblemeMotorException{
        System.out.println("Motorul autoturismului a fost verificat.");

    }

    @Override

    public void verificaRoti() throws ProblemeRotiException {
        if (roti != 4)
            throw new ProblemeRotiException();

        System.out.println("Rotile autoturismului au fost verificate.");
    }

    public void alimenteaza(PompaDeBenzina pompa, double cantitate) throws Exception{
        pompa.scoateCombustibil(cantitate);
        System.out.println("Autoturismul a fost alimentat cu "+cantitate+" litri.");
    }

}
