import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Gabriel Jadderson on 08/05/2017.
 */
public class TestingUtility
{
    public TestingUtility()
    {

    }

    public static void main(String[] args) throws Exception
    {
        new TestingUtility();

        BitInputStream bitInputStream = new BitInputStream(new FileInputStream(new File("out.txt")));

        for (int i = 0; i < 256; i++) {
            bitInputStream.readInt();
        }

        int i = 0;
        while ((i = bitInputStream.readBit()) != -1) {
            System.out.print(i);
        }
    }

}
