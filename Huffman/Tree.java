import java.io.IOException;

public class Tree
{
    Tree right;
    Tree left;
    int character;

    public Tree(Tree left, Tree right, int character)
    {
        this.left = left;
        this.right = right;
        this.character = character;
    }

    public int getCharacter(BitInputStream bitInputStream)
    {
        if(left == null && right == null) { //leaf node
            return character;
        }

        try
        {
            int bit = bitInputStream.readBit();
            if (bit == 0 && left != null)
            {
                return left.getCharacter(bitInputStream);
            } else if (bit == 1 && right != null)
            {
                return right.getCharacter(bitInputStream);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return character;
    }
}