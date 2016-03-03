
tar -xzvf apache-tomcat*.tar.gz
cd WarwickAnalyticsTest
ant
cp WarwickAnalyticsTest.war ../apache-tomcat-7.0.68/webapps/
cd ../apache-tomcat-*
bin/startup.sh