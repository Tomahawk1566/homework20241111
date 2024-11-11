public class BankAccount {
    private Person owner;
private String IBAN;
double balance;

    public BankAccount(Person owner, String IBAN, double balance) {
        this.owner = owner;
        this.IBAN = IBAN;
        this.balance = balance;

    }

    public Person getOwner() {
        return owner;
    }

    public String getIBAN() {
        return IBAN;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "owner=" + owner +
                ", IBAN='" + IBAN + '\'' +
                ", balance=" + balance +
                '}';
    }
}
