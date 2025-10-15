package Bank.Banking;

import Bank.Config.Configurations;
import Bank.Schemas.CreateAccount;

import java.util.Scanner;

public class Operations {
    Scanner sc = new Scanner(System.in);

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
    static {
        //    Load Conf
        try {
            Configurations.initializeConf();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        try {
            Account account = new Account();
            while (true) {
                System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::");
                System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::");
                System.out.println(":::::::::::::::::SWISS BANK:::::::::::::::::::::");
                System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::");
                System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::");
                System.out.println("Welcome ,\n Choose Your Option \n 1 -  Login \n 2 -  Create Account \n 3 - Exit .\n");
                int inpOne = sc.nextInt();
                try {
                    if (inpOne == 1) {
                        System.out.println("Enter Phone Number");
                        if (account.logginUser(sc.next())) {
                            while (true) {
                                System.out.println("Enter You Choice \n 1 - View Balance \n 2 - View Transactions \n 3- Deposit \n 4 - Withdraw \n 5 - View Account Details \n 6 - Exit");
                                int inpTwo = sc.nextInt();
                                if (inpTwo == 1) {
                                    System.out.println("Your Balance is ₹ " + account.getBalance());
                                } else if (inpTwo == 2) {
                                    account.displayTransactions();
                                } else if (inpTwo == 3) {
                                    System.out.println("Enter The Amount To Deposit ");
                                    account.makeDeposit(sc.nextFloat());
                                } else if (inpTwo == 4) {
                                    System.out.println("Enter The Amount To Withdraw ");
                                    account.makeWithdraw(sc.nextFloat());
                                } else if (inpTwo == 5) {
                                    account.getMyDetails();
                                } else if (inpTwo == 6) {
                                    break;
                                } else {
                                    System.out.println("Invalid Input Please Provide A Valid Response");
                                }
                            }
                        }
                    } else if (inpOne == 2) {
                        System.out.println("Enter Your Name , FullName , Email , Phone .");
                        account.createAccount(new CreateAccount(sc.next(), sc.next(), sc.next(), sc.next()));
                    } else if (inpOne == 3) {
                        break;
                    } else {
                        System.out.println("Invalid Input Please Provide A Valid Response");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
