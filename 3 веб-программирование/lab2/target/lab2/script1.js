const form = document.getElementById("valForm");
const xInput = document.getElementById("x");
const yInput = document.getElementById("y");
const rRadios = document.querySelectorAll('input[name="r"]');
const svg = document.querySelector(".graph svg");
const clearHistoryBtn = document.getElementById("clearHistory");
const rContainer = document.getElementById("r");


function ensureErrorSlot(id, fallbackEl) {
    let el = document.getElementById(id);
    if (!el) {
        el = document.createElement("div");
        el.id = id;
        fallbackEl.insertAdjacentElement("afterend", el);
    }
    return el;
}

function showError(errorId, message) {
    const fallbackMap = {
        "error-x": xInput,
        "error-y": yInput,
        "error-r": rContainer || form
    };
    const host = ensureErrorSlot(errorId, fallbackMap[errorId] || form);
    host.innerHTML = message;
    host.style.color = "red";
    host.style.fontSize = "medium";
}

function hideError(errorId) {
    const el = document.getElementById(errorId);
    if (el) el.innerHTML = "";
}

function clearAllErrors() {
    hideError("error-x");
    hideError("error-y");
    hideError("error-r");
}

function whichFieldFromError(msg = "") {
    const e = msg.toLowerCase();
    if (/(^|\W)y(\W|$)|игрек|значение y/.test(e)) return "error-y";
    if (/(^|\W)x(\W|$)|значение x/.test(e)) return "error-x";
    if (/(^|\W)r(\W|$)|значение r/.test(e)) return "error-r";
    return "error-r";
}

function showServerError(msg) {
    const slot = whichFieldFromError(msg);
    showError(slot, msg || "Неизвестная ошибка");
}


function checkXValue(xStr) {
    const norm = String(xStr).trim().replace(",", ".");

    if (norm === "" || !/^[-+]?\d+(\.\d+)?$/.test(norm)) {
        showError("error-x", "X должен быть числом");
        return false;
    }

    const val = parseFloat(norm);

    if (val < -3 || val > 3) {
        showError("error-x", "X должен быть в диапазоне [-3; 3]");
        return false;
    }

    if (/^-3\.0*[1-9]\d*$/.test(norm)) {
        showError("error-x", "X должен быть в диапазоне [-3; 3]");
        return false;
    }
    if (/^3\.0*[1-9]\d*$/.test(norm)) {
        showError("error-x", "X должен быть в диапазоне [-3; 3]");
        return false;
    }

    return true;
}

function checkYValue(yStr) {
    const norm = String(yStr).trim().replace(",", ".");

    if (norm === "" || !/^[-+]?\d+(\.\d+)?$/.test(norm)) {
        showError("error-y", "Y должен быть числом");
        return false;
    }

    const val = parseFloat(norm);

    if (val < -3 || val > 3) {
        showError("error-y", "Необходимо выбрать значение Y от -3 до 3");
        return false;
    }

    if (/^-3\.0*[1-9]\d*$/.test(norm)) {
        showError("error-y", "Необходимо выбрать значение Y от -3 до 3");
        return false;
    }
    if (/^3\.0*[1-9]\d*$/.test(norm)) {
        showError("error-y", "Необходимо выбрать значение Y от -3 до 3");
        return false;
    }

    return true;
}


function getSelectedR() {
    for (const r of rRadios) {
        if (r.checked) return r.value;
    }
    return null;
}

function checkRValue() {
    const r = getSelectedR();
    if (!r) {
        showError("error-r", "Необходимо выбрать значение R");
        return false;
    }
    return true;
}


function validateForm(event) {
    clearAllErrors();

    const okX = checkXValue(xInput.value);
    const okY = checkYValue(yInput.value);
    const okR = checkRValue();

    if (!(okX && okY && okR)) {
        event.preventDefault();
        return false;
    }

    return true;
}

if (form) {
    form.addEventListener("submit", validateForm);
    form.addEventListener("reset", () => {
        clearAllErrors();
    });
}


if (svg && form) {
    svg.addEventListener("click", (event) => {
        clearAllErrors();

        const rStr = getSelectedR();
        if (!rStr) {
            showError(
                "error-r",
                "Невозможно определить координаты точки: сначала выберите R"
            );
            return;
        }

        const r = parseFloat(rStr);

        const rect = svg.getBoundingClientRect();
        const clickX = event.clientX - rect.left;
        const clickY = event.clientY - rect.top;

        const scale = r / 100.0;

        const x = (clickX - 150) * scale;
        const y = (150 - clickY) * scale;

        if (x < -3 || x > 3 || y < -3 || y > 3) {
            showError("error-r", "Клик вне диапазона координат [-3; 3]");
            return;
        }

        xInput.value = x.toFixed(3);
        yInput.value = y.toFixed(3);

        form.submit();
    });

}


if (clearHistoryBtn) {
    clearHistoryBtn.addEventListener("click", () => {
        window.location.href = "controller";
    });
}
