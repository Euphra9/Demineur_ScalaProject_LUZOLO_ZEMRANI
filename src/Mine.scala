import scala.util.Random

case class Mine() extends Game{

  //Cette fonction permet de renvoyer le nombre de mines qu'il y aura dans le plateau
  def get_nb_mine(choice: Int): Int = {
    choice match {
      case 1 => nb_mine = 9
      case 2 => nb_mine = 38
      case 3 => nb_mine = 99
    }
    nb_mine
  }

  //Cette fonction sert à générer dans une cellule une mine
  def random_mine(width: Int, height: Int, gridWithSolution: Array[Array[String]]): Array[Array[String]] = {
    var x = Random.nextInt(width)
    var y = Random.nextInt(height)
    while (gridWithSolution(y)(x)=="-1"){
       x = Random.nextInt(width)
       y = Random.nextInt(height)
    }
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