package in.vakrangee.vkms.servlet;

// Author: Shreayan Chaudhary
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.*;
import java.sql.*;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "Display", urlPatterns = {"/Display"})
public class Display extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public String updateString(String str1, String str2) {
        return str1 + str2;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Display</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println(obj.toString());
//            out.println("<h1>Servlet Display at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=Pass@123;databaseName=TestDB");

                String genderS = request.getParameter("gender");
//                System.out.println(genderS);

//                String sql1 = "SELECT * FROM dbo.employees_master WHERE gender='?'";
//                Statement statement = con.createStatement();
//                ResultSet result = statement.executeQuery(sql1);
                String sql1 = "SELECT * FROM dbo.employees_master WHERE gender=?";
                PreparedStatement statement = con.prepareStatement(sql1);
                statement.setString(1, genderS);

//                int count = statement.executeQuery(sql1);
                ResultSet result = statement.executeQuery();

//                String sql2 = "SELECT * FROM dbo.employees_master WHERE gender='?';";
//                PreparedStatement statement3 = con.prepareStatement(sql2);
                JSONArray jarr = new JSONArray();

                while (result.next()) {
                    String empid = result.getString("emp_id");
                    String empno = result.getString("emp_no");
                    String fname = result.getString("emp_name");
                    String lname = result.getString("emp_lastname");
                    String dob = result.getString("dob");
                    String email = result.getString("email");
                    String phone = result.getString("phone");
                    String add = result.getString("address");
                    String gender = result.getString("gender");
                    String mar = result.getString("marital");

                    JSONObject obj = new JSONObject();
                    obj.put("First Name", fname);
                    obj.put("Last Name", lname);
                    obj.put("Employee Number", empno);
                    obj.put("Employee ID", empid);
                    obj.put("Gender", gender);
                    obj.put("Email ID", email);
                    obj.put("Date of Birth", dob == null ? "" : dob);
                    obj.put("Phone Number", phone);
                    obj.put("Address", add);
                    obj.put("Marital Status", mar);

                    jarr.put(obj);
                }
//                out.println("<!DOCTYPE html>");
//                out.println("<html>");
//                out.println("<head>");
//                out.println("<title>Display Servlet Page</title>");
//                out.println("</head>");
//                out.println("<body>");
//                out.println(jarr);
//                out.println("<br><br><br>");
//                out.println("</body>");
//                out.println("</html>");
//                response.sendRedirect("display.html");

                JSONObject returnObj = new JSONObject();
                returnObj.put("sEcho", (request.getParameter("sEcho")));
                returnObj.put("iTotalRecords", jarr.length());
                returnObj.put("iTotalDisplayRecords", jarr.length());
                returnObj.put("aaData", jarr);
                out.println(returnObj);
                //response.sendRedirect("display.html");
            } catch (SQLException err) {
                err.printStackTrace();
            } catch (ClassNotFoundException err) {
                err.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}