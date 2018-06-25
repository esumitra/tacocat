/**
  * scala cats law testing for simple app
  */

package com.example

import cats._
import cats.implicits._
import cats.tests.CatsSuite
import cats.laws.discipline.FunctorTests
import cats.kernel.laws.discipline.MonoidTests

class FunctorLawsTests extends CatsSuite {
  checkAll("Option.FunctorLaws", FunctorTests[Option].functor[Int, Int, Int])
}

class IntMonoidLawsTest extends CatsSuite {
  checkAll("Int.MonoidLaws", MonoidTests[Int].monoid)
}


