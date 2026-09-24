const graphsContainer = document.getElementById("graphs");

const rScale = 150;
const halfRScale = rScale / 2;

const tickLength = 10;
const halfTickLength = tickLength / 2;


function drawGraph(canvas, xValue = null, yValue = null, rValue = null) {
    const context = canvas.getContext("2d");

    const centerX = canvas.width / 2;
    const centerY = canvas.height / 2;

    context.clearRect(0, 0, canvas.width, canvas.height);
    drawArea(context, centerX, centerY);
    drawAxes(context, centerX, centerY);
    drawTicks(context, centerX, centerY);
    drawLabels(context, centerX, centerY, rValue);

    if (xValue !== null && yValue !== null && rValue !== null) {
        drawPoint(context, centerX, centerY, xValue, yValue, rValue);
    }
}

function drawArea(context, centerX, centerY) {
    context.fillStyle = "rgba(128, 90, 213, 0.75)";

    // Прямоугольник
    context.fillRect(centerX - rScale, centerY, rScale, halfRScale);

    // Четверть круга
    context.beginPath();
    context.moveTo(centerX, centerY);
    context.arc(centerX, centerY, halfRScale, Math.PI, 3 * Math.PI / 2);
    context.lineTo(centerX, centerY);
    context.fill();

    // Треугольник
    context.beginPath();
    context.moveTo(centerX, centerY);
    context.lineTo(centerX + halfRScale, centerY);
    context.lineTo(centerX, centerY + rScale);
    context.closePath();
    context.fill();
}

function drawAxes(context, centerX, centerY) {
    context.lineWidth = 2;

    // Ось Y
    context.beginPath();
    context.moveTo(centerX, 50);
    context.lineTo(centerX, 450);
    context.stroke();

    // Стрелка оси Y
    context.beginPath();
    context.moveTo(centerX, 50);
    context.lineTo(centerX - 5, 60);

    context.moveTo(centerX, 50);
    context.lineTo(centerX + 5, 60);
    context.stroke();

    // Ось X
    context.beginPath();
    context.moveTo(50, centerY);
    context.lineTo(450, centerY);
    context.stroke();

    // Стрелка оси X
    context.beginPath();
    context.moveTo(450, centerY);
    context.lineTo(440, centerY - 5);

    context.moveTo(450, centerY);
    context.lineTo(440, centerY + 5);
    context.stroke();
}

function drawTicks(context, centerX, centerY) {
    // Засечки на оси X
    context.beginPath();

    context.moveTo(centerX - rScale, centerY - halfTickLength);
    context.lineTo(centerX - rScale, centerY + halfTickLength);

    context.moveTo(centerX - halfRScale, centerY - halfTickLength);
    context.lineTo(centerX - halfRScale, centerY + halfTickLength);

    context.moveTo(centerX + halfRScale, centerY - halfTickLength);
    context.lineTo(centerX + halfRScale, centerY + halfTickLength);

    context.moveTo(centerX + rScale, centerY - halfTickLength);
    context.lineTo(centerX + rScale, centerY + halfTickLength);

    context.stroke();

    // Засечки на оси Y
    context.beginPath();

    context.moveTo(centerX - halfTickLength, centerY - rScale);
    context.lineTo(centerX + halfTickLength, centerY - rScale);

    context.moveTo(centerX - halfTickLength, centerY - halfRScale);
    context.lineTo(centerX + halfTickLength, centerY - halfRScale);

    context.moveTo(centerX - halfTickLength, centerY + halfRScale);
    context.lineTo(centerX + halfTickLength, centerY + halfRScale);

    context.moveTo(centerX - halfTickLength, centerY + rScale);
    context.lineTo(centerX + halfTickLength, centerY + rScale);

    context.stroke();
}

function formatGraphNumber(value) {
    return Number(value.toFixed(2)).toString();
}

function drawLabels(context, centerX, centerY, rValue) {
    const rLabel = rValue === null ? "R" : formatGraphNumber(rValue);
    const halfRLabel = rValue === null ? "R/2" : formatGraphNumber(rValue / 2);
    const negativeHalfRLabel = rValue === null ? "-R/2" : formatGraphNumber(-rValue / 2);
    const negativeRLabel = rValue === null ? "-R" : formatGraphNumber(-rValue);

    // Подписи к осям

    context.fillStyle = "black";
    context.font = "25px fantasy";

    context.textAlign = "center";

    context.textBaseline = "bottom";
    context.fillText("X", 445, centerY - 5);

    context.textBaseline = "top";
    context.fillText("Y", centerX + 20, 45);

    context.textBaseline = "bottom";
    context.fillText(rLabel, centerX + rScale, centerY - 5);
    context.fillText(halfRLabel, centerX + halfRScale, centerY - 5);
    context.fillText(negativeHalfRLabel, centerX - halfRScale, centerY - 5);
    context.fillText(negativeRLabel, centerX - rScale, centerY - 5);

    context.textAlign = "left";
    context.textBaseline = "middle";
    context.fillText(rLabel, centerX + 10, centerY - rScale);
    context.fillText(halfRLabel, centerX + 10, centerY - halfRScale);
    context.fillText(negativeHalfRLabel, centerX + 10, centerY + halfRScale);
    context.fillText(negativeRLabel, centerX + 10, centerY + rScale);
}

function drawPoint(context, centerX, centerY, xValue, yValue, rValue) {
    const scale = rScale / rValue;

    const pixelX = centerX + xValue * scale;
    const pixelY = centerY - yValue * scale;

    context.beginPath();
    context.arc(pixelX, pixelY, 5, 0, Math.PI * 2);
    context.fillStyle = "black";
    context.fill();
}

function createCanvas() {
    const canvas = document.createElement("canvas");

    canvas.width = 500;
    canvas.height = 500;
    canvas.className = "graph-canvas";

    return canvas;
}

function redrawGraphs(xValues, yValue, rValue) {
    graphsContainer.innerHTML = "";

    if (xValues.length === 0) {
        const canvas = createCanvas();
        graphsContainer.appendChild(canvas);
        drawGraph(canvas, null, null);
        return;
    }

    xValues.forEach(x => {
        const canvas = createCanvas();
        graphsContainer.appendChild(canvas);

        drawGraph(canvas, x, yValue, rValue);
    });
}

redrawGraphs([], null, null);

const form = document.getElementById("point-form");
const errorBox = document.getElementById("form-error");
const resultsBody = document.getElementById("results-body");
const clearResultsButton = document.getElementById("clear-results");
const STORAGE_KEY = "results";
const xButtons = document.querySelectorAll('input[name="x"]');


xButtons.forEach(button => {
    button.addEventListener("click", () => {
        button.classList.toggle("selected");
    });
});

form.addEventListener("reset", () => {
    clearError();

    xButtons.forEach(button => {
        button.classList.remove("selected");
    });

    redrawGraphs([], null, null);
});

clearResultsButton.addEventListener("click", () => {
    resultsBody.innerHTML = "";
    localStorage.removeItem(STORAGE_KEY);
});

function showError(message) {
    errorBox.textContent = message;
    errorBox.classList.add("error-active");
}

function clearError() {
    errorBox.textContent = "Тут будут сообщения об ошибках...";
    errorBox.classList.remove("error-active");
}

function isPointInside(x, y, r) {
    const inRectangle = x >= -r && x <= 0 && y >= -r / 2 && y <= 0;

    const inCircle = x <= 0 && y >= 0 && x * x + y * y <= (r / 2) * (r / 2);

    const inTriangle = x >= 0 && y <= 0 && y >= 2 * x - r;

    return inRectangle || inCircle || inTriangle;
}

function formatDate(timestamp) {
    return new Date(timestamp).toLocaleString("ru-RU");
}

function addResultRow(result) {
    const row = document.createElement("tr");

    row.innerHTML = `
        <td>${result.x}</td>
        <td>${result.y}</td>
        <td>${result.r}</td>
        <td>${result.hit ? "Попадание" : "Промах"}</td>
        <td>${formatDate(result.timestamp)}</td>
    `;

    resultsBody.appendChild(row);
}

function saveResults(results) {
    const savedResults = JSON.parse(localStorage.getItem(STORAGE_KEY)) || [];

    savedResults.push(...results);

    localStorage.setItem(STORAGE_KEY, JSON.stringify(savedResults));
}

function loadResults() {
    const savedResults = JSON.parse(localStorage.getItem(STORAGE_KEY)) || [];

    savedResults.forEach(result => {
        addResultRow(result);
    });
}

loadResults();

form.addEventListener("submit", event => {
    event.preventDefault();
    clearError();

    const selectedXButtons = document.querySelectorAll(".x-option.selected");

    const yInput = document.getElementById("y");

    const yText = yInput.value.trim();

    const rInput = document.getElementById("r");

    const rText = rInput.value.trim();

    const xValues = Array.from(selectedXButtons)
        .map(button => Number(button.value));

    if (xValues.length === 0) {
        showError("Выберите хотя бы одно значение X");
        return;
    }

    if (yText === "") {
        showError("Введите значение Y");
        return;
    }

    if (!/^-?\d+([.,]\d+)?$/.test(yText)) {
        showError("Y должен быть числом");
        return;
    }

    if (rText === "") {
        showError("Введите значение R");
        return;
    }

    if (!/^-?\d+([.,]\d+)?$/.test(rText)) {
        showError("R должен быть числом");
        return;
    }

    const y = Number(yText.replace(",", "."));

    const r = Number(rText.replace(",", "."));

    if (y <= -3 || y >= 3) {
        showError("Y должен находиться в диапазоне (-3; 3)");
        return;
    }

    if (r <= 1 || r >= 4) {
        showError("R должен находиться в диапазоне (1; 4)");
        return;
    }

    const timestamp = Date.now();

    const results = xValues.map(x => {
        return {
            x, y, r, hit: isPointInside(x, y, r), timestamp
        };
    });

    results.forEach(result => {
        addResultRow(result);
    });

    saveResults(results);

    form.reset();

    redrawGraphs(xValues, y, r);
});



