import java.util.*;
public class Textprocessing {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a sentence: ");
        String sentence=sc.nextLine();
        String[] words=sentence.split(" ");
        int wordcount=words.length;
        System.out.println("Total no of words: "+wordcount);
        String uc=sentence.toUpperCase();
        System.out.println("The UpperCase of sentence: "+uc);
        StringBuilder sb=new StringBuilder(sentence);
        sb.reverse();
        System.out.println("The Reversed String is: "+sb);
        StringBuffer sBuffer=new StringBuffer(sentence);
        System.out.println("Enter word to Replace: ");
        String old=sc.nextLine();
        System.out.println("Enter new Word: ");
        String newword=sc.nextLine();
        int index=sBuffer.indexOf(old);
        if (index!=-1){
            sBuffer.replace(index,index+old.length(),newword);
        } else {
            System.out.println("Word not found");
        }
        System.out.println("Updated sentence: "+sBuffer);
        sc.close();
    }
}
