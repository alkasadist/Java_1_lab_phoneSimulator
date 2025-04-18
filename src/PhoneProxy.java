public class PhoneProxy implements PhoneInterface {
    private final Phone realPhone;

    public PhoneProxy(String number) {
        this.realPhone = Phone.getInstance(number);
    }

    @Override
    public String getNumber() { return realPhone.getNumber(); }

    @Override
    public int getBalance() { return realPhone.getBalance(); }

    @Override
    public State getState() { return realPhone.getState(); }

    private void setState(State state) {
        realPhone.setState(state);
    }

    @Override
    public void replenishBalance(int amount) {
        if (amount <= 0) {
            System.out.println("ERROR: wrong deposit amount.");
            return;
        }
        realPhone.replenishBalance(amount);
        System.out.println(amount + " rubbles added to the balance.\nCurrent balance: " + realPhone.getBalance());
    }

    public void call(PhoneProxy otherPhoneProxy) {
        if (this.getNumber().equals(otherPhoneProxy.getNumber())) {
            System.out.println("ERROR: you can't call yourself.");
        }
        else if (this.getState() == State.BLOCKED) {
            System.out.println("ERROR: your phone is blocked, please replenish your balance.");
        }
        else if (this.getState() == State.IN_CALL ||
                this.getState() == State.CALLING) {
            System.out.println("ERROR: you are already calling someone.");
        }
        else if (otherPhoneProxy.getState() == State.IN_CALL ||
                otherPhoneProxy.getState() == State.CALLING ||
                otherPhoneProxy.getState() == State.RINGING) {
            System.out.println("ERROR: this number is busy, please call again later.");
        }
        else {
            realPhone.call(otherPhoneProxy.realPhone);
        }
    }
}
