Command to run Postresql docker container/image
docker run --name moneycounter-postgres -e POSTGRES_DB=moneycounter -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=secret -p 5432:5432 -d postgres

Command to run RabbitMQ docker container/image
docker run -d --hostname my-rabbit --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
