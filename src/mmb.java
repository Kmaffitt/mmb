
import java.io.*;

/**
 * Created by Kevin on 6/26/2017.
 */
public class mmb {
    public static void main(String[] args){
        int key[] = new int[args[0].length()/2];
        int[] k0 = new int[key.length/4], k1 = new int[key.length/4], k2 = new int[key.length/4], k3 = new int[key.length/4];


        //read in key from cmdline argument
        int keypointer=0;
        for(int i = 0; i < args[0].length(); i++){
            if(i%2 != 0) {
                String substring = args[0].substring(i-1, i+1);
                //System.out.println("Substring:" + substring);
                key[keypointer] = Integer.parseInt(substring, 16);
                //System.out.println("After int conv: " + key[keypointer]);
                keypointer ++;
            }
        }
        //split key into subarrays
        int k0ptr = 0, k1ptr = 0, k2ptr = 0, k3ptr = 0;
        for(int k = 0; k < key.length; k++){
            if(k < 4){
                k0[k0ptr] = key[k];
                k0ptr ++;
            }else if(k < 8){
                k1[k1ptr] = key[k];
                k1ptr ++;
            }else if(k < 12){
                k2[k2ptr] = key[k];
                k2ptr ++;
            }else{
                k3[k3ptr] = key[k];
                k3ptr ++;
            }
        }
        printKeyArrays(k0,k1, k2, k3);

        char message[] = new char[16];
        char[] x0 = new char[4], x1 = new char[4], x2 = new char[4], x3 = new char[4];

        //initialize message array to empty chars so we don't have to backfill later if
        //given input of length < 16
        for(int j = 0;j < message.length; j++){
            message[j] = '\0';
        }
        //read in message from StdIn TODO: switch back to input stream reader before submission
        //InputStreamReader reader = new InputStreamReader(System.in);


        int i;
        int msgPointer = 0;
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Kevin\\Desktop\\input.txt");
            //while ((i = reader.read()) != -1) {
                //message[msgPointer] = (char) i;
                //msgPointer ++;
            //}

            while ((i = fis.read()) != -1) {
                message[msgPointer] = (char) i;
                msgPointer ++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //split message into subarrays
        int x0ptr = 0, x1ptr = 0, x2ptr = 0, x3ptr = 0;
        for(int k = 0; k < message.length; k++){
            if(k < 4){
                x0[x0ptr] = message[k];
                x0ptr ++;
            }else if(k < 8){
                x1[x1ptr] = message[k];
                x1ptr ++;
            }else if(k < 12){
                x2[x2ptr] = message[k];
                x2ptr ++;
            }else{
                x3[x3ptr] = message[k];
                x3ptr ++;
            }
        }



    }

    public static int xOr(int a, int b){
        return a^b;
    }

    public static void printKeyArrays(int[]k0, int[]k1, int[]k2, int[]k3){
        for(int i : k0){
            System.out.println(i);
        }
        System.out.println("--------");
        for(int i : k1){
            System.out.println(i);
        }
        System.out.println("--------");
        for(int i : k2){
            System.out.println(i);
        }
        System.out.println("--------");
        for(int i : k3){
            System.out.println(i);
        }
    }

}
