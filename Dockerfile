FROM ubuntu:latest
RUN apt-get update -y
RUN apt-get install default-jre -y
RUN apt-get install vim -y
RUN echo "JAVA_HOME='/usr/" >> ~/.bashrc
ADD https://dlcdn.apache.org/tomcat/tomcat-10/v10.0.26/bin/apache-tomcat-10.0.26.tar.gz /tmp
RUN cd /tmp && tar -xvzf apache-tomcat-10.0.26.tar.gz
RUN cd /tmp && mv apache-tomcat-10.0.26 /opt/
EXPOSE 8080
ADD https://get.jenkins.io/war/2.372/jenkins.war /opt/apache-tomcat-10.0.26/webapps/
CMD ["/opt/apache-tomcat-10.0.26/bin/catalina.sh", "run"]
