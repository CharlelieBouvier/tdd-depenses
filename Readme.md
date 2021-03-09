
KATA 1 

Tests :

On test qu'une liste d'enregistrement vide nous renvoie bien rien.

On ajoute une 1�re d�pense de la cat�gorie 1 et on v�rifie que �a nous renvoi bien le montant de cette d�pense pour cette cat�gorie.
On ajoute une 2�me d�pense de la cat�gorie 1 et on v�rifie que �a nous renvoi la somme des d�pense de cette cat�gorie.
On ajoute une 3�me d�pense de la cat�gorie 1 avec un montant n�gatif qui annule les 2 premi�res d�penses et on v�rifie que �a nous renvoi 0.
On ajoute une 4�me d�pense de la cat�gorie 1 avec un montant n�gatif qui et on v�rifie que le montant remont� est bien n�gatif.

On ajoute des d�penses de cat�gorie 2 et on v�rifie que les sommes des 2 cat�gories de d�penses sont bien remont�s.

On teste le r�sultat pour un mois donn�s.
On teste le r�sultat pour un mois et une cat�gories donn�s.




Sujet : 

�crivez un programme qui �tant donn�e une liste d'enregistrements :
(Date de D�pense, Cat�gorie, Montant)
produit une liste d'enregistrements :
(Cat�gorie, Montant)
repr�sentant le total des d�penses par cat�gorie.
(Les montants peuvent �tre n�gatifs).


Produire le total des d�penses par cat�gorie.
La liste en sortie doit �tre tri�e par cat�gorie.
S'il n'y a aucune d�pense pour une cat�gorie, cette cat�gorie ne doit pas �tre pr�sente dans la liste en sortie.
Si les montants s'annulent pour une cat�gorie, cette cat�gorie doit appara�tre avec un montant nul.
Produire le total des d�penses par cat�gorie et par mois.
Produire le total des d�penses pour une cat�gorie et un mois donn�.
Produire le total des d�penses pour toutes les cat�gories pour un mois donn�.


KATA 2

- Charger un csv vide, renvoie une liste vide 
- Charger un csv a une ligne, renvoie une liste non vide
- Charger un csv dont les categories contiennent des espaces.
- Charger un csv dont certains montants sont negatifs
- Charger un csv dont certains montants sont en centime