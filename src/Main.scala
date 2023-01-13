
object Main {

  def main(args: Array[String]): Unit = {
    println("--------------- DEMINEUR ---------------")

    println("Niveau Facile\t\t :\t 1\nNiveau Moyen\t\t :\t 2 \nNiveau Difficile\t :\t 3")
    var choice = scala.io.StdIn.readLine("Quel niveau souhaité vous jouer? ")
    while (!choice.forall(_.isDigit) || choice.toInt != 1 && choice.toInt != 2 && choice.toInt != 3) {
      choice = scala.io.StdIn.readLine("Vueillez choisir entre 1, 2 et 3\nQuel niveau souhaité vous jouer? ")
    }

    choice.toInt match {
      case 1 => println("Niveau choisi : Facile !")
      case 2 => println("Niveau choisi : Moyen !")
      case 3 => println("Niveau choisi : Difficile !")
    }

    val new_game= new Game()
    new_game.play(choice.toInt)
    println("--------------- FIN DE PARTIE ---------------")





  }
}