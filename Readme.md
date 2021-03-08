Tests :

On test qu'une liste d'enregistrement vide nous renvoie bien rien.

On ajoute une 1ère dépense de la catégorie 1 et on vérifie que ça nous renvoi bien le montant de cette dépense pour cette catégorie.
On ajoute une 2ème dépense de la catégorie 1 et on vérifie que ça nous renvoi la somme des dépense de cette catégorie.
On ajoute une 3ème dépense de la catégorie 1 avec un montant négatif qui annule les 2 premières dépenses et on vérifie que ça nous renvoi 0.
On ajoute une 4ème dépense de la catégorie 1 avec un montant négatif qui et on vérifie que le montant remonté est bien négatif.

On ajoute des dépenses de catégorie 2 et on vérifie que les sommes des 2 catégories de dépenses sont bien remontés.

On teste le résultat pour un mois donnés.
On teste le résultat pour un mois et une catégories donnés.




Sujet : 

Écrivez un programme qui étant donnée une liste d'enregistrements :
(Date de Dépense, Catégorie, Montant)
produit une liste d'enregistrements :
(Catégorie, Montant)
représentant le total des dépenses par catégorie.
(Les montants peuvent être négatifs).


Produire le total des dépenses par catégorie.
La liste en sortie doit être triée par catégorie.
S'il n'y a aucune dépense pour une catégorie, cette catégorie ne doit pas être présente dans la liste en sortie.
Si les montants s'annulent pour une catégorie, cette catégorie doit apparaître avec un montant nul.
Produire le total des dépenses par catégorie et par mois.
Produire le total des dépenses pour une catégorie et un mois donné.
Produire le total des dépenses pour toutes les catégories pour un mois donné.

