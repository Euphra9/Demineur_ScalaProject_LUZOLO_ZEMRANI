import scala.util.Random

object Main {


  def main(args: Array[String]): Unit = {
    println("--------------- DEMINEUR ---------------")
    println("Niveau Facile\t\t :\t 1\nNiveau Moyen\t\t :\t 2 \nNiveau Difficile\t :\t 3")
    var choice = scala.io.StdIn.readLine("Quel niveau souhaité vous jouer? ").toInt
    while (choice != 1 && choice != 2 && choice != 3) {
      choice = scala.io.StdIn.readLine("Veillez choisir entre 1, 2 et 3\nQuel niveau souhaité vous jouer? ").toInt
    }

    if (choice == 1) {
      println("Niveau choisi : Facile !")
    } else if (choice == 2) {
      println("Niveau choisi : Moyen !")
    } else if (choice == 3) {
      println("Niveau choisi : Difficile !")
    }

    val new_game= new Partie()
    new_game.play(choice)




  }
}