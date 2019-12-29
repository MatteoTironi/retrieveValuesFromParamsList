package main;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) throws IOException {


    /*
     * Importante: per funzionare correttamente il loop nestato con lo scanner ha bisogno che lo
     * scanner venga instanziato ogni volta all'interno del loop precedente, non solo lo Scanner ma
     * anche il FileReader!
     */
    FileReader params = new FileReader("resources\\params.txt");
    Scanner scanParams = new Scanner(params);

    final String regex = "(?<=\\=)(.*?)(?=\\s+?)";
    Pattern p = Pattern.compile(regex, Pattern.MULTILINE);

    // cicla tutti i parametri presenti nel file params.txt
    while (scanParams.hasNextLine()) {
      // salva il parametro
      String param = scanParams.nextLine().toLowerCase();
      FileReader logFile = new FileReader("resources\\test.log");
      Scanner scanLog = new Scanner(logFile);
      // cicla tutte le righe del file test.log
      while (scanLog.hasNextLine()) {
        String logLine = scanLog.nextLine().toLowerCase();
        final Matcher matcher = p.matcher(logLine);
        // se il parametro trovato combacia con la riga del test.log
        if (logLine.contains(param)) {
          // usa la regex per trovare il valore
          while (matcher.find()) {
            System.out.println("Full match: " + param + matcher.group(0));
          }
        }
      }
      scanLog.close();
    }
    scanParams.close();
  }
}


