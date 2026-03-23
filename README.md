Demo project to test out [JobRunr](https://www.jobrunr.io/en/).

## Starting the app locally
1. Start two instances of the Spring Boot application, one with `web` profile activated and another with `worker` profile activated. The `web` instance serves REST API, while the `worker` instance functions as a worker for background tasks.
    - Web server can be reached at `http://localhost:8080`.
    - JobRunr dashboard can be accessed at `http://localhost:3000` with username `admin` and password `admin`.