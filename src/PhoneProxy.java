public class PhoneProxy implements PhoneInterface {
    private final Phone realPhone;
    private final PhoneCallMediator mediator;

    public PhoneProxy(String number, PhoneCallMediator mediator) {
        this.realPhone = Phone.getInstance(number);
        this.mediator = mediator;
        mediator.registerPhone(this);
    }

    @Override
    public String getNumber() { return realPhone.getNumber(); }

    @Override
    public int getBalance() { return realPhone.getBalance(); }

    @Override
    public State getState() { return realPhone.getState(); }

    public void setState(State state) {
        realPhone.setState(state);
    }

    public String getConnectedPhoneNumber() { return realPhone.getConnectedPhoneNumber(); }

    public void setConnectedPhoneNumber(String connectedPhoneNumber) {
        realPhone.setConnectedPhoneNumber(connectedPhoneNumber);
    }

    @Override
    public void replenishBalance(int amount) {
        if (amount <= 0) {
            System.out.println("ERROR: wrong deposit amount.");
            return;
        }
        realPhone.replenishBalance(amount);
        System.out.println(amount + " rubbles added to the balance.");
    }

    @Override
    public void decreaseBalance(int amount) {
        if (amount <= 0) {
            System.out.println("ERROR: wrong decrease amount.");
            return;
        }
        realPhone.decreaseBalance(amount);
    }

    public void call(String toNumber) {
        mediator.makeCall(this.getNumber(), toNumber);
    }

    public void answer() {
        mediator.answerCall(this);
    }

    public void drop() { mediator.dropCall(this); }

    @Override
    public String toString() {
        return realPhone.toString();
    }
}
