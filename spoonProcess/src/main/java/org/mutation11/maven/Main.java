package org.mutation11.maven;

import java.io.*;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;


public class Main {
    public static String op;
    public static String sel;
    public static String theFolder;
    public static String result;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        // Copy ALL
        theFolder = args[0];
        op = args[1];
        sel = args[2];
        result = args[3];
//        String theFolder = "toBeMutated/";
//        copyFile(theFolder, "../mutatedCode/src/main/java/minimal/");
        recursiveCopy(new File(theFolder), new File("../mutatedCode/src/main/java"));

//        File currentDir = new File("../mutatedCode/src/main/java"); // current directory
//        displayDirectoryContents(currentDir);

        // Mutated
          /** Mutated all file.java of a folder **/
//        convertTheJava(args[0], Integer.parseInt(args[1]));
        convertTheJava(args[0], paramToMutator(args[1]), Integer.parseInt(args[2]));
    }

    private static void convertTheJava(String nameFile, AbstractProcessor<CtElement> mutator, int percentSelecteur) throws FileNotFoundException, UnsupportedEncodingException {
        // we instantiate the mutation tester
//        MutationTester<Racine> mutationTester = new MutationTester<Racine>(nameFile,  listMutator().get(indexMutator));
        MutationTester<Racine> mutationTester = new MutationTester<Racine>(nameFile,  mutator);

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
     * HashMap converting the param string in command, in the mutator corresponding
     * @param str
     * @return
     */
    private static AbstractProcessor<CtElement> paramToMutator(String str) {
        HashMap<String, AbstractProcessor<CtElement>> convert = new HashMap<String, AbstractProcessor<CtElement>>();
        convert.put("Op1", new Op1());
        convert.put("Op9", new Op9());
        convert.put("Op12", new Op12());
        convert.put("Op13", new Op13());
        convert.put("Op16", new Op16());

        return convert.get(str);
    }


    public static void recursiveCopy(File fSource, File fDest) {
        try {
            if (fSource.isDirectory()) {
                // A simple validation, if the destination is not exist then create it
                if (!fDest.exists()) {
                    fDest.mkdirs();
                }

                // Create list of files and directories on the current source
                // Note: with the recursion 'fSource' changed accordingly
                String[] fList = fSource.list();

                for (int index = 0; index < fList.length; index++) {
                    File dest = new File(fDest, fList[index]);
                    File source = new File(fSource, fList[index]);

                    // Recursion call take place here
                    recursiveCopy(source, dest);
                }
            }
            else {
                // Found a file. Copy it into the destination, which is already created in 'if' condition above

                // Open a file for read and write (copy)
                FileInputStream fInStream = new FileInputStream(fSource);
                FileOutputStream fOutStream = new FileOutputStream(fDest);

                // Read 2K at a time from the file
                byte[] buffer = new byte[2048];
                int iBytesReads;

                // In each successful read, write back to the source
                while ((iBytesReads = fInStream.read(buffer)) >= 0) {
                    fOutStream.write(buffer, 0, iBytesReads);
                }

                // Safe exit
                if (fInStream != null) {
                    fInStream.close();
                }

                if (fOutStream != null) {
                    fOutStream.close();
                }
            }
        }
        catch (Exception ex) {
            // Please handle all the relevant exceptions here
        }
    }

//    private static void copyFile(String source, String dest) {
//
//        File srcFolder = new File(source);
//        File destFolder = new File(dest);
//
//        //make sure source exists
//        if(!srcFolder.exists()){
//
//            System.out.println("Directory does not exist.");
//            //just exit
//            System.exit(0);
//
//        }else{
//
//            try{
//                copyFolder(srcFolder,destFolder);
//            }catch(IOException e){
//                e.printStackTrace();
//                //error, just exit
//                System.exit(0);
//            }
//        }
//
//        System.out.println("Done");
//    }

//    public static void copyFolder(File src, File dest)
//            throws IOException{
//
//        if(src.isDirectory()){
//
//            //if directory not exists, create it
//            if(!dest.exists()){
//                dest.mkdir();
//                System.out.println("Directory copied from "
//                        + src + "  to " + dest);
//            }
//
//            //list all the directory contents
//            String files[] = src.list();
//
//            for (String file : files) {
//                //construct the src and dest file structure
//                File srcFile = new File(src, file);
//                File destFile = new File(dest, file);
//                //recursive copy
//                copyFolder(srcFile,destFile);
//            }
//
//        }else{
//            //if file, then copy it
//            //Use bytes stream to support all file types
//            InputStream in = new FileInputStream(src);
//            OutputStream out = new FileOutputStream(dest);
//
//            byte[] buffer = new byte[1024];
//
//            int length;
//            //copy the file content in bytes
//            while ((length = in.read(buffer)) > 0){
//                out.write(buffer, 0, length);
//            }
//
//            in.close();
//            out.close();
//            System.out.println("File copied from " + src + " to " + dest);
//        }
//    }
}
