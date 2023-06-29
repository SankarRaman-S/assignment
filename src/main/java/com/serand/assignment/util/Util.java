package com.serand.assignment.util;

import com.serand.assignment.model.Question;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Util {

    public static final String COMPLETED = "completed";

    /**
     * Method calculates the score for the survey.
     * Stream of survey question is passed to method calculate(userAnswers, correctAnswers, points)
     * calculate method does the below operation
     * 1. Filter the correct answers.
     * 2. Get the points for each of the correct answer.
     * 3. Sum the points for the correct answer.
     * @param questions List of Questions for the survey.
     * @return the score for correct answers.
     */
    public static long calculateScore(List<Question> questions) {
        return questions.stream()
                .map(question -> calculate(question.getUserAnswers(), question.getCorrectAnswers(), question.getPoints()))
                .collect(Collectors.summingLong(Long::longValue));
    }

    /**
     * @param userAnswers Answers by the users.
     * @param correctAns Correct answers defined by the system.
     * @param points Points for the correct answers.
     * @return the calculated score for the correct answers based on the points
     */
    public static Long calculate (List<String> userAnswers, List<String> correctAns, Map<String,Integer> points){
        return userAnswers.stream()
                .filter(correctAns::contains)
                .map(answer -> getPoints(answer, points))
                .mapToLong(Long::longValue).sum();
    }

    /**
     * @param answer correct answer
     * @param points points map for the correct answers Answer as key and point as value
     * @return the point of the correct answer.
     */
    private static long getPoints(String answer, Map<String, Integer> points) {
        if(points.containsKey(answer)){
            return points.get(answer);
        }
        return 0;
    }



}