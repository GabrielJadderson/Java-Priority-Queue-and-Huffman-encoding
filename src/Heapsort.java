import java.util.Scanner;

/**
 * Created by gabriel on 01/03/2017.
 */
public class Heapsort
{
    public static void main(String[] args)
    {
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
    }

}
