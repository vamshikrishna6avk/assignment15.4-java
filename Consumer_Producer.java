package consumerProducerProblem;//created package as consumerProducerProblem

import java.util.LinkedList;//import keyword used to import user defined packages into our java source file
                            //java.util contaions collection of frameworks,legacy collections,data and time facilities
                          //Linked list is a linear data structure where each element is separate object

public class Consumer_Producer {//created class as Consumer-producer
	                           //classes are the basics of opps(object oriented programming)
	 public static void main(String[] args) //Here public is a access modifier which defines who can access this method
		//Here static a keyword which identifies class related thing
		//void is used to define return type of the method,void means method wont return any value
		//main is name of method
		//declares method String[]
		//String[]args means arguments will be passed into main method and says that main() as a parameter
             throws InterruptedException
{
// Object of a class that has both produce and consumemethods
final PC pc = new PC();

// Create producer thread
Thread t1 = new Thread(new Runnable()//thread is a light weight processes within a process
                                    //implementing runnable interface
{
 @Override
 public void run() //Here public is a access modifier which defines who can access this method
	//void is used to define return type of the method,void means method wont return any value

 {
     try
     {
         pc.produce();//produce pc
     }
     catch(InterruptedException e)
     {
         e.printStackTrace();
     }
 }
});

// Create consumer thread
Thread t2 = new Thread(new Runnable()
{
 @Override
 public void run() //Here public is a access modifier which defines who can access this method
	//void is used to define return type of the method,void means method wont return any value

 {
     try//throwing statement
     {
         pc.consume();
     }
     catch(InterruptedException e)//catches the expection
                                  //exception thrown by statement in try block the catch block examines one by one
     //interruptedException
     {
         e.printStackTrace();
     }
 }
});

// Start both threads
t1.start();
t2.start();

// t1 finishes before t2
t1.join();
t2.join();
}

// This class has a list, producer (adds items to list and consumer (removes items).
public static class PC //Here public is a access modifier which defines who can access this method
                    //Here static a keyword which identifies class related thing


{
// Create a list shared by producer and consumer Size of list is 2.
LinkedList<Integer> list = new LinkedList<>();
int capacity = 2;

// Function called by producer thread
public void produce() throws InterruptedException //Here public is a access modifier which defines who can access this method
//void is used to define return type of the method,void means method wont return any value

{
 int value = 0;//initializing value
 while (true)//statement true
 {
     synchronized (this)//synchronized
     {
         // producer thread waits while list
         // is full
         while (list.size()==capacity)
             wait();

         System.out.println("Producer-"
                                       + value);//system is used to return code
         //out is a static member
       	//Println is used to print text  and gives output 

         // To insert the jobs in the list
         list.add(value++);

         // notifies the consumer thread that now it can start consuming
         notify();

         // makes the working of program easier
         // to  understand
         Thread.sleep(2000);
     }
 }
}

// Function called by consumer thread
public void consume() throws InterruptedException //Here public is a access modifier which defines who can access this method
//void is used to define return type of the method,void means method wont return any value

{
 while (true)
 {
     synchronized (this)
     {
         // consumer thread waits while list is empty
         while (list.size()==0)
             wait();

         //to retrieve the first job in the list
         int val = list.removeFirst();

         System.out.println("Consumer-"+ val);//system is used to return code
         //out is a static member
       	//Println is used to print text  and gives output 

         // Wake up producer thread
         notify();

         // and sleep
         Thread.sleep(1000);
     }
 }
}
}

}