package main;
import java.util.Random;

public class randomnumber
{

public static void main(String[] args) 
{

    Random numberGenerator = new Random ();
    int[] arrayOfGenerator = new int[100000];
    int[] countOfArray     = new int[5];
    int count;

    for (int countOfGenerator = 0; countOfGenerator < 100000; countOfGenerator++)
    {
        count = numberGenerator.nextInt(5);
        countOfArray[count]++;
     //   arrayOfGenerator[countOfGenerator] = count;
    }

  /*  int countOfNumbersOnLine = 0;
    for (int countOfOutput = 0; countOfOutput < 100; countOfOutput++)
    {
        if (countOfNumbersOnLine == 10)
        {
            System.out.println("");
            countOfNumbersOnLine = 0;
            countOfOutput--;
        }
        else
        {
            if (arrayOfGenerator[countOfOutput] == 10)
            {
                System.out.print(arrayOfGenerator[countOfOutput] + "  ");
                countOfNumbersOnLine++;
            }
            else
            {
                System.out.print(arrayOfGenerator[countOfOutput] + "   ");
                countOfNumbersOnLine++;
            }
        }
    }
*/
    System.out.println("");
    System.out.println("");

// This section

    for (int countOfNumbers = 0; countOfNumbers < countOfArray.length; countOfNumbers++)
        System.out.println("The number " + (countOfNumbers + 0) + " occurs " + countOfArray[countOfNumbers] + " times.");

    System.out.println("");

   /* for (int countOfNumbers = 0; countOfNumbers < countOfArray.length; countOfNumbers++)
    {
        if (countOfNumbers != 9)
                System.out.print((countOfNumbers + 1) + "   ");
        else
                System.out.print((countOfNumbers + 1) + "  ");

        for (int a = 0; a < countOfArray[countOfNumbers]; a++)
        {
            System.out.print("*");
        }
        System.out.println("");
    }

 // To this section

    System.out.println("");

    int max = 0;
    int test = 0;
    for (int counter = 0; counter < countOfArray.length; counter++)
    {
        if (countOfArray[counter] > max)
        {
            max = countOfArray[counter];
            test = counter + 1;
        }
    }

    System.out.println("The number that appears the most is " + test);
*/
}
}