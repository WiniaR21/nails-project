docker run -d --name postgres-container  -e POSTGRES_USER=postgres  -e POSTGRES_PASSWORD=password -e POSTGRES_DB=psql -p 5432:5432 postgres:13
