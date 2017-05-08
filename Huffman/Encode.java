import java.io.*;

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

    public Encode()
    {
        //counter = 0;
        frequency = new int[256];
        codes = new String[256];
        pqHeap = new PQHeap(256);
    }


    public static void main(String[] args)
    {

        new Encode();


        try (FileInputStream fileInputStream = new FileInputStream(new File("in.txt")); BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream))
        {
            int x = bufferedInputStream.read();
            while (x != -1 && x < 255)
            {
                //System.out.println(x);
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
        for (int i = 0; i < codes.length; i++)
        {
            String s = codes[i];
            if (!(s == null))
            {
                System.out.println("_Code for " + i + ": " + s);
            }
        }


        //NOW SAVE SHIT :D
        try
        {
            BitOutputStream bitOutputStream = new BitOutputStream(new FileOutputStream(new File("COMPRESSEDOUT.txt")));

            for (int i = 0; i < frequency.length; i++)
            {
                bitOutputStream.writeInt(frequency[i]);
            }
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
                System.out.println("Code for " + t.character + " is: " + s + "  .");
            }

            huffTraverse(t.left, s + left);
            huffTraverse(t.right, s + right);
        }
    }

    /*public static void huffTraverse(Object t, String s)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(s);

        if (!(t instanceof Tree))
        {
            sb.append(right);
            Element e = (Element) t;
            codes[(int) e.data] = sb.toString();
            return;
        }

        Tree current = (Tree) t;

        huffTraverse(current.right, sb.toString());

        sb.append(left);

        if (current.data instanceof Tree)
        {
            huffTraverse(current.data, sb.toString());
        } else
        {
            Element e = (Element) current.data;
            codes[(int) e.data] = sb.toString();
        }


    }   */


    public static class Tree
    {
        Tree right;
        Tree left;
        int character;

        public Tree(Tree left, Tree right, int character)
        {
            this.left = left;
            this.right = right;
            this.character = character;
        }
    }

    public static Tree Huffman()
    {
        int n = frequency.length;

        for (int i = 1; i < n; i++)
        {
            Element left = pqHeap.extractMin();
            Element right = pqHeap.extractMin();

            //Tree t = new Tree(left.key + right.key, left, right);
            //System.out.println(left.key + right.key);

            //System.out.println((int)left.data + " has been paired with " + (int)right.data);

            //pqHeap.insert(t);

            pqHeap.insert(new Element(left.key + right.key, new Tree((Tree) left.data, (Tree) right.data, -1)));

        }

        return (Tree) pqHeap.extractMin().data;
    }


}
