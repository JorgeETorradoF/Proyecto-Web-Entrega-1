document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("contactForm");
    const nombresInput = document.getElementById("nombres");
    const apellidosInput = document.getElementById("apellidos");
    const correoInput = document.getElementById("correo");
    const semestreInput = document.getElementById("semestre");
    const descripcionInput = document.getElementById("descripcion");

    const nombresCount = document.getElementById("nombresCount");
    const apellidosCount = document.getElementById("apellidosCount");
    const correoCount = document.getElementById("correoCount");
    const descripcionCount = document.getElementById("descripcionCount");
    const formErrors = document.getElementById("formErrors");

    const updateCount = (input, countElement, maxLength) => {
        countElement.textContent = `${input.value.length}/${maxLength}`;
    };

    const validateEmail = (email) => {
        const re = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i;
        return re.test(email);
    };

    const validateInput = (input, condition) => {
        if (condition) {
            input.classList.remove("is-invalid");
            input.nextElementSibling.style.display = "none";
        } else {
            input.classList.add("is-invalid");
            input.nextElementSibling.style.display = "block";
        }
    };

    nombresInput.addEventListener("input", () => {
        updateCount(nombresInput, nombresCount, 100);
        validateInput(nombresInput, nombresInput.value.length > 0 && nombresInput.value.length <= 100);
    });

    apellidosInput.addEventListener("input", () => {
        updateCount(apellidosInput, apellidosCount, 100);
        validateInput(apellidosInput, apellidosInput.value.length > 0 && apellidosInput.value.length <= 100);
    });

    correoInput.addEventListener("input", () => {
        updateCount(correoInput, correoCount, 100);
        const isValid = validateEmail(correoInput.value) && correoInput.value.length <= 100;
        validateInput(correoInput, isValid);
    });

    semestreInput.addEventListener("input", () => {
        validateInput(semestreInput, semestreInput.value >= 0 && semestreInput.value <= 16);
    });

    descripcionInput.addEventListener("input", () => {
        updateCount(descripcionInput, descripcionCount, 500);
        validateInput(descripcionInput, descripcionInput.value.length > 0 && descripcionInput.value.length <= 500);
    });

    form.addEventListener("submit", async (event) => {
        event.preventDefault();
        let formIsValid = true;

        formIsValid = formIsValid && nombresInput.value.length > 0 && nombresInput.value.length <= 100;
        formIsValid = formIsValid && apellidosInput.value.length > 0 && apellidosInput.value.length <= 100;
        formIsValid = formIsValid && validateEmail(correoInput.value) && correoInput.value.length <= 100;
        formIsValid = formIsValid && semestreInput.value >= 0 && semestreInput.value <= 16;
        formIsValid = formIsValid && descripcionInput.value.length > 0 && descripcionInput.value.length <= 500;

        if (formIsValid) {
            const formData = {
                nombres: nombresInput.value,
                apellidos: apellidosInput.value,
                correo: correoInput.value,
                semestre: semestreInput.value,
                descripcion: descripcionInput.value
            };

            try {
                const response = await fetch('/formulario-enviado', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(formData)
                });

                if (response.ok) {
                    alert("Formulario enviado correctamente");
                    form.reset();
                    nombresCount.textContent = "0/100";
                    apellidosCount.textContent = "0/100";
                    correoCount.textContent = "0/100";
                    descripcionCount.textContent = "0/500";
                    formErrors.style.display = "none";
                } else {
                    throw new Error('Error en el envío del formulario');
                }
            } catch (error) {
                formErrors.textContent = "Hubo un problema al enviar el formulario.";
                formErrors.style.display = "block";
            }
        } else {
            formErrors.textContent = "Por favor, corrija los errores en el formulario.";
            formErrors.style.display = "block";
        }
    });
});
