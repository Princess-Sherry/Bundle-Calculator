# Bundle-Calculator
This Bundle Calculator is for the user to determine the cost and bundle breakdown given a brands order.

## Description
Social media influencers are basing the price of their social media post on a single post basis. Now the company allows 
the influencer to provide their price lists on the basis of bundle for each format. According to these price lists 
provided by social media influencers, given a brands order, the user can calculate the cost and bundle breakdown for 
each format.

## How to build project

You can use gradle wrapper to build the project.

```./gradlew build```

## How to run project
After you build the project, you can run the command to see the output report in stdout with default file paths.

```./gradlew run```

You can overwrite price list file and order file paths by providing arguments.

```./gradlew run --args="<the path of your price lists file> <the path of your orders file>"```

## How to run test cases

Any changes made needs to make sure all tests passed. Run this command to see the results of unit testing.

```./gradlew test```

