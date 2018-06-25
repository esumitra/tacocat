/**
  * event data structures
  */
package com.example.data

case class LessonEvent(studentId: String, classid: String, lessonId: String, domainId: String, timeOnTask: Long)

object LessonEvent {
  val events = Stream[LessonEvent](
    LessonEvent("student1", "class1", "lesson1", "domain1", 30),
    LessonEvent("student1", "class1", "lesson1", "domain1", 45),
    LessonEvent("student1", "class1", "lesson2", "domain1", 30),
    LessonEvent("student1", "class1", "lesson2", "domain1", 30),
    LessonEvent("student1", "class1", "lesson2", "domain1", 40),
    LessonEvent("student1", "class1", "lesson1", "domain1", 30),
    LessonEvent("student2", "class1", "lesson2", "domain1", 40),
    LessonEvent("student2", "class1", "lesson1", "domain1", 30),
    LessonEvent("student1", "class1", "lesson20", "domain2", 40),
    LessonEvent("student1", "class1", "lesson21", "domain2", 35),
    LessonEvent("student2", "class1", "lesson20", "domain2", 40),
    LessonEvent("student2", "class1", "lesson21", "domain2", 25)
  )
}
