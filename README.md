# Cómo ejecutar

Se debe tener Docker y Kubernetes (k8s) instalados.

Luego, se debe desplegar el Nginx Ingress Controller en k8s con el siguiente comando:
 ```bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml -n ingress-nginx
 ```

Una vez se haya desplegado y tenga una IP externa (en algunos casos será localhost como por ejemplo en docker desktop), se puede continuar:

1. **Cree el namespace thymeleaf:**
    ```bash
    kubectl create namespace proyecto-web
    ```

2. **Se debe tener en ejecución el contenedor registry con el comando:**
    ```bash
    docker run -d -p 5000:5000 --name registry registry:2.7
    ```
    **Si ya ejecutó almenos 1 vez el run del registry, no es necesario volver a correrlo, solo se requiere encenderlo (en caso de que esté apagado) con el comando:**
   ```bash
    docker start registry
    ```

4. **Entre a la carpeta `postgre` y ejecute el script de build y push de imagen:**
    ```bash
    buildAndPush.bat dockersito-postgres
    ```

5. **Entre a la carpeta `k8s` dentro de la carpeta `postgre` y aplique el deployment:**
    ```bash
    kubectl apply -f deployment.yaml
    ```

6. **Entre a la carpeta `test` y ejecute el script de build y push de imagen:**
    ```bash
    buildAndPush.bat proyecto_e1
    ```
    **posdata: si le aparece algún error al buildear, abra con visual studio el archivo mvnw y cambiele sus caracteres especiales de crlf a lf**

7. **Entre a la carpeta `k8s` dentro de la carpeta `tests` y aplique el deployment y el ingress:**
    ```bash
    kubectl apply -f deployment.yaml
    kubectl apply -f ingress.yaml
    ```

8. **Para confirmar que todo salió bien ejecute el comando:**
    ```bash
    kubectl get pods -n proyecto-web
    ```

Deberían aparecerle 2 pods con estado `Running` y replicas `1/1`, en cuyo caso ya puede acceder desde su browser digitando la external ip que aparece en el nginx-controller de tipo LoadBalancer (digite este comando si necesita saberla):
   ```bash
   kubectl get svc -n ingress-nginx
   ```
    
## En caso de que realice algún cambio y quiera probarlo: 
- **Para desplegar y probar cambios en la base de datos:**
  - Repita los pasos 2 (solo la segunda parte de este paso) y 3.

- **Para desplegar y probar cambios en el backend/frontend:**
  - Repita los pasos 2 (solo la segunda parte de este paso) y 5.

- **Para desplegar y probar todo (backend/frontend y base de datos):**
  - Repita los pasos 2 (solo la segunda parte de este paso), 3, y 5.

Luego de haber repetido los pasos respectivos, ejecute el comando:
   ```bash
    kubectl delete pods --all -n proyecto-web
   ```
    
esto reiniciará los pods para que hagan pull a la nueva imagen que usted buildeó y pusheó en el paso de la posdata. Luego espere unos segundos o unos minutos y pruebe de nuevo digitando la external ip del nginx-controller
