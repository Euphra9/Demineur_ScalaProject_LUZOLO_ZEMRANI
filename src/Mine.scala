import scala.util.Random

case class Mine() extends Game{

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
          if (j.toString.length == 1) {
            grid(i)(j) = "*"
          }
          else {
            grid(i)(j) = " *"
          }
        }
      }
    }
    grid
  }

}