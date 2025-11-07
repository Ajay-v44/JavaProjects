@echo off
echo Docker Management Script for Ecommerce Backend
echo ============================================
echo.

if "%1"=="" (
    echo Usage: docker-scripts.bat [command]
    echo.
    echo Commands:
    echo   build    - Build the Docker images
    echo   up       - Start all services
    echo   down     - Stop all services
    echo   logs     - View application logs
    echo   restart  - Restart the application
    echo   clean    - Remove all containers and volumes
    echo   status   - Show container status
    echo.
    goto :eof
)

if "%1"=="build" (
    echo Building Docker images...
    docker-compose build
    goto :eof
)

if "%1"=="up" (
    echo Starting services...
    docker-compose up -d
    echo.
    echo Services started!
    echo - Backend API: http://localhost:8080
    echo - pgAdmin: http://localhost:5050 (admin@ecomm.com/admin123)
    goto :eof
)

if "%1"=="down" (
    echo Stopping services...
    docker-compose down
    goto :eof
)

if "%1"=="logs" (
    echo Showing application logs...
    docker-compose logs -f app
    goto :eof
)

if "%1"=="restart" (
    echo Restarting application...
    docker-compose restart app
    goto :eof
)

if "%1"=="clean" (
    echo Removing all containers and volumes...
    docker-compose down -v
    docker system prune -f
    goto :eof
)

if "%1"=="status" (
    echo Container status:
    docker-compose ps
    goto :eof
)

echo Unknown command: %1
echo Use 'docker-scripts.bat' for help