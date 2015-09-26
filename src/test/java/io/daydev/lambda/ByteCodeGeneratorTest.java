package io.daydev.lambda;

import io.daydev.lambda.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

/**
 * Тест одинакового поведения класса, который генерит javac и ByteCodeGenerator
 */
public class ByteCodeGeneratorTest {

    /**
     *  вызов через класс сгенерированный javac
     */
    @Test
    public void testGenerateJVM() throws Exception {
        UntypedRequestHandler test = new TestResult(new TestController());
        test.process();
    }

    /**
     * вызов через класс сгенерированный через ASM
     */
    @Test
    public void testGenerateByteCode() throws Exception {
        ByteCodeGenerator byteCodegenerator = new ByteCodeGenerator();
        TestController controller = new TestController();
        for (Method method : TestController.class.getDeclaredMethods()) {
            if ((method.getModifiers() & Modifier.STATIC) == 0) {
                if (!method.getName().equals("around")) {
                    UntypedRequestHandler rh = byteCodegenerator.generate(TestController.class, method, controller);
                    rh.process();
                }
            }
        }

    }

    /**
     * Пример контроллера
     */
    public static class TestController implements ControllerWithInterception {

        private static final Logger log = LoggerFactory.getLogger(TestController.class);

        public CompletionStage<Response> around(Exchange exchange, Supplier<CompletionStage<Response>> controllerAction) {
            return controllerAction == null ? null : controllerAction.get();
        }

        public CompletionStage<Response> stubMethod1(Exchange exchange, Long param) {
            log.info("Controller call stubMethod1 with {}", param);
            Assert.assertEquals(param, Long.valueOf(1l));
            return null;
        }
    }

    /**
     * То что мы хотим получить
     */
    public static class TestResult implements UntypedRequestHandler {

        private final TestController controller;

        public TestResult(TestController controller) {
            this.controller = controller;
        }

        public CompletionStage<Response> process() {
            Exchange exchange = new DefaultExchange();

            return controller.around(exchange, () -> controller.stubMethod1(exchange, 1l));
        }
    }
}
