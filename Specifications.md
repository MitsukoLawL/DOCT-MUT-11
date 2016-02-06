Description du framework 
==

La chaine de build 
-

Les mutations
-

Voici notre ensemble d’opérateurs de mutations. Ceux-ci visent surtout traiter des détails spécieux du code orienté objet qui peuvent souvent faire tromper le programmateur. Comme exemple là-dessus, on peut citer les détails de l’héritage, du polymorphisme, de la surcharges de méthodes, de controle d’accès, aussi bien des détails particuliers de la language java. On propose aussi quelques opérateurs de mutations classiques.

### Op1 (héritage et controle d’accès): #
<p>Soit <i>A</i> une classe qui a une déclaration d’attribut <i>protected a </i>. Soit <i>B</i> une classe que hérite d’A et qui utilise l'attribut <i>a</i>. Cet opérateur propose de changer le controle d’accès d’<i>a</i> par <i>private</i>.</p>

### Op2 (héritage et redéfinition d’attribut): #
 <p>Soit <i>A</i> une classe qui a une déclaration d’attribut <i> a </i> quelconque. Soit <i>B</i> une classe que hérite d’A et qui redefine l’attribut <i>a</i> (c’est-à-dire, <i>B</i> a une définition d’un attribut qui a le même nom d‘<i>a</i> ). Cet opérateur propose d’enlever la définition d‘<i>a</i> en <i>B</i>.</p> 
### Op3 (héritage et surcharge de méthode): #
 <p> Soit <i>A</i> une classe qui a la déclaration d’une méthode <i> a </i>. Soit <i> B </i> une classe qui hérite d’<i> A </i> et dans laquelle la méthode <i> a </i> sera surchargée. Cet opérateur propose de enlever la surcharge de <i> a </i> en <i> B </i>. </p>

### Op4 (héritage et redéfinition de méthode): #
<p> Soit <i>A</i> une classe qui a la déclaration d’une méthode <i> a </i>. Soit <i> B </i> une classe qui hérite d’<i> A </i> et dans laquelle la méthode <i> a </i> sera redéfinie. Cet opérateur propose de changer le nom d’<i> a </i> par <i> aa </i> en <i> A </i>. </p>

### Op5 (héritage et le mot clé <i>super </i>). #
<p> Soient <i>A</i> une classe et <i>B</i> une autre classe qui hérite d’<i>A</i>. Supposez que <i>B</i> référence une variable d’<i>A</i> avec le mot clé <i>super</i>. Cet opérateur propose d’enlever juste le mot clé <i>super</i> en <i>B</i>. Cela permettre vérifier si les variables redéfinies en <i>B</i> sont bien utilisées. </p>

### Op6 (polymorphisme, type et instance). # 
</p> Soient <i>A </i> une classe et <i>B </i> une sous-classe d’<i>A</i>. Soupesez que une méthode quelconque a la ligne: <i>A a = new A()</i>. Cet opérateur propose de la changer par: <i>A a = new B()</i>. C’est-à-dire, si <i>a</i> est un objet du type de <i>A</i> avec une instance de <i>A</i>, maintenant il se devient un objet du type <i>A</i> et avec une instance de <i>B</i>. </p>

### Op7 #
### Op8 #
### Op9 #
### Op10 #
### Op11 #
### Op12 #
### Op13 #
### Op14 #
### Op15 #