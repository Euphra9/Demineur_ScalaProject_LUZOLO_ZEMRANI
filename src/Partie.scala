import com.sun.prism.image.Coords

import scala.::
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
    gridWithSolution(y)(x) = "-1"
    gridWithSolution

  }
  // Génère une grille de mines aléatoires de la taille donnée
  def init_board_game(width: Int, height: Int, mines: Int): (Array[Array[String]], Array[Array[String]]) = {
    val grid = Array.fill(height, width)(".")
    var gridWithSolution = Array.fill(height, width)("0")

    for (i <- 0 until width) {
      for (j <- 0 until height) {
        val x = i
        val y = j
        if (x.toString.length == 1) {
          grid(y)(x) = "."
        }
        else {
          grid(y)(x) = " ."
        }
        gridWithSolution(y)(x) = "0"

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
    val x= coords.split(",")
    val y:(Int,Int)=(x(0).toInt,x(1).toInt)
    println("Les coordonnées : "+y)

  }
  def play(choice:Int):Unit={
    println("Début de partie")
    val mine=get_nb_mine(choice)
    val width=get_dimension(choice)._1
    val length=get_dimension(choice)._2
    val initialGrid=init_board_game(width,length,mine)._1
    val gridWithMines=init_board_game(width,length,mine)._2 // solution finale
    val mineSweeper = new MineSweeper()

    //
    //mineSweeper.displayGrid(gridWithMines)
    //println("------")
    //mineSweeper.displayGrid(init_game(gridWithMines))
    //
    mineSweeper.displayGrid(initialGrid)
    get_new_coords()
    //println(random_coords(initialGrid))


  }

  //gestion des voisins

def init_game(grid:Array[Array[String]]):Array[Array[String]]={
  val finalGrid=grid
  val height=finalGrid.length
  val width=finalGrid(0).length
  for (i <- 0 until height) {
    for (j <- 0 until width) {
       // println("A l'emplacement (" + i + "," + j + ") ->" + finalGrid(i)(j) + " : " + get_neighbors((i, j), finalGrid))
     if(!finalGrid(i)(j).equals("-1")) {
      finalGrid(i)(j)=incr_tab(get_neighbors((i, j), finalGrid),finalGrid).toString
     }
    }
  }
  finalGrid

}
  def get_neighbors(coords:(Int,Int),grid:Array[Array[String]]): List[(Int,Int)]={
    var list_of_neighbors :List[(Int,Int)]=List()
    val i=coords._1
    val j= coords._2
    // couple des voisins
    val voisin_gauche=(i, j - 1)
    val voisin_droite=(i, j + 1)
    val voisin_haut=(i-1, j)
    val voisin_bas=(i+1, j)
    val voisin_diagonal_haut_gauche=(i - 1, j - 1)
    val voisin_diagonal_haut_droite=(i - 1, j + 1)
    val voisin_diagonal_bas_gauche=(i+1, j-1)
    val voisin_diagonal_bas_droite=(i+1, j + 1)

    //----
    // j'ai 3 voisins
    if (on_the_corner((i, j), (grid.length, grid(0).length))) {
      //grid(i)(j)="3"

      if (i==0){
        list_of_neighbors = list_of_neighbors :+ voisin_bas
        if(j==0) {
          list_of_neighbors = list_of_neighbors :+ voisin_droite
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_bas_droite
        }
        else {
          list_of_neighbors = list_of_neighbors :+ voisin_gauche
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_bas_gauche
        }
      }
      else{
        list_of_neighbors = list_of_neighbors :+ voisin_haut

        if (j == 0) {
          list_of_neighbors = list_of_neighbors :+ voisin_droite
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_haut_droite
        }
        else {
          list_of_neighbors = list_of_neighbors :+ voisin_gauche
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_haut_gauche
        }

      }

    }
    // j'ai 5 voisins
    else if (on_the_verge((i, j),(grid.length, grid(1).length))) {
      //grid(i)(j)="5"
        if(i==0) {
          list_of_neighbors = list_of_neighbors :+ voisin_gauche
          list_of_neighbors = list_of_neighbors :+ voisin_droite
          list_of_neighbors = list_of_neighbors :+ voisin_bas
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_bas_gauche
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_bas_droite
        }

        else if(j==0){
          list_of_neighbors = list_of_neighbors :+ voisin_haut
          list_of_neighbors = list_of_neighbors :+ voisin_droite
          list_of_neighbors = list_of_neighbors :+ voisin_bas
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_haut_droite
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_bas_droite
        }
        else if (i == grid.length-1) {
          list_of_neighbors = list_of_neighbors :+ voisin_gauche
          list_of_neighbors = list_of_neighbors :+ voisin_droite
          list_of_neighbors = list_of_neighbors :+ voisin_haut
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_haut_gauche
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_haut_droite

        }
        else if(j==grid(0).length-1){
          list_of_neighbors = list_of_neighbors :+ voisin_haut
          list_of_neighbors = list_of_neighbors :+ voisin_gauche
          list_of_neighbors = list_of_neighbors :+ voisin_bas
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_haut_gauche
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_bas_gauche
        }
      }

    else {
      //grid(i)(j)="8"
      list_of_neighbors = list_of_neighbors :+ voisin_haut
          list_of_neighbors = list_of_neighbors :+ voisin_gauche
          list_of_neighbors = list_of_neighbors :+ voisin_droite
          list_of_neighbors = list_of_neighbors :+ voisin_bas
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_haut_gauche
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_haut_droite
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_bas_gauche
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_bas_droite

    }


    list_of_neighbors
  }
  def on_the_corner(coords:(Int,Int), size:(Int,Int)):Boolean={
    var result=false
    if(coords._1 == 0){
      if (coords._2 == 0 || coords._2==size._2-1) {
        result = true
      }
    }
    else if (coords._1 == size._1 - 1) {
      if (coords._2 == 0 || coords._2 == size._2 - 1) {
        result = true
      }
    }
    result
  }

  def on_the_verge(coords: (Int, Int), size: (Int, Int)): Boolean = {
    var result = false
    if(!on_the_corner(coords,size))
    {
      if((coords._1==0)
        || (coords._2==0)
        || (coords._1==size._1-1) || (coords._2 == size._2-1))
      {
        result=true
      }
    }


    result
  }

  def incr_tab(neighbors: List[(Int,Int)],grid:Array[Array[String]]):Int={
    var nb_mine=0
      for (ngb <- neighbors) {
        if (grid(ngb._1)(ngb._2).equals("-1")) {
          nb_mine += 1
        }
    }

    nb_mine
  }

}
