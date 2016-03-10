<!-- Comment construire votre framework -->
<!-- Build c'est comment construire le framework,
le faire fonctionner (avoir ces 3 dossiers à tels endroits, compiler ce machin, avoir python d'installé, ...)
 donc tout ce qui est nécessaire niveau techniuqe-->
# Build #


1. Déposer ses <b>sources</b> dans les <b>deux</b> répertoires :
    - <b>spoonProcess/toBeMutated/</b> <b>(**)</b>
    - <b>mutatedCode/src/main/java/</b>
2. Dans le dossier <b>mutatedCode/</b> :
    1. Ajouter ses classes de <b>tests</b> unitaires dans <b>mutatedCode/src/test/</b>
    2. Ajouter son fichier <b>POM.xml</b>
3. Ajouter les dépendences du pom.xml du projet à muter dans le pom.xml de spoonProcess

<br/>
<br/>
<b>(**)</b> le répertoire toBeMutated/ peut être remplacé par n'importe quel autre repertoire,
 tant que c'est un dossier contenant une copie originale des sources du projet à tester.
 Il faudra alors préciser le chemin de ce dossier. <br/>Mais, pour simplifier, nous conseillons d'utiliser ce dossier toBeMutated/

-
### Projet testé #
Le projet à tester devra obligatoirement être un projet <b>maven</b> (afin que l'on puisse utiliser $ mvn test), et devra contenir le plugin maven-surefire-plugin.

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.5</version>
    <configuration>
        <useSystemClassLoader>false</useSystemClassLoader>
          <forkCount>1</forkCount>
          <argLine>-Dgumtree.match.gt.minh=1</argLine>
    </configuration>
</plugin>
```

Lorsque vous aurez fini ces étapes, vous pourrez vous référer à [HowTo.md](../blob/master/HowTo.md)