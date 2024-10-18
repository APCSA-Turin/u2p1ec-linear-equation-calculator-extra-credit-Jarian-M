package com.example.project;
public class LinearCalculator{
    //FOR EXTRA CREDIT 
    //you should copy and paste all of your code from the LinearCalculator class
    // but NOT printInfo(). Please update it below

    //INSTANCE VARIABLES
    //4 INTEGER variables (name them: x1,x2,y1,y2)
    private int x1; //4 instance variables, each representing half of a coordinate pt.
    private int x2;
    private int y1;
    private int y2;




    //CONSTRUCTOR
    //1 constructor with 2 String parameters. Each parameter represents a coordinate.
    //For example, "(1,2)" and "(3,4)" would be two parameter values
    //You will have to parse the string into 4 integers, representing the 2 points.
    public LinearCalculator(String coord1, String coord2){ // <--add 2 string parameters to this constructor
        int parenthesis1 = coord1.indexOf(")"); //used as the ending of the substring method so that the last parentheses is exluded from the substring method.
        int parenthesis2 = coord2.indexOf(")");
        String newPt1 = coord1.substring(1, parenthesis1); //I made new substrings newPt1 and newPt2, which exlude the initial and final parentheses, so that only the x and y values as well as the comma in between them remain in the new string.
        String newPt2 = coord2.substring(1, parenthesis2);


        String[] pt = newPt1.split(","); //I learned this easier method of parsing string values into integers, especially negative values which I had problems with, in the website here: https://stackoverflow.com/questions/27372530/how-to-parse-string-containing-negative-number-into-integer
        x1 = Integer.parseInt(pt[0]); //splits the substring, using the comma as the divisor, into two integer values
        y1 = Integer.parseInt(pt[1]);
        String[] pt2 = newPt2.split(",");
        x2 = Integer.parseInt(pt2[0]);
        y2 = Integer.parseInt(pt2[1]);
    }






    //METHODS
    //getters and setters for the 4 instance variables (8 methods total)
    public int getX1() {
        return x1; //getters only return the value that they are getting and are the type of the value that they are returning, so this getter is int.
    }
    public int getY1() {
        return y1;
    }
    public int getX2() {
        return x2;
    }
    public int getY2() {
        return y2;
    }
    public void setX1(int newX1) {
        this.x1 = newX1; //sets the instance variables to new values using the int parameter. Setters don't return values so their type is void
    }
    public void setY1(int newY1) {
        this.y1 = newY1;
    }
    public void setX2(int newX2) {
        this.x2 = newX2;
    }
    public void setY2(int newY2) {
        this.y2 = newY2;
    }




    //distance() -> returns a double.
    //calculates the distance between the two points to the nearest HUNDREDTH and returns the value.
    public double distance() {
        double ptDist = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)); //the equation for the distance between two pts is the square root of the sum of change in x squared and change in y squared
        double roundedDist = roundedToHundredth(ptDist); //the distance must be to the nearest hundredth, so the roundedToHundredth method rounds the distance to the nearest hundredth
        return roundedDist;
    }


    //yInt() -> returns a double.
    //calculates the y intercept of the equation and returns the value to the nearest HUNDREDTH
    //if y-int if undefined, should return -999.99
    public double yInt(){
        double slope = slope(); //slope is needed to identify the y-intercept
        if(slope == -999.99) { //if the slope is undefined, aka slope is equal to -999.99, then the y-intercept is also undefined and also equal to -999.99
            return -999.99;
        } else {
            double intercept = y1 - (slope * x1); //if slope isn't undefined, then a y-intercept must exist. The equation for the y-intercept is y-value - (slope * x-value), I used x1 and y1 as the x and y values.
            intercept = roundedToHundredth(intercept); //slope must be a value rounded to the nearest hundredth, so I used the roundedToHundredth method to round the slope to the nearest hundredth.
            return intercept;
        }
    }


    //slope() -> returns a double.
    //calculates the slope of the equations and returns the value to the nearest HUNDREDTH
    //if slope is undefined, should return -999.99
    public double slope(){
        double roundedSlope;
        if((x2 - x1) == 0) { //if x2 - x1, the denominator of the slope equation, is equal to zero, then the slope must be undefined because anything divided by zero is undefined.
            roundedSlope = -999.99;
            return roundedSlope;
        }
        roundedSlope = (double) (y2 - y1) / (x2 - x1); //You need to cast one of the dividends to a double in order to prevent int division and returning the incorrect slope. The equation of slope is change in y divided by change in x.
        roundedSlope = roundedToHundredth(roundedSlope); //slope must be a value rounded to the nearest hundredth, so I used the roundedToHundredth method to round the slope to the nearest hundredth.
        return roundedSlope;
    }


    //equations() -> returns a String.
    //calculates the final equation in y=mx+b form and returns the string
    //if the equation has no slope, the equation should return -> "undefined"
    //HINT: You may need other custom methods to decrease the amount of code in the equations() method
    public String equation(){
        String finalEq;
        if(yInt() == 0) { //if the y-intercept is equal to zero, then we can ignore it in the final equation y=mx+b
            finalEq= "y=" + slope() + "x";
        } else {
            if(slope() == -999.99) { //if slope is equal to -999.99, then the slope is undefined. In turn, the final equation is also undefined.
                finalEq = "undefined";
            } else {
                if(slope() == 0) { //if the slope is equal to zero, then the line is horizontal and the value of the final equation is in the form of y=b or y is equal to the y-intercept.
                    finalEq = "y=" + yInt();
                } else {
                    if(yInt() < 0) { //if the y-intercept is less than 0, or negative, then the plus sign is removed from the final equation y=mx+b so that the negative sign of the y-intercept subracts from mx instead of adding a negative value.
                        finalEq = "y=" + slope() + "x" + yInt();
                    } else {
                        finalEq = "y=" + slope() + "x+" + yInt(); //if everything else is false, then the slope and y-intercept MUST be defined, and in turn the final equation must be in the form y=mx+b.
                    }
                }
            }
        }
        return finalEq;
    }




    //roundedToHundredth(double x)-> returns double
    //calculates the input to the nearest hundredth and returns that value
    public double roundedToHundredth(double x){
        double rounded = Math.round(x * 100.0) / 100.0; //I learned how to round to the nearest hundredth using this website here: https://stackoverflow.com/questions/8825209/rounding-decimal-points
        return rounded;
    }

    //You will need to concatenate to the string 
    //the results from findSymmetry() and Midpoint()
    public String printInfo(){
        String str = "The two points are: (" + x1 + "," + y1 + ")"; //prints the first coordinate pair
        str += " and " + "(" + x2 + "," + y2 + ")"; //prints the second coordinate pair
        str += "\nThe equation of the line between these points is: " + equation(); //prints the final equation of the line, otherwise, it prints that the line is undefined
        str += "\nThe slope of this line is: " + slope(); //prints the slope rounded to the nearest hundredth if the slope is defined, otherwise, it prints -999.99
        str += "\nThe y-intercept of the line is: " + yInt(); //prints the y-intercept rounded to the nearest hundredth if it's defined, otherwise, it prints -999.99
        str += "\nThe distance between the two points is: " + distance(); //prints the distance between the two coordinate pairs rounded to the nearest hundredth.
        str += "\n" + findSymmetry();
        str += "\n" + Midpoint();
        return str;
    }

    //findSymmetry()-> returns a string 
    //the method should determine if there is symmetry between the two points
    // there should be  4 return statements 
    // return "Symmetric about the x-axis";
    // return "Symmetric about the y-axis";
    //return "Symmetric about the origin";
    //return "No symmetry";
    public String findSymmetry(){
        int midX = ((x1 + x2) / 2);
        int midY = ((y1 + y2) / 2);
        String midPt = "(" + midX + "," + midY + ")";
        if(midPt.equals("(0,0)")) {
            return "Symmetric about the origin";
        } else {
            if(midX == 0) {
                return "Symmetric about the y-axis";
            } else {
                if(midY == 0) {
                    return "Symmetric about the x-axis";
                } else {
                    return "No symmetry";
                }
            }
        }
    }

    //Midpoint()->return a string 
    //the method should calculate the midpoint between the two points
    //it should return "The midpoint of this line is: (0,0)";
    public String Midpoint() {
        double midX = (double) ((x1 + x2) / 2);
        double midY = (double) ((y1 + y2) / 2);
        String midPt = "(" + midX + "," + midY + ")";
        String midPoint = "The midpoint of this line is: " + midPt;
        return midPoint;
    }
}