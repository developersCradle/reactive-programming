# Section 02: Mono.

Mono.

# What I learned.

# [Resource] - Source Code / Maven Dependencies.

- [Original Repository](https://github.com/vinsguru/java-reactive-programming-course).

- Use following to set repo:
- [POM](https://github.com/vinsguru/java-reactive-programming-course/blob/master/01-reactive-programming-playground/pom.xml#L11-L80).

````
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.vinsguru</groupId>
    <artifactId>reactive-programming-playground</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <reactor.version>2024.0.1</reactor.version>
        <logback.version>1.5.12</logback.version>
        <faker.version>1.0.2</faker.version>
        <junit.version>5.11.3</junit.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-core</artifactId>
        </dependency>
        <dependency>
            <groupId>io.projectreactor.netty</groupId>
            <artifactId>reactor-netty-core</artifactId>
        </dependency>
        <dependency>
            <groupId>io.projectreactor.netty</groupId>
            <artifactId>reactor-netty-http</artifactId>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>${logback.version}</version>
        </dependency>
        <dependency>
            <groupId>com.github.javafaker</groupId>
            <artifactId>javafaker</artifactId>
            <version>${faker.version}</version>
        </dependency>
        <!-- test dependencies -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
        <!-- step-verifier -->
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>io.projectreactor</groupId>
                <artifactId>reactor-bom</artifactId>
                <version>${reactor.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
````

- [Logback](https://github.com/vinsguru/java-reactive-programming-course/blob/master/01-reactive-programming-playground/src/main/resources/logback.xml).

````
<!-- http://dev.cs.ovgu.de/java/logback/manual/layouts.html -->
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} %-5level [%15.15t] %cyan(%-30.30logger{30}) : %m%n</pattern>
        </encoder>
    </appender>
    <logger name="io.netty.resolver.dns.DnsServerAddressStreamProviders" level="OFF"/>
    <root level="INFO">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
````

# Project Setup.

- We are going to **Maven**, **IntelliJ** and **Java21**.

<div align="center">
    <img src="useFollowingProjectSetup.JPG" alt="reactive programming" width="700"/>
</div>

# Publisher/Subscriber Implementation - Part 1.

# Publisher/Subscriber Implementation - Part 2.

# Publisher/Subscriber Demo.

# Mono / Flux - Introduction.

# Why We Need Mono!

# Stream Lazy Behavior.

# Mono Just.

# Mono Subscribe - Overloaded Methods.

# Creating Default Subscriber.

# Mono - Empty / Error.

# On Error Dropped - Problem.

# Mono - From Supplier.

# Mono - From Callable.

# Mono - From Runnable.

# Mono - From Future.

# Publisher - Create Vs Execute.

# Mono - Defer.

# What About Data From Remote Service?  

# [Resource] - External Services.

# External Services.

# Non-Blocking IO Client.

# Non-Blocking IO Demo.

# ***FAQ*** - How Event Loop Works.

# ***FAQ*** - Why We Should NOT Use Block.

# Why Reactive Netty?

# ***Assignment***.

# Assignment Solution.

# What About Unit Testing?

# Summary.

# Quiz 2: Quiz.