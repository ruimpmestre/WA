tar -xzvf apache-tomcat*.tar.gz
cd WarwickAnalyticsTest
ant
cp WarwickAnalyticsTest.war ../apache-tomcat*/webapps/
cd ../apache-tomcat*
bin/startup.sh
