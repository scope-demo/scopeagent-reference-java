package com.undefinedlabs.scope.testing;

import org.testng.SkipException;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestNGExamplesTest {

    @Test
    public void ok_test_assert_true_is_true() {
        assertThat(true).isTrue();
    }

    @Test
    public void failed_test_assert_that_false_is_true() {
        assertThat(false).isTrue();
    }

    @Test
    public void error_test_throw_exception() {
        throw new ArithmeticException("This is a test that throws an exception");
    }

    @Test
    public void skip_test_ignored() {
        throw new SkipException("test_ignore");
    }
}
