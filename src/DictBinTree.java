/**
 * Created by gabriel on 23/03/2017.
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
        root = null;
    }

    /**
     * <font color="#448483">rofl</font>
     * @param k
     */
    @Override
    public void insert(int k)
    {
        Node y = null;
        Node x = root;

        while (x != null) {
            //Keep track of the previously checked node at all times.
            y = x;

            if(k < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        if(y == null) {
            root = new Node(k);
        } else if(k < y.data) {
            y.left = new Node(k);
        } else {
            y.right = new Node(k);
        }

        //The tree is one larger now.
        treeSize++;
    }

    /**
     *
     * @return
     */
    @Override
    public int[] orderedTraversal()
    {
        int[] result = new int[treeSize];

        treewalk(root, result);

        return result;
    }


    static int mark = 0;
    /**
     *
     * @param start
     * @param array
     */
    private void treewalk(Node start, int[] array) {

        if(start.left != null) {
            treewalk(start.left, array);
        }

        System.out.println(start.data);
        array[mark] = start.data;
        System.out.println("array contains here: " + array[mark] + "and the mark is: " + mark);
        mark++;

        if(start.right != null) {
            treewalk(start.right, array);
        }

    }

    /**
     *
     * @param k
     * @return
     */
    @Override
    public boolean search(int k)
    {
        Node x = root;
        while (x != null && k != x.data)
        {
            if (k < x.data)
            {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        if (x != null) {
            return true;
        } else {
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
