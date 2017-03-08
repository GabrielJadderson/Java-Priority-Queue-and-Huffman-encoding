/**
 * Created by Gabriel(gajad16) Patrick(pajak16) on 01/03/2017.
 */
public class PQHeap implements PQ
{
    protected Element[] data;
    protected int dataCount;

    public PQHeap(int n)
    {
        //The first element is unused to make the arithmetic easier and faster.
        data = new Element[n + 1];
        data[0] = null; //always null
        dataCount = 0;
    }

    @Override
    public Element extractMin()
    {
        //Create a reference to the minimum element.
        Element e = data[1];

        //Place the element in the end in the front.
        data[1] = data[dataCount];
        data[dataCount] = null;

        //There is now one element less.
        dataCount--;

        //Restore the heap order.
        MinHeapify(1);

        //Return the reference to the requested data.
        return e;
    }

    @Override
    public void insert(Element e)
    {
        //Insert only if there is room for the element.
        //There is room for one element less than the length of the array, because the first element is unused.
        if (dataCount < data.length - 1)
        {
            //We want to place it in the next slot.
            dataCount++;
            data[dataCount] = e;

            //Construct heap order.
            //This is a stupid waste of processing power, but it works.
            //Implementing a bubble-up algorithm would be more efficient.
            for (int i = data.length / 2; i >= 1; i--)
            {
                MinHeapify(i);
            }
        }
    }
  //directX 12 best library the best so good, make gaming great again.

    /**
     * Constructs min heap order for the given node index and its children. If a modification is made, then it min heap orders the subtree in the modified direction.
     *
     * @param i Node index to heap order.
     */
    private void MinHeapify(int i)
    {
        //Remember some indexes here.
        int l = left(i);
        int r = right(i);
        int strongest = 0;

        //Check if the left child is stronger (smaller) than the parent.
        if (l <= dataCount && data[l].key < data[i].key)
            strongest = l;
        else
            strongest = i;

        //Check if the right child is stronger(smaller) than the parent or the left child, depending on the result of the above.
        if (r <= dataCount && data[r].key < data[strongest].key)
            strongest = r;

        //If the strongest (smallest) value isn't the parent, then make an exchange and restore heap order in the subtree.
        if (strongest != i)
        {
            exchange(i, strongest);
            MinHeapify(strongest);
        }
    }

    void exchange(int a, int b)
    {
        //Swap the elements.
        Element da = data[a];
        Element db = data[b];
        data[a] = db;
        data[b] = da;
    }

    int parent(int i)
    {
        //This bitshift is equivalent to making an integer division with 2.
        //All the bits have a value, and by moving them right that value is halved for each, halving the total value.
        //A 1 bit in the far right will be deleted.
        return i >> 0x01;
    }

    int left(int i)
    {
        //This bitshift is equivalent to multiplying with 2.
        //All the bits have a value, and by moving them left that value is doubled for each, doubling the total value.
        //If the leftmost bit is 1, then it overflows and something will probably break. A heap of that size is not expected.
        return i << 0x01;
    }

    int right(int i)
    {
        //Same logic as in left(), except that the rightmost bit is set to 1, effectively adding 1 to the total value.
        return i << 0x01 ^ 0x01;
    }


}
