# transactionstatistics
a restful API for transaction statistics<br />

POST /transactions<br />
Every Time a new transaction happened, this endpoint will be called.<br />
Body:<br />
{<br />
"amount": 12.3,<br />
"timestamp": 1478192204000<br />
}<br />
Where:<br />
● amount - transaction amount<br />
● timestamp - transaction time in epoch in millis in UTC time zone <br />
<br />
GET /statistics<br />
This endpoint have to execute in constant time and memory (O(1)). <br />
It returns the statistic based on the transactions which happened in the last 60 seconds. <br />
Returns:<br />
{<br />
"sum": 1000,<br />
"avg": 100,<br />
"max": 200,<br />
"min": 50,<br />
"count": 10<br />
}<br />
Where:<br />
● sum is a double specifying the total sum of transaction value in the last 60 seconds<br />
● avg is a double specifying the average amount of transaction value in the last 60 seconds<br />
● max is a double specifying single highest transaction value in the last 60 seconds<br />
● min is a double specifying single lowest transaction value in the last 60 seconds<br />
● count is a long specifying the total number of transactions happened in the last 60 seconds<br />