import java.util.Scanner;

public class SmartLocker {
    private String lockerID;
    private boolean isOpen;
    private boolean hasItem;
    private String accessCode;

    public SmartLocker(String lockerID) {
        this.lockerID = lockerID;
        isOpen = false;
        hasItem = false;
        accessCode = "";
    }

    public boolean depositItem(String accessCode) {
        if (!hasItem && !isOpen) {
            this.hasItem = true;
            this.accessCode = accessCode;
            return true;
        }
        return false;
    }

    public void openLocker(String accessCode) {
        if (this.accessCode != null && this.accessCode.equals(accessCode)) {
            this.isOpen = true;
        } else {
            throw new IllegalArgumentException("Pin-ul introdus este incorect.");
        }
    }

    public void closeLocker() {
        this.isOpen = false;
    }

    public boolean retrieveItem(String accessCode) {
        try {
            openLocker(accessCode);

            if (this.hasItem) {
                this.hasItem = false;
                this.accessCode = "";
                return true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Setati ID-ul lockerului: ");
        String lockerId = input.nextLine();

        System.out.print("Setati PIN-ul pentru depozitare: ");
        String pinCorect = input.nextLine();

        SmartLocker smartLocker = new SmartLocker(lockerId);

        if (smartLocker.depositItem(pinCorect)) {
            System.out.println("Item deposited.");
        }

        int incercari = 0;
        boolean accesat = false;

        while (incercari < 3 && !accesat) {
            System.out.print("Introduceti PIN-ul pentru extragere: ");
            String pinIncercat = input.nextLine();

            if (smartLocker.retrieveItem(pinIncercat)) {
                System.out.println("Item retrieved.");
                accesat = true;
            } else {
                incercari++;
                System.out.println("Incercari ramase: " + (3 - incercari));
            }
        }

        if (!accesat) {
            System.out.println("Acces respins. Ati introdus PIN-ul gresit de 3 ori.");
        }

        input.close();
    }
}