import scala.util.Random

object Main {
  def choix_niveau(choice: Int): (Array[Array[String]],Array[Array[String]]) = {

    var width = 0
    var height = 0
    var mines = 0

    if (choice == 1) {
      println("Niveau choisi : Facile !")
      width = 10
      height = 8
      mines = 9

    } else if (choice == 2) {
      println("Niveau choisi : Moyen !")
      width = 18
      height = 14
      mines = 38
    } else if (choice == 3) {
      println("Niveau choisi : Difficile !")
      width = 24
      height = 20
      mines = 99
    }

    var grid = generateMines(width, height, mines)
    //return grid
    grid

  }

  def displayGrid(grid: Array[Array[String]]): Unit = {
    print("   ")
    for (i <- 0 until grid(0).length) {
      print(i + " ")

    }
    println()
    for (y <- 0 until grid.length) {
      if(y.toString.length == 1){
        print(y + "  ")
      }
      else {print(y + " ")}
      for (x <- 0 until grid(0).length) {
        print(grid(y)(x) + " ")
      }
      println()
    }
  }

  // Génère une grille de mines aléatoires de la taille donnée
  def generateMines(width: Int, height: Int, mines: Int): (Array[Array[String]],Array[Array[String]]) = {
    val grid = Array.fill(height, width)(".")
    val gridWithSolution = Array.fill(height, width)(".")

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

      val x = Random.nextInt(width)
      val y = Random.nextInt(height)

      if (x.toString.length == 1) {
        gridWithSolution(y)(x) = "*"

      }
      else {
        gridWithSolution(y)(x) = " *"

      }
      //gridWithSolution(y)(x) = "*"
    }
    (grid,gridWithSolution)
  }

  def star_game(grid: Array[Array[String]]):Unit={
      displayGrid(grid)
  }
  def main(args: Array[String]): Unit = {
    println("--------------- DEMINEUR ---------------")
    println("Niveau Facile\t\t :\t 1\nNiveau Moyen\t\t :\t 2 \nNiveau Difficile\t :\t 3")
    var choice = scala.io.StdIn.readLine("Quel niveau souhaité vous jouer? ").toInt
    while (choice != 1 && choice != 2 && choice != 3) {
      choice = scala.io.StdIn.readLine("Veillez choisir entre 1, 2 et 3\nQuel niveau souhaité vous jouer? ").toInt
    }

    val grid=choix_niveau(choice)
    star_game(grid._1)
    println("Solution de la gille : ")
    star_game(grid._2)



  }
}