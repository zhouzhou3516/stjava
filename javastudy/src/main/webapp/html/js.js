/**
 * Created by liqingzhou on 18/2/8.
 */
$(document).ready(function () {
    function focus() {
        document.getElementById("url").focus()
    }

    function formatUrl() {
        if (inputUrl.match("^" + httpPrefix)) {
            $("iframe").attr("src", inputUrl)
        } else {
            if (inputUrl.match(
                    "^" + httpsPrefix)) {
                $("iframe").attr("src",
                    httpsPrefix + inputUrl)
            } else {
                $("iframe").attr("src", httpPrefix + inputUrl), $(
                    "input#url").val(
                    httpPrefix + inputUrl)
            }
        }
    }

    function a() {
        $("#filters").fadeTo(200, 0, function () {
            $("#filters").css({
                display: "none"
            }), s = !1
        })
    }

    var r,
        inputUrl,
        width = ($("iframe").width(), 768),
        height = 1024,
        l = !0,
        randomUrl = Math.floor(8 * Math.random() + 1),
        s = !1,
        f = !1,
        httpPrefix = "http://",
        httpsPrefix = "https://";
    if (0 == randomUrl) {
        inputUrl = "http://reform.typemachine.com/";
        $("input#url").val(inputUrl);
        $("iframe").attr("src", inputUrl);
        $("footer section").html('<p>Featured site: <a href="' + inputUrl
            + '" target="_blank">Reform</a></p>');
        focus();
        $("input#url").change(function () {
            inputUrl = $("input#url").val()
        });
        $("a.loader").click(function (t) {
            formatUrl(), t.preventDefault()
        });
        $(document).keydown(function (t) {
            inputUrl = $("input#url").val();
            13 == t.keyCode && formatUrl()
        });
        $("a.resize").click(function (t) {
            width = $(this).data("dima");
            height = $(this).data("dimb");
            $("a.resize").removeClass("active");
            $(this).addClass("active");
            if (1 == l) {
                $("iframe").css({
                    width: height,
                    height: width
                }), t.preventDefault(), a()
            } else {
                0 == l && $("iframe").css({
                    width: width,
                    height: height
                }), t.preventDefault(), a()
            }
        }), $("a.orientation").click(function (t) {
            1 == l ? (l = !1, $("iframe").css({
                width: width,
                height: height
            }), $("a.orientation").text("Portrait")) : 0 == l && (l = !0, $(
                    "iframe").css({
                    width: height,
                    height: width
                }), $("a.orientation").text("Landscape")), t.preventDefault()
        }), $("nav#devices a.device").click(function (t) {
            0 == s ? ($("#filters").fadeTo(200, 1), s = !0) : 1 == s
                && a(), t.preventDefault()
        }), $("a.close, #cover").click(function (t) {
            $("#filters, #faq, #cover, #privacy").fadeTo(200, 0, function () {
                $("#filters, #faq, #cover, #privacy").css({
                    display: "none"
                }), s = !1
            }), t.preventDefault()
        }), $("a.scrolling").click(function (t) {
            0 == f ? ($("iframe").attr("scrolling", "yes"), $(
                "a.scrolling").text(
                "Hide scrollbar"), $("iframe").attr("src", inputUrl), f = !0)
                : ($(
                "iframe").attr("scrolling", "no"), $("a.scrolling").text(
                "Show scrollbar"), $("iframe").attr("src",
                inputUrl), f = !1), t.preventDefault()
        }), $("footer nav a.overlay").click(function (t) {
            r = $(this).data("type"), $("#cover, #" + r).fadeTo(200,
                1), t.preventDefault()
        })
    } else {
        if (1 == randomUrl) {
            inputUrl = "http://nylllon.com/", $(
                "input#url").val(inputUrl), $(
                "iframe").attr("src", inputUrl), $("footer section").html(
                '<p>Featured site: <a href="' + inputUrl
                + '" target="_blank">Nylllon</a></p>'), focus(), $(
                "input#url").change(function () {
                inputUrl = $("input#url").val()
            }), $("a.loader").click(function (t) {
                formatUrl(), t.preventDefault()
            }), $(document).keydown(function (t) {
                inputUrl = $("input#url").val(), 13 == t.keyCode && formatUrl()
            }), $("a.resize").click(function (t) {
                width = $(this).data("dima"), height = $(this).data("dimb"), $(
                    "a.resize").removeClass("active"), $(this).addClass(
                    "active"), 1
                == l ? $("iframe").css({
                    width: height,
                    height: width
                }) : 0 == l && $("iframe").css({
                        width: width,
                        height: height
                    }), t.preventDefault(), a()
            }), $("a.orientation").click(function (t) {
                1 == l ? (l = !1, $("iframe").css({
                    width: width,
                    height: height
                }), $("a.orientation").text("Portrait")) : 0 == l && (l = !0, $(
                        "iframe").css({
                        width: height,
                        height: width
                    }), $("a.orientation").text(
                        "Landscape")), t.preventDefault()
            }), $("nav#devices a.device").click(function (t) {
                0 == s ? ($("#filters").fadeTo(200, 1), s = !0) : 1 == s
                    && a(), t.preventDefault()
            }), $("a.close, #cover").click(function (t) {
                $("#filters, #faq, #cover, #privacy").fadeTo(200, 0,
                    function () {
                        $("#filters, #faq, #cover, #privacy").css({
                            display: "none"
                        }), s = !1
                    }), t.preventDefault()
            }), $("a.scrolling").click(function (t) {
                0 == f ? ($("iframe").attr("scrolling", "yes"), $(
                    "a.scrolling").text(
                    "Hide scrollbar"), $("iframe").attr("src",
                    inputUrl), f = !0)
                    : ($(
                    "iframe").attr("scrolling", "no"), $("a.scrolling").text(
                    "Show scrollbar"), $("iframe").attr("src",
                    inputUrl), f = !1), t.preventDefault()
            }), $("footer nav a.overlay").click(function (t) {
                r = $(this).data("type"), $("#cover, #" + r).fadeTo(200,
                    1), t.preventDefault()
            })
        } else {
            2 == randomUrl
                ? (inputUrl = "http://finely.co/", $("input#url").val(
                inputUrl), $(
                "iframe").attr(
                "src", inputUrl), $("footer section").html(
                '<p>Featured site: <a href="' + inputUrl
                + '" target="_blank">Finely</a></p>')) : 3 == randomUrl
                ? (inputUrl = "http://makerbook.net/", $("input#url").val(
                    inputUrl), $(
                    "iframe").attr("src", inputUrl), $("footer section").html(
                    '<p>Featured site: <a href="' + inputUrl
                    + '" target="_blank">Makerbook</a></p>')) : 4 == randomUrl
                    ? (inputUrl = "http://htmlarrows.com/", $("input#url").val(
                        inputUrl), $(
                        "iframe").attr("src", inputUrl), $(
                        "footer section").html(
                        '<p>Featured site: <a href="' + inputUrl
                        + '" target="_blank">HTML Arrows</a></p>')) : 5
                    == randomUrl
                        ? (inputUrl = "http://residence-mixte.com/", $(
                            "input#url").val(
                            inputUrl), $("iframe").attr("src", inputUrl), $(
                            "footer section").html(
                            '<p>Featured site: <a href="' + inputUrl
                            + '" target="_blank">R�©sidence Mixte</a></p>')) : 6
                        == randomUrl ? (inputUrl = "http://michelvelay.com/", $(
                            "input#url").val(
                            inputUrl), $("iframe").attr("src", inputUrl), $(
                            "footer section").html(
                            '<p>Featured site: <a href="' + inputUrl
                            + '" target="_blank">Michel Velay</a></p>')) : 7
                        == randomUrl
                            ? (inputUrl = "http://fonds-maisonbernard.com/fr/", $(
                                "input#url").val(inputUrl), $("iframe").attr(
                                "src",
                                inputUrl), $(
                                "footer section").html(
                                '<p>Featured site: <a href="' + inputUrl
                                + '" target="_blank">Maison Bernard</a></p>'))
                            : 8
                            == randomUrl
                            && (inputUrl = "http://fonds-maisonbernard.com/fr/", $(
                                "input#url").val(inputUrl), $("iframe").attr(
                                "src",
                                inputUrl), $(
                                "footer section").html(
                                '<p>Featured site: <a href="' + inputUrl
                                + '" target="_blank">Maison Bernard</a></p>')), focus(), $(
                "input#url").change(function () {
                inputUrl = $("input#url").val()
            }), $("a.loader").click(function (t) {
                formatUrl(), t.preventDefault()
            }), $(document).keydown(function (t) {
                inputUrl = $("input#url").val(), 13 == t.keyCode && formatUrl()
            }), $("a.resize").click(function (t) {
                width = $(this).data("dima"), height = $(this).data("dimb"), $(
                    "a.resize").removeClass("active"), $(this).addClass(
                    "active"), 1
                == l ? $("iframe").css({
                    width: height,
                    height: width
                }) : 0 == l && $("iframe").css({
                        width: width,
                        height: height
                    }), t.preventDefault(), a()
            }), $("a.orientation").click(function (t) {
                1 == l ? (l = !1, $("iframe").css({
                    width: width,
                    height: height
                }), $("a.orientation").text("Portrait")) : 0 == l && (l = !0, $(
                        "iframe").css({
                        width: height,
                        height: width
                    }), $("a.orientation").text(
                        "Landscape")), t.preventDefault()
            }), $("nav#devices a.device").click(function (t) {
                0 == s ? ($("#filters").fadeTo(200, 1), s = !0) : 1 == s
                    && a(), t.preventDefault()
            }), $("a.close, #cover").click(function (t) {
                $("#filters, #faq, #cover, #privacy").fadeTo(200, 0,
                    function () {
                        $("#filters, #faq, #cover, #privacy").css({
                            display: "none"
                        }), s = !1
                    }), t.preventDefault()
            }), $("a.scrolling").click(function (t) {
                0 == f ? ($("iframe").attr("scrolling", "yes"), $(
                    "a.scrolling").text(
                    "Hide scrollbar"), $("iframe").attr("src",
                    inputUrl), f = !0)
                    : ($(
                    "iframe").attr("scrolling", "no"), $("a.scrolling").text(
                    "Show scrollbar"), $("iframe").attr("src",
                    inputUrl), f = !1), t.preventDefault()
            }), $("footer nav a.overlay").click(function (t) {
                r = $(this).data("type"), $("#cover, #" + r).fadeTo(200,
                    1), t.preventDefault()
            })
        }
    }
});