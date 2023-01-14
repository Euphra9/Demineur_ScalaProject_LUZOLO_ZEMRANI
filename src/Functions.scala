case class Functions(){
  def entry_message()="Saisir les coordonnées de la case à cliquer?"

  def mis_en_forme(x: Int,chaine:String,col:Boolean): String = {
    var result = ""
    if (x.toString.length == 1) {
      if(col) {result = chaine
      }
      else {
        result = " "+chaine
      }
    }
    else {
      if(col) {result = " "+chaine}
      else{result = chaine}
    }

    result
  }

  // méthode qui permet de verifier que la saisie utilisateur est correct
  // seule saisie correcte possible : longeur,largeur
  // exemple : 1,2
  def is_format_correct(x: String): Boolean = {
    var result = false
    if (x.contains(",")) {
      val tmp = x.split(",")
      if (tmp.length == 2) {
        if (tmp(0).forall(_.isDigit) && tmp(1).forall(_.isDigit)) {
          result = true
        }
      }
    }
    result
  }

  // fonction qui verifie que la saisie utilisateur ne correspond pas à une case deja decouverte
  // par exemple si la cellule (1,2) affiche 2,
  // si l'utilisateur entre 1,2 alors un message d'erreur va s'afficher pour lui dire d'entrer une nouvelle valeur
  def already_played(x: Int, y: Int,grid: Array[Array[String]]): Boolean = {
    var result = false
    if (!grid(x)(y).contains(".")) {
      result = true
    }
    result
  }


  //fonction qui verifie que la saisie utilisateur fait parti de la matrice du plateai
  //par exemple si le plateau fait 3x4 et que l'utilisateur saisi 5,2,
  // alors un message d'erreur sera affiché afin de saisir des valeurs correctes
  def is_inside(x: Int, y: Int, size:(Int,Int)): Boolean = {
    var result = false
    if ((x < size._1) && y < size._2) {
      result = true
    }
    result
  }

}
