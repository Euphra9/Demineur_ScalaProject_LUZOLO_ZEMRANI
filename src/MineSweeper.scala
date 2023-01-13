
class MineSweeper(var reveledCell: Int) {
  var mine=Mine()
  var element=""
  var tmpList:List[(Int,Int)]=List() // utilisée pour stocker la liste des voisins d'une cellule

  def displayGrid(grid: Array[Array[String]]): Unit = {
    print("   ")
    for (i <- 0 until grid(0).length) {
      print(i + " ")
    }
    println()
    for (y <- 0 until grid.length) {
      if (y.toString.length == 1) {
        print(y + "  ")
      }
      else {
        print(y + " ")
      }
      for (x <- 0 until grid(0).length) {
        print(grid(y)(x) + " ")
      }
      println()
    }
  }

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
        println("ITER : "+couple._2.toString)
        if (couple._2.toString.length == 1) {
          tmpGrid(couple._1)(couple._2)=Resultgrid(couple._1)(couple._2)
        }
        else {
          tmpGrid(couple._1)(couple._2)=" "+Resultgrid(couple._1)(couple._2)
        }
    }
    println("Case restante à trouver : "+reveledCell)
    tmpGrid
  }


  //Si l'utilisateur interagit avec une case vide,
  // elle est révélée. Ses voisines le sont récursivement jusqu’à être
  //adjacentes à une mine.
  def is_nextTo_mine(couple: (Int, Int),
                     grid: Array[Array[String]],
                     Resultgrid: Array[Array[String]]): Array[Array[String]] = {
    val cell = new Neighbor()
    val liste = cell.get_neighbors((couple._1, couple._2), Resultgrid)
    var tmpGrid = grid


    if (Resultgrid(couple._1)(couple._2) == "0") {
      for (x <- liste) {
        if (!tmpList.contains(x)) {
          tmpList = tmpList :+ x
          tmpGrid = is_nextTo_mine(x, tmpGrid, Resultgrid)
        }
      }

    }
    tmpGrid(couple._1)(couple._2) = Resultgrid(couple._1)(couple._2)
    reveledCell -= 1

    tmpGrid

  }

  def is_fin_partie():Boolean={
    var result=false
    if(element=="-1"){
      println("Vous avez perdu ! ")
      result=true
    }
    else if(reveledCell==0){
      println("Vous avez gagné ! ")
      result = true
    }
    result
  }



}
