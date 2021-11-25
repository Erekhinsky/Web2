<%@ page import="utils.Result" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <meta charset="UTF-8">
    <link href="css/main_page.css" rel="stylesheet">
    <link href="css/check_button.css" rel="stylesheet">
    <link rel="icon" type="image/ico" href="images/baby-yoda-icon.png">
    <title>ЛР №2 Ерехинский А.В.</title>
</head>
<body>
<header class="header">
    <%
        ArrayList<Result> results = new ArrayList<>();
        if (request.getServletContext().getAttribute("results") != null) {
            results = (ArrayList<Result>) request.getServletContext().getAttribute("results");
        }
    %>
    <table>
        <tr>
            <td>
                <h1>Web ЛР №2 Вариант 33475</h1>
                <h2>Ерехинский Андрей Владимирович</h2>
            </td>
            <td>
                <img src="images/baby-yoda-very-cute.png" alt="baby yoda" width=35%>
            </td>
        </tr>
    </table>
</header>
<br>
<table id="mainTable" class="shaded">
    <tr height="400px">
        <td>
            <form method="get" action="/Web2-1/">
                <table id="interaction-table" width="500x400">
                    <tr>
                        <td>
                            <table id="value-table">
                                <tr>
                                    <td>
                                        <fieldset id="X-table">
                                            <label for="x">
                                                Введите X:
                                            </label>
                                            <input id="x" name="x" required type="text"
                                                   placeholder="X ∈ (-3;5)" maxlength="6"
                                                   size="15">
                                        </fieldset>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <fieldset>
                                            <table id="Y-table" class="ready">
                                                <tr>
                                                    <td rowspan="3">Выберите Y:</td>
                                                    <td><input name="y" class="illuminated-animated"
                                                               type="button"
                                                               value="-2.0"></td>
                                                    <td><input name="y" class="illuminated-animated"
                                                               type="button"
                                                               value="-1.5"></td>
                                                    <td><input name="y" class="illuminated-animated"
                                                               type="button"
                                                               value="-1.0"></td>
                                                </tr>
                                                <tr>
                                                    <td><input name="y" class="illuminated-animated"
                                                               type="button"
                                                               value="-0.5"></td>
                                                    <td><input name="y" class="illuminated-animated"
                                                               type="button"
                                                               value="0"></td>
                                                    <td><input name="y" class="illuminated-animated"
                                                               type="button"
                                                               value="0.5"></td>
                                                </tr>
                                                <tr>
                                                    <td><input name="y" class="illuminated-animated"
                                                               type="button"
                                                               value="1.0"></td>
                                                    <td><input name="y" class="illuminated-animated"
                                                               type="button"
                                                               value="1.5"></td>
                                                    <td><input name="y" class="illuminated-animated"
                                                               type="button"
                                                               value="2.0"></td>
                                                    <td><input name="true-y" type="hidden"></td>
                                                </tr>
                                            </table>
                                        </fieldset>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <fieldset>
                                            <table id="R-table">
                                                <tr>
                                                    <td>
                                                        <table id="R-table-value" class="ready">
                                                            <tr>
                                                                <td>
                                                                    Выберите R:
                                                                </td>
                                                                <td>
                                                                    1<label for="r_1"></label><input type="checkbox"
                                                                                                     name="r"
                                                                                                     autocomplete="off"
                                                                                                     class="rb" id="r_1"
                                                                                                     value="1">
                                                                </td>
                                                                <td>
                                                                    2<label for="r_2"></label><input type="checkbox"
                                                                                                     name="r"
                                                                                                     class="rb"
                                                                                                     autocomplete="off"
                                                                                                     id="r_2" value="2">
                                                                </td>
                                                                <td>
                                                                    3<label for="r_3"></label><input type="checkbox"
                                                                                                     name="r"
                                                                                                     class="rb" id="r_3"
                                                                                                     autocomplete="off"
                                                                                                     value="3">
                                                                </td>
                                                                <td>
                                                                    4<label for="r_4"></label><input type="checkbox"
                                                                                                     name="r"
                                                                                                     class="rb" id="r_4"
                                                                                                     value="4"
                                                                                                     autocomplete="off">
                                                                <td>
                                                                    5<label for="r_5"></label><input type="checkbox"
                                                                                                     name="r"
                                                                                                     class="rb" id="r_5"
                                                                                                     value="5"
                                                                                                     autocomplete="off">
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </fieldset>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            <fieldset>
                                <table id="check-table">
                                    <tr>
                                        <td>
                                            <input id="checkButton" class="input-button" type="submit"
                                                   value="Отправить"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input id="clearButton" class="input-button" type="reset" value="Сбросить"/>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </td>
                    </tr>
                </table>
            </form>
        </td>
        <td>
            <table id="graph" class="shaded" align="left">
                <tr>
                    <td>
                        <svg xmlns="http://www.w3.org/2000/svg">
                            <line x1="0" y1="150" x2="300" y2="150" stroke="#b1b28f"></line>
                            <line x1="150" y1="0" x2="150" y2="300" stroke="#b1b28f"></line>

                            <line x1="270" y1="148" x2="270" y2="152" stroke="#b1b28f"></line>
                            <text x="265" y="140">R</text>

                            <line x1="210" y1="148" x2="210" y2="152" stroke="#b1b28f"></line>
                            <text x="200" y="140">R/2</text>

                            <line x1="90" y1="148" x2="90" y2="152" stroke="#b1b28f"></line>
                            <text x="75" y="140">-R/2</text>

                            <line x1="30" y1="148" x2="30" y2="152" stroke="#b1b28f"></line>
                            <text x="20" y="140">-R</text>

                            <line x1="148" y1="30" x2="152" y2="30" stroke="#b1b28f"></line>
                            <text x="156" y="35">R</text>

                            <line x1="148" y1="90" x2="152" y2="90" stroke="#b1b28f"></line>
                            <text x="156" y="95">R/2</text>

                            <line x1="148" y1="210" x2="152" y2="210" stroke="#b1b28f"></line>
                            <text x="156" y="215">-R/2</text>

                            <line x1="148" y1="270" x2="152" y2="270" stroke="#b1b28f"></line>
                            <text x="156" y="275">-R</text>

                            <polygon points="300,150 295,155 295,145" fill="#b1b28f" stroke="#b1b28f"></polygon>
                            SVG namespace
                            www.w3.org
                            <polygon points="150,0 145,5 155,5" fill="#b1b28f" stroke="#b1b28f"></polygon>

                            <polygon points="150,150 150,30 30,30 30,150" fill-opacity="0.4" fill="b1b28f"></polygon>
                            <polygon points="150,150 210,150 150,270" fill-opacity="0.4" fill="b1b28f"></polygon>
                            <path d="M150 150 L 210 150 C 220 130 200 80 150 90 L Z" fill-opacity="0.4"
                                  fill="b1b28f"></path>
                            <circle id="pointer" r="4" cx="150" cy="150" fill-opacity="0.7" fill="green"
                                    stroke="green" visibility="hidden"></circle>
                            <%
                                for (Result result : results) {
                            %>
                            <circle r="4" cx="<%=150 + Math.round(120 * result.getX() / result.getR())%>"
                                    cy="<%=150 - Math.round(120 * result.getY() / result.getR())%>" fill="green"
                                    fill-opacity="0.85"></circle>
                            <%
                                }
                            %>
                        </svg>
                    </td>
                </tr>
            </table>
        </td>
        <td>
            <img src="images/baby-yoda-spec.png" alt="baby-yoda-cute" width="300x300" align="left">
        </td>
        <td width="200px"></td>
    </tr>
    <tr>
        <td colspan="3">
            <div class="table">
                <table id="outputTable">
                    <thead>
                    <tr>
                        <th class="coords-column">X</th>
                        <th class="coords-column">Y</th>
                        <th class="coords-column">R</th>
                        <th class="time-column">Current time</th>
                        <th class="time-column">Execution time</th>
                        <th>Hit fact</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (Result result : results) {
                    %>
                    <tr>
                        <td><%=result.getX()%>
                        </td>
                        <td><%=result.getY()%>
                        </td>
                        <td><%=result.getR()%>
                        </td>
                        <td><%=result.getCurrentTime()%>
                        </td>
                        <td><%=result.getExecutionTime()%>
                        </td>
                        <td><%=result.getHitFact()%>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </td>
    </tr>
</table>
<%--<script>--%>
<%--    let request = "x=0" + "&y=0" + "&r=0";--%>
<%--    fetch("app", {--%>
<%--        method: "GET",--%>
<%--        headers: {"Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"},--%>
<%--        body: request--%>
<%--    }).then(response => response.text()).then(function (serverAnswer) {--%>
<%--        document.getElementById("outputTable").innerHTML = serverAnswer;--%>
<%--    }).catch(err => createNotification(`Ошибка HTTP ${err.textContent}. Повторите попытку позже.`));   --%>
<%--</script>--%>
<script src="js/jquery.js"></script>
<script src="js/main.js"></script>
</body>
</html>