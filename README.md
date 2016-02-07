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

<p>On propose un framework pour automatiser des testes par mutation au niveau du code orienté objet de la langage Java. De manière général, notre framework reçoit, en lignes de commands, le code qui va être testé et les mutations qui lui seront appliqués; comme sortie, il donne des pages HTML qui affichent les statistiques des testes réalisés: les mutations qui ont été tuées, des graphiques circulaires représentant le pourcentage, des graphiques sur la progression des itérations, s’il y a eu des erreurs ou échecs et etc.</p>

<p>Par rapport aux lignes commandes, la principal est <b> execute </b> qui démarre le framework et qui peut (doit) être suivie par les options: </p>

<p><b> -s <b> pour le code source qui va être testé. (obligatoire).</p>
<p><b> -m <b> pour l’ensemble de mutation qui seront appliquées (qui, par default, seront appliquées toues au même temps). Les mutations doivent être spécifiées par sont code (voir le ficher spécifications.md). </p>  

<p>La commende <b> -m <b> peut être répétées plusieurs fois. À chaque fois, l’ensemble correspondant sera appliqué séparément générant les respectives résultats.</p>   

