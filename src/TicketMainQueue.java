import java.util.*;
import java.io.*;

public class TicketMainQueue{
    public static void main(String[] args) throws IOException
    {
        Scanner inputText=new Scanner(System.in);
        Scanner inputNum=new Scanner(System.in);
        
        Queue ticketList = new Queue();
        Queue tempTicketList = new Queue();
        
        String filePath = "ticketdata.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        
        Ticket data;
        String line;
        
        try{
            while ((line = reader.readLine()) != null) {

            StringTokenizer tokenizer = new StringTokenizer(line,"*");
            
            String ticketID = tokenizer.nextToken();
            String ticketType = tokenizer.nextToken();
            double ticketPrice =  Double.parseDouble(tokenizer.nextToken());
            int seatNum = Integer.parseInt(tokenizer.nextToken());
            String custName = tokenizer.nextToken();
            String custPhoneNum = tokenizer.nextToken();
            String custIC = tokenizer.nextToken();
            int custAge = Integer.parseInt(tokenizer.nextToken());
            int ticketQuantity = Integer.parseInt(tokenizer.nextToken());

            data = new Ticket(ticketID,ticketType,ticketPrice,seatNum,custName,custPhoneNum,custIC,custAge,ticketQuantity);
            ticketList.enqueue(data); 
        }
        reader.close();
        }
        catch(Exception e){
            System.out.println("Exception catched");
        }
        
        
        boolean check = false;
        while (check == false){
            System.out.println("Please Enter Process Number: ");
            System.out.println("[4] - UPDATE DATA");
            System.out.println("[5] - SEARCH DATA");
            System.out.println("[6] - EXIT");
            System.out.print("Enter choice: ");
            int choice=inputNum.nextInt();
            
            if(choice == 4){
              boolean foundIC = false;  
               do{
                System.out.println("Enter IC Numbers to update your data: ");
                String yourIC =inputText.nextLine();
                
                while (!ticketList.isEmpty()){
                  data = (Ticket)ticketList.dequeue();
                    if(data.getCustIC().equalsIgnoreCase(yourIC)){
                    foundIC = true;   
                    boolean update = true;
                    
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
                       System.out.println("Choose New Ticket Type: ");
                       System.out.println("[1] VIP Ticket");
                       System.out.println("[2] Premium Ticket");
                       System.out.println("[3] Standard Ticket");
                       int newTicketType =inputNum.nextInt();
                       if(newTicketType == 1){
                           data.setTicketType("VIP Ticket");
                           data.setTicketPrice(300.00);
                       }
                       
                       if(newTicketType == 2){
                           data.setTicketType("Premium Ticket");
                           data.setTicketPrice(200.00);
                       }
                       
                       if(newTicketType == 3){
                           data.setTicketType("Standard Ticket");
                           data.setTicketPrice(150.00);
                       }
                       
                    }
                    
                    else if(updChoice == 2){
                       System.out.println("Enter New Seat Number: ");
                       int newSeatNum =inputNum.nextInt();
                       data.setSeatNum(newSeatNum);
                       
                    }
                    
                    else if(updChoice == 3){
                       System.out.println("Enter New Name: ");
                       String newName =inputText.nextLine();
                       data.setCustName(newName);
                       
                    }
                    
                    else if(updChoice == 4){
                       System.out.println("Enter New Phone Number: ");
                       String newPhoneNum =inputText.nextLine();
                       data.setCustPhoneNum(newPhoneNum);
                       
                    }
                    
                    else if(updChoice == 5){
                       System.out.println("Enter New IC Numbers: ");
                       String newICNum =inputText.nextLine();
                       data.setCustIC(newICNum);
                       
                    }
                    
                    else if(updChoice == 6){
                       System.out.println("Enter New Age: ");
                       int newAge =inputNum.nextInt();
                       data.setCustAge(newAge);
                       
                    }
                    
                    else if(updChoice == 7){
                       System.out.println("Enter New Ticket Quanitity: ");
                       int newQuantity =inputNum.nextInt();
                       data.setTicketQuantity(newQuantity);
                       
                    }
                    
                    else{
                        System.out.println("Wrong Input,Please enter again: ");
                     }
                     
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
                
                  tempTicketList.enqueue(data);  
                }
                
                while(!tempTicketList.isEmpty()){
                    data = (Ticket)tempTicketList.dequeue();
                    ticketList.enqueue(data);
                }
                
              }while(foundIC == false);
            }
            
            else if(choice == 5){
                System.out.println("Enter Your IC Numbers to search your information: ");
                String SearchIC = inputText.nextLine();
                boolean ICfound = false;
                while(!ticketList.isEmpty()){
                    data = (Ticket)ticketList.dequeue();
                    if(data.getCustIC().equalsIgnoreCase(SearchIC)){
                        ICfound = true;
                        System.out.println("Your Ticket Information:");
                        System.out.println(data.toString());
                    }
                    tempTicketList.enqueue(data);
                }
                
                if(ICfound == false){
                    System.out.println("Your IC numbers is not in the data");
                }
                
                while(!tempTicketList.isEmpty()){
                    data = (Ticket)tempTicketList.dequeue();
                     ticketList.enqueue(data);
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
                check = false;
                System.out.print("enter 1 for clear the terminal: ");
                int ans1 = inputNum.nextInt();
                if(ans1 == 1)
                {
                    System.out.print("\u000C");
                    System.out.println("The system have been cleared");
                }
                System.out.println();
            }
            else
            {
                check = true;
            }
        }
    }
}