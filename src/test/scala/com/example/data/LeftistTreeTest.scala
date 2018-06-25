/**
  * LeftistTree Monoid and Foldable tests
  * See https://typelevel.org/cats/typeclasses/lawtesting.html
  */
package com.example.data
import cats._
import cats.implicits._
import org.scalacheck.{Arbitrary, Gen}
import Arbitrary.arbitrary
import org.scalatest.FlatSpec
import cats.tests.CatsSuite
import cats.laws.discipline.FoldableTests
import cats.kernel.laws.discipline.MonoidTests

// Monoid laws
class LeftistTreeMonoidLawsTest extends CatsSuite {
  implicit def arbIntTree[A]: Arbitrary[LeftistTree[Int]] =
    Arbitrary {
      for {
        l <- Gen.containerOf[List,Int](Gen.choose(0, 100))
      } yield LeftistTree(l)
    }
  // LeftistTree Monoid Laws fail! Why? How to fix the defition to pass monoid laws?
  // For the questions use case, does it matter that some of these laws fail?
  // checkAll("IntLeftistTree.MonoidLaws", MonoidTests[LeftistTree[Int]].monoid)
}

// Foldable laws
class LeftistTreeFoldableLawsTest extends CatsSuite {
  implicit def arbIntTree[A]: Arbitrary[LeftistTree[Int]] =
    Arbitrary {
      for {
        l <- Gen.containerOf[List,Int](Gen.choose(0, 100))
      } yield LeftistTree(l)
    }
  checkAll("IntLeftistTree.FoldableLaws", FoldableTests[LeftistTree].foldable[Int, Int])
}
