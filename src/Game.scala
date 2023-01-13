
class Game {
  //variables globales
  var width=0
  var height = 0
  var reveledCell=0
  var nb_mine=0
  var grid:Array[Array[String]]=Array()



// Fonction qui renvoit les dimmensions du plateau de jeu en fonction du niveau choisi par l'utilisateur

  def get_dimension(choice: Int): (Int, Int) = {
    choice match{
      case 1 => width = 10
                height = 8
      case 2 => width = 18
                height = 14
      case 3 => width = 24
                height = 20
    }
    (width, height)
  }


  // Fonction qui fabrique à la fois le plateau de jeu visible par l'utilisateur
  // ainsi que la matrice contenant la solution
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

  //fonction qui verifie que la saisie utilisateur fait parti de la matrice du plateai
  //par exemple si le plateau fait 3x4 et que l'utilisateur saisi 5,2,
  // alors un message d'erreur sera affiché afin de saisir des valeurs correctes
  def is_inside(x:Int,y:Int):Boolean={
    var result=false
    if((x<height)&&y<width){
      result = true
    }
    result
  }
  // fonction qui verifie que la saisie utilisateur ne correspond pas à une case deja decouverte
  // par exemple si la cellule (1,2) affiche 2,
  // si l'utilisateur entre 1,2 alors un message d'erreur va s'afficher pour lui dire d'entrer une nouvelle valeur
  def already_played(x:Int,y:Int):Boolean={
    var result = false
    if (!grid(x)(y).contains(".")) {
      result = true
    }
    result
  }

  // méthode qui permet de verifier que la saisie utilisateur est correct
  // seule saisie correcte possible : longeur,largeur
  // exemple : 1,2
  def is_format_correct(x:String):Boolean={
    var result=false
    if(x.contains(",")){
      var tmp=x.split(",")
      if(tmp.length==2){
        if(tmp(0).forall(_.isDigit) && tmp(1).forall(_.isDigit)) {
          result = true
        }
      }
    }
    result
  }

  //Cette fonction permet de récupérer ce qu'un utilisateur saisit. Lorsque ce dernier saisie au clavier une valeur on la transforme en un tuple
  // (la ligne, la colonne)
  def get_new_coords():(Int,Int)={
    var coords = scala.io.StdIn.readLine("Saisir les coordonnées de la case à cliquer? ")

    while (!is_format_correct(coords)) {
      coords = scala.io.StdIn.readLine("Veillez choisir un format correct ligne,colonne \nExemple : 1,2" +
        "\nSaisir les coordonnées de la case à cliquer?")
    }

    var x= coords.split(",")
    //on verifie si le couple est une combinaison possible de la matrice
    while (!is_inside(x(0).toInt,x(1).toInt)) {
      coords = scala.io.StdIn.readLine("Vueillez choisir des coordonnées valide\nSaisir les coordonnées de la case à cliquer?")
      x= coords.split(",")
    }
    //on verifie si le couple est une combinaison deja joué
    while (already_played(x(0).toInt, x(1).toInt)) {
      coords = scala.io.StdIn.readLine("Ces coordonnées ont deja été choisi, vueillez en choisir d'autre\nSaisir les coordonnées de la case à cliquer?")
      x = coords.split(",")
    }
    val new_coords: (Int, Int) = (x(0).toInt, x(1).toInt)
    println("Les coordonnées choisies: " + new_coords)

    new_coords

  }
  //fonction qui permet de commencer une partie
  def play(choice:Int):Unit={
    println("Début de partie")
    nb_mine= Mine().get_nb_mine(choice)
    val width=get_dimension(choice)._1
    val length=get_dimension(choice)._2
    val initialGrid=init_board_game(width,length,nb_mine)._1
    val gridWithMines=init_board_game(width,length,nb_mine)._2 // solution finale
    reveledCell=(width*height)-nb_mine
    val mineSweeper = new MineSweeper(reveledCell)
    mineSweeper.displayGrid(init_game(gridWithMines))
    mineSweeper.displayGrid(initialGrid)
    grid=initialGrid
    grid=mineSweeper.interact(grid,gridWithMines,get_new_coords())
    while(!mineSweeper.is_fin_partie()){
      mineSweeper.displayGrid(grid)
      grid=mineSweeper.interact(grid,gridWithMines,get_new_coords())
    }
    mineSweeper.displayGrid(grid)
  }

  //Cette fonction permet d'initialiser la grille de solution en spécifiant le nombre de voisins qui sont des mines
  def init_game(grid:Array[Array[String]]):Array[Array[String]]={
    val cell= new Neighbor()
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

  //Cette fonction permet d'incrémenter la valeur des voisins aux coordonnées (i, j) si elle est différente de -1.
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