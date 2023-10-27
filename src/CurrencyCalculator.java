import java.text.DecimalFormat;
import java.util.Scanner;

public class CurrencyCalculator {
    private static double KRW_RATE;
    private static double KRW_SELL_RATE; // USD -> KRW 환율 (파는 가격)
    private static double KRW_BUY_RATE;  // KRW -> USD 환율 (사는 가격)
    private static double USD_RATE;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // 실시간 환율 정보를 가져옵니다.
            KRW_RATE = APIFetcher.fetchUSDKRW();
            KRW_SELL_RATE = APIFetcher.fetchTTBuyingPrice();  // USD -> KRW
            KRW_BUY_RATE =  1/APIFetcher.fetchTTSellingPrice() ;// KRW -> USD 환율 계산
            USD_RATE = APIFetcher.fetchTTSellingPrice();
            String currentDate = APIFetcher.fetchUSDDate();
            String currentTime = APIFetcher.fetchUSDTime();

            System.out.println("------------------ 환율 계산기 ------------------");
            System.out.println("현재 날짜와 시간: " + currentDate + " " + currentTime);
            System.out.println(" ");
            System.out.println("[ 환율정보 ]");
            System.out.println(" ");
            System.out.println("현재환율 : " + KRW_RATE + "원");
            System.out.println(" ");
            System.out.println("달러를 살때 " +  USD_RATE+ "원");
            System.out.println(" ");
            System.out.println("달러를 팔때 " + KRW_SELL_RATE + "원");
            System.out.println(" ");
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
        System.out.println("달러를 팔기 " + KRW_SELL_RATE + "원");
        System.out.println("1. USD -> KRW : 달러 팔기");
        System.out.println(" ");
        System.out.println("달러를 사기 " +  USD_RATE+ "원");
        System.out.println("2. KRW -> USD : 달러 사기");
        System.out.print("원하시는 항복을 선택해주세요: ");
        int countryChoice = scanner.nextInt();
        System.out.print("환전하실 금액을 입력하세요: ");
        double amount = scanner.nextDouble();

        double result = 0;
        switch (countryChoice) {
            case 1:
                result = amount *  KRW_SELL_RATE; // USD -> KRW (파는 가격)
                break;
            case 2:
                result = amount * KRW_BUY_RATE;   // KRW -> USD (사는 가격)
                break;
            default:
                System.out.println("잘못된 선택입니다.");
                return;
        }

        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("변환된 금액은 " + formatter.format(result) + " 입니다.");
        System.out.println(" ");
        System.out.println(" ");
    }
}
