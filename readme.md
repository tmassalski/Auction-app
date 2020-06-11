(Work in progress)

This is simple auction application with REST api that serves as Kafka message producer for Bank-app https://gitlab.com/tmassalski/bank-app.

To create auction, use POST request to {ip_address}:8082/auction-app/auctions

{
  "description": "string",
  "expirationDays": 0,
  "ownerAccountId": 0,
  "ownerId": 0,
  "price": 0,
  "quantity": 0,
  "title": "string"
}

To place and order, use POST request to {ip_address}:8082/auction-app/auctions/{auctionId}/orders

{
  "clientAccountNumber": "string",
  "clientId": 0,
  "quantity": 0
}

Once order is created, the app will publish message with payment data to Kafka server and change payment status from "Pending" to "Paid".
Every 15 minutes the app will search for any order with "Pending" status and will try to publish it again.
You can also find any order with "Pending status by GET request to {ip_address}:8082/auction-app/orders/pending

All endpoints are also available via Swagger under {ip_address}:8082/auction-app/swagger-ui.html

To provide communication between Auction-app and Bank-app you need Kafka server
https://kafka.apache.org/quickstart

To Start zookeeper on Windows machine:
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

To Start Kafka server on Windows machine:
.\bin\windows\kafka-server-start.bat .\config\server.properties

Messages will be published under topic "transfer", which should be automatically created.