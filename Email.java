package in.vakrangee.vkms.servlet;

// Author: Shreayan Chaudhary
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.*;

@WebServlet(name = "Email", urlPatterns = {"/Email"})
public class Email extends HttpServlet {

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

        try (PrintWriter out = response.getWriter()) {

//            String dataSrc = request.getParameter("data");
//            JSONArray jsonarray = new JSONArray(dataSrc);
//            for(int i=0;i<jsonarray.length();i++){
//                JSONObject jsonobject = jsonarray.getJSONObject(i);
//                String mail = jsonobject.getString("Email ID");
//            String array[] = request.getParameterValues("array");
//            String mail = request.getParameter("check1");
//            String sms = request.getParameter("check2");
//            String notif = request.getParameter("check3");
            String check = request.getParameter("check");
            String num = request.getParameter("num");
            String data = request.getParameter("ids");
            String emp = request.getParameter("emp");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Email Send</title>");
            out.println("</head>");
            out.println("<body>");

            char c1, c2, c3;
            c1 = check.charAt(0);
            c2 = check.charAt(2);
            c3 = check.charAt(4);

            out.println("<h2>Email ID Data: " + data + "</h2>");
            out.println("<h2>Checkbox Array: " + check + "<br> Checkbox Values: " + c1 + c2 + c3 + "</h2>");
            out.println("<h2>Phone Numbers: " + num + "</h2>");
            out.println("<h2>Employee Numbers: " + emp + "</h2>");

            if (c1 == '1') {

                int cti = 0, ctj = 0;
                String arr1[] = new String[50];
                String[] emps = emp.split(",");
                for (String w : emps) {
                    arr1[cti] = w;
                    cti++;
                    //out.println("<h4>The empids are: " + w + "</h4>");
                }

                in.vakrangee.vkms.utilities.EmailService obj = new in.vakrangee.vkms.utilities.EmailService();
                String sender = "shreayan.chaudhary.project@gmail.com";
                String sub = "Welcome to Vakrangee Team!";

                out.println("<h2>Email Recipient(s):</h2>");

                String[] emailid = data.split(",");
                for (String w : emailid) {
                    String empcode = arr1[ctj];
                    ctj++;
                    String body = "Your Employee ID is: " + empcode + ". Please remember your Employee ID. For any future reference or communication kindly quote your Employee ID.";
                    out.println("<h4>" + w + "</h4>");
                    obj.send_email(sender, w, sub, body);
                }
                out.println("<h3>Mail sent to recipient(s)!</h3>");
            }

            if (c2 != '0') {

                in.vakrangee.vkms.utilities.SMSService smsobj = new in.vakrangee.vkms.utilities.SMSService();

                String arr1[] = new String[20];
                String arr2[] = new String[20];
                int cti = 0, ctj = 0;

                out.println("<h2>SMS Recipient(s):</h2>");

                String[] emps = emp.split(",");
                for (String w : emps) {
                    arr1[cti] = w;
                    cti++;
                    //System.out.println(w);
                    //out.println("<h4>" + w + "</h4>");
                }

                String[] numbers = num.split(",");
                for (String w : numbers) {
                    arr2[ctj] = w;
                    ctj++;
                    //System.out.println(w);
                    //out.println("<h4>" + w + "</h4>");
                }

                int i;
                for (i = 0; arr1[i] != null; i++) {
                    out.println("<h4>" + arr1[i] + " " + arr2[i] + "</h4>");
                }
                out.println("<h3>" + cti + "</h3>");

                //smsobj.sendRequest("8939331189", "ra1611020010011");
                for (i = 0; i < cti; i++) {
                    smsobj.sendRequest(arr2[i], arr1[i]);
                }
                out.println("<h3>SMS sent to recipient(s)!</h3>");
            }

            if (c3 != '0') {

                out.println("<h2>Notification Recipient(s):</h2>");
                
                in.vakrangee.vkms.utilities.NotifService objectn = new in.vakrangee.vkms.utilities.NotifService();
                boolean success = objectn.testNotification("dVf5huIGIMU:APA91bFBpJ3we0USd6nFNetGFmuOCwYlJYuKokdc77Tbe7jyobBfW2lYaF9_bGU3-0Bm2Ayrb6xR7IX912uVwZzgP7sXr3MLhFv75c6GTdYguQlbBnFw8GQ9VMHoAFgjaH0mUcQDJJvFFDgZjIt-1iwlqnaKHivgfQ");
                    
                if(success){
                    out.println("<h3>Notification sent to recipient(s)!</h3>");
                }
                else {
                    out.println("<h3>Notification could not be sent!</h3>");
                }
            }

            out.println("</body>");
            out.println("</html>");

        } catch (Exception ex) {
            ex.printStackTrace();
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