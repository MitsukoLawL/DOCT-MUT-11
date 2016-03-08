<!-- Comment construire votre framework -->
<!-- Build c'est comment construire le framework,
le faire fonctionner (avoir ces 3 dossiers à tels endroits, compiler ce machin, avoir python d'installé, ...)
 donc tout ce qui est nécessaire niveau techniuqe-->
# Build #

1. Déposer ses sources dans <b>spoonProcess/toBeMutated/</b>
2. Déposer ses sources dans <b>mutatedCode/src</b>
3. Ajouter son projet en entier dans <b>mutatedCode/</b>
    1. Ajouter ses tests dans <b>mutatedCode/test/</b>
    2. Ajouter son fichier <b>POM.xml</b>
    3. Ajouter les fichiers complémentaires


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