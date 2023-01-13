class Neighbor extends Game{
  // DOIT etre une méthode recursive

  //Cette fonction permet de récupérer la liste des voisins
  def get_neighbors(coords: (Int, Int), grid: Array[Array[String]]): List[(Int, Int)] = {

    var list_of_neighbors: List[(Int, Int)] = List()
    val i = coords._1
    val j = coords._2
    // couple des voisins
    val voisin_gauche = (i, j - 1)
    val voisin_droite = (i, j + 1)
    val voisin_haut = (i - 1, j)
    val voisin_bas = (i + 1, j)
    val voisin_diagonal_haut_gauche = (i - 1, j - 1)
    val voisin_diagonal_haut_droite = (i - 1, j + 1)
    val voisin_diagonal_bas_gauche = (i + 1, j - 1)
    val voisin_diagonal_bas_droite = (i + 1, j + 1)

    //----
    // j'ai 3 voisins
    if (on_the_corner((i, j), (grid.length, grid(0).length))) {

      if (i == 0) {
        list_of_neighbors = list_of_neighbors :+ voisin_bas
        if (j == 0) {
          list_of_neighbors = list_of_neighbors :+ voisin_droite
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_bas_droite
        }
        else {
          list_of_neighbors = list_of_neighbors :+ voisin_gauche
          list_of_neighbors = list_of_neighbors :+ voisin_diagonal_bas_gauche
        }
      }
      else {
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

    else if (on_the_verge((i, j), (grid.length, grid(1).length))) {
      if (i == 0) {
        list_of_neighbors = list_of_neighbors :+ voisin_gauche
        list_of_neighbors = list_of_neighbors :+ voisin_droite
        list_of_neighbors = list_of_neighbors :+ voisin_bas
        list_of_neighbors = list_of_neighbors :+ voisin_diagonal_bas_gauche
        list_of_neighbors = list_of_neighbors :+ voisin_diagonal_bas_droite
      }

      else if (j == 0) {
        list_of_neighbors = list_of_neighbors :+ voisin_haut
        list_of_neighbors = list_of_neighbors :+ voisin_droite
        list_of_neighbors = list_of_neighbors :+ voisin_bas
        list_of_neighbors = list_of_neighbors :+ voisin_diagonal_haut_droite
        list_of_neighbors = list_of_neighbors :+ voisin_diagonal_bas_droite
      }
      else if (i == grid.length - 1) {
        list_of_neighbors = list_of_neighbors :+ voisin_gauche
        list_of_neighbors = list_of_neighbors :+ voisin_droite
        list_of_neighbors = list_of_neighbors :+ voisin_haut
        list_of_neighbors = list_of_neighbors :+ voisin_diagonal_haut_gauche
        list_of_neighbors = list_of_neighbors :+ voisin_diagonal_haut_droite

      }
      else if (j == grid(0).length - 1) {
        list_of_neighbors = list_of_neighbors :+ voisin_haut
        list_of_neighbors = list_of_neighbors :+ voisin_gauche
        list_of_neighbors = list_of_neighbors :+ voisin_bas
        list_of_neighbors = list_of_neighbors :+ voisin_diagonal_haut_gauche
        list_of_neighbors = list_of_neighbors :+ voisin_diagonal_bas_gauche
      }
    }
    else {
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


  //vérifie si la cellule actuelle se trouve au bord
  def on_the_verge(coords: (Int, Int), size: (Int, Int)): Boolean = {
    var result = false
    if (!on_the_corner(coords, size)) {
      if ((coords._1 == 0)
        || (coords._2 == 0)
        || (coords._1 == size._1 - 1) || (coords._2 == size._2 - 1)) {
        result = true
      }
    }
    result
  }

  ////vérifie si la cellule actuelle se trouve au coin
  def on_the_corner(coords: (Int, Int), size: (Int, Int)): Boolean = {
    var result = false
    if (coords._1 == 0) {
      if (coords._2 == 0 || coords._2 == size._2 - 1) {
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
}
