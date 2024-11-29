public class ShortLinkApp {
    public static void main(String[] args) {
        ShortLinkService shortLinkService = new ShortLinkService(new InMemoryLinkRepository());
        ShortLinkController shortLinkController = new ShortLinkController(shortLinkService);

        //Запуск программы отсюда
        controller.run();
    }

}