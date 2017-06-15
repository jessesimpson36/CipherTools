package com.example.jesse.ciphertools;

import java.util.Scanner;

/**
 * this translates to and from different ciphers
 */
public class BasicCiphers {

    /**
     * the alphabet
     */
    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    /**
     * the number of letters in the alphabet.
     */
    public static final int LETTERS_IN_ALPHABET = 26;

//    /**
//     * this is the main program.
//     * @param args the type of function to run.
//     */
//    public static void main(String[] args) {
//
//        // prompts the user for the cipher
//        System.out.println("What would you like to translate?");
//
//        // gets the input
//        Scanner input = new Scanner( System.in );
//
//        // processes the encoded message.
//        switch( input.next() ){
//            case "atbash":
//                System.out.println( translateAtbash(input.nextLine().toUpperCase()));
//                break;
//
//            case "CaesarianShift":
//                System.out.println( caesarianShift(input.nextInt(), input.nextLine()) );
//                break;
//
//            case "skip":
//                System.out.println(skip(input.nextInt(), input.nextInt(), input.nextLine()));
//                break;
//        }
//    }

    /**
     * translates atbash encrypted message.
     * @param message the string
     * @return the translated string.
     */
    public static String translateAtbash( String message ){
        // creates a string builder for returned value.
        StringBuilder sb = new StringBuilder();

        // iterates through the message and turns every letter
        // into the opposite as the atbash cipher would.
        for( char c : message.toUpperCase().toCharArray() ){
            if( Character.isLetter(c) ) {
                int newChar = ('Z' - c) + 'A';
                sb.append( (char) newChar );
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * shifts the alphabet an arbitrary amount towards the right
     * (positive) or left (negative)
     * @param message the message to translate
     * @param shift the number to shift.
     * @return the translated string.
     */
    public static String caesarianShift( int shift, String message ){
        StringBuilder returned = new StringBuilder();

        // iterates through the message and shifts the alphabet left or right.
        for ( char c : message.toUpperCase().toCharArray() ){
            if ( Character.isLetter(c) ) {
                int newChar = ((c + shift - (int)'A') % LETTERS_IN_ALPHABET) + (int)'A';
                // if the value is less than A, there will be garbage output.
                if (newChar < (int) 'A') {
                    newChar += LETTERS_IN_ALPHABET;
                }
                returned.append((char) newChar);
            } else {
                returned.append(c);
            }
        }
        return returned.toString();
    }

    /**
     * this makes the message decode by skipping some amount of letters
     * and wrapping the message around until all characters are read.
     * @param bypass the amount of letters to bypass
     * @param skipVal the amount of spaces to skip
     * @param message the message to encode or decode.
     * @return the new string, decoded or encoded.
     */
    public static String skip( int bypass, int skipVal, String message ){

        int length = message.length();
        char[] returned = message.toCharArray();

        int index = bypass;
        for( int i = 0; i < length; i++){
            returned[index] = message.charAt(i);
            index += skipVal;
            index = index % length;
        }
        String b = new String(returned);
        return b;

    }

    /**
     * takes a message either in morse code or from morse code and translates it the other way.
     * @param isMorse whether it is morse or not.
     * @param message the input
     * @return
     */
    public static String morseCode( boolean isMorse, String message ){
        String[] alpha = { " ", "1", "2", "3", "4", "5", "6", "7", "8",
                "9", "0","b", "c","f","h", "j", "l",  "p", "q", "v", "x",
                "y", "z", "u", "w", "g", "d", "k", "o", "r", "s", "a", "i",
                "m", "n", "t", "e" };

        String[] dottie = { "/ ", ".---- ", "..--- ", "...-- ", "....- ", "..... ",
                "-.... ", "--... ", "---.. ", "----. ", "----- ",

                //b      c        f        h       j      l         p      q       v         x      y       z
                "-... ","-.-. ", "..-. ",".... ",".--- ",".-.. ",".--. ","--.- ", "...- ","-..- ","-.-- ","--.. ",

                //u       w        g      d        k      o     r         s
                "..- ",  ".-- ", "--. ", "-.. ", "-.- ","--- ",".-. ", "... ",
                //a      i      m      n
                ".- ", ".. ", "-- ", "-. ",
                // t      e
                  "- ", ". " };

        if( !isMorse ){
            for( int i = 0; i < alpha.length; i++ ){
                message = message.toLowerCase().replaceAll(alpha[i], dottie[i]);
            }
        } else {
            message = message.trim() + " ";
            for( int i = 0; i < alpha.length; i++ ){
                message = message.toLowerCase().replaceAll(dottie[i], alpha[i]);
            }
        }
        return message;
    }


}
