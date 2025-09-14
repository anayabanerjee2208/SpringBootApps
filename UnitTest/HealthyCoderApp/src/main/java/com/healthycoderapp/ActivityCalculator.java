package com.healthycoderapp;

public class ActivityCalculator {

    private static final int WORKOUT_DURATION_MIN = 45;
    public static String rateActivityLevel(int weeklyCardioMin, int weeklyWorkoutSessions){

        int totalMin = weeklyCardioMin + weeklyWorkoutSessions * WORKOUT_DURATION_MIN;
        double avgDailyActivityMin = totalMin / 7.0;
        if(avgDailyActivityMin < 20)
            return "bad";
        return "";
    }
}
