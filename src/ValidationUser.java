public class ValidationUser {
    public static boolean isValidLink(String originalUrl) {
        if (originalUrl.length() < 10) {
            return false;
        }

        // Проверяем, что строка не состоит только из цифр
        if (originalUrl.matches("\\d+")) {
            return false;
        }

        return !originalUrl.trim().isEmpty();
    }

    public static boolean isValidClickLimit(int clickThroughLimit) {
        return clickThroughLimit >= 1 && clickThroughLimit <= 100;
    }

    public static boolean isValidUUID(String userID) {
        if (userID == null || userID.trim().isEmpty()) {
            return false;
        }

        if (userID.length() <= 20) {
            return false;
        }

        if (userID.contains(":") || userID.contains(" ") || userID.contains("\n") || userID.contains("\r")) {
            return false;
        }

        if (userID.matches("\\d+")) {
            return false;
        }

        return true;

    }

    // Если создан 1й ID без короткой ссылки (пустой), то нельзя следом создать новый
    public static boolean isValidEmptyID () {
    return true;
    }

    // Метод сравнения соотношения одного ID (если у 1го ID не привязаны кор. ссылки)
    //    // с другим 2ID (у него привязаны ссылки), чтобы к пустому 1ID не привязывалась ссылка
    //    // от второго ID
    public static boolean isValidRatioUrlOrID () {
    return true;
    }

    // Сопоставление двух ID, чтобы один не имел возможности переписать второй
    public  static boolean isValidRatioID () {
        return true;
    }

}
