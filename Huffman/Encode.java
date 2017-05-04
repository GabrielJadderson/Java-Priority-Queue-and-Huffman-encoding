import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Gabriel Jadderson on 17/04/2017.
 */
public class Encode
{
    static int[] frequency;
    static int counter;
    static String[] codes;
    static PQHeap pqHeap;
    static final String left = "0";
    static final String right = "1";

    public Encode()
    {
        counter = 0;
        frequency = new int[256];
        codes = new String[256];
        pqHeap = new PQHeap(256);
    }


    public static void main(String[] args)
    {

        new Encode();

        try (FileInputStream fileInputStream = new FileInputStream(new File("test.txt")); BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream))
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
            if(frequency[i] > 0)
            {
                pqHeap.insert(new Element(frequency[i], i));
                counter++;
            }
        }


        Tree HuffTree = Huffman();

        //StringBuilder stringBuilder = new StringBuilder();

        huffTraverse(HuffTree, "");
        for (String s : codes)
        {
            if(!(s == null))
            {
                System.out.println(s);
            }
        }



    }

    public static void huffTraverse(Object t, String s) {


        if(!(t instanceof Tree)) {
            Element e = (Element)t;
            //if(e.key > 0)
            //{
                codes[(int) e.data] = Integer.toString((int) e.data) + ": " + s;
            //} else {
            //    codes[(int)e.data] = "";
            //}

            return;
        }

        Tree T = (Tree)t;

        huffTraverse(T.data, s + left);
        huffTraverse(T.right, s+right);


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


    public static class Tree extends Element
    {
        //Object left; //See Element.data
        Object right;


        public Tree(int freq, Object left, Object right)
        {
            super(freq, left);
            this.right = right;
        }
    }

    public static Tree Huffman()
    {
        //int n = frequency.length;
        int n = counter;

        for (int i = 1; i < n; i++)
        {
            Element left = pqHeap.extractMin();
            Element right = pqHeap.extractMin();

            Tree t = new Tree(left.key + right.key, left, right);
            //System.out.println(left.key + right.key);

            //System.out.println((int)left.data + " has been paired with " + (int)right.data);

            pqHeap.insert(t);
        }
             
        return (Tree) pqHeap.extractMin();
    }



}
