package Bank.Banking;

import Bank.Banking.Constants.AccountType;
import Bank.Banking.Constants.SQLQUERIES;
import Bank.Banking.Constants.TRANSACTIONTYPE;
import Bank.DB.DBConfig;
import Bank.Schemas.CreateAccount;
import Bank.util.UniqueNumberGenerator;

import java.sql.*;

public class Account {
    Long userId;
    String userName;

    String checkUser = "SELECT user_id,username FROM users WHERE phone = ?;";

    public void createAccount(CreateAccount input) {

        String addUser = "INSERT INTO users (username, full_name, email, phone)\n" +
                "VALUES (?, ?, ?, ?)\n" +
                "RETURNING user_id;";
        String createAccount = "INSERT INTO accounts (user_id, account_number, account_type)\n" +
                "VALUES (?, ?, CAST(? AS account_type_enum))\n" +
                "RETURNING account_id;";

        try (Connection con = DBConfig.getDBConn()) {

            try (PreparedStatement stmt = con.prepareStatement(checkUser)) {
                stmt.setString(1, input.getPhone());
                ResultSet result = stmt.executeQuery();
                if (result.next()) {
                    System.out.println("User And Account Already Exists");
                    return;
                } else {
                    System.out.println("New User");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            try (PreparedStatement st = con.prepareStatement(addUser)) {
                st.setString(1, input.getName());
                st.setString(2, input.getFullname());
                st.setString(3, input.getEmail());
                st.setString(4, input.getPhone());
                ResultSet res = st.executeQuery();
                if (res.next()) {
                    userId = res.getLong(1);
                    userName = input.getName();
                    System.out.println("Created User With Id " + userId);
                } else {
                    throw new SQLException("Failed To create User");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            try (PreparedStatement account = con.prepareStatement(createAccount)) {
                account.setLong(1, userId);
                account.setString(2, UniqueNumberGenerator.generateUniqueNumber());
                account.setString(3, String.valueOf(AccountType.CURRENT));
                account.executeQuery();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean logginUser(String phoneNumber) {
        try (Connection con = DBConfig.getDBConn()) {
            try (PreparedStatement stmt = con.prepareStatement(checkUser)) {
                stmt.setString(1, phoneNumber);
                ResultSet result = stmt.executeQuery();
                if (result.next()) {
                    userId = result.getLong(1);
                    userName = result.getString("username");
                    System.out.println("Welcome Mr/Mrs " + result.getString("username"));
                    return true;
                } else {
                    throw new SQLException("User Doesn't Exists");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public float getBalance() {
        try (Connection con = DBConfig.getDBConn()) {
            PreparedStatement stmt = con.prepareStatement(SQLQUERIES.CHECKBALANCE.getQuery());
            stmt.setLong(1, userId);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return result.getFloat(1);
            }
            throw new SQLException("Unable To Fetch Balance .Please Try Again.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void makeDeposit(float amount) {
        if (userId == null) {
            System.out.println("User Not Authenticated");
            return;
        }
        try (Connection con = DBConfig.getDBConn()) {
            PreparedStatement stmt = con.prepareStatement(SQLQUERIES.UPDATEBALANCE.getQuery());
            stmt.setFloat(1, amount);
            stmt.setLong(2, userId);
            if (stmt.execute()) {
                System.out.println("Account Updated With New Balance " + getBalance());
                Transaction.addTransaction(userId, con, TRANSACTIONTYPE.DEPOSIT, amount);
                return;
            }
            throw new SQLException("Unable To Make Deposit .Please Try Again.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void makeWithdraw(float amount) {
        if (userId == null) {
            System.out.println("User Not Authenticated");
            return;
        }
        try (Connection con = DBConfig.getDBConn()) {
            float balance = getBalance();
            if (balance < amount)
                throw new SQLException("UN-SUFFICENT BALANCE");
            PreparedStatement stmt = con.prepareStatement(SQLQUERIES.UPDATEBALANCE.getQuery());
            stmt.setFloat(1, balance - amount);
            stmt.setLong(2, userId);
            if (stmt.execute()) {
                System.out.println("Account Updated With New Balance " + getBalance());
                Transaction.addTransaction(userId, con, TRANSACTIONTYPE.WITHDRAWAL, amount);
                return;
            }
            throw new SQLException("Unable To Make Withdraw .Please Try Again.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void displayTransactions() {
        if (userId == null) {
            System.out.println("User Not Authenticated");
            return;
        }
        try (Connection con = DBConfig.getDBConn()) {
            Transaction.viewTransactions(userId, con);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
