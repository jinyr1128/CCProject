import java.text.DecimalFormat;
import java.util.Scanner;

public class CurrencyCalculator {
    private static double KRW_RATE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // 실시간 환율 정보를 가져옵니다.
            KRW_RATE = APIFetcher.fetchUSDKRW();
            String currentDate = APIFetcher.fetchUSDDate();
            String currentTime = APIFetcher.fetchUSDTime();

            System.out.println("------------------ 환율 계산기 ------------------");
            System.out.println("현재 날짜와 시간: " + currentDate + " " + currentTime);
            System.out.println("현재 USD -> KRW 환율: " + KRW_RATE);
            System.out.println("1. 환율 계산하기");
            System.out.println("2. 종료하기");
            System.out.print("\n원하시는 메뉴 번호를 선택해주세요: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    calculateCurrency(scanner);
                    break;
                case 2:
                    System.out.println("환율 계산기를 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
                    break;
            }
        }
    }

    private static void calculateCurrency(Scanner scanner) {
        System.out.println("1. 한국 (KRW)");
        System.out.print("수취 국가를 선택해주세요: ");
        int countryChoice = scanner.nextInt();
        System.out.print("송금액(USD)를 입력하세요: ");
        double amount = scanner.nextDouble();

        double result = 0;
        switch (countryChoice) {
            case 1:
                result = amount * KRW_RATE;
                break;
            default:
                System.out.println("잘못된 선택입니다.");
                return;
        }

        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        System.out.println("변환된 금액은 " + formatter.format(result) + " 입니다.");
    }
}
