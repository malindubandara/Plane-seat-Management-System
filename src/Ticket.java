import java.io.FileWriter;
import java.io.IOException;

public class Ticket {

    private String row;
    private int seat;
    private double price;
    private Person person;

    public Ticket(String row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getRow() {
        return row;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getSeat() {
        return seat;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void ticketInfo() {
        System.out.println("row - " + this.row);
        System.out.println("seat number - " + this.seat);
        System.out.println("Ticket Price is £" + this.price);
        System.out.println("Passenger Details -");
        person.printInfo();
        System.out.println("-----------------------------------");
    }

    public void save() {
        String fileName = row + seat + ".txt"; // File name based on row and seat number
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("Ticket Information:\n");
            writer.write("Row: " + row + "\n");
            writer.write("Seat: " + seat + "\n");
            writer.write("Price: £" + price + "\n");
            writer.write("-------------------\n");
            writer.write("Person Information:\n");
            writer.write("Name: " + person.getname() + "\n");
            writer.write("Surname: " + person.getSurName() + "\n");
            writer.write("Email: " + person.getEmail() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error occurred");
        }
    }
}