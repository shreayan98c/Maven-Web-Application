package in.vakrangee.vkms.servlet;

// Author: Shreayan Chaudhary
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "response", urlPatterns = {"/response"})
public class DBServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String fname = request.getParameter("firstname");
        String lname = request.getParameter("lastname");
        String empno = request.getParameter("empno");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String add = request.getParameter("add");
        String mar = request.getParameter("mar");
        System.out.println(fname);
        System.out.println(lname);
        System.out.println(empno);
        //System.out.println(gender);
        if (gender.equals("male")) {
            System.out.println("male");
        } else if (gender.equals("female")) {
            System.out.println("female");
        } else {
            System.out.println("other");
        }
        System.out.println(dob);
        System.out.println(email);
        System.out.println(phone);
        System.out.println(add);
        if (mar.equals("single")) {
            System.out.println("single");
        } else if (mar.equals("married")) {
            System.out.println("married");
        } else {
            System.out.println("other");
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DBServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DBServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            try {
//                test.DBConnect ob = new test.DBConnect();
                in.vakrangee.vkms.conn.CRUDFunctions obj = new in.vakrangee.vkms.conn.CRUDFunctions();

                int test = obj.insert(empno, fname, lname, gender, dob, email, phone, add, mar);
                if (test == 1) {
                    System.out.println("Successfully inserted!!");
                    response.sendRedirect("Display");
                    //request.getRequestDispatcher("Display");
                } else {
                    System.out.println("Could not insert!");
                }
            } catch (Exception error) {
                System.out.println(error.getMessage());
                error.printStackTrace();
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();
            request.getRequestDispatcher("/error.html");
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