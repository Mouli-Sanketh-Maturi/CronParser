package org.deliveroo;

public class CronParser {
    public static void main(String[] args) {

        parseCronExpression(args);

    }

    public static void parseCronExpression(String[] args) {
        if (  args.length != 6 ) {
            System.out.println("Invalid argument count");
            return;
        }
        try {
            //Parse the individual entities.
            String minutes = parseIndividualInterval(args[0],0,59);
            String hours = parseIndividualInterval(args[1],0,23);
            String dayOfMonth = parseIndividualInterval(args[2],1,31);
            String month = parseIndividualInterval(args[3],1,12);
            String dayOfWeek;
            //This check handles week scenario to determine if week range is from
            // 0-Sunday to 6- Saturday or 1-Monday to 7-Sunday
            if (checkWeekStartsWith0(args[4])) {
                dayOfWeek = parseIndividualInterval(args[4],0,6);
            } else {
                dayOfWeek = parseIndividualInterval(args[4],1,7);
            }
            String command = args[5];

            //Output the parsed cron entities
            printIndividualEntities("minute", minutes);
            printIndividualEntities("hour", hours);
            printIndividualEntities("day of month", dayOfMonth);
            printIndividualEntities("month", month);
            printIndividualEntities("day of week", dayOfWeek);
            printIndividualEntities("command", command);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String parseIndividualInterval(String expression, int min, int max) throws CronParseRangeException {
        // Parse wild card *
        if (expression.equals("*")) {
            StringBuilder resolvedNums = new StringBuilder();
            for (int num = min; num<=max; num++) {
                resolvedNums.append(num);
                resolvedNums.append(" ");
            }
            return resolvedNums.toString();
        }// Parse range of a period.
        else if (expression.contains("-")) {
            String[] periods = expression.split("-");
            if (periods.length != 2) {
                throw new CronParseRangeException("Invalid range provided. Range should be between "+min+" and "+max
                        +" Given Range " + expression);
            }
            int startPeriod;
            int endPeriod;
            try {
                startPeriod = Integer.parseInt(periods[0]);
                endPeriod = Integer.parseInt(periods[1]);
            } catch (NumberFormatException e) {
                throw new CronParseRangeException("Invalid range provided. Range should be between "+min+" and "+max
                        +" Given Range " + expression);
            }


            if (startPeriod < min || startPeriod > max || endPeriod < min || endPeriod > max || startPeriod >= endPeriod) {
                throw new CronParseRangeException("Invalid range provided. Range should be between "+min+" and "+max
                        +" Given Range " + expression);
            }
            StringBuilder resolvedNums = new StringBuilder();
            for (int num = startPeriod; num <=endPeriod; num ++) {
                resolvedNums.append(num);
                resolvedNums.append(" ");
            }
            return resolvedNums.toString();
        } //Parse individual elements of a period.
        else if (expression.contains(",")) {
            String[] individualNums = expression.split(",");
            StringBuilder resolvedNums = new StringBuilder();
            for (String individualNum : individualNums) {
                Integer individualIntegerNum;
                try {
                    individualIntegerNum = Integer.parseInt(individualNum);
                } catch (NumberFormatException e) {
                    throw new CronParseRangeException("Invalid numbers provided. Range should be between "+min+" and "+max
                            +" Given numbers " + expression);
                }
                if (individualIntegerNum < min || individualIntegerNum > max) {
                    throw new CronParseRangeException("Invalid numbers provided. Range should be between "+min+" and "
                            +max+", Given Numbers: "+expression);
                }
                resolvedNums.append(individualNum);
                resolvedNums.append(" ");
            }
            return resolvedNums.toString();
        } // Parse every nth period
        else if (expression.contains("*/")) {
            Integer step;
            try {
                step = Integer.parseInt(expression.replace("*/",""));
            } catch (NumberFormatException e) {
                throw new CronParseRangeException("Invalid step for every provided. Range should be between "+min+" and "
                        +max+", Given every step: "+expression);
            }

            StringBuilder resolvedNums = new StringBuilder();
            if (step <= 0 || step > max) {
                throw new CronParseRangeException("Invalid step for every provided. Range should be between "+1+" and "
                        +max+", Given every step: "+expression);
            }
            for (int period = min; period<= max; period += step) {
                resolvedNums.append(period);
                resolvedNums.append(" ");
            }
            return resolvedNums.toString();
        }
        // If all those don't exist, then it's a single number, check for range and return it.
        else {
            Integer num;
            try {
                num = Integer.parseInt(expression);
            } catch (NumberFormatException e) {
                throw new CronParseRangeException("Invalid individual number provided. Range should be between "+min+" and "
                        +max+", Given number: "+expression);
            }

            if (num < min || num > max) {
                throw new CronParseRangeException("Invalid individual number provided. Range should be between "+min+" and "
                        +max+", Given number: "+expression);
            }
            return expression;
        }
    }

    public static void printIndividualEntities(String entity, String values) {
        Integer whiteSpaceCount = 14 - entity.length();
        System.out.print(entity);
        for (int i=0; i< whiteSpaceCount; i++) {
            System.out.print(" ");
        }
        System.out.println(values);
    }

    public static boolean checkWeekStartsWith0(String expression) throws CronParseRangeException {
        if (expression.contains("0") && expression.contains("7")) {
            throw new CronParseRangeException("Invalid range for week provided. Range should be between 0 and 6 or 1 and 7");
        } else if (expression.contains("0")) {
            return true;
        } else if (expression.contains("7")) {
            return false;
        }
        return true;
    }
}