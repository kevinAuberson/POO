Auteurs : Kevin Auberson, Leonard Klasen
## Classe `Matrix`
La classe `Matrix` représente une matrice d'entiers avec des opérations de base.
### Attributs
- `matrix`: Un tableau 2D pour stocker les éléments de la matrice.
- `columns`: Le nombre de colonnes dans la matrice.
- `rows`: Le nombre de lignes dans la matrice.
- `modulus`: Le modulo à utiliser pour les valeurs dans la matrice.
### Constructeur
Le constructeur `Matrix` permet de créer une instance de la classe en initialisant les attributs privés de la matrice. Il effectue une validation des paramètres d'entrée puis génère la matrice en conséquence. 
- `rows` : Le nombre de lignes de la matrice.
- `columns` : Le nombre de colonnes de la matrice.
- `modulus` : Le modulo à utiliser pour les valeurs dans la matrice.
- `data` : Les données pour la matrice (ou `null` pour une génération aléatoire).
### Méthodes
- `getModulus()`: Cette méthode retourne le modulo de la matrice.
- `toString()`: Cette méthode convertit la matrice en un String.
- `execOperation(Matrix rhs, Operation op)`: Cette méthode effectue des opérations matricielles avec une autre matrice (`rhs`) en utilisant un objet de type `Operation`. Elle vérifie la compatibilité des modulos, effectue l'opération tout en appliquant le modulo et renvoie le résultat sous forme d'un nouvel objet `Matrix`.
## Classe `Operation`
La classe `Operation` est une classe abstraite qui représente une opération mathématique de base.
### Attributs
- `symbol`:  représentant l'opération.
### Méthodes
- `exec(int a, int b)`: Cette méthode est abstraite et doit être implémentée par les sous-classes. Elle exécute l'opération sur deux entiers (`a` et `b`) et renvoie le résultat.
- `toString()`: Cette méthode retourne le symbole de l'opération.

Le code comprend également des classes d'opération concrètes telles que `Addition`, `Substraction`, et `Multiplication`. Chaque classe d'opération étend la classe abstraite `Operation` et implémente la méthode `exec(int a, int b)` pour effectuer l'opération spécifique.

### Classe `Addition`
- Effectue une addition d'entiers.
- Le constructeur définit le symbole d'addition "+".

### Classe `Substraction`
- Effectue une soustraction d'entiers.
- Le constructeur définit le symbole de soustraction "-".

### Classe `Multiplication`
- Effectue une multiplication d'entiers.
- Le constructeur définit le symbole de multiplication "\*".

### Classe `MatrixTest`
La classe MatrixTest nous permet de réaliser tous les tests nécessaires pour vérifier le bon fonctionnement de notre
code. Dans cette classe nous testons :
- si l'addition se fait correctement avec : testMatrixAddition
- si la soustraction se fait correctement avec : testMatrixSubstraction
- si la multiplication se fait correctement avec : testMatrixMultiplication
- si l'on retourne une exception lorsque les 2 matrices n'ont pas le même module : notSameModulus
- si la création aléatoire d'une matrice fonctionne : testRandomizeMatrixCreation
- si le constructeur fonctionne : testMatrixConstructor
- si les opérations avec des matrices de différentes tailles fonctionne : testDifferentSizedMatrix

