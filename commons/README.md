

curl http://localhost:8080/orders

echo '{""}' | curl -d @- http://localhost:8080/orders

curl -H "Content-Type: application/json" -X POST -d '{"username":"xyz","password":"xyz"}' http://localhost:8080/orders


Place a bet
PUT /coupon.json;jsessionid=<jsession-id>