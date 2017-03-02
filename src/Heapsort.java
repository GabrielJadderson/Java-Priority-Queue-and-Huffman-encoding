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
        //test_2();
    }

    public static void test_1()
    {
        int[] AA = new int[10];

        AA = new int[]{0, 10, 1, 14, 25, 3};

        for (int i = 1; i < AA.length; i++)
        {
            // AA[i] = new Random().nextInt(10);
            System.out.println("random: " + AA[i] + " index: " + i);
        }

        new tempClass(5).max_heapify(AA, 1);

        for (int i = 1; i < AA.length; i++)
        {
            System.out.println(AA[i] + " index: " + i);
        }
    }

    public static void test_2()
    {

        PQ pq = new PQHeap(6);

        pq.insert(new Element(40, null));
        pq.insert(new Element(1, null));
        pq.insert(new Element(3, null));
        pq.insert(new Element(25, null));
        pq.insert(new Element(100, null));


        System.out.println(pq.extractMin().key);

    }

}
