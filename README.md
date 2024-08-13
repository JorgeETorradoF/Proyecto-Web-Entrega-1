# Cómo ejecutar

Se debe tener Docker y Kubernetes (k8s) instalados.

Luego, se debe desplegar el Nginx Ingress Controller en k8s con el siguiente comando:
 ```bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml
 ```

Una vez se haya desplegado y tenga una IP externa (en algunos casos será localhost como por ejemplo en docker desktop), se puede continuar:

1. **Cree el namespace thymeleaf:**
    ```bash
    kubectl create namespace thymeleaf
    ```

2. **Se debe tener en ejecución el contenedor registry con el comando:**
    ```bash
    docker run -d -p 5000:5000 --name registry registry:2.7
    ```

3. **Entre a la carpeta `postgre` y buildee la imagen:**
    ```bash
    docker build -t localhost:5000/dockersito-postgres:latest .
    ```

4. **Entre a la carpeta `k8s` dentro de la carpeta `postgre` y aplique el deployment:**
    ```bash
    kubectl apply -f deployment.yaml
    ```

5. **Entre a la carpeta `test` y buildee la imagen de la siguiente manera:**
    ```bash
    docker build -t localhost:5000/test:latest .
    ```

6. **Luego se debe pushear la imagen al registry:**
    ```bash
    docker push localhost:5000/test:latest
    ```

7. **Entre a la carpeta `k8s` dentro de la carpeta `tests` y aplique el deployment y el ingress:**
    ```bash
    kubectl apply -f deployment.yaml
    kubectl apply -f ingress.yaml
    ```

8. **Para confirmar que todo salió bien ejecute el comando:**
    ```bash
    kubectl get pods -n thymeleaf
    ```

Deberían aparecerle 2 pods con estado `Running` y replicas `1/1`.
