package in.vakrangee.vkms.utilities;

// Author: Shreayan Chaudhary
import java.io.*;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class SMSService {

    public void sendRequest(String number, String empid) {

        try {

            String url = "https://vkmssit.vakrangee.in/WSSMS/SMSServlet";
            URL ob = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) ob.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            StringBuilder parameters = new StringBuilder();
            parameters.append("smsType").append("=").append("WELCOME_EMPLOYEE").append("&").append("userId").append("=").append("1").append("&").append("vkId").append("=").append("1").append("&").append("parameter1").append("=").append(number).append("&").append("parameter2").append("=").append(empid);

            System.out.println(parameters);

            OutputStream os = con.getOutputStream();
            os.write(parameters.toString().getBytes());
            os.flush();
            os.close();
            int responseCode = con.getResponseCode();

            System.out.println("Response Code: " + responseCode);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}