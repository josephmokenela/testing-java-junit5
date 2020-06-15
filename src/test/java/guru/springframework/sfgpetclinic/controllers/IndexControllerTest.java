package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.exceptions.ValueNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class IndexControllerTest implements ControllerTests {

    IndexController indexController;

    @BeforeEach
    public void setup() {
        indexController = new IndexController();
    }

    @Test
    @DisplayName("Testing the view name to return index")
    public void index() {
        assertEquals("index", indexController.index(), ()-> "Evaluated on failure, Expensive call");

        assertThat(indexController.index()).isEqualTo("index");
    }

    @Test
    @DisplayName("Testing the exception path")
    public void oopsHandler() {
        assertThrows(ValueNotFoundException.class, () -> {
            indexController.oopsHandler();
        });
    }

    @Disabled("This is the demo")
    @Test
    public void test_timeout() {
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(2000);
            System.out.println("I got here and I slept");
        });
    }

    @Disabled("This is the demo")
    @Test
    public void test_timeout_prempt() {
        assertTimeoutPreemptively(Duration.ofMillis(100), ()-> {
            Thread.sleep(2000);
            System.out.println("I got here premptively");
        });
    }

    @Test
    public void test_assumption_true() {
        assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
    }

    @EnabledOnOs(OS.MAC)
    @Test
    public void test_on_mac_os() {
        System.out.println("Testing on OSx");
    }

    @EnabledOnOs(OS.LINUX)
    @Test
    public void test_on_linux() {
        System.out.println("Testing on Linux");
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void test_on_windows() {
        System.out.println("Testing on Windows");
    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    public void test_on_java8() {
        System.out.println("Testing if Java 8");
    }

    @EnabledOnJre(JRE.JAVA_11)
    @Test
    public void test_on_java11() {
        System.out.println("Testing if Java 11");
    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "matooane")
    @Test
    public void test_if_user_joseph() {
        System.out.println("Testing if user is Joseph");
    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "tlotliso")
    @Test
    public void test_if_user_tlotliso() {
        System.out.println("Testing if user is Tlotliso");
    }
}