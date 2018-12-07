FROM java:latest
COPY ./mysql-connector-java-8.0.13.jar /mysql-connector-java-8.0.13.jar
COPY . /
WORKDIR /
RUN javac Main.java
CMD ["java", "-classpath", "mysql-connector-java-8.0.13.jar:.", "Main"]
