package org.mutation11.maven;

import java.io.*;
import java.util.List;

import org.junit.Assert;
import spoon.Launcher;
import spoon.reflect.declaration.CtClass;



public class Main {

    public static void main(String[] args) {
        // Copy ALL
//        String theFolder = args[0];
        String theFolder = "toBeMutated/";
        copyFile(theFolder, "../mutatedCode/src/main/java/minimal/");
//        copyFile("toBeMutated/", "../mutatedCode/src/main/java/minimal/");

        // Mutated
        File folder = new File(theFolder);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {

          /** Mutated all file.java of a folder **/
            if (listOfFiles[i].isFile()) {
                convertTheJava("toBeMutated/"+listOfFiles[i].getName());
            }
//            else if (listOfFiles[i].isDirectory()) {
//                System.out.println("Directory " + listOfFiles[i].getName());
//            }
        }
    }

    private static void convertTheJava(String nameFile) {
        BinaryOperatorMutator mutationOperator = new BinaryOperatorMutator();

        // we instantiate the mutation tester
        MutationTester<Racine> mutationTester = new MutationTester<Racine>(nameFile,  mutationOperator);

        // generating the mutants
        mutationTester.generateMutants();
        List<CtClass> mutants = mutationTester.getMutants();

        // killing the mutants, no exception should be thrown
        try {
            mutationTester.killMutants();
        } catch (MutantNotKilledException e) {
            System.out.println("mutant NOT KILLED");
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void copyFile(String source, String dest) {

        File srcFolder = new File(source);
        File destFolder = new File(dest);

        //make sure source exists
        if(!srcFolder.exists()){

            System.out.println("Directory does not exist.");
            //just exit
            System.exit(0);

        }else{

            try{
                copyFolder(srcFolder,destFolder);
            }catch(IOException e){
                e.printStackTrace();
                //error, just exit
                System.exit(0);
            }
        }

        System.out.println("Done");
    }

    public static void copyFolder(File src, File dest)
            throws IOException{

        if(src.isDirectory()){

            //if directory not exists, create it
            if(!dest.exists()){
                dest.mkdir();
                System.out.println("Directory copied from "
                        + src + "  to " + dest);
            }

            //list all the directory contents
            String files[] = src.list();

            for (String file : files) {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                copyFolder(srcFile,destFile);
            }

        }else{
            //if file, then copy it
            //Use bytes stream to support all file types
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes
            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
            System.out.println("File copied from " + src + " to " + dest);
        }
    }
}
