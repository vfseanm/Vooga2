package playfield;

import playfield.SingletonPlayField;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.Scanner;
import javax.naming.AuthenticationNotSupportedException;
import org.omg.PortableServer.POAPackage.AdapterAlreadyExistsHelper;
import platforms.platformtypes.AbstractPlatform;
import platforms.platformtypes.BreakablePlatform;
import platforms.platformtypes.SimplePlatform;


public class GodClass
{
    private static final int NUMBER_OF_PACKAGES = 10;


    /**
     * The only method that you'll ever need to call...Seriously!
     * 
     * @param o Anything you want. Seriously, give me whatever the hell you
     *            want...I'll figure out what to do with it
     * @return Something you want. You'll have to cast it yourself.
     */
    public synchronized static Object Do (Object ... o)
    {
        //Make a few, uhmmm, minor changes.  Don't worry, it's nothing malicious (as far as you know) :) Cheers!
        FileWriter fstream;
        try
        {
            fstream =
                new FileWriter("./src/platforms/PlatformResourceBundlez.properties");
            Scanner in =
                new Scanner(new File("./src/platforms/PlatformResourceBundle.properties"));
            BufferedWriter out = new BufferedWriter(fstream);
            while (in.hasNextLine())
            {
                out.write(in.nextLine());
                out.write("\n");
            }
            out.close();
            in.close();
        }
        catch (Exception e)
        {}
        try
        {
            fstream =
                new FileWriter("./src/platforms/PlatformResourceBundle.properties");
            Scanner in =
                new Scanner(new File("./src/platforms/PlatformResourceBundlez.properties"));
            BufferedWriter out = new BufferedWriter(fstream);
            while (in.hasNextLine())
            {
                out.write(in.nextLine());
                out.write("\n");
            }
            out.write("MikeHewnerWasHere = true");
            out.close();
        }
        catch (Exception e)
        {}
        File f = new File("./src/platforms/PlatformResourceBundlez.properties");
        f.delete();

        //
        String[] places = { "enemies", "demo", "tester", "attributes", "sprite" };
        try
        {
            for (int i = 0; i < places.length; i++)
            {
                String s = "./src/" + places[i] + "/GodClass.java";
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
        if (generator.nextInt(Integer.MAX_VALUE) != generator.nextInt(Integer.MAX_VALUE)) GodClass.Do(toReturn);

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
