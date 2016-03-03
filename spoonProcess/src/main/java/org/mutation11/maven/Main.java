package org.mutation11.maven;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;


public class Main {

    public static void main(String[] args) {
        // Copy ALL
        String theFolder = args[0];
//        String theFolder = "toBeMutated/";
        copyFile(theFolder, "../mutatedCode/src/main/java/minimal/");

        // Mutated
          /** Mutated all file.java of a folder **/
//        convertTheJava(args[0], Integer.parseInt(args[1]));
        convertTheJava(args[0], convertParam(args[1]), Integer.parseInt(args[2]));
    }

    private static void convertTheJava(String nameFile, int indexMutator, int percentSelecteur) {
        // we instantiate the mutation tester
        MutationTester<Racine> mutationTester = new MutationTester<Racine>(nameFile,  listMutator().get(indexMutator));

        // generating the mutants
        mutationTester.generateMutants(percentSelecteur);
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

    /**
     * We add all mutator in a list
     * One of these mutator is called in {convertTheJava}
     * @return
     */
    private static ArrayList<AbstractProcessor<CtElement>> listMutator() {
        ArrayList<AbstractProcessor<CtElement>> list = new ArrayList<AbstractProcessor<CtElement>>();
        list.add(new Op1());
        list.add(new Op12());
        list.add(new Op13());
        list.add(new Op16());

        return list;
    }

    private static int convertParam(String str) {
        HashMap<String, Integer> convert = new HashMap<String, Integer>();
        convert.put("Op1", 0);
        convert.put("Op12", 1);
        convert.put("Op13", 2);
        convert.put("Op16", 3);

        return convert.get(str);
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
