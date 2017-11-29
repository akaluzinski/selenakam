## Careers

### Deployment steps
Following steps assume that environment is fresh. If you already have JDK8 and Maven, please go to 3rd step.
1. Download and install JDK8 from [Oracle Downloads](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. Download and install Maven 3 [Maven Install](https://maven.apache.org/install.html)
  - Ensure `JAVA_HOME` environment variable is set and points to your JDK directory
3. Download `geckodriver` (e.g. geckodriver-xx-macos.tar.gz for Mac OS) to run Selenium 3 tests on latest (v47+) Firefox versions [Geckodriver download](https://github.com/mozilla/geckodriver/releases)
   - Add `geckodriver` to your `PATH`
     - `export PATH=$PATH:/path/to/geckodriver/directory`
4. Download source code
5. Compile source code running
     - mvn compile

### Execution steps
1. To run test script please run following goal:
   - **`mvn -Dtest=CareersTest test`**
     - Note:  running `mvn test` will run utility class' unit tests as well.
    
### Version stack:
- Selenium WebDriver 3.7.1
- Junit 4.12

### Tested environments
Script was tested on below environment configurations:
- macOS Sierra 10.12.6 + FF57 + geckodriver v0.19.1 + JDK1.8.0_151 + mvn 3.5.2 
- Windows 8.1 + FF54.0 (64-bit) + geckodriver v0.19.1 + JDK1.8.0_151 + mvn 3.5.2
