Description du framework 
==

La chaine de build 
-

Les mutations
-

Voici notre ensemble d’opérateurs de mutations. Ceux-ci visent surtout traiter des détails spécieux du code orienté objet qui peuvent souvent faire tromper le programmateur. Comme exemple là-dessus, on peut citer les détails de l’héritage, du polymorphisme, de la surcharges de méthodes, de controle d’accès, aussi bien des détails particuliers de la language Java. On propose aussi quelques opérateurs des mutations classiques.

### Op1 (héritage et controle d’accès): #
<p>Soit <i>A</i> une classe qui a une déclaration d’attribut <i>protected a </i>. Soit <i>B</i> une classe que hérite d’A et qui utilise l'attribut <i>a</i>. Cet opérateur propose de changer le controle d’accès d’<i>a</i> par <i>private</i>.</p>

### Op2 (héritage et redéfinition d’attribut): #
 <p>Soit <i>A</i> une classe qui a une déclaration d’attribut <i> a </i> quelconque. Soit <i>B</i> une classe que hérite d’A et qui redefine l’attribut <i>a</i> (c’est-à-dire, <i>B</i> a une définition d’un attribut qui a le même nom d‘<i>a</i> ). Cet opérateur propose d’enlever la définition d‘<i>a</i> en <i>B</i>.</p> 
### Op3 (héritage et surcharge de méthode): #
 <p> Soit <i>A</i> une classe qui a la déclaration d’une méthode <i> a </i>. Soit <i> B </i> une classe qui hérite d’<i> A </i> et dans laquelle la méthode <i> a </i> sera surchargée. Cet opérateur propose de enlever la surcharge de <i> a </i> en <i> B </i>. </p>

### Op4 (héritage et redéfinition de méthode): #
<p> Soit <i>A</i> une classe qui a la déclaration d’une méthode <i> a </i>. Soit <i> B </i> une classe qui hérite d’<i> A </i> et dans laquelle la méthode <i> a </i> sera redéfinie. Cet opérateur propose de changer le nom d’<i> a </i> par <i> aa </i> en <i> A </i>. </p>

### Op5 (héritage et le mot clé <i>super </i>): #
<p> Soient <i>A</i> une classe et <i>B</i> une autre classe qui hérite d’<i>A</i>. Supposez que <i>B</i> référence une variable d’<i>A</i> avec le mot clé <i>super</i>. Cet opérateur propose d’enlever juste le mot clé <i>super</i> en <i>B</i>. Cela permettre vérifier si les variables redéfinies en <i>B</i> sont bien utilisées. </p>

### Op6 (polymorphisme, type et instance): # 
<p> Soient <i>A </i> une classe et <i>B </i> une sous-classe d’<i>A</i>. Soupesez que une méthode quelconque a la ligne: <i>A a = new A()</i>. Cet opérateur propose de la changer par: <i>A a = new B()</i>. C’est-à-dire, si <i>a</i> est un objet du type de <i>A</i> avec une instance de <i>A</i>, maintenant il se devient un objet du type <i>A</i> et avec une instance de <i>B</i>. </p>

### Op7 (polymorphisme et les paramètres): #
<p>Soient <i>A </i> une classe et <i>B </i> une sous-classe d’<i>A</i>. Soupesez que une méthode quelconque a besoin d’un objet <i>A a</i> comme paramètre. Cet opérateur propose de changer ce paramètre par <i>B a</i>. </p>
### Op8 (Java et le mot clé  <i>this </i>): # 
</p> Soient <i>A </i> une classe. Les attributs d’une instance d’<i>A </i> peuvent être référencés dans une méthode d’<i>A </i> par leur nom. En revanche, il peut y avoir que tels attributs soient dans une méthode qui reçoit des paramètres avec les mêmes noms et types. Donc, ces attributs doivent être référencés avec le mot <i>this</i>. Cet opérateur de mutation propose d’enlever juste le mot clé  <i>this </i>. </p>

### Op9 (Java et le mot clé <i> static </i>): # 
<p> Soient <i>A </i> une classe qui a des variables statiques. De leurs déclarations, on va enlever le mot clé  <i> static </i>. Cet opérateur permettre d’évaluer le comportement du système par rapport les variables de classes et d'instance. </p>

### Op10 (Java et l’initialisation des variables d’instance): #
<p> Les variables d’instance peuvent être initialiser au moment de leur déclaration. Cet opérateur enlever l’initialisation et laisse juste la déclaration, en permettant vérifier si leur initialisation était correct. </p>

### Op11 (Java et la méthode <i> equals() </i>): #
<p> Soient deux objets, <i>a </i> et <i>b </i>, qui sont comparés par référence avec <i>== </i>. Cet opérateur substitue le <i>== </i> par la méthode <i> equals() </i>. Cele permettre savoir ne s’est pas tromper avec les concepts de comparaison par référence et contenu. </p>
