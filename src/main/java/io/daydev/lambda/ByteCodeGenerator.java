package io.daydev.lambda;

import io.daydev.lambda.domain.Controller;
import io.daydev.lambda.domain.DefaultExchange;
import io.daydev.lambda.domain.Exchange;
import io.daydev.lambda.domain.UntypedRequestHandler;
import org.apache.commons.io.IOUtils;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

import static io.daydev.lambda.ByteCodeUtils.*;
import static org.objectweb.asm.Opcodes.*;

/**
 * Created by dmitry on 26.09.15.
 */
public class ByteCodeGenerator {

    private static final Logger logger = LoggerFactory.getLogger(ByteCodeGenerator.class);

    private static final String CONTROLLER_FIELD = "controller";

    private static final Method defineClass0 = getDefineClass0();
    protected static final Handle metafactoryHandle = findMetaFactoryHandle();

    private static final Type VOID_TYPE = Type.getType(void.class);
    private static final Type COMPLETION_STAGE_TYPE = Type.getType(CompletionStage.class);
    private static final Type DEFAULT_EXCHANGE_TYPE = Type.getType(DefaultExchange.class);
    private static final Type EXCHANGE_TYPE = Type.getType(Exchange.class);
    private static final Type LONG_TYPE = Type.getType(Long.class);

    private static final String VOID_DESCR = Type.getMethodDescriptor(VOID_TYPE);


    public UntypedRequestHandler generate(Class<?> controllerClass, Method controllerMethod, Controller instance) {
        return withLambda(controllerClass, controllerMethod, instance);
    }

    private UntypedRequestHandler withLambda(Class<?> controllerClass, Method controllerMethod, Controller instance) {
        String packageName = controllerClass.getPackage().getName();
        String className = "Gen_L" + (int)(Math.random()*100);
        String newClassName = packageName + '.' + className;
        String internalName = newClassName.replace('.', '/');

        Type controllerType = Type.getType(controllerClass);
        Type thisType = Type.getObjectType(internalName);

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        /*** Class definitions ***/
        // public class io.daydev.lambda.TestResult implements io.daydev.lambda.domain.UntypedRequestHandler
        cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER, internalName, null, Type.getInternalName(Object.class), new String[]{Type.getInternalName(UntypedRequestHandler.class)});


        /*** class field controller ***/
        //  private final io.daydev.lambda.TestController controller;
        // descriptor: Lio/daydev/lambda/TestController;
        // flags: ACC_PRIVATE, ACC_FINAL
        cw.visitField(ACC_PRIVATE + ACC_FINAL, CONTROLLER_FIELD, controllerType.getDescriptor(), null, null);

        /*** Default constructor ***/
        // public io.daydev.lambda.TestResult(io.daydev.lambda.TestController);
        // descriptor: (Lio/daydev/lambda/TestController;)V
        // flags: ACC_PUBLIC
        // Code:
        // stack=2, locals=2, args_size=2
        // 0: aload_0
        // 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
        // 4: aload_0
        // 5: aload_1
        // 6: putfield      #2                  // Field controller:Lio/daydev/lambda/TestController;
        // 9: return

        GeneratorAdapter ga = createMethod(cw, ACC_PUBLIC, "<init>", Type.getMethodDescriptor(VOID_TYPE, controllerType));

        ga.loadThis();
        ga.invokeConstructor(Type.getType(Object.class), new org.objectweb.asm.commons.Method("<init>", VOID_DESCR));

        ga.loadThis();
        ga.loadArg(0);
        ga.putField(thisType, CONTROLLER_FIELD, controllerType);

        ga.returnValue();
        ga.endMethod();

        //createSimpleProcess(cw, controllerType, thisType);
        /** Lambda function **/
        createLambda(cw, controllerType, thisType, controllerMethod);

        /*** main call ***/
        createProcess(cw, controllerType, thisType, internalName);

        logger.info("javap -v -p -s -c {}.class", className);
        try {
            IOUtils.write(cw.toByteArray(), new FileOutputStream(className+".class"));
        } catch (IOException e) {
            logger.error("While write class file", e);
        }

        return getUntypedRequestHandler(controllerClass, instance, newClassName, cw.toByteArray());
    }

    @SuppressWarnings("unchecked")
    private UntypedRequestHandler getUntypedRequestHandler(Class<?> controllerClass, Controller instance, String newClassName, byte[] classBytes) {
        try {
            assert defineClass0 != null;
            Class<UntypedRequestHandler> cls = (Class<UntypedRequestHandler>) defineClass0.invoke(null, controllerClass.getClassLoader(), newClassName, classBytes, 0, classBytes.length);
            return cls.getConstructor(controllerClass).newInstance(instance);
        } catch (Exception e) {
            logger.error("Error while generating handler for process requests", e);
            return null;
        }
    }


    private void createLambda(ClassWriter cw, Type controllerType, Type thisType, Method controllerMethod){
        // private java.util.concurrent.CompletionStage lambda$process$0(io.daydev.lambda.domain.Controller$Exchange);
        // descriptor: (Lio/daydev/lambda/domain/Controller$Exchange;)Ljava/util/concurrent/CompletionStage;
        // flags: ACC_PRIVATE, ACC_SYNTHETIC
        GeneratorAdapter ga = createMethod(cw, ACC_PRIVATE | ACC_SYNTHETIC, "lambda$process$0", Type.getMethodDescriptor(COMPLETION_STAGE_TYPE, EXCHANGE_TYPE));
        // Code:
        // stack=4, locals=2, args_size=2
        // 0: aload_0
        ga.loadThis();
        // 1: getfield      #2                  // Field controller:Lio/daydev/lambda/TestController;
        ga.getField(thisType, CONTROLLER_FIELD, controllerType);

        // 4: aload_1
        ga.visitVarInsn(Opcodes.ALOAD, 1);

        // 5: lconst_0
        ga.visitInsn(LCONST_1);

        // 6: invokestatic  #7                  // Method java/lang/Long.valueOf:(J)Ljava/lang/Long;

        ga.invokeStatic(LONG_TYPE, new org.objectweb.asm.commons.Method("valueOf", LONG_TYPE, new Type[]{Type.LONG_TYPE}));

        // 9: invokevirtual #8                  // Method io/daydev/lambda/TestController.stubMethod1:(Lio/daydev/lambda/domain/Controller$Exchange;Ljava/lang/Long;)Ljava/util/concurrent/CompletionStage;
        ga.invokeVirtual(controllerType, new org.objectweb.asm.commons.Method(controllerMethod.getName(), COMPLETION_STAGE_TYPE, new Type[]{EXCHANGE_TYPE, LONG_TYPE}));

        // 12: areturn
        ga.returnValue();

        ga.endMethod();
    }

    private void createProcess(ClassWriter cw, Type controllerType, Type thisType, String internalName){
        //  public java.util.concurrent.CompletionStage<io.daydev.lambda.domain.Response> process(io.daydev.lambda.domain.Request, io.daydev.lambda.domain.Response);
        // descriptor: (Lio/daydev/lambda/domain/Request;Lio/daydev/lambda/domain/Response;)Ljava/util/concurrent/CompletionStage;
        // flags: ACC_PUBLIC
        GeneratorAdapter ga = createMethod(cw, ACC_PUBLIC, "process", Type.getMethodDescriptor(COMPLETION_STAGE_TYPE));
        // Code:
        // stack=4, locals=4, args_size=3
        // 0: new           #3                  // class io/daydev/lambda/domain/DefaultExchange
        // 3: dup
        // 4: invokespecial #4                  // Method io/daydev/lambda/domain/DefaultExchange."<init>":()V
        // 7: astore_3

        int exchange = ga.newLocal(DEFAULT_EXCHANGE_TYPE);
        ga.newInstance(DEFAULT_EXCHANGE_TYPE);
        ga.dup();
        ga.invokeConstructor(DEFAULT_EXCHANGE_TYPE, new org.objectweb.asm.commons.Method("<init>", VOID_DESCR));
        ga.storeLocal(exchange);

        // 8: aload_0
        // 9: getfield      #2                  // Field controller:Lio/daydev/lambda/TestController;
        ga.loadThis();
        ga.getField(thisType, CONTROLLER_FIELD, controllerType);

        // 12: aload_1
        // 13: aload_0
        // 14: aload_1
        ga.loadLocal(exchange);
        ga.loadThis();
        ga.loadLocal(exchange);

        // 15: invokedynamic #5,  0              // InvokeDynamic #0:get:(Lio/daydev/lambda/TestResult;Lio/daydev/lambda/domain/Controller$Exchange;)Ljava/util/function/Supplier;
        final Handle processLambdaHanlde = new Handle(H_INVOKESPECIAL, internalName, "lambda$process$0", Type.getMethodDescriptor(COMPLETION_STAGE_TYPE, EXCHANGE_TYPE));
        final String bsmDesc = Type.getMethodDescriptor(Type.getType(Supplier.class), thisType, EXCHANGE_TYPE);
        Method method = getInterfaceMethod(Supplier.class);
        final Object[] bsmArgs = new Object[]{Type.getType(method), processLambdaHanlde, Type.getMethodType(COMPLETION_STAGE_TYPE)};
        ga.invokeDynamic("get", bsmDesc, metafactoryHandle, bsmArgs);

        // 20: invokevirtual #6                  // Method io/daydev/lambda/TestController.around:(Lio/daydev/lambda/domain/Controller$Exchange;Ljava/util/function/Supplier;)Ljava/util/concurrent/CompletionStage;
        ga.invokeVirtual(controllerType, new org.objectweb.asm.commons.Method("around", COMPLETION_STAGE_TYPE, new Type[]{EXCHANGE_TYPE, Type.getType(Supplier.class)}));

        // 23: areturn
        ga.returnValue();

        ga.endMethod();
    }
}
