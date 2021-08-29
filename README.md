# Bundle-Calculator
This Bundle Calculator is for the user to determine the cost and bundle breakdown given a brands order.

## Description
Social media influencers are basing the price of their social media post on a single post basis. Now the company allows 
the influencer to provide their price lists on the basis of bundle for each format. According to these price lists 
provided by social media influencers, given a brands order, the user can calculate the cost and bundle breakdown for 
each format.

## How to use the calculator
The calculator is to be run with two parameters.
* Parameter One: the file path of social media influencer's price lists (.txt file) 
* Parameter Two: the file path of the customer's orders (.txt file)
### Default files
There are default files for the calculator. Please add the path of these two files as the parameters in your IDE.
* Parameter One: src/main/resources/priceList.txt
* Parameter Two: src/main/resources/orders.txt
### Run with your own files
You can use your own files to run the calculator.
* First compile all the .java files.
* Then run the following command:
```
java BundleCalculator <the path of your price lists file> <the path of your orders file>
```

