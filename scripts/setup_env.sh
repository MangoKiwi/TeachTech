#!/bin/bash

# Variables
DBPASSWD=development

# prevent dialogs that ask you to enter settings while installing and/or
# updating packages and will use the default instead
export DEBIAN_FRONTEND=noninteractive

# update apt-get tool
echo "Provision Start"
apt-get update > /dev/null

# install MySQL specific packages and settings
echo "Preparing MySQL"
apt-get install debconf-utils -y > /dev/null
debconf-set-selections <<< "mysql-server-5.5 mysql-server/root_password password $DBPASSWD"
debconf-set-selections <<< "mysql-server-5.5 mysql-server/root_password_again password $DBPASSWD"
echo "Install MySQL"
apt-get install mysql-server-5.5 -y > /dev/null

# install JDK8
echo "Preparing JDK8"
apt-get install -y python-software-properties > /dev/null
add-apt-repository -y ppa:webupd8team/java > /dev/null
apt-get update -qq > /dev/null
debconf-set-selections <<< "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true"
debconf-set-selections <<< "oracle-java8-installer shared/accepted-oracle-license-v1-1 seen true"
echo "Install JDK8"
apt-get install -y -q oracle-java8-installer > /dev/null
update-java-alternatives -s java-8-oracle

# install Maven
echo "Install Maven"
apt-get install maven -y > /dev/null

#======================================================
# install Tomcat
#echo "Setup Tomcat user"
#groupadd tomcat
#useradd -s /bin/false -g tomcat -d /opt/tomcat tomcat
#======================================================

echo "Install Tomcat"
cd ~
wget http://apache.mirrors.ionfish.org/tomcat/tomcat-9/v9.0.0.M20/bin/apache-tomcat-9.0.0.M20.tar.gz > /dev/null
mkdir /opt/tomcat
tar xvf apache-tomcat-9*tar.gz -C /opt/tomcat --strip-components=1 > /dev/null
echo "Update Permission"
cd /opt
chmod -R 777 tomcat
cd /opt/tomcat

#======================================================
#chgrp -R tomcat conf
#chmod g+rwx conf
#chmod g+r conf/*
#chown -R tomcat work/ temp/ logs/
#======================================================

scp /vagrant/tomcat.conf /etc/init/
sed -i "s#</tomcat-users>#  <!-- user manager can access only manager section -->\n  <roles rolename=\"manager-gui\" />\n  <!-- user admin can access manager and admin section both -->\n  <roles rolename=\"admin-gui\" />\n  <user username=\"admin\" password=\"password\" roles=\"manager-gui,admin-gui\" />\n</tomcat-users>#" /opt/tomcat/conf/tomcat-users.xml
sed -i "s#<Context antiResourceLocking=\"false\" privileged=\"true\" >#<Context antiResourceLocking=\"false\" privileged=\"true\" >\n  <!--#" /opt/tomcat/webapps/manager/META-INF/context.xml
sed -i "s#</Context>#  -->\n</Context>#" /opt/tomcat/webapps/manager/META-INF/context.xml
initctl reload-configuration
#initctl start tomcat


# install git
#echo "Install git"
#apt-get install git -y > /dev/null

