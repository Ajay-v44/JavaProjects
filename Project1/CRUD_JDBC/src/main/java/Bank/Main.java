package Bank;

import Bank.Config.Configurations;

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
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
