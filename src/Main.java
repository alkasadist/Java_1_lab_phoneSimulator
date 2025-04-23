public class Main {
    public static void main(String[] args) {
        PhoneCallMediator mediator = PhoneCallMediator.getInstance();

        PhoneProxy phone1 = new PhoneProxy.Builder("123", mediator)
                .setBalance(100)
                .build();
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
