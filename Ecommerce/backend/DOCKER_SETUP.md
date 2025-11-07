# Docker Setup for Ecommerce Backend

This guide will help you containerize and run your Spring Boot application using Docker and Docker Compose.

## Prerequisites

- Docker Desktop installed and running
- Docker Compose installed (comes with Docker Desktop)
- Ports 8080, 5432, and 5050 available on your machine

## Quick Start

### Using the provided script (Windows):
```bash
# Build the Docker images
docker-scripts.bat build

# Start all services
docker-scripts.bat up

# Check status
docker-scripts.bat status

# View logs
docker-scripts.bat logs

# Stop services
docker-scripts.bat down
```

### Manual commands:
```bash
# Build the Docker images
docker-compose build

# Start all services in detached mode
docker-compose up -d

# View logs
docker-compose logs -f app

# Stop all services
docker-compose down

# Remove everything including volumes
docker-compose down -v
```

## Services Overview

1. **PostgreSQL Database** (`db`)
   - Port: 5432
   - Database: Ecomm
   - User: postgres
   - Password: 1234
   - Volume: postgres_data (persistent storage)

2. **Spring Boot Application** (`app`)
   - Port: 8080
   - Automatically connects to PostgreSQL
   - Health checks enabled
   - Logs available in `./logs` directory

3. **pgAdmin** (optional, `pgadmin`)
   - Port: 5050
   - Login: admin@ecomm.com / admin123
   - Useful for database management and queries

## Access Points

- **Backend API**: http://localhost:8080
- **Swagger/OpenAPI**: http://localhost:8080/swagger-ui.html
- **pgAdmin**: http://localhost:5050
- **Database**: localhost:5432

## Environment Configuration

The application uses environment variables that can be customized in `docker-compose.yml`:

```yaml
environment:
  SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/Ecomm
  SPRING_DATASOURCE_USERNAME: postgres
  SPRING_DATASOURCE_PASSWORD: 1234
  SPRING_JPA_HIBERNATE_DDL_AUTO: update
```

## Development vs Production

### For Development:
- Uses `spring.jpa.hibernate.ddl-auto=update` (auto-creates tables)
- Includes pgAdmin for database management
- Shows SQL queries in logs

### For Production:
- Change `SPRING_JPA_HIBERNATE_DDL_AUTO: validate` or `none`
- Remove pgAdmin service
- Use external database service
- Consider using Docker secrets for sensitive data

## Troubleshooting

### Container won't start:
```bash
# Check logs
docker-compose logs app

# Check if ports are available
netstat -ano | findstr :8080
netstat -ano | findstr :5432
```

### Database connection issues:
```bash
# Check if PostgreSQL is healthy
docker-compose ps

# Connect to database manually
docker exec -it ecomm-postgres psql -U postgres -d Ecomm
```

### Rebuild everything:
```bash
docker-compose down -v
docker system prune -f
docker-compose build --no-cache
docker-compose up -d
```

## Security Notes

- Change default passwords in production
- Use Docker secrets for sensitive data
- Consider using environment files for configuration
- Run containers as non-root user (already configured)

## Performance Optimization

- Adjust JVM heap size: Add `-Xmx512m -Xms256m` to ENTRYPOINT
- Use connection pooling (already configured via Spring Boot)
- Consider using a reverse proxy like Nginx for production

## Monitoring

- Health check endpoint: http://localhost:8080/actuator/health
- Application info: http://localhost:8080/actuator/info
- Database health: Monitored via pg_isready in health checks