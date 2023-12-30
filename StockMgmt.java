import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//@author Rudra Pandya
//To reach out to me on LinkedIn : Rudra Pandya, Rutgers University '27.
//Directions on how to compile: javac StockMgmt.java, java StockMgmt. (period is exclusive)

/*  Description about my project:
-> This project effectively focuses on implementing a comprehensive Stock Management and Analysis system by tracking 
and analyzing financial data of different firms and applying mathematical techniques for price analysis 
using Object-Oriented Design and Object Oriented Programming with Java. In future, I am planning to implement 
Data Science Techniques in order to create visualization for companies' performances, and use APIs to fetch real-world data instead of hard-coding it.

PS: I made this project before taking Data Structures (which I am next semester). I made this on the basis of my knowledge from Intro to CS (prereq to Data Structures)
*/

/*  Note about GitHub: 
-> If you liked my project and are planning to use ideas from it, please cite me or give credit to me.
-> Please do not copy this project and claim it as your own work. 
*/

/*  Side notes about my project:
-> prices.txt is where the 19 prices are located for each stock.
-> names.txt is where the companies are located to visually align where each stock price corresponds to which company. 
*/

/*  About prices.txt (percentDifference method)
-> prices are subject to change once every two days (manually).
-> the old price(s) is going to be located in prices.txt. They function as yesterday's closing-in price when market closes at 4:00 PM
-> the new price(s) are going to be inputted by you in the terminal. They function as today's closing-in price.
-> Using the percentDifference method, it shows you the percent increase or decrease from yesterdays price to todays price.
*/

/*
StdIn library and StdOut library credits: Princeton University
StdOut is same as System.out.print(ln)
StdIn.read_____() means reading something like a char, int, double, string. 
*/

//creating Stock object - Object Oriented Design, Object Oriented Programming
class Stock{
    //1st private variables : focusing on final aspects of company
    private String Stockname;       //company name : Stockname.
    private String Ticker;          //company ticker, a shortened version of company listed on NASDAQ : Ticker.
    private double currentPrice;    //trading price : listed in prices.txt, current price at which the company's share are being traded.
    private double FirstPrice;      //IPO or earliest price : earliest recorded price of the stock through IPO or all-time time-series view.  


    //2nd creating constructors : data type focuses on financial contents of company

    // Stock class declaration

    // Example below (with getter methods):
    // -> Stock variable = new Stock("Company's name", "Ticker", decimal point price, earliest recorded price)
    public Stock(String stockname, String ticker, double currPrice, double Firstprice){
        this.Stockname = stockname;
        this.Ticker = ticker;
        this.currentPrice = currPrice;
        this.FirstPrice = Firstprice;
    }

    //3rd getter methods

    //gets Stockname of the particular company
    //variable.getStockname()
    public String getStockname(){
        return this.Stockname;
    }
    //gets Ticker name of the particular company
    //variable.getTicker();
    public String getTicker(){
        return this.Ticker;
    }
    //gets current price of the share of the particular company
    //variable.getCurrentPrice();
    public double getCurrentPrice(){
        return this.currentPrice;
    }
    //gets financial info of the company excluding the IPO price
    //variable.getFinancialInfo();
    public void getFinancialInfo(){
        StdOut.println("The company is " + getStockname() + " , with ticker symbol " + getTicker() + " , has the latest share price of " + getCurrentPrice());
    }
    //gets the IPO or the earliest recorded price of the company
    public double getFirstPrice(){
        return this.FirstPrice;
    }
}

//functions to retrieve or analyze price information. Triggered by user input only. 
public class StockMgmt {
    //gets financial information of each company using an enhanced for loop.
    public static void AllStockInfo(ArrayList<Stock> stocksOfcompanies){
        for (Stock stocks : stocksOfcompanies){
            stocks.getFinancialInfo();
        }
        StdOut.println();
        StdOut.println("--------------------------------------------------------------------------");
    }

    //gets specific company financial informations based on user input, saves client time by manual view.
    public static void SpecificStockInfo(ArrayList<Stock> stocksOfcompanies, String specificCompany){
        for (Stock stocks : stocksOfcompanies){
            if(stocks.getStockname().equalsIgnoreCase(specificCompany)){
                stocks.getFinancialInfo();
            }
        }
        StdOut.println();
        StdOut.println("--------------------------------------------------------------------------");
    }

    //gets every existent stockname of each company using an enhanced for loop.
    public static void AllStockNameAndTicker(ArrayList<Stock> stocksOfcompanies){
        for (int i = 0; i < stocksOfcompanies.size(); i++){
            StdOut.println((i+1) + " - " + stocksOfcompanies.get(i).getStockname() + " and its ticker " + stocksOfcompanies.get(i).getTicker());
        }
        StdOut.println();
        StdOut.println("--------------------------------------------------------------------------");
    }

    //gets every existent ticker of each company using an enhanced for loop.
    public static void TickerExistence(ArrayList<Stock> stocksOfcompanies, String ticker){
        Boolean tickerExists = false;
        String tickerBelonging = null;
        for (int i = 0; i < stocksOfcompanies.size(); i++){
            if(stocksOfcompanies.get(i).getTicker().equalsIgnoreCase(ticker)){
                tickerExists = true;
                tickerBelonging = stocksOfcompanies.get(i).getStockname();
                break;
            }
        }
        if(tickerExists == true){
            StdOut.println("Ticker " + ticker.toUpperCase() + " exists in the ArrayList and belongs to " + tickerBelonging);
        }
        else{
            StdOut.println("Ticker " + ticker.toUpperCase() + " does not exist in the ArrayList.");
        }
        StdOut.println();
        StdOut.println("--------------------------------------------------------------------------");
    }

    //gets individual current price of the stock using an enchanced for loop.
    public static void IndividualCurrentPrice(ArrayList<Stock> stocksOfcompanies){
        for (int i = 0; i < stocksOfcompanies.size(); i++){
            StdOut.println(stocksOfcompanies.get(i).getStockname() + " - " + stocksOfcompanies.get(i).getCurrentPrice());
        }
        StdOut.println();
        StdOut.println("--------------------------------------------------------------------------");
    }

    //gets individual IPO/firstPrice using an enhanced for loop.
    public static void IndividualFirstPrice(ArrayList<Stock> stocksOfcompanies){
        for (int i = 0; i < stocksOfcompanies.size(); i++){
            StdOut.println(stocksOfcompanies.get(i).getStockname() + " - " + stocksOfcompanies.get(i).getFirstPrice());
        }
        StdOut.println();
        StdOut.println("--------------------------------------------------------------------------");
    }

    //gets percentage difference from current price to new updated current price using StdIn. libraries for reading new price and updating it to double. 
    //if the user enters break, it breaks loop and quits program. 
    public static void percentDifference(ArrayList<Stock> stocksOfcompanies){
        for (Stock stock : stocksOfcompanies) {
            StdOut.println("Company: " + stock.getStockname());
            double oldPrice = stock.getCurrentPrice();
            StdOut.println("Yesterday's closing in price of the stock " + stock.getStockname() + " was: " + oldPrice);
            
            boolean validInput = false;
            double newPrice = 0;

            while (!validInput) {
                StdOut.print("Today's closing in price of the stock " + stock.getStockname() + " is (or type 'break' to quit): ");
                String userInput = StdIn.readString();
    
                if ("break".equalsIgnoreCase(userInput)) {
                    StdOut.println("Quitting the program as per user request.");
                    StdOut.println("--------------------------------------------------------------------------");
                    return; // Exit the method
                }
    
                try {
                    newPrice = Double.parseDouble(userInput);
                    validInput = true;
                } 
                catch (NumberFormatException e) {
                    StdOut.println("Invalid input. Please enter a valid numeric value.");
                }
            }
    
            double percentDiff = (((newPrice - oldPrice) / oldPrice) * 100);
            if (percentDiff > 0) {
                StdOut.println(stock.getStockname() + " stock price is increasing");
            } else if (percentDiff < 0) {
                StdOut.println(stock.getStockname() + " stock price is decreasing");
            } else {
                StdOut.println(stock.getStockname() + " stock price remains unchanged");
            }
            StdOut.println("The percent difference in comparison to the new price is: " + percentDiff + "%");
            StdOut.println("--------------------------------------------------------------------------");
        }
    }
    //gets percentage difference of the specific stock the user wants instead of all of them. 
    //Outputs percentage difference between the updated and the previous price. The previous price are in prices.txt
    public static void specificStockPercentDifference(ArrayList<Stock> stocksOfcompanies, String company){
        for (Stock stock : stocksOfcompanies){
            if(stock.getStockname().equalsIgnoreCase(company)){
                StdOut.println("Company: " + stock.getStockname());
                double oldPrice = stock.getCurrentPrice();
                StdOut.println("The old price of the stock " + stock.getStockname() + " was: " + oldPrice);
                StdOut.print("Enter the new price of the stock " + stock.getStockname() + " (or type 'break' to quit): ");
                String userInput = StdIn.readString();
            
            if ("break".equalsIgnoreCase(userInput)) {
                StdOut.println("Quitting the program as per user request.");
                StdOut.println();
                StdOut.println("--------------------------------------------------------------------------");
                break; // Exit the loop
            }
    
            double newPrice;
            try {
                newPrice = Double.parseDouble(userInput);
            } catch (NumberFormatException e) {
                StdOut.println("Invalid input. Please enter a valid numeric value.");
                continue; // Skip the rest of the loop and proceed to the next stock
            }
    
            double percentDiff = (((newPrice - oldPrice) / oldPrice) * 100);
            if (percentDiff > 0){
                StdOut.println(stock.getStockname() + " stock price is increasing");
            }
            else{
                StdOut.println(stock.getStockname() + " stock price is decreasing");
            }
            StdOut.println("The percent difference in comparison to the new price is: " + percentDiff + "%");
            StdOut.println();
            StdOut.println("--------------------------------------------------------------------------");
            }
        }
    }

    //gets memory address or memory location of the stock for identitification purposes. 
    //may have cybersecurity or personal infomation access application in the real world when discussing about trade execution addresses/receipts.
    public static void printOutStockAddress(ArrayList<Stock> stocksOfcompanies){
        StdOut.println("The following are the memory addresses of the stocks: ");
        for (int i = 0; i < stocksOfcompanies.size(); i++){
            StdOut.println((i+1) + ") " + "The memory location of: " + stocksOfcompanies.get(i).getStockname() + " is " + stocksOfcompanies.get(i));
        }
        StdOut.println();
        StdOut.println("--------------------------------------------------------------------------");
    }

    //printing out stock memory address as per client's request, saving user time by not doing it manually. 
    //may have cybersecurity or personal information access application based on client's specific request when discussing about trade execution addresses/receipts.
    public static void printOutSpecificStockAddress(ArrayList<Stock> stocksOfcompanies, String company){
        StdOut.print("The following is the memory address of " + company + ": ");
        for (Stock stock : stocksOfcompanies) {
            if (stock.getStockname().equalsIgnoreCase(company)) {
                StdOut.println(stock);
                StdOut.println();
                StdOut.println("--------------------------------------------------------------------------");
                return; // Exit the loop once a match is found
        }
    }
        StdOut.println("Stock not found."); //if stock is not present, this would be outputted.
    }
    /*compares every companies" performances by comparing the percent difference btween yesterday's closing-in price and 
    today's closing-in price respectively for all companies */
    public static void comparativePercentDifference(ArrayList<Stock> stocksOfcompanies){
    ArrayList<Double> percentCollection = new ArrayList<>(19);
    ArrayList<String> companyCollection = new ArrayList<>(19);

    for (int i = 0; i < stocksOfcompanies.size(); i++) {
        StdOut.println();
        String name = stocksOfcompanies.get(i).getStockname();
        StdOut.println("Company: " + name);
        double oldPrice = stocksOfcompanies.get(i).getCurrentPrice();
        StdOut.println("Yesterday's closing in price for stock " + name + " was: " + oldPrice);
        
        boolean validInput = false;
        double newPrice = 0;

        while (!validInput) {
            StdOut.print("Today's closing in price for stock " + name + " is (or type 'break' to quit): ");
            String userInput = StdIn.readString();

            if ("break".equalsIgnoreCase(userInput)) {
                StdOut.println("Quitting the program as per user request.");
                StdOut.println();
                return; // Exit the function
            }

            try {
                newPrice = Double.parseDouble(userInput);
                validInput = true;
            } 
            catch (NumberFormatException e) {
                StdOut.println("Invalid input. Please enter a valid numeric value.");
            }
        }

        double percentDiff = (((newPrice - oldPrice) / oldPrice) * 100);
        percentCollection.add(percentDiff);
        companyCollection.add(name);
        StdOut.println();
        StdOut.println("--------------------------------------------------------------------------");
    }

    int maxIndex = 0;
    int minIndex = 0;

    for (int i = 0; i < percentCollection.size(); i++) {
        if (percentCollection.get(i) > percentCollection.get(maxIndex)) {
            maxIndex = i;
        }
        if (percentCollection.get(i) < percentCollection.get(minIndex)) {
            minIndex = i;
        }
    }

    String maxBelonging = companyCollection.get(maxIndex);
    String minBelonging = companyCollection.get(minIndex);

    StdOut.println();
    StdOut.println("The highest percent difference is: " + percentCollection.get(maxIndex) + "% by " + maxBelonging);
    StdOut.println("The lowest percent difference is: " + percentCollection.get(minIndex) + "% by " + minBelonging);
    StdOut.println();
    StdOut.println("--------------------------------------------------------------------------");
}
    //compares two companies' performances by comparing the percent difference between yesterday's closing-in price and today's closing-in price respectively for both companies. 
    public static void specificComparativePercentDifference(ArrayList<Stock> stocksOfcompanies, String company1, String company2){
        StdOut.println();
        double percentDiff1 = 0;
        double percentDiff2 = 0;
        for (Stock stocks : stocksOfcompanies){
            //IgnoreEqualCase saves the day
            if (stocks.getStockname().equalsIgnoreCase(company1)){
                StdOut.println("Company #1 is: " + stocks.getStockname());
                double oldPrice1 = stocks.getCurrentPrice();
                StdOut.println("Yesterday's closing in price for " + stocks.getStockname() + " was: " + oldPrice1);
                StdOut.print("Today's closing in price for " + stocks.getStockname() + " is: ");
                double newPrice1 = StdIn.readDouble();
                percentDiff1 = (((newPrice1 - oldPrice1)/(oldPrice1)) * 100);
                if (percentDiff1 > 0){
                    StdOut.println("The price is increasing for " + stocks.getStockname());
                }
                else{
                    StdOut.println("The price is decreasing " + stocks.getStockname());
                }
                StdOut.println("The percent difference in comparison to new price for " + stocks.getStockname() + " is " +  + percentDiff1 + "%");
            }
        }
        for (Stock stocks : stocksOfcompanies){
            //IgnoreEqualCase saved the day
            if (stocks.getStockname().equalsIgnoreCase(company2)){
                StdOut.println();
                StdOut.println("Company #2 is: " + stocks.getStockname());
                double oldPrice2 = stocks.getCurrentPrice();
                StdOut.println("Yesterday's closing in price for " + stocks.getStockname() + " was: " + oldPrice2);
                StdOut.print("Today's closing in price for " + stocks.getStockname() + " is: ");
                double newPrice2 = StdIn.readDouble();
                percentDiff2 = (((newPrice2 - oldPrice2)/(oldPrice2)) * 100);
                if (percentDiff2 > 0){
                    StdOut.println("The price is increasing for " + stocks.getStockname());
                }
                else{
                    StdOut.println("The price is decreasing " + stocks.getStockname());
                }
                StdOut.println("The percent difference in comparison to new price for " + stocks.getStockname() + " is " + percentDiff2 + "%");
            }
        }
        double company1PercentDiff = percentDiff1;
        double company2PercentDiff = percentDiff2;
        if (company2PercentDiff > company1PercentDiff){
            StdOut.println();
            StdOut.println(company2 + " is performing better than " + company1 + " by " + (company2PercentDiff-company1PercentDiff) + "%");
            StdOut.println();
            StdOut.println("--------------------------------------------------------------------------");
        }
        else if (company1PercentDiff > company2PercentDiff){
            StdOut.println();
            StdOut.println(company1 + " is performing better than " + company2 + " by " + (company1PercentDiff-company2PercentDiff) + "%");
            StdOut.println();
            StdOut.println("--------------------------------------------------------------------------");

        }
        else{
            StdOut.println();
            StdOut.println(company1 + " and " + company2 + " are performing the same");
            StdOut.println();;
            StdOut.println("--------------------------------------------------------------------------");
        }
    }
    //main method
    public static void main(String[] args) {
         //Technology and Banking firms

         ArrayList <Stock> StocksOfCompanies = new ArrayList<Stock>(19);
        
         //setting 18 firms with their respective financial information, share current price recorded as of 12/26/2023
         // share price subject to change once every 3 days 
         try{
 
         Scanner fileScanner = new Scanner(new File("prices.txt"));

         //currentprice : This is present in prices.txt. They are read as we create the class.
         //used for handling panic in tech-driven practices, user can put in the closing price of the stock every 2 days in the prices.txt.
         //for more information, names.txt parallel represents the prices.txt about where the prices are assigned to.
         Stock Meta = new Stock("Meta", "META", fileScanner.nextDouble(), 38);
         Stock Apple = new Stock("Apple", "AAPL", fileScanner.nextDouble(), 22);
         Stock Amazon = new Stock("Amazon", "AMZN", fileScanner.nextDouble(), 18);
         Stock Netflix = new Stock("Netflix", "NFLX", fileScanner.nextDouble(), 15);
         Stock Google = new Stock("Google", "GOOGL", fileScanner.nextDouble(), 85);
         Stock Nvidia = new Stock("Nvidia", "NVDA", fileScanner.nextDouble(), 12);
         Stock IBM = new Stock("IBM", "IBM", fileScanner.nextDouble(), 29.13);
         Stock Accenture = new Stock("Accenture", "ACN", fileScanner.nextDouble(), 18.31);
         Stock AMD = new Stock("Advanced Micro Devices", "AMD", fileScanner.nextDouble(), 16.81);
         Stock Tesla = new Stock("Tesla", "TSLA", fileScanner.nextDouble(), 17);
         Stock Microsoft = new Stock("Microsoft", "MSFT", fileScanner.nextDouble(), 21);
         Stock Intel = new Stock("Intel", "INTC", fileScanner.nextDouble(), 23.50);
         Stock Dell = new Stock("Dell", "DELL", fileScanner.nextDouble(), 8.50);
         Stock SPglobal = new Stock("S&P Global", "SPGI", fileScanner.nextDouble(), 5.28);
         Stock JPMorganChase = new Stock("JPMorgan Chase", "JPM", fileScanner.nextDouble(), 9.78);
         Stock MorganStanley = new Stock("Morgan Stanley", "MS", fileScanner.nextDouble(), 2.82);
         Stock GoldmanSachs = new Stock("Goldman Sachs", "GS", fileScanner.nextDouble(), 74.69);
         Stock Blackstone = new Stock("Blackstone", "BX", fileScanner.nextDouble(), 31);
         Stock BlackRock = new Stock("BlackRock", "BLK", fileScanner.nextDouble(), 14.13);
 
         //adding the created firms with their respective information to the ArrayList of type Stock
         StocksOfCompanies.add(Meta);
         StocksOfCompanies.add(Apple);
         StocksOfCompanies.add(Amazon);
         StocksOfCompanies.add(Netflix);
         StocksOfCompanies.add(Google);
         StocksOfCompanies.add(Nvidia);
         StocksOfCompanies.add(IBM);
         StocksOfCompanies.add(Accenture);
         StocksOfCompanies.add(AMD);
         StocksOfCompanies.add(Tesla);
         StocksOfCompanies.add(Microsoft);
         StocksOfCompanies.add(Intel);
         StocksOfCompanies.add(Dell);
         StocksOfCompanies.add(SPglobal);
         StocksOfCompanies.add(JPMorganChase);
         StocksOfCompanies.add(MorganStanley);
         StocksOfCompanies.add(GoldmanSachs);
         StocksOfCompanies.add(Blackstone);
         StocksOfCompanies.add(BlackRock);

         fileScanner.close();
         }
         //if the file name is changed or does not exist within the folder, file not found. Used for handling edge cases.
         catch(FileNotFoundException e){
             {
                 StdOut.println("File not found: " + e.getMessage());
             }
         }
         //let the action begin!

         //Creating menu for user interface to retrieve information or conduct price analysis.
         Scanner scanner = new Scanner(System.in);
         int choice;
         do {
             // Display menu
             StdOut.println();
             StdOut.println("Welcome to my Stock Management and Analysis project!");
             StdOut.println();
             StdOut.println("Menu:");
             StdOut.println("1. View all stock information: ");
             StdOut.println("2. View specific stock information: ");
             StdOut.println("3. View all stock names and their tickers: ");
             StdOut.println("4. View ticker existence: ");
             StdOut.println("5. View all current prices: ");
             StdOut.println("6. View all first/IPO prices: ");
             StdOut.println("7. View all stock addresses: ");
             StdOut.println("8. View specific stock address");
             StdOut.println("9. View percentage difference for all stocks: ");
             StdOut.println("10. View percentage difference for a specific stock: ");
             StdOut.println("11. View comparative percentage difference between all stocks:  ");
             StdOut.println("12. View comparative percentage difference between two stocks: ");
             StdOut.println("13. Quit");
             StdOut.print("Enter your choice: ");
 
             // Get user choice
             choice = Integer.parseInt(scanner.nextLine());
             StdOut.println();
             switch (choice) {
                //if user inputs 1, the function below is triggered.
                 case 1:
                     AllStockInfo(StocksOfCompanies);
                     break;
                 case 2:
                //if user inputs 2, the function below about specific stock is triggered.
                     StdOut.print("Enter the stock name for specific stock info: ");
                     String specificStockName = scanner.nextLine();
                     Stock specificStock = null;
                     for (Stock stock : StocksOfCompanies) {
                         if (stock.getStockname().equalsIgnoreCase(specificStockName)) {
                             specificStock = stock;
                             break;
                         }
                     }
                     if (specificStock != null) {
                         SpecificStockInfo(StocksOfCompanies, specificStockName);
                     } else {
                         StdOut.println("Stock not found.");
                     }
                     break;
                //if the user inputs 3, the function below is triggered.
                 case 3:
                     AllStockNameAndTicker(StocksOfCompanies);
                     break;
                //if the user inputs 4, the function below is triggered.
                 case 4:
                     StdOut.print("Enter the ticker to see if it exists or not: ");
                     String ticker = scanner.nextLine();
                     TickerExistence(StocksOfCompanies, ticker);
                     break;
                //if the user inputs 5, the function below is triggered.
                 case 5:
                     IndividualCurrentPrice(StocksOfCompanies);
                     break;
                //if the user inputs 6, the function below is triggered.
                 case 6:
                     IndividualFirstPrice(StocksOfCompanies);
                     break;
                //if the user inputs 7, the function below is triggered.
                 case 7:
                     printOutStockAddress(StocksOfCompanies);
                     break;
                //if the user inputs 8, the function below is triggered.
                 case 8:
                    StdOut.print("Enter the stock name for retrieving its memory address: ");
                    String stockNameForAddress = scanner.nextLine();
                    printOutSpecificStockAddress(StocksOfCompanies, stockNameForAddress);
                    break;
                //if the user inputs 9, the function below is triggered.
                 case 9:
                     percentDifference(StocksOfCompanies);
                     break;
                //if the user inputs 10, the function below is triggered.
                 case 10:
                     StdOut.print("Enter the stock name for percentage difference: ");
                     String specificStockForDiff = scanner.nextLine();
                     specificStockPercentDifference(StocksOfCompanies, specificStockForDiff);
                     break;
                //if the user inputs 11, the function below is triggered.
                case 11:
                    StdOut.println("Comparative percent for all stocks: ");
                    comparativePercentDifference(StocksOfCompanies);
                    break;
                //if the user inputs 12, the function below is triggered.
                 case 12:
                    StdOut.print("Enter the first stock name for comparative percentage difference: ");
                    String company1Name = scanner.nextLine();

                    StdOut.print("Enter the second stock name for comparative percentage difference: ");
                    String company2Name = scanner.nextLine();

                    specificComparativePercentDifference(StocksOfCompanies, company1Name, company2Name);
                    break;
                //input of 13, quits the program due to user's request. 
                 case 13:
                     StdOut.println("Quitting the program. Goodbye!");
                     break;
                 default:
                     StdOut.println("Invalid choice. Please enter a valid option.");
             }

         } while (choice != 13);
 
         // Close the scanner
         scanner.close();
    }
}
