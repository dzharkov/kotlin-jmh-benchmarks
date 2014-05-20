# Benchmarks for Kotlin2JVM Compiler
=====================

## Building
To build this project you need to run this

    mvn -Dkotlin.version=<kotlin.version> clean compile

where `<kotlin.version>` is the version of Kotlin that you need to benchmark.

## Running
To run specific benchmark in kotlin-module you need to run

    java -jar kotlin/target/microbenchmarks.jar <benchmark-name-pattern> <jmh-options>
    
where `<benchmark-name-pattern>` is a regular expression that matches full name of benchmark (in format `Class.method`).

To see full list of `<jmh-options>` go to [JMH Page](http://openjdk.java.net/projects/code-tools/jmh/)
 
## Available benchmarks

* **InliningTest** is available in kotlin module. 
    
    It proofs that there are problems with inlining even in such a simple case
    
    There are tho methods: **inliningIdeal** and **varInlining**

* **MergeSort** is available both in kotlin and java modules.

* **SimpleElvisTest** is available both in kotlin and java modules
    
    It demonstrates that there are problems with combination of safe calls and elvis operators

* **Stars** is available both in kotlin and java modules
    
    Implementation of problem 1028 from [Timus](http://acm.timus.ru) written in Kotlin and Java to compare performance.
    
    There are variations **StarsFaster** and **StarsSemiFaster** in kotlin module.
    
* **SwitchTest** is available both in kotlin and java modules.
 
    It demonstrates that there were problems with `when` operator in case of using it like switch in Java
    
    It contains two methods: **testDenseSwitch** and **testSparseSwitch**
    
## Examples
To run all methods within **SwitchTest** benchmark you need to run

    java -jar java/target/microbenchmarks.jar .*SwitchTest.* -i 10 -wi 5 -f 1
    java -jar kotlin/target/microbenchmarks.jar .*SwitchTest.* -i 10 -wi 5 -f 1

There will be 5 warmup iterations and 10 main iterations for each method

To run all benchmarks you should run something like this

    java -jar java/target/microbenchmarks.jar .* 
    java -jar kotlin/target/microbenchmarks.jar .*
