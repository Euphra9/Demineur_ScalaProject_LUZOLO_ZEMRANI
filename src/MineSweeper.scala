import scala.collection.convert.ImplicitConversions.`mutableSeq AsJavaList`

class MineSweeper {
  var element=""
  var tmpList:List[(Int,Int)]=List()
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
    print("Etat de la partie\n")
    var tmpGrid=grid
    element=Resultgrid(couple._1)(couple._2)
    if(element=="-1"){
      tmpGrid=show_all_mine(tmpGrid,Resultgrid)
      displayGrid(tmpGrid)
    }else if(element=="0"){
      tmpGrid=is_nextTo_mine((couple._1,couple._2),tmpGrid,Resultgrid)

    }
    else {
      tmpGrid(couple._1)(couple._2)=Resultgrid(couple._1)(couple._2)
    }
    //displayGrid(tmpGrid)
    tmpGrid
  }
  def show_all_mine(grid: Array[Array[String]],Resultgrid: Array[Array[String]]):Array[Array[String]]={
    for (i <- 0 until Resultgrid.length) {
      for (j <- 0 until Resultgrid(0).length) {
        if(Resultgrid(i)(j)=="-1"){
          grid(i)(j)="*"
        }

      }
    }
    grid
    }

  def is_nextTo_mine(couple:(Int,Int),
                     grid:Array[Array[String]],
                     Resultgrid: Array[Array[String]]):Array[Array[String]]={
    val partie = new Partie()
    val liste=partie.get_neighbors((couple._1, couple._2), Resultgrid)
    var tmpGrid=grid


    if(Resultgrid(couple._1)( couple._2)=="0"){
      for (x <- liste) {
        if(!tmpList.contains(x)) {
          tmpList = tmpList :+ x
          tmpGrid = is_nextTo_mine(x, tmpGrid, Resultgrid)
        }
      }

      }
    tmpGrid(couple._1)(couple._2)=Resultgrid(couple._1)(couple._2)

    tmpGrid

  }
  def is_fin_partie():Boolean={
    var result=false
    if(element=="-1"){
      println("Vous avez perdu ! ")
      result=true
    }
    result
  }



}
