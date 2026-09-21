function drawGraph(rValue) {
    context.clearRect(0, 0, canvas.width, canvas.height);

    const canvas = document.getElementById("graph");
    const context = canvas.getContext("2d");

    const centerX = canvas.width / 2;
    const centerY = canvas.height / 2;

    const rScale = 150;
    const halfRScale = rScale / 2;

    const tickLength = 10;
    const halfTickLength = tickLength / 2;

    context.lineWidth = 2;

    //
    // Заливка фигур области
    //
    context.fillStyle = "rgba(128, 90, 213, 0.75)";

    // Прямоугольник
    context.fillRect(centerX - rScale, centerY, rScale, halfRScale);

    // Четверть круга
    context.beginPath();
    context.moveTo(centerX, centerY);
    context.arc(centerX, centerY, rScale, -Math.PI / 2, 0);
    context.lineTo(centerX, centerY);
    context.fill();

    // Треугольник
    context.beginPath();
    context.moveTo(centerX, centerY);
    context.lineTo(centerX, centerY);
    context.lineTo(centerX + rScale, centerY);
    context.lineTo(centerX, centerY + rScale);
    context.fill();

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

    // Подписи к осям

    context.fillStyle = "black";
    context.font = "25px fantasy";

    context.textAlign = "center";

    context.textBaseline = "bottom";
    context.fillText("X", 445, centerY - 5);

    context.textBaseline = "top";
    context.fillText("Y", centerX + 20, 45);

    context.textBaseline = "bottom";
    context.fillText(String(rValue), centerX + rScale, centerY - 5);
    context.fillText(String(rValue / 2), centerX + halfRScale, centerY - 5);
    context.fillText(String(-rValue / 2), centerX - halfRScale, centerY - 5);
    context.fillText(String(-rValue), centerX - rScale, centerY - 5);

    context.textAlign = "left";
    context.textBaseline = "center";
    context.fillText(String(rValue), centerX + 10, centerY - rScale + 15);
    context.fillText(String(rValue / 2), centerX + 10, centerY - halfRScale + 15);
    context.fillText(String(-rValue / 2), centerX + 10, centerY + halfRScale + 15);
    context.fillText(String(-rValue), centerX + 10, centerY + rScale + 15);
}




