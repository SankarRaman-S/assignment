package com.serand.assignment.util;

import com.serand.assignment.model.Question;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Util {

    public static final String COMPLETED = "completed";

    public static Long calculate (List<String> userAnswers, List<String> correctAns, Map<String,Integer> points){
        return userAnswers.stream()
                .filter(correctAns::contains)
                .map(answer -> getPoints(answer, points))
                .mapToLong(Long::longValue).sum();
    }

    private static long getPoints(String answer, Map<String, Integer> points) {
        if(points.containsKey(answer)){
            return points.get(answer);
        }
        return 0;
    }

    public static long calculateScore(List<Question> questions) {
        return questions.stream()
                .map(question -> calculate(question.getUserAnswers(), question.getCorrectAnswers(), question.getPoints()))
                .collect(Collectors.summingLong(Long::longValue));


    }

}