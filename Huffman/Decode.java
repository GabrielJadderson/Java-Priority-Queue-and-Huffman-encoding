import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.stream.IntStream;

/**
 * Created by Gabriel Jadderson on 08/05/2017.
 */
public class Decode extends Encode
{
    public Decode()
    {
        super();
    }

    public static void main(String[] args)
    {

        new Decode();

        try
        {
            FileInputStream fileInputStream = new FileInputStream(new File(args[0]));
            FileOutputStream fileOutputStream = new FileOutputStream(new File(args[1]));

            BitInputStream bitInputStream = new BitInputStream(fileInputStream);

            for (int i = 0; i < frequency.length; i++)
            {
                int freq = bitInputStream.readInt();
                pqHeap.insert(new Element(freq, new Tree(null, null, i)));
                frequency[i] = freq;
            }

            int sum = IntStream.of(frequency).sum();

            Tree huffTree = Huffman();

            int c = 0;
            while (c < sum)
            {

                int val = huffTree.getCharacter(bitInputStream);
                if (val == -1) break;

                fileOutputStream.write(val);
                c++;
            }

            fileInputStream.close();
            bitInputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
