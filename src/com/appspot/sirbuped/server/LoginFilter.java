package com.appspot.sirbuped.server;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;



/**
 * Filtro para el manejo de autenticación
 * @author mvera
 */
public class LoginFilter implements Filter 
{

    /**
     * Default constructor
     */
	public LoginFilter() {
    
    }//end constructor 

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	try{
      	  
      	  String requestUri = ((HttpServletRequest)request).getRequestURI();
      	  
      	  //Se aplica solo a peticiones html diferentes a la pantalla de logout
      	  if( ! requestUri.contains("logout.bsc")){    	      	  
  	    	  UserService userService = UserServiceFactory.getUserService();
  	    	  
  	    	  User user = userService.getCurrentUser();
  	
  	          if (user != null) {
  	        	  ((HttpServletRequest)request).getSession().setAttribute("bscGAEUser", user.getEmail());
  	        	  System.err.println(request.getAttribute("bscGAEUser"));
  	          }else{
  	        	System.err.println("redireccionar");
  	        	  ((HttpServletResponse)response).sendRedirect(userService.createLoginURL(((HttpServletRequest)request).getRequestURI()));
  	          }//end if-else user !=null
      	  }//end if 
            
        }//end try
        catch(Exception e){
          System.err.println("Error en Filtro de Autenticación");
          e.printStackTrace();
        }//end catch
        
        
        Throwable problem = null;
    	try {
    	    chain.doFilter(request, response);
    	}
    	catch(Throwable t) {
    	    problem = t;
    	    t.printStackTrace();
    	}
    	
    	if (problem != null) {
    	    if (problem instanceof ServletException) throw (ServletException)problem;
    	    if (problem instanceof IOException) throw (IOException)problem;
    	    sendProcessingError(problem, response);
    	}


        
    }//end method doFilter

	@Override
	public void destroy() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub		
	}
	
	
	private void sendProcessingError(Throwable t, ServletResponse response) {		
			String stackTrace = getStackTrace(t); 	
			if(stackTrace != null && !stackTrace.equals("")) {
	
			    try {
				    
				response.setContentType("text/html");
				PrintStream ps = new PrintStream(response.getOutputStream());
				PrintWriter pw = new PrintWriter(ps); 
				pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N
				    
				// PENDING! Localize this for next official release
				pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n"); 
				pw.print(stackTrace); 
				pw.print("</pre></body>\n</html>"); //NOI18N
				pw.close();
				ps.close();
				response.getOutputStream().close();;
			    }
				
			    catch(Exception ex){ }
			}
			else {
			    try {
				PrintStream ps = new PrintStream(response.getOutputStream());
				t.printStackTrace(ps);
				ps.close();
				response.getOutputStream().close();;
			    }
			    catch(Exception ex){ }
			}
	    }//end method sendProcessingError

	    
	public static String getStackTrace(Throwable t) {

			String stackTrace = null;
			    
			try {
			    StringWriter sw = new StringWriter();
			    PrintWriter pw = new PrintWriter(sw);
			    t.printStackTrace(pw);
			    pw.close();
			    sw.close();
			    stackTrace = sw.getBuffer().toString();
			}
			catch(Exception ex) {}
			return stackTrace;
	    }//end method getStackTrace

    
}//end class Filter