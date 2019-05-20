package com.undefinedlabs.scope.testing;

import org.assertj.core.api.Java6Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.Collection;

public class JUnit5ExamplesTestFactoryTest {

    @TestFactory
    public Collection<DynamicTest> dynamic_test_factory() {

        return Arrays.asList(
                DynamicTest.dynamicTest("dynamic_test_succeed", new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        Java6Assertions.assertThat(true).isTrue();
                    }
                }),
                DynamicTest.dynamicTest("dynamic_test_failed", new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        Java6Assertions.assertThat(true).isFalse();
                    }
                }),
                DynamicTest.dynamicTest("dynamic_test_error", new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        throw new IllegalArgumentException("This exception is an example");
                    }
                }));
    }
}
