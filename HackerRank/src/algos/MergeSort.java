package algos;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MergeSort {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int count = scan.nextInt();
        int[] a= new int[count];
        for(int i=0;i<count;i++){
            a[i]=scan.nextInt();
        }
        merge_sort(a,0,count-1);
        print(a);
        //System.out.println(a[0]+" - "+a[1]+" - "+a[2]+" - "+a[3]+" - "+a[4]);
    }
    
    static void print(int[] a){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0;i<a.length-1;i++){
            sb.append(a[i]+",");
        }
        sb.append(a[a.length-1]+"]");
        System.out.println(sb.toString());
    }
    
    static void merge_sort(int[] a,int start, int end){
        int size = end - start;
        if (size==0)
            return;
        int q = size/2; 
        //System.out.println(start+" - "+q+" - "+end);
        merge_sort(a,start,start+q);
        merge_sort(a,start+q+1,end);
        merge(a,start,start+q,end);
    }
    
    static void merge(int[] a, int p, int q, int r){
        int n1 = q-p+1;
        int n2 = r-q;
        int[] left= new int[n1+1];
        int[] right = new int[n2+1];
        
        for(int i = 0; i<n1;i++){
            left[i]=a[p+i];
            //System.out.println(a[p+i]);
        }
        
        for(int i = 0; i<n2;i++){
            right[i]=a[q+i+1];
        }
        left[n1]=Integer.MAX_VALUE;
        right[n2]=Integer.MAX_VALUE;
        int leftI=0;
        int rightI=0;
        for(int i = p; i<=r;i++){
            if(left[leftI]<right[rightI]){
                a[i]=left[leftI];
                leftI++;
            }else{
                a[i]=right[rightI];
                rightI++;
            }
        }
    }
}
