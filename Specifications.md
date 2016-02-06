Description du framework
==

La chaine de build
-

Les mutations
-

Vous trouverez ci-dessous l'ensemble des opérateurs de mutations.  Les mutations vont modifier différents aspect de notre code, dans le but de mesurer la fragilité de nos tests.
Comme exemple, nous pouvons citer les aspects d’héritage, du polymorphisme, de la surcharge de méthodes, de controle d’accès, ainsi que les termes particuliers du language Java. Nous proposons aussi quelques opérateurs de mutations classiques.

### Op1 (héritage et controle d’accès): #
<p>Soit <i>A</i> une classe qui a une déclaration d’attribut <i>protected a </i>. Soit <i>B</i> une classe que hérite d’A et qui utilise l'attribut <i>a</i>. Cet opérateur propose de changer le contrôle d’accès de<i>a</i> par <i>private</i>.</p>

### Op2 (héritage et redéfinition d’attribut): #
 <p>Soit <i>A</i> une classe qui a une déclaration d’attribut <i> a </i> quelconque. Soit <i>B</i> une classe qui hérite de A et qui redéfini l’attribut <i>a</i>. Cet opérateur propose d’enlever la définition de <i>a</i> en <i>B</i>.</p>
### Op3 (héritage et surcharge de méthode): #
 <p> Soit <i>A</i> une classe qui a la déclaration d’une méthode <i> a </i>. Soit <i> B </i> une classe qui hérite de <i> A </i> et dans laquelle la méthode <i> a </i> sera surchargée. Cet opérateur propose d'enlever la surcharge de <i> a </i> en <i> B </i>. </p>

### Op4 (héritage et redéfinition de méthode): #
<p> Soit <i>A</i> une classe qui a la déclaration d’une méthode <i> a </i>. Soit <i> B </i> une classe qui hérite de <i> A </i> et dans laquelle la méthode <i> a </i> sera redéfinie. Cet opérateur propose de changer le nom d’<i> a </i> par <i> aa </i> en <i> A </i>. </p>

### Op5 (héritage et le mot clé <i>super </i>): #
<p> Soient <i>A</i> une classe, et <i>B</i> une autre classe qui hérite de <i>A</i>. Supposons que <i>B</i> référence une variable de <i>A</i> avec le mot clé <i>super</i>. Cet opérateur propose d’enlever ce mot clé <i>super</i> en <i>B</i>. Cela permettra de vérifier que les variables re-définies en <i>B</i> sont bien utilisées. </p>

### Op6 (polymorphisme, type et instance): #
<p> Soient <i>A </i> une classe et <i>B </i> une sous-classe d’<i>A</i>. Supposons qu'une méthode quelconque ait la ligne: <i>A a = new A()</i>. Cet opérateur propose de la changer par: <i>A a = new B()</i>. C’est-à-dire, si <i>a</i> est un objet de type <i>A</i> d'instance <i>A</i>, il sera alors un objet de type <i>A</i> et d'instance <i>B</i>. </p>

### Op7 (polymorphisme et les paramètres): #
<p>Soient <i>A </i> une classe et <i>B </i> une sous-classe de <i>A</i>. Supposons qu'une méthode quelconque ait besoin d’un objet <i>A a</i> comme paramètre. Cet opérateur propose de changer ce paramètre par <i>B a</i>. </p>
### Op8 (Java et le mot clé  <i>this </i>): #
<p> Soit <i>this</i> faisant référence à l'objet courant.
Cet opérateur de mutation propose d’enlever le mot clé  <i>this </i>. </p>

### Op9 (Java et le mot clé <i> static </i>): #
<p> Soit <i>A </i> une classe comportant des variables statiques. Cet opérateur de mutation propose d’enlever le mot clé  <i> static </i>. Cela permettra d’évaluer le comportement du système par rapport aux variables de classes et d'instance. </p>

### Op10 (Java et l’initialisation des variables d’instance): #
<p> Les variables d’instance peuvent être initialiser au moment de leur déclaration. Cet opérateur enleve l’initialisation mais laisse la déclaration. Cela permettra de vérifier que leur initialisation était correct. </p>

### Op11 (Java et la méthode <i> equals() </i>): #
<p> Soient deux objets, <i>a </i> et <i>b </i>, qui sont comparés par référence avec <i>== </i>. Cet opérateur substitue le <i>== </i> par la méthode <i> equals() </i>. Cela permettra de vérifier que les concepts de comparaison par référence et contenu. </p>

### Op12 (operateurs et comparaisons): #
<p> Nous pouvons aussi changer les opérateurs tels que : <br/>
"+" soit remplacé par "-"<br/>
"*" soit remplacé par "/"<br/>
"!=" soit remplacé par "=="<br/>
">" soit remplacé par "<"<br/>
</p>

### Op13 (les boucles): #
<p> Nous pouvons remplacer "++" par "--" dans une boucle for, ou augmenter le pas.
</p>

-

Les selecteurs
-
Il existe plusieurs manière d'appliquer des mutations au programme.

-

Les outils utilisés
-

<ul>
<li>Maven</li>
<li>JUnit</li>
<li>Spoon</li>
<li>XML files</li>
<li>HTML files</li>
</ul>
