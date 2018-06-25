## tacocat
<img src="http://www.thinkgeek.com/images/products/zoom/jthl_explode_kittens_tacocat_plush.jpg" width="30%" height="30%"/>

The tacocat project shows how to use cats to fold and add many things ...including folding tacos? Maybe not tacos, but you can apply the Monoid and Foldable typeclass FP patterns to many things. The slides show you how.

This project accompanies the Boston Scala Meetup talk at ~~link to talk~~

The project requires Java 8, Scala 2.12.1 and sbt 0.13.16 environment to run.

### Summary
Most Java and Scala programmers are familiar with Object Oriented Design Patterns. Functional Programming (FP) patterns attempt to bring similar techniques of providing robust solutions to common problems in software engineering using functional programming techniques. This talk illustrates the usefulness of FP patterns by applying these patterns to two common use cases using the Monoid and Foldable type classes with the Scala cats library.

**Slides**: ~~link to slides~~

### Where to find code samples
| Pattern        | Sample           | Code  |
| ------------- |---------------| ------|
| Monoid      | Demo script | [MonoidDemo.scala](https://github.com/esumitra/tacocat/blob/master/src/main/scala/com/example/demo/MonoidDemo.scala) |
| Monoid      | Aggregate Data types      |   [EventAggregate.scala](https://github.com/esumitra/tacocat/blob/master/src/main/scala/com/example/data/EventAggregate.scala) |
| Monoid | Monoid Laws tests      |    [EventAggregateTest.scala](https://github.com/esumitra/tacocat/blob/master/src/test/scala/com/example/data/EventAggregateTest.scala) |
| Foldable | Demo script      |    [FoldableDemo.scala](https://github.com/esumitra/tacocat/blob/master/src/main/scala/com/example/demo/FoldableDemo.scala) |
| Foldable | LeftistTree      |    [LeftistTree](https://github.com/esumitra/tacocat/blob/master/src/main/scala/com/example/data/LeftistTree.scala) |
| Foldable | Foldable Laws tests      |    [LeftistTreeTest.scala](https://github.com/esumitra/tacocat/blob/master/src/test/scala/com/example/data/LeftistTreeTest.scala) |

### Getting started
 Use the following commands to get started with your project

 - Compile: `sbt compile`
 - Create a "fat" jar: `sbt assembly`
 - Run tests: `sbt test`

### License
Copyright 2018, Edward Sumitra

Licensed under the Apache License, Version 2.0.
