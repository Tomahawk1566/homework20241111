import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*

Дан список  BankAccount {Person owner, String IBAN, double balance). Класс Person состоит из {String fName, String lName String email). Используя stream необходимо получить:

map где ключом будет owner (Person), а значением будет баланс (Double)
map где ключом будет IBAN (String), а значением будет owner (Person)
map где ключом будет IBAN (String), а значением будет строка  J.Jack (т.e. первая буква fName и lName)

В рамках данной задачи person и account  уникальны и у каждого owner a единственный счет.



2 (чуть сложнее)
Дана Map <String, BankAccount,> ключ это email. Ваша задача используя стримы лист из имен 3 самых крупных клиентов (т.е. тех у кого на счету самый большой остаток)



 */
public class Main {
    public static void main(String[] args) {
        List<BankAccount> bankAccounts = List.of(
                new BankAccount(new Person("Jack", "Vorobey", "vorobei@mail.com"), "DE89370400440532013001", 0.00),
                new BankAccount(new Person("John", "Smnith", "SmT@mail.com"), "DE89370400440532013002", 100000),
                new BankAccount(new Person("Nick", "NIckson", "mickson@mail.com"), "DE89370400440532013003", 256789),
                new BankAccount(new Person("Sveta", "Ivanova", "ivanova@mail.com"), "DE89370400440532013004", 99999999.9),
                new BankAccount(new Person("Homer", "Simson", "simpson@mail.com"), "DE89370400440532013005", 77777777.7)

        );
        Map<Person, Double> mapByKeyPersonBalanceValue = bankAccounts.stream()//map где ключом будет owner (Person), а значением будет баланс (Double)
                .collect(Collectors.toMap(
                        BankAccount::getOwner,
                        BankAccount::getBalance
                ));
        Map<String, Person> mapByKeyIBANOwnerValue = bankAccounts.stream()//map где ключом будет IBAN (String), а значением будет owner (Person)
                .collect(Collectors.toMap(
                        BankAccount::getIBAN,
                        BankAccount::getOwner
                ));
        Map<String, String> mapByKeyPersonStringValue = bankAccounts.stream()//map где ключом будет IBAN (String), а значением будет строка  J.Jack (т.e. первая буква fName и lName)
                .collect(Collectors.toMap(
                        BankAccount::getIBAN,
                     bankAccount -> bankAccount.getOwner().getfName().charAt(0)+"."+bankAccount.getOwner().getlName()
                ));
        System.out.println(mapByKeyPersonBalanceValue);
        System.out.println("-----------------------------------------------");
        System.out.println(mapByKeyIBANOwnerValue);
        System.out.println("-----------------------------------------------");
        System.out.println(mapByKeyPersonStringValue);
        //Task #2
        //Дана Map <String, BankAccount,> ключ это email. Ваша задача используя стримы лист из имен 3 самых крупных клиентов (т.е. тех у кого на счету самый большой остаток)
        Map<String, BankAccount> emailToAccountMap = bankAccounts.stream()
                .sorted((a, b) -> b.getBalance() > a.getBalance() ? 1 : (b.getBalance() < a.getBalance() ? -1 : 0))
                .limit(3)
                .collect(Collectors.toMap(
                        account -> account.getOwner().getEmail(),
                        account -> account

                ));
        System.out.println(emailToAccountMap);



    }
}