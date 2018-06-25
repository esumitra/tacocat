package com.example.demo
import cats._, cats.data._, cats.implicits._
import com.example.data._
import scala.language.implicitConversions

/*
REPL:
import com.example.demo._
 */
object FoldableDemo {
  import com.example.data._
  import cats._, cats.data._, cats.implicits._

  // Basic operations
  val lt2 = LeftistTree(1) |+| LeftistTree(3)
  lt2.printTree()
  LeftistTree(3 to 6).printTree()

  // Foldable Int operations
  LeftistTree(List(10,2,5,30,1)).size

  LeftistTree(List(10,2,5,30,1)).toList

  LeftistTree(List(10,2,5,30,1)).dropWhile_(_ < 10)

  LeftistTree(List(10,2,5,30,1)).filter_(_ % 2 == 0)

  LeftistTree(List(10,2,5,30,1)).foldMap((x:Int) => LeftistTree(x * 3)).printTree()

  LeftistTree(List(10,2,5,30,1)).reduceLeftOption(_ + _)

  LeftistTree(List(10,2,5,30,1)).find(_ == 5)

  // Question Bank operations

  Question.items.foreach(println)

  val qbank = LeftistTree(Question.items)

  // convert to list and check question ordering
  qbank.printTree()

  qbank.toList.map(q => (q.grade, q.difficulty)).foreach(println)

  // filter math questions
  qbank.filter_(q => q.subject == "math").foreach(println)

  // check if question bank has difficulty 4 questions
  qbank.exists(q => q.difficulty == 4)
}
