// ===== базовые ссылки
const form = document.getElementById("valForm");
const yInput = document.getElementById("y");
const rSelect = document.getElementById("r");
const xButtonsBox = document.querySelector(".x-buttons");
const resultsBody = document.querySelector("#results tbody");

const ENDPOINT = "/~s466785/fcgi_proxy.php";
let selectedX = null;

xButtonsBox.addEventListener("click", (e) => {
  if (e.target.tagName !== "BUTTON") return;
  selectedX = e.target.value;
  xButtonsBox.querySelectorAll("button").forEach(function (b) {
    b.classList.remove("active");
  });
  e.target.classList.add("active");
});

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
    "error-x": xButtonsBox,
    "error-y": yInput,
    "error-r": rSelect,
  };
  const host = ensureErrorSlot(errorId, fallbackMap[errorId] || form);
  host.innerHTML = message;
  host.style.color = "red";
  host.style.fontSize = "medium";
  setTimeout(() => hideError(errorId), 2500);
}

function hideError(errorId) {
  const el = document.getElementById(errorId);
  if (el) el.innerHTML = "";
}

function getAllowedX() {
  return Array.from(xButtonsBox.querySelectorAll("button")).map((b) => b.value);
}
function checkXValue(xStr) {
  const ok = getAllowedX().includes(String(xStr));
  if (!ok) showError("error-x", "Необходимо выбрать значение X");
  return ok;
}

function checkYValue(yStr) {
  const norm = String(yStr).trim().replace(",", ".");
  if (norm === "" || !/^[-+]?\d+(\.\d+)?$/.test(norm)) {
    showError("error-y", "Y должен быть числом");
    return false;
  }
  const val = parseFloat(norm);
  if (val < -3 || val > 5) {
    showError("error-y", "Необходимо выбрать значение Y от -3 до 5");
    return false;
  }
  return true;
}

function getAllowedR() {
  return Array.from(rSelect.options)
    .map((o) => o.value)
    .filter((v) => v !== "");
}
function checkRValue(rStr) {
  const ok = getAllowedR().includes(String(rStr));
  if (!ok) showError("error-r", "Необходимо выбрать значение R");
  return ok;
}

function clearTable() {
  resultsBody.innerHTML = "";
}
function saveResultToTable(result) {
  const tr = document.createElement("tr");
  const hitVal =
    result.hit !== undefined
      ? result.hit
      : result.result !== undefined
      ? result.result
      : undefined;

  const cur = result.currentTime ?? result.time ?? "—";
  const exec = result.executionTime ?? result.workTime;

  tr.innerHTML = `
    <td>${result.x}</td>
    <td>${result.y}</td>
    <td>${result.r}</td>
    <td>${
      hitVal === undefined ? "undefined" : hitVal ? "есть пробитие" : "осечка"
    }</td>
    <td>${cur}</td>
    <td>${exec !== undefined ? exec + " мс" : "—"}</td>
  `;
  resultsBody.prepend(tr);
}
function renderHistory(list) {
  clearTable();
  list.forEach(saveResultToTable);
}

async function fetchHistory() {
  try {
    const resp = await fetch(`${ENDPOINT}?action=history`, {
      method: "GET",
      cache: "no-store",
    });
    if (!resp.ok) throw new Error(`HTTP ${resp.status}`);
    const data = await resp.json();
    const hist = Array.isArray(data.history) ? data.history : [];
    renderHistory(hist);
  } catch (e) {
    console.warn("Не удалось получить историю:", e.message);
  }
}

async function requestClearHistory() {
  try {
    const resp = await fetch(`${ENDPOINT}?action=clear`, {
      method: "GET",
      cache: "no-store",
    });
    if (!resp.ok) throw new Error(`HTTP ${resp.status}`);
    renderHistory([]);
  } catch (e) {
    showError("error-r", `Не удалось очистить историю: ${e.message}`);
  }
}

function send(x, y, r) {
  const params = new URLSearchParams({
    x: String(x),
    y: String(y),
    r: String(r),
  }).toString();
  const url = `${ENDPOINT}?${params}`;

  const controller = new AbortController();
  const timer = setTimeout(() => controller.abort(), 5000);

  fetch(url, { method: "GET", signal: controller.signal })
    .then((resp) => {
      clearTimeout(timer);
      if (!resp.ok) throw new Error(`HTTP ${resp.status}`);
      return resp.json();
    })
    .then((result) => {
      if (result.hit === undefined && result.result === undefined) {
        showError("error-r", "Ответ сервера не содержит поле result/hit");
        return;
      }
      const normalized = {
        ...result,
        currentTime: result.currentTime ?? result.time,
        executionTime: result.executionTime ?? result.workTime,
      };
      saveResultToTable(normalized);
    })
    .catch((err) => {
      showError(
        "error-r",
        err.name === "AbortError"
          ? "Таймаут запроса к серверу"
          : `Ошибка запроса: ${err.message}`
      );
    });
}

function validationOfForm(event) {
  event.preventDefault();
  const x = selectedX;
  const yRaw = yInput.value.trim().replace(",", ".");
  const r = rSelect.value;
  if (checkXValue(x) && checkYValue(yRaw) && checkRValue(r)) {
    send(x, yRaw, r);
  }
}

document.addEventListener("DOMContentLoaded", () => {
  form.addEventListener("submit", validationOfForm);

  form.addEventListener("reset", () => {
    xButtonsBox
      .querySelectorAll("button")
      .forEach((b) => b.classList.remove("active"));
    selectedX = null;
  });

  document.getElementById("clearHistory").addEventListener("click", () => {
    clearTable();
    requestClearHistory();
  });

  fetchHistory();
});
