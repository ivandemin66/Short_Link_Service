public class ValidationUser {
    public static boolean isValidLink(String originalUrl) {
        if (originalUrl.length() < 10) {
            return false;
        }

        if (originalUrl.length() > 300) {
            return false;
        }


        // Проверяем, что строка не состоит только из цифр
        if (originalUrl.matches("\\d+")) {
            return false;
        }

        String urlRegex = "^https://.*";
        if (!originalUrl.matches(urlRegex)) {
            return false;
        }
        return !originalUrl.trim().isEmpty();
    }

    public static boolean isValidClickLimit(int clicks) {
        return clicks > 0 && clicks <= Config.getMaxClicks();
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

    public static boolean isValidCorrectShortUrl (String shortLink) {
        if (shortLink.length() < 10) {
            return false;
        }

        if (shortLink.trim().isEmpty()) {
            return false;
        }
            return shortLink.startsWith("clck.ru/") && shortLink.length() > 10;
    }

}
