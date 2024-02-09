import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

import tools.Matrix;
import tools.parse;
import tools.Sequence;
import tools.Brute;

public class Main {
    
   
    public static void main(String[] args) {
        //Deklarasi awal
        Scanner scanner = new Scanner(System.in);


        System.out.println("1. TXT:  ");
        System.out.println("2. COMMAND LINE:  ");
        System.out.println("3. EXIT  ");
        System.out.print("Pilih Input :  ");
        int cmdMenu = scanner.nextInt();
        scanner.nextLine();
        if (cmdMenu == 1)
        {
            parse.inputTxt(scanner);
        }
        else if(cmdMenu == 2)
        {
            parse.inputCli(scanner);

        }
        else{
            scanner.close();
        }
        

        
    }
}
