package P1;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static void showCommands() {
        System.out.println("help         - Displays this list of commands");
        System.out.println("add          - Adds a new person (registration)");
        System.out.println("check        - Checks if a person is registered for the event");
        System.out.println("remove       - Removes an existing person from the list");
        System.out.println("update       - Updates a person's details");
        System.out.println("guests       - List of people attending the event");
        System.out.println("waitlist     - People on the waiting list");
        System.out.println("available    - Number of available spots");
        System.out.println("guests_no    - Number of people attending the event");
        System.out.println("waitlist_no  - Number of people on the waiting list");
        System.out.println("subscribe_no - Total number of registered people");
        System.out.println("search       - Searches all guests based on the entered string");
        System.out.println("save         - Saves the guest list");
        System.out.println("restore      - Restores the list with previously saved information");
        System.out.println("reset        - Deletes saved guest information");
        System.out.println("quit         - Closes the application");
    }

    private static void addNewGuest(Scanner sc, GuestsList list) {
        System.out.println("Introduce the first name:");
        String firstName = sc.nextLine() ;
        System.out.println("Introduce the last name:");
        String lastName = sc.nextLine();
        System.out.println("Introduce the email address:");
        String email = sc.nextLine();
        System.out.println("Introduce the phone number:");
        String phoneNumber = sc.nextLine();

        Guest g  = new Guest(firstName, lastName, email, phoneNumber);
        list.add(g);
    }

    private static void checkGuest(Scanner sc, GuestsList list) {
        int opt;
        while (true) {
            opt = sc.nextInt();
            sc.nextLine();

            if (opt < 1 || opt > 3) System.out.println("Please enter an integer between 1 and 3.");
            else break;
        }

        Guest foundGuest = null;

        switch (opt) {
            case 1 :
                String firstName = sc.nextLine();
                String lastName = sc.nextLine();
                foundGuest = list.search(firstName, lastName);
                break;
            case 2:
            case 3:
                String match = sc.nextLine();
                foundGuest = list.search(opt, match);
                break;
        }
        if (foundGuest == null) System.out.println("Not found");
        else System.out.println(foundGuest);
    }

    private static void removeGuest(Scanner sc, GuestsList list) {
        System.out.println("An existing person is deleted from the list...");
        int opt;
        while (true) {
            System.out.println("Choose the authentication method by typing:\n" +
                    "\"1\" - Name and surname\n" +
                    "\"2\" - Email\n" +
                    "\"3\" - Phone number");
            opt = sc.nextInt();
            sc.nextLine();

            if (opt < 1 || opt > 3) System.out.println("Please enter an integer between 1 and 3.");
            else break;
        }

        switch (opt) {
            case 1:
                System.out.println("Introduce first name:");
                String firstName = sc.nextLine();
                System.out.println("Intoduce last name:");
                String lastName = sc.nextLine();
                list.remove(firstName, lastName);
                break;
            case 2:
                System.out.println("Introduce the email address:");
                String match = sc.nextLine();
                list.remove(opt, match);
                break;
            case 3:
                System.out.println("Introduce the phone number:");
                match = sc.nextLine();
                list.remove(opt, match);
                break;
        }
    }

    private static void updateGuest(Scanner sc, GuestsList list) {
        System.out.println("Updating a person's details...");
        int opt;
        while (true) {
            System.out.println("Choose the authentication method by typing:\n" +
                    "\"1\" - Fisrt name and Last name\n" +
                    "\"2\" - Email\n" +
                    "\"3\" - Phone number");
            opt = sc.nextInt();
            sc.nextLine();

            if (opt < 1 || opt > 3) System.out.println("Please enter an integer between 1 and 3.");
            else break;
        }

        Guest foundGuest = null;

        switch (opt) {
            case 1:
                System.out.println("Introduce the first name:");
                String firstName = sc.nextLine();
                System.out.println("Introduce the last name:");
                String lastName = sc.nextLine();
                foundGuest = list.search(firstName, lastName);
                break;
            case 2:
                System.out.println("Introduce the email address:");
                String match = sc.nextLine();
                foundGuest = list.search(opt, match);
                break;
            case 3:
                System.out.println("Introduce the phone number:");
                match = sc.nextLine();
                foundGuest = list.search(opt, match);
                break;
        }
        // If we have no results, exit
        if (foundGuest == null) {
            System.out.println("The person is not registered for the event.");
            return;
        }
        while (true) {
            System.out.println("Choose the field to update by typing:\n" +
                    "\"1\" - First name\n" +
                    "\"2\" - Last name\n" +
                    "\"3\" - Email\n" +
                    "\"4\" - Phone number");
            opt = sc.nextInt();
            sc.nextLine();

            if (opt < 1 || opt > 4) System.out.println("Please enter an integer between 1 and 4.");
            else break;
        }

        switch (opt) {
            case 1:
                System.out.println("Introduce the first name:");
                foundGuest.setFirstName(sc.nextLine());
                break;
            case 2:
                System.out.println("Introduce the last name:");
                foundGuest.setLastName(sc.nextLine());
                break;
            case 3 :
                System.out.println("Introduce the email address:");
                foundGuest.setEmail(sc.nextLine());
                break;
            case 4:
                System.out.println("Introduce the phone number:");
                foundGuest.setPhoneNumber(sc.nextLine());
                break;
        }
    }

    private static void searchList(Scanner sc, GuestsList list) {
        String match = sc.nextLine();
        List<Guest> results = list.partialSearch(match);
        for (Guest g : results)
            System.out.println(g.toString());
        if (results.size() == 0)
            System.out.println("Nothing found");

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome! Please enter the number of available spots:");
        int size = scanner.nextInt();
        scanner.nextLine();

        GuestsList list = new GuestsList(size);

        boolean running = true;
        while (running) {
            System.out.println("Waiting for your order: (help - Show the list of orders)");
            String command = scanner.nextLine();

            switch (command) {
                case "help":
                    showCommands();
                    break;
                case "add":
                    addNewGuest(scanner, list);
                    break;
                case "check":
                    checkGuest(scanner, list);
                    break;
                case "remove":
                    removeGuest(scanner, list);
                    break;
                case "update":
                    updateGuest(scanner, list);
                    break;
                case "guests":
                    list.showGuestsList();
                    break;
                case "waitlist":
                    list.showWaitingList();
                    break;
                case "available":
                    System.out.println("Number of spots remaining: " + list.numberOfAvailableSpots());
                    break;
                case "guests_no":
                    System.out.println("Number of participants " + list.numberOfGuests());
                    break;
                case "waitlist_no":
                    System.out.println("Size of the waiting list: " + list.numberOfPeopleWaiting());
                    break;
                case "subscribe_no":
                    System.out.println("Total number of people: " + list.numberOfPeopleTotal());
                    break;
                case "search":
                    searchList(scanner, list);
                    break;
                case "quit":
                    System.out.println("The application is closing...");
                    scanner.close();
                    running = false;
                    break;
                default:
                    System.out.println("The command you have entered is not valid.");
                    System.out.println("Please try again.");

            }
        }
    }
}