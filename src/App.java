//import java.io.BufferedReader;
//import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {

    public static Float[] createRandomArray(int arrayLength){
        Float[] RandomArray = new Float[arrayLength];
        Random rand = new Random();
        for(int i = 0; i < RandomArray.length; i++){
            RandomArray[i] = rand.nextFloat(10);
        }
        return(RandomArray);
    }

    public static<E extends Comparable<E>> void writeArrayToFile(E[] array, String filename){
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)))){
                for(int i = 0; i < array.length; i++){
                    writer.write(array[i] +"\n");
                }
            }catch (IOException e){
                System.out.println("Error, file could not be written.");
            }
    }
 
 /*   public static int[] readFileToArray(String filename, boolean outputError){
 *           try(BufferedReader reader = new BufferedReader(new FileReader(new File(filename)))){
 *               String line;
 *               List<Integer> list = new ArrayList<>();
 *               while((line = reader.readLine()) != null){
 *                   list.add(Integer.parseInt(line));
 *               }
 *               int[] array = new int[list.size()];
 *               for(int i = 0; i < array.length; i++){
 *                   array[i] = list.get(i);
 *               }
 *               return array;
 *           }catch (Exception e){
 *               if(outputError){
 *                   System.out.println("Error, File could not be read");
 *               }
 *           }
 *           return null;
 *   }
 */
    public static<E extends Comparable<E>> void bubbleSort(E[] array){
        int dottimer = 0;
        for(int i = 0; i < array.length; i++){
            boolean swapmade = false;
            for(int t = 0; t < (array.length - (i + 1)); t++){
                dottimer++;      //Exists to let user know program is still running
                if((dottimer % 100000000) == 0){
                    System.err.print(".");
                }
                E a = array[t];
                E b = array[t + 1];
                if(a.compareTo(b) > 0){
                    array[t] = b;
                    array[t+1] = a;
                    swapmade = true;
                }
            }
            
            if(!swapmade){
                return;
            }
        }
    }
    public static <E extends Comparable<E>> void mergeSort(E[] array){
        mergeSort(array, 0, array.length);
    }
    public static <E extends Comparable<E>> void mergeSort(E[] array, int start, int end){
        if ((end - start) <= 1){
            return;
        }
            int middle = (start + end)/2;
            mergeSort(array, start, middle);
            mergeSort(array, middle, end);
            merge(array, start, middle, end);

    }
    public static <E extends Comparable<E>> void merge(E[] array, int start, int middle, int end){
        int half1st = start;
        int half2nd = middle;
        List<E> tempArray = new ArrayList<E>();

        while( half1st < middle && half2nd < end){
            if (array[half1st].compareTo(array[half2nd]) >= 0){
                tempArray.add(array[half1st]);
                half1st++;
            } else {
                tempArray.add(array[half2nd]);
                half2nd++;
            }
            
        }

        while( half1st < middle){
            tempArray.add(array[half1st]);
            half1st++;
            
        }
        while( half2nd < end){
            tempArray.add(array[half2nd]);
            half2nd++;
            
        }

        for(int i = start; i < end; i++){
            array[i] = tempArray.get(i - start); 
        }

    }
    public static void main(String[] args) throws Exception {
        //String filename = null;
        int arrayLength = 0;
        Float[] array;
        Float[] array2;
        long tempstart;
        long tempend;
        long temptotaltime;
        //char mode = '!';
        char compare = '!';
        Scanner scnr = new Scanner(System.in);
       /* while(mode != 'F' && mode !='R'){
            System.out.print("Please enter either [R] for a random array of numbers, or [F] to specify a file to sort: ");
            mode = scnr.nextLine().toUpperCase().charAt(0);
        }
        if(mode == 'F'){
            
            while(readFileToArray(filename, false) == null){
                System.out.print("Please enter the file you would like to sort: ");
                filename = scnr.nextLine();
                array = readFileToArray(filename, true);
                
            }
            array = readFileToArray(filename, true);
            array2 = new int[array.length];
                }else{
           */ while(arrayLength < 1){
                System.out.print("Please enter a number greater than 0 for the random array length: ");
                arrayLength = scnr.nextInt();
                scnr.nextLine();
                
            } 
            array = createRandomArray(arrayLength);
            array2 = new Float[array.length];
        //}
        for(int i = 0; i < array.length; i++){
            array2[i] = array[i];
        }
        tempstart = System.currentTimeMillis();
        mergeSort(array);
        tempend = System.currentTimeMillis();
        temptotaltime = tempend - tempstart;
        System.out.println("");
        System.out.println("Time elapsed to merge sort: " + temptotaltime + "ms");
        writeArrayToFile(array, "Output.txt");
        while(compare != 'Y' && compare !='N'){
            System.out.println("");
            System.out.println("Compare speeds to bubble sort? [Y/N]");
            System.out.println("<Warning: May be a Lengthy Process>");
            compare = scnr.nextLine().toUpperCase().charAt(0);
        }
        if(compare == 'Y'){
            tempstart = System.currentTimeMillis();
            bubbleSort(array2);
            tempend = System.currentTimeMillis();
            System.out.println("");
            temptotaltime = tempend - tempstart;
            System.out.println("Time elapsed to bubble sort: " + temptotaltime + "ms");
            System.out.println("");
        }
        
        scnr.close();
    }
}
