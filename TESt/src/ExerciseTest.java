import static org.junit.Assert.*;

import org.junit.Test;

public class ExerciseTest {

    private static int errors = 0;  
    
    
    public static void main(String[] args)  
    {  
        try  
        {  
            add();  
        }  
        catch (IllegalAccessException e)  
        {  
            errors++;  
            e.printStackTrace();  
        }  
        if (errors > 0)  
        {  
            System.out.println("һ����" + errors + "����");  
        }  
    }  
  
  
    private static void add() throws IllegalAccessException  
    {  
        Double result = Exercise.add(1, 2);  
        if (result != 3)  
        {  
            throw new IllegalAccessException("����ûͨ��������");  
        }  
    }  
  
  
} 