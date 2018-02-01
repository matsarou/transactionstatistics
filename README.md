# transactionstatistics
a restful API for transaction statistics

POST /transactions
Every Time a new transaction happened, this endpoint will be called.
Body:
{
"amount": 12.3,
"timestamp": 1478192204000
}
Where:
● amount - transaction amount
● timestamp - transaction time in epoch in millis in UTC time zone 

GET /statistics
This endpoint have to execute in constant time and memory (O(1)). 
It returns the statistic based on the transactions which happened in the last 60 seconds. 
Returns:
{
"sum": 1000,
"avg": 100,
"max": 200,
"min": 50,
"count": 10
}
Where:
● sum is a double specifying the total sum of transaction value in the last 60 seconds
● avg is a double specifying the average amount of transaction value in the last 60
seconds
● max is a double specifying single highest transaction value in the last 60 seconds
● min is a double specifying single lowest transaction value in the last 60 seconds
● count is a long specifying the total number of transactions happened in the last 60
seconds