import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Race {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, String> myHashMap = new HashMap<>();
        ArrayList<Integer> sorting = new ArrayList<>();

        try {
            while (true) {
                System.out.println("Type the name of the athlete (or press Enter to finish): ");
                String name = scanner.nextLine();

                // Exiting the loop if Enter is pressed
                if (name.isEmpty()) {
                    break;
                }

                System.out.println("Enter their time (HH:mm:ss): ");
                String inputTime = scanner.nextLine();

                // Parsing the input string to LocalTime to see if the format is ok
                LocalTime raceTime = parseInputTime(inputTime);

                if (raceTime != null) {
                    // Converting time to seconds
                    int timeInSeconds = convertToSeconds(raceTime);

                    // Storing the result in the map and array
                    myHashMap.put(timeInSeconds, name);
                    sorting.add(timeInSeconds);
                } else {
                    System.out.println("Invalid time format. Please enter time in HH:mm:ss format.");
                }
            }

        } catch (DateTimeParseException e) {
            System.out.println("Incorrect time format. Please enter a valid time.");
            scanner.nextLine();
        }

        // Sorting in ascending order
        sorting.sort(Integer::compareTo);
        System.out.println("Participants and their times (sorted):");
        for (int timeInSeconds : sorting) {
            System.out.println(myHashMap.get(timeInSeconds) + ": " + formatSecondsToTime(timeInSeconds));
        }

        scanner.close();
    }

    private static int convertToSeconds(LocalTime time) {
        int seconds = time.getSecond() + time.getMinute() * 60 + time.getHour() * 3600;
        return seconds;
    }

    private static LocalTime parseInputTime(String inputTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            return LocalTime.parse(inputTime, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static String formatSecondsToTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }
}