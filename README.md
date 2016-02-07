DOCT-MUT-11
==

Présentation de l'équipe
-
<ul><b> Groupe 11</b> :
<li>DA COSTA FERNANDES	Islame Felipe</li>
<li>DAHMOUL	Salah</li>
<li>MARIN Alicia</li>
</ul>

-

Présentation du projet
-

<p>Nous proposons un framework afin d'automatiser des testes par mutation dans le langage Java. Pour l'utiliser, il faudra passer par la ligne de commande en ajoutant comme parametre le code qui va être testé, une liste de mutations qui lui seront appliqués ainsi qu'une liste de selecteurs.
En sortie, le framework génère des pages HTML où seront affichés les statistiques des testes réalisés. C'est-à-dire les mutations appliquées, celles qui ont été tuées. Il y aura de plus un diagramme circulaire représentant le pourcentage de mutants tués, ainsi qu'un graphiques représentant les résultats en fonction de l'itérations.</p>

<p>Pour lancer le framework en ligne de commande, il faudra utiliser <b>$ mutationTest</b> et devra être suivie par les options: </p>

<p><b> -s </b> pour le code source qui va être testé. (obligatoire).</p>
<p><b> -m </b> pour l’ensemble de mutation qui seront appliquées. Les mutations doivent être spécifiées par sont code (cf spécifications.md). </p>  

En suite, il est possible de spécifier les sélecteurs avec les commandes:

<p><b> -t </b> pour appliquer toutes les mutations au programme.</p>
<p><b> -f </b> pour appliquer une seule mutation au programme à la fois</p>
<p><b> -c </b> pour appliquer des combinaisons spécifiques des mutations. Cette commande sera suivie par un sous-ensemble spécifié par <b> -m </b>, qui représentent une combinaison. La commande <b> -c </b> peut être répétée plusieurs fois, en symbolisant qui plusieurs combinaisons seront appliquées à la fois. </p>   
