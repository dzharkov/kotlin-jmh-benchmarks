Compiled from "Stars.kt"
public final class org.jetbrains.benchmarks.StarsKotlin$AVLTree$NodeGetter implements kotlin.jvm.internal.KObject {
  public final <K extends java/lang/Object, D extends java/lang/Object> int height(org.jetbrains.benchmarks.StarsKotlin$AVLTree$Node<K, D>);
    Code:
       0: aload_1       
       1: dup           
       2: ifnull        14
       5: invokevirtual #27                 // Method org/jetbrains/benchmarks/StarsKotlin$AVLTree$Node.getHeight:()I
       8: invokestatic  #33                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
      11: goto          16
      14: pop           
      15: aconst_null   
      16: dup           
      17: ifnull        29
      20: checkcast     #35                 // class java/lang/Number
      23: invokevirtual #38                 // Method java/lang/Number.intValue:()I
      26: goto          31
      29: pop           
      30: iconst_0      
      31: ireturn       

  public final <K extends java/lang/Object, D extends java/lang/Object> int size(org.jetbrains.benchmarks.StarsKotlin$AVLTree$Node<K, D>);
    Code:
       0: aload_1       
       1: dup           
       2: ifnull        14
       5: invokevirtual #45                 // Method org/jetbrains/benchmarks/StarsKotlin$AVLTree$Node.getSize:()I
       8: invokestatic  #33                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
      11: goto          16
      14: pop           
      15: aconst_null   
      16: dup           
      17: ifnull        29
      20: checkcast     #35                 // class java/lang/Number
      23: invokevirtual #38                 // Method java/lang/Number.intValue:()I
      26: goto          31
      29: pop           
      30: iconst_0      
      31: ireturn       

  public org.jetbrains.benchmarks.StarsKotlin$AVLTree$NodeGetter();
    Code:
       0: aload_0       
       1: invokespecial #50                 // Method java/lang/Object."<init>":()V
       4: return        
}
