# Flight-manager

flight-manager is a simple java boilerplate configured with springboot and grpc that manages flight in voloOk application.

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

It's necessary to set the following configurations.
##### Generating communication services
1. Add the .proto file used by your services in /src/main/proto
2. Launch the Maven clean command.
3. Launch the Maven install command.

##### Grpc communications

1. Add the @GrpcService on your controllers which implements the proto services;
Example:


```java
@GrpcService
public class ExampleController extends GenericServiceGrpc.GenericServiceImplBase{
	
	@Override
	public void sendMessage(MessageReq request, StreamObserver<MessageRes> responseObserver) {
		MessageRes response = MessageRes
				.newBuilder()
				.setResponse(request)
				.build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}

```



