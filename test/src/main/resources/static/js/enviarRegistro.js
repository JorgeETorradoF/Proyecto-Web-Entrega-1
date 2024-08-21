document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('registroForm');
    
    form.addEventListener('submit', async (event) => {
        event.preventDefault(); // Previene la acción por defecto del formulario

        // Obtén los valores del formulario
        const nombre = document.getElementById('nombre').value.trim();
        const apellido = document.getElementById('apellido').value.trim();
        const correo = document.getElementById('correo').value.trim();
        const contraseña = document.getElementById('contraseña').value.trim();
        const isArrendador = document.getElementById('arrendador').checked;

        // Expresión regular para validación del correo
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        // Validación
        if (!nombre || nombre.length > 100) {
            alert('Nombre es requerido y no debe exceder los 100 caracteres.');
            return;
        }
        if (!apellido || apellido.length > 100) {
            alert('Apellido es requerido y no debe exceder los 100 caracteres.');
            return;
        }
        if (!correo || !emailRegex.test(correo)) {
            alert('Correo Electrónico es inválido.');
            return;
        }
        if (!contraseña || contraseña.length > 100) {
            alert('Contraseña es requerida y no debe exceder los 100 caracteres.');
            return;
        }

        // Función para registrar usuario
        async function registrarUsuario(datos) {
            try {
                const response = await fetch('/crear-cuenta', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(datos)
                });

                // Verificar si la respuesta es exitosa
                if (response.ok) {
                    const data = await response.json();
                    console.log('Usuario registrado:', data);
                    alert('Usuario registrado exitosamente.');
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
                console.error('Error al registrar el usuario:', error.message);
                alert('Error: ' + error.message);
            }
        }
        
        // Datos que serán enviados
        const formData = {
            nombre: nombre,
            apellido: apellido,
            correo: correo,
            contraseña: contraseña,
            arrendador: isArrendador
        };

        registrarUsuario(formData);
    });
});
