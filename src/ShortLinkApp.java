public class ShortLinkApp {
    public static void main(String[] args) {
        ShortLinkService shortLinkService = new ShortLinkService(new InMemoryLinkRepository());
        ShortLinkController controller = new ShortLinkController(shortLinkService);

        // Single line to start the program
        controller.run();
    }

}