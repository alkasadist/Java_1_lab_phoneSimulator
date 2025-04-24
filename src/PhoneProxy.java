public class PhoneProxy implements PhoneInterface {
    private final Phone realPhone;
    private final PhoneCallMediator mediator;

    public PhoneProxy(String number, PhoneCallMediator mediator) {
        this.realPhone = Phone.getInstance(number);
        this.mediator = mediator;
        mediator.registerPhone(this);
    }

    private PhoneProxy(Builder builder) {
        this.realPhone = builder.realPhone;
        this.mediator = builder.mediator;
        mediator.registerPhone(this);
    }

    public static class Builder {
        private final Phone realPhone;
        private final PhoneCallMediator mediator;

        public Builder(String number, PhoneCallMediator mediator) {
            this.realPhone = Phone.getInstance(number);
            this.mediator = mediator;
        }

        public PhoneProxy build() { return new PhoneProxy(this); }

        public Builder setBalance(int amount) {
            if (amount <= 0) {
                System.out.println("INIT ERROR: wrong balance amount set.");
            } else {
                this.realPhone.replenishBalance(amount);
            }
            return this;
        }
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

    public String getConnectedPhoneNumber() {
        return realPhone.getConnectedPhoneNumber();
    }

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
        if (this.getBalance() < 50) {
            this.setState(State.BLOCKED);
        }
        if (this.getState() == State.BLOCKED) {
            System.out.println("ERROR: your phone is blocked, replenish your balance.");
            return;
        }
        if (this.getNumber().equals(toNumber)) {
            System.out.println("ERROR: you can't call yourself.");
            return;
        }
        if (this.getState() == State.CALLING ||
                this.getState() == State.IN_CALL) {
            System.out.println("ERROR: you are already calling someone.");
            return;
        }

        mediator.makeCall(this.getNumber(), toNumber);
    }

    public void answer() {
        if (this.realPhone.getState() != State.RINGING) {
            System.out.println("ERROR: nobody is calling you.");
            return;
        }

        mediator.answerCall(this);
    }

    public void drop() {
        if (this.realPhone.getState() != State.IN_CALL) {
            System.out.println("ERROR: you are not in the call.");
            return;
        }
        mediator.dropCall(this);
    }

    @Override
    public String toString() {
        return realPhone.toString();
    }
}
