document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('loginForm');
    
    form.addEventListener('submit', async (event) => {
        event.preventDefault(); // Previene la acción por defecto del formulario

        // Se extraen los valores del formulario
        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value.trim();

        // Construir la URL con los parámetros
        const queryString = new URLSearchParams({
            email: email,
            password: password
        }).toString();

        try {
            const response = await fetch(`/iniciar-sesion?${queryString}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            // Verificar si la respuesta es exitosa
            if (response.ok) {
                const responseData = await response.text();
                
                // Intenta parsear como JSON si es posible
                let data;
                try {
                    data = JSON.parse(responseData);
                    console.log('Usuario:', data);
                    alert('Inicio Sesión exitoso.');
                } catch (error) {
                    console.error('Respuesta no es JSON:', responseData);
                    alert('Respuesta inesperada: ' + responseData);
                }
            } else {
                // Manejar errores
                const responseText = await response.text(); // Leer el cuerpo como texto

                let errorMessage;
                try {
                    const errorData = JSON.parse(responseText); // Intenta parsear como JSON
                    errorMessage = errorData.error || 'Error desconocido';
                } catch (error) {
                    errorMessage = responseText; // Si no es JSON, usa el texto plano
                }

                console.error('Server error response:', errorMessage);
                alert('Error: ' + errorMessage);
            }
        } catch (error) {
            console.error('Error al iniciar sesión:', error.message);
            alert('Error: ' + error.message);
        }
    });
});
