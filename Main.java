// --== CS400 File Header Information ==--
// Name: Christopher Semler
// Email: csemler@wisc.edu
// Team: CG
// Role: Front End Developer 2
// TA: Yeping
// Lecturer: Florian
// Notes to Grader: none

import java.util.List;
import java.util.Scanner;

/**
 * Class to be run to access program.
 * Contains the main method.
 * Contains helper methods to help execute tasks.
 * 
 * @author Chris Semler
 *
 */
public class Main {

  private static Scanner scan;
  private static RelationshipDatabase database;

  /**
   * Main method to ask user what task to do and get input
   * Sends input to helper methods to perform tasks
   * Displays if successful or not
   * 
   * @param args
   */
  public static void main(String[] args) {
    scan = new Scanner(System.in);
    String input = "";
    
    //welcome message
    System.out.println("Welcome to the Social Network Relations System!");
    
    //display list of options user can choose from
    displayOptions();
    //get input from user
    input = scan.nextLine();
    
    //add spacing
    style();
    
    //continue until user wants to exit (as long as input != 11)
    while (!input.equals("11")) {      
      //if option 1 (add person) was chosen
      if (input.equals("1")) {
        
        //find name of new person to be added
        System.out.println("What is the name of the person you would like to add?");
        String tempName = "";
        tempName = scan.nextLine();
        
        //find age of new person to be added
        System.out.println("What is " + tempName + "'s age?");
        int tempAge = 0;
        boolean valid = false;
        
        //will repeat question until a valid input (a positive integer) was entered
        while (valid == false) {
          try {
            //gets new age
            tempAge = scan.nextInt();
            if (tempAge > 0)
              valid = true;
            //if age isn't valid, repeat
            else
              System.out.println("Please enter a valid age.");
          }
          catch (Exception e) {
            System.out.println("Please enter a valid age.");
          }
        }
          
        //find profession of new person to be added
        System.out.println("What is " + tempName + "'s profession?");
        String tempProf = "";
        tempProf = scan.nextLine();
        
        //spacing
        style();
        
        //add new person to database
        if(addPerson(tempName, tempAge, tempProf) == true)
          System.out.println("Added new person sucessfully!");
        else
          System.out.println("Sorry. Adding new person failed.");
      }      
      //if option 2 (add relation) was chosen
      else if (input.equals("2")) {
        //get first person
        System.out.println("Who is the first person in the relation?");
        String person1 = "";
        person1 = scan.nextLine();
        
        //get second person
        System.out.println("Who is the second person in the relation?");
        String person2 = "";
        person2 = scan.nextLine();
      
        //get valid weight of relation
        int tempRelation = 0;
        while (tempRelation < 1 || tempRelation > 8) {
          System.out.println("What is their relation to each other? (enter the number)");
          System.out.println("1: Close family (brothers/sisters/parents/spouse/kids)");
          System.out.println("2: Extended family (cousins/aunts/uncles/grandparents/grandkids)");
          System.out.println("3: Best friends");
          System.out.println("4: Normal friends");
          System.out.println("5: Classmates");
          System.out.println("6: Colleagues");
          System.out.println("7: Acquitances");
          System.out.println("8: Other");
          tempRelation = scan.nextInt();
          
          //check if valid
          if (tempRelation < 1 || tempRelation > 8) {
            System.out.println("Please enter a valid relation (1-8).");
          }
        }
              
        //spacing
        style();
        
        //add relation
        if (addRelationship(person1, person2, tempRelation) == true)
          System.out.println("Adding relationship was successful!");
        else 
          System.out.println("Something went wrong!");
      }
      //if option 3 (delete person) was chosen
      else if (input.equals("3")) {
        String tempName = "";
        
        //get name of person being deleted
        System.out.println("Who would you like to delete?");
        tempName = scan.nextLine();
        
        //spacing
        style();
        
        //delete person
        if (deletePerson(tempName) == true)
          System.out.println("Deleting person was successful!");
        else
          System.out.println("Something went wrong!");
      }
      //if option 4 (update person info) was chosen
      else if (input.equals("4")) {
        //initialize variables
        int tempChangeNum = 0;
        boolean validInput = false;
        String tempName = "";
        
        //repeat until valid input
        while (!validInput) {
          //get name of person to update
          System.out.println("Who's info would you like to update?");
          tempName = scan.nextLine();
          
          //get info to change
          System.out.println("What would you like to update? (enter the number)");
          System.out.println("1: Age");
          System.out.println("2: Profession");
          tempChangeNum = scan.nextInt();
          
          //spacing
          style();
          
          //check if valid
          if (tempChangeNum == 1 || tempChangeNum == 2) {
            validInput = true;
          }
          else {
            System.out.println("Please enter a valid input (1 or 2)");
          }            
        }
        
        //if user wants to change person's age
        if (tempChangeNum == 1) {
          System.out.println("What is " + tempName + "'s new age?");
          int tempAge = 0;
          //get valid input for age
          while (!(tempAge > 0)) {
            tempAge = scan.nextInt();
            if (tempAge <= 0)
              System.out.println("Please enter a valid age (positive number)");
          }
          
          //update age
          if (updateAge(tempName, tempAge) == true)
            System.out.println("Updating " + tempName + "'s age was successful!");
          else
            System.out.println("Something went wrong!");
        }
        //if user wants to change person's profession
        else if (tempChangeNum == 2) {
          System.out.println("What is " + tempName + "'s new profession?");
          String tempProf = "";
          
          //get valid input for profession
          tempProf = scan.nextLine();
          
          //spacing
          style();
          
          //update profession
          if (updateProfession(tempName, tempProf) == true)
            System.out.println("Updating " + tempName + "'s profession was successful!");
          else
            System.out.println("Something went wrong!");
        }
      }
      //if option 5 (delete relation) was chosen
      else if (input.equals("5")) {
        //set up local variables
        String person1 = "";
        String person2 = "";

        //get names of people in relation
        System.out.println("What is the first person's name?");
        person1 = scan.nextLine();
        
        System.out.println("What is the second person's name?");
        person2 = scan.nextLine();
        
        //spacing
        style();
        
        //delete relationship
        if (deleteRelationship(person1, person2) == true)
          System.out.println("Deleting the relationship was successful!");
        else
          System.out.println("Something went wrong!");
      }
      //if option 6 (change degree of relation) was chosen
      else if (input.equals("6")) {
        //set up local variables
        String person1 = "";
        String person2 = "";

        //get names of people in relation
        System.out.println("What is the first person's name?");
        person1 = scan.nextLine();
        
        System.out.println("What is the second person's name?");
        person2 = scan.nextLine();
        
        //find new relation
        int tempRelation = 0;
        while (tempRelation < 1 || tempRelation > 8) {
          System.out.println("What is their new relation to each other? (enter the number)");
          System.out.println("1: Close family (brothers/sisters/parents/spouse/kids)");
          System.out.println("2: Extended family (cousins/aunts/uncles/grandparents/grandkids)");
          System.out.println("3: Best friends");
          System.out.println("4: Normal friends");
          System.out.println("5: Classmates");
          System.out.println("6: Colleagues");
          System.out.println("7: Acquitances");
          System.out.println("8: Other");
          tempRelation = scan.nextInt();
          
          //check if valid
          if (tempRelation < 1 || tempRelation > 8) {
            System.out.println("Please enter a valid relation (1-8).");
          }
        }
        
        //spacing
        style();
        
        //updating the relationship
        if (updateRelationship(person1, person2, tempRelation) == true)
          System.out.println("Updating the relationship was successful!");
        else
          System.out.println("Something went wrong!");
      }
      //if option 7 (get path of relation) was chosen
      else if (input.equals("7")) {
        String person1 = "";
        String person2 = "";
        
        //get names of people in relation
        System.out.println("What is the first person's name?");
        person1 = scan.nextLine();
        
        System.out.println("What is the second person's name?");
        person2 = scan.nextLine();
      
        //spacing
        style();
        
        //get path
        List<Person> path = getPathOfRelationship(person1, person2);
        if (path != null)
          System.out.println(path.toString());
        else
          System.out.println("Something went wrong!");
      }
      //if option 8 (get total weight) was chosen
      else if (input.equals("8")) {
        String person1 = "";
        String person2 = "";
        
        //get names of people in relation
        System.out.println("What is the first person's name?");
        person1 = scan.nextLine();
        
        System.out.println("What is the second person's name?");
        person2 = scan.nextLine();
        
        //spacing
        style();
        
        //get path cost
        int cost = getTotalDegreeOfRelationship(person1, person2);
        if (cost >= 0)
          System.out.println("Total degree of path: " + cost);        
        else
          System.out.println("Something went wrong!");
      }
      //if option 9 (get total number of people in database) was chosen
      else if (input.equals("9")) {
        System.out.println("The total number of people in database is " + totalNumOfPerson());
        System.out.println("The total number of relationships in database is " + totalNumOfRelationships());
      }
      //if option 10 (check if person is in database) was chosen
      else if (input.equals("10")) {
        System.out.println("Who do you want to search for?");
        //get name to check
        String tempName = "";
        tempName = scan.nextLine();
        
        //spacing
        style();
        
        //check if in database
        if (containPerson(tempName) == true) {
          System.out.println(tempName + " is in the database!");
        }
        //if not in database
        else {
          System.out.println(tempName + " is not in the databse!");
        }
      }
      
      //space out previous task with new task
      style();
      
      //display list of options user can choose from
      displayOptions();
      //get input from user
      input = scan.nextLine(); 
      
      //add spacing
      style();
    }
    
    //exit message
    System.out.println("Thank you for using the social network!");
  }
  
  //method to add more spacing and some style to the user interface
  //allows user interface to look presentable and more easily read
  public static void style() {
    System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");
  }
  
  /**
   * Method to display options to user
   * User will then type in the number of the desired task
   */
  public static void displayOptions() {
    System.out.println("What would you like to do? (use the number of the task)");
    System.out.println("1: Add person");
    System.out.println("2: Add relation");
    System.out.println("3: Delete person");
    System.out.println("4: Update person information");
    System.out.println("5: Delete relation");
    System.out.println("6: Change degree of relation");
    System.out.println("7: Get path of relation");
    System.out.println("8: Get total degree (weight) of a path/relation");
    System.out.println("9: Check total number of people in database");
    System.out.println("10: Check if person exists in database");
    System.out.println("11: Exit program");
  }
  /**
   * Getter method to find a person
   * 
   * @param name
   * @return
   */
  public Person getPerson(String name){
    return database.getPerson(name);
  }
  
  /**
   * Method to add person to database
   * Returns false if duplicate person, true otherwise
   * 
   * @param name
   * @param age
   * @param profession
   * @return
   */
  public static boolean addPerson(String name, int age, String profession) {
    if (database.hasPerson(name)) {
      System.out.println(name + " already exists in the database. No duplicates allowed.");
      return false;
    }
    
    return database.addPerson(name, age, profession);
  }
  
  /**
   * Method to add a relationship to database
   * Returns false if relationship between people already exists, true otherwise
   * 
   * @param person1
   * @param person2
   * @param weight
   * @return
   */
  public static boolean addRelationship(String person1, String person2, int weight) {
    if (database.containsRelationship(person1, person2)) {
      System.out.println("This relationship already exists in the database. No duplicates allowed.");
      System.out.println("If you want to update the relationship between these people, please select the update function.");
      return false;
    }
    
    return database.addsRelationship(person1, person2, weight);
  }
  
  /**
   * Method to delete person from database
   * Returns false if person does not exist, true if successful
   * 
   * @param name
   * @return
   */
  public static boolean deletePerson(String name) {
    if (database.hasPerson(name))
      return database.removePerson(name);
    else { 
      System.out.println("Person does not exist in the database.");
      return false;
    }
  }
  
  /** 
   * Method to update a person's age
   * Returns false if person does not exist, along with sending an error message
   * Returns true if successful
   * 
   * @param name
   * @param newAge
   * @return
   */
  public static boolean updateAge(String name, int newAge) {
    if (database.hasPerson(name))
      return database.updateAge(name, newAge);
    else { 
      System.out.println("Person does not exist in the database.");
      return false;
    }
  }
  
  /**
   * Method to update a person's profession
   * Returns false if person does not exist, along with sending an error message
   * Returns true if successful
   * 
   * @param name
   * @param newProfession
   * @return
   */
  public static boolean updateProfession (String name, String newProfession) {
    if (database.hasPerson(name))
      return database.updateProfession(name, newProfession);
    else { 
      System.out.println("Person does not exist in the database.");
      return false;
    }
  }
  
  /**
   * Method to check if person exists in database
   * Returns true if yes, false if not
   * 
   * @param name
   * @return
   */
  public static boolean containPerson(String name) {
    return database.hasPerson(name);
  }
  
  /**
   * Method to check if a relationship exists between two people
   * Returns true if yes, false if not
   * 
   * @param name1
   * @param name2
   * @return
   */
  public static boolean containRelationship(String name1, String name2) {
    if (database.hasPerson(name1) && database.hasPerson(name2))
      return database.containsRelationship(name1, name2);
    else { 
      if (!(database.hasPerson(name1)))
        System.out.println("Person1 does not exist in the database.");
      else
        System.out.println("Person2 does not exist in the database.");
      return false;
    }
  }
  
  /**
   * Method to delete a relationship between people
   * Returns true if successful, false if not or a person does not exist
   * 
   * @param person1
   * @param person2
   * @return
   */
  public static boolean deleteRelationship(String person1, String person2) {
    if (database.hasPerson(person1) && database.hasPerson(person2))
      return database.removeRelationship(person1, person2);
    else { 
      if (!(database.hasPerson(person1)))
        System.out.println("Person1 does not exist in the database.");
      else
        System.out.println("Person2 does not exist in the database.");
      return false;
    }
  }
  
  /**
   * Method to update a relationship between people
   * Returns true if successful, false if not or a person does not exist
   * 
   * @param person1
   * @param person2
   * @param weight
   * @return
   */
  public static boolean updateRelationship(String person1, String person2, int weight) {
    if (database.hasPerson(person1) && database.hasPerson(person2))
      return database.updateRelationship(person1, person2, weight);
    else { 
      if (!(database.hasPerson(person1)))
        System.out.println("Person1 does not exist in the database.");
      else
        System.out.println("Person2 does not exist in the database.");
      return false;
    }
  }
  
  /**
   * Method to get the path of a relationship between people
   * Returns that path as a List object
   * Returns null if not successful
   * 
   * @param name1
   * @param name2
   * @return
   */
  public static List<Person> getPathOfRelationship(String name1, String name2) {
    if (database.hasPerson(name1) && database.hasPerson(name1))
      return database.getRelationshipPath(name1, name2);
    else { 
      if (!(database.hasPerson(name1)))
        System.out.println("Person1 does not exist in the database.");
      else
        System.out.println("Person2 does not exist in the database.");
      return null;
    }
  }
  
  /**
   * Method to get total degree (weight) of a relationship path
   * Returns the weight/cost/degree of that relationship
   * Returns a negative int if not successful
   * 
   * @param name1
   * @param name2
   * @return
   */
  public static int getTotalDegreeOfRelationship(String name1, String name2) {
    if (database.hasPerson(name1) && database.hasPerson(name1))
      return database.getRelationshipWeight(name1, name2);
    else { 
      if (!(database.hasPerson(name1)))
        System.out.println("Person1 does not exist in the database.");
      else
        System.out.println("Person2 does not exist in the database.");
      
      //returns a negative number to indicate method failed
      return -1;
    }
  }
  
  /**
   * Method to return total number of people in database
   * 
   * @return
   */
  public static int totalNumOfPerson() {
    return database.getNumberOfPersons();
  }
  
  /**
   * Method to return total number of relationships in database
   * 
   * @return
   */
  public static int totalNumOfRelationships() {
    return database.getNumberOfRelationships();
  }
}
