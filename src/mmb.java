
import java.io.*;

/**
 * Created by Kevin on 6/26/2017.
 */
public class mmb {
    //5 constants as defined by algo specs
    public static final int C = 0x2AAAAAAA;
    public static final int c0 = 0x025F1CDB;
    public static final int c1 = 0x04BE39B6;
    public static final int c2 = 0x12F8E6D8;
    public static final int c3 = 0x2F8E6D81;



    public static void main(String[] args){
        //int key[] = new int[args[0].length()];
        String key = args[0];
        int k0 = 0, k1 = 0, k2 = 0, k3 = 0;


        //read in key from cmdline argument
        int keypointer=0;

        System.out.println(key.length());
        for(int i = 0; i <= key.length(); i++){

            if(i%8==0 && i!=0) {
                String substring = args[0].substring(i-8, i);
                System.out.println("Substring:" + substring);
                if(i == 8){
                    k0 = Integer.parseInt(substring, 16);
                }
                else if(i == 16){
                    k1 = Integer.parseInt(substring, 16);
                } else if(i == 24){
                    k2 = Integer.parseInt(substring, 16);
                } else if(i == 32){
                    k3 = Integer.parseInt(substring, 16);
                }

            }


        }
        System.out.println("k0: " + k0);
        System.out.println("k1: " + k1);
        System.out.println("k2: " + k2);
        System.out.println("k3: " + k3);

        System.out.println("---------");

        System.out.println("k0: " + Integer.toHexString(k0));
        System.out.println("k1: " + Integer.toHexString(k1));
        System.out.println("k2: " + Integer.toHexString(k2));
        System.out.println("k3: " + Integer.toHexString(k3));



        char message[] = new char[16];
        int int_val_message[] = new int[16];
        int x0 = 0, x1 = 0, x2 = 0, x3 = 0;

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

        for(int k = 0; k < message.length; k++){
            int_val_message[k] = (int)message[k];
            if(k % 4 == 0 && k != 0) {
                String substring = message.toString().substring(k-4, k);


            }
        }



    }

    public static int xOr(int a, int b){
        return a^b;
    }
    public static int multMod(int a, int b, int m){
        return (a*b)%m;
    }
    public static char[][] mmbFunc(char[][] message){
        for(char[] charArray : message){

        }
        return message;
    }



}
