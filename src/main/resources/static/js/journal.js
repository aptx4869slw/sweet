window.onload = initData();

var defaultOffset = '50%';

function initData() {
    $.ajax({
        type: 'GET',
        dataType: "json",
        url: "api/journals",
        success: function (res) {
            for (i = 0; i < res.length; i++) {
                var item0 = "<div class=\"timeline__item timeline__item--" + i + "\">";
                var item1 = "<div class=\"timeline__item__station\"></div>";
                var item2 = "<div class=\"timeline__item__content\">";
                var item3 = "<h2 class=\"timeline__item__content__date\">" + res[i].contentDate + "</h2>";
                var item4 = "<p class=\"timeline__item__content__description\">" + res[i].content + "</p>";
                var item5 = "</div></div>";
                var journal = item0 + item1 + item2 + item3 + item4 + item5;
                $("#journal").append(journal);
                customWayPoint('.timeline__item--' + i, 'timeline__item-bg', defaultOffset);
            }
        },
        error: function () {
            alert("发生错误");
        }
    });
}

function customWayPoint(className, addClassName, customOffset) {
    var waypoints = $(className).waypoint({
        handler: function (direction) {
            if (direction == "down") {
                $(className).addClass(addClassName);
            } else {
                $(className).removeClass(addClassName);
            }
        },
        offset: customOffset
    });
}