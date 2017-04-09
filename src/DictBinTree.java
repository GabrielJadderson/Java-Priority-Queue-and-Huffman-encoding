/**
 * Created by Patrick Jakobsen(pajak16) Gabriel Jadderson(gajad16) on 09/04/2017.
 *
 * @author pajak16, gajad1
 */
public class DictBinTree implements Dict
{

    Node root;
    int treeSize;

    /**
     * Constructor
     */
    public DictBinTree()
    {
        //The tree starts out empty.
        root = null;
    }

    /**
     * Inserts a key in the dictionary.
     *
     * @param k is the key to insert into the dictionary.
     */
    @Override
    public void insert(int k)
    {
        //y keeps track of the previous node checked.
        Node y = null;
        //x is the starting outset.
        Node x = root;

        //If x is null, then we reached an empty spot that the new node can be inserted on. The node will belong to the last node traversed.
        while (x != null)
        {
            //Keep track of the previously checked node at all times.
            y = x;

            //Figure out in which direction the new node should be inserted.
            if (k < x.data)
            {
                x = x.left;
            } else
            {
                x = x.right;
            }
        }

        //Here the node is inserted somewhere.
        //If y is null, that means that root is null as well, otherwise y would have become root.
        if (y == null)
        {
            //There is no root, so make the inserted value root.
            root = new Node(k);
        } else if (k < y.data)
        { //Otherwise, place the new node either left or right depending on the relation between the new key and the key in y.
            y.left = new Node(k);
        } else
        {
            y.right = new Node(k);
        }

        //The tree is one larger now. This is kept track of for orderedTraversal.
        treeSize++;
    }

    /**
     * This method creates a sorted array of all the keys in the dictionary and returns it. The array is sorted in nondescending order.
     *
     * @return The method returns an int[] of the keys sorted in nondescending order.
     */
    @Override
    public int[] orderedTraversal()
    {
        //Result to be returned.
        int[] result = new int[treeSize];

        //The function that is used to traverse the tree recursively.
        if (root != null)
        {
            treewalk(root, result);
        }
        //Returns the resulting int[] with the keys.
        return result;
    }

    //This variable is used as a marker for inserting the elements in the array in treewalk.
    static int mark = 0;

    /**
     * This method recursively traverses the tree and inserts the contents of its right subtree in the array, then its own element and then the contents of its right subtree. This is done using the static mark variable in the class, which makes this method not thread safe, even with synchronized blocks.
     *
     * @param start The Node object to work on in the current call.
     * @param array The array to fill elements into.
     */
    private void treewalk(Node start, int[] array)
    {

        //If there is a left subtree, then those elements are all smaller than the key in this Node. Traverse that subtree first.
        if (start.left != null)
        {
            treewalk(start.left, array);
        }

        //System.out.println(start.data);

        //All the smaller elements have already been added to the array. Because the elements in the right subtree are all larger than the key in this Node, this is the next key to be added to the list.
        array[mark] = start.data;

        //System.out.println("array contains here: " + array[mark] + "and the mark is: " + mark);

        //Increment the location at which the next element will be inserted.
        mark++;

        //Insert the remaining elements.
        if (start.right != null)
        {
            treewalk(start.right, array);
        }

    }

    /**
     * This method takes searches for a key in the dictionary and return true if it exists and false if it doesn't.
     *
     * @param k is the key to search for.
     * @return If the key exists in the tree, it returns true, otherwise it returns false.
     */
    @Override
    public boolean search(int k)
    {
        //Start searching at the root.
        Node x = root;

        //If the Node that is being searched is null, a dead end has been reached. If k == data, then a Node that fulfills the criteria has been found.
        while (x != null && k != x.data)
        {
            //In here is is known that x is not null.
            //If k is smaller than the key in the Node, then search to the left, otherwise search to the right.
            if (k < x.data)
            {
                x = x.left;
            } else
            {
                x = x.right;
            }
        }

        //Here the outcome is determined. If the above ended with null, then k was never found, and hence it isn't in the tree and false is returned. If x != null, then k was found in some Node, and true is returned.
        if (x != null)
        {
            return true;
        } else
        {
            return false;
        }
    }

}

/**
 * Node class to represent a node in the binary tree.
 */
class Node
{
    Node left;
    Node right;
    int data;

    public Node(int data)
    {
        this.left = null;
        this.right = null;
        this.data = data;
    }
}
