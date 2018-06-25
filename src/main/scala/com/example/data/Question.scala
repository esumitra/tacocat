/**
  * Question data structures
  */
package com.example.data
case class Question(questionId: String, uri: String, subject: String, grade: Int, difficulty: Int)

object Question {
  val item1 = Question("q1", "/questions/q1", "math", 4, 2)
  val item2 = Question("q2", "/questions/q2", "math", 4, 3)
  val items = List(
    Question("mq1", "/questions/q1", "math", 4, 2),
    Question("mq2", "/questions/q2", "math", 4, 3),
    Question("mq5", "/questions/q5", "math", 0, 2),
    Question("mqa", "/questions/qa", "math", 2, 1),
    Question("mq4", "/questions/q4", "math", 2, 4),
    Question("mq", "/questions/mq", "math", 4, 2),
    Question("eq1", "/questions/ela-q1", "ela", 4, 2),
    Question("eq2", "/questions/ela-q2", "ela", 4, 3),
    Question("eq5", "/questions/ela-q5", "ela", 0, 2),
    Question("eqa", "/questions/ela-qa", "ela", 2, 1),
    Question("eq4", "/questions/ela-q4", "ela", 2, 4),
    Question("eqq", "/questions/ela-qq", "ela", 4, 2)
  )

  // implicit ordering to order by grade and then by difficulty
  implicit val questionOrder:Ordering[Question] = Ordering[(Int, Int)].on(q => (q.grade, q.difficulty))

}
