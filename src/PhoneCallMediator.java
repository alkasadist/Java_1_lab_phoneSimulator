import java.util.HashMap;
import java.util.Map;

public class PhoneCallMediator {
    private final Map<String, PhoneProxy> phones = new HashMap<>();

    public void registerPhone(PhoneProxy phone) {
        phones.put(phone.getNumber(), phone);
    }

    public void makeCall(String fromNumber, String toNumber) {
        if (phones.get(toNumber) == null) {
            System.out.println("CALL ERROR: phone number " + toNumber + " not found.");
            return;
        }

        PhoneProxy fromPhone = phones.get(fromNumber);
        PhoneProxy toPhone = phones.get(toNumber);

        if (fromPhone.getBalance() < 50) {
            fromPhone.setState(State.BLOCKED);
        }
        if (fromPhone.getState() == State.BLOCKED) {
            System.out.println("CALL ERROR: your phone is blocked, please replenish your balance.");
            return;
        }
        if (fromNumber.equals(toNumber)) {
            System.out.println("CALL ERROR: you can't call yourself.");
            return;
        }
        if (fromPhone.getState() == State.CALLING ||
            fromPhone.getState() == State.IN_CALL) {
            System.out.println("CALL ERROR: you are already calling someone.");
            return;
        }
        if (toPhone.getState() == State.IN_CALL ||
            toPhone.getState() == State.CALLING ||
            toPhone.getState() == State.RINGING) {
            System.out.println("CALL ERROR: this number is busy, please call again later.");
            return;
        }

        fromPhone.decreaseBalance(50);
        fromPhone.setState(State.CALLING);
        toPhone.setState(State.RINGING);
        fromPhone.setConnectedPhoneNumber(toPhone.getNumber());
        toPhone.setConnectedPhoneNumber(fromPhone.getNumber());

        System.out.println(fromNumber + " is calling " + toNumber);
    }

    public void answerCall(PhoneProxy caller) {
        PhoneProxy callee = phones.get(caller.getConnectedPhoneNumber());

        if (caller.getConnectedPhoneNumber().equals(callee.getNumber()) &&
                caller.getConnectedPhoneNumber().equals(callee.getNumber())) {
            caller.setState(State.IN_CALL);
            callee.setState(State.IN_CALL);

            System.out.println(callee.getNumber() + " answered the " + caller.getNumber());
        } else {
            System.out.println("ANSWER ERROR: specified caller is invalid.");
        }

    }

    public void dropCall(PhoneProxy caller) {
        PhoneProxy callee = phones.get(caller.getConnectedPhoneNumber());

        if (caller.getConnectedPhoneNumber().equals(callee.getNumber()) &&
                caller.getConnectedPhoneNumber().equals(callee.getNumber())) {
            caller.setConnectedPhoneNumber(null);
            caller.setState(State.WAITING);
            callee.setConnectedPhoneNumber(null);
            callee.setState(State.WAITING);

            System.out.println(callee.getNumber() + " dropped the " + caller.getNumber());
        } else {
            System.out.println("DROP ERROR: specified caller is invalid.");
        }
    }
}
