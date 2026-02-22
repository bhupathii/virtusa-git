public class VideoStore {
    private Video[] store;
    private int videoCount;

    public VideoStore() {
        store = new Video[100];
        videoCount = 0;
    }

    public void addVideo(String name) {
        Video video = new Video(name);
        store[videoCount] = video;
        videoCount++;
        System.out.println("Video \"" + name + "\" added successfully.");
    }

    public void doCheckout(String name) {
        for (int i = 0; i < videoCount; i++) {
            if (store[i].getName().equals(name)) {
                store[i].doCheckout();
                System.out.println("Video \"" + name + "\" checked out successfully.");
                return;
            }
        }
    }

    public void doReturn(String name) {
        for (int i = 0; i < videoCount; i++) {
            if (store[i].getName().equals(name)) {
                store[i].doReturn();
                System.out.println("Video \"" + name + "\" returned successfully.");
                return;
            }
        }
    }

    public void receiveRating(String name, int rating) {
        for (int i = 0; i < videoCount; i++) {
            if (store[i].getName().equals(name)) {
                store[i].receiveRating(rating);
                System.out.println("Rating \"" + rating + "\" has been mapped to the Video \"" + name + "\".");
                return;
            }
        }
    }

    public void listInventory() {
        System.out.println("Video Name | Checkout Status | Rating");
        for (int i = 0; i < videoCount; i++) {
            System.out.println(store[i].getName() + " | " + store[i].getCheckout() + " | " + store[i].getRating());
        }
    }
}
