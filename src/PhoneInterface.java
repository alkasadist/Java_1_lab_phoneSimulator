public interface PhoneInterface {
    String getNumber();
    int getBalance();
    State getState();

    void setState(State state);

    void replenishBalance(int amount);
}
