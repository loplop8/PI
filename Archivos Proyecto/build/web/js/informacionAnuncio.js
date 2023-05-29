document.addEventListener('DOMContentLoaded', function () {
    // Obtener el formulario y el div del slider
    var form = document.querySelector('.requires-validation');
    var sliderDiv = document.getElementById('slider');

    // Obtener los campos de entrada del formulario
    var inputFields = form.querySelectorAll('input:not([type="file"]), textarea');

    // Escuchar el evento de cambio en los campos de entrada
    inputFields.forEach(function (inputField) {
        inputField.addEventListener('blur', function () {
            if (this.value === '') {
                this.classList.add('is-invalid');
            } else {
                this.classList.remove('is-invalid');
            }
        });
    });

    // Escuchar el evento de env�o del formulario
    form.addEventListener('submit', function (event) {
        // Verificar si hay campos vac�os
        var hasEmptyFields = false;
        inputFields.forEach(function (inputField) {
            if (inputField.value === '') {
                inputField.classList.add('is-invalid');
                hasEmptyFields = true;
            } else {
                inputField.classList.remove('is-invalid');
            }
        });

        // Prevenir el env�o del formulario si hay campos vac�os o no se han cargado im�genes en el slider
        if (hasEmptyFields || sliderDiv.innerHTML === '') {
            event.preventDefault();
            alert('Por favor, completa todos los campos y selecciona al menos una imagen antes de enviar el formulario.');
        }
    });

    // Escuchar el evento de cambio en los campos de entrada de tipo "file"
    var inputFiles = document.querySelectorAll('input[type="file"]');
    inputFiles.forEach(function (inputFile) {
        inputFile.addEventListener('change', function () {
            // Obtener las im�genes del campo de entrada actual
            var imagenes = [];
            var files = this.files;

            // Verificar si el archivo es una imagen
            for (var i = 0; i < files.length; i++) {
                var file = files[i];
                if (file.type.startsWith('image/')) {
                    imagenes.push(file);
                } else {
                    alert('El archivo "' + file.name + '" no es una imagen v�lida. Por favor, selecciona solo im�genes.');
                    this.value = ''; // Limpiar el campo de entrada de archivo
                    return;
                }
            }

            // Verificar si se cargaron im�genes
            if (imagenes.length === 0) {
                alert('Por favor, selecciona al menos una imagen.');
                return;
            }

            // Crear el slider
            var slider = document.createElement('div');
            slider.className = 'carousel slide ';
            slider.setAttribute('data-bs-ride', 'carousel');

            


            // Crear los slides
            var slides = document.createElement('div');
            slides.className = 'carousel-inner';
            for (var i = 0; i < imagenes.length; i++) {
                var slide = document.createElement('div');
                slide.className = 'carousel-item';
                if (i === 0) {
                    slide.classList.add('active');
                }

                var image = document.createElement('img');
                image.src = URL.createObjectURL(imagenes[i]);
                image.className = 'd-block w-25 mx-auto'; // Tama�o m�s peque�o: w-50 y centrado: mx-auto
                slide.appendChild(image);

                slides.appendChild(slide);
            }
            slider.appendChild(slides);

            // Agregar las flechas de avance y retroceso
            


                var prevButton = document.createElement('button');
                prevButton.className = 'carousel-control-prev';
                prevButton.type = 'button';
                prevButton.setAttribute('data-bs-target', '#slider');
                prevButton.setAttribute('data-bs-slide', 'prev');
                prevButton.innerHTML = '<span class="carousel-control-prev-icon" aria-hidden="true"></span><span class="visually-hidden">Previous</span>';

                var nextButton = document.createElement('button');
                nextButton.className = 'carousel-control-next';
                nextButton.type = 'button';
                nextButton.setAttribute('data-bs-target', '#slider');
                nextButton.setAttribute('data-bs-slide', 'next');
                nextButton.innerHTML = '<span class="carousel-control-next-icon" aria-hidden="true"></span><span class="visually-hidden">Next</span>';
            
            slider.appendChild(prevButton);
            slider.appendChild(nextButton);
            // Agregar el slider al div del slider
            sliderDiv.innerHTML = '';
            sliderDiv.appendChild(slider);
        });
    });
});