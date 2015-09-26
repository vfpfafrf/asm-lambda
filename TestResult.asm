Classfile /lambda/target/classes/io/daydev/lambda/TestResult.class
  Last modified Sep 26, 2015; size 1908 bytes
  MD5 checksum f3b57e223f66bc7a1d0b343586c1c7d0
  Compiled from "TestResult.java"
public class io.daydev.lambda.TestResult implements io.daydev.lambda.domain.UntypedRequestHandler
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #10.#31        // java/lang/Object."<init>":()V
   #2 = Fieldref           #9.#32         // io/daydev/lambda/TestResult.controller:Lio/daydev/lambda/TestController;
   #3 = Class              #33            // io/daydev/lambda/domain/DefaultExchange
   #4 = Methodref          #3.#31         // io/daydev/lambda/domain/DefaultExchange."<init>":()V
   #5 = InvokeDynamic      #0:#39         // #0:get:(Lio/daydev/lambda/TestResult;Lio/daydev/lambda/domain/Exchange;)Ljava/util/function/Supplier;
   #6 = Methodref          #40.#41        // io/daydev/lambda/TestController.around:(Lio/daydev/lambda/domain/Exchange;Ljava/util/function/Supplier;)Ljava/util/concurrent/CompletionStage;
   #7 = Methodref          #42.#43        // java/lang/Long.valueOf:(J)Ljava/lang/Long;
   #8 = Methodref          #40.#44        // io/daydev/lambda/TestController.stubMethod1:(Lio/daydev/lambda/domain/Exchange;Ljava/lang/Long;)Ljava/util/concurrent/CompletionStage;
   #9 = Class              #45            // io/daydev/lambda/TestResult
  #10 = Class              #46            // java/lang/Object
  #11 = Class              #47            // io/daydev/lambda/domain/UntypedRequestHandler
  #12 = Utf8               controller
  #13 = Utf8               Lio/daydev/lambda/TestController;
  #14 = Utf8               <init>
  #15 = Utf8               (Lio/daydev/lambda/TestController;)V
  #16 = Utf8               Code
  #17 = Utf8               LineNumberTable
  #18 = Utf8               LocalVariableTable
  #19 = Utf8               this
  #20 = Utf8               Lio/daydev/lambda/TestResult;
  #21 = Utf8               process
  #22 = Utf8               ()Ljava/util/concurrent/CompletionStage;
  #23 = Utf8               exchange
  #24 = Utf8               Lio/daydev/lambda/domain/Exchange;
  #25 = Utf8               Signature
  #26 = Utf8               ()Ljava/util/concurrent/CompletionStage<Lio/daydev/lambda/domain/Response;>;
  #27 = Utf8               lambda$process$0
  #28 = Utf8               (Lio/daydev/lambda/domain/Exchange;)Ljava/util/concurrent/CompletionStage;
  #29 = Utf8               SourceFile
  #30 = Utf8               TestResult.java
  #31 = NameAndType        #14:#48        // "<init>":()V
  #32 = NameAndType        #12:#13        // controller:Lio/daydev/lambda/TestController;
  #33 = Utf8               io/daydev/lambda/domain/DefaultExchange
  #34 = Utf8               BootstrapMethods
  #35 = MethodHandle       #6:#49         // invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #36 = MethodType         #50            //  ()Ljava/lang/Object;
  #37 = MethodHandle       #7:#51         // invokespecial io/daydev/lambda/TestResult.lambda$process$0:(Lio/daydev/lambda/domain/Exchange;)Ljava/util/concurrent/CompletionStage;
  #38 = MethodType         #22            //  ()Ljava/util/concurrent/CompletionStage;
  #39 = NameAndType        #52:#53        // get:(Lio/daydev/lambda/TestResult;Lio/daydev/lambda/domain/Exchange;)Ljava/util/function/Supplier;
  #40 = Class              #54            // io/daydev/lambda/TestController
  #41 = NameAndType        #55:#56        // around:(Lio/daydev/lambda/domain/Exchange;Ljava/util/function/Supplier;)Ljava/util/concurrent/CompletionStage;
  #42 = Class              #57            // java/lang/Long
  #43 = NameAndType        #58:#59        // valueOf:(J)Ljava/lang/Long;
  #44 = NameAndType        #60:#61        // stubMethod1:(Lio/daydev/lambda/domain/Exchange;Ljava/lang/Long;)Ljava/util/concurrent/CompletionStage;
  #45 = Utf8               io/daydev/lambda/TestResult
  #46 = Utf8               java/lang/Object
  #47 = Utf8               io/daydev/lambda/domain/UntypedRequestHandler
  #48 = Utf8               ()V
  #49 = Methodref          #62.#63        // java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #50 = Utf8               ()Ljava/lang/Object;
  #51 = Methodref          #9.#64         // io/daydev/lambda/TestResult.lambda$process$0:(Lio/daydev/lambda/domain/Exchange;)Ljava/util/concurrent/CompletionStage;
  #52 = Utf8               get
  #53 = Utf8               (Lio/daydev/lambda/TestResult;Lio/daydev/lambda/domain/Exchange;)Ljava/util/function/Supplier;
  #54 = Utf8               io/daydev/lambda/TestController
  #55 = Utf8               around
  #56 = Utf8               (Lio/daydev/lambda/domain/Exchange;Ljava/util/function/Supplier;)Ljava/util/concurrent/CompletionStage;
  #57 = Utf8               java/lang/Long
  #58 = Utf8               valueOf
  #59 = Utf8               (J)Ljava/lang/Long;
  #60 = Utf8               stubMethod1
  #61 = Utf8               (Lio/daydev/lambda/domain/Exchange;Ljava/lang/Long;)Ljava/util/concurrent/CompletionStage;
  #62 = Class              #65            // java/lang/invoke/LambdaMetafactory
  #63 = NameAndType        #66:#70        // metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #64 = NameAndType        #27:#28        // lambda$process$0:(Lio/daydev/lambda/domain/Exchange;)Ljava/util/concurrent/CompletionStage;
  #65 = Utf8               java/lang/invoke/LambdaMetafactory
  #66 = Utf8               metafactory
  #67 = Class              #72            // java/lang/invoke/MethodHandles$Lookup
  #68 = Utf8               Lookup
  #69 = Utf8               InnerClasses
  #70 = Utf8               (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #71 = Class              #73            // java/lang/invoke/MethodHandles
  #72 = Utf8               java/lang/invoke/MethodHandles$Lookup
  #73 = Utf8               java/lang/invoke/MethodHandles
{
  private final io.daydev.lambda.TestController controller;
    descriptor: Lio/daydev/lambda/TestController;
    flags: ACC_PRIVATE, ACC_FINAL

  public io.daydev.lambda.TestResult(io.daydev.lambda.TestController);
    descriptor: (Lio/daydev/lambda/TestController;)V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=2, args_size=2
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: aload_1
         6: putfield      #2                  // Field controller:Lio/daydev/lambda/TestController;
         9: return
      LineNumberTable:
        line 14: 0
        line 15: 4
        line 16: 9
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      10     0  this   Lio/daydev/lambda/TestResult;
            0      10     1 controller   Lio/daydev/lambda/TestController;

  public java.util.concurrent.CompletionStage<io.daydev.lambda.domain.Response> process();
    descriptor: ()Ljava/util/concurrent/CompletionStage;
    flags: ACC_PUBLIC
    Code:
      stack=4, locals=2, args_size=1
         0: new           #3                  // class io/daydev/lambda/domain/DefaultExchange
         3: dup
         4: invokespecial #4                  // Method io/daydev/lambda/domain/DefaultExchange."<init>":()V
         7: astore_1
         8: aload_0
         9: getfield      #2                  // Field controller:Lio/daydev/lambda/TestController;
        12: aload_1
        13: aload_0
        14: aload_1
        15: invokedynamic #5,  0              // InvokeDynamic #0:get:(Lio/daydev/lambda/TestResult;Lio/daydev/lambda/domain/Exchange;)Ljava/util/function/Supplier;
        20: invokevirtual #6                  // Method io/daydev/lambda/TestController.around:(Lio/daydev/lambda/domain/Exchange;Ljava/util/function/Supplier;)Ljava/util/concurrent/CompletionStage;
        23: areturn
      LineNumberTable:
        line 19: 0
        line 21: 8
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      24     0  this   Lio/daydev/lambda/TestResult;
            8      16     1 exchange   Lio/daydev/lambda/domain/Exchange;
    Signature: #26                          // ()Ljava/util/concurrent/CompletionStage<Lio/daydev/lambda/domain/Response;>;

  private java.util.concurrent.CompletionStage lambda$process$0(io.daydev.lambda.domain.Exchange);
    descriptor: (Lio/daydev/lambda/domain/Exchange;)Ljava/util/concurrent/CompletionStage;
    flags: ACC_PRIVATE, ACC_SYNTHETIC
    Code:
      stack=4, locals=2, args_size=2
         0: aload_0
         1: getfield      #2                  // Field controller:Lio/daydev/lambda/TestController;
         4: aload_1
         5: lconst_0
         6: invokestatic  #7                  // Method java/lang/Long.valueOf:(J)Ljava/lang/Long;
         9: invokevirtual #8                  // Method io/daydev/lambda/TestController.stubMethod1:(Lio/daydev/lambda/domain/Exchange;Ljava/lang/Long;)Ljava/util/concurrent/CompletionStage;
        12: areturn
      LineNumberTable:
        line 21: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      13     0  this   Lio/daydev/lambda/TestResult;
}
SourceFile: "TestResult.java"
InnerClasses:
     public static final #68= #67 of #71; //Lookup=class java/lang/invoke/MethodHandles$Lookup of class java/lang/invoke/MethodHandles
BootstrapMethods:
  0: #35 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #36 ()Ljava/lang/Object;
      #37 invokespecial io/daydev/lambda/TestResult.lambda$process$0:(Lio/daydev/lambda/domain/Exchange;)Ljava/util/concurrent/CompletionStage;
      #38 ()Ljava/util/concurrent/CompletionStage;
