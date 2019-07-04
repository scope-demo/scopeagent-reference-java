package com.undefinedlabs.scope.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.testng.annotations.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

public class SLF4JExamplesTestNGTest {

    private static final String SAMPLE_MESSAGE_OTHER_EXECUTOR = "sampleMessage - OtherExecutor";
    private static final String SAMPLE_MESSAGE_OTHER_THREAD = "sampleMessage - OtherThread";
    private static final String SAMPLE_MESSAGE = "sampleMessage";
    private static final String SAMPLE_PARAM = "sampleParam";
    private static final String WITH_MARKER = " with Marker";

    private static final String SAMPLE_MESSAGE_ONE_PARAM = SAMPLE_MESSAGE + " {}";
    private static final String SAMPLE_MESSAGE_TWO_PARAM = SAMPLE_MESSAGE + " {} {}";
    private static final String SAMPLE_MESSAGE_THREE_PARAM = SAMPLE_MESSAGE + " {} {} {}";
    private static final RuntimeException SAMPLE_THROWABLE = new RuntimeException("");

    private static final String SAMPLE_MARKER_TEXT = "sampleMarkerText";
    private static final Marker SAMPLE_MARKER = MarkerFactory.getMarker(SAMPLE_MARKER_TEXT);

    @Test
    public void should_instrument_logger_info_thread_prop() throws InterruptedException {
        final Logger log = LoggerFactory.getLogger(this.getClass());

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.info(SAMPLE_MESSAGE_OTHER_THREAD);
                log.debug(SAMPLE_MESSAGE_OTHER_THREAD);
                log.error(SAMPLE_MESSAGE_OTHER_THREAD);
                log.warn(SAMPLE_MESSAGE_OTHER_THREAD);
                log.trace(SAMPLE_MESSAGE_OTHER_THREAD);
            }
        };
        final Thread thread = new Thread(runnable);

        thread.start();
        thread.join();
    }

    @Test
    public void should_instrument_logger_executor_prop() throws InterruptedException {
        final Logger log = LoggerFactory.getLogger(this.getClass());
        final ExecutorService executor = Executors.newCachedThreadPool();
        final Semaphore tested = new Semaphore(0);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                log.info(SAMPLE_MESSAGE_OTHER_EXECUTOR);
                log.debug(SAMPLE_MESSAGE_OTHER_EXECUTOR);
                log.error(SAMPLE_MESSAGE_OTHER_EXECUTOR);
                log.warn(SAMPLE_MESSAGE_OTHER_EXECUTOR);
                log.trace(SAMPLE_MESSAGE_OTHER_EXECUTOR);
                tested.release();
            }
        });

        tested.acquire();
    }

    @Test
    public void should_instrument_logger_executor_prop_submit_runnable() throws InterruptedException, ExecutionException {
        final Logger log = LoggerFactory.getLogger(this.getClass());
        final ExecutorService executor = Executors.newCachedThreadPool();
        final AtomicBoolean tested = new AtomicBoolean(false);

        executor.submit(new Runnable() {
            @Override
            public void run() {
                log.info(SAMPLE_MESSAGE_OTHER_EXECUTOR);
                log.debug(SAMPLE_MESSAGE_OTHER_EXECUTOR);
                log.error(SAMPLE_MESSAGE_OTHER_EXECUTOR);
                log.warn(SAMPLE_MESSAGE_OTHER_EXECUTOR);
                log.trace(SAMPLE_MESSAGE_OTHER_EXECUTOR);
                tested.set(true);
            }
        }).get();

        assertThat(tested.get()).isTrue();
    }

    @Test
    public void should_instrument_logger_executor_prop_submit_callable() throws InterruptedException, ExecutionException {
        final Logger log = LoggerFactory.getLogger(this.getClass());
        final ExecutorService executor = Executors.newCachedThreadPool();
        final AtomicBoolean tested = new AtomicBoolean(false);

        executor.submit(new Callable<Void>() {
            @Override
            public Void call() {
                log.info(SAMPLE_MESSAGE_OTHER_EXECUTOR);
                log.debug(SAMPLE_MESSAGE_OTHER_EXECUTOR);
                log.error(SAMPLE_MESSAGE_OTHER_EXECUTOR);
                log.warn(SAMPLE_MESSAGE_OTHER_EXECUTOR);
                log.trace(SAMPLE_MESSAGE_OTHER_EXECUTOR);
                tested.set(true);
                return null;
            }
        }).get();

        assertThat(tested.get()).isTrue();
    }

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

    @Test
    public void should_instrument_logger_debug() {
        //Given
        final Logger log = LoggerFactory.getLogger(this.getClass());

        //When
        log.debug(SAMPLE_MESSAGE);
        log.debug(SAMPLE_MESSAGE_ONE_PARAM, SAMPLE_PARAM);
        log.debug(SAMPLE_MESSAGE_TWO_PARAM, SAMPLE_PARAM, SAMPLE_PARAM);
        log.debug(SAMPLE_MESSAGE_THREE_PARAM, SAMPLE_PARAM, SAMPLE_PARAM, SAMPLE_PARAM);
        log.debug(SAMPLE_MESSAGE, SAMPLE_THROWABLE);

        log.debug(SAMPLE_MARKER, SAMPLE_MESSAGE + WITH_MARKER);
        log.debug(SAMPLE_MARKER, SAMPLE_MESSAGE_ONE_PARAM + WITH_MARKER, SAMPLE_PARAM);
        log.debug(SAMPLE_MARKER, SAMPLE_MESSAGE_TWO_PARAM + WITH_MARKER, SAMPLE_PARAM, SAMPLE_PARAM);
        log.debug(SAMPLE_MARKER, SAMPLE_MESSAGE_THREE_PARAM + WITH_MARKER, SAMPLE_PARAM, SAMPLE_PARAM, SAMPLE_PARAM);
        log.debug(SAMPLE_MARKER, SAMPLE_MESSAGE + WITH_MARKER, SAMPLE_THROWABLE);

        //Then
        assertThat(true).isTrue();
    }

    @Test
    public void should_instrument_logger_error() {
        //Given
        final Logger log = LoggerFactory.getLogger(this.getClass());

        //When
        log.error(SAMPLE_MESSAGE);
        log.error(SAMPLE_MESSAGE_ONE_PARAM, SAMPLE_PARAM);
        log.error(SAMPLE_MESSAGE_TWO_PARAM, SAMPLE_PARAM, SAMPLE_PARAM);
        log.error(SAMPLE_MESSAGE_THREE_PARAM, SAMPLE_PARAM, SAMPLE_PARAM, SAMPLE_PARAM);
        log.error(SAMPLE_MESSAGE, SAMPLE_THROWABLE);

        log.error(SAMPLE_MARKER, SAMPLE_MESSAGE + WITH_MARKER);
        log.error(SAMPLE_MARKER, SAMPLE_MESSAGE_ONE_PARAM + WITH_MARKER, SAMPLE_PARAM);
        log.error(SAMPLE_MARKER, SAMPLE_MESSAGE_TWO_PARAM + WITH_MARKER, SAMPLE_PARAM, SAMPLE_PARAM);
        log.error(SAMPLE_MARKER, SAMPLE_MESSAGE_THREE_PARAM + WITH_MARKER, SAMPLE_PARAM, SAMPLE_PARAM, SAMPLE_PARAM);
        log.error(SAMPLE_MARKER, SAMPLE_MESSAGE + WITH_MARKER, SAMPLE_THROWABLE);

        //Then
        assertThat(true).isTrue();
    }

    @Test
    public void should_instrument_logger_warning() {
        //Given
        final Logger log = LoggerFactory.getLogger(this.getClass());

        //When
        log.warn(SAMPLE_MESSAGE);
        log.warn(SAMPLE_MESSAGE_ONE_PARAM, SAMPLE_PARAM);
        log.warn(SAMPLE_MESSAGE_TWO_PARAM, SAMPLE_PARAM, SAMPLE_PARAM);
        log.warn(SAMPLE_MESSAGE_THREE_PARAM, SAMPLE_PARAM, SAMPLE_PARAM, SAMPLE_PARAM);
        log.warn(SAMPLE_MESSAGE, SAMPLE_THROWABLE);

        log.warn(SAMPLE_MARKER, SAMPLE_MESSAGE + WITH_MARKER);
        log.warn(SAMPLE_MARKER, SAMPLE_MESSAGE_ONE_PARAM + WITH_MARKER, SAMPLE_PARAM);
        log.warn(SAMPLE_MARKER, SAMPLE_MESSAGE_TWO_PARAM + WITH_MARKER, SAMPLE_PARAM, SAMPLE_PARAM);
        log.warn(SAMPLE_MARKER, SAMPLE_MESSAGE_THREE_PARAM + WITH_MARKER, SAMPLE_PARAM, SAMPLE_PARAM, SAMPLE_PARAM);
        log.warn(SAMPLE_MARKER, SAMPLE_MESSAGE + WITH_MARKER, SAMPLE_THROWABLE);

        //Then
        assertThat(true).isTrue();
    }

    @Test
    public void should_instrument_logger_verbose() {
        //Given
        final Logger log = LoggerFactory.getLogger(this.getClass());

        //When
        log.trace(SAMPLE_MESSAGE);
        log.trace(SAMPLE_MESSAGE_ONE_PARAM, SAMPLE_PARAM);
        log.trace(SAMPLE_MESSAGE_TWO_PARAM, SAMPLE_PARAM, SAMPLE_PARAM);
        log.trace(SAMPLE_MESSAGE_THREE_PARAM, SAMPLE_PARAM, SAMPLE_PARAM, SAMPLE_PARAM);
        log.trace(SAMPLE_MESSAGE, SAMPLE_THROWABLE);

        log.trace(SAMPLE_MARKER, SAMPLE_MESSAGE + WITH_MARKER);
        log.trace(SAMPLE_MARKER, SAMPLE_MESSAGE_ONE_PARAM + WITH_MARKER, SAMPLE_PARAM);
        log.trace(SAMPLE_MARKER, SAMPLE_MESSAGE_TWO_PARAM + WITH_MARKER, SAMPLE_PARAM, SAMPLE_PARAM);
        log.trace(SAMPLE_MARKER, SAMPLE_MESSAGE_THREE_PARAM + WITH_MARKER, SAMPLE_PARAM, SAMPLE_PARAM, SAMPLE_PARAM);
        log.trace(SAMPLE_MARKER, SAMPLE_MESSAGE + WITH_MARKER, SAMPLE_THROWABLE);

        //Then
        assertThat(true).isTrue();
    }
}
