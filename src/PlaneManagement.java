import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PlaneManagement {
    private static String[][] seatArray = new String[4][]; // 2d jagged array
    static {
        seatArray[0] = new String[14];
        seatArray[1] = new String[12];
        seatArray[2] = new String[12];
        seatArray[3] = new String[14];
        String count = "O";
        for (int x = 0; x < seatArray.length; x++) {
            for (int y = 0; y < seatArray[x].length; y++)
                seatArray[x][y] = count;
        }
    }
    private static Ticket[] ticketArray = new Ticket[52]; // declare ticket array

    public static void main(String[] args) {

        System.out.println("Welcome to the Plane Management Application");
        menu();
    }

    private static void menu() {
        int option;
        do {
            Scanner menu = new Scanner(System.in);
            System.out.println("");
            System.out.println("*******************************************************");
            System.out.println("* MENU *");
            System.out.println("*******************************************************");
            System.out.println("");
            System.out.println(" 1) Buy a seat ");
            System.out.println(" 2) Cancel a seat ");
            System.out.println(" 3) Find first available seat ");
            System.out.println(" 4) Show seating plan ");
            System.out.println(" 5) Printing tickets information and total sales ");
            System.out.println(" 6) Search ticket ");
            System.out.println(" 0) Quit ");
            System.out.println("");
            System.out.println("*******************************************************");
            System.out.println("");
            try {
                System.out.println("Please select an option (1-6) [ Quit - 0 ] :");
                option = menu.nextInt();
            } catch (NoSuchElementException e) {
                System.out.println("Invalid Input");
                menu();
                return;
            }

            switch (option) {
                case 1:
                    buy_seat();
                    break;
                case 2:
                    cancel_seat();
                    break;
                case 3:
                    find_first_available();
                    break;
                case 4:
                    show_seating_plan();
                    break;
                case 5:
                    print_tickets_info();
                    break;
                case 6:
                    search_ticket();
                    break;
                case 0:
                    System.out.println("Shuting Down.......");
                    break;
                default:
                    System.out.println("Invalid option");

            }
        } while (option != 0);

    }

    private static int findEmptyTicketIndex() {
        for (int i = 0; i < ticketArray.length; i++) {
            if (ticketArray[i] == null) {
                return i;
            }
        }
        return -1; // No empty slot found
    }

    // Task 3
    private static void buy_seat() {
        Scanner read = new Scanner(System.in);

        System.out.println("Enter your Name -");
        String name = read.nextLine();
        System.out.println("Enter your Surname -");
        String surName = read.nextLine();

        String email; // check email validation
        while (true) {
            System.out.println("Enter your email - ");
            email = read.next();
            if (!email.contains("@")) {
                System.out.println("email invalid");
            } else {
                break;
            }
        }

        Person person = new Person(name, surName, email); // caling person class

        int index = findEmptyTicketIndex();
        if (index == -1) {
            System.out.println("Maximum ticket limit reached.");
            return;
        }

        try {
            System.out.println("Enter seat row letter (A/B/C/D) -");
            char rowLetter = read.next().charAt(0);
            rowLetter = Character.toUpperCase(rowLetter);
            // Convert row letter to array index
            int rowNumber = rowLetter - '0';
            rowNumber = rowNumber - 17;

            if (rowLetter == 'A' || rowLetter == 'D') {
                System.out.print("Enter seat number (1-14) - ");
            } else {
                System.out.println("Enter sear number (1-12) - ");
            }
            int seatNum = read.nextInt();

            if (rowLetter == 'A' || rowLetter == 'D') {
                if (seatNum > 0 && seatNum <= 14) {
                    if (seatArray[rowNumber][seatNum - 1] != "X") {
                        System.out.println("You booked " + rowLetter + " " + seatNum + " seat");
                    } else {
                        System.out.println(rowLetter + " " + seatNum + " seat is not available");
                        return;
                    }

                } else {
                    System.out.println("Invalid seat num");
                    return;
                }
            } else if (rowLetter == 'B' || rowLetter == 'C') {

                if (seatNum > 0 && seatNum <= 12) {
                    if (seatArray[rowNumber][seatNum - 1] != "X") {
                        System.out.println("you booked " + rowLetter + " " + seatNum + " seat");
                    } else {
                        System.out.println(rowLetter + " " + seatNum + " seat is not available");
                        return;
                    }

                } else {
                    System.out.println("Invalid seat num");
                    return;
                }
            } else {
                System.out.println("Invalid row letter");
                return;
            }

            int ticketPrice;
            if (seatNum > 0 && seatNum < 6) {
                ticketPrice = 200;
            } else if (seatNum < 10) {
                ticketPrice = 150;
            } else {
                ticketPrice = 180;
            }

            // append to array
            ticketArray[index] = new Ticket(String.valueOf(rowLetter), seatNum, ticketPrice, person);
            // write file
            ticketArray[index].save();
            // creat seating position
            seatArray[rowNumber][seatNum - 1] = "X";

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("check Inputs");
            return;
        } catch (InputMismatchException e) {
            System.out.println("check Inputs");
            return;
        }

    }

    // Task 4
    private static void cancel_seat() {

        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Enter row letter (A/B/C/D) - ");

            char rowLetter = input.next().charAt(0);
            rowLetter = Character.toUpperCase(rowLetter);
            // Convert row letter to array index
            int rowNumber = rowLetter - '0';
            rowNumber = rowNumber - 17;
            if (rowLetter == 'A' || rowLetter == 'D') {
                System.out.print("Enter seat number (1-14) - ");
            } else {
                System.out.println("Enter sear number (1-12) - ");
            }
            int seatNum = input.nextInt();

            if (rowLetter == 'A' || rowLetter == 'D') {

                if (seatNum > 0 && seatNum <= 14) {
                    if (seatArray[rowNumber][seatNum - 1] == "X") {
                        System.out.println("You cancle " + rowLetter + " " + seatNum + " seat");
                    } else {
                        System.out.println(rowLetter + " " + seatNum + " seat is already available");
                    }
                } else {
                    System.out.println("Invalid seat num");
                }
            } else if (rowLetter == 'B' || rowLetter == 'C') {
                if (seatNum > 0 && seatNum <= 12) {
                    if (seatArray[rowNumber][seatNum - 1] == "X") {
                        System.out.println("You cancle " + rowLetter + " " + seatNum + " seat");
                    } else {
                        System.out.println(rowLetter + " " + seatNum + " seat is already available");
                    }
                } else {
                    System.out.println("Invalid seat num");
                }
            } else {
                System.out.println("Invalid row letter");
            }

            for (int i = 0; i < ticketArray.length; i++) {
                if (ticketArray[i] != null && ticketArray[i].getRow().equals(String.valueOf(rowLetter))
                        && ticketArray[i].getSeat() == seatNum) {
                    ticketArray[i] = null;
                    break;
                }
            }

            seatArray[rowNumber][seatNum - 1] = "O";

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("check Inputs");
        } catch (InputMismatchException e) {
            System.out.println("check Inputs");
            return;
        }

    }

    // Task 5
    private static void find_first_available() {
        for (int x = 0; x < seatArray.length; x++) {
            for (int y = 0; y < seatArray[x].length; y++) {
                if (seatArray[x][y] == "O") {
                    char row = (char) ('A' + x);
                    int seatNum = y + 1;
                    System.out.println("First available seat found at row " + row + ", seat " + seatNum);
                    return;
                }
            }
        }
        System.out.println("No available seats found.");
    }

    // Task 6
    private static void show_seating_plan() {
        System.out.println("--------------------");
        System.out.println("Seating Plan");
        System.out.println("--------------------");

        for (int x = 0; x < seatArray.length; x++) {
            for (int y = 0; y < seatArray[x].length; y++) {
                if (y > 6 && y < 8) {
                    System.out.print(seatArray[x][y] + "   ");
                } else {
                    System.out.print(seatArray[x][y] + " ");

                }

            }
            System.out.println("");
        }
    }

    // Task 10
    private static void print_tickets_info() {

        double totalAmount = 0;
        for (Ticket ticket : ticketArray) {
            if (ticket != null) {
                ticket.ticketInfo();
                System.out.println("---------------------------------");
                totalAmount += ticket.getPrice();
            }
        }
        System.out.println("Total amount for all tickets - £" + totalAmount);
    }

    // Task 11
    private static void search_ticket() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter seat row letter (A/B/C/D) -");
            char rowLetter = scanner.next().charAt(0);
            rowLetter = Character.toUpperCase(rowLetter);
            if (rowLetter == 'A' || rowLetter == 'D') {
                System.out.print("Enter seat number (1-14) - ");
            } else {
                System.out.println("Enter sear number (1-12) - ");
            }
            int seatNum = scanner.nextInt();

            if (rowLetter == 'A' || rowLetter == 'D') {

                if (seatNum > 0 && seatNum <= 14) {
                    System.out.println("Ticket Found....");
                } else {
                    System.out.println("Invalid seat num");
                    return;
                }
            } else if (rowLetter == 'B' || rowLetter == 'C') {
                if (seatNum > 0 && seatNum <= 12) {
                    System.out.println("Search Results...");
                } else {
                    System.out.println("Invalid seat num");
                    return;
                }
            } else {
                System.out.println("Invalid Inputs");
                return;
            }

            boolean found = false;
            for (Ticket ticket : ticketArray) {
                if (ticket != null) {
                    if (ticket.getRow().equals(String.valueOf(rowLetter)) && ticket.getSeat() == seatNum) {
                        ticket.ticketInfo();
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                System.out.println("This seat is available.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Check Inputs");
        }
    }
}
