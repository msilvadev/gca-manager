### Build
> mvn clean install

### Generate a Docker image
> docker build -t gca-manager .

### Run Docker
> docker run -p 8090:8090 -t "image id"

### Data Base - H2 in memory
After starting the application, you can navigate to `http://localhost:8090/h2`,
which will present us with a login page.
> user: sa
>
> password: sa