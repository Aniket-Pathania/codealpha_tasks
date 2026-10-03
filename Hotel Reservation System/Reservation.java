import java.io.FileWriter;
import java.io.IOException;

public class Reservation {

    int reservationId;
    String customerName;
    Room room;
    boolean paymentDone;

    public Reservation(
            int reservationId,
            String customerName,
            Room room
    ) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.room = room;
        this.paymentDone = false;
    }

    public void makePayment() {

        paymentDone = true;

        System.out.println("\nPayment successful!");
        System.out.println("Amount Paid: ₹" + room.price);
    }

    public void saveToFile() {

        try {

            FileWriter writer = new FileWriter(
                    "reservations.txt",
                    true
            );

            writer.write(
                    "Reservation ID: " + reservationId + "\n"
            );

            writer.write(
                    "Customer Name: " + customerName + "\n"
            );

            writer.write(
                    "Room Number: " + room.roomNumber + "\n"
            );

            writer.write(
                    "Room Category: " + room.category + "\n"
            );

            writer.write(
                    "Room Price: ₹" + room.price + "\n"
            );

            writer.write(
                    "Payment Status: "
                            + (paymentDone ? "Paid" : "Pending")
                            + "\n"
            );

            writer.write(
                    "---------------------------------\n"
            );

            writer.close();

            System.out.println(
                    "Reservation saved to file."
            );

        } catch (IOException e) {

            System.out.println(
                    "Error while saving reservation."
            );
        }
    }

    public void displayReservation() {

        System.out.println("\n========== RESERVATION ==========");
        System.out.println("Reservation ID : " + reservationId);
        System.out.println("Customer Name  : " + customerName);
        System.out.println("Room Number    : " + room.roomNumber);
        System.out.println("Room Category  : " + room.category);
        System.out.println("Room Price     : ₹" + room.price);

        if (paymentDone) {
            System.out.println("Payment Status : Paid");
        } else {
            System.out.println("Payment Status : Pending");
        }

        System.out.println("=================================");
    }
}