/**
 * 
 */
package datastructure;

/**
 * @author Pritam
 *
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class AlgorithmicCrush {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int testCount = scan.nextInt();
        long[] array = new long[size+1];
        for(int i=0;i<testCount;i++){
            int startIndex = scan.nextInt();
            int endIndex = scan.nextInt();
            long numberToAdd = scan.nextLong();
            array[startIndex]+=numberToAdd;
            if(endIndex+1<=size)
                array[endIndex+1]-=numberToAdd;
        }
        long max = Long.MIN_VALUE;
        long sum = 0;
        for(int i=1;i<=size;i++){
            sum+=array[i];
            if(sum>max)
                max=sum;
        }
        System.out.println(max);
    }
}
