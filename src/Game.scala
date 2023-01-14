import scala.Console._

class Game {
  //variables globales
  var width=0
  var height = 0
  var reveledCell=0
  var nb_mine=0
  var grid:Array[Array[String]]=Array()

  //fonction qui permet de commencer une partie
  def play(choice: Int): Unit = {
    println("--- Début de partie --- ")
    nb_mine = Mine().get_nb_mine(choice) // on recupere le nombre de mine qu'il y aura dans la partie
    width = get_dimension(choice)._1 // on recupere la largeur du plateau de jeu
    height = get_dimension(choice)._2 // on recupère la longueur du plateau de jeu
    grid = init_board_game()._1 //on recupere la grille visible par l'utilisateur
    var gridWithMines = init_board_game()._2 // on recupere la grille de la solution finale
    reveledCell = (width * height) - nb_mine //on recupère le nombre de cellule que le joueur doit reveller pour gagner la partie
    val mineSweeper = new MineSweeper(reveledCell) // on cree un objet de type MineSweeper qui gère le plateau
    mineSweeper.displayGrid(init_game(gridWithMines))
    gridWithMines=init_game(gridWithMines)//on initialise la grille de solution
    mineSweeper.displayGrid(grid) //on affiche le plateau
    grid = mineSweeper.interact(grid, gridWithMines, get_new_coords()) // on met à jour le plateau
    while (!mineSweeper.is_fin_partie()) {
      mineSweeper.displayGrid(grid)
      grid = mineSweeper.interact(grid, gridWithMines, get_new_coords())
    }
    mineSweeper.displayGrid(grid)
  }


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
  def init_board_game(): (Array[Array[String]], Array[Array[String]]) = {
    var gridWithSolution = Array.fill(height, width)("0") // Dans la matrice contenant la solution, on place des 0 partout
    val grid=Array.ofDim[String](height,width)//Dans le plateau visible par l'utilisateur, on place des . partout dans la plateau
    //on fait une mise en forme agréable pour l'utilisateur
    for (i <- 0 until width) {
      for (j <- 0 until height) {
        grid(j)(i) =Functions().mis_en_forme(i,".",col = true)
      }
    }
    //on genere une mine dans une case aléatoire
    for (_ <- 0 until nb_mine) {
      gridWithSolution = Mine().random_mine(width,height,gridWithSolution)

    }
    //on renvoie la grille visible et la matrice contenant la solution
    (grid, gridWithSolution)
  }

  //Cette fonction permet d'initialiser la grille de solution en spécifiant le nombre de voisins qui sont des mines
  def init_game(grid: Array[Array[String]]): Array[Array[String]] = {
    val finalGrid = grid
    val height = finalGrid.length
    val width = finalGrid(0).length
    for (i <- 0 until height) {
      for (j <- 0 until width) {
        if (!finalGrid(i)(j).equals("-1")) {
          finalGrid(i)(j) = Mine().incr_tab(Mine().get_neighbors((i, j), finalGrid), finalGrid).toString
        }
      }
    }
    finalGrid

  }

  //Cette fonction permet de récupérer ce qu'un utilisateur saisit. Lorsque ce dernier saisie au clavier une valeur on la transforme en un tuple
  // (la ligne, la colonne)
  def get_new_coords():(Int,Int)={
    var coords = scala.io.StdIn.readLine(Functions().entry_message())

    while (!Functions().is_format_correct(coords)) {
      coords = scala.io.StdIn.readLine(RED+"Veillez choisir un format correct ligne,colonne \nExemple : 1,2" +RESET+
        "\n"+Functions().entry_message)
    }

    var x= coords.split(",")
    //on verifie si le couple est une combinaison possible de la matrice
    while (!Functions().is_inside(x(0).toInt,x(1).toInt,(height,width))) {
      coords = scala.io.StdIn.readLine(RED+"Vueillez choisir des coordonnées valide\n"+RESET+Functions().entry_message)
      x= coords.split(",")
    }
    //on verifie si le couple est une combinaison deja joué
    while (Functions().already_played(x(0).toInt, x(1).toInt,grid)) {
      coords = scala.io.StdIn.readLine(RED+"Ces coordonnées ont deja été choisi, vueillez en choisir d'autre\n"+RESET+Functions().entry_message)
      x = coords.split(",")
    }
    //on transforme la saisie utilisateur en coordonnées
    val new_coords: (Int, Int) = (x(0).toInt, x(1).toInt)
    println("Les coordonnées choisies: " + new_coords)

    new_coords

  }

}