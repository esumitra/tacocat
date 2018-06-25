package com.example.demo
import cats._, cats.data._, cats.implicits._
import com.example.data._
import scala.language.implicitConversions

/*
REPL:
import com.example.demo._
 */
object MonoidDemo {
  import com.example.data._
  import cats._, cats.data._, cats.implicits._

  // start with a lesson event stream
  val es = LessonEvent.events
  es.foreach(println)

  // calculate total time-on-task for each student
  es
    .map(e => AllTimesSum(e.studentId, e.timeOnTask))
    .groupBy(_.studentId)
    .mapValues(_.map(_.timeOnTask).sum)

  // calculate average time-on-task for each student
  es
    .map(e => AllTimesSum(e.studentId, e.timeOnTask))
    .groupBy(_.studentId)
    .mapValues(les => les.map(_.timeOnTask).sum.toDouble / les.size)

  // with Monoids
  // calculate total time-on-task for each student
  es
    .map(e => AllTimesSum(e.studentId, e.timeOnTask))
    .groupBy(_.studentId)
    .mapValues(Monoid.combineAll(_))

  // calculate average time-on-task for each student
  es
    .map(e => AllTimesAvg(e.studentId, e.timeOnTask, 1))
    .groupBy(_.studentId)
    .mapValues(Monoid.combineAll(_))

  // pretty print
  es
    .map(e => AllTimesAvg(e.studentId, e.timeOnTask, 1))
    .groupBy(_.studentId)
    .mapValues(Monoid.combineAll(_).value)
    .foreach(println)

  // we can follow the same pattern for other aggregates
  es
    .map(e => StudentLessonSum(e.studentId, e.lessonId, e.timeOnTask))
    .groupBy(e => (e.studentId, e.lessonId))
    .mapValues(Monoid.combineAll(_))
    .foreach(println)

  // what can we do with Monoids?
  val List(s1, s2) = es.map(e => AllTimesSum(e.studentId, e.timeOnTask)).take(2).toList

  // combine them
  s1 combine s2

  // combine using a symbolic operator |+|
  s1 |+| s2

  // below does not work
  // List(s1,s2).sum

  // but,
  // combineAll monoids in a sequence
  Monoid.combineAll(List(s1,s2))

  // fold a sequence of monoids
  List(s1,s2).foldLeft(AllTimesSum("",0))(_ |+| _)

  // or foldMap
  es
    .foldMap((e:LessonEvent) => Map(e.studentId -> AllTimesSum(e.studentId, e.timeOnTask)))
    .values
    .foreach(println)

  // what just happened? no groupBy but still get the tot sum by student
  Map("1" -> 3) |+| Map("1" -> 4)
}
