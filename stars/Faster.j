; StarsKotlinFaster$AVLTree$NodeGetter.j

; Generated by ClassFileAnalyzer (Can)
; Analyzer and Disassembler for Java class files
; (Jasmin syntax 2, http://jasmin.sourceforge.net)
;
; ClassFileAnalyzer, version 0.7.0 


.bytecode 50.0
.source StarsFaster.kt
.class public final org/jetbrains/benchmarks/StarsKotlinFaster$AVLTree$NodeGetter
.super java/lang/Object
.implements kotlin/jvm/internal/KObject
.annotation visible Lkotlin/jvm/internal/KotlinClass;
  abiVersion I = 15
  data [s = ")Qaj3f$H/:3F\n:fK*\t2^1sg.{G\\5o\rH/:=xMCkKR\'/Y5og*Q!-8dQ6\f\'o[:sPl_RdNy%t -SQLw\r;\t9|G->$WMCJ]RTad;beN\\u7j]7^3sI3F\n:fKruZ3\tML\'0&A!!QAC)1B\t1QaCa!B!AA)1B\t1Qaa!B!9AA\tAy\reQ!E[1\"!!%QcAa\t!!)QcAa\tg$BBAA!\nAI1!!A)Q!E#)A1B\tiC.YC)2!B\t\b1CC)2!B\t\b1CO\tAa!D!!B\t!\t\n\t%QACa\tUC\t)#R!\"C5\t\"B\f)!1\rC\t)#R\r!A!C"
  .end annotation
.inner class public static final NodeGetter inner org/jetbrains/benchmarks/StarsKotlinFaster$AVLTree$NodeGetter outer org/jetbrains/benchmarks/StarsKotlinFaster$AVLTree
.inner class public static AVLTree inner org/jetbrains/benchmarks/StarsKotlinFaster$AVLTree outer org/jetbrains/benchmarks/StarsKotlinFaster

.method public final height(Lorg/jetbrains/benchmarks/StarsKotlinFaster$AVLTree$Node;)I
  .limit stack 2
  .limit locals 2
  .var 0 is this Lorg/jetbrains/benchmarks/StarsKotlinFaster$AVLTree$NodeGetter; from Label0 to Label18
  .var 1 is node Lorg/jetbrains/benchmarks/StarsKotlinFaster$AVLTree$Node; from Label0 to Label18
Label0:
  .line 26
  0: iconst_1
  1: aload_1
  2: ifnonnull Label7
  5: pop
  6: iconst_0
Label7:
  7: ifeq Label17
  10: aload_1
  11: invokevirtual org/jetbrains/benchmarks/StarsKotlinFaster$AVLTree$Node/getHeight()I
  14: goto Label18
Label17:
  17: iconst_0
Label18:
  18: ireturn
  ; same_locals_1_stack_item_frame (frameNumber = 0)
  ; frame_type = 71, offset_delta = 7
  ; frame bytes: 71 1 
  .stack 
    offset 7
    stack Integer
    .end stack
  ; same_frame (frameNumber = 1)
  ; frame_type = 9, offset_delta = 9
  ; frame bytes: 9 
  .stack 
    offset 17
    .end stack
  ; same_locals_1_stack_item_frame (frameNumber = 2)
  ; frame_type = 64, offset_delta = 0
  ; frame bytes: 64 1 
  .stack 
    offset 18
    stack Integer
    .end stack
  .signature "<K:Ljava/lang/Object;D:Ljava/lang/Object;>(Lorg/jetbrains/benchmarks/StarsKotlinFaster$AVLTree$Node<TK;TD;>;)I"
  .annotation visibleparam 1 Ljet/runtime/typeinfo/JetValueParameter;
    name s = "node"
    type s = "?"
    .end annotation
  .annotation invisibleparam 1 Lorg/jetbrains/annotations/Nullable;
    .end annotation
.end method

.method public final size(Lorg/jetbrains/benchmarks/StarsKotlinFaster$AVLTree$Node;)I
  .limit stack 2
  .limit locals 2
  .var 0 is this Lorg/jetbrains/benchmarks/StarsKotlinFaster$AVLTree$NodeGetter; from Label0 to Label18
  .var 1 is node Lorg/jetbrains/benchmarks/StarsKotlinFaster$AVLTree$Node; from Label0 to Label18
Label0:
  .line 30
  0: iconst_1
  1: aload_1
  2: ifnonnull Label7
  5: pop
  6: iconst_0
Label7:
  7: ifeq Label17
  10: aload_1
  11: invokevirtual org/jetbrains/benchmarks/StarsKotlinFaster$AVLTree$Node/getSize()I
  14: goto Label18
Label17:
  17: iconst_0
Label18:
  18: ireturn
  ; same_locals_1_stack_item_frame (frameNumber = 0)
  ; frame_type = 71, offset_delta = 7
  ; frame bytes: 71 1 
  .stack 
    offset 7
    stack Integer
    .end stack
  ; same_frame (frameNumber = 1)
  ; frame_type = 9, offset_delta = 9
  ; frame bytes: 9 
  .stack 
    offset 17
    .end stack
  ; same_locals_1_stack_item_frame (frameNumber = 2)
  ; frame_type = 64, offset_delta = 0
  ; frame bytes: 64 1 
  .stack 
    offset 18
    stack Integer
    .end stack
  .signature "<K:Ljava/lang/Object;D:Ljava/lang/Object;>(Lorg/jetbrains/benchmarks/StarsKotlinFaster$AVLTree$Node<TK;TD;>;)I"
  .annotation visibleparam 1 Ljet/runtime/typeinfo/JetValueParameter;
    name s = "node"
    type s = "?"
    .end annotation
  .annotation invisibleparam 1 Lorg/jetbrains/annotations/Nullable;
    .end annotation
.end method

.method public <init>()V
  .limit stack 1
  .limit locals 1
  .var 0 is this Lorg/jetbrains/benchmarks/StarsKotlinFaster$AVLTree$NodeGetter; from Label0 to Label4
Label0:
  0: aload_0
  1: invokespecial java/lang/Object/<init>()V
Label4:
  4: return
  .annotation invisible Lorg/jetbrains/annotations/NotNull;
    .end annotation
.end method


