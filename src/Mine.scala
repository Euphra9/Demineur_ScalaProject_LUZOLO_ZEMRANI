import scala.util.Random

case class Mine() extends Game{
  var tmpList:List[(Int,Int)]=List() // utilisée pour stocker la liste des voisins d'une cellule

  def get_nb_mine(choice: Int): Int = {
    if (choice == 1) {
      nb_mine = 9
    }
    else if (choice == 2) {
      nb_mine = 38
    }
    else if (choice == 3) {
      nb_mine = 99
    }
    nb_mine;
  }

  def random_mine(width: Int, height: Int, gridWithSolution: Array[Array[String]]): Array[Array[String]] = {
    val x = Random.nextInt(width)
    val y = Random.nextInt(height)
    gridWithSolution(y)(x) = "-1"
    gridWithSolution

  }

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
    val cell= new Neighbor()
    val liste = cell.get_neighbors((couple._1, couple._2), Resultgrid)
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