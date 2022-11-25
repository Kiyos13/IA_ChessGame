# EXECUTION

Pour obtenir l'exécutable (.jar) du joueur suivre ces procédures :

```make jar```

Copier-coller ces instructions dans le fichier **MANIFEST.MF**

```
Manifest-Version: 1.0
Main-Class: Game
Created-By: 18.0.2-ea (Private Build)
```
# Le JOUEUR

## Stratégie mise en place

La stratégie suivante a été implémentée :
* Selection de 3 coups aléatoires parmi les coups possibles pour ouvrir la partie (variable `nbMovesRandomInit` modifiable), génération des coups aléatoire possile pour le joueur grâce à la méthode `chooseRandomMove(board, verifyCheck)`
* Algorithme alpha-beta pour la sélection des coups lors de la partie avec une profondeur de 3 (variable `depth` modifiable) :
    * Lorsqu'il ne reste plus que 5 pièces en tout sur le plateau de jeu, la profondeur passe automatiquement à 5
    * Si un coup mène à un echec et mat, ce dernier est choisi par l'algorithme

* Algorithme miniMax pour la sélection des coups lors de la partie avec une profondeur de 3 (variable `depth` modifiable) [Algorithme non utilisée, car moins efficace que alpha-beta]

* Un blocage de boucle a été mis en place pour éviter de reproduire 2 schémas : 
    * Mouvement a -> b puis Mouvement b -> a (on évite ce 2ème mouvement qui n'est que répétition)
    * Mouvement a -> b, Mouvement b -> a, Mouvement a -> c, Mouvement c -> a, Mouvement a -> b puis Mouvement b -> a (on évite ce 6ème mouvement qui n'est que répétition)  

## Autres fonctionnalités pratiques mises en place

Autres fonctionnalités mises en place :
* `isKingCheck(color)` : méthode de la **classe Board** permettant de savoir si le roi de couleur **color** est en échec
* `isKingCheckMate(color)` : méthode de la **classe Board** permettant de savoir si le roi de couleur **color** est en échec et mat
* `generateLegalMoves(board, verifyCheck)` : méthode de la **classe abstraite Piece** (donc implémentée pour chaque pièce héritant de cette classe) qui renvoie l'ensemble des coups possibles pour une pièce. Le **booléen verifyCheck** permet de savoir si les coups renvoyés doivent tenir compte de la mise en échec de son propre roi si le coup est réalisé (utile lorsque nous n'avons plus de coup possible ne menant pas à un échec inévitable)

# Auteurs
Quentin PAUWELS & Mathilde POMMIER
