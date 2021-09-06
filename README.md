# Bundle-Calculator
This Bundle Calculator is for the user to determine the cost and bundle breakdown given a brands order.

## Description
Social media influencers are basing the price of their social media post on a single post basis. Now the company allows 
the influencer to provide their price lists on the basis of bundle for each format. According to these price lists 
provided by social media influencers, given a brands order, the user can calculate the cost and bundle breakdown for 
each format.

## How to build project

You can use gradle wrapper to build the project. JDK version is Java 8 for this project.

```./gradlew build```



## How to run project
After you build the project, you can run the command to see the output report in stdout with default file paths.

```./gradlew run```

You can overwrite bundle prices file and orders file paths by providing arguments.

```./gradlew run --args="<the path of your bundle prices file> <the path of your orders file>"```

## How to run test cases

Any changes made needs to make sure all tests passed. Run this command to see the results of all the unit tests.

```./gradlew test```

If you need to run a specific test, you can choose the test and click run in your IDE, or you can run the command such as following.

```./gradlew test --tests BundleCalculatorTest```

## Format of bundle prices file
* Your bundle prices file should be a .txt file.
* First line should be the header, divided by "|".
* Second line should be a divider line such as "----".
* Starting from the third line, you should list the price information, divided by "|".

Example of bundle prices file format:  
```
Submission format | Format code | Bundles  
----------------- | ----------- | -------  
Image | IMG | 5 @ $450 10 @ $800  
Audio | Flac | 3 @ $427.50 6 @ $810 9 @ $1147.50  
Video | VID | 3 @ $570 5 @ $900 9 @ $1530
```

## Format of orders file
* Your orders file should be a .txt file.
* The file must have two columns without header. First column is the order amount. Second column is the order format.

Example of orders file format:
```
10 IMG  
15 FLAC  
13 VID
```

##Expected Output
The output of the Bundle Calculator will be printed in console. The following are the expected reports given the bundle prices and orders above.  
```
10 IMG $800
  1 x 10 $800
15 FLAC $1957.50
  1 x 9 $1147.50
  1 x 6 $810
13 VID $2370
  2 x 5 $1800
  1 x 3 $570
```