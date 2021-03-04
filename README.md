# Web/API Automation Gauge Framework
This is the Soteria Web Automation Project. It is created with Selenide and Junit5 and uses Gauge as the test specification framework.

## Requirements
* homebrew ~ 2.4.12
* sdkman ~ 5.9.0+555
* java ~ OpenJDK 13.0.2
* Gauge ~ 1.1.1 
    * gauge java ~  0.7.9
    * gauge html-report ~ 4.0.12
    * gauge json-report ~ 0.3.3
    * gauge screenshot ~ 0.0.1
* Chrome & Chromedriver ~ chromedriver version depends on chrome used

## Installation Guide
### Install Homebrew
https://brew.sh/

1. Open Terminal
2. Run Following command
```
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install.sh)"

brew --version

brew doctor
```

### Install SDKMan and Java 13
sdk man used in this context to manage jdk installation

https://sdkman.io/

* Run the following command 
```
$ curl -s "https://get.sdkman.io" | bash
```
* Check if SDKMan is installed properly
```
sdk version
```
* Install Required JDK
```
sdk list java
sdk install java 13.0.2-open
```

* Setup Java
    * validate installed java :
        ``java --version``
    * this command tells you the location of the java installation  
        ```
        which java
        ```
    * set env variable JAVA_HOME
      ```
      export JAVA_HOME={output above}
      echo $JAVA_HOME
      ```

## Setup Environment for Gauge
https://docs.gauge.org/getting_started/installing-gauge.html?os=macos&language=java&ide=intellij

Installing Gauge
```
brew install gauge@1.1.1
```

Expected Output (May Vary)

```
######################################################################## 100.0%
==> Pouring gauge-1.1.1.catalina.bottle.tar.gz
🍺  /usr/local/Cellar/gauge/1.1.1 etc
```

Installing Dependencies
```
gauge install java
gauge install html-report
gauge install json-report
```

Verify installations on Gauge

```
gauge -v
```

Output

```
Gauge version: 1.1.1
Plugins
-------
html-report (4.0.12)
java (0.7.9)
json-report (0.3.3)
screenshot (0.0.1)
```

## Setup Chrome & Chromedriver
 * Install Chrome
 * Download chromedriver that is required for chrome downloaded above
 * move chromedriver to path:
    ```
   cd Downloads
   mv chromedriver /usr/local/bin
    ``` 
 * test chromedriver with command:
    ```
    chromedriver 
   ```
## Usage with IDE (IntelliJ)

* Download and install IntelliJ.
* Navigate to plugins and install plugin Gauge
* Open project
    * Let Gradle download dependencies and index project
    * build project to ensure project can compile
* create dir in env folder with java.properties inside created folder see other env folders for naming convention
* Folder : src/test/java/resources refactor copy property file eg. default.properties and rename according to convention
* specs/*.spec are the execution files if plugin installed successfully then play buttons are located next to each specification and markdowns are applied
* to quickly create a run configuration click play inside a spec file a failure is expected to resolve edit the run configuration and add a program argument
    ```
    --env {envFolderName eg. default}
    ```
* Run again and specification should run


## Running without IDE

* open terminal
* cd to directory of project
* run command
    ```
    ./gradlew clean build gauge -Penv="default"
    ``` 
* Report is located in - report/html-report or report/json-report    