/**
 * Created by Gabriel Jadderson on 02-03-2017.
 */
public class tempClass
{

    /**
     * not tested yet
     *
     * @param A
     * @param i
     */
    public void max_heapify(int[] A, int i)
    {
        int l = left(i);
        int r = right(i);
        int largest = 0;
        if (l <= A.length && A[l] > A[i])
            largest = l;
        else
            largest = r;

        if (r <= A.length && A[r] > A[largest])
            largest = r;


        if (largest != i)
        {
            try
            {
                swap(A, i, largest);
                max_heapify(A, largest);
            } catch (ArrayIndexOutOfBoundsException e)
            {
                return;
            }
        }
    }

    public int parent(int i)
    {
        return (int) Math.floor(i / 2);
    }

    public int left(int i)
    {
        return 2 * i;
    }

    public int right(int i)
    {
        return 2 * i + 1;
    }

    /**
     * swaps/replaces/exchanges a with b given their indices as input.
     *
     * @param list a list containing integers
     * @param a    the index of the element to be swapped
     * @param b    the index of the element to be swapped
     */
    public void swap(int[] list, int a, int b)
    {
        int valA = list[a];
        int valB = list[b];
        list[a] = valB;
        list[b] = valA;
    }

}
