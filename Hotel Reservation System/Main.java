import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Reservation> reservations = new ArrayList<>();

        // Creating hotel rooms
        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));
        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));
        rooms.add(new Room(301, "Suite", 4000));

        int choice = 0;
        int nextReservationId = 1001;

        while (choice != 6) {

            System.out.println("\n====================================");
            System.out.println("       HOTEL RESERVATION SYSTEM");
            System.out.println("====================================");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Search Rooms");
            System.out.println("3. Book a Room");
            System.out.println("4. View Reservations");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Exit");
            System.out.println("====================================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            // 1. View available rooms
            if (choice == 1) {

                System.out.println("\n========== AVAILABLE ROOMS ==========");

                boolean found = false;

                for (Room room : rooms) {

                    if (room.available) {
                        room.displayRoom();
                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("No rooms are currently available.");
                }
            }

            // 2. Search rooms
            else if (choice == 2) {

                System.out.print(
                        "\nEnter category (Standard/Deluxe/Suite): "
                );

                String searchCategory = sc.nextLine();

                boolean found = false;

                System.out.println("\n========== SEARCH RESULTS ==========");

                for (Room room : rooms) {

                    if (room.category.equalsIgnoreCase(searchCategory)
                            && room.available) {

                        room.displayRoom();
                        found = true;
                    }
                }

                if (!found) {
                    System.out.println(
                            "No available rooms found in this category."
                    );
                }
            }

            // 3. Book a room
            else if (choice == 3) {

                System.out.println("\n========== BOOK A ROOM ==========");

                System.out.print("Enter room number: ");
                int roomNumber = sc.nextInt();
                sc.nextLine();

                Room selectedRoom = null;

                for (Room room : rooms) {

                    if (room.roomNumber == roomNumber) {
                        selectedRoom = room;
                        break;
                    }
                }

                if (selectedRoom == null) {

                    System.out.println("Room not found.");

                } else if (!selectedRoom.available) {

                    System.out.println(
                            "Sorry, this room is already booked."
                    );

                } else {

                    System.out.print("Enter customer name: ");
                    String customerName = sc.nextLine();

                    Reservation reservation =
                            new Reservation(
                                    nextReservationId,
                                    customerName,
                                    selectedRoom
                            );

                    reservations.add(reservation);

                    selectedRoom.available = false;

                    System.out.println(
                            "\nRoom booked successfully!"
                    );

                    System.out.println(
                            "Your Reservation ID is: "
                                    + nextReservationId
                    );

                    System.out.print(
                            "Do you want to make payment? (yes/no): "
                    );

                    String paymentChoice = sc.nextLine();

                    if (paymentChoice.equalsIgnoreCase("yes")) {

                        reservation.makePayment();

                    } else {

                        System.out.println(
                                "\nPayment is pending."
                        );
                    }

                    reservation.displayReservation();

                    reservation.saveToFile();

                    nextReservationId++;
                }
            }

            // 4. View reservations
            else if (choice == 4) {

                if (reservations.isEmpty()) {

                    System.out.println(
                            "\nNo reservations found."
                    );

                } else {

                    System.out.println(
                            "\n========== ALL RESERVATIONS =========="
                    );

                    for (Reservation reservation : reservations) {
                        reservation.displayReservation();
                    }
                }
            }

            // 5. Cancel reservation
            else if (choice == 5) {

                if (reservations.isEmpty()) {

                    System.out.println(
                            "\nNo reservations available to cancel."
                    );

                } else {

                    System.out.print(
                            "\nEnter reservation ID to cancel: "
                    );

                    int reservationId = sc.nextInt();

                    Reservation reservationToCancel = null;

                    for (Reservation reservation : reservations) {

                        if (reservation.reservationId
                                == reservationId) {

                            reservationToCancel = reservation;
                            break;
                        }
                    }

                    if (reservationToCancel != null) {

                        reservationToCancel.room.available = true;

                        reservations.remove(reservationToCancel);

                        System.out.println(
                                "\nReservation cancelled successfully!"
                        );

                        System.out.println(
                                "Room "
                                        + reservationToCancel.room.roomNumber
                                        + " is now available."
                        );

                    } else {

                        System.out.println(
                                "\nReservation not found."
                        );
                    }
                }
            }

            // 6. Exit
            else if (choice == 6) {

                System.out.println(
                        "\nThank you for using Hotel Reservation System!"
                );
            }

            // Invalid choice
            else {

                System.out.println(
                        "\nInvalid choice! Please select 1 to 6."
                );
            }
        }

        sc.close();
    }
}
