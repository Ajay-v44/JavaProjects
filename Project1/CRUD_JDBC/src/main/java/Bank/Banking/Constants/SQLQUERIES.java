package Bank.Banking.Constants;

public enum SQLQUERIES {
    CHECKBALANCE("SELECT amount FROM accounts WHERE user_id = ?;"),
    UPDATEBALANCE("UPDATE accounts SET balance = balance + ? where user_id ?"),
    ADDTRANSACTION("INSERT INTO transactions (account_id,transaction_type,amount) value (?,?,?)"),
    GETACCOUNTDETAILS("SELECT account_id from accounts where user_id = ?"),
    VIEWTRANSACTION("SELECT transaction_id,transaction_type,amount,transaction_date,status from transactions  WHERE account_id = ?");

    final String query;

    SQLQUERIES(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }

}
