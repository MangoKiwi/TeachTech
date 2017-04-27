#!/bin/bash

mvn package
scp /vagrant/target/teachtech-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/
/opt/tomcat/bin/catalina.sh run
