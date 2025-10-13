package Bank;

import Bank.Banking.Account;
import Bank.Config.Configurations;
import Bank.Schemas.CreateAccount;

public class Main {
    public static void main(String[] args) {
        /*
        User can-
            1) Create A new Account
                1) Create User
                2) Create Account
            2) Login To Account
                1) Withdraw
                    1) Add Translation
                2) Deposit
                    1) Add Translation
                3) View Transactions
     */
        try {
            //    Load Conf
            Configurations.initializeConf();
            Account obj=new Account();
            obj.createAccount(new CreateAccount("Ajay","Ajay v","vajay@gmail.com","891275"));
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
