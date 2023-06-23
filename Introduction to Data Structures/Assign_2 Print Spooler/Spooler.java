package Assign_2;


import BasicIO.*;

/**This class implements the print spooler.
  * Micah Rose-Mighty
  * 6498935
  * 2020/02/24
  */


public class Spooler {
    
    
    private Node            printqueue;    // list of individuals in print queue
    private ASCIIDisplayer  display;  // for display of printed items
    private BasicForm       form;     // form for user interaction
    private int totpages; // Int variable to represent the sum of the pages.
    
    
    public Spooler ( ) {
        
        int  button; 
        display = new ASCIIDisplayer();
        form = new BasicForm("Add Job","Print Next","Check","Quit");
        printqueue = null;
        totpages = 0;
        setupForm(); 
        
        for ( ; ; ) {
            form.clearAll();
            button = form.accept();
        if ( button == 3 ) break;  // Quit
            switch ( button ) {
                case 0: {          // Adds a print job
                    addJob();
                    break;
                }
                case 1: {          // Prints the next individual in the queue
                    printNext();
                    break;
                }
                case 2: {          // Shows total number of pages in the print spooler
                    check();
                    break;
                }
            };
            form.accept("OK");
        };
        form.close();
        display.close();
        
    }; // constructor
    
    
    private void addJob ( ) { //adds a Print Job to the sorted list based on the information entered by the user.
        
        String description; // description of the print job
        String sender;      //name of the sender of the print job
        int pages;          //number of pages in print job
        int  priority;      // priority of the print job
        PrintJob printjob;  //creates the print job variable
        
        description = form.readString("description");
        sender = form.readString("sender");
        pages = form.readInt("pages");
        priority = form.readInt("priority");
        printjob = new PrintJob(description, sender, pages, priority);
        addNext(printjob);
        totpages = totpages + pages;
        form.writeString("status", totpages + " Pages Pending");
        
    };  // addJob
    
    
    private void displayJob (PrintJob aJob) { // Displays the jobs that have been printed onn the ASCII Displayer.
       
      display.writeString("{" +aJob.getSender() + " prints " + aJob.getDescription() + ": " + aJob.getPages() + " pages}");
      display.newLine();
                          
    };  //displayJob
    
    
    private void printNext ( ) { //Prints the next item in the queue by removing it from the list and calculates total number of pages pending. 
      
      PrintJob item;
      
      if(printqueue == null){
        item = null;
        form.writeString("status", "No Print Jobs currently in Queue,");
      }
      else{
        item = printqueue.item;
        printqueue = printqueue.next;
        totpages = totpages - item.getPages();
        form.writeString("status", totpages + " Pages Pending");
      };
      displayJob(item);
    }; //printNext

    
    private void check(){ // checks total amount of pages pending and sends feedback to status bar.
       form.writeString("status", totpages + " Pages Pending");
      }; //check
    
    private void setupForm ( ) { // sets up the form to allow user interaction where necessary and deny it where it is not.
        
        form.addTextField("description","Description:",12,10,10);
        form.addTextField("sender","Sender:",10,10,40);
        form.addTextField("pages","# Pages:",8,10,70);
        form.addRadioButtons("priority","Priority",true,250,10,PrintJob.PRIORITY);
        form.addTextField("status","Status:",40,10,100);
        form.setEditable("status",false);
        
    };  // setupForm
    
    
  
    private void addNext ( PrintJob aJob ) { // sorts the Print job in order in the Print Queue based on priority
        
        PrintJob  item; 
        Node p;
        Node q;
        
        item = aJob;
        q = null;
        p = printqueue;
        while ( p != null && item.getPriority() >= p.item.getPriority() ) {
            q = p;
            p = p.next;
        };
        if ( q == null ) {
            printqueue = new Node(item, p);
        }
        else{
          q.next = new Node(item,p);
    };
    }; //addNext
  
    
    public static void main ( String[] args ) { Spooler s = new Spooler(); };
    
    
}  // Spooler