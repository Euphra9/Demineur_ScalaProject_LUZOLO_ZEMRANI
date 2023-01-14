# Programmation fonctionnelle (Scala) : Démineur
Réalisé par LUZOLO Euphraïm et ZEMRANI Malak
## Spécifications techniques
#### Object Main
#### Class Game
#### Class Mine
##### Récuperer le nombre de mine à générer. 
def get_nb_mine(Int) : Int
* Prend en paramètre le choix de la difficulté(facile-moyen-difficile)
* Renvoie le nombre de mine à generer en fonction de la difficulté
***
##### Placer une mine dans une cellule. 
def random_mine(Int,Int,Array[Array[String]]):Array[Array[String]]
* Prend en paramètre le nombre de mine à placer et la matrice à modifier
* Renvoie la matrice modifiée
***
##### Afficher toutes les cellules contenant une mine
def show_all_mine(Array[Array[String]],Array[Array[String]]):Array[Array[String]]
* Prend en paramètre la matrice visible par l'utilisateur et la matrice contenant la solution
* Renvoie la matrice visible par l'utilisateur avec toutes les mines placée
***
##### Récupérer la liste des voisins. 
def get_neighbors((Int,Int),Array[Array[String]]):List[(Int,Int)]
* Prend en paramètre le couple des coordonnées et la matrice 
* Renvoie la liste des coordonnées des voisins
***
##### Incrémenter le nombre des voisins qui sont des mines. 
def incr_tab(List(Int,Int),Array[Array[String]]):Int
* Prend en paramètre la liste des coordonnées des voisins et la matrice contenant la solution
* Renvoie le nombre de voisin qui contiennent des mines
#### Class MineSweeper
#### Class Functions




