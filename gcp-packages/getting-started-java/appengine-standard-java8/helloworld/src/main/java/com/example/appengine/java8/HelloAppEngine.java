package com.example.appengine.java8;

// [START example]
import com.google.appengine.api.utils.SystemProperty;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// With @WebServlet annotation the webapp/WEB-INF/web.xml is no longer required.
@WebServlet(name = "ColorRecommender", value = "/GetReco")
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Properties properties = System.getProperties();

    String pixel = request.getParameter("color");

    String result = ServerUtils.GetReco(pixel);

    response.setContentType("text/plain"); 
    response.getWriter().println(pixel + result);

    //response.getWriter().println("Hello App Engine - Standard using "
      //      + SystemProperty.version.get() + " Java "
        //    + properties.get("java.specification.version"));
  }


  public static String getInfo() {
    return "Version: " + System.getProperty("java.version")
          + " OS: " + System.getProperty("os.name")
          + " User: " + System.getProperty("user.name");
  }

}
// [END example]