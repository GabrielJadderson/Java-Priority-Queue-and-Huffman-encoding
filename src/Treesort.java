import java.util.Scanner;

public class Treesort {
    public static void main(String[] args) {
        
        //The dictionary used.
        //To ensure only the functions in the interface are used, the interface is used as the type.
        Dict d = new DictBinTree();
        
        //Read from standard input with this.
        Scanner s = new Scanner(System.in);
        
        //Insert all the numbers given as input.
        while(s.hasNextInt()) {
          d.insert(s.nextInt());
        }
        
        //Fetch the sorted version of the input.
        int[] sorted = d.orderedTraversal();
        
        //Write it out on the screen.
        for(int i = 0; i < sorted.length; i++) {
          System.out.println(sorted[i]);
        }
        
    }
}





