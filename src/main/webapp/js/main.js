$(function () {

    function getX() {
        let x = $("input[name='x-value']").val();
        let regex = /^[+-]?[0-9]{1,10}([.]?[0-9]{1,10})?$/;
        if (x.match(regex)) {
            return parseFloat(x);
        } else {
            return NaN;
        }
    }

    function getY() {
        if ($("#Y-table").hasClass("ready")) {
            return parseFloat($("button[type='button'].selected-y").val());
        } else {
            return NaN;
        }
    }

    function getR() {
        if ($("#R-table-value").hasClass("ready")) {
            return parseFloat($("button[type='button'].selected-r").val());
        } else {
            return NaN;
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
        if ($("input[type='button']").is(":checked")) {
            $(".input-button").removeClass("button-error");
            return true;
        } else {
            $(".input-button").addClass("button-error");
            return false;
        }
    }

    function validateR() {
        let ready = $("#R-table-value").hasClass("ready");
        if (!ready) {
            $("#R-table-value").addClass("buttons-error");
        } else {
            $("#R-table-value").removeClass("buttons-error");
        }
        return ready;
    }

    function validateData() {
        let x = validateX();
        let y = validateY();
        let r = validateR();
        return x && y && r;
    }

    $("button[type='checkbox']").click(function () {
        if ($(this).hasClass("selected-r")) {
            $(this).removeClass("selected-r");
            $("#r-buttons").removeClass("ready");
        } else {
            $(this).addClass("selected-r");
            $(this).siblings("button.selected-r").removeClass("selected-r");
            $("#r-buttons").addClass("ready");
        }
    });

    $("button[type='reset']").click(function () {
        if ($("button[type='checkbox']").hasClass("selected-r")) {
            $("button[type='checkbox']").removeClass("selected-r");
            $("#R-table-value").removeClass("ready");
        }
        clearTable();
    })

    function clearTable() {
        $.ajax({
            url: "controller",
            type: "GET",
            data: {clear: "true"},
            success: function (data) {
                $("table").html(data);
                drawPoints();
            }
        });
    }

    $("form").submit(function (event) {
        event.preventDefault();
        if (validateData()) {
            requestWithArgs(getX(), getY());
        }
    })

    function requestWithArgs(xArg, yArg) {
        $.ajax({
            url: "controller",
            type: "GET",
            data: {x: xArg, y: yArg, r: getR()},
            success: function (data) {
                $("table").html(data);
                drawPoints();
            }
        });
    }

    $("svg").click(function (e) {
        if ($("#r-buttons").hasClass("ready")) {
            let x = (e.offsetX - 193) * getR() / 140;
            let y = (193 - e.offsetY) * getR() / 140;
            requestWithArgs(x.toFixed(1), y.toFixed(1));
        } else {
            alert("Choose R value.");
        }
    })

    function drawPoints() {
        $("circle").remove();
        $("table tbody tr").each(function (row) {
            let x = parseFloat(row.cells[0].innerText);
            let y = parseFloat(row.cells[1].innerText);
            let r = parseFloat(row.cells[2].innerText);
            let cX = 150 + x * 120 / r;
            let cY = 150 - y * 120 / r;
            $("svg").append(`<circle r="4" cx=${cX} cy=${cY} fill="black"
                fill-opacity="0.85"></circle>`);
        })
    }
});