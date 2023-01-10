class MineSweeper {
  def displayGrid(grid: Array[Array[String]]): Unit = {
    print("   ")
    for (i <- 0 until grid(0).length) {
      print(i + " ")

    }
    println()
    for (y <- 0 until grid.length) {
      if (y.toString.length == 1) {
        print(y + "  ")
      }
      else {
        print(y + " ")
      }
      for (x <- 0 until grid(0).length) {
        print(grid(y)(x) + " ")
      }
      println()
    }
  }

  def interact(grid: Array[Array[String]], couple: (Int,Int)):Unit={
    print("Etat de la partie")
  }

}
