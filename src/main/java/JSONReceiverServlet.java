import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Collection;

@WebServlet(name = "JSONReceiverServlet", urlPatterns = {"/position"}, loadOnStartup = 1)
public class JSONReceiverServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("Hello, World!");
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
        //We have to do this to serialize a collection of beacons incoming from the client
        Type collectionType = new TypeToken<Collection<Beacon>>(){}.getType();
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
