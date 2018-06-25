package com.example.data
import cats._, cats.data._, cats.implicits._

/**
  * Container trait for LeftistTree data structure
  * based on: http://typeocaml.com/2015/03/12/heap-leftist-tree/
  */

trait LeftistTree[A] {
  // ordering required to maintain heap property parent < child nodes 
  implicit val evidence: Ordering[A]

  def rank: Int = this match {
    case LTLeaf() => 0
    case LTBranch(_,_,_,r) => r
  }

  /**
    * helper function to display tree
    */
  def printTree(prefix: String = "", depth: Int = 0): Unit = {
    val pad = " " * 4 * depth
    this match {
      case LTLeaf() => println(s"${pad}${prefix}()")
      case LTBranch(left, k, right, r) => {
        println(s"${pad}${prefix}(${k})")
        left.printTree("L ", depth+1)
        right.printTree("R ", depth+1)
      }
    }
  }

  // all other tree operations
  def getMin: Option[A] = this match {
    case LTLeaf() => None
    case LTBranch(_, k, _, _) => k.some
  }

  def insert(a: A): LeftistTree[A] = this |+| LeftistTree(a)

  def merge(other: LeftistTree[A]): LeftistTree[A]  = this |+| other

  def deleteMin: (Option[A], LeftistTree[A]) = this match {
    case LTLeaf() => (None, LTLeaf())
    case LTBranch(left, k, right, _) => (k.some, left |+| right)
  }

}

case class LTLeaf[A](implicit ev: Ordering[A]) extends LeftistTree[A] {
  val evidence = ev
}
case class LTBranch[A](left: LeftistTree[A], k: A, right: LeftistTree[A], r: Int)(implicit ev: Ordering[A]) extends LeftistTree[A] {
  val evidence = ev
}

object LeftistTree {
  def empty[A](implicit ev: Ordering[A]): LeftistTree[A] = LTLeaf()
  def apply[A](k: A)(implicit ev: Ordering[A]): LeftistTree[A] = LTBranch(LeftistTree.empty, k, LeftistTree.empty, 1)
  def apply[A](ks: Seq[A])(implicit ev: Ordering[A]): LeftistTree[A] = Monoid.combineAll(ks.map(LeftistTree(_)))

  // Eq required for laws testing
  implicit def eqLeftistTree[A](implicit ev: Eq[A]): Eq[LeftistTree[A]] = new Eq[LeftistTree[A]] {
    def eqv(t1: LeftistTree[A], t2: LeftistTree[A]): Boolean = (t1,t2) match {
      case (LTLeaf(), LTLeaf()) => true
      case (LTBranch(l1, k1, r1, rank1), LTBranch(l2, k2, r2, rank2)) =>
        k1 === k2 && rank1 === rank2 && l1 === l2 && r1 === r2
      case _ => false
    }
  }


  // Monoid for merging trees
  implicit def leftistTreeMonoid[A](implicit ev: Ordering[A]): Monoid[LeftistTree[A]] = new Monoid[LeftistTree[A]] {
    import Ordering.Implicits._ // get convenience operator syntax for Ordering
    def empty: LeftistTree[A] = LeftistTree.empty
    def combine(t1: LeftistTree[A], t2: LeftistTree[A]): LeftistTree[A] = (t1, t2) match {
      case (LTLeaf(), t) => t
      case (t, LTLeaf()) => t
      case (LTBranch(l, k1, r, _),LTBranch(_, k2, _, _)) =>
        if (k1 > k2) {
          combine(t2, t1)
        } else {
          val merged = combine(r,t2)
          val rankLeft = l.rank
          val rankRight = merged.rank
          if (rankLeft >= rankRight) {
            LTBranch(l, k1, merged, rankRight+1)
          } else {
            LTBranch(merged, k1, l, rankLeft+1)
          }
        }
    }
  }

  // Foldable type class
  implicit def leftistTreeFoldable: Foldable[LeftistTree] = new Foldable[LeftistTree] {
    // eager evaluation
    def foldLeft[A, B](fa: com.example.data.LeftistTree[A],b: B)(f: (B, A) => B): B = fa match {
      case LTLeaf() => b
      case LTBranch(_, _, _, _) => {
        val (optA, restTree) = fa.deleteMin
        val newB = f(b, optA.get)
        foldLeft(restTree, newB)(f)
      }
    }
    // lazy evaluation
    def foldRight[A, B](fa: com.example.data.LeftistTree[A],lb: cats.Eval[B])(f: (A, cats.Eval[B]) => cats.Eval[B]): cats.Eval[B] = {
      def loop(as: com.example.data.LeftistTree[A]): Eval[B] = as match {
        case LTLeaf() => lb
        case LTBranch(_, _, _, _) => {
          val (optA, restTree) = as.deleteMin
          f(optA.get, Eval.defer(loop(restTree)))
        }
      }
      Eval.defer(loop(fa))
    }
  }

}
