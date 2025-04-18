public interface PhoneInterface {
    String getNumber();
    int getBalance();
    State getState();

    void replenishBalance(int amount);
}
