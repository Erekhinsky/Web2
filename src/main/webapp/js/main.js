$(function () {

    let y;

    function getX() {
        let x = $("input[name='x']").val();
        let regex = /^[+-]?[0-9]{1,10}([.,]?[0-9]{1,10})?$/;
        if (x.match(regex)) {
            return parseFloat(x);
        } else {
            return NaN;
        }
    }

    function getY() {
        if ($("#Y-table").hasClass("ready")) {
            return y;         //fixed
        } else {
            return NaN;
        }
    }

    $("input[type='button'].illuminated-animated").click(function (event) {
        let buttons = document.querySelectorAll("input[type='button'].illuminated-animated");
        y = $(this).val();
        buttons.forEach(function (element) {
            element.style.boxShadow = null;
            element.style.backgroundColor = null;
            element.style.color = null;
        });
        $(this).css({ "backgroundColor": "#837666", "color": "white" });
        // $(this).style.backgroundColor = "#f41c52";
        // $(this).style.color = "white";
    })

    $(".rb").click(function () {
        redrawPoints();
    });

    function getR() {
        // if ($("#R-table-value").hasClass("ready")) {
        //     return parseFloat($("input[type='checkbox']").val());
        // } else {
        //     return NaN;
        // }

        let checked = document.getElementsByClassName('rb');
        let r = 0;
        for (let el = 0; checked[el]; el++) {
            if (checked[el].checked) {
                r += Number(checked[el].value);
            }
        }
        if (r <= 0 || r > 15) {
            alert("Значение R не выбрано");
            return NaN
        } else {
            return r;
        }
    }

    function validateX() {
        let x = getX();
        const MIN_X = -3;
        const MAX_X = 5;
        if (x > MIN_X && x < MAX_X) {
            $("input[type='text']").removeClass('text-error');
            return true;
        } else {
            $("input[type='text']").addClass('text-error');
            return false;
        }
    }

    function validateY() {
        let valY = $("input[type='button']");
        if (valY.click) {
            $(valY.removeClass("button-error"));
            return true;
        } else {
            $(".input-button").addClass("button-error");
            return false;
        }
    }

    function validateR() {
        let rTableValue = $('#R-table-value');
        let ready = rTableValue.hasClass("ready");
        if (!ready) {
            rTableValue.addClass("buttons-error");
        } else {
            rTableValue.removeClass("buttons-error");
        }
        return ready;
    }

    function validateData() {
        let x = validateX();
        let y = validateY();
        let r = validateR();
        return x && y && r;
    }

    $("input[type='chekbox']").click(function () {
        if ($(this).hasClass("rb")) {
            $(this).removeClass("rb");
            $("#R-table-value").removeClass("ready");
        } else {
            $(this).addClass("rb");
            $(this).siblings("input[type='checkbox'].rb").removeClass("rb");
            $("#R-table-value").addClass("ready");
        }
    });

    $("input[type='reset']").click(function () {
        let reset = $("input[type='checkbox']");
        if (reset.hasClass("rb")) {
            reset.removeClass("rb");
            $("#R-table-value").removeClass("ready");
        }
        // document.getElementById('outputTable').getElementsByTagName("tbody")[0].innerHTML = document.getElementById("outputTable").rows[0].innerHTML;
        // document.getElementById("X-table").value = "";
        // document.querySelector("#X-table").removeClass('text-error');
        // document.querySelectorAll('input[type="checkbox"]').forEach(r => r.checked = false);
        // document.getElementById(("Y-table").value.backgroundColor = null);
        clearTable();
    })

    function clearTable() {
        $.ajax({
            url: "/Web2-1/app",
            type: "GET",
            data: {clear: "true"},
            success: function (data) {
                $("#outputTable").html(data);
                drawPoints();
            }
        });
    }

    $("form").submit(function (event) {
        event.preventDefault();
        if (validateData()) {
            requestWithArgs(getX(), getY());
        } else alert("Проверьте введенные значения")
    })


    function requestWithArgs(xArg, yArg) {
        $.ajax({
            url: "/Web2-1/app",
            type: "GET",
            data: {x: xArg, y: yArg, r: getR()},
            success: function (data) {
                $("#outputTable").html(data);
                drawPoints();
            }
        });
    }

    // let svg = document.querySelector("svg");
    // $("svg").click(function (event) {
    //     if ($("#R-table-value").hasClass("ready")) {
    //         let position = getMousePosition(svg, event)
    //         let x = position.x;
    //         let y = position.y;
    //         setPointer(position.x_draw, position.y_draw);
    //         let k = 120/getR(); //отношение радиуса и плоскости
    //         x = (x / k).toFixed(1);
    //         y = (y / k).toFixed(1);
    //         requestWithArgs(x,y);
    //     } else {
    //         alert("Choose R value.");
    //     }
    // })
    //
    // function getMousePosition(svg, event) {
    //     let rect = svg.getBoundingClientRect();
    //     return {
    //         x: event.clientX - (rect.left+150),
    //         y: (event.clientY - (rect.top+150))*-1,
    //         x_draw: event.clientX - rect.left,
    //         y_draw: event.clientY - rect.top
    //     };
    // }
    //
    // function setPointer(x, y) {
    //     console.log(x + " " + y);
    //     svg.insertAdjacentHTML("beforeend", `<circle r="5" cx="${x}" cy="${y}" fill-opacity="0.7" fill="red" stroke="firebrick"></circle>`);
    // }

    let svg = document.querySelector("svg");

    function drawPoints() {
        $("circle").remove();
        document.querySelectorAll("#outputTable tbody tr").forEach(function (row) {
            let x = parseFloat(row.cells[0].innerText);
            let y = parseFloat(row.cells[1].innerText);
            let r = parseFloat(row.cells[2].innerText);
            let cX = 150 + x * 120 / r;
            let cY = 150 - y * 120 / r;
            svg.insertAdjacentHTML("beforeend", `<circle r="5" cx="${cX}" cy="${cY}" fill-opacity="0.7" fill="red" stroke="firebrick"></circle>`);
        });

        /*
        $("#outputTable tbody tr").each(function (row) {
            alert($("#outputTable tbody tr"));
            alert(row);
            let x = parseFloat(row.cells[0].innerText);
            let y = parseFloat(row.cells[1].innerText);
            let r = parseFloat(row.cells[2].innerText);
            let cX = 150 + x * 120 / r;
            let cY = 150 - y * 120 / r;
            svg.insertAdjacentHTML("beforeend", `<circle r="5" cx="${cX}" cy="${cY}" fill-opacity="0.7" fill="red" stroke="firebrick"></circle>`);
        })*/
    }

    function redrawPoints() {
        $("circle").remove();
        document.querySelectorAll("#outputTable tbody tr").forEach(function (row) {
            let x = parseFloat(row.cells[0].innerText);
            let y = parseFloat(row.cells[1].innerText);
            let r = getR();
            let cX = 150 + x * 120 / r;
            let cY = 150 - y * 120 / r;
            svg.insertAdjacentHTML("beforeend", `<circle r="5" cx="${cX}" cy="${cY}" fill-opacity="0.7" fill="red" stroke="firebrick"></circle>`);
        });
    }

    $("svg").click(function (e) {
        if ($("#R-table-value").hasClass("ready")) {
            let x = (e.offsetX - 150) * getR() / 120;
            let y = (150 - e.offsetY) * getR() / 120;
            requestWithArgs(x.toFixed(1), y.toFixed(1));
        } else {
            alert("Choose R value.");
        }
    })

});