import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(name = "JSONReceiverServlet", urlPatterns = {"/position"}, loadOnStartup = 1)
public class JSONReceiverServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Hello, World!");
        try {
            DBConnector db = new DBConnector();
            response.getWriter().print(db.testTableContents());
        } catch (Exception e) {
            response.getWriter().println("Error constructing DB connector: " + e.getMessage());
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter respWriter = response.getWriter();
        //This gets JSON that was sent in the post request from the client
        String json = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        while(br.ready()) {
            json += br.readLine();
        }

        Gson gson = new Gson();
        try {
            Beacon[] beacons = gson.fromJson(json, Beacon[].class);

            if(beacons == null) {
                respWriter.print("No JSON POSTed");
                return;
            }


            for(Beacon b: beacons) {
                respWriter.println(b);
            }
        } catch(JsonParseException e) {
            respWriter.print("Invalid JSON");
        }

    }
}
