public class Client {

    private String name;

    private Account account;

    public Client(String name){
        this.name= name;
    }

    public void setAccount(Account account){
        this.account = account;
    }

    public Account getAccount(){
        return account;
    }
}
