call mvn clean install -DskipTests=true -f c:\Users\netlynx\menko-mikhail\service_center\pom.xml
echo on
copy "c:\Users\netlynx\menko-mikhail\service_center\webapp\target\webapp-1.0-SNAPSHOT.war" "c:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\"
pause;