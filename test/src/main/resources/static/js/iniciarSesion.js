document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('loginForm');
    
    form.addEventListener('submit', async (event) => {
        event.preventDefault(); // Previene la acción por defecto del formulario

        // Se extraen los valores del formulario
        const correo = document.getElementById('correo').value.trim();
        const contraseña = document.getElementById('contraseña').value.trim();

        // Función para registrar usuario
        async function loginUsuario(datos) {
            try {
                const response = await fetch('/iniciar-sesion', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(datos)
                });

                // Verificar si la respuesta es exitosa
                if (response.ok) {
                    const data = await response.json();
                    console.log('Usuario:', data);
                    alert('Inicio Sesión exitoso.');
                } else {
                    // Manejar errores
                    let errorMessage = 'Error desconocido'; // Mensaje por defecto
                    try {
                        const errorData = await response.json();
                        errorMessage = errorData.error || 'Error desconocido';
                    } catch (e) {
                        // Si la respuesta no es JSON, usar texto plano
                        errorMessage = await response.text();
                    }
                    console.error('Server error response:', errorMessage);
                    alert('Error: ' + errorMessage);
                }
            } catch (error) {
                console.error('Error al inciar sesión:', error.message);
                alert('Error: ' + error.message);
            }
        }
        
        // Datos que serán enviados
        const formData = {
            correo: correo,
            contraseña: contraseña
        };

        loginUsuario(formData);
    });
});
