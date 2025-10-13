package Bank.Schemas;

public class CreateAccount {
    String name;
    String fullname;
    String email;
    String phone;

    public CreateAccount(String name, String fullname, String email, String phone) {
        this.name = name;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
