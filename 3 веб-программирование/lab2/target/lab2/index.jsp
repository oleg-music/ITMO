<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="git.olegmusic.lab2.PointResult" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Lab 2</title>
    <style>
        body {
            background-color: #a5c9e7;
        }

        .header {
            background-color: #99c2ff;
            text-align: center;
            padding: 15px;
            border: 2px;
            border-style: solid;
            border-color: black;
            border-radius: 8px;
            font-size: 20px;
            font-family: monospace;
            color: black;
        }

        .header p {
            margin: 5px 0;
        }

        .graph line {
            stroke: black;
        }

        .x-buttons {
            margin: 10px 0;
        }

        .x-buttons button.active {
            background-color: #66a3ff;
            outline: 2px solid black;
        }

        .x-buttons button {
            margin: 3px;
            padding: 5px 10px;
            border: 2px solid black;
            background-color: #cce0ff;
            cursor: pointer;
        }

        .x-buttons button:hover {
            background-color: #99c2ff;
        }

        .submit-block {
            margin-top: 10px;
        }

        .submit-block button {
            margin: 5px;
            padding: 6px 12px;
            border: 2px solid black;
            background-color: #cce0ff;
            cursor: pointer;
        }

        .submit-block button:hover {
            background-color: #99c2ff;
        }

        .result-table {
            margin: 20px auto;
            width: fit-content;
        }

        .result-table table {
            background-color: #99c2ff;
        }

        .result-table th,
        .result-table td {
            border: 2px solid black;
            padding: 8px 12px;
        }

        .main-container,
        .result-table {
            margin: 20px auto;
            padding: 15px;
            border: 2px solid black;
            border-radius: 8px;
            background-color: #f9f9f9;
            font-family: monospace;
            text-align: center;
        }

        input[name="y"] {
            border-radius: 7px;
        }

        #valForm {
            background-color: #51c6f0;
            border-radius: 8px;
            padding: 10px 0px;
        }

        #clearHistory {
            margin-top: 10px;
            padding: 6px 12px;
            border: 2px solid black;
            background-color: #cce0ff;
            cursor: pointer;
        }

        #clearHistory:hover {
            background-color: #99c2ff;
        }

        .wrap {
            position: relative;
            padding: 3px;
            border-radius: 8px;
            overflow: hidden;
            display: inline-block;
        }

        .wrap::before {
            content: "";
            position: absolute;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            border-radius: inherit;
            background: conic-gradient(
                    red,
                    orange,
                    yellow,
                    green,
                    cyan,
                    blue,
                    violet,
                    red
            );
            animation: spin 4s linear infinite;
        }

        .wrap input {
            position: relative;
            z-index: 1;
            border: none;
            border-radius: 6px;
            padding: 6px 10px;
            background: #fff;
            color: #000;
            font-size: 15px;
            outline: none;
            min-width: 150px;
        }

        @keyframes spin {
            to {
                transform: rotate(360deg);
            }
        }
    </style>
</head>
<body>
<div class="header">
    <p>ФИО: Музыка Олег Сергеевич</p>
    <p>Группа: P3220</p>
    <p>Вариант: 20093</p>
</div>

<div class="main-container">
    <div class="graph">
        <svg width="300" height="300" xmlns="http://www.w3.org/2000/svg">
            <line x1="0" y1="150" x2="300" y2="150"/>
            <line x1="150" y1="0" x2="150" y2="300"/>

            <line
                    class="arrow"
                    x1="300"
                    y1="150"
                    x2="290"
                    y2="155"
                    stroke="black"
            />
            <line
                    class="arrow"
                    x1="300"
                    y1="150"
                    x2="290"
                    y2="145"
                    stroke="black"
            />

            <line class="arrow" x1="150" y1="0" x2="145" y2="10"/>
            <line class="arrow" x1="150" y1="0" x2="155" y2="10"/>

            <!-- Засечки на оси -->
            <line x1="200" x2="200" y1="153" y2="147"></line>
            <line x1="250" x2="250" y1="153" y2="147"></line>

            <line x1="50" x2="50" y1="153" y2="147"></line>
            <line x1="100" x2="100" y1="153" y2="147"></line>

            <line x1="147" x2="153" y1="100" y2="100"></line>
            <line x1="147" x2="153" y1="50" y2="50"></line>

            <line x1="147" x2="153" y1="200" y2="200"></line>
            <line x1="147" x2="153" y1="250" y2="250"></line>

            <!-- Подписи к засечкам на осях  -->
            <text x="190" y="140">R/2</text>
            <text x="245" y="140">R</text>

            <text x="40" y="140">-R</text>
            <text x="85" y="140">-R/2</text>

            <text x="160" y="105">R/2</text>
            <text x="160" y="55">R</text>

            <text x="160" y="205">-R/2</text>
            <text x="160" y="255">-R</text>

            <text x="160" y="10">y</text>
            <text x="290" y="140">x</text>

            <polygon
                    points="50,150 150,50 150,150"
                    fill="#2e70f4"
                    fill-opacity="0.5"
            />

            <rect
                    x="150"
                    y="150"
                    width="50"
                    height="100"
                    fill="#2e70f4"
                    fill-opacity="0.5"
            />

            <path
                    d="M150,150 L150,100 A50,50 0 0,1 200,150 Z"
                    fill="#2e70f4"
                    fill-opacity="0.5"
            />

            <%
                Object histAttrSvg = request.getAttribute("history");
                if (histAttrSvg != null && histAttrSvg instanceof java.util.List) {
                    java.util.List<git.olegmusic.lab2.PointResult> historySvg =
                            (java.util.List<git.olegmusic.lab2.PointResult>) histAttrSvg;

                    if (!historySvg.isEmpty()) {
                        // текущее значение R = R последней точки
                        git.olegmusic.lab2.PointResult last = historySvg.get(historySvg.size() - 1);
                        double currentR = Double.parseDouble(last.getRStr().replace(',', '.'));

                        for (git.olegmusic.lab2.PointResult p : historySvg) {
                            try {
                                double rVal = Double.parseDouble(p.getRStr().replace(',', '.'));

                                // Рисуем только точки с таким же R
                                if (rVal != currentR) continue;

                                double xVal = Double.parseDouble(p.getXStr().replace(',', '.'));
                                double yVal = Double.parseDouble(p.getYStr().replace(',', '.'));

                                // Перевод координат
                                double px = 150 + xVal * 100.0 / currentR;
                                double py = 150 - yVal * 100.0 / currentR;
            %>
            <circle cx="<%= px %>" cy="<%= py %>" r="3" fill="black"/>
            <%
                            } catch (Exception ignore) {
                            }
                        }
                    }
                }
            %>


        </svg>
    </div>

    <form
            id="valForm"
            method="post"
            action="controller"
    >
        <label for="x">Координата X:</label>
        <div class="wrap">
            <input type="text" id="x" name="x" placeholder="от -3 до 3"/>
        </div>
        <div id="error-x" class="error-msg"></div>

        <label for="y">Координата Y:</label>
        <div class="wrap">
            <input type="text" id="y" name="y" placeholder="от -3 до 3"/>
        </div>
        <div id="error-y" class="error-msg"></div>

        <label for="r">Радиус R:</label>
        <div id="r">
            <label><input type="radio" name="r" value="1"/>1</label>
            <label><input type="radio" name="r" value="2"/>2</label>
            <label><input type="radio" name="r" value="3"/>3</label>
            <label><input type="radio" name="r" value="4"/>4</label>
            <label><input type="radio" name="r" value="5"/>5</label>
        </div>
        <div id="error-r" class="error-msg"></div>

        <div class="submit-block">
            <button type="submit">Отправить данные</button>
            <button type="reset">Сбросить данные</button>
        </div>
    </form>
</div>

<div class="result-table">
    <table id="results">
        <thead>
        <tr>
            <th>Координата X</th>
            <th>Координата Y</th>
            <th>Радиус R</th>
            <th>Факт попадания в область</th>
            <th>Текущее время</th>
            <th>Время выполнения (ms)</th>
        </tr>
        </thead>
        <tbody>
        <%
            Object histAttr = request.getAttribute("history");
            if (histAttr != null && histAttr instanceof List) {
                List<PointResult> history = (List<PointResult>) histAttr;
                for (PointResult res : history) {
        %>
        <tr>
            <td><%= res.getXStr() %>
            </td>
            <td><%= res.getYStr() %>
            </td>
            <td><%= res.getRStr() %>
            </td>
            <td><%= res.isHit() ? "Попадание" : "Не попадание" %>
            </td>
            <td><%= res.getCurrentTime() %>
            </td>
            <td><%= res.getExecTimeMs() %>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>

    </table>
</div>
<button id="clearHistory" type="button">Очистить историю</button>

<script src="script1.js"></script>
</body>
</html>
