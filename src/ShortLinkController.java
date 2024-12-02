import java.util.Scanner;
import java.util.UUID;

public class ShortLinkController {
    private final ShortLinkService service;
    private final Scanner scanner = new Scanner(System.in);

    public ShortLinkController(ShortLinkService service) {
        this.service = service;
    }

    public void run() {
        System.out.println("Рад вас видеть в сервисе по сокращению ссылок!");
        while (true) {
            System.out.println("""
            \n Выберите необходимое действие: 
            1. Сгенерировать пользовательский ID
            2. Создать короткую ссылку
            3. Перейти по короткой ссылке
            4. Удалить короткую ссылку
            5. Изменить параметры короткой ссылки
            6. Сменить пользователя
            7. Завершить работу""");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    generateID();
                    break;
                case "2":
                    createLink();
                    break;
                case "3":
                    redirectLink();
                    break;
                case "4":
                    deleteLink();
                    break;
                case "5":
                    changeParams();
                    break;
                case "6":
                    changeUser();
                    break;
                case "7":
                    System.out.println("Всего хорошего!");
                    return;
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова");
            }
        }
    }



    public void generateID() {
        UUID userUUID = UUID.randomUUID();
        System.out.println("Сгенерированный ID: " + userUUID);
    }

    public void createLink() {
        System.out.println("Введите пользовательский ID: ");
        String userId = scanner.nextLine();
        if (ValidationUser.isValidUUID(userId)) {
            System.out.println("Вы ввели корректный ID");
        } else {
            System.out.println("Вы ввели неподдерживаемый ID. Введите правильный ID");
            return;
        }
        System.out.println("Вставьте оригинальную ссылку: ");
        String originalUrl = scanner.nextLine();
        if (ValidationUser.isValidLink(originalUrl)) {
            System.out.println("Ссылка валидна.");
        } else {
            System.out.println("Ссылка недействительна. Она должна быть длиной не менее 10 символов и не состоять только из цифр.");
            return;
        }

        System.out.println("Установите максимальное количество кликов: ");
        int maxClicks = Integer.parseInt(scanner.nextLine());

        LinkResponce response = service.createShortLink(userId, originalUrl, maxClicks);
        System.out.println("Короткая ссылка создана: " + response.getShortLink());
        System.out.println("User UUID: " + response.getUserUUID());
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
        if (ValidationUser.isValidUUID(userId)) {
            System.out.println("Вы ввели правильный ID");
        } else {
            System.out.println("Вы ввели неподдерживаемый ID. Введите правильный ID");
            return;
        }
        System.out.println("Введите короткую ссылку: ");
        String shortLink = scanner.nextLine();

        if (service.deleteLink(shortLink, userId)) {
            System.out.println("Ссылка успешно удалена!");
        } else  {
            System.out.println("Не удалось удалить ссылку.");
        }
    }

    private void changeParams() {
        System.out.println("Введите свой ID: ");
        String userId = scanner.nextLine();
        if (ValidationUser.isValidUUID(userId)) {
            System.out.println("Вы ввели корректный ID");
        } else {
            System.out.println("Вы ввели неподдерживаемый ID. Введите правильный ID");
            return;
        }
        System.out.println("Введите короткую ссылку: ");
        String shortLink = scanner.nextLine();
        System.out.println("Установите новое максимальное количество кликов: ");
        int newClicks = Integer.parseInt(scanner.nextLine());

        if (service.updateLinkParams(shortLink, userId, newClicks)) {
            System.out.println("Параметры ссылки успешно обновлены!");
        } else {
            System.out.println("Не удалось обновить параметры ссылки.");
        }

        if (ValidationUser.isValidClickLimit(newClicks)) {
            System.out.println("Максимальное количество кликов валидно.");
        } else {
            System.out.println("Превышение максимально допустимого количества кликов");
            return;
        }





    }


    public void changeUser() {
        System.out.println("Введите другой пользовательский ID: ");
        String newUserId = scanner.nextLine();
        if (ValidationUser.isValidUUID(newUserId)) {
            System.out.println("Вы ввели ID, соответствующей условиям проверки");
        } else {
            System.out.println("Вы ввели неподдерживаемый ID. Введите правильный ID");
            return;
        }
        service.changeUser(newUserId);
        System.out.println("Пользовательский ID изменен на: " + newUserId);
    }

}
//https://apps.skillfactory.ru/learning/course/course-v1:SkillFactory+MIFIDEV+SEP2024/home
// https://roadmap.sh/backend

//user1 = 1d9ab7f6-de05-46b7-a0c2-e4bfe080f257
//clck.ru/405e5e6

//user2 = cee74897-2ba5-4156-b65d-d8d92ff98f59
//clck.ru/2a1422