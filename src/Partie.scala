import scala.util.Random

class Partie {
  def get_nb_mine(choice: Int): Int = {
    var nb_mine = 0
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

  def get_dimension(choice: Int): (Int, Int) = {
    var width = 0
    var height = 0
    if (choice == 1) {
      width = 10
      height = 8
    }
    else if (choice == 2) {
      width = 18
      height = 14
    }
    else if (choice == 3) {
      width = 24
      height = 20
    }
    (width, height);
  }

  def random_mine(width: Int, height: Int,gridWithSolution:Array[Array[String]]):Array[Array[String]]={
    val x = Random.nextInt (width)
    val y = Random.nextInt (height)
    if (x.toString.length == 1) {
      gridWithSolution(y)(x) = "*"

    }
    else {
      gridWithSolution(y)(x) = " *"

    }
    gridWithSolution

  }
  // Génère une grille de mines aléatoires de la taille donnée
  def init_game(width: Int, height: Int, mines: Int): (Array[Array[String]], Array[Array[String]]) = {
    val grid = Array.fill(height, width)(".")
    var gridWithSolution = Array.fill(height, width)(".")

    for (i <- 0 until width) {
      for (j <- 0 until height) {
        val x = i
        val y = j
        if (x.toString.length == 1) {
          grid(y)(x) = "."
          gridWithSolution(y)(x) = "."
        }
        else {
          grid(y)(x) = " ."
          gridWithSolution(y)(x) = " ."

        }
      }
    }

    for (i <- 0 until mines) {
        gridWithSolution = random_mine(width,height,gridWithSolution)

    }
    (grid, gridWithSolution)
  }

 def random_coords(grid:Array[Array[String]]):List[(Int,Int)]={
   val coords1=(1,1)
   val coords2=(1,1)
   val list=List(coords1,coords2)

   list
 }
  def get_new_coords():Unit={
    var coords = scala.io.StdIn.readLine("Saisir les coordonnées de la case à cliquer? ")
    println("Vous avez choisi : "+coords)
  }
  def play(choice:Int):Unit={
    println("Début de partie")
    val mine=get_nb_mine(choice)
    val width=get_dimension(choice)._1
    val length=get_dimension(choice)._2
    val initialGrid=init_game(width,length,mine)._1
    val solutionGrid=init_game(width,length,mine)._2 // solution finale
    val mineSweeper = new MineSweeper()
    mineSweeper.displayGrid(initialGrid)
    get_new_coords()
    println(random_coords(initialGrid))

  }


}
