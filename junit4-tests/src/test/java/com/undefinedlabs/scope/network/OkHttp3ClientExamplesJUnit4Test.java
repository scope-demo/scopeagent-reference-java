package com.undefinedlabs.scope.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class OkHttp3ClientExamplesJUnit4Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttp3ClientExamplesJUnit4Test.class);

    @Test
    public void ok_okhttp3cli_simple_http_get() throws IOException {
        //Given
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        final Request.Builder reqBuilder = new Request.Builder().url("http://www.google.com");
        LOGGER.info("Executing HttpClient request");

        //When
        final Response execute = okHttpClient.newCall(reqBuilder.build()).execute();

        //Then
        assertThat(execute.isSuccessful()).isTrue();
    }

    @Test
    public void ko_okhttp3cli_simple_http_get() throws IOException {
        //Given
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        final Request.Builder reqBuilder = new Request.Builder().url("http://localhost");
        LOGGER.info("Executing HttpClient request");

        //When
        final Response execute = okHttpClient.newCall(reqBuilder.build()).execute();

        //Then
        assertThat(execute.isSuccessful()).isTrue();
    }

    @Test
    public void ko_okhttp3cli_simple_http_get_unknown_host() throws IOException {
        //Given
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        final Request.Builder reqBuilder = new Request.Builder().url("http://www.badUrl.c213");
        LOGGER.info("Executing HttpClient request");

        //When
        final Response execute = okHttpClient.newCall(reqBuilder.build()).execute();

        //Then
        assertThat(execute.isSuccessful()).isTrue();
    }

    @Test
    public void ok_okhttp3cli_integration_http_get() throws IOException {
        //Given
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        final Request.Builder reqBuilder = new Request.Builder().url("http://flask-example-project.codescope.com:8000/car/9E219725-490E-4509-A42D-D0388DF317D4");
        LOGGER.info("Executing HttpClient request");

        //When
        final Response execute = okHttpClient.newCall(reqBuilder.build()).execute();

        //Then
        assertThat(execute.isSuccessful()).isTrue();
    }

    @Test
    public void ko_okhttp3cli_integration_http_get_500() throws IOException {
        //Given
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        final Request.Builder reqBuilder = new Request.Builder().url("http://flask-example-project.codescope.com:8000/car/9E219725-490E-4509-A42D-D0388DF317DG");
        LOGGER.info("Executing HttpClient request");

        //When
        final Response execute = okHttpClient.newCall(reqBuilder.build()).execute();

        //Then
        assertThat(execute.isSuccessful()).isTrue();
    }

    @Test
    public void ko_okhttp3cli_integration_http_get_404() throws IOException {
        //Given
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        final Request.Builder reqBuilder = new Request.Builder().url("http://flask-example-project.codescope.com:8000/car");
        LOGGER.info("Executing HttpClient request");

        //When
        final Response execute = okHttpClient.newCall(reqBuilder.build()).execute();

        //Then
        assertThat(execute.isSuccessful()).isTrue();
    }
}
