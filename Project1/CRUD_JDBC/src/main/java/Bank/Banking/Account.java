package Bank.Banking;

import Bank.Banking.Constants.AccountType;
import Bank.DB.DBConfig;
import Bank.Schemas.CreateAccount;
import Bank.util.UniqueNumberGenerator;

import java.sql.*;

public class Account {

    public void createAccount(CreateAccount input) {
        Long userId;

        String checkUser = "SELECT user_id FROM users WHERE phone = ?;";
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
                }else {
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
                    System.out.println("Created User With Id " + userId);
                } else {
                    throw new SQLException("Failed To create User");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            try(PreparedStatement account = con.prepareStatement(createAccount)){
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
}
