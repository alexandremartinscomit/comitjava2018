public class Account {

    private Client client;
    private int balance;
    private String number;
    private String type;

    public Account(String number, String type, Client client){
        this.number = number;
        this.type = type;
        this.client = client;
    }

    void deposit(int amount){
        balance = balance + amount;
    }

    int withdraw(int amount){
        if(balance >= amount) {
            balance = balance - amount;
            return amount;
        } else {
            System.out.println("Oh oh...no possible!");
            return 0;
        }
    }

    int transfer(int amount, Account account){
        if(balance >= amount) {
            balance = balance - amount;
            account.deposit(amount);
            return amount;
        } else {
            System.out.println("Oh oh...no possible!");
            return 0;
        }
    }

    int getBalance(){
        return balance;
    }

    Client getClient(){
        return client;
    }


}
