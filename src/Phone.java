import java.util.Map;
import java.util.HashMap;

public class Phone implements PhoneInterface {
    private static final Map<String, Phone> instances = new HashMap<>();

    private final String number;
    private int balance = 0;
    private State state = State.WAITING;

    private Phone(String number) { this.number = number; }

    public static Phone getInstance(String number) {
        if (!instances.containsKey(number)) {
            instances.put(number, new Phone(number));
        }
        return instances.get(number);
    }

    @Override
    public String getNumber() { return number; }

    @Override
    public int getBalance() { return balance; }

    @Override
    public State getState() { return state; }

    public void setState(State state) { this.state = state; }

    public void replenishBalance(int amount) {
        this.balance += amount;
    }

    public void call(Phone otherPhone) {
            this.setState(State.CALLING);
            otherPhone.setState(State.RINGING);
    }

//    public void answer();
//    public void drop();
}
