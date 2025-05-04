import models.ConversionRequest;
import services.ConversionService;
import services.ExchangeRateFetcher;
import services.FileExporter;
import ui.ConsoleUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Main
{
    public static void main(String[] args)
    {
        ExchangeRateFetcher exchangeRateFetcher = new ExchangeRateFetcher();
        ConversionService conversionService = new ConversionService(exchangeRateFetcher);
        FileExporter fileExporter = new FileExporter();
        ConsoleUI consoleUi = new ConsoleUI();

        List<String> history = new ArrayList<>();

        Map<Integer, String[]> presetConversions = Map.of(
                1, new String[]{"USD", "ARS"},
                2, new String[]{"ARS", "USD"},
                3, new String[]{"USD", "BRL"},
                4, new String[]{"BRL", "USD"},
                5, new String[]{"USD", "COP"},
                6, new String[]{"COP", "USD"}
        );

        int choice;

        do
        {
            consoleUi.ConverterMenu();
            choice = consoleUi.getUserChoice();

            try
            {
                ConversionRequest request;

                if (presetConversions.containsKey(choice))
                {
                    String[] currencies = presetConversions.get(choice);
                    request = consoleUi.getPredefinedRequest(currencies[0], currencies[1]);
                }
                else if (choice == 7) {
                    request = consoleUi.getCustomRequest();
                }
                else if (choice == 8) {
                    break;
                }
                else
                {
                    System.out.println("Opción inválida.");
                    continue;
                }

                String result = conversionService.convert(request);
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                String fullEntry = timestamp + " - " + result;

                System.out.println(result);

                history.add(fullEntry);

            } catch (Exception e) {
                System.out.println("Un error inesperado ocurrió: " + e.getMessage());
            }

        } while (choice != 8);

        fileExporter.saveAsText(history);
        System.out.println("Programa finalizado.");
    }
}


