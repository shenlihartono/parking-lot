### Setup

First, you need to install [Java](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html) and [Maven](https://www.baeldung.com/install-maven-on-windows-linux-mac). 

Then run the following commands to make sure Java & Maven are already installed on your system.

```
seagate:~ elcapitan$ java -version
java version "1.8.0_121"
Java(TM) SE Runtime Environment (build 1.8.0_121-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.121-b13, mixed mode)
...
seagate:~ elcapitan$ mvn -version
Apache Maven 3.5.3 (3383c37e1f9e9b3bc3df5050c29c8aff9f295297; 2018-02-25T02:49:05+07:00)
Maven home: /usr/local/Cellar/maven/3.5.3/libexec
Java version: 1.8.0_121, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/jre
Default locale: en_SG, platform encoding: UTF-8
OS name: "mac os x", version: "10.15.4", arch: "x86_64", family: "mac"
```

### Usage

Go to the root directory at /parking_lot

To install all dependencies, compile and run tests:
```
$ sh bin/setup
```

To run the program and launch the shell:
```
$ sh bin/parking_lot
```

To run the code to accept inputs from a file:
```
$ sh bin/parking_lot file_inputs.txt
```
