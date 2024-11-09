package compiler;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public final class OptimJCompiler {
    private final String contentSourceRoot;
    private String[][] allFilesContents;
    private final ArrayList<File[]> files;
    private final ArrayList<String> outputDirectories;
    private final String outputRoot;


    public OptimJCompiler(String contentSourceRoot, String outputRoot) throws FileNotFoundException {
        files = new ArrayList<>();
        outputDirectories = new ArrayList<>();
        this.contentSourceRoot = contentSourceRoot;
        this.outputRoot=outputRoot;
        setUpFileContent();
    }
    public void compile(String outputSourceRoot){

    }


    public void setUpFileContent() throws FileNotFoundException {
        getFiles(new File(contentSourceRoot));
        File[][] allFiles = files.toArray(new File[][]{});
        Scanner theOneWhoReads;
        ArrayList<String> data = new ArrayList<>();
        ArrayList<String[]> datas = new ArrayList<>();
        for(int l =0; l<allFiles.length; l++){
            for(int k=0; k<allFiles[l].length;k++){
                theOneWhoReads = new Scanner(allFiles[l][k]);
                try {
                    while(true) {
                        data.add(theOneWhoReads.nextLine());
                    }
                }catch (Exception ignored){
                }
                datas.add(data.toArray(new String[]{}));
                data.clear();
            }
        }
        allFilesContents = datas.toArray(new String[][]{});
    }
    private void getFiles(@NotNull File source){
        /*T
        This simply just tries to find all
        the files in the Source Root Folder,
        which prepares the system for all known
        files and gets ready to compile immediately.
         */

        File[] starterSources = source.listFiles();
        if(starterSources!= null) {
            ArrayList<File> internalFiles = new ArrayList<>();
            for (int l = 0; l < starterSources.length; l++) {
                if(starterSources[l].isDirectory()){
                    outputDirectories.add(starterSources[l].getPath());
                    getFiles(starterSources[l]); //Recursion, Yeah!
                }else{
                    internalFiles.add(starterSources[l]);
                }
            }
            files.add(internalFiles.toArray(new File[]{}));
        }
    }

    private void setUpFoldersNecessary(){
        if(!outputDirectories.isEmpty()) {
            File f = new File(outputRoot);
            for(int l =0; outputDirectories.size()>l;l++){
                File f1 = new File(f.getAbsolutePath()+"/"+outputDirectories.get(l));
                f1.mkdirs();
            }
        }
    }
    private String convertFromOptimJ(){
        setUpFoldersNecessary();
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        OptimJCompiler f = new OptimJCompiler("src","test");
        f.setUpFoldersNecessary();

    }
}
