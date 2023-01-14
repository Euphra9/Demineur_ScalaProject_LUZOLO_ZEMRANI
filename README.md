# Programmation fonctionnelle (Scala) : Démineur
Réalisé par LUZOLO Euphraïm et ZEMRANI Malak
## Spécifications techniques
#### Class Game
##### Lancer une nouvelle partie. 
def play(Int):Unit
* Prend en paramètre le choix de la difficulté(facile-moyen-difficile)
##### Récuprer les dimensions du plateau. 
def get_dimension(Int):(Int,Int)
* Prend en paramètre le choix de la difficulté(facile-moyen-difficile)
* Renvoie les dimensions du plateau (longueyr,largeur) en fonction du choix
##### Initialiser le plateau de jeu. 
def init_board_game():(Array[Array[String]],Array[Array[String]])
* Renvoie la matrice visible par l'utilisateur et la matrice contenant la solution(avec toutes les cellules initialisées à 0)
#####
def init_game(Array[Array[String]]):Array[Array[String]]
* Prend en paramètre la matrice contenant la solution
* Renvoie la matrice avec les nombres des voisons etant des mines dans chaque cellule
##### Récupérer les coordonnées saisies par l'utilisateur. 
def get_new_coords():(Int,Int)
* Renvoie les coordonnées saisies

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




