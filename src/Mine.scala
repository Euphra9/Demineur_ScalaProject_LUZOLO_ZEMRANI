case class Mine(var reveledCell: Int) {
  var tmpList:List[(Int,Int)]=List() // utilisée pour stocker la liste des voisins d'une cellule

  //si l'utilisateur perd, on affiche toutes les mines cachées
  def show_all_mine(grid: Array[Array[String]], Resultgrid: Array[Array[String]]): Array[Array[String]] = {
    for (i <- 0 until Resultgrid.length) {
      for (j <- 0 until Resultgrid(0).length) {
        if (Resultgrid(i)(j) == "-1") {
          grid(i)(j) = "*"
        }
      }
    }
    grid
  }

  //Si l'utilisateur interagit avec une case vide,
  // elle est révélée. Ses voisines le sont récursivement jusqu’à être
  //adjacentes à une mine.
  def is_nextTo_mine(couple: (Int, Int),
                     grid: Array[Array[String]],
                     Resultgrid: Array[Array[String]]): Array[Array[String]] = {
    val partie = new Game()
    val liste = partie.get_neighbors((couple._1, couple._2), Resultgrid)
    var tmpGrid = grid


    if (Resultgrid(couple._1)(couple._2) == "0") {
      for (x <- liste) {
        if (!tmpList.contains(x)) {
          tmpList = tmpList :+ x
          tmpGrid = is_nextTo_mine(x, tmpGrid, Resultgrid)
        }
      }

    }
    tmpGrid(couple._1)(couple._2) = Resultgrid(couple._1)(couple._2)
    reveledCell -= 1


    tmpGrid

  }
}