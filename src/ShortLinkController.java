import java.util.Scanner;

public class ShortLinkController {
    private final ShortLinkService service;
    private final Scanner scanner = new Scanner(System.in);

    public ShortLinkController(ShortLinkService service) {
        this.service = service;
    }

    public void run() {
        System.out.println("Рад вас видеть в сервисе по сокращению ссылок!");
        while (true) {
            System.out.println("\n Выберите необходимое действие:");
            System.out.println("1. Создать короткую ссылку");
            System.out.println("2. Изменить параметры ссылки");
            System.out.println("3. Удалить короткую ссылку");
            System.out.println("4. Выйти");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    createLink();
                    break;
                case "2":
                    redirectLink();
                    break;
                case "3":
                    deleteLink();
                    break;
                case "4":
                    System.out.println("Всего хорошего!");
                    return;
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова");
            }
        }
    }

    private void createLink() {
        System.out.println("Введите пользовательский ID: ");
        String userId = scanner.nextLine();
        System.out.println("Вставьте оригинальную ссылку: ");
        String originalUrl = scanner.nextLine();
        System.out.println("Установите максимальное количество кликов: ");
        int maxClicks = Integer.parseInt(scanner.nextLine());

        String shortLink = service.createShortLink(userId, originalUrl, maxClicks);
        System.out.println("Короткая ссылка создана: " + shortLink);
    }

    private void redirectLink() {
        System.out.println("Введите короткую ссылку: ");
        String shortLink = scanner.nextLine();

        try {
            String originalUrl = service.redirect(shortLink);
            System.out.println("Перенаправление на: " + originalUrl);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteLink() {
        System.out.println("Введите свой ID: ");
        String userId = scanner.nextLine();
        System.out.println("Введите короткую ссылку: ");
        String shortLink = scanner.nextLine();

        if (service.deleteLink(shortLink, userId)) {
            System.out.println("Ссылка успешно удалена!");
        } else  {
            System.out.println("Не удалось удалить ссылку.");
        }
    }

}
