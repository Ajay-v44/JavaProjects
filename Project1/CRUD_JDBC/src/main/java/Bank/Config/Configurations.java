package Bank.Config;

import Bank.DB.DBConfig;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class Configurations {
    private static final String ENUM = "DO $$\n" + "BEGIN\n" + "    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'account_type_enum') THEN\n" + "        CREATE TYPE account_type_enum AS ENUM ('CURRENT', 'SAVINGS');\n" + "    END IF;\n" + "END\n" + "$$;";
    private static final String USER = "CREATE TABLE IF NOT EXISTS users (\n" + "    user_id BIGSERIAL PRIMARY KEY,\n" + "    username VARCHAR(50) UNIQUE NOT NULL,\n" + "    full_name VARCHAR(200) NOT NULL,\n" + "    email VARCHAR(100) UNIQUE NOT NULL,\n" + "    \n" + "    -- New Fields\n" + "    phone VARCHAR(15) UNIQUE, -- Added, ensuring phone numbers are unique\n" + "    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE', -- Added, e.g., 'ACTIVE', 'INACTIVE'\n" + "    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() -- Added, records user creation time\n" + "    -- Password hash column would typically be here (omitted for simplicity)\n" + ");";
    private static final String ACCOUNT = "CREATE TABLE IF NOT EXISTS accounts (\n" +
            "    account_id BIGSERIAL PRIMARY KEY,\n" +
            "    \n" +
            "    -- Foreign Key: Links this account to the user who owns it\n" +
            "    user_id BIGINT NOT NULL,\n" +
            "    \n" +
            "    account_number VARCHAR(20) UNIQUE NOT NULL, -- Unique identifier for the CLI app\n" +
            "    balance NUMERIC(15, 2) NOT NULL DEFAULT 0.00,\n" +
            "    \n" +
            "    -- Uses the custom ENUM type for account classification\n" +
            "    account_type account_type_enum NOT NULL, \n" +
            "    \n" +
            "    -- Audit field\n" +
            "    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),\n" +
            "    \n" +
            "    -- Constraint: Defines the foreign key relationship\n" +
            "    CONSTRAINT fk_account_user\n" +
            "        FOREIGN KEY (user_id)\n" +
            "        REFERENCES users (user_id)\n" +
            "        -- ON DELETE RESTRICT: Prevents a user from being deleted if they still have an active account.\n" +
            "        ON DELETE RESTRICT\n" +
            ");";
    private static final String TRANSACTION = " CREATE TABLE IF NOT EXISTS transactions (\n" + "    transaction_id BIGSERIAL PRIMARY KEY,\n" + "    \n" + "    -- Foreign Key: Links this transaction to the account it affects\n" + "    account_id BIGINT NOT NULL,\n" + "    \n" + "    transaction_type VARCHAR(10) NOT NULL, -- e.g., 'DEPOSIT', 'WITHDRAW', 'TRANSFER'\n" + "    amount NUMERIC(15, 2) NOT NULL,\n" + "    \n" + "    -- Audit field\n" + "    transaction_date TIMESTAMP WITH TIME ZONE DEFAULT NOW(),\n" + "    \n" + "    -- Status of the transaction\n" + "    status VARCHAR(10) NOT NULL DEFAULT 'COMPLETED', \n" + "    \n" + "    -- Constraint: Defines the foreign key relationship\n" + "    CONSTRAINT fk_transaction_account\n" + "        FOREIGN KEY (account_id)\n" + "        REFERENCES accounts (account_id)\n" + "        -- ON DELETE CASCADE: If an account is deleted, all historical transactions linked to it are also deleted.\n" + "        ON DELETE CASCADE\n" + ");";

    public static void initializeConf() throws Exception {
        try (Connection con = DBConfig.getDBConn()) {
            Statement st = con.createStatement();
            st.execute(ENUM);
            st.execute(USER);
            st.execute(ACCOUNT);
            st.execute(TRANSACTION);
        } catch (Exception e) {
            throw e;
        }
    }
}
