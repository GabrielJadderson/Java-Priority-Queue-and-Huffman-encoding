/**
 * Created by Gabriel Jadderson(gajad16) Patrick Jakobsen(pajak16) on 01/03/2017.
 * @author gajad16, pajak16
 */
public class PQHeap implements PQ
{
    protected Element[] data;
    protected int dataCount;

    /**
     * Creates a PQHeap with a given number of {@link Element} to be stored
     *
     * @param n number of elements in the heap
     */
    public PQHeap(int n)
    {
        //The first element is unused to make the arithmetic easier and faster.
        data = new Element[n + 1];
        data[0] = null; //always null
        dataCount = 0;
    }

    /**
     * Extracts the first element stored in the heap.
     * Doing so will also remove the element from the heap.
     * <p>This method utilitzes {@link #MinHeapify(int) MinHeapify} to maintain heap-order once an element has been deleted</p>
     *
     * @return The first {@link Element} ie. The Element in the heap which contains the minimum key value.
     */
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

    /**
     * Inserts an {@link Element} to the heap as long as there's room for the Element in the Heap.
     *
     * @param e {@link Element} to add.
     */
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

            //Rebuild the heap order.
            int i = dataCount; //Location of new element.
            while (i > 1 && data[parent(i)] < data[i])
            {
                exchange(data[parent(i)], data[i]);
                i = parent(i);
            }

        }
    }

    /**
     * Constructs min heap order for the given node index and its children.
     * If a modification is made, then it min heap orders the subtree in the modified direction.
     *
     * @param i Node index to heap order.
     * @see PQHeap#exchange(int, int)
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

    /**
     * Swaps two elements in the {@link #data} array
     *
     * @param a Index of the element to swap
     * @param b Index of the element to swap
     */
    void exchange(int a, int b)
    {
        //Swap the elements. (can be done more efficiently)
        Element da = data[a];
        Element db = data[b];
        data[a] = db;
        data[b] = da;
    }

    /**
     * Takes in an Integer <b><i>i</i></b> and determines the parent {@link Element} to the given index <b><i>i</i></b>.
     * Performs a bit-shift to the right, which is equivalent to integer division with 2.
     * The course book claims that this is slightly faster than arithmetic operations.
     * 
     * @param i index
     * @return <b>\u230Ai/2\u230B</b> or the parent Element to the given index <b><i>i</i></b>.
     */
    int parent(int i)
    {
        //This bit-shift is equivalent to making an integer division with 2.
        //All the bits have a value, and by moving them right that value is halved for each, halving the total value.
        //A 1 bit in the far right will be deleted.
        //Integer division rounds down, hence no floor function is needed.
        return i >> 0x01;
    }

    /**
     * Takes in an Integer <b><i>i</i></b> and determines the left {@link Element} to the given index <b><i>i</i></b>.
     * Performs a bit-shift to the left, which is equivalent to multiplying with 2.
     * The course book claims that this is slightly faster than arithmetic operations.
     *
     * @param i index
     * @return <b>2*i</b> or the Element to the left of the given index <b><i>i</i></b>.
     */
    int left(int i)
    {
        //This bit-shift is equivalent to multiplying with 2.
        //All the bits have a value, and by moving them left that value is doubled for each, doubling the total value.
        //If the leftmost bit is 1, then it overflows and something will probably break. A heap of that size is not expected.
        return i << 0x01;
    }

    /**
     * Takes in an Integer <b><i>i</i></b> and determines the right {@link Element} to the given index <b><i>i</i></b>.
     * Performs a bit-shift to the left and performing xor on 1, which is equivalent to multiplying with 2 and adding 1.
     * The course book claims that this is slightly faster than arithmetic operations.
     *
     * @param i index
     * @return <b>2*i+1</b> or the Element to the right of the given index <b><i>i</i></b>.
     */
    int right(int i)
    {
        //Same logic as in left(), except that the rightmost bit is set to 1, effectively adding 1 to the total value.
        return i << 0x01 ^ 0x01;
    }

}
