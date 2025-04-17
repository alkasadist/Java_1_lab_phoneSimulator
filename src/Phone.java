public class Phone {
    private String number;
    private int balance = 0;
    protected enum State { WAITING, CALLING, IN_CALL, BLOCKED }
    private State state;

    public String getNumber() { return number; }
    public int getBalance() { return balance; }
    public State getState() { return state; }

    public Phone(String number) {
        this.number = number;
    }

    public void replenishBalance(int amount) {
        if (amount <= 0) {
            System.out.println("ERROR: wrong deposit amount.");
            return;
        }

        this.balance += amount;
    }

}
