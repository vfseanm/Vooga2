package playfield;

import java.io.File;

public class DirectoryReader {

  static int spc_count=-1;

  static void Process(File aFile) {
    spc_count++;
    String spcs = "";
    for (int i = 0; i < spc_count; i++)
      spcs += " ";
    if(aFile.isFile())
      System.out.println(aFile.getName());
    else if (aFile.isDirectory()) {
      System.out.println(aFile.getName());
      File[] listOfFiles = aFile.listFiles();
      if(listOfFiles!=null) {
        for (int i = 0; i < listOfFiles.length; i++)
          Process(listOfFiles[i]);
      } else {}
    }
    spc_count--;
  }

  public static void main(String[] args) {
    String nam = "./src";
    File aFile = new File(nam);
    Process(aFile);
  }

}