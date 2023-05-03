package cinema;
import java.util.Scanner;

public class Cinema {

    public static void menu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static String[][] create_seats(int rows, int cols) {
        String[][] seats = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = "S";
            }
        }
        return seats;
    }

    public static void show_seats(String[][] seats) {
        System.out.println("Cinema:");
        for (int i = 0; i <= seats[0].length; i++) {
            if (i == 0) {
                System.out.print("  ");
            }
            else {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        for (int i = 0; i < seats.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seats[0].length; j++) {
                System.out.print(seats[i][j] + " ");
            }

            System.out.println();
        }
        System.out.println();
    }


    public static void statistics(String[][] seats) {
        int purchased_tick, total_seats, current_income, total_income;
        purchased_tick = 0;
        current_income = 0;
        total_income = 0;
        total_seats = seats.length * seats[0].length;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j] == "B") {
                    purchased_tick += 1;
                    current_income += check_price(i + 1, seats);
                }
                total_income += check_price(i + 1, seats);
            }
        }
        float percent = (float) purchased_tick / total_seats * 100;
        System.out.printf("Number of purchased tickets: %d\n", purchased_tick);
        System.out.printf("Percentage: %.2f%%\n", percent);
        System.out.printf("Current income: $%d\n", current_income);
        System.out.printf("Total income: $%d\n\n", total_income);

    }

    public static void buy_ticket(int row_num, int col_num, String[][] seats) {
        seats[row_num - 1][col_num - 1] = "B";
        int totalSeats = seats.length * seats[0].length;
        int price;
        if (totalSeats <= 60) {
            price = 10;
        }
        else if ( row_num <= seats.length / 2 ) {
            price = 10;
        }
        else {
            price = 8;
        }
        System.out.println("Ticket Price: $" + price);
        System.out.println();

    }


    public static int check_price(int row_num, String[][] seats) {
        int totalSeats = seats.length * seats[0].length;
        int price;
        if (totalSeats <= 60) {
            price = 10;
        } else if (row_num <= seats.length / 2) {
            price = 10;
        } else {
            price = 8;
        }
        return price;
    }

    public static void main(String[] args) {
        // Write your code here
//        System.out.println("Cinema:");
//        System.out.println("  1 2 3 4 5 6 7 8");
//        String row = "S ".repeat(8);
//        for (int i = 1; i < 8; i++) {
//            System.out.println(Integer.toString(i) + ' ' + row);
//        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = scanner.nextInt();

        String[][] seats = create_seats(rows, cols);

        menu();
        int option = scanner.nextInt();

        while (option != 0) {
            switch (option) {
                case 1:
                    show_seats(seats);
                    break;
                case 2:
                    System.out.println("Enter a row number:");
                    int row_num = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int col_num = scanner.nextInt();
                    while ( row_num > rows || col_num > cols || seats[row_num - 1][col_num - 1] == "B") {
                        if ( row_num > rows || col_num > cols ) {
                            System.out.println("Wrong input!");
                        } else {
                            System.out.println("That ticket has already been purchased!");

                        }

                        System.out.println();
                        System.out.println("Enter a row number:");
                        row_num = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        col_num = scanner.nextInt();
                    }
                    buy_ticket(row_num, col_num, seats);
                    break;
                case 3:
                    statistics(seats);
                    break;
                case 0:
                    break;
            }
            menu();
            option = scanner.nextInt();
        }

    }
}