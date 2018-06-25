/**
  * event aggregates
  */
package com.example.data
import cats._, cats.data._, cats.implicits._
import scala.language.implicitConversions

/*
 Monoid definitions should satisfy the Monoid laws below
 associative law: combine(x, combine(y, z)) == combine(combine(x, y), z)
 identity element: combine(x, empty) == combine(empty, x) == x
 */

case class AllTimesSum(studentId: String, timeOnTask: Long)
object AllTimesSum {
  implicit val allTimesSumMonoid = new Monoid[AllTimesSum] {
    def empty = AllTimesSum("", 0)
    def combine(s1: AllTimesSum, s2: AllTimesSum): AllTimesSum = {
      // studentId needs to be defined in such a way that Monoid laws pass
      val studentId =
        if (s1.studentId === s2.studentId) s1.studentId
        else (s1.studentId + s2.studentId)
      AllTimesSum(studentId, s1.timeOnTask + s2.timeOnTask)
    }
  }
}

case class AllTimesAvg(studentId: String, sum: Long, count: Long) {
  def value: Double = sum.toDouble / count.toDouble
}
object AllTimesAvg {
  implicit val timesAvgMonoid = new Monoid[AllTimesAvg] {
    def empty = AllTimesAvg("", 0, 0)
    def combine(a1: AllTimesAvg, a2: AllTimesAvg): AllTimesAvg = {
      val id = if (a1.studentId === a2.studentId) a1.studentId else (a1.studentId + a2.studentId)
      AllTimesAvg(id, a1.sum+a2.sum, a1.count+a2.count)
    }
  }
}

case class StudentLessonSum(studentId: String, lessonId:String, timeOnTask: Long)
object StudentLessonSum {
  implicit val studentLessonMonoid = new Monoid[StudentLessonSum] {
    def empty = StudentLessonSum("", "", 0)
    def combine(s1: StudentLessonSum, s2: StudentLessonSum): StudentLessonSum = {
      val (studentId, lessonId) =
        if (s1.studentId === s2.studentId)
          (s1.studentId, s1.lessonId) else ((s1.studentId+s2.studentId,(s1.lessonId+s2.lessonId)))
      StudentLessonSum(studentId, lessonId, s1.timeOnTask+s2.timeOnTask)
    }
  }
}
