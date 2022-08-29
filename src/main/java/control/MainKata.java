package control;

import exception.ReadFileException;
import model.Pelouse;
import model.Tondeuse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MainKata {

    protected static final Logger logger = LogManager.getLogger(MainKata.class);

    public static void main(String[] args) {
        try {
            readFile();
        } catch (IOException ioException) {
            logger.error("File reading failed ! : " + ioException.getMessage());
        } catch (ReadFileException e) {
            logger.error("File reading failed ! : " + e.getMessage());
        }
    }

    public static void readFile() throws IOException, ReadFileException {
        InputStream is = MainKata.class.getClassLoader().getResourceAsStream("entry");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        Pelouse pelouse;
        Set<Tondeuse> tondeuseList = new HashSet<>();
        int lineID = 0;
        while ((line = br.readLine()) != null) {
            line = line.replaceAll(" ", "");
            if (checkLine(line, lineID)) {
                sb.append(line + System.lineSeparator());
                if (lineID == 0) {
                    pelouse = Pelouse.getInstance(
                            Integer.parseInt(line.substring(0, 1)),
                            Integer.parseInt(line.substring(1, 2)));
                    System.out.println(pelouse);
                } else {
                    tondeuseList.add( new Tondeuse(
                            Integer.parseInt(line.substring(0, 1)),
                            Integer.parseInt(line.substring(1, 2)),
                            line.charAt(2),
                            line.substring(3)
                    ));
                }
            }
            else {
                if (lineID == 0) {
                    throw new ReadFileException("Error initiating Pelouse, check first line");
                } else {
                    logger.debug("Skipping tondeuse line N° " + lineID);
                }
            }
            lineID++;
        }

        for (Tondeuse t : tondeuseList) {
            execute(t);
        }
    }
    private static void execute(Tondeuse t) {
        Pelouse p = Pelouse.getInstance();
        char [] lineCharsArray = t.getOperation().toCharArray();

        for (char c : lineCharsArray) {
            switch (c) {
                case 'D' -> t.turnRight();
                case 'G' -> t.turnLeft();
                case 'A' -> t.moveForward(p);
            }
        }

        System.out.println(t);
    }

    private static boolean checkLine(String line, int lineID) {
        boolean toReturn = false;
        // first line Pelouse definition
        if (lineID ==0) {
            if (line.length()==2)
                toReturn = true;
        }
        //Tondeuse lines
        else {
            if (line.length()>=3 &&
                    Character.isDigit(line.charAt(0))  &&
                    Character.isDigit(line.charAt(1))  &&
                    Character.isLetter(line.charAt(2)) &&
                    line.substring(2,3).matches("[NSEW]") &&
                    line.substring(3).matches("[DGA]*")) {
                toReturn = true;
            }
        }

        return toReturn;
    }
}
