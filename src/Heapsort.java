import java.util.Random;

/**
 * Created by gabriel on 01/03/2017.
 */
public class Heapsort
{
    public static void main(String[] args)
    {
        /*
        PQ pq = new PQHeap(1000);

        int n = 0;
        int i;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt())

        {
            i = sc.nextInt();
            n++;
            pq.insert(new Element(i, null));
        }
        while (n > 0)

        {
            System.out.println(pq.extractMin().key);
            n--;
        }
        */

        test_1();
    }

    public static void test_1()
    {
        int[] AA = new int[10];

        for (int i = 0; i < AA.length; i++)
        {
            AA[i] = new Random().nextInt(10);
            System.out.println("random: " + AA[i] + " index: " + i);
        }

        new tempClass().max_heapify(AA, 1);

        for (int i = 0; i < AA.length; i++)
        {
            System.out.println(AA[i] + " index: " + i);
        }
    }

}
