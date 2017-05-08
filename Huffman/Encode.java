import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbccccccccccccddddddddddddddddeeeeeeeeefffff

/**
 * Created by Gabriel Jadderson on 17/04/2017.
 */
public class Encode
{
    static int[] frequency;
    //static int counter;
    static String[] codes;
    static PQHeap pqHeap;
    static final String left = "0";
    static final String right = "1";
    static String inputFile = "";
    static String outputFile = "";

    public Encode()
    {
        //counter = 0;
        frequency = new int[256];
        codes = new String[256];
        pqHeap = new PQHeap(256);
    }


    public static void main(String[] args)
    {
        inputFile = args[0];
        outputFile = args[1];


        new Encode();


        try (FileInputStream fileInputStream = new FileInputStream(new File(inputFile)); BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream))
        {
            int x = bufferedInputStream.read();
            while (x != -1 && x < 255)
            {
                System.out.println(x);
                frequency[x]++;

                x = bufferedInputStream.read();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < frequency.length; i++)
        {
                pqHeap.insert(new Element(frequency[i], new Tree(null, null, i)));
                //counter++;
        }


        Tree HuffTree = Huffman();

        //StringBuilder stringBuilder = new StringBuilder();

        huffTraverse(HuffTree, "");
        /*for (int i = 0; i < codes.length; i++)
        {
            String s = codes[i];
            if (!(s == null))
            {
                System.out.println("_Code for " + i + ": " + s);
            }
        }*/


        //NOW SAVE SHIT :D
        try
        {
            BitOutputStream bitOutputStream = new BitOutputStream(new FileOutputStream(new File(outputFile)));
            FileInputStream fileInputStream = new FileInputStream(new File(inputFile));
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            for (int i = 0; i < frequency.length; i++)
            {
                bitOutputStream.writeInt(frequency[i]);
            }

            // byte in -> codeword out
            for(int i = bufferedInputStream.read(); i != -1; i = bufferedInputStream.read()) {
                String s = codes[i];
                System.out.print("Writing code: " + s + ": ");

                for (char c : s.toCharArray()) {
                    if (c == '0') {
                        bitOutputStream.writeBit(0);
                        System.out.print(left);
                    } else if (c == '1') {
                        bitOutputStream.writeBit(1);
                        System.out.print(right);
                    }
                }
                System.out.print("\n");
            }

            bitOutputStream.close();
            bufferedInputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void huffTraverse(Tree t, String s)
    {
        if (t != null)
        {
            if (t.character != -1)
            {
                codes[t.character] = s;
                //System.out.println("Code for " + t.character + " is: " + s + "  .");
            }

            huffTraverse(t.left, s + left);
            huffTraverse(t.right, s + right);
        }
    }


    public static Tree Huffman()
    {
        int n = frequency.length;

        for (int i = 1; i < n; i++)
        {
            Element left = pqHeap.extractMin();
            Element right = pqHeap.extractMin();

            pqHeap.insert(new Element(left.key + right.key, new Tree((Tree) left.data, (Tree) right.data, -1)));
        }

        return (Tree) pqHeap.extractMin().data;
    }


}
