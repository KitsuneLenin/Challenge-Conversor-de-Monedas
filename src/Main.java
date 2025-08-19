import com.aluracursos.currencies.SupportedCurrency;
import com.aluracursos.models.Currency;
import com.aluracursos.models.CurrencyExchange;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String baseCurrency;
        String targetCurrency;
        double amount;
        boolean continueProgram = true;
        String menu = """
                **************************************************
                
                Sea bienvenido/a al Conversor de Monedas
                
                Monedas disponibles:
                1) USD - Dólar Estadounidense
                2) EUR - Euro
                3) CAD - Dólar Canadiense
                4) BRL - Real Brasileño
                5) CLP - Peso Chileno
                6) JPY - Yen Japonés
                
                **************************************************
                """;

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        try {
            System.out.println(menu);
            while (continueProgram) {
                // Get base currency
                System.out.println("Seleccione la moneda de origen (por ejemplo, USD):");
                baseCurrency = scanner.nextLine().toUpperCase();
                while (!SupportedCurrency.isSupported(baseCurrency)) {
                    System.out.println("Moneda no válida, intente de nuevo");
                    baseCurrency = scanner.nextLine().toUpperCase();
                }

                // Get target currency
                System.out.println("Seleccione la moneda de destino (por ejemplo, EUR):");
                targetCurrency = scanner.nextLine();
                while (!SupportedCurrency.isSupported(targetCurrency)) {
                    System.out.println("Moneda no válida, intente de nuevo");
                    targetCurrency = scanner.nextLine().toUpperCase();
                }

                // Get amount to exchange
                System.out.println("Ingrese el valor que desea convertir:");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Cantidad no válida, ingrese un valor de tipo numérico");
                    scanner.next();
                }
                amount = scanner.nextDouble();
                scanner.nextLine();

                CurrencyExchange currencyExchange = new CurrencyExchange();
                Currency currency = currencyExchange.exchange(baseCurrency, targetCurrency, amount);

                System.out.println("Calculando conversión...");
                System.out.println(
                        String.format(Locale.US, "%.2f [%S] equivale a %.2f [%S] según el tipo de cambio actual",
                                amount, currency.base_code(), currency.conversion_result(), currency.target_code())
                );
                System.out.println("¿Desea realizar otra conversión? (S/N):");
                String again = scanner.nextLine().trim().toUpperCase();
                if (!again.equals("S")) {
                    continueProgram = false;
                }
            }
        } catch(IllegalArgumentException e) {
            System.out.println("Se ingresó un valor no válido.");
            System.out.println("Detalles técnicos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado.");
            System.out.println("Detalles técnicos: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Programa finalizado.");
        }
    }
}



