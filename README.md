# K8S Resources Deployment Endpoint


## Contract

```json
{
    "template": "<encodedTemplate>",
    "namespace": "<namespace>",
    "cluster": "<clusterIp>",
    "token": "<kubernetes-cluster-token>"
}
```


## How to use ?

```bash
mvn clean package -DskipTests
java -jar target/k8s-resources-deployment-endpoint-0.0.1-SNAPSHOT.jar
```