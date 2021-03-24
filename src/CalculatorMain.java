import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CalculatorMain {

    static List<Double> argumentsList = new ArrayList<>();

    public static void main(String[] args) {

        startCalkulator();
    }

    private static void startCalkulator() {

        greeting();
        operatingModeSelection();
    }

    private static void greeting() {
        System.out.println("Proszę wybrać tryb działania aplikacji:");
        System.out.println("1 : Kalkulator");
        System.out.println("0 : Wyjście z aplikacji");
        System.out.println("Proszę podać swój wybór: ");
    }

    private static void operatingModeSelection() {

        Scanner scanner = new Scanner(System.in);
        String firstSelect = scanner.nextLine();
        switch (firstSelect) {
            case "1":
                enterQuantityArguments();
                break;
            case "0":
                System.out.println("Do widzenia");
                break;
            default:
                System.out.println("Wprowadż właściwy znak");
                operatingModeSelection();
        }
    }

    public static String enterSign() {

          String sign = "";

          System.out.println("Proszę wybrać rodzaj operacji:");
          System.out.println("+ : Dodawanie");
          System.out.println("- : Odejmowanie");
          System.out.println("* : Mnożenie");
          System.out.println("/ : Dzielenie");
          System.out.println("Proszę podać swój wybór: ");
          Scanner scanner = new Scanner(System.in);
          String unCheckingSign = scanner.nextLine();
          if (unCheckingSign.equals("*") || unCheckingSign.equals("/") || unCheckingSign.equals("+") || unCheckingSign.equals("-")) {
              sign = unCheckingSign;
          } else {
              System.out.println("Podany znak nie jest wlaściwym");
              enterQuantityArguments();
          }
          return sign;
    }

    private static void enterQuantityArguments() {
        System.out.println("Proszę wybrać tryb wprowadzania argumentów operacji: ");
        System.out.println("1 : Dwa argumenty (np. 2 + 2)");
        System.out.println("2 : Więcej niż dwa argumenty (np. 2 + 2 + 2 + 2 + 2 dla 5 argumentów)");
        System.out.println("3 : Nieokreślona liczba argumentów (aplikacja przyjmuje argumenty aż użytkownik nie przerwie wprowadzania)");
        System.out.println("0 : Wyjście z aplikacji");
        System.out.println("Proszę podać swój wybór: ");
        Scanner scanner = new Scanner(System.in);
        String quantityArguments = scanner.nextLine();

        switch (quantityArguments) {
            case "1":
                enterTwoArguments(enterSign());
                break;
            case "2":
                moreThanTwoArguments(enterSign());
                break;
            case "3":
                unlimitedAmountArguments(enterSign());
                break;
            case "0":
                System.out.println("Do widzenia");
                break;
            default:
                System.out.println("Zrób wybór");
                enterQuantityArguments();
        }
    }

    public static void enterTwoArguments(String sign) {

        if (!sign.equals("")) {
            Scanner scanner = new Scanner(System.in);
            try {
                for (int i = 1; i <= 2; i++) {
                    System.out.println("Proszę wprowadzić argument nr " + i);
                    double argument = scanner.nextDouble();
                    argumentsList.add(argument);
                }
                System.out.println(argumentsList);

                switch (sign) {
                    case "*":
                        multiplication();
                        break;
                    case "/":
                        division();
                        break;
                    case "+":
                        addition();
                        break;
                    case "-":
                        subtraction();
                        break;
                    default:
                        startCalkulator();
                }
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzony znak nie jest liczbą");
                argumentsList.clear();
                startCalkulator();
            }
        }
    }

    private static void moreThanTwoArguments(String sign) {

        if (!sign.equals("")) {
            System.out.println("Proszę wprowadzić liczbę argumentów większą dwóch");
            Scanner scanner = new Scanner(System.in);
            try {
                int nArguments = scanner.nextInt();

                if (nArguments > 2) {
                    for (int i = 1; i <= nArguments; i++) {
                        System.out.println("Proszę wprowadzić argument nr " + i);
                        double argument = scanner.nextDouble();
                        argumentsList.add(argument);
                    }
                    System.out.println(argumentsList);

                    switch (sign) {
                        case "*":
                            multiplication();
                            break;
                        case "/":
                            division();
                            break;
                        case "+":
                            addition();
                            break;
                        default:
                            subtraction();
                            break;
                    }
                } else {
                    System.out.println("Wprowadzono niepoprawną liczbę");
                    argumentsList.clear();
                    moreThanTwoArguments(enterSign());
                }
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzony znak nie jest liczbą");
                moreThanTwoArguments(enterSign());
            }
        }
    }

    private static void firstTwoArg() {
        System.out.println("Możesz wprowadzać dowolną liczbę argumentów");
        Scanner scanner = new Scanner(System.in);
        try {
            for (int i = 1; i <= 2; i++) {
                System.out.println("Proszę wprowadzić argument nr " + i +
                        " ('X' przerywa wprowadzanie (dopiero po wprowadzeniu co najmniej 2 argumentów))");
                String firstsTwoArguments = scanner.nextLine();
                double argDouble = Double.parseDouble(firstsTwoArguments);
                argumentsList.add(argDouble);
            }
        } catch (NumberFormatException e) {
            System.out.println("Koniecznie musisz wprowadzić liczbę");
            argumentsList.clear();
            firstTwoArg();
        }
    }

    private static void unlimitedAmountArguments(String sign) {

        if (!sign.equals("")) {
            firstTwoArg();

            Scanner scanner = new Scanner(System.in);
            try {
                for (int i = 3; i <= 1000; i++) {
                    System.out.println("Proszę wprowadzić argument nr " + i + " ('X' przepywa wprowadzanie)");
                    String argumentStr = scanner.nextLine();
                    if (!"X".equals(argumentStr)) {
                        double argument = Double.parseDouble(argumentStr);
                        argumentsList.add(argument);
                    } else {
                        System.out.println(argumentsList);
                        switch (sign) {
                            case "*":
                                multiplication();
                                break;
                            case "/":
                                division();
                                break;
                            case "+":
                                addition();
                                break;
                            default:
                                subtraction();
                                break;
                        }
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Wprowadzony znak nie jest liczbą ani znakiem 'X");
                argumentsList.clear();
                startCalkulator();
            }
        }
    }

    public static void multiplication() {
        double composition = argumentsList.stream()
                .mapToDouble(a -> a)
                .reduce(1, (a, b) -> a * b);
        System.out.println(composition);
        argumentsList.clear();
        startCalkulator();
    }

    private static void division() {
        for (int j = 1; j < argumentsList.size(); j++) {
            if (argumentsList.get(j) != 0) {
                double result = argumentsList.get(0);
                for (int i = 1; i < argumentsList.size(); i++) {
                    result = result / argumentsList.get(i);
                }
                System.out.println(result);
                break;
            } else {
                System.out.println("Nie możesz dzielić na 0");
                System.out.println("Sprobuj ponownie");
                argumentsList.clear();
                enterQuantityArguments();
                break;
            }
        }
    }

    private static void addition() {
        double sum = argumentsList.stream()
                .mapToDouble(a -> a)
                .sum();
        System.out.println(sum);
        argumentsList.clear();
        startCalkulator();
    }

    private static void subtraction() {
        double sub = argumentsList.stream()
                .mapToDouble(a -> a)
                .sum();
        System.out.println(argumentsList.get(0) * 2 - sub);
        argumentsList.clear();
        startCalkulator();
    }
}
