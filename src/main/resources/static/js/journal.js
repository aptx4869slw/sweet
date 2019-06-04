window.onload = initData();

function initData() {
    $.ajax({
        type: 'GET',
        dataType: "json",
        url: "api/journals",
        success: function (res) {
            for (i = 0; i < res.length; i++) {
                var timeline__item = "<div class=\"timeline__item timeline__item--0\">";
                var timeline__item__station = "<div class=\"timeline__item__station\"></div>";
                var timeline__item__content = "<div class=\"timeline__item__content\">";
                var timeline__item__content__date = "<h2 class=\"timeline__item__content__date\">2019 May. 30</h2>";
                var timeline__item__content__description = "<p class=\"timeline__item__content__description\">所有页面基本完成，准备开始改造，放到后台数据库中去.</p>";
                var foot = "</div></div>";
                var journal = timeline__item + timeline__item__station + timeline__item__content + timeline__item__content__date + timeline__item__content__description + foot;
                $("#journal").prepend(journal);
            }
        },
        error: function () {
            alert("发生错误");
        }
    });
}

var defaultOffset = '50%';
for (i = 0; i < 17; i++) {
    customWayPoint('.timeline__item--' + i, 'timeline__item-bg', defaultOffset);
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