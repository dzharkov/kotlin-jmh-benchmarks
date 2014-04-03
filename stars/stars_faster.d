Compiled from "StarsFaster.kt"
public final class org.jetbrains.benchmarks.StarsKotlinFaster$AVLTree$NodeGetter implements kotlin.jvm.internal.KObject {
  public final <K extends java/lang/Object, D extends java/lang/Object> int height(org.jetbrains.benchmarks.StarsKotlinFaster$AVLTree$Node<K, D>);
    Code:
       0: iconst_1      
       1: aload_1       
       2: ifnonnull     7
       5: pop           
       6: iconst_0      
       7: ifeq          17
      10: aload_1       
      11: invokevirtual #27                 // Method org/jetbrains/benchmarks/StarsKotlinFaster$AVLTree$Node.getHeight:()I
      14: goto          18
      17: iconst_0      
      18: ireturn       

  public final <K extends java/lang/Object, D extends java/lang/Object> int size(org.jetbrains.benchmarks.StarsKotlinFaster$AVLTree$Node<K, D>);
    Code:
       0: iconst_1      
       1: aload_1       
       2: ifnonnull     7
       5: pop           
       6: iconst_0      
       7: ifeq          17
      10: aload_1       
      11: invokevirtual #34                 // Method org/jetbrains/benchmarks/StarsKotlinFaster$AVLTree$Node.getSize:()I
      14: goto          18
      17: iconst_0      
      18: ireturn       

  public org.jetbrains.benchmarks.StarsKotlinFaster$AVLTree$NodeGetter();
    Code:
       0: aload_0       
       1: invokespecial #39                 // Method java/lang/Object."<init>":()V
       4: return        
}
