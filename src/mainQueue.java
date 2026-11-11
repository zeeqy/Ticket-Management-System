import java.util.*;
import java.io.*;

public class mainQueue
{
    public static void main(String[] args) throws IOException
    {        
        Scanner in = new Scanner (System.in);
        Scanner inLine = new Scanner (System.in);
        
        Queue custList = new Queue();
        Queue shipmentList = new Queue();
        Queue itemList = new Queue();
        
        String filePath = "customerData.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        
        String line;
        while ((line = reader.readLine()) != null) {

            StringTokenizer tokenizer = new StringTokenizer(line,";");
            
            String custID = tokenizer.nextToken();
            String custName = tokenizer.nextToken();
            String custContact =  tokenizer.nextToken();  
            String custAddress = tokenizer.nextToken();

            Customer cust = new Customer(custID,custName,custContact,custAddress);
            custList.enqueue(cust); 
        }
        reader.close();
        
        filePath = "itemData.txt";
        reader = new BufferedReader(new FileReader(filePath));
        while ((line = reader.readLine()) != null) {

            StringTokenizer tokenizer = new StringTokenizer(line,";");
            
            String itemID = tokenizer.nextToken();
            char itemType = tokenizer.nextToken().charAt(0);
            int itemQty = Integer.parseInt(tokenizer.nextToken());
            

            Item item= new Item(itemID,itemType,itemQty);
            itemList.enqueue(item); 
        }
        reader.close();
        
        filePath = "shipmentData.txt";
        reader = new BufferedReader(new FileReader(filePath));
        while ((line = reader.readLine()) != null) {

            StringTokenizer tokenizer = new StringTokenizer(line,";");
            
            String shipmentID = tokenizer.nextToken();
            String shipmentStatus = tokenizer.nextToken();
            String shipmentOrigin =  tokenizer.nextToken();  
            double shipmentWeight = Double.parseDouble(tokenizer.nextToken());

            Shipment shp = new Shipment(shipmentID, shipmentStatus, shipmentOrigin, shipmentWeight);
            shipmentList.enqueue(shp); 
        }
        reader.close();
        
        System.out.println("===============WELCOME TO CARGO MANAGEMENT SYSTEM===============");
        //SELECT PROCESS
        boolean check = false;
        while (check == false)
        {
            System.out.println("Please Enter Process Number: ");
            System.out.println("[1] - ENTER NEW DATA");
            System.out.println("[2] - REMOVE DATA");
            System.out.println("[3] - SHOW CURRENT DATA");
            System.out.println("[4] - UPDATE DATA");
            System.out.println("[5] - SEARCH DATA");
            System.out.println("[6] - EXIT");
            System.out.print("Enter choice: ");
            int choice = in.nextInt();
            if (choice == 1)
            {
                //ENTER NEW DATA
                System.out.println("\nINSERTION");
                System.out.println("Insert new data");
                System.out.print("Enter customer/shipment/item ID:");
                String custID = inLine.nextLine();
                String shipID;
                String itemID;
                itemID = shipID = custID;
                System.out.println("--------CUSTOMER DETAIL---------");
                System.out.print("Enter the Customer's Name: ");
                String custName = inLine.nextLine();
                System.out.print("Enter the Customer's Phone Number: ");
                String custNum = inLine.nextLine();
                System.out.print("Enter the Customer's Address: ");
                String custAddress = inLine.nextLine();
                
                System.out.println("-----------ITEM DETAIL---------");
                System.out.println("[1] Fragile");
                System.out.println("[2] Non-Fragile");
                System.out.print("Enter item type: ");
                int itemType = in.nextInt();
                while(itemType != 1 && itemType != 2) {
                    System.out.println("Enter the correct input: ");
                    itemType = in.nextInt(); 
                }
                System.out.print("Enter the Item's Quantity: ");
                int itemQty = in.nextInt();
                
                System.out.println("---------SHIPMENT DETAIL--------");
                System.out.print("Enter the Shipment's Status (ARRIVED OR ON BOARD): ");
                String shpStatus = inLine.nextLine();
                System.out.print("Enter the Shipment's Place of Origin: ");
                String shpOrigin = inLine.nextLine();
                System.out.print("Enter the Shipment's Weight in KG: ");
                double shpWeight = in.nextDouble();
            
                Customer cust = new Customer(custID, custName, custNum, custAddress);
                Item item = new Item(itemID, itemType, itemQty);
                Shipment shp = new Shipment(shipID, shpStatus, shpOrigin, shpWeight);
            
                custList.enqueue(cust);
                itemList.enqueue(item);
                shipmentList.enqueue(shp);
            }
            
            else if (choice == 2)
            {
                //REMOVING
                System.out.println("\nREMOVAL");
                System.out.print("Enter customer/item/shipment ID: ");
                String custIDtemp = inLine.nextLine();
                System.out.println("Are you sure to remove " + custIDtemp +" data?");
                System.out.print("Yes or No: ");
                String ans = inLine.nextLine();
                Queue custTemp = new Queue();
                Queue itemTemp = new Queue();
                Queue shipmentTemp = new Queue();
                if(ans.equalsIgnoreCase("yes"))
                {
                    while (!custList.isEmpty())
                    {
                        Customer cust = (Customer) custList.dequeue();
                        if(!(cust.getCustomerID().equalsIgnoreCase(custIDtemp))) 
                        {
                            custTemp.enqueue(cust);
                        }
                    }
                    while (!custTemp.isEmpty())
                    {
                        custList.enqueue(custTemp.dequeue());
                    }
                    while (!itemList.isEmpty())
                    {
                        Item item = (Item) itemList.dequeue();
                        if(!(item.getItemID().equalsIgnoreCase(custIDtemp))) 
                        {
                            itemTemp.enqueue(item);
                        }
                    }
                    while(!itemTemp.isEmpty())
                    {
                        itemList.enqueue(itemTemp.dequeue());
                    }
                    while (!shipmentList.isEmpty())
                    {
                        Shipment shp = (Shipment) shipmentList.dequeue();
                        if(!(shp.getShipmentID().equalsIgnoreCase(custIDtemp)))
                        {
                            shipmentTemp.enqueue(shp);
                        }
                    }
                    while(!shipmentTemp.isEmpty())
                    {
                        shipmentList.enqueue(shipmentTemp.dequeue());
                    }
                }
                else
                {
                    System.out.println("You cancel the removal");
                }
            }
            else if (choice == 3)
            {
                //TRAVERSING
                System.out.println("\nTRAVERSING");
                Queue tempC = new Queue();
                Queue tempI = new Queue();
                Queue tempS = new Queue();
                System.out.println("Display All Records");
                System.out.println("------------------------------------------");
                if(custList.isEmpty())
                {
                    System.out.println("No record of data");
                }
                while(!custList.isEmpty())
                {
                    Customer cust = (Customer) custList.dequeue();
                    Item item = (Item)itemList.dequeue();
                    Shipment shp = (Shipment) shipmentList.dequeue();
                    System.out.println(cust.toString());
                    tempC.enqueue(cust);
                    
                    System.out.println(item.toString());
                    tempI.enqueue(item);
            
                    System.out.println(shp.toString());
                    tempS.enqueue(shp);
                    System.out.println();
                }
                while(!tempC.isEmpty())
                {
                    custList.enqueue(tempC.dequeue());
                }
                while(!tempI.isEmpty())
                {
                    itemList.enqueue(tempI.dequeue());
                }
                while(!tempS.isEmpty())
                {
                    shipmentList.enqueue(tempS.dequeue());
                }
            }
        
            else if (choice == 4)
            {
                //UPDATING
                System.out.println("\nUPDATING");
                System.out.print("Enter Customer/Shipment/Item ID that want to be updated: ");
                String custIDtemp = inLine.nextLine();
                Queue custTemp = new Queue();
                Queue itemTemp = new Queue();
                Queue shpTemp = new Queue();
                int cond = 1;
                Customer cust = (Customer) custList.dequeue();
                Item item = (Item) itemList.dequeue();
                Shipment shp = (Shipment) shipmentList.dequeue();
                while(cust != null)
                {
                    if(cust.getCustomerID().equalsIgnoreCase(custIDtemp)&& item.getItemID().equalsIgnoreCase(custIDtemp) && shp.getShipmentID().equalsIgnoreCase(custIDtemp)){
                        
                        
                        System.out.println("Update the following data: ");
                        System.out.print("CUSTOMER/ITEM/SHIPMENT ID: ");
                        String custID = inLine.nextLine();
                        System.out.print("Customer Name: ");
                        String custName = inLine.nextLine();
                        System.out.print("Customer Contact Number: ");
                        String custNum = inLine.nextLine();
                        System.out.print("Customer Address: ");
                        String custAddress = inLine.nextLine();
                        String itemID = custID;
                        System.out.println("[1] Fragile");
                        System.out.println("[2] Non-Fragile");
                        System.out.print("Item Type code: ");
                        int itemType = in.nextInt();
                        while(itemType != 1 && itemType != 2) {
                            System.out.println("Enter the correct input: ");
                            itemType = in.nextInt(); 
                        }
                        System.out.print("Item Quantity: ");
                        int itemQty = in.nextInt();
                        String shpID = custID;
                        System.out.print("Shipment Status(ARRIVED OR ON BOARD): ");
                        String shpStatus = inLine.nextLine();
                        System.out.print("Shipment's Weight in KG:: ");
                        double shpWeight = in.nextDouble();
                        System.out.print("Shipment's Place of Origin: ");
                        String shpOrigin = inLine.nextLine();
                        
                        shp.setShipmentID(shpID);
                        shp.setShipmentStatus(shpStatus);
                        shp.setShipmentOrigin(shpOrigin);
                        shp.setShipmentWeight(shpWeight);
                        
                        cust.setCustomerID(custID);
                        cust.setCustomerName(custName);
                        cust.setCustomerContact(custNum);
                        cust.setCustomerAddress(custAddress);      
                        item.setItemID(itemID);
                        item.setItemType(itemType);
                        item.setItemQty(itemQty);
                        cond = 0;
                    }
                    
                    custTemp.enqueue(cust);
                    cust = (Customer) custList.dequeue();
                    itemTemp.enqueue(item);
                    item = (Item) itemList.dequeue();
                    shpTemp.enqueue(shp);
                    shp = (Shipment) shipmentList.dequeue();
                    
                }
                while(!custTemp.isEmpty())
                {
                    custList.enqueue(custTemp.dequeue());
                }
                while(!itemTemp.isEmpty())
                {
                    itemList.enqueue(itemTemp.dequeue());
                }
                while(!shpTemp.isEmpty())
                {
                    shipmentList.enqueue(shpTemp.dequeue());
                }
                if(cond == 1)
                {
                        System.out.println("Customer/Shipment/Item ID does not exist ");
                }
            }
            else if (choice == 5)
            {
                //SEARCHING
                System.out.println("\nSEARCHING");
                System.out.println("Enter code to Search data");
                System.out.println("[1] - Customer \n[2] - Item \n[3] - Shipment");
                System.out.print("code: ");
                int choose = in.nextInt();
                if (choose == 1)
                {
                    Queue tempQ = new Queue();
                    System.out.print("Enter Your Customer ID: ");
                    String keyword = inLine.nextLine();
                    int count = 0;
                    System.out.println();
                    while (!custList.isEmpty())
                    {
                        Customer data = (Customer) custList.dequeue();
                        String ID  = data.getCustomerID();
            
                        if (ID.equalsIgnoreCase(keyword))
                        {
                            System.out.println(data.toString());
                            count++;
                        }
                        tempQ.enqueue(data);
                    }
                    System.out.print(count);
                    if (count > 1)
                        System.out.print(" Records");
                    else
                        System.out.print(" Record");
                    System.out.println(" matched.");
                    while(!tempQ.isEmpty())
                    {
                        custList.enqueue(tempQ.dequeue());
                    }
                }
                else if (choose == 2)
                {
                    Queue tempQ = new Queue();
                    System.out.print("Enter Your Item ID: ");
                    String keyword = inLine.nextLine();
                    int count = 0;
                    System.out.println();
                    while (!shipmentList.isEmpty())
                    {
                        Shipment data = (Shipment) shipmentList.dequeue();
                        String ID  = data.getShipmentID();
                        if (ID.equalsIgnoreCase(keyword))
                        {
                            System.out.println(data.toString());
                            count++;
                        }
                        tempQ.enqueue(data);
                    }
        
                    System.out.print(count);
                    if (count > 1)
                        System.out.print(" Records");
                    else
                        System.out.print(" Record");
                    System.out.println(" matched.");
                    while(!tempQ.isEmpty())
                    {
                        shipmentList.enqueue(tempQ.dequeue());
                    }
                }
                else if (choose == 3)
                {
                    Queue tempQ = new Queue();
                    System.out.print("Enter Your Shipment ID: ");
                    String keyword = inLine.nextLine();
                    int count = 0;
                    System.out.println();
                    while (!itemList.isEmpty())
                    {
                        Item data = (Item) itemList.dequeue();
                        String ID  = data.getItemID();
                        if (ID.equalsIgnoreCase(keyword))
                        {
                            System.out.println(data.toString());
                            count++;
                        }
                        tempQ.enqueue(data);
                    }
        
                    System.out.print(count);
                    if (count > 1)
                        System.out.print(" Records");
                    else
                        System.out.print(" Record");
                    System.out.println(" matched.");
                    while(!tempQ.isEmpty())
                    {
                        itemList.enqueue(tempQ.dequeue());
                    }
                }
                else
                {
                    System.out.println("Please enter correct code!");
                }
            }
            else if (choice == 6)
            {
                System.out.println("You've exited the system.");
                break;
            }
            else
            {
                System.out.println("You enter the wrong code,enter again");
            }
            System.out.println("\nProceed ?");
            System.out.println("\n[1] YES\n[2] NO");
            int checker = in.nextInt();
            if (checker == 1)
            {
                check = false;
                System.out.print("enter 1 for clear the terminal: ");
                int ans1 = in.nextInt();
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
        System.out.println("Thank you for using this system.\nSystem ended");
    }
}