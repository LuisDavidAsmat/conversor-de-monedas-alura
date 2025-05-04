package services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileExporter
{
    private static final String FILE_NAME = "historial_conversion.txt";

    public void saveAsText (List<String> fileLines)
    {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME)))
        {
            for (String line : fileLines)
            {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            System.out.println("Historial de conversión guardado en el archivo '" + FILE_NAME + "'");
        }
        catch (IOException e)
        {
            System.err.println("Error al guardar el historial de conversión. " + e.getMessage());
        }
    }
}
