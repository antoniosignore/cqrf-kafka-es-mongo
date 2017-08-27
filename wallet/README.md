docker build -f Dockerfile -t wallet .
docker images
docker run -p 8081:8081 wallet





curl http://localhost:8080/orders

echo '{""}' | curl -d @- http://localhost:8080/orders

curl -H "Content-Type: application/json" -X POST -d '{"username":"xyz","password":"xyz"}' http://localhost:8080/orders

