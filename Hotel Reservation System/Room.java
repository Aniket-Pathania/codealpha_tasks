public class Room {

    int roomNumber;
    String category;
    double price;
    boolean available;

    // Constructor
    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.available = true;
    }

    // Display room details
    public void displayRoom() {

        System.out.println(
                "Room " + roomNumber +
                        " | Category: " + category +
                        " | Price: ₹" + price +
                        " | Available: " + available
        );
    }
}