/**
  * EventAggregate Monoid tests
  * See https://typelevel.org/cats/typeclasses/lawtesting.html
  */
package com.example.data


import cats._
import cats.implicits._
import org.scalacheck.{Arbitrary, Gen}
import Arbitrary.arbitrary
import org.scalatest.FlatSpec
import cats.tests.CatsSuite
import cats.kernel.laws.discipline.MonoidTests

class AllTimesSumMonoidLawsTest extends CatsSuite {
  implicit def allTimesSumEq: Eq[AllTimesSum] = Eq.fromUniversalEquals
  implicit def arbAllTimesSumArb: Arbitrary[AllTimesSum] =
    Arbitrary {
      for {
        id <- arbitrary[String]
        tot <- arbitrary[Long]
      } yield AllTimesSum(id,tot)
    }
  checkAll("AllTimesSum.MonoidLaws", MonoidTests[AllTimesSum].monoid)
}
