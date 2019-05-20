package com.undefinedlabs.scope.testing;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JUnit5ExamplesTest {

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

    @Disabled("Test Ignored")
    @Test
    public void skip_test_ignored() {

    }
}
