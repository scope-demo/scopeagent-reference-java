# ScopeAgent Demo Java

Example repository to check how to use ScopeAgent with several test examples in Java.

### Modules

In this repository are being maintained 3 modules, which correspond with the ScopeAgent supported tests frameworks.

* **junit4-tests**: Module where are kept [JUnit4](https://junit.org/junit4/) tests.
* **junit5-tests**: Module where are kept [JUnit5](https://junit.org/junit5/) tests.
* **testng-tests**: Module where are kept [TestNG](https://testng.org/doc/) tests.

#### Module structure

Every module contains several test packages where are being maintained the tests examples of every supported library that could be instrumented by ScopeAgent.

* **```com.undefinedlabs.scope.testing```** test package.
* **```com.undefinedlabs.scope.logging```** test package.
* **```com.undefinedlabs.scope.network```** test package.

##### Plain tests examples:
This tests examples are kept within ```com.undefinedlabs.scope.testing``` test package.

* ```Junit4ExampleTest```
* ```Junit5ExampleTest```
* ```TestNGExampleTest```

In every test class you can observe the four types of test examples: ```Success```, ```Failed``` ```Error```, and ```Skipped```.

Notice that, depending on which test framework are you observing, the test syntax may changed based on the test framework.

Find below an example of the four type examples maintained within ```Junit4ExampleTest``` class.

* **Test Success**: This is an example of a succeed test.
```java
   @Test
    public void ok_test_assert_true_is_true() {
        assertThat(true).isTrue();
    } 
```

* **Test Failed**: This is an example of a failed test. (The assertions don't match)
```java
    @Test
    public void failed_test_assert_that_false_is_true() {
        assertThat(false).isTrue();
    }
```

* **Test Error**: This is an example of an error test. (An uncaught exception is thrown during the test)
```java
    @Test
    public void error_test_throw_exception() {
        throw new ArithmeticException("This is a test that throws an exception");
    }
```

* **Test Skip**: This is an example of an skipped test.
```java
    @Ignore("Test Ignored")
    @Test
    public void skip_test_ignored() { }
```

##### Logging tests examples:
This tests examples are kept within ```com.undefinedlabs.scope.logging``` test package.

* ```SLF4JExamplesJUnit4Test```
* ```SLF4JExamplesJUnit5Test```
* ```SLF4JExamplesTestNGTest```

In every test class you can observe examples about every SLF4J log level: ```info```, ```debug```, ```error```, ```warn```, and ```trace```.

Notice that, depending on which test framework are you observing, the test syntax may changed based on the test framework.

Find below an example of the ```info``` log level in the class ```SLF4JExamplesJUnit4Test``` class.

```java
    @Test
    public void should_instrument_logger_info() {
        //Given
        final Logger log = LoggerFactory.getLogger(this.getClass());

        //When
        log.info(SAMPLE_MESSAGE);
        log.info(SAMPLE_MESSAGE_ONE_PARAM, SAMPLE_PARAM);
        log.info(SAMPLE_MESSAGE_TWO_PARAM, SAMPLE_PARAM, SAMPLE_PARAM);
        log.info(SAMPLE_MESSAGE_THREE_PARAM, SAMPLE_PARAM, SAMPLE_PARAM, SAMPLE_PARAM);
        log.info(SAMPLE_MESSAGE, SAMPLE_THROWABLE);

        log.info(SAMPLE_MARKER, SAMPLE_MESSAGE + WITH_MARKER);
        log.info(SAMPLE_MARKER, SAMPLE_MESSAGE_ONE_PARAM + WITH_MARKER, SAMPLE_PARAM);
        log.info(SAMPLE_MARKER, SAMPLE_MESSAGE_TWO_PARAM + WITH_MARKER, SAMPLE_PARAM, SAMPLE_PARAM);
        log.info(SAMPLE_MARKER, SAMPLE_MESSAGE_THREE_PARAM + WITH_MARKER, SAMPLE_PARAM, SAMPLE_PARAM, SAMPLE_PARAM);
        log.info(SAMPLE_MARKER, SAMPLE_MESSAGE + WITH_MARKER, SAMPLE_THROWABLE);

        //Then
        assertThat(true).isTrue();
    }
```

##### Network tests examples:

This tests examples are kept within ```com.undefinedlabs.scope.network``` test package.

In every test class you can observe different test examples about OK and KO request to instrumented and not instrumented servers.

Notice that, depending on which test framework are you observing, the test syntax may changed based on the test framework and the used HTTP library.

Depending on the used HTTP library, there are the following test classes: 

###### OkHttp3 Tests

* ```OkHttp3ClientExamplesJUnit4Test```
* ```OkHttp3ClientExamplesJUnit5Test```
* ```OkHttp3ClientExamplesTestNGTest```

Find below some examples using the [```OkHttp```](https://square.github.io/okhttp/) HTTP client:

**Requests targeting NOT instrumented server**:
* ```HTTP GET OK 200``` test example.
```java
    @Test
    public void ok_okhttp3cli_simple_http_get() throws IOException { ... }
```    
* ```HTTP GET KO Connection refused``` test example.
```java
    @Test
    public void ko_okhttp3cli_simple_http_get() throws IOException { ... }
```
* ```HTTP GET KO Unknown host``` test example.
```java
    @Test
    public void ko_okhttp3cli_simple_http_get_unknown_host() throws IOException { ... }
```

**Requests targeting instrumented server**:
* ```HTTP GET OK 200``` test example.
```java
    @Test
    public void ok_okhttp3cli_integration_http_get() throws IOException { ... }
```
* ```HTTP GET KO 500``` test example.
```java
    @Test
    public void ko_okhttp3cli_integration_http_get_500() throws IOException { ... }
```
* ```HTTP GET KO 404``` test example.
```java
    @Test
    public void ko_khttp3cli_integration_http_get_500() throws IOException {
```

### CI integration

This demo repository is using [```Jenkins```](https://jenkins.io/) to execute the build pipeline.

The ```Jenkins``` configuration is kept under the ```Jenkinsfile``` file, which contains the ```Jenkins pipeline``` configuration.

In this case, the pipeline is configured to execute parallel stages for ```JDK1.7```, ```JDK1.8```, and ```JDK11```.

### ScopeAgent installation and execution

ScopeAgent execution is attached to the ```test``` phase of the Maven build phases, through the ```argLine``` property configured in the ```surefire``` maven plugin.

You can check a detail explanation about the installation of the ScopeAgent in the following instructions: 
* [Scope Java Agent instructions](https://scope.undefinedlabs.com/docs/java-installation) 

### Test results dashboard

Once the CI server has executed the build pipeline, all test classes will have been executed in every JDK version declared in the CI server configuration file.

This means that you can check the examples test results following the next instructions:
 
1. Access to [```https://demo.codescope.com/```](https://demo.codescope.com)
2. Log in with your GitHub account.
3. Locate the ```scopeagent-reference-java``` project.
4. Click on ```master``` branch link.

Now you can check the details about every test executed for every JDK version in the las CI build pipeline associated with the last changed you commited to the repository.
