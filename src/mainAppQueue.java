import java.util.*;
import java.io.*;

public class mainAppQueue
{
    public static void main(String[] args)throws IOException
    {
        Scanner inputText=new Scanner(System.in); 
        Scanner inputNum=new Scanner(System.in);
         
        Queue ticketList=new Queue();
        Queue tempTick=new Queue();
        Queue removeTick=new Queue();
        
        try
        {
            FileReader fr=new FileReader("ticketdata.txt");
            BufferedReader br=new BufferedReader(fr);
            
            Ticket tick=new Ticket();
            String s=null;
            
        
            int count=0;
            s=br.readLine();
            while (s!=null)
            {
                StringTokenizer st=new StringTokenizer(s,"*");
                String ticketID=st.nextToken();
                String ticketType=st.nextToken();
                double ticketPrice=Double.parseDouble(st.nextToken());
                int seatNum=Integer.parseInt(st.nextToken());
                String custName=st.nextToken();
                String custPhoneNum=st.nextToken();
                String custIC=st.nextToken();
                int custAge=Integer.parseInt(st.nextToken());
                int ticketQuantity=Integer.parseInt(st.nextToken());
                
                tick=new Ticket(ticketID,ticketType,ticketPrice,seatNum,custName,custPhoneNum,custIC,custAge,ticketQuantity);
                ticketList.enqueue(tick);
                
                s=br.readLine();
            }
        }
        catch(EOFException eof)
        {
            System.out.println("Problem: "+eof.getMessage());
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Problem: "+e.getMessage());
        }
        catch(IOException ioe)
        {
            System.out.println("Problem: "+ioe.getMessage());
        }
        
        
        
        boolean status=true;
        while(status==true)
        {
            System.out.println("Welcome to Ticketing System :D");
            System.out.println("Please enter the desired choice:");
            System.out.println("[1] - INSERT NEW DATA");
            System.out.println("[2] - REMOVE EXISTING DATA");
            System.out.println("[3] - DISPLAY DATA");
            System.out.println("[4] - UPDATE DATA");
            System.out.println("[5] - SEARCH DATA");
            System.out.println("[6] - EXIT");
            
            

            int choice=inputNum.nextInt();
            if(choice==1)   // Inserting new data     
            {
                String ans="y";
                while (ans.equalsIgnoreCase("y"))
                {

                    System.out.println("Enter the data for the new customer:");
                
                    System.out.println("Enter the Ticket ID:");
                    String ticketID=inputText.nextLine();
                    System.out.println("Enter the Ticket Type:");
                    String ticketType=inputText.nextLine();
                    System.out.println("Enter the ticket price:");
                    double ticketPrice=inputNum.nextDouble();
                    System.out.println("Enter the seat number:");
                    int seatNum=inputNum.nextInt();
                    System.out.println("Enter the customer name:");
                    String custName=inputText.nextLine();
                    System.out.println("Enter the customer phone number:");
                    String custPhoneNum=inputText.nextLine();
                    System.out.println("Enter the customer IC number:");
                    String custIC=inputText.nextLine();
                    System.out.println("Enter the customer age:");
                    int custAge=inputNum.nextInt();
                    System.out.println("Enter the ticket quantity:");
                    int ticketQuantity=inputNum.nextInt();
                    

                    
                    Ticket tick=new Ticket(ticketID, ticketType, ticketPrice, seatNum, custName, custPhoneNum, custIC, custAge, ticketQuantity);
                    
                    
                    ticketList.enqueue(tick);
                    
                    System.out.println("Do you want to add another data?:");
                    ans=inputText.nextLine();
                }
            }
            else if(choice==2) //removing existing data
            {
                String ans="y";
                while(ans.equalsIgnoreCase("y"))
                {
                    System.out.println("---[REMOVAL]---");
                    System.out.println("Please enter the Ticket ID to be removed:"); //remove the Ticket details based on Ticket ID
                    String ticketID=inputText.nextLine();
                    
                    
                    while(!ticketList.isEmpty())
                    {
                        Ticket tick=(Ticket)ticketList.dequeue(); 
                        if(tick.getTicketID().equalsIgnoreCase(ticketID))
                        {
                            removeTick.enqueue(tick);
                        }
                        else
                        {
                            tempTick.enqueue(tick);
                        }
                    }
                    //inserting back data from tempTick to ticketList
                    while(!tempTick.isEmpty())
                    {
                        ticketList.enqueue(tempTick.dequeue());
                    }
                    
                    System.out.println("Do you want to remove another data?");
                    ans=inputText.nextLine();
                }
            }
            else if (choice==3) // traversing the data (display)
            {
                    System.out.println("Showing all the data:");
                    while(!ticketList.isEmpty())
                    {
                        Ticket tick=(Ticket)ticketList.dequeue();
                        
                        System.out.println(tick.toString());
                        tempTick.enqueue(tick);
                    }
                    
                    //inserting back data from tempTick to ticketList
                    while(!tempTick.isEmpty())
                    {
                        ticketList.enqueue(tempTick.dequeue());
                    }
                    
            }
 
            else if(choice == 4){
              boolean foundIC = false;  
               do{
                System.out.println("Enter IC Numbers to update your data: ");
                String yourIC =inputText.nextLine();
                
                while (!ticketList.isEmpty()){
                  Ticket tick = (Ticket)ticketList.dequeue();
                    if(tick.getCustIC().equalsIgnoreCase(yourIC)){
                    foundIC = true;   
                    boolean update = true;
                
                   do{
                    boolean correctUpd=false;
                    do{
                    System.out.println("Choose which do you want to update:");
                    System.out.println("[1] - Ticket Type");
                    System.out.println("[2] - Seat Number");
                    System.out.println("[3] - Name");
                    System.out.println("[4] - Phone Number");
                    System.out.println("[5] - IC Number");
                    System.out.println("[6] - Age");
                    System.out.println("[7] - Ticket Quantity");
                    int updChoice=inputNum.nextInt();
                        
                    if(updChoice == 1){
                        correctUpd=true;
                       System.out.println("Choose New Ticket Type: ");
                       System.out.println("[1] VIP Ticket");
                       System.out.println("[2] Premium Ticket");
                       System.out.println("[3] Standard Ticket");
                       int newTicketType =inputNum.nextInt();
                       if(newTicketType == 1){
                           tick.setTicketType("VIP Ticket");
                           tick.setTicketPrice(300.00);
                       }
                       
                       if(newTicketType == 2){
                           tick.setTicketType("Premium Ticket");
                           tick.setTicketPrice(200.00);
                       }
                       
                       if(newTicketType == 3){
                           tick.setTicketType("Standard Ticket");
                           tick.setTicketPrice(150.00);
                       }
                       
                    }
                    
                    else if(updChoice == 2){
                        correctUpd=true;
                       System.out.println("Enter New Seat Number: ");
                       int newSeatNum =inputNum.nextInt();
                       tick.setSeatNum(newSeatNum);
                       
                    }
                    
                    else if(updChoice == 3){
                        correctUpd=true;
                       System.out.println("Enter New Name: ");
                       String newName =inputText.nextLine();
                       tick.setCustName(newName);
                       
                    }
                    
                    else if(updChoice == 4){
                        correctUpd=true;
                       System.out.println("Enter New Phone Number: ");
                       String newPhoneNum =inputText.nextLine();
                       tick.setCustPhoneNum(newPhoneNum);
                       
                    }
                    
                    else if(updChoice == 5){
                        correctUpd=true;
                       System.out.println("Enter New IC Numbers: ");
                       String newICNum =inputText.nextLine();
                       tick.setCustIC(newICNum);
                       
                    }
                    
                    else if(updChoice == 6){
                        correctUpd=true;
                       System.out.println("Enter New Age: ");
                       int newAge =inputNum.nextInt();
                       tick.setCustAge(newAge);
                       
                    }
                    
                    else if(updChoice == 7){
                        correctUpd=true;
                       System.out.println("Enter New Ticket Quanitity: ");
                       int newQuantity =inputNum.nextInt();
                       tick.setTicketQuantity(newQuantity);
                       
                    }
                    
                    else{
                        System.out.println("Wrong Input,Please enter again: ");
                     }
                   }while(correctUpd == false);
                    
                   System.out.println("Do you want to update another data? [1/2]");
                   int UpdateAgain =inputNum.nextInt();
                   if(UpdateAgain == 1){
                        update = true;
                    }
                    else{
                        update = false;
                    }
                    
                   }while(update == true);
                  }
                
                  tempTick.enqueue(tick);  
                }
                
                while(!tempTick.isEmpty()){
                    Ticket tick = (Ticket)tempTick.dequeue();
                    ticketList.enqueue(tick);
                }
                
              }while(foundIC == false);
            }
            
            else if(choice == 5){
                System.out.println("Enter Your IC Numbers to search your information: ");
                String SearchIC = inputText.nextLine();
                boolean ICfound = false;
                while(!ticketList.isEmpty()){
                    Ticket tick = (Ticket)ticketList.dequeue();
                    if(tick.getCustIC().equalsIgnoreCase(SearchIC)){
                        ICfound = true;
                        System.out.println("Your Ticket Information:");
                        System.out.println(tick.toString());
                    }
                    tempTick.enqueue(tick);
                }
                
                if(ICfound == false){
                    System.out.println("Your IC numbers is not in the data");
                }
                
                while(!tempTick.isEmpty()){
                    Ticket tick = (Ticket)tempTick.dequeue();
                    ticketList.enqueue(tick);
                }
            }
            
            else if(choice == 6){
                System.out.println("You've exited the system.");
                break;
            }
            
            else{
                System.out.println("You enter the wrong code,enter again");
            }
            
            System.out.println("\nProceed ?");
            System.out.println("\n[1] YES\n[2] NO");
            int checker = inputNum.nextInt();
            if (checker == 1)
            {
                status = true;
                System.out.print("\u000C");
            }
            else 
            {
                status = false;
                System.out.print("enter 1 for clear the terminal: ");
                int ans1 = inputNum.nextInt();
                if(ans1 == 1)
                {
                    System.out.print("\u000C");
                    System.out.println("The system have been cleared");
                }
            }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            /*
            System.out.println("Do you wish to continue?");
            System.out.println("Press Y to continue");
            System.out.println("or");
            System.out.println("Press N to exit");
            String answer=inputText.nextLine();
            
            if(answer.equalsIgnoreCase("Y"))
            {
                status=true;
            }
            else if(answer.equalsIgnoreCase("N"))
            {
                status=false;
            }*/
        }
        
        
        
        
        
        
        
        
        
    }
        
        
        
        
        
        
        
                
}
