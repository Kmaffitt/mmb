
import sun.nio.cs.US_ASCII;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * Created by Kevin on 6/26/2017.
 */
public class mmb {
    //5 constants as defined by algo specs
    public static final BigInteger C = new BigInteger("2AAAAAAA", 16);
    public static final BigInteger c0 = new BigInteger("025F1CDB", 16);
    public static final BigInteger c1 = new BigInteger("04BE39B6", 16);
    public static final BigInteger c2 = new BigInteger("12F8E6D8", 16);
    public static final BigInteger c3 = new BigInteger("2F8E6D81", 16);
    public static final BigInteger[] constArray = {c0, c1, c2, c3};
    public static final BigInteger modVal = new BigInteger("4294967295", 10);


    public static void main(String[] args){
        //int key[] = new int[args[0].length()];
        String key = args[0];
        BigInteger k0 = new BigInteger("0", 16), k1 = new BigInteger("0", 16), k2 = new BigInteger("0", 16), k3 = new BigInteger("0", 16);

        //read in key from cmdline argument

        for(int i = 0; i <= key.length(); i++){

            if(i%8==0 && i!=0) {
                String substring = args[0].substring(i-8, i);
                //byte[] stringBytes = flipBytes(substring.getBytes());
                //String newsubString = new String(stringBytes);

                System.out.println("Substring:" + substring);
                if(i == 8){
                    k0 = new BigInteger(substring, 16);
                }
                else if(i == 16){
                    k1 = new BigInteger(substring, 16);
                } else if(i == 24){
                    k2 = new BigInteger(substring, 16);
                } else if(i == 32){
                    k3 = new BigInteger(substring, 16);
                }

            }


        }
        BigInteger[] keyArray = {k0, k1, k2, k3};
        for(BigInteger k: keyArray){

        }


//        System.out.println("k0: " + k0);
//        System.out.println("k1: " + k1);
//        System.out.println("k2: " + k2);
//        System.out.println("k3: " + k3);
//
//        System.out.println("---------");
//
//        System.out.println("k0: " + k0.toString(16));
//        System.out.println("k1: " + k1.toString(16));
//        System.out.println("k2: " + k2.toString(16));
//        System.out.println("k3: " + k3.toString(16));



        char message[] = new char[16];

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
                //System.out.println("i is :" + i);
                message[msgPointer] = (char) i;
                msgPointer ++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //split message into subarrays
        String s = "";
        for(char c : message){
            s += String.valueOf(c);
        }

        BigInteger[] messageArrays = string2BigInts(s);


        printArrays(messageArrays);
        //do encryption
        for(int j = 0; j < 4; j++){
            messageArrays[j] = messageArrays[j].xor(keyArray[j]);
        }
        printArrays(messageArrays);
        mmbFunc(messageArrays);

        printArrays(messageArrays);


    }
    public static byte[] flipBytes(byte[] bytes){
        byte[] newArray = new byte[bytes.length];
        for(int i =0; i < bytes.length; i++){
            newArray[bytes.length-1-i] = bytes[i];
        }
        return newArray;
    }
    public static void printArrays(BigInteger[] messageArrays){
        System.out.println("------------");
        System.out.println(messageArrays[0].toString(16));
        System.out.println(messageArrays[1].toString(16));
        System.out.println(messageArrays[2].toString(16));
        System.out.println(messageArrays[3].toString(16));
        System.out.println("------------");
    }

    public static BigInteger[] string2BigInts(String s){

        //System.out.println("in s2bi s is: " + s);
        BigInteger x0 = new BigInteger(s.substring(0,4).getBytes(StandardCharsets.US_ASCII));
        BigInteger x1 = new BigInteger(s.substring(4,8).getBytes(StandardCharsets.US_ASCII));
        BigInteger x2 = new BigInteger(s.substring(8,12).getBytes(StandardCharsets.US_ASCII));
        BigInteger x3 = new BigInteger(s.substring(12,16).getBytes(StandardCharsets.US_ASCII));

        BigInteger[] messageArrays = {x0, x1, x2, x3};
        return messageArrays;
    }

    public static int xOr(int a, int b){
        return a^b;
    }
    public static int multMod(int a, int b, int m){
        return (a*b)%m;
    }
    public static BigInteger[] mmbFunc(BigInteger[] message){
        //step 1
        for(int i = 0; i < 4; i++){
            message[i] = message[i].multiply(constArray[i]).mod(modVal);
        }
        //step 2
        if(message[0].mod(new BigInteger("2", 10)) != BigInteger.ZERO){
            message[0] = message[0].xor(C);
        }
        if(message[3].mod(new BigInteger("2", 10)) != BigInteger.ZERO){
            message[3] = message[3].xor(C);
        }
        //step 3
        BigInteger temp0 = message[0].xor(message[2]);
        BigInteger temp1 = message[1].xor(message[3]);
        message[0] = message[0].xor(temp1);
        message[1] = message[1].xor(temp0);
        message[2] = message[2].xor(temp1);
        message[3] = message[3].xor(temp0);

        return message;
    }



}
