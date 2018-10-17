package in.vakrangee.vkms.conn;

// Author: Shreayan Chaudhary
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUDFunctions {

    public void display() {
        Connection con = null;
        Statement statement1 = null;
        ResultSet result = null;
        try {
            //step 1 - connect to driver(load driver)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //step 2- form a connection
            con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=Pass@123;databaseName=TestDB");

            String sql1 = "SELECT * FROM dbo.employees_master";

            statement1 = con.createStatement();
            result = statement1.executeQuery(sql1);

            int count = 0;

            while (result.next()) {
                int emp_id = result.getInt(1);
                String emp_no = result.getString(2);
                String emp_name = result.getString("emp_name");

                String output = "Employee #%d: %s - %s";
                System.out.println(String.format(output, emp_id, emp_no, emp_name));
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                result.close();
            } catch (SQLException err) {
                err.getMessage();
            }
            try {
                statement1.close();
            } catch (SQLException err) {
                err.getMessage();
            }
            try {
                con.close();
            } catch (SQLException err) {
                err.getMessage();
            }
        }
    }

    public void delete(int n) {
        Connection con = null;
        PreparedStatement statement3 = null;
        try {
            //step 1 - connect to driver(load driver)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //step 2- form a connection
            con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=Pass@123;databaseName=TestDB");

            String sql3 = "DELETE FROM dbo.employees_master WHERE emp_id=?";

            statement3 = con.prepareStatement(sql3);
            statement3.setInt(1, n);

            int rowsDeleted = statement3.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                statement3.close();
            } catch (SQLException err) {
                err.getMessage();
            }
            try {
                con.close();
            } catch (SQLException err) {
                err.getMessage();
            }
        }
    }

    public int insert(int emp_id, String emp_no, String emp_name) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            //step 1 - connect to driver(load driver)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //step 2- form a connection
            con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=Pass@123;databaseName=TestDB");

            //step 3 - inserting to table
            String sql = "INSERT INTO dbo.employees_master(emp_id,emp_no,emp_name) VALUES(?,?,?); ";
            statement = con.prepareStatement(sql);
            statement.setInt(1, emp_id);
            statement.setString(2, emp_no);
            statement.setString(3, emp_name);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
                return 1;
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            err.printStackTrace();
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return 0;
        }// finally {
        /* try {
                
                statement.close();
            } catch (SQLException err) {
                err.getMessage();
            }
            try {
                con.close();
            } catch (SQLException err) {
                err.getMessage();
            }
        }*/ return 0;
    }

    public int insert(String emp_no, String emp_name, String emp_lastname, String gender, String dob, String email, 
            String phone, String address, String mar) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            //step 1 - connect to driver(load driver)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //step 2- form a connection
            con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=Pass@123;databaseName=TestDB");

            //step 3 - inserting to table
            String sql = "INSERT INTO dbo.employees_master(emp_no,emp_name,emp_lastname,gender,dob,email,phone,address,marital) VALUES(?,?,?,?,?,?,?,?,?); ";
            statement = con.prepareStatement(sql);
            statement.setString(1, emp_no);
            statement.setString(2, emp_name);
            statement.setString(3, emp_lastname);
            statement.setString(4, gender);
            statement.setString(5, dob);
            statement.setString(6, email);
            statement.setString(7, phone);
            statement.setString(8, address);
            statement.setString(9, mar);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
                return 1;
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            err.printStackTrace();
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public void update(int emp_id, String emp_no, String emp_name, String emp_newname) {
        PreparedStatement statement2 = null;
        Connection con = null;
        try {
            //step 1 - connect to driver(load driver)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //step 2- form a connection
            con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=Pass@123;databaseName=TestDB");

            String sql2 = "UPDATE dbo.employees_master SET emp_no=?, emp_name=?, emp_id=? WHERE emp_name=?";

            statement2 = con.prepareStatement(sql2);
            statement2.setString(1, emp_no);
            statement2.setString(2, emp_newname);
            statement2.setInt(3, emp_id);
            statement2.setString(4, emp_name);

            int rowsUpdated1 = statement2.executeUpdate();
            if (rowsUpdated1 > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                statement2.close();
            } catch (SQLException err) {
                err.getMessage();
            }
            try {
                con.close();
            } catch (SQLException err) {
                err.getMessage();
            }
        }
    }

}