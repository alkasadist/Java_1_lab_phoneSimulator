import java.util.HashMap;
import java.util.Map;

public class PhoneCallMediator {
    private final Map<String, PhoneProxy> phones = new HashMap<>();
//    private final Map<PhoneProxy, PhoneProxy> establishedCalls = new HashMap<>();

    public void registerPhone(PhoneProxy phone) {
        phones.put(phone.getNumber(), phone);
    }

    public void makeCall(String fromNumber, String toNumber) {
        PhoneProxy fromPhone = phones.get(fromNumber);
        PhoneProxy toPhone = phones.get(toNumber);

        if (fromNumber.equals(toNumber)) {
            System.out.println("ERROR: you can't call yourself.");
            return;
        }
        if (fromPhone.getState() == State.BLOCKED) {
            System.out.println("ERROR: your phone is blocked, please replenish your balance.");
            return;
        }
        if (fromPhone.getState() == State.CALLING ||
            fromPhone.getState() == State.IN_CALL) {
            System.out.println("ERROR: you are already calling someone.");
            return;
        }
        if (toPhone.getState() == State.IN_CALL ||
            toPhone.getState() == State.CALLING ||
            toPhone.getState() == State.RINGING) {
            System.out.println("ERROR: this number is busy, please call again later.");
            return;
        }

        fromPhone.setState(State.CALLING);
        toPhone.setState(State.RINGING);
        fromPhone.setConnectedPhoneNumber(toPhone.getNumber());
        toPhone.setConnectedPhoneNumber(fromPhone.getNumber());

        System.out.println(fromNumber + " is calling " + toNumber);
    }

    public void answer(PhoneProxy caller) {
        PhoneProxy callee = phones.get(caller.getConnectedPhoneNumber());

        if (caller.getConnectedPhoneNumber().equals(callee.getNumber()) &&
                caller.getConnectedPhoneNumber().equals(callee.getNumber())) {
            caller.setState(State.IN_CALL);
            callee.setState(State.IN_CALL);

            System.out.println(callee.getNumber() + " answered the " + caller.getNumber());
        }
    }

}
