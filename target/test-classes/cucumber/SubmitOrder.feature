@Tag
Feature: purchase the order from ecommerce website 


@tag2
Scenario Outline: Positive test of purchasing the order
Given  logged in with username<name> and password<password>
When I add the product<product> to cart
Examples:
|name					|				password|					productName|
|shreyasharma@gmail.com |  		rajput@123gudiyA	|		ZARA COAT 3			|
|						|							|					|	