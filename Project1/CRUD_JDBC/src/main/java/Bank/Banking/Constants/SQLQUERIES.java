package Bank.Banking.Constants;

public enum SQLQUERIES {
    CHECKBALANCE("SELECT balance FROM accounts WHERE user_id = ?;"),
    UPDATEBALANCE("UPDATE accounts SET balance =  ? where user_id = ?"),
    ADDTRANSACTION("INSERT INTO transactions (account_id,transaction_type,amount) VALUES (?,?,?)"),
    GETACCOUNTDETAILS("SELECT account_id from accounts where user_id = ?"),
    VIEWTRANSACTION("SELECT transaction_id,transaction_type,amount,transaction_date,status from transactions  WHERE account_id = ?"),
    GETUSERDETAILS("SELECT username,full_name,email,phone,accounts.account_number,accounts.account_type,accounts.balance from users LEFT JOIN accounts ON accounts.user_id = users.user_id where users.user_id = ?;");

    final String query;

    SQLQUERIES(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }

}
