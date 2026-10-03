// Question 3: Online Examination System

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Question {
    private String id;
    private String text;
    private int points;

    public Question(String id, String text, int points) {
        this.id = id;
        this.text = text;
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getPoints() {
        return points;
    }

    public abstract boolean evaluate(String answer);
}

class MCQQuestion extends Question {
    private String correctAnswer;

    public MCQQuestion(String id, String text, int points, String correctAnswer) {
        super(id, text, points);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(String id, String text, int points, boolean correctAnswer) {
        super(id, text, points);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}

class Examination {
    private String name;
    private List<Question> questions = new ArrayList<Question>();

    public Examination(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getTotalPossiblePoints() {
        int total = 0;
        for (Question q : questions) {
            total += q.getPoints();
        }
        return total;
    }
}

class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Attempt {
    private Student student;
    private Examination exam;
    private Map<String, String> answers = new HashMap<String, String>();
    private boolean submitted = false;

    public Attempt(Student student, Examination exam) {
        this.student = student;
        this.exam = exam;
    }

    public boolean recordAnswer(String questionId, String answer) {
        if (submitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return false;
        }
        answers.put(questionId, answer);
        System.out.println("Answer recorded for Question " + questionId + ".");
        return true;
    }

    public void submit() {
        if (submitted) {
            System.out.println("Exam already submitted.");
            return;
        }
        submitted = true;
        System.out.println(exam.getName() + " submitted by " + student.getName() + ".");
        calculateResult();
    }

    private void calculateResult() {
        int totalScore = 0;
        StringBuilder resultBuilder = new StringBuilder("Result: ");

        List<Question> questions = exam.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            String ans = answers.get(q.getId());
            boolean correct = (ans != null) && q.evaluate(ans);

            int pointsEarned = correct ? q.getPoints() : 0;
            totalScore += pointsEarned;

            resultBuilder.append("Question ").append(i + 1).append(": ")
                    .append(correct ? "Correct (" : "Incorrect (")
                    .append(pointsEarned).append(" points)");

            if (i < questions.size() - 1) {
                resultBuilder.append(", ");
            }
        }

        resultBuilder.append(". Total score: ").append(totalScore).append("/").append(exam.getTotalPossiblePoints()).append(".");
        System.out.println(resultBuilder.toString());
    }
}

public class Problem3_OnlineExamSystem {
    public static void main(String[] args) {
        Examination examA = new Examination("Exam A");
        examA.addQuestion(new MCQQuestion("1", "What is OOP?", 5, "C"));
        examA.addQuestion(new TrueFalseQuestion("2", "Java supports multiple inheritance for classes?", 5, false));

        Student student1 = new Student("S1", "Student 1");

        System.out.println("Exam A started by Student 1.");
        Attempt attempt = new Attempt(student1, examA);

        // Record answers
        attempt.recordAnswer("1", "C");
        attempt.recordAnswer("2", "true"); // Incorrect, as correct is false

        // Submit examination
        attempt.submit();

        // Attempt to change answer after submission
        attempt.recordAnswer("1", "A");
    }
}