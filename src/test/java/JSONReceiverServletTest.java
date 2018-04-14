import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class JSONReceiverServletTest {
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;

    private StringWriter stringWriter;
    private PrintWriter printWriter;
    private DummyServletInputStream inputStream;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        //Each test that uses this will put its own string into it
        inputStream = new DummyServletInputStream("");

    }
    /*
    Tests for doPost method
     */
    @Test
    public void postEmptyJson() throws Exception {
        when(response.getWriter()).thenReturn(printWriter);
        //Setup the input stream so the servlet can read it
        String json = "";
        inputStream.setBackingString(json);
        when(request.getInputStream()).thenReturn(inputStream);

        new JSONReceiverServlet().doPost(request, response);
        assertEquals("No JSON POSTed", stringWriter.toString());

    }

    @Test
    public void postJson() throws Exception{
        when(response.getWriter()).thenReturn(printWriter);
        //Setup the input stream so the servlet can read it
        String json = "[{'proximityUUID':'uuid1','major':1,'minor':2,'accuracy':13.3}]";
        inputStream.setBackingString(json);
        when(request.getInputStream()).thenReturn(inputStream);

        new JSONReceiverServlet().doPost(request, response);
        assertEquals("Beacon<proximityUUID: uuid1, major: 1, minor: 2, accuracy: 13.300>\n", stringWriter.toString());
    }

    @Test
    public void postInvalidJson() throws Exception {
        when(response.getWriter()).thenReturn(printWriter);
        //Setup the input stream so the servlet can read it
        //json is invalid- missing a close square bracket
        String json = "[{'proximityUUID':'uuid1','major':1,'minor':2,'accuracy':13.3}";
        inputStream.setBackingString(json);
        when(request.getInputStream()).thenReturn(inputStream);

        new JSONReceiverServlet().doPost(request, response);
        assertEquals("Invalid JSON", stringWriter.toString());
    }
}
