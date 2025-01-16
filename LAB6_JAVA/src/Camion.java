public class Camion extends Vehicul{

    private int roti=6;

    public void setRoti(int roti) {
        this.roti = roti;
    }

    @Override
    public void verificaMotor() throws ProblemeMotorException{
        System.out.println("Motorul camionului a fost verificat.");

    }

    @Override

    public void verificaRoti() throws ProblemeRotiException {
        if (roti != 6)
            throw new ProblemeRotiException();
        System.out.println("Rotile camionului au fost verificate.");
    }

    public void alimenteaza(PompaDeBenzina pompa, double cantitate) throws Exception{
        pompa.scoateCombustibil(cantitate);
        System.out.println("Camionul a fost alimentat cu "+cantitate+" litri.");
    }

}
