package PresentationLayer;// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class

public class FirstPageManager extends HttpServlet {
        boolean isRealCustomer=false;
        boolean isLegalCustomer=false;

    // Method to handle GET method request.
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {


        String customerType;

        customerType = request.getParameter("type");
       if (customerType.equals("real customer"))
           isRealCustomer=true;

        else if(customerType.equals("legal customer"))
           isLegalCustomer=true;
        response.sendRedirect("customer-manager.html");
        System.out.println("**********************"+isLegalCustomer);

    }


}