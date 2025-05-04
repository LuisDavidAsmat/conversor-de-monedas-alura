package ui;

import data.CurrencyList;
import models.ConversionRequest;

import java.util.Scanner;

public class ConsoleUI
{
    private final Scanner scanner = new Scanner(System.in);

    public void ConverterMenu()
    {
        System.out.println("""
        \n***************************************************
        Sea bienvenido/a al Conversor de Monedas =]
        
        1) Dólar =>> Peso argentino
        2) Peso argentino =>> Dólar
        3) Dólar =>> Real brasileño
        4) Real brasileño =>> Dólar
        5) Dólar =>> Peso colombiano
        6) Peso colombiano =>> Dólar
        7) Otra opción de conversión
        8) Salir
        Elija una opción válida:
        ***************************************************
        """);
    }

    public int getUserChoice ()
    {
        try{
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public ConversionRequest getPredefinedRequest (String base, String target)
    {
        System.out.print("Ingrese el valor que deseas convertir: ");

        double amount = Double.parseDouble(scanner.nextLine());

        return new ConversionRequest(base, target, amount);
    }

    public ConversionRequest getCustomRequest ()
    {
        System.out.println("Formatos de monedas disponibles: ");
        CurrencyList.CURRENCIES.forEach((code, name) ->
                System.out.printf("%s - %s%n", code, name)
                );

        System.out.print("Ingrese el código de la moneda base (3 letras): ");
        String base = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese el código de la moneda objetivo (3 letras): ");
        String target = scanner.nextLine().toUpperCase();

        double amount;

        while (true)
        {
            System.out.print("Enter amount: ");
            try
            {
                amount = Double.parseDouble(scanner.nextLine());
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("Número inválido, por favor inténtelo de nuevo.");
            }
        }

        return new ConversionRequest(base, target, amount);
    }

}
