import java.util.*;


// =====================================================
// INTERFACE
// =====================================================

interface Evaluatable
{
    boolean evaluateAnswer(String answer);
}


// =====================================================
// ABSTRACT CLASS - QUESTION
// =====================================================

abstract class Question
    implements Evaluatable
{
    private String question;
    private String correctAnswer;

    public Question(String question, String correctAnswer)
    {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    // Getter methods
    public String getQuestion()
    {
        return question;
    }

    public String getCorrectAnswer()
    {
        return correctAnswer;
    }

    // Abstract method
    public abstract void displayQuestion();
}


// =====================================================
// TECHNICAL QUESTION
// =====================================================

class TechnicalQuestion extends Question
{
    public TechnicalQuestion(
        String question,
        String correctAnswer
    )
    {
        super(question, correctAnswer);
    }

    @Override
    public void displayQuestion()
    {
        System.out.println("Technical Question:");
        System.out.println(getQuestion());
    }

    @Override
    public boolean evaluateAnswer(String answer)
    {
        return answer.toLowerCase().contains(
            getCorrectAnswer().toLowerCase()
        );
    }
}


// =====================================================
// HR QUESTION
// =====================================================

class HRQuestion extends Question
{
    public HRQuestion(
        String question,
        String correctAnswer
    )
    {
        super(question, correctAnswer);
    }

    @Override
    public void displayQuestion()
    {
        System.out.println("HR Question:");
        System.out.println(getQuestion());
    }

    @Override
    public boolean evaluateAnswer(String answer)
    {
        return answer.trim().length() >= 10;
    }
}


// =====================================================
// CODING QUESTION
// =====================================================

class CodingQuestion extends Question
{
    public CodingQuestion(
        String question,
        String correctAnswer
    )
    {
        super(question, correctAnswer);
    }

    @Override
    public void displayQuestion()
    {
        System.out.println("Coding Question:");
        System.out.println(getQuestion());
    }

    @Override
    public boolean evaluateAnswer(String answer)
    {
        return answer.toLowerCase().contains(
            getCorrectAnswer().toLowerCase()
        );
    }
}


// =====================================================
// CANDIDATE CLASS
// =====================================================

class Candidate
{
    private String name;
    private String experience;
    private String jobRole;

    public Candidate(
        String name,
        String experience,
        String jobRole
    )
    {
        this.name = name;
        this.experience = experience;
        this.jobRole = jobRole;
    }

    public String getName()
    {
        return name;
    }

    public String getExperience()
    {
        return experience;
    }

    public String getJobRole()
    {
        return jobRole;
    }
}


// =====================================================
// INTERVIEW CLASS
// =====================================================

class Interview
{
    private Candidate candidate;
    private ArrayList<Question> questions;

    private int correctAnswers;
    private int wrongAnswers;


    public Interview(Candidate candidate)
    {
        this.candidate = candidate;

        questions = new ArrayList<>();

        correctAnswers = 0;
        wrongAnswers = 0;
    }


    // Add question
    public void addQuestion(Question question)
    {
        questions.add(question);
    }


    // Start interview
    public void startInterview(Scanner sc)
    {
        System.out.println();
        System.out.println("========================================");
        System.out.println("          INTERVIEW STARTED");
        System.out.println("========================================");


        for(int i = 0; i < questions.size(); i++)
        {
            System.out.println();
            System.out.println("----------------------------------------");
            System.out.println("Question " + (i + 1));
            System.out.println("----------------------------------------");


            // Polymorphism
            Question question = questions.get(i);

            question.displayQuestion();


            System.out.println();
            System.out.print("Your Answer: ");

            String answer = sc.nextLine();


            if(answer.trim().isEmpty())
            {
                System.out.println(
                    "Answer cannot be empty."
                );

                wrongAnswers++;
            }
            else
            {
                if(question.evaluateAnswer(answer))
                {
                    System.out.println(
                        "Answer Accepted"
                    );

                    correctAnswers++;
                }
                else
                {
                    System.out.println(
                        "Answer Not Matched"
                    );

                    wrongAnswers++;
                }
            }
        }
    }


    // Display result
    public void displayResult()
    {
        int totalQuestions = questions.size();

        double percentage =
            ((double) correctAnswers / totalQuestions) * 100;


        System.out.println();
        System.out.println("========================================");
        System.out.println("           INTERVIEW RESULT");
        System.out.println("========================================");


        System.out.println(
            "Candidate       : " +
            candidate.getName()
        );

        System.out.println(
            "Experience      : " +
            candidate.getExperience()
        );

        System.out.println(
            "Job Role        : " +
            candidate.getJobRole()
        );


        System.out.println("----------------------------------------");


        System.out.println(
            "Total Questions : " +
            totalQuestions
        );

        System.out.println(
            "Correct Answers : " +
            correctAnswers
        );

        System.out.println(
            "Wrong Answers   : " +
            wrongAnswers
        );


        System.out.printf(
            "Score           : %.2f%%\n",
            percentage
        );


        System.out.println("----------------------------------------");


        if(percentage >= 80)
        {
            System.out.println(
                "Performance     : Excellent"
            );
        }
        else if(percentage >= 60)
        {
            System.out.println(
                "Performance     : Good"
            );
        }
        else if(percentage >= 40)
        {
            System.out.println(
                "Performance     : Average"
            );
        }
        else
        {
            System.out.println(
                "Performance     : Needs Improvement"
            );
        }


        System.out.println("========================================");
        System.out.println("        INTERVIEW COMPLETED");
        System.out.println("========================================");
    }
}


// =====================================================
// MAIN CLASS
// FILE NAME: skillText.java
// =====================================================

public class skillText
{
    static Scanner sc = new Scanner(System.in);


    // =================================================
    // JAVA DEVELOPER INTERVIEW
    // =================================================

    public static void javaInterview(
        Candidate candidate
    )
    {
        Interview interview =
            new Interview(candidate);


        interview.addQuestion(
            new TechnicalQuestion(
                "What is OOPs in Java?",
                "objects"
            )
        );


        interview.addQuestion(
            new TechnicalQuestion(
                "What is inheritance in Java?",
                "inheritance"
            )
        );


        interview.addQuestion(
            new TechnicalQuestion(
                "What is polymorphism?",
                "multiple forms"
            )
        );


        interview.addQuestion(
            new TechnicalQuestion(
                "What is encapsulation?",
                "data hiding"
            )
        );


        interview.addQuestion(
            new CodingQuestion(
                "Which keyword is used to inherit a class in Java?",
                "extends"
            )
        );


        interview.addQuestion(
            new HRQuestion(
                "Tell me about yourself.",
                ""
            )
        );


        interview.startInterview(sc);

        interview.displayResult();
    }


    // =================================================
    // WEB DEVELOPER INTERVIEW
    // =================================================

    public static void webDeveloperInterview(
        Candidate candidate
    )
    {
        Interview interview =
            new Interview(candidate);


        interview.addQuestion(
            new TechnicalQuestion(
                "What is HTML?",
                "markup"
            )
        );


        interview.addQuestion(
            new TechnicalQuestion(
                "What is CSS?",
                "style"
            )
        );


        interview.addQuestion(
            new TechnicalQuestion(
                "What is JavaScript?",
                "programming"
            )
        );


        interview.addQuestion(
            new TechnicalQuestion(
                "What is DOM?",
                "document"
            )
        );


        interview.addQuestion(
            new CodingQuestion(
                "Which HTML tag is used to create a hyperlink?",
                "a"
            )
        );


        interview.addQuestion(
            new HRQuestion(
                "Why do you want to become a web developer?",
                ""
            )
        );


        interview.startInterview(sc);

        interview.displayResult();
    }


    // =================================================
    // SOFTWARE ENGINEER INTERVIEW
    // =================================================

    public static void softwareEngineerInterview(
        Candidate candidate
    )
    {
        Interview interview =
            new Interview(candidate);


        interview.addQuestion(
            new TechnicalQuestion(
                "What is a software development life cycle?",
                "process"
            )
        );


        interview.addQuestion(
            new TechnicalQuestion(
                "What is debugging?",
                "errors"
            )
        );


        interview.addQuestion(
            new TechnicalQuestion(
                "What is an algorithm?",
                "steps"
            )
        );


        interview.addQuestion(
            new TechnicalQuestion(
                "What is a database?",
                "data"
            )
        );


        interview.addQuestion(
            new CodingQuestion(
                "Which data structure follows FIFO?",
                "queue"
            )
        );


        interview.addQuestion(
            new HRQuestion(
                "Why should we hire you?",
                ""
            )
        );


        interview.startInterview(sc);

        interview.displayResult();
    }


    // =================================================
    // MAIN METHOD
    // =================================================

    public static void main(String[] args)
    {
        while(true)
        {
            try
            {
                System.out.println();
                System.out.println("========================================");
                System.out.println("         SKILL INTERVIEW SYSTEM");
                System.out.println("========================================");


                System.out.println(
                    "1. Start New Interview"
                );

                System.out.println(
                    "2. Exit"
                );


                System.out.print(
                    "Enter your choice: "
                );


                int choice =
                    Integer.parseInt(
                        sc.nextLine()
                    );


                // =====================================
                // START INTERVIEW
                // =====================================

                if(choice == 1)
                {
                    System.out.println();
                    System.out.println("----------------------------------------");
                    System.out.println("         CANDIDATE DETAILS");
                    System.out.println("----------------------------------------");


                    System.out.print(
                        "Enter Candidate Name: "
                    );

                    String name = sc.nextLine();


                    System.out.print(
                        "Enter Experience: "
                    );

                    String experience =
                        sc.nextLine();


                    // =================================
                    // SELECT JOB ROLE
                    // =================================

                    System.out.println();
                    System.out.println(
                        "Select Job Role:"
                    );


                    System.out.println(
                        "1. Java Developer"
                    );

                    System.out.println(
                        "2. Web Developer"
                    );

                    System.out.println(
                        "3. Software Engineer"
                    );


                    System.out.print(
                        "Enter choice: "
                    );


                    int roleChoice =
                        Integer.parseInt(
                            sc.nextLine()
                        );


                    String jobRole = "";


                    if(roleChoice == 1)
                    {
                        jobRole =
                            "Java Developer";
                    }
                    else if(roleChoice == 2)
                    {
                        jobRole =
                            "Web Developer";
                    }
                    else if(roleChoice == 3)
                    {
                        jobRole =
                            "Software Engineer";
                    }
                    else
                    {
                        System.out.println(
                            "Invalid Job Role."
                        );

                        continue;
                    }


                    // =================================
                    // CREATE CANDIDATE OBJECT
                    // =================================

                    Candidate candidate =
                        new Candidate(
                            name,
                            experience,
                            jobRole
                        );


                    System.out.println();


                    System.out.println(
                        "Interview Role: " +
                        jobRole
                    );


                    // =================================
                    // START SELECTED INTERVIEW
                    // =================================

                    if(roleChoice == 1)
                    {
                        javaInterview(candidate);
                    }
                    else if(roleChoice == 2)
                    {
                        webDeveloperInterview(
                            candidate
                        );
                    }
                    else
                    {
                        softwareEngineerInterview(
                            candidate
                        );
                    }
                }


                // =====================================
                // EXIT
                // =====================================

                else if(choice == 2)
                {
                    System.out.println();

                    System.out.println(
                        "Thank you for using " +
                        "Skill Interview System!"
                    );

                    break;
                }


                // =====================================
                // INVALID CHOICE
                // =====================================

                else
                {
                    System.out.println(
                        "Invalid choice. " +
                        "Please try again."
                    );
                }
            }


            // =========================================
            // NUMBER FORMAT EXCEPTION
            // =========================================

            catch(NumberFormatException e)
            {
                System.out.println();

                System.out.println(
                    "Please enter a valid number."
                );
            }


            // =========================================
            // GENERAL EXCEPTION
            // =========================================

            catch(Exception e)
            {
                System.out.println();

                System.out.println(
                    "Something went wrong: " +
                    e.getMessage()
                );
            }
        }


        sc.close();
    }
}