package com.warwickanalytics.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.warwickanalytics.process.DataFormatter;
import com.warwickanalytics.process.FileProcessor;

/**
 * Servlet implementation class 
 */

@WebServlet("/uploadFile")
public class UploadServlet extends HttpServlet {
  private static final long serialVersionUID = 102831973239L;

  int count;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    // Set the session valid for 5 secs
    session.setMaxInactiveInterval(5);
    response.setContentType("text/html");
    
    // Read from request
    StringBuilder buffer = new StringBuilder();
    BufferedReader reader = request.getReader();
    boolean file = false;
    String line;
    while ((line = reader.readLine()) != null) {
    	if(file && (line.isEmpty() || line.startsWith("-"))) {
    		break;
    	}
    	if(file) {
    		buffer.append(line + "\n");
    	}
    	if(line.isEmpty()) {
    		file = !file;
    	}
    }
     
    String data = buffer.toString();

    PrintWriter out = response.getWriter();
    out.println( DataFormatter.formatTabularData( new FileProcessor(data).processData()));
  }

} 