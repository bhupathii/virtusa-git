import java.util.Scanner;

public class VideoLauncher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoStore store = new VideoStore();
        int choice;

        do {
            System.out.println("MAIN MENU");
            System.out.println("=========");
            System.out.println("1. Add Videos:");
            System.out.println("2. Check Out Video :");
            System.out.println("3. Return Video :");
            System.out.println("4. Receive Rating :");
            System.out.println("5. List Inventory :");
            System.out.println("6. Exit :");
            System.out.print("Enter your choice (1..6): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    System.out.print("Enter the name of the video you want to add: ");
                    String videoName = scanner.nextLine();
                    store.addVideo(videoName);
                    break;
                case 2:
                    System.out.print("Enter the name of the video you want to check out: ");
                    String checkoutName = scanner.nextLine();
                    store.doCheckout(checkoutName);
                    break;
                case 3:
                    System.out.print("Enter the name of the video you want to Return: ");
                    String returnName = scanner.nextLine();
                    store.doReturn(returnName);
                    break;
                case 4:
                    System.out.print("Enter the name of the video you want to Rate: ");
                    String rateName = scanner.nextLine();
                    System.out.print("Enter the rating for this video: ");
                    int rating = scanner.nextInt();
                    scanner.nextLine();
                    store.receiveRating(rateName, rating);
                    break;
                case 5:
                    store.listInventory();
                    break;
                case 6:
                    System.out.println("Exiting...!! Thanks for using the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        } while(choice != 6);

        scanner.close();
    }
}
