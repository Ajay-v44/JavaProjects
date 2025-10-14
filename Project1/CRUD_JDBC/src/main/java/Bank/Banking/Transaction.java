package Bank.Banking;

import Bank.Banking.Constants.SQLQUERIES;
import Bank.Banking.Constants.TRANSACTIONTYPE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction {

    private static long getAccountID(long userId, Connection con) throws SQLException {
        PreparedStatement statement = con.prepareStatement(SQLQUERIES.GETACCOUNTDETAILS.getQuery());
        statement.setLong(1, userId);
        ResultSet result = statement.executeQuery();
        if (result.next())
            return result.getLong("id");
        else
            throw new SQLException("No ACCOUNT FOUND FOR PARTICULAR USER");
    }

    public static void addTransaction(long userId, Connection con, TRANSACTIONTYPE ts, float amount) throws SQLException {

        long accountId = getAccountID(userId, con);

        PreparedStatement addTrans = con.prepareStatement(SQLQUERIES.ADDTRANSACTION.getQuery());
        addTrans.setLong(1, accountId);
        addTrans.setString(2, ts.toString());
        addTrans.setFloat(3, amount);

        ResultSet rs = addTrans.executeQuery();
        if (rs.next())
            System.out.println("Successfully Added Transaction Log");
        else
            throw new SQLException("Unable To Add Transaction");
    }

    public static void viewTransactions(long userId, Connection con) throws SQLException {
        long accountId = getAccountID(userId, con);
        PreparedStatement getTrans = con.prepareStatement(SQLQUERIES.VIEWTRANSACTION.getQuery());
        getTrans.setLong(1,accountId);
        ResultSet rs = getTrans.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " - " + rs.getString(2) + " - " + rs.getFloat(3) + " - " + rs.getString(4) + " - " + rs.getString(5) + " . ");
        }
    }


}
