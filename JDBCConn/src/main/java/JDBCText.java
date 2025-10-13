import java.sql.*;

public class JDBCText {
    public static void main(String[] args) {
        try(Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Demo","postgres","1234");){
//            Class.forName("org.postgresql.Driver");
            System.out.println("Connected");
            String query="SELECT * FROM public.student ORDER BY id ASC ";
            Statement st= con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                System.out.println(rs.getString("name")+" : "+rs.getString("age"));
            }
////            Insert Quesry
//            String insertQuery="INSERT INTO public.student( id, name, age) VALUES (4, 'rijay', 23);";
//            boolean rs2=st.execute(insertQuery);
//            System.out.println(rs2);


//            Prepared Statement
            String newQuery="update student set name = ? where id = ? ";
            PreparedStatement pstm=con.prepareStatement(newQuery);
            pstm.setString(1,"Ramu");
            pstm.setInt(2,3);
            pstm.execute();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
