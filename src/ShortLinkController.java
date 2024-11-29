import java.util.Scanner;

public class ShortLinkController {
    private final ShortLinkService service;
    private final Scanner scanner = new Scanner(System.in);

    public ShortLinkController(ShortLinkService service) {
        this.service = service;
    }


}
