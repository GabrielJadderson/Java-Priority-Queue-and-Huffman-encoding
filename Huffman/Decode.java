import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Gabriel Jadderson on 08/05/2017.
 */
public class Decode
{
    static int[] frequency;
    static String[] codes;
    static PQHeap pqHeap;
    static String inputFile = "";
    static String outputFile = "";

    public Decode()
    {
        frequency = new int[256];
        codes = new String[256];
        pqHeap = new PQHeap(256);
    }

    public static void main(String[] args) {
        inputFile = args[0];
        outputFile = args[1];

        new Decode();

        try (FileInputStream fileInputStream = new FileInputStream(new File(inputFile)); BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream))
        {
            for(int i = 0; i < frequency.length; i++) {
                frequency[i] = bufferedInputStream.read();
            }

            /*int x = bufferedInputStream.read();
            while (x != -1 && x < 255)
            {
                //System.out.println(x);
                frequency[x]++;

                x = bufferedInputStream.read();
            }*/
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        for(int i = 0; i < frequency.length; i++) {
            System.out.println(frequency[i]);
        }

    }

}
