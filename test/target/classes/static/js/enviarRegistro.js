document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('registroForm');
    
    form.addEventListener('submit', async (event) => {
        event.preventDefault(); // Prevent the default form submission

        // Get form values
        const nombre = document.getElementById('nombre').value.trim();
        const apellido = document.getElementById('apellido').value.trim();
        const correo = document.getElementById('correo').value.trim();
        const contraseña = document.getElementById('contraseña').value.trim();
        const isArrendador = document.getElementById('arrendador').checked;

        // Regular expression for email validation
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        // Validation
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

        // Data to be sent
        const formData = {
            nombre: nombre,
            apellido: apellido,
            correo: correo,
            contraseña: contraseña,
            arrendador: isArrendador
        };

        try {
            const response = await fetch('/crear-cuenta', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            });

            if (response.ok) {
                alert('Registro exitoso!');
                form.reset(); // Optionally reset the form
            } else {
                alert('Hubo un problema con el registro. Por favor, inténtelo de nuevo.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error al enviar los datos. Por favor, inténtelo de nuevo.');
        }
    });
});
