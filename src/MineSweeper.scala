import scala.Console._

class MineSweeper(var reveledCell: Int) {
  var mine=Mine()
  var element=""
  var tmpList:List[(Int,Int)]=List() // utilisée pour stocker la liste des voisins d'une cellule
  def this() {
    this(0)
  }

  //Cette fonction sert à afficher le plateau
  def displayGrid(grid: Array[Array[String]]): Unit = {
    print("   ")
    for (i <- 0 until grid(0).length) {
      print(BLUE+i + " "+RESET)
    }
    println()
    for (y <- 0 until grid.length) {
      if (y.toString.length == 1) {
        print(BLUE+y + "  "+RESET)
      }
      else {
        print(BLUE+y + " "+RESET)
      }

      for (x <- 0 until grid(0).length) {
        print(grid(y)(x) + " ")
      }
      println()
    }
  }

  //Cette fonction sert à mettre à jour le jeu.
  def interact(grid: Array[Array[String]],Resultgrid: Array[Array[String]], couple: (Int,Int)):Array[Array[String]]={
    var tmpGrid=grid
    element=Resultgrid(couple._1)(couple._2)
      if(element=="-1") {
        tmpGrid = mine.show_all_mine(tmpGrid, Resultgrid)
      }
    else if(element=="0"){
      tmpGrid=is_nextTo_mine((couple._1,couple._2),tmpGrid,Resultgrid)
      reveledCell+=1
    }
    else {
      reveledCell-=1
        tmpGrid(couple._1)(couple._2)= Functions().mis_en_forme(couple._2,"",false)+Resultgrid(couple._1)(couple._2)

    }
    tmpGrid
  }


  //Si l'utilisateur interagit avec une case vide,
  // elle est révélée. Ses voisines le sont récursivement jusqu’à être
  //adjacentes à une mine.
  def is_nextTo_mine(couple: (Int, Int),
                     grid: Array[Array[String]],
                     Resultgrid: Array[Array[String]]): Array[Array[String]] = {
    val liste = Mine().get_neighbors((couple._1, couple._2), Resultgrid)
    var tmpGrid = grid
    if (Resultgrid(couple._1)(couple._2) == "0") {
      for (x <- liste) {
        if (!tmpList.contains(x)) {
          tmpList = tmpList :+ x
          tmpGrid = is_nextTo_mine(x, tmpGrid, Resultgrid)
        }
      }
    }
      tmpGrid(couple._1)(couple._2) = Functions().mis_en_forme(couple._2,"",true)+Resultgrid(couple._1)(couple._2)
    reveledCell -= 1

    tmpGrid

  }

  //Cette fonction permet d'informer l'utilisateur de sa perte ou de son gain
  def is_fin_partie():Boolean={
    var result=false
    //Si la cellule contient -1 alors l'utilisateur clique sur une bombre
    // => l'utilisateur a perdu
    if(element=="-1"){
      println(RED+"Vous avez perdu !!! "+RESET)
      result=true
    }
      // s(il ne reste que des bombes dans la matrice
      // => Lutilisateur a gagné
    else if(reveledCell==0){
      println(GREEN+"Vous avez gagné ! "+RESET)
      result = true
    }
    result
  }

}
