public abstract class Vehicul {

    public abstract void verificaMotor() throws ProblemeMotorException;
    public abstract void verificaRoti() throws ProblemeRotiException;

    public static class ProblemeMotorException extends Exception {
        public ProblemeMotorException() {
            super("Alerta motor");
        }
    }

    public static class ProblemeRotiException extends Exception {
        public ProblemeRotiException() {
            super("Alerta roti");
        }
    }

    public <T extends Vehicul> void pornesteAutovehicul(T vehicul){
        try{
            vehicul.verificaMotor();
            vehicul.verificaRoti();
            System.out.println("Vehiculul a fost pornit cu succes.");
        } catch(ProblemeMotorException e){
            System.out.println(e.getMessage());
        } catch(ProblemeRotiException e){
            System.out.println(e.getMessage());
        }
    }
}
