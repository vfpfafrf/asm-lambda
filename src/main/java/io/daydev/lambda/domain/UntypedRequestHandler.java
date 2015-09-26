package io.daydev.lambda.domain;

import java.util.concurrent.CompletionStage;

/**
 * Created by dmitry on 26.09.15.
 */
public interface UntypedRequestHandler {


    CompletionStage<Response> process();
}
