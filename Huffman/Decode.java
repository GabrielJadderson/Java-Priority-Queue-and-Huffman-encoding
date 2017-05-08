import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Gabriel Jadderson on 08/05/2017.
 */
public class Decode extends Encode
{
    //static int sum;

    public Decode()
    {
        super();
        //sum = 0;
    }

    public static void main(String[] args)
    {
        inputFile = args[0];
        outputFile = args[1];

        new Decode();

        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFile));
            FileInputStream fileInputStream = new FileInputStream(new File(inputFile));

            BitInputStream bitInputStream = new BitInputStream(fileInputStream);

            for (int i = 0; i < frequency.length; i++)
            {
                //int freq = bitInputStream.readInt();
                pqHeap.insert(new Element(bitInputStream.readInt(), new Tree(null, null, i)));
                //sum += freq;
            }

            Tree huffTree = Huffman();

            int charactersWritten = 0;
            while (true)
            {

                int val = huffTree.getCharacter(bitInputStream);
                if (val == -1) break;
                charactersWritten++;

                System.out.println(val);
                fileOutputStream.write(val);

            }


            fileInputStream.close();
            bitInputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < frequency.length; i++)
        {
            //System.out.println(frequency[i]);
        }

    }

}
