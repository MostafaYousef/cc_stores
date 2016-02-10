# cc_stores
Coding Challenge Stores

# Running the application
Simply run: `mvn spring-boot:run`


Get all stores `GET http://localhost:8080/store`

Get all stores, sort by city ascending `GET http://localhost:8080/store?sort=city`

Get all stores, sort by city descending `GET http://localhost:8080/store?sort=-city`

Get all stores, sort by date ascending `GET http://localhost:8080/store?sort=date`

Get all stores, sort by date descending `GET http://localhost:8080/store?sort=-date`

Get a single store `GET http://localhost:8080/store/{store_id}`

Add a new store `POST http://localhost:8080/store`
