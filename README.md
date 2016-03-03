# Warwick Analytics Test - Part 1

* Requirements *

  - Linux machine
  - Java 1.8
  - ant (for building the web application)

   
* Installation instructions *

After uncompressing the file containig the development there are two approaches to launching the application:

1. Quick Launch

  - enter the folder generated from uncompressing the source package 
  - execute script quick-launch.sh
    > sh quick-launch.sh

2. Detailed configuration

  a. Enter the folder generated from uncompressing the source package
  
  b. (Optional) Install Apache Tomcat web server
    This is done by dimply umcompressing the tomcat deployment pacakge
    > tar -xzvf apache-tomcat*.tar.gz

    Note: In case the user already has an instance of Apache Tomcat (or other web application server) the Web Application can be installed there.
    
  c. Build Web Application
     > cd WarwickAnalyticsTest
     > ant
    
  d. Deploy the Web Application in the Web Application server
  
     > cp WarwickAnalyticsTest.war ../apache-tomcat*/webapps/
     
    Note: In case the user already has another instance of Apache Tomcat the web application war file shall be copied to that instance's webapps folder
    Note2: For installing in other Web application servers please follow those software's specific instructions
    
  e. Launch the Web Application Server
  
    > cd ../apache-tomcat*
    > bin/startup.sh
    
    
* Execution Instructions    
    
  - Launch your prefered Web Browser
  - Access the following URL: http://localhost:8080/WarwickAnalyticsTest/
  - Use the application according to the Programming Test Feature Specification


* Development Indications

The deployment package can be imported into Eclipse IDE by using Option File > Import > Existing Project into Workspace... 
and selecting the main folder containing the Web application source code.
