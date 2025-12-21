function getCurrentR() {
    const rInput = document.querySelector('[id*="current-r"]');
    if (rInput && rInput.value && rInput.value.trim() !== '') {
        const value = parseFloat(rInput.value);
        return !isNaN(value) ? value : null;
    }
    return null;
}

function convertToGraphX(pixelX) {
    const centerX = 150;
    const scale = 100;
    const r = getCurrentR();
    return ((pixelX - centerX) / scale * r);
}

function convertToGraphY(pixelY) {
    const centerY = 150;
    const scale = 100;
    const r = getCurrentR();
    return ((centerY - pixelY) / scale * r);
}

function setFormValues(x, y) {
    const xInput = document.querySelector('[id*="graph-x"]');
    const yInput = document.querySelector('[id*="graph-y"]');

    if (xInput && yInput) {
        xInput.value = x;
        yInput.value = y;
    }
}

function submitForm() {
    const submitButton = document.querySelector('[id*="graph-submit"]');
    if (submitButton) {
        submitButton.click();
    }
}

function handleGraphClick(event) {
    const r = getCurrentR();

    const svg = document.getElementById('graph');
    const rect = svg.getBoundingClientRect();
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;

    const graphX = convertToGraphX(x);
    const graphY = convertToGraphY(y);

    console.log("Graph click - X:", graphX, "Y:", graphY, "R:", r);

    setFormValues(graphX, graphY);
    submitForm();
}

function handleCheckResult(data) {
    if (data.status === 'complete') {
        const resultField = document.querySelector('[id*="lastHitResult"]');

        if (resultField && resultField.value !== '') {
            const result = resultField.value === 'true';
            console.log("Результат проверки:", result ? "HIT" : "MISS");
        } else {
            console.log("Поле lastHitResult пустое (валидация не пройдена)");
        }
    }
}


