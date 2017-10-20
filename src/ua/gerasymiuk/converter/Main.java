package ua.gerasymiuk.converter;

import java.util.Scanner;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        while (!str.equals("q")) {
            InToPostConverter converter = new InToPostConverter(str);
            System.out.println(converter.getPostfixExpression());
            System.out.println("result="+converter.getResult());
            str = in.nextLine();
        }
    }
}
