public class Bank {


    public static void main(String[] args){

        Client client = new Client("Alex");

        Account account = new Account("123","savings", client);

        Client client2 = new Client("Paul");

        Account account2 = new Account("456","savings", client2);

        System.out.println("Current Balande: "+account.getBalance());

        account.deposit(1000);

        System.out.println("Current Balande: "+account.getBalance());

        account.deposit(2000);

        System.out.println("Current Balande: "+account.getBalance());

        int value = account.withdraw(500);

        System.out.println("Value received from withdraw: "+ value);
        System.out.println("Current Balande: "+account.getBalance());

        int value2 = account.transfer(100, account2);

        System.out.println(value2);

        System.out.println("Current Balande: "+account.getBalance());
        System.out.println("Current Balande2: "+account2.getBalance());


    }


}
