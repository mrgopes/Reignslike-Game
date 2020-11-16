import java.io.*;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

/**
 * @author Crunchify.com
 * Best and simple Production ready utility to save/load
 *         (read/write) data from/to file
 */

public class savenread {

    static {

    }

    private static String crunchify_file_location = "crunchify.txt";
    private static Gson gson = new Gson();
    public static savenread ii;
    public static Highscore hs;

    // CrunchifyComapny Class with two fields
    // - Employees
    // - CompanyName
    public static class Highscore {
        private int score;
        private String name;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    public static class Stats {
        private int people;
        private int money;
        private int army;
        private int religion;

        public int getPeople() {
            return people;
        }

        public void setPeople(int people) {
            this.people = people;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getArmy() {
            return army;
        }

        public void setArmy(int army) {
            this.army = army;
        }

        public int getReligion() {
            return religion;
        }

        public void setReligion(int religion) {
            this.religion = religion;
        }

    }

    private static Highscore getHighscore() {
        return hs;
    }

    // Save to file Utility
    public static void crunchifyWriteToFile(String myData) {
        File crunchifyFile = new File(crunchify_file_location);
        if (!crunchifyFile.exists()) {
            try {
                File directory = new File(crunchifyFile.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                crunchifyFile.createNewFile();
            } catch (IOException e) {
                log("Excepton Occured: " + e.toString());
            }
        }

        try {
            // Convenience class for writing character files
            FileWriter crunchifyWriter;
            crunchifyWriter = new FileWriter(crunchifyFile.getAbsoluteFile(), true);

            PrintWriter writer = new PrintWriter(crunchifyFile);
            writer.print("");
            writer.close();

            // Writes text to a character-output stream
            BufferedWriter bufferWriter = new BufferedWriter(crunchifyWriter);
            bufferWriter.write(myData.toString());
            bufferWriter.close();

        } catch (IOException e) {
            log("Hmm.. Got an error while saving Company data to file " + e.toString());
        }
    }

    // Save to file Utility
    public static void Score(String myData) {
        File crunchifyFile = new File("score.txt");
        if (!crunchifyFile.exists()) {
            try {
                File directory = new File(crunchifyFile.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                crunchifyFile.createNewFile();
            } catch (IOException e) {
                log("Excepton Occured: " + e.toString());
            }
        }

        try {
            // Convenience class for writing character files
            FileWriter crunchifyWriter;
            crunchifyWriter = new FileWriter(crunchifyFile.getAbsoluteFile(), true);

            PrintWriter writer = new PrintWriter(crunchifyFile);
            writer.print("");
            writer.close();

            // Writes text to a character-output stream
            BufferedWriter bufferWriter = new BufferedWriter(crunchifyWriter);
            bufferWriter.write(myData.toString());
            bufferWriter.close();
        } catch (IOException e) {
        }
    }

    public static String getHighname() {
        InputStreamReader isReader;
        try {
            isReader = new InputStreamReader(new FileInputStream("D:\\SigewardDevelopement\\ReignslikeGame\\score.txt"), "UTF-8");

            JsonReader myReader = new JsonReader(isReader);
            Highscore hs = gson.fromJson(myReader, Highscore.class);
            String i = hs.getName();
            return i;
        } catch (Exception ex) {
            return "null";
        }
    }

    public static int getHighsc() {
        InputStreamReader isReader;
        try {
            isReader = new InputStreamReader(new FileInputStream("D:\\SigewardDevelopement\\ReignslikeGame\\score.txt"), "UTF-8");

            JsonReader myReader = new JsonReader(isReader);
            Highscore hs = gson.fromJson(myReader, Highscore.class);
            int i = hs.getScore();
            return i;
        } catch (Exception ex) {
            return 0;
        }
    }

    private static void log(String string) {
        System.out.println(string);
    }

}