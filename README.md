# Flight-manager

flight-manager is a java project configured with springboot and grpc that manages flight in voloOk application.

### Environment Configuration

The following params must be specified in the src/main/resources/env.properties file (as specified in the env.properties.example):
* APPLICATION_NAME: the name of your application;
* GRPC_SERVER_PORT: the grpc server port(where the microservice response to requests)
* DB_HOST: ip address
* DB_PORT: port
* DB_SCHEMA: the name of flight schema
* DB_USERNAME: 
* DB_PASSWORD:
* DB_DDL: none' in production to avoid data loss, 'update' to generate tables
### Grpc service configurations

##### Generate proto resources and maven dependencies.

1. Launch the Maven clean command.
2. Launch the Maven install command.
3. Build the application in the war format.
4. Deploy the generated war using an Apache Tomcat.

#### Database configuration
1. Install MySQL and create a schema named volook_flight_manager.
2. Make sure that the configuration in the env.properties respect those in your db.
3. Launch the application with DB_DLL=update to generate tables.
4. Populate the airport table.

