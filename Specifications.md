Description du framework 
==

La chaine de build 
-

Les mutations
-

Vous trouverez ci-dessous l'ensemble des opérateurs de mutations.  Les mutations vont modifier différents aspect de notre code, ayant pour but de mesurer la fragilité de nos tests.
Comme exemple, nous pouvons citer les aspects d’héritage, du polymorphisme, de la surcharge de méthodes, de controle d’accès, ainsi que les termes particuliers du language Java. Nous proposons aussi quelques opérateurs des mutations classiques.

### Op1 (héritage et controle d’accès): #
<p>Soit <i>A</i> une classe qui a une déclaration d’attribut <i>protected a </i>. Soit <i>B</i> une classe que hérite d’A et qui utilise l'attribut <i>a</i>. Cet opérateur propose de changer le controle d’accès d’<i>a</i> par <i>private</i>.</p>

### Op2 (héritage et redéfinition d’attribut): #
 <p>Soit <i>A</i> une classe qui a une déclaration d’attribut <i> a </i> quelconque. Soit <i>B</i> une classe qui hérite de A et qui redéfini l’attribut <i>a</i>. Cet opérateur propose d’enlever la définition de <i>a</i> en <i>B</i>.</p> 
### Op3 (héritage et surcharge de méthode): #
 <p> Soit <i>A</i> une classe qui a la déclaration d’une méthode <i> a </i>. Soit <i> B </i> une classe qui hérite de <i> A </i> et dans laquelle la méthode <i> a </i> sera surchargée. Cet opérateur propose d'enlever la surcharge de <i> a </i> en <i> B </i>. </p>

### Op4 (héritage et redéfinition de méthode): #
<p> Soit <i>A</i> une classe qui a la déclaration d’une méthode <i> a </i>. Soit <i> B </i> une classe qui hérite de <i> A </i> et dans laquelle la méthode <i> a </i> sera redéfinie. Cet opérateur propose de changer le nom d’<i> a </i> par <i> aa </i> en <i> A </i>. </p>

### Op5 (héritage et le mot clé <i>super </i>): #
<p> Soient <i>A</i> une classe et <i>B</i> une autre classe qui hérite d’<i>A</i>. Supposez que <i>B</i> référence une variable de <i>A</i> avec le mot clé <i>super</i>. Cet opérateur propose d’enlever juste le mot clé <i>super</i> en <i>B</i>. Cela permettra de vérifier si les variables redéfinies en <i>B</i> sont bien utilisées. </p>

### Op6 (polymorphisme, type et instance): # 
<p> Soient <i>A </i> une classe et <i>B </i> une sous-classe d’<i>A</i>. Supposons qu'une méthode quelconque ait la ligne: <i>A a = new A()</i>. Cet opérateur propose de la changer par: <i>A a = new B()</i>. C’est-à-dire, si <i>a</i> est un objet du type de <i>A</i> avec une instance de <i>A</i>, maintenant il devient un objet du type <i>A</i> et d'une instance de <i>B</i>. </p>

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
