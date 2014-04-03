Compiled from "StarsSemiFaster.kt"
public final class org.jetbrains.benchmarks.StarsKotlinSemiFaster$AVLTree$NodeGetter implements kotlin.jvm.internal.KObject {
  public final <K extends java/lang/Object, D extends java/lang/Object> int height(org.jetbrains.benchmarks.StarsKotlinSemiFaster$AVLTree$Node<K, D>);
    Code:
       0: iconst_1      
       1: aload_1       
       2: ifnonnull     7
       5: pop           
       6: iconst_0      
       7: ifeq          20
      10: aload_1       
      11: invokevirtual #8                  // Method org/jetbrains/benchmarks/StarsKotlinSemiFaster$AVLTree$Node.getHeight:()I
      14: invokestatic  #59                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
      17: goto          24
      20: iconst_0      
      21: invokestatic  #59                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
      24: checkcast     #38                 // class java/lang/Number
      27: invokevirtual #7                  // Method java/lang/Number.intValue:()I
      30: ireturn       

  public final <K extends java/lang/Object, D extends java/lang/Object> int size(org.jetbrains.benchmarks.StarsKotlinSemiFaster$AVLTree$Node<K, D>);
    Code:
       0: iconst_1      
       1: aload_1       
       2: ifnonnull     7
       5: pop           
       6: iconst_0      
       7: ifeq          20
      10: aload_1       
      11: invokevirtual #32                 // Method org/jetbrains/benchmarks/StarsKotlinSemiFaster$AVLTree$Node.getSize:()I
      14: invokestatic  #59                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
      17: goto          24
      20: iconst_0      
      21: invokestatic  #59                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
      24: checkcast     #38                 // class java/lang/Number
      27: invokevirtual #7                  // Method java/lang/Number.intValue:()I
      30: ireturn       

  public org.jetbrains.benchmarks.StarsKotlinSemiFaster$AVLTree$NodeGetter();
    Code:
       0: aload_0       
       1: invokespecial #55                 // Method java/lang/Object."<init>":()V
       4: return        
}
