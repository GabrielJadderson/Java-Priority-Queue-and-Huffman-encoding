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


        //test_1();
        //test_2();
        test_3();
        */
        test_4();
    }

    private static void test_4()
    {
        PQ pq = new PQHeap(1000); //4 ms to create this.

        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++)
        {
            pq.insert(new Element((int)Math.random(), null));
        }

        long endTime = System.nanoTime();




        System.out.println((endTime - startTime)/1e06 + " ms");
    }

    /*public static void test_1()
    {
        int[] AA = new int[10];

        AA = new int[]{0, 10, 1, 14, 25, 3};

        for (int i = AA.length / 2; i >= 1; i--)
        {
            System.out.println("Max_Heapify iterations: " + i);
            new tempClass(AA.length).max_heapify(AA, i);
        }


        for (int i = 1; i < AA.length; i++)
        {
            System.out.println(AA[i] + " index: " + i);
        }

        System.out.print("generated output: ");
        for (int i = 1; i < AA.length; i++)
        {
            System.out.print(AA[i] + " ");
        }


        System.out.println("\nexpected output: 25, 10, 14, 1, 3");
    }*/

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

    public static void test_3()
    {
        PQHeap pq = new PQHeap(50);

        pq.insert(new Element(42, null));
        pq.insert(new Element(55, null));
        pq.insert(new Element(3, null));
        pq.insert(new Element(1011, null));
        pq.insert(new Element(25, null));
        pq.insert(new Element(100, null));
        pq.insert(new Element(200, null));
        pq.insert(new Element(321, null));
        pq.insert(new Element(503, null));
        pq.insert(new Element(1711, null));
        pq.insert(new Element(101, null));
        pq.insert(new Element(171, null));
        pq.insert(new Element(811, null));

        for (Element p : pq.data)
        {
            if (p != null)
            {
                System.out.println(p.key);
                //System.out.println(pq.dataCount);
            }
        }

        System.out.println("-----");

        Element e = pq.extractMin();
        while (e != null)
        {
            System.out.println(e.key);
            e = pq.extractMin();
        }
    }

}
