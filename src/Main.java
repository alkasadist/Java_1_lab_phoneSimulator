public class Main {
    public static void main(String[] args) {
        PhoneCallMediator mediator = new PhoneCallMediator();

        PhoneProxy phone1 = new PhoneProxy("123", mediator);
        PhoneProxy phone2 = new PhoneProxy("456", mediator);
        System.out.println(phone1);
        System.out.println(phone2);

        phone1.replenishBalance(100);
        phone2.replenishBalance(100);

        phone1.call("456");
        System.out.println(phone1);
        System.out.println(phone2);

        phone2.answer();
        System.out.println(phone1);
        System.out.println(phone2);

        phone2.drop();
        System.out.println(phone1);
        System.out.println(phone2);

    }
}
