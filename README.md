This is a web application based on microservice architecture.

App represents a hotel, where strangers can look through all available rooms, search room on desired dates with certain daily price.
Moreover, you can register (via mail and further confirmation latter), sign in into an account, book the rooms, leave comments under rooms (room rate is formed based on response assessment) and update personal information + replenish the balance. 
Administrator can create new rooms, delete comments.

    Architecture info
There are 4 independent microservices available:
- Front service (UI);
- Main service (interacts with database and implements almost the whole application logic);
- Mail service (send activation account mails after registration process);
- Eureka-service (connects back-end services with each other).

        Technology stack:
- React;
- HTML/CSS;
- Spring Boot;
- Spring Data JPA;
- Spring Mail;
- Spring Cloud Netflix;
- Spring Security (with JWT);
- Maven;
- Docker (Docker-compose);
- Lombok;
- PostgreSQL;
- Postman;
- Figma;


        Executors:
- Bohdana O. (Designer)
- Anton S. (Front-end)
- Hlib F. (Back-end)