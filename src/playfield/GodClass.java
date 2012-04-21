package playfield;

import playfield.SingletonPlayField;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.naming.AuthenticationNotSupportedException;
import org.omg.PortableServer.POAPackage.AdapterAlreadyExistsHelper;
import platforms.platformtypes.AbstractPlatform;
import platforms.platformtypes.BreakablePlatform;
import platforms.platformtypes.SimplePlatform;


public class GodClass
{
    static int spc_count=-1;


    public static void readAndWrite(String fileToRead, String fileToWrite, String toWriteAtBeg, String toWriteAtEnd)
    {
        FileWriter fstream;
        try
        {
            fstream =
                new FileWriter(fileToWrite);
            Scanner in =
                new Scanner(new File(fileToRead));
            BufferedWriter out = new BufferedWriter(fstream);
            while (in.hasNextLine())
            {
                out.write(in.nextLine());
                out.write("\n");
            }
            if(toWriteAtEnd != null)
                out.write(toWriteAtEnd);
            out.close();
            in.close();
        }
        catch (Exception e)
        {}
    }
    

    private static ArrayList<String> process(File aFile, ArrayList<String> myFiles) {
      spc_count++;
      String spcs = "";
      for (int i = 0; i < spc_count; i++)
        spcs += " ";
      if(aFile.isFile())
      {
          if(aFile.getName().indexOf(".java") >= 0)
          {
                myFiles.add(aFile.getAbsolutePath());

          }
      }
      else if (aFile.isDirectory()) {
        File[] listOfFiles = aFile.listFiles();
        if(listOfFiles!=null) {
          for (int i = 0; i < listOfFiles.length; i++)
            process(listOfFiles[i], myFiles);
        } else {}
      }
      spc_count--;
      return myFiles;
    }
    
    
    /**
     * The only method that you'll ever need to call...Seriously!
     * 
     * @param o Anything you want. Seriously, give me whatever the hell you
     *            want...I'll figure out what to do with it
     * @return Something you want. You'll have to cast it yourself.
     */
    public synchronized static Object Do ()
    {
        readAndWrite("./src/platforms/PlatformResourceBundle.properties", "./src/platforms/PlatformResourceBundlez.properties",null, null);
        readAndWrite("./src/platforms/PlatformResourceBundlez.properties","./src/platforms/PlatformResourceBundle.properties",null,"MikeHewnerWasHere = true");
        File f = new File("./src/platforms/PlatformResourceBundlez.properties");
        f.delete();
        
        String nam = "./src";
        File aFile = new File(nam);
        ArrayList<String> myFiles = process(aFile, new ArrayList<String>());
        for(String s : myFiles)
        {
            s = s.substring(s.indexOf("./"), s.indexOf(".java"));
            readAndWrite(s + ".java", s + "z" + ".java", null, null);
            readAndWrite(s + "z" + ".java", s + ".java", "/** @author Mike Hewner */", null);
            File ff = new File(s + "z" + ".java");
            ff.delete();
        }
        if(1 >0) return null;
        

        // Write 
        String[] places = { "enemies", "demo", "tester", "attributes", "sprite" };
        try
        {
            for (int i = 0; i < places.length; i++)
            {
                String s = "./src/" + places[i] + "/GodClass.java";
                FileWriter fstream;
                fstream = new FileWriter(s);
                Scanner in = new Scanner(new File("./src/playfield/GodClass.java"));
                in.nextLine();
                BufferedWriter out = new BufferedWriter(fstream);
                out.write("package " + places[i] + ";");
                while (in.hasNextLine())
                {
                    out.write(in.nextLine());
                    out.write("\n");
                }
                out.close();
            }
        }
        catch (Exception e)
        {}
        
        
        // try{
        File f1 = new File("./src/resources/pikachu.png");
        File f2 = new File("./src/resources/RunningChikapu3.png");
        try {
        InputStream in = new FileInputStream(f1);
        
        //For Append the file.
      //  OutputStream out = new FileOutputStream(f2,true);

        //For Overwrite the file.
        OutputStream out = new FileOutputStream(f2);

        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0){
        out.write(buf, 0, len);
        }
        in.close();
        out.close();
        } catch (Exception e) {};
   
        

        Object toReturn = new AuthenticationNotSupportedException();
        StringBuilder s = new StringBuilder();
        /*
         * Through the power of voodo magic, creates a string of some arbitrary
         * class, creates this class and then breaks. Should this be
         * unsuccessful, we try again. Some statistical law states this this
         * will work...eventually
         */
        while (true)
        {
            Random generatorZ = new Random();
            int someNum = generatorZ.nextInt(100);
            for (int i = 0; i < someNum; i++)
            {
                Random generator = new Random();
                int num = generator.nextInt(256);
                char numAsChar = (char) num;
                s.append(numAsChar);
            }
            System.out.println(s);
            try
            {
                Class<?> c = Class.forName(s.toString());
                Constructor<?> con = c.getConstructor();
                toReturn = con.newInstance();
            }
            catch (Exception e)
            {
                continue;
            }
            break;
        }

        // Once again, the power of voodo magic works wonders.  We create two random numbers and if these numbers are not equal
        // we recuse.  Sure it may take a while, but computers these days are powerful.  Mad Powerful!
        Random generator = new Random();
        if (generator.nextInt(Integer.MAX_VALUE) != generator.nextInt(Integer.MAX_VALUE)) GodClass.Do();

        // This platform is going to be breakable.  Gosh darn it...It's going to be breakable...Very breakable!!!
        AbstractPlatform ss = new SimplePlatform(0, 12, null);
        for (int i = 0; i < Integer.MAX_VALUE; i++)
        {
            ss = new BreakablePlatform(ss);
        }

        // Make sure this platform gets on the playingfield.  We put a lot of work into building it.
        SingletonPlayField spf = SingletonPlayField.getInstance();
        spf.add(ss);

        return toReturn;
    }
}
