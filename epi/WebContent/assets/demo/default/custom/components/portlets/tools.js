var PortletTools = function() {
    var e = function() {
            toastr.options.showDuration = 1e3
        },
        t = function() {
            var e = $("#m_portlet_tools_1").mPortlet();
            e.on("beforeCollapse", function(e) {
                setTimeout(function() {
                    toastr.info("Before collapse event fired!")
                }, 100)
            }), e.on("afterCollapse", function(e) {
                setTimeout(function() {
                    toastr.warning("Before collapse event fired!")
                }, 2e3)
            }), e.on("beforeExpand", function(e) {
                setTimeout(function() {
                    toastr.info("Before expand event fired!")
                }, 100)
            }), e.on("afterExpand", function(e) {
                setTimeout(function() {
                    toastr.warning("After expand event fired!")
                }, 2e3)
            }), e.on("beforeRemove", function(e) {
                return toastr.info("Before remove event fired!"), confirm("Are you sure to remove this portlet ?")
            }), e.on("afterRemove", function(e) {
                setTimeout(function() {
                    toastr.warning("After remove event fired!")
                }, 2e3)
            }), e.on("reload", function(e) {
                toastr.info("Leload event fired!"), mApp.block(e.getSelf(), {
                    overlayColor: "#ffffff",
                    type: "loader",
                    state: "accent",
                    opacity: .3,
                    size: "lg"
                }), setTimeout(function() {
                    mApp.unblock(e.getSelf())
                }, 2e3)
            }), e.on("afterFullscreenOn", function(e) {
                var t = e.getBody().find("> .m-scrollable");
                t.data("original-height", t.data("max-height")), t.css("height", "100%"), t.css("max-height", "100%"), mApp.initScroller(t, {})
            }), e.on("afterFullscreenOff", function(e) {
                toastr.warning("After fullscreen off event fired!");
                var t = e.getBody().find("> .m-scrollable");
                t.css("height", t.data("original-height")), t.data("max-height", t.data("original-height")), mApp.initScroller(t, {})
            })
        },
        o = function() {
            var e = $("#m_portlet_tools_2").mPortlet();
            e.on("beforeCollapse", function(e) {
                setTimeout(function() {
                    toastr.info("Before collapse event fired!")
                }, 100)
            }), e.on("afterCollapse", function(e) {
                setTimeout(function() {
                    toastr.warning("Before collapse event fired!")
                }, 2e3)
            }), e.on("beforeExpand", function(e) {
                setTimeout(function() {
                    toastr.info("Before expand event fired!")
                }, 100)
            }), e.on("afterExpand", function(e) {
                setTimeout(function() {
                    toastr.warning("After expand event fired!")
                }, 2e3)
            }), e.on("beforeRemove", function(e) {
                return toastr.info("Before remove event fired!"), confirm("Are you sure to remove this portlet ?")
            }), e.on("afterRemove", function(e) {
                setTimeout(function() {
                    toastr.warning("After remove event fired!")
                }, 2e3)
            }), e.on("reload", function(e) {
                toastr.info("Leload event fired!"), mApp.block(e.getSelf(), {
                    overlayColor: "#000000",
                    type: "spinner",
                    state: "brand",
                    opacity: .05,
                    size: "lg"
                }), setTimeout(function() {
                    mApp.unblock(e.getSelf())
                }, 2e3)
            })
        },
        n = function() {
            var e = $("#m_portlet_tools_3").mPortlet();
            e.on("beforeCollapse", function(e) {
                setTimeout(function() {
                    toastr.info("Before collapse event fired!")
                }, 100)
            }), e.on("afterCollapse", function(e) {
                setTimeout(function() {
                    toastr.warning("Before collapse event fired!")
                }, 2e3)
            }), e.on("beforeExpand", function(e) {
                setTimeout(function() {
                    toastr.info("Before expand event fired!")
                }, 100)
            }), e.on("afterExpand", function(e) {
                setTimeout(function() {
                    toastr.warning("After expand event fired!")
                }, 2e3)
            }), e.on("beforeRemove", function(e) {
                return toastr.info("Before remove event fired!"), confirm("Are you sure to remove this portlet ?")
            }), e.on("afterRemove", function(e) {
                setTimeout(function() {
                    toastr.warning("After remove event fired!")
                }, 2e3)
            }), e.on("reload", function(e) {
                toastr.info("Leload event fired!"), mApp.block(e.getSelf(), {
                    type: "loader",
                    state: "success",
                    message: "Please wait..."
                }), setTimeout(function() {
                    mApp.unblock(e.getSelf())
                }, 2e3)
            }), e.on("afterFullscreenOn", function(e) {
                var t = e.getBody().find("> .m-scrollable");
                t.data("original-height", t.data("max-height")), t.css("height", "100%"), t.css("max-height", "100%"), mApp.initScroller(t, {})
            }), e.on("afterFullscreenOff", function(e) {
                toastr.warning("After fullscreen off event fired!");
                var t = e.getBody().find("> .m-scrollable");
                t.css("height", t.data("original-height")), t.data("max-height", t.data("original-height")), mApp.initScroller(t, {})
            })
        },
        r = function() {
            var e = $("#m_portlet_tools_4").mPortlet();
            e.on("beforeCollapse", function(e) {
                setTimeout(function() {
                    toastr.info("Before collapse event fired!")
                }, 100)
            }), e.on("afterCollapse", function(e) {
                setTimeout(function() {
                    toastr.warning("Before collapse event fired!")
                }, 2e3)
            }), e.on("beforeExpand", function(e) {
                setTimeout(function() {
                    toastr.info("Before expand event fired!")
                }, 100)
            }), e.on("afterExpand", function(e) {
                setTimeout(function() {
                    toastr.warning("After expand event fired!")
                }, 2e3)
            }), e.on("beforeRemove", function(e) {
                return toastr.info("Before remove event fired!"), confirm("Are you sure to remove this portlet ?")
            }), e.on("afterRemove", function(e) {
                setTimeout(function() {
                    toastr.warning("After remove event fired!")
                }, 2e3)
            }), e.on("reload", function(e) {
                toastr.info("Leload event fired!"), mApp.block(e.getSelf(), {
                    type: "loader",
                    state: "brand",
                    message: "Please wait..."
                }), setTimeout(function() {
                    mApp.unblock(e.getSelf())
                }, 2e3)
            }), e.on("afterFullscreenOn", function(e) {
                var t = e.getBody().find("> .m-scrollable");
                t.data("original-height", t.data("max-height")), t.css("height", "100%"), t.css("max-height", "100%"), mApp.initScroller(t, {})
            }), e.on("afterFullscreenOff", function(e) {
                toastr.warning("After fullscreen off event fired!");
                var t = e.getBody().find("> .m-scrollable");
                t.css("height", t.data("original-height")), t.data("max-height", t.data("original-height")), mApp.initScroller(t, {})
            })
        },
        i = function() {
            var e = $("#m_portlet_tools_5").mPortlet();
            e.on("beforeCollapse", function(e) {
                setTimeout(function() {
                    toastr.info("Before collapse event fired!")
                }, 100)
            }), e.on("afterCollapse", function(e) {
                setTimeout(function() {
                    toastr.warning("Before collapse event fired!")
                }, 2e3)
            }), e.on("beforeExpand", function(e) {
                setTimeout(function() {
                    toastr.info("Before expand event fired!")
                }, 100)
            }), e.on("afterExpand", function(e) {
                setTimeout(function() {
                    toastr.warning("After expand event fired!")
                }, 2e3)
            }), e.on("beforeRemove", function(e) {
                return toastr.info("Before remove event fired!"), confirm("Are you sure to remove this portlet ?")
            }), e.on("afterRemove", function(e) {
                setTimeout(function() {
                    toastr.warning("After remove event fired!")
                }, 2e3)
            }), e.on("reload", function(e) {
                toastr.info("Leload event fired!"), mApp.block(e.getSelf(), {
                    type: "loader",
                    state: "brand",
                    message: "Please wait..."
                }), setTimeout(function() {
                    mApp.unblock(e.getSelf())
                }, 2e3)
            }), e.on("afterFullscreenOn", function(e) {
                toastr.info("After fullscreen on event fired!"), mApp.initScroller(scrollable, {})
            }), e.on("afterFullscreenOff", function(e) {
                toastr.warning("After fullscreen off event fired!")
            })
        },
        f = function() {
            var e = $("#m_portlet_tools_6").mPortlet();
            e.on("beforeCollapse", function(e) {
                setTimeout(function() {
                    toastr.info("Before collapse event fired!")
                }, 100)
            }), e.on("afterCollapse", function(e) {
                setTimeout(function() {
                    toastr.warning("Before collapse event fired!")
                }, 2e3)
            }), e.on("beforeExpand", function(e) {
                setTimeout(function() {
                    toastr.info("Before expand event fired!")
                }, 100)
            }), e.on("afterExpand", function(e) {
                setTimeout(function() {
                    toastr.warning("After expand event fired!")
                }, 2e3)
            }), e.on("beforeRemove", function(e) {
                return toastr.info("Before remove event fired!"), confirm("Are you sure to remove this portlet ?")
            }), e.on("afterRemove", function(e) {
                setTimeout(function() {
                    toastr.warning("After remove event fired!")
                }, 2e3)
            }), e.on("reload", function(e) {
                toastr.info("Leload event fired!"), mApp.block(e.getSelf(), {
                    type: "loader",
                    state: "brand",
                    message: "Please wait..."
                }), setTimeout(function() {
                    mApp.unblock(e.getSelf())
                }, 2e3)
            }), e.on("afterFullscreenOn", function(e) {
                toastr.info("After fullscreen on event fired!"), mApp.initScroller(scrollable, {})
            }), e.on("afterFullscreenOff", function(e) {
                toastr.warning("After fullscreen off event fired!")
            })
        };
    return {
        init: function() {
            e(), t(), o(), n(), r(), i(), f()
        }
    }
}();
jQuery(document).ready(function() {
    PortletTools.init()
});
