import com.sun.prism.image.Coords

import scala.::
import scala.util.Random

class Game {
  //variables globales
  var width=0
  var height = 0
  var reveledCell=0;
  var nb_mine=0
  var grid:Array[Array[String]]=Array()



  def get_dimension(choice: Int): (Int, Int) = {
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
      gridWithSolution = Mine().random_mine(width,height,gridWithSolution)

    }
    (grid, gridWithSolution)
  }

  def is_inside(x:Int,y:Int):Boolean={
    var result=false
    if((x<height)&&y<width){
      result = true
    }
    result
  }
  def already_played(x:Int,y:Int):Boolean={
    var result = false
    println(x+"-"+y+"->"+grid(x)(y))
    if (!grid(x)(y).contains(".")) {
      result = true
    }
    result
  }
  def get_new_coords():(Int,Int)={
    var coords = scala.io.StdIn.readLine("Saisir les coordonnées de la case à cliquer? ")
    var x= coords.split(",")
    //on verifie si le couple est une combinaison possible de la matrice
    while (!is_inside(x(0).toInt,x(1).toInt)) {
      coords = scala.io.StdIn.readLine("Veillez choisir des coordonnées valide\nSaisir les coordonnées de la case à cliquer?")
      x= coords.split(",")
    }
    //on verifie si le couple est une combinaison deja joué
    while (already_played(x(0).toInt, x(1).toInt)) {
      coords = scala.io.StdIn.readLine("Ces coordonnées ont deja été choisi, veillez en choisir d'aut\nSaisir les coordonnées de la case à cliquer?")
      x = coords.split(",")
    }

    val new_coords: (Int, Int) = (x(0).toInt, x(1).toInt)
    println("Les coordonnées choisies: " + new_coords)

    new_coords

  }
  def play(choice:Int):Unit={
    println("Début de partie")
    val mine= new Mine().get_nb_mine(choice)
    val width=get_dimension(choice)._1
    val length=get_dimension(choice)._2
    val initialGrid=init_board_game(width,length,mine)._1
    val gridWithMines=init_board_game(width,length,mine)._2 // solution finale
    reveledCell=(width*height)-nb_mine
    val mineSweeper = new MineSweeper(reveledCell)

    val cell= new Neighbor()
    mineSweeper.displayGrid(init_game(cell,gridWithMines))
    //
    mineSweeper.displayGrid(initialGrid)

    //println(random_coords(initialGrid))
    grid=initialGrid
    grid=mineSweeper.interact(grid,gridWithMines,get_new_coords())
    while(!mineSweeper.is_fin_partie()){
      mineSweeper.displayGrid(grid)
      grid=mineSweeper.interact(grid,gridWithMines,get_new_coords())
    }
    mineSweeper.displayGrid(grid)



  }

  //gestion des voisins

  def init_game(cell:Neighbor, grid:Array[Array[String]]):Array[Array[String]]={
    var cell= new Neighbor()
    val finalGrid=grid
    val height=finalGrid.length
    val width=finalGrid(0).length
    for (i <- 0 until height) {
      for (j <- 0 until width) {
        if(!finalGrid(i)(j).equals("-1")) {
          finalGrid(i)(j)=incr_tab(cell.get_neighbors((i, j), finalGrid),finalGrid).toString
        }
      }
    }
    finalGrid

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