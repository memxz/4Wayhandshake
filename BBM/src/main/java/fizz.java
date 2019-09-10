package main.java;

public class fizz {


/*for x in range(1,101):
    if x % 15 == 0:
        print "FizzBuzz" # Catch multiples of both first.
    elif x % 3 == 0:
        print "Fizz"
    elif x % 5 == 0:
        print "Buzz"
    else:
        print x
	*/
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int x = 1; x<101; x++ ){
			
			if(x%3==0)
				System.out.println("Fizz");
			else if(x%5==0)
				System.out.println("Buzz");
			else if(x%15==0)
				System.out.println("FizzBuzz");
			else
				System.out.println(+x);
			
		}

	}

}
