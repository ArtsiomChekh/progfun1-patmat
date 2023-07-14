package patmat

class HuffmanSuite extends munit.FunSuite:

  import Huffman.*

  trait TestTrees {
    val t1 = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5)
    val t2 = Fork(Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5), Leaf('d', 4), List('a', 'b', 'd'), 9)
    val charsList = List('a', 'a', 'b', 'b', 'b', 'c')
    val tree: CodeTree = t1
    var listOfTrees: List[CodeTree] = List()
  }

  test("def times") {
    new TestTrees:
      assertEquals(times(charsList), List(('a', 2), ('b', 3), ('c', 1)))
  }

  test("def singleton") {
    new TestTrees:
      listOfTrees = tree :: listOfTrees
      assert(singleton(listOfTrees))
  }

  test("weight of a larger tree (10pts)") {
    new TestTrees:
      assertEquals(weight(t1), 5)
  }


  test("chars of a larger tree (10pts)") {
    new TestTrees:
      assertEquals(chars(t2), List('a', 'b', 'd'))
  }

  test("string2chars hello world") {
    assertEquals(string2Chars("hello, world"), List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("make ordered leaf list for some frequency table (15pts)") {
    assertEquals(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))), List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 3)))
  }


  test("combine of some leaf list (15pts)") {
    val leafList = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    val leafList2 = List(Leaf('e', 3), Leaf('t', 2), Leaf('x', 4))
    assertEquals(combine(leafList), List(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3), Leaf('x', 4)))
    assertEquals(combine(leafList2), List(Leaf('x', 4), Fork(Leaf('e', 3), Leaf('t', 2), List('e', 't'), 5)))
  }
  test("until") {
    val leafList = List(Leaf('e', 3), Leaf('t', 2), Leaf('x', 4))
    assertEquals(singleton(until(singleton, combine)(leafList)), true)
  }


  test("decode and encode a very short text should be identity (10pts)") {
    new TestTrees:
      assertEquals(decode(t1, encode(t1)("ab".toList)), "ab".toList)
  }


  import scala.concurrent.duration.*

  override val munitTimeout = 10.seconds
