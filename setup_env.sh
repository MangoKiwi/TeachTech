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
apt-get install -y -q oracle-java8-installer

# install Tomcat
echo "Preparing Tomcat"


# install git
#echo "Install git"
#apt-get install git -y > /dev/null

