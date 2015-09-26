package io.daydev.lambda.domain;

import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

public interface ControllerWithInterception extends Controller {

    /**
     * Метод будет вызван перед вызовом метода контроллера. Для того чтобы вызвать метод контроллера необходимо воспользоваться суплаером
     * controllerAction при вызове метода гет которого будет вызван метод контроллера с уже проинжектированными переменными.
     *
     * @param exchange         ексчендж
     * @param controllerAction замыкание на вызов метода контроллера
     * @return фьючер на результат
     */
    CompletionStage<Response> around(Exchange exchange, Supplier<CompletionStage<Response>> controllerAction);
}