import java.util.HashMap;

public class LessonManager {
    private int currentLesson = 1;
    private HashMap<Integer, String> lessons = new HashMap<>();
    private HashMap<Integer, String> starterCodes = new HashMap<>();
    private HashMap<Integer, String> expectedOutputs = new HashMap<>();

    public LessonManager() {
        lessons.put(1, "Scrie un program care afișează 'Hello, World!'");
        starterCodes.put(1, "public class Main {\n    public static void main(String[] args) {\n        // Scrie codul aici\n    }\n}");
        expectedOutputs.put(1, "Hello, World!");

        lessons.put(2, "Scrie un program care afișează 'Hello, Mike!'");
        starterCodes.put(2, "public class Main {\n    public static void main(String[] args) {\n        // Scrie codul aici\n    }\n}");
        expectedOutputs.put(2, "Hello, Mike!");
    }

    public String getLessonContent(int lessonNumber) {
        return lessons.getOrDefault(lessonNumber, "Lecție indisponibilă.");
    }

    public String getStarterCode(int lessonNumber) {
        return starterCodes.getOrDefault(lessonNumber, "");
    }

    public boolean checkCode(String userCode) {
        String output = CodeRunner.run(userCode);
        return expectedOutputs.get(currentLesson).equals(output.trim());
    }

    public boolean unlockNextLesson() {
        if (currentLesson < lessons.size()) {
            currentLesson++;
            return true;
        }
        return false;
    }

    public int getCurrentLesson() {
        return currentLesson;
    }
}
