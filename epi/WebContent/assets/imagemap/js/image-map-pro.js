; (function ($, window, document, undefined) {
    "use strict";

    // Variable to hold the currently fullscreen image map
    var fullscreenMap = undefined;

    // API

    /*
        HTML API
        ---------------------------------------
        data-imp-highlight-shape-on-mouseover
        data-imp-highlight-shape-on-click
        data-imp-unhighlight-shape-on-mouseover
        data-imp-unhighlight-shape-on-click

        data-imp-open-tooltip-on-mouseover
        data-imp-open-tooltip-on-click
        data-imp-close-tooltip-on-mouseover
        data-imp-close-tooltip-on-click

        data-imp-trigger-shape-on-mouseover
        data-imp-trigger-shape-on-click
        data-imp-untrigger-shape-on-mouseover
        data-imp-untrigger-shape-on-click

        EXAMPLE
        ---------------------------------------
        <div data-imp-highlight-shape-on-mouseover="myshape1" data-imp-image-map-name="map1">Click</div>
    */

    // Events (called by the plugin, need implementation)
    $.imageMapProInitialized = function (imageMapName) {

    }
    $.imageMapProEventHighlightedShape = function (imageMapName, shapeName) {

    }
    $.imageMapProEventUnhighlightedShape = function (imageMapName, shapeName) {

    }
    $.imageMapProEventClickedShape = function (imageMapName, shapeName) {

    }
    $.imageMapProEventOpenedTooltip = function (imageMapName, shapeName) {

    }
    $.imageMapProEventClosedTooltip = function (imageMapName, shapeName) {

    }
    // Actions (called by a third party, implemented here)
    $.imageMapProHighlightShape = function (imageMapName, shapeName) {
        var i = $('[data-shape-title="' + shapeName + '"]').data('index');
        var s = instances[imageMapName].settings.spots[i];

        // Add shape to the list of highlighted shapes by the API
        if (instances[imageMapName].apiHighlightedShapes.indexOf(i) == -1) {
            instances[imageMapName].apiHighlightedShapes.push(i);
        }

        // If the shape is a master, then add its slaves too
        if (instances[imageMapName].connectedShapes[s.id]) {
            for (var j = 0; j < instances[imageMapName].connectedShapes[s.id].length; j++) {
                var index = instances[imageMapName].connectedShapes[s.id][j].index;
                if (instances[imageMapName].apiHighlightedShapes.indexOf(index) == -1) {
                    instances[imageMapName].apiHighlightedShapes.push(index);
                }
            }
        }

        instances[imageMapName].highlightShape(i, true);
    }
    $.imageMapProUnhighlightShape = function (imageMapName, shapeName) {
        var i = $('[data-shape-title="' + shapeName + '"]').data('index');
        var s = instances[imageMapName].settings.spots[i];

        // Remove the shape from the list of highlighted shapes by the API
        if (instances[imageMapName].apiHighlightedShapes.indexOf(i) != -1) {
            var arrayIndex = instances[imageMapName].apiHighlightedShapes.indexOf(i);
            instances[imageMapName].apiHighlightedShapes.splice(arrayIndex, 1);
        }

        // If the shape is a master, then remove its slaves too, and unhighlight them
        if (instances[imageMapName].connectedShapes[s.id]) {
            for (var j = 0; j < instances[imageMapName].connectedShapes[s.id].length; j++) {
                var index = instances[imageMapName].connectedShapes[s.id][j].index;
                var index2 = instances[imageMapName].apiHighlightedShapes.indexOf(index);
                instances[imageMapName].apiHighlightedShapes.splice(index2, 1);
                instances[imageMapName].unhighlightShape(index);
            }
        }

        instances[imageMapName].unhighlightShape(i);
    }
    $.imageMapProOpenTooltip = function (imageMapName, shapeName) {
        var i = $('[data-shape-title="' + shapeName + '"]').data('index');

        instances[imageMapName].showTooltip(i);
        instances[imageMapName].updateTooltipPosition(i);

        // Add the tooltip to the list of tooltips opened with the API
        if (instances[imageMapName].apiOpenedTooltips.indexOf(i) == -1) {
            instances[imageMapName].apiOpenedTooltips.push(i);
        }
    }
    $.imageMapProHideTooltip = function (imageMapName, shapeName) {
        var i = $('[data-shape-title="' + shapeName + '"]').data('index');

        // Remove the tooltip to the list of tooltips opened with the API
        if (instances[imageMapName].apiOpenedTooltips.indexOf(i) != -1) {
            var arrayIndex = instances[imageMapName].apiOpenedTooltips.indexOf(i);
            instances[imageMapName].apiOpenedTooltips.splice(arrayIndex, 1);
        }

        instances[imageMapName].hideTooltip(i);
    }
    $.imageMapProReInitMap = function (imageMapName) {
        instances[imageMapName].init();
    }
    $.imageMapProIsMobile = function () {
        return isMobile();
    }

    // Create the defaults once
    var pluginName = "imageMapPro";
    var default_settings = $.imageMapProEditorDefaults;
    var default_spot_settings = $.imageMapProShapeDefaults;

    var instances = new Array();

    // The actual plugin constructor
    function Plugin(element, options) {
        this.element = element;
        // jQuery has an extend method which merges the contents of two or
        // more objects, storing the result in the first object. The first object
        // is generally empty as we don't want to alter the default options for
        // future instances of the plugin
        this.settings = $.extend(true, {}, default_settings, options);

        this.root = $(element);
        this.wrap = undefined;
        this.ui = undefined;
        this.shapeContainer = undefined;
        this.shapeSvgContainer = undefined;
        this.fullscreenTooltipsContainer = undefined;

        // Cache
        this.visibleFullscreenTooltip = undefined;
        this.visibleFullscreenTooltipIndex = undefined;
        this.bodyOverflow = undefined;
        this.highlightedShapes = new Array();
        this.connectedShapes = new Array();
        this.openedTooltips = new Array();
        this.apiHighlightedShapes = new Array();
        this.apiOpenedTooltips = new Array();

        // Flags
        this.touch = false;
        this.fullscreenTooltipVisible = false;

        this.init();
    }

    // Avoid Plugin.prototype conflicts
    $.extend(Plugin.prototype, {
        init: function () {
            var self = this;

            self.parseSettings();

            instances[this.settings.general.name] = this;

            this.id = Math.random() * 100;

            // Various preparations
            for (var i = 0; i < self.settings.spots.length; i++) {
                // Fill out any missing properties
                var s = self.settings.spots[i];
                var d = $.extend(true, {}, default_spot_settings);
                s = $.extend(true, d, s);
                self.settings.spots[i] = $.extend(true, {}, s);

                // Support for image maps created before 3.1.0
                if (!self.settings.spots[i].title || self.settings.spots[i].title.length == 0) {
                    self.settings.spots[i].title = self.settings.spots[i].id;
                }

                // Create connected shape groups
                if (s.connected_to != '') {
                    if (!this.connectedShapes[s.connected_to]) {
                        this.connectedShapes[s.connected_to] = new Array();
                    }

                    this.connectedShapes[s.connected_to].push({ id: s.id, index: i });
                }
            }

            var img = new Image();
            img.src = self.settings.image.url;

            self.loadImage(img, function () {
                // Image loading
            }, function () {
                // Image loaded
                var html = '';

                self.root.addClass('imp-initialized');
                self.root.attr('data-image-map-pro-id', self.settings.id);
                self.root.html('<div class="imp-wrap"></div>');
                self.wrap = self.root.find('.imp-wrap');

                html += '<img src="' + self.settings.image.url + '">';
                self.wrap.html(html);

                self.centerImageMap();
                self.adjustSize();
                self.drawShapes();
                self.addTooltips();
                self.initFullscreen();
                self.events();
                self.APIEvents();
                self.animateShapesLoop();

                $.imageMapProInitialized(self.settings.general.name);
            });

            $(window).on('resize', function () {
                if (self.openedTooltips.length > 0) {
                    self.updateTooltipPosition(self.openedTooltips[self.openedTooltips.length - 1]);
                }
            });
        },
        parseSettings: function () {
            // If there is a value for the old image URL in the settings, use that instead
            // This happens when the user updates from an older version using the old format and the
            // image map has not been saved yet.
            if (this.settings.general.image_url) {
                this.settings.image.url = this.settings.general.image_url;
            }
        },
        loadImage: function (image, cbLoading, cbComplete) {
            if (!image.complete || image.naturalWidth === undefined || image.naturalHeight === undefined) {
                cbLoading();
                $(image).on('load', function () {
                    $(image).off('load');
                    cbComplete();
                });
            } else {
                cbComplete();
            }
        },

        centerImageMap: function () {
            var self = this;

            if (parseInt(self.settings.general.center_image_map, 10) == 1) {
                self.wrap.css({
                    margin: '0 auto'
                });
            }
        },
        adjustSize: function () {
            var self = this;

            // If the image map is in fullscreen mode, adjust according to the window and return
            if (parseInt(self.settings.runtime.is_fullscreen, 10) == 1) {
                var screenRatio = $(window).width() / $(window).height();
                var imageRatio = self.settings.general.width / self.settings.general.height;

                if (imageRatio < screenRatio) {
                    self.settings.general.width = $(window).height() * imageRatio;
                    self.settings.general.height = $(window).height();
                } else {
                    self.settings.general.width = $(window).width();
                    self.settings.general.height = $(window).width() / imageRatio;
                }

                self.wrap.css({
                    width: self.settings.general.width,
                    height: self.settings.general.height,
                });

                return;
            }

            // If the image map is responsive, fit the map to its parent element
            if (parseInt(self.settings.general.responsive, 10) == 1) {
                if (parseInt(self.settings.general.preserve_quality, 10) == 1) {
                    var width = self.settings.general.naturalWidth || self.settings.general.width;
                    self.wrap.css({
                        'max-width': self.settings.general.naturalWidth
                    });
                }
            } else {
                self.wrap.css({
                    width: self.settings.general.width,
                    height: self.settings.general.height,
                });
            }
        },
        drawShapes: function () {
            var self = this;

            // Make sure spot coordinates are numbers
            for (var i = 0; i < self.settings.spots.length; i++) {
                var s = self.settings.spots[i];

                s.x = parseFloat(s.x);
                s.y = parseFloat(s.y);
                s.width = parseFloat(s.width);
                s.height = parseFloat(s.height);
                s.default_style.stroke_width = parseInt(s.default_style.stroke_width);
                s.mouseover_style.stroke_width = parseInt(s.mouseover_style.stroke_width);

                if (s.type == 'poly') {
                    for (var j = 0; j < s.points.length; j++) {
                        s.points[j].x = parseFloat(s.points[j].x);
                        s.points[j].y = parseFloat(s.points[j].y);
                    }
                }
            }
            self.settings.general.width = parseInt(self.settings.general.width);
            self.settings.general.height = parseInt(self.settings.general.height);

            self.wrap.prepend('<div class="imp-shape-container"></div>');
            self.shapeContainer = self.wrap.find('.imp-shape-container');

            var html = '';
            var hasPolygons = false;

            // If the image map is responsive, use natural width and height
            // Otherwise, use the width/height set from the editor
            var imageMapWidth = self.settings.general.width;
            var imageMapHeight = self.settings.general.height;
            if (parseInt(self.settings.general.responsive, 10) == 1) {
                imageMapWidth = self.settings.general.naturalWidth;
                imageMapHeight = self.settings.general.naturalHeight;
            }
            var svgHtml = '<svg class="hs-poly-svg" viewBox="0 0 ' + imageMapWidth + ' ' + imageMapHeight + '" preserveAspectRatio="none">';

            for (var i = 0; i < self.settings.spots.length; i++) {
                var s = self.settings.spots[i];
                var style = this.calcStyles(s.default_style, i);

                if (s.type == 'spot') {
                    if (parseInt(s.default_style.use_icon, 10) == 1) {
                        html += '<div class="imp-shape imp-shape-spot" id="' + s.id + '" data-shape-title="' + s.title + '" style="' + style + '" data-index=' + i + '>';

                        // Icon
                        if (s.default_style.icon_type == 'library') {
                            html += '   <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" viewBox="' + s.default_style.icon_svg_viewbox + '" xml:space="preserve" width="' + s.width + 'px" height="' + s.height + 'px">';
                            html += '       <path style="fill:' + s.default_style.icon_fill + '" d="' + s.default_style.icon_svg_path + '"></path>';
                            html += '   </svg>';
                        } else {
                            html += '<img src="' + s.default_style.icon_url + '" style="width: ' + s.width + 'px; height: ' + s.height + 'px">';
                        }

                        // Shadow
                        if (parseInt(s.default_style.icon_shadow, 10) == 1) {
                            var shadowStyle = '';

                            shadowStyle += 'width: ' + s.width + 'px;';
                            shadowStyle += 'height: ' + s.height + 'px;';
                            shadowStyle += 'top: ' + s.height / 2 + 'px;';

                            html += '<div style="' + shadowStyle + '" class="imp-shape-icon-shadow"></div>';
                        }

                        html += '</div>';
                    } else {
                        html += '<div class="imp-shape imp-shape-spot" id="' + s.id + '" data-shape-title="' + s.title + '" style="' + style + '" data-index=' + i + '></div>';
                    }
                }
                if (s.type == 'rect') {

                    html += '<div class="imp-shape imp-shape-rect" id="' + s.id + '" data-shape-title="' + s.title + '" style="' + style + '" data-index=' + i + '></div>';
                }
                if (s.type == 'oval') {

                    html += '<div class="imp-shape imp-shape-oval" id="' + s.id + '" data-shape-title="' + s.title + '" style="' + style + '" data-index=' + i + '></div>';
                }
                if (s.type == 'poly') {
                    svgHtml += '<polygon class="imp-shape imp-shape-poly" style="' + style + '" data-index=' + i + ' id="' + s.id + '" data-shape-title="' + s.title + '" points="';

                    var shapeWidthPx = imageMapWidth * (s.width / 100);
                    var shapeHeightPx = imageMapHeight * (s.height / 100);

                    s.vs = new Array();
                    for (var j = 0; j < s.points.length; j++) {
                        var x = (imageMapWidth * (s.x / 100)) + (s.points[j].x / 100) * (shapeWidthPx);
                        var y = (imageMapHeight * (s.y / 100)) + (s.points[j].y / 100) * (shapeHeightPx);

                        svgHtml += x + ',' + y + ' ';

                        // Cache an array of coordinates for later use in mouse events
                        s.vs.push([x, y]);
                    }

                    svgHtml += '"></polygon>';
                }
            }
            svgHtml += '</svg>';

            self.shapeContainer.html(html + svgHtml);
        },
        addTooltips: function () {
            var self = this;

            if (self.settings.tooltips.fullscreen_tooltips == 'always' || (self.settings.tooltips.fullscreen_tooltips == 'mobile-only' && isMobile())) {
                // Fullscreen tooltips
                if (!self.fullscreenTooltipsContainer) {
                    $('.imp-fullscreen-tooltips-container[data-image-map-id="' + self.settings.id + '"]').remove();
                    $('body').prepend('<div class="imp-fullscreen-tooltips-container" data-image-map-id="' + self.settings.id + '"></div>');
                    self.fullscreenTooltipsContainer = $('.imp-fullscreen-tooltips-container[data-image-map-id="' + self.settings.id + '"]');
                }

                var html = '';

                for (var i = 0; i < self.settings.spots.length; i++) {
                    var s = self.settings.spots[i];

                    // Fix new lines
                    s.tooltip_content.plain_text = s.tooltip_content.plain_text.replace(/\n/g, '<br>');

                    var style = '';
                    var color_bg = hexToRgb(s.tooltip_style.background_color) || { r: 0, b: 0, g: 0 };

                    style += 'padding: ' + s.tooltip_style.padding + 'px;';
                    style += 'background: rgba(' + color_bg.r + ', ' + color_bg.g + ', ' + color_bg.b + ', ' + s.tooltip_style.background_opacity + ');';

                    if (self.settings.tooltips.tooltip_animation == 'none') {
                        style += 'opacity: 0;';
                    }
                    if (self.settings.tooltips.tooltip_animation == 'fade') {
                        style += 'opacity: 0;';
                        style += 'transition-property: opacity;-moz-transition-property: opacity;-webkit-transition-property: opacity;';
                    }
                    if (self.settings.tooltips.tooltip_animation == 'grow') {
                        style += 'transform: scale(0, 0);-moz-transform: scale(0, 0);-webkit-transform: scale(0, 0);';
                        style += 'transition-property: transform;-moz-transition-property: -moz-transform;-webkit-transition-property: -webkit-transform;';
                        style += 'transform-origin: 50% 50%;-moz-transform-origin: 50% 50%;-webkit-transform-origin: 50% 50%;';
                    }

                    html += '<div class="imp-fullscreen-tooltip" style="' + style + '" data-index="' + i + '">';
                    html += '   <div class="imp-tooltip-close-button" data-index="' + i + '"><i class="fa fa-times" aria-hidden="true"></i></div>';

                    if (s.tooltip_content.content_type == 'plain-text') {
                        var style = '';
                        style += 'color: ' + s.tooltip_content.plain_text_color + ';';

                        html += '<div class="imp-tooltip-plain-text" style="' + style + '">' + s.tooltip_content.plain_text + '</div>';
                    } else {
                        if (s.tooltip_content.squares_json) {
                            html += $.squaresRendererRenderObject(s.tooltip_content.squares_json);
                        } else {
                            html += $.squaresRendererRenderObject(s.tooltip_content.squares_settings);
                        }
                    }

                    html += '</div>';
                }

                self.fullscreenTooltipsContainer.html(html);
            } else {
                // Regular tooltips
                var html = '';

                for (var i = 0; i < self.settings.spots.length; i++) {
                    var s = self.settings.spots[i];

                    // Fix new lines
                    s.tooltip_content.plain_text = s.tooltip_content.plain_text.replace(/\n/g, '<br>');

                    var style = '';
                    var color_bg = hexToRgb(s.tooltip_style.background_color) || { r: 0, b: 0, g: 0 };

                    style += 'border-radius: ' + s.tooltip_style.border_radius + 'px;';
                    style += 'padding: ' + s.tooltip_style.padding + 'px;';
                    style += 'background: rgba(' + color_bg.r + ', ' + color_bg.g + ', ' + color_bg.b + ', ' + s.tooltip_style.background_opacity + ');';

                    if (self.settings.tooltips.tooltip_animation == 'none') {
                        style += 'opacity: 0;';
                    }
                    if (self.settings.tooltips.tooltip_animation == 'fade') {
                        style += 'opacity: 0;';
                        style += 'transition-property: opacity;-moz-transition-property: opacity;-webkit-transition-property: opacity;';
                    }
                    if (self.settings.tooltips.tooltip_animation == 'grow') {
                        style += 'transform: scale(0, 0);-moz-transform: scale(0, 0);-webkit-transform: scale(0, 0);';
                        style += 'transition-property: transform;-moz-transition-property: -moz-transform;-webkit-transition-property: -webkit-transform;';

                        if (s.tooltip_style.position == 'top') {
                            style += 'transform-origin: 50% 100%;-moz-transform-origin: 50% 100%;-webkit-transform-origin: 50% 100%;';
                        }
                        if (s.tooltip_style.position == 'bottom') {
                            style += 'transform-origin: 50% 0%;-moz-transform-origin: 50% 0%;-webkit-transform-origin: 50% 0%;';
                        }
                        if (s.tooltip_style.position == 'left') {
                            style += 'transform-origin: 100% 50%;-moz-transform-origin: 100% 50%;-webkit-transform-origin: 100% 50%;';
                        }
                        if (s.tooltip_style.position == 'right') {
                            style += 'transform-origin: 0% 50%;-moz-transform-origin: 0% 50%;-webkit-transform-origin: 0% 50%;';
                        }
                    }

                    html += '<div class="imp-tooltip" style="' + style + '" data-index="' + i + '">';

                    if (s.tooltip_style.position == 'top') {
                        html += '   <div class="hs-arrow hs-arrow-bottom" style="border-top-color: rgba(' + color_bg.r + ', ' + color_bg.g + ', ' + color_bg.b + ', ' + s.tooltip_style.background_opacity + ');"></div>';
                    }
                    if (s.tooltip_style.position == 'bottom') {
                        html += '   <div class="hs-arrow hs-arrow-top" style="border-bottom-color: rgba(' + color_bg.r + ', ' + color_bg.g + ', ' + color_bg.b + ', ' + s.tooltip_style.background_opacity + ');"></div>';
                    }
                    if (s.tooltip_style.position == 'left') {
                        html += '   <div class="hs-arrow hs-arrow-right" style="border-left-color: rgba(' + color_bg.r + ', ' + color_bg.g + ', ' + color_bg.b + ', ' + s.tooltip_style.background_opacity + ');"></div>';
                    }
                    if (s.tooltip_style.position == 'right') {
                        html += '   <div class="hs-arrow hs-arrow-left" style="border-right-color: rgba(' + color_bg.r + ', ' + color_bg.g + ', ' + color_bg.b + ', ' + s.tooltip_style.background_opacity + ');"></div>';
                    }

                    if (s.tooltip_content.content_type == 'plain-text') {
                        var style = '';
                        style += 'color: ' + s.tooltip_content.plain_text_color + ';';

                        html += '<div class="imp-tooltip-plain-text" style="' + style + '">' + s.tooltip_content.plain_text + '</div>';
                    } else {
                        if (s.tooltip_content.squares_json) {
                            html += $.squaresRendererRenderObject(s.tooltip_content.squares_json);
                        } else {
                            html += $.squaresRendererRenderObject(s.tooltip_content.squares_settings);
                        }
                    }

                    html += '</div>';
                }

                self.wrap.prepend(html);
            }
        },
        initFullscreen: function () {
            var self = this;

            if (parseInt(self.settings.fullscreen.enable_fullscreen_mode, 10) == 1) {
                // Button style
                var style = '';
                style += 'background: ' + self.settings.fullscreen.fullscreen_button_color + '; ';
                style += 'color: ' + self.settings.fullscreen.fullscreen_button_text_color + '; ';

                // Button content
                var icon = '<i class="fa fa-arrows-alt" aria-hidden="true"></i>';
                if (parseInt(self.settings.runtime.is_fullscreen, 10) == 1) {
                    icon = '<i class="fa fa-times" aria-hidden="true"></i>';
                }

                var text = '<!--Go Fullscreen-->';
                if (parseInt(self.settings.runtime.is_fullscreen, 10) == 1) {
                    text = '<!--Close Fullscreen-->';
                }

                var buttonContent = '';
                if (self.settings.fullscreen.fullscreen_button_type == 'icon') {
                    buttonContent += icon;
                }
                if (self.settings.fullscreen.fullscreen_button_type == 'text') {
                    buttonContent += text;
                }
                if (self.settings.fullscreen.fullscreen_button_type == 'icon_and_text') {
                    buttonContent += icon + ' ' + text;
                }

                // Button classes
                var classes = '';
                if (self.settings.fullscreen.fullscreen_button_type == 'icon') {
                    classes += 'imp-fullscreen-button-icon-only';
                }

                // Button html
                var html = '';
                html += '<div style="' + style + '" class="' + classes + ' imp-fullscreen-button imp-fullscreen-button-position-' + self.settings.fullscreen.fullscreen_button_position + '">';
                html += buttonContent;
                html += '</div>';

                // Append
                self.wrap.append('<div class="imp-ui"></div>');
                self.ui = self.wrap.find('.imp-ui');
                self.ui.append(html);

                // Correct the button's position
                var btn = self.ui.find('.imp-fullscreen-button');

                if (parseInt(self.settings.fullscreen.fullscreen_button_position, 10) == 1 || parseInt(self.settings.fullscreen.fullscreen_button_position, 10) == 4) {
                    btn.css({ "margin-left": - btn.outerWidth() / 2 });
                }

                // Start in fullscreen mode
                if (parseInt(self.settings.fullscreen.start_in_fullscreen_mode, 10) == 1 && self.settings.runtime.is_fullscreen == 0) {
                    self.toggleFullscreen();
                }
            }
        },
        measureTooltipSize: function (i) {
            // Size needs to be calculated just before
            // the tooltip displays, and for the specific tooltip only.
            // No calculations needed if in fullscreen mode

            // 1. Does size need to be calculated?
            if (this.settings.tooltips.fullscreen_tooltips == 'always' || (this.settings.tooltips.fullscreen_tooltips == 'mobile' && isMobile())) return;

            var s = this.settings.spots[i];
            var t = this.wrap.find('.imp-tooltip[data-index="' + i + '"]');

            // 2. If the tooltip has manual width, set it
            if (parseInt(s.tooltip_style.auto_width, 10) == 0) {
                t.css({
                    width: s.tooltip_style.width
                });
            }

            // 3. Measure width/height
            t.data('imp-measured-width', t.outerWidth());
            t.data('imp-measured-height', t.outerHeight());
        },
        animateShapesLoop: function () {
            if (this.settings.general.pageload_animation == 'none') return;

            var interval = 750 / this.settings.spots.length;
            var shapesRandomOrderArray = shuffle(this.settings.spots.slice());

            for (var i = 0; i < shapesRandomOrderArray.length; i++) {
                this.animateShape(shapesRandomOrderArray[i], interval * i);
            }
        },
        animateShape: function (shape, delay) {
            var self = this;
            var spotEl = $('#' + shape.id);

            setTimeout(function () {
                if (self.settings.general.pageload_animation == 'fade') {
                    spotEl.css({
                        opacity: shape.default_style.opacity
                    });
                }
                if (self.settings.general.pageload_animation == 'grow') {
                    spotEl.css({
                        transform: 'scale(1, 1)',
                        '-moz-transform': 'scale(1, 1)',
                        '-webkit-transform': 'scale(1, 1)'
                    });
                }
            }, delay);
        },
        events: function () {
            // to do - complete rewrite
            var self = this;

            // Mouse events
            this.wrap.off('mousemove');
            this.wrap.on('mousemove', function (e) {
                if (self.touch) return;
                self.handleEventMove(e);
            });
            this.wrap.off('mouseup');
            this.wrap.on('mouseup', function (e) {
                if (self.touch) return;
                self.handleEventEnd(e);
            });

            // Touch events
            this.wrap.off('touchstart');
            this.wrap.on('touchstart', function (e) {
                self.touch = true;
                self.handleEventMove(e);
            });
            this.wrap.off('touchmove');
            this.wrap.on('touchmove', function (e) {
                self.handleEventMove(e);
            });
            this.wrap.off('touchend');
            this.wrap.on('touchend', function (e) {
                self.handleEventEnd(e);
            });

            // Hide tooltips when mouse leaves the image map container
            $(document).off('mousemove.' + this.settings.id);
            $(document).on('mousemove.' + this.settings.id, function (e) {
                if (self.touch) return;

                // Is the event outsite the current image map container?
                if (($(e.target).closest('.imp-wrap').length == 0 || $(e.target).closest('[data-image-map-pro-id="' + self.settings.id + '"]').length == 0) && $(e.target).closest('.imp-fullscreen-tooltips-container').length == 0) {
                    // Is the tooltip open method set to "mouseover"?
                    if (self.settings.tooltips.show_tooltips == 'mouseover') {
                        // If the event is not inside an HTML API tooltip mouseover element, then hide all tooltips
                        if ($(e.target).closest('[data-imp-open-tooltip-on-mouseover]').length == 0 && $(e.target).closest('[data-imp-trigger-shape-on-mouseover]').length == 0) {
                            self.hideAllTooltips();
                        }
                    }

                    // If the event is not inside an HTML API shape highlight mouseover element, then unhighlight all shapes
                    if ($(e.target).closest('[data-imp-highlight-shape-on-mouseover]').length == 0 && $(e.target).closest('[data-imp-trigger-shape-on-mouseover]').length == 0) {
                        self.unhighlightAllShapes();
                    }
                }
            });
            $(document).off('touchstart.' + this.settings.id);
            $(document).on('touchstart.' + this.settings.id, function (e) {
                // Is the event outsite the current image map container?
                if (($(e.target).closest('.imp-wrap').length == 0 || $(e.target).closest('[data-image-map-pro-id="' + self.settings.id + '"]').length == 0) && $(e.target).closest('.imp-fullscreen-tooltips-container').length == 0) {

                    // Is the tooltip open method set to "mouseover"?
                    if (self.settings.tooltips.show_tooltips == 'mouseover') {
                        // If the event is not inside an HTML API tooltip mouseover element, then hide all tooltips
                        if ($(e.target).closest('[data-imp-open-tooltip-on-mouseover]').length == 0 && $(e.target).closest('[data-imp-trigger-shape-on-mouseover]').length == 0) {
                            self.hideAllTooltips();
                        }
                    }

                    // If the event is not inside an HTML API shape highlight mouseover element, then unhighlight all shapes
                    if ($(e.target).closest('[data-imp-highlight-shape-on-mouseover]').length == 0 && $(e.target).closest('[data-imp-trigger-shape-on-mouseover]').length == 0) {
                        self.unhighlightAllShapes();
                    }
                }
            });
            $(document).off('mouseup.' + this.settings.id);
            $(document).on('mouseup.' + this.settings.id, function (e) {
                if (self.touch) return;

                // Is the event outsite the current image map container?
                if (($(e.target).closest('.imp-wrap').length == 0 || $(e.target).closest('[data-image-map-pro-id="' + self.settings.id + '"]').length == 0) && $(e.target).closest('.imp-fullscreen-tooltips-container').length == 0) {
                    // Is the tooltip open method set to "click"?
                    if (self.settings.tooltips.show_tooltips == 'click') {
                        self.hideAllTooltips();
                    }
                    self.unhighlightAllShapes();
                }
            });
            $(document).off('touchend.' + this.settings.id);
            $(document).on('touchend.' + this.settings.id, function (e) {
                // Is the event outsite the current image map container?
                if (($(e.target).closest('.imp-wrap').length == 0 || $(e.target).closest('[data-image-map-pro-id="' + self.settings.id + '"]').length == 0) && $(e.target).closest('.imp-fullscreen-tooltips-container').length == 0) {
                    // Is the tooltip open method set to "click"?
                    if (self.settings.tooltips.show_tooltips == 'click') {
                        self.hideAllTooltips();
                    }
                    self.unhighlightAllShapes();
                }
            });

            // Tooltips close button
            $(document).off('click.' + this.settings.id, '.imp-tooltip-close-button');
            $(document).on('click.' + this.settings.id, '.imp-tooltip-close-button', function () {
                self.hideAllTooltips();
            });

            // Fullscreen button
            $(document).off('click.' + this.settings.id, '[data-image-map-pro-id="' + this.settings.id + '"] .imp-fullscreen-button');
            $(document).on('click.' + this.settings.id, '[data-image-map-pro-id="' + this.settings.id + '"] .imp-fullscreen-button', function () {
                self.toggleFullscreen();
            });
        },
        APIEvents: function () {
            /*

            HTML API
            ---------------------------------------
            data-imp-highlight-shape-on-mouseover
            data-imp-highlight-shape-on-click
            data-imp-unhighlight-shape-on-mouseover
            data-imp-unhighlight-shape-on-click

            data-imp-open-tooltip-on-mouseover
            data-imp-open-tooltip-on-click
            data-imp-close-tooltip-on-mouseover
            data-imp-close-tooltip-on-click

            data-imp-trigger-shape-on-mouseover
            data-imp-trigger-shape-on-click
            data-imp-untrigger-shape-on-mouseover
            data-imp-untrigger-shape-on-click

            */

            // HTML API - SHAPE

            var self = this;

            // [data-imp-highlight-shape-on-mouseover]
            $(document).on('mouseover', '[data-imp-highlight-shape-on-mouseover]', function () {
                var shapeName = $(this).data('imp-highlight-shape-on-mouseover');
                var mapName = $(this).data('imp-image-map-name');
                if (!mapName) mapName = self.settings.general.name;

                if (mapName == self.settings.general.name) {
                    var i = $('[data-shape-title="' + shapeName + '"]').data('index');

                    self.highlightShape(i, true);
                }
            });
            $(document).on('mouseout', '[data-imp-highlight-shape-on-mouseover]', function () {
                var shapeName = $(this).data('imp-highlight-shape-on-mouseover');
                var mapName = $(this).data('imp-image-map-name');
                if (!mapName) mapName = self.settings.general.name;

                if (mapName == self.settings.general.name) {
                    self.unhighlightAllShapes();
                }
            });

            // [data-imp-highlight-shape-on-click]
            $(document).on('click', '[data-imp-highlight-shape-on-click]', function () {
                var shapeName = $(this).data('imp-highlight-shape-on-click');
                var mapName = $(this).data('imp-image-map-name');
                if (!mapName) mapName = self.settings.general.name;

                if (mapName == self.settings.general.name) {
                    var i = $('[data-shape-title="' + shapeName + '"]').data('index');
                    var s = self.settings.spots[i];

                    self.highlightShape(i, true);

                    // Add shape to the list of highlighted shapes by the API
                    if (instances[mapName].apiHighlightedShapes.indexOf(i) == -1) {
                        instances[mapName].apiHighlightedShapes.push(i);
                    }

                    // If the shape is a master, then add its slaves too
                    if (instances[mapName].connectedShapes[s.id]) {
                        for (var j = 0; j < instances[mapName].connectedShapes[s.id].length; j++) {
                            var index = instances[mapName].connectedShapes[s.id][j].index;
                            if (instances[mapName].apiHighlightedShapes.indexOf(index) == -1) {
                                instances[mapName].apiHighlightedShapes.push(index);
                            }
                        }
                    }
                }
            });

            // [data-imp-unhighlight-shape-on-mouseover]
            $(document).on('mouseover', '[data-imp-unhighlight-shape-on-mouseover]', function () {
                var shapeName = $(this).data('imp-unhighlight-shape-on-mouseover');
                var mapName = $(this).data('imp-image-map-name');
                if (!mapName) mapName = self.settings.general.name;

                if (mapName == self.settings.general.name) {
                    var i = $('[data-shape-title="' + shapeName + '"]').data('index');
                    var s = self.settings.spots[i];

                    // Remove the shape from the list of highlighted shapes by the API
                    if (instances[mapName].apiHighlightedShapes.indexOf(i) != -1) {
                        var arrayIndex = instances[mapName].apiHighlightedShapes.indexOf(i);
                        instances[mapName].apiHighlightedShapes.splice(arrayIndex, 1);
                    }

                    // If the shape is a master, then remove its slaves too, and unhighlight them
                    if (instances[mapName].connectedShapes[s.id]) {
                        for (var j = 0; j < instances[mapName].connectedShapes[s.id].length; j++) {
                            var index = instances[mapName].connectedShapes[s.id][j].index;
                            var index2 = instances[mapName].apiHighlightedShapes.indexOf(index);
                            instances[mapName].apiHighlightedShapes.splice(index2, 1);
                            instances[mapName].unhighlightShape(index);
                        }
                    }

                    self.unhighlightShape(i);
                }
            });

            // [data-imp-unhighlight-shape-on-click]
            $(document).on('click', '[data-imp-unhighlight-shape-on-click]', function () {
                var shapeName = $(this).data('imp-unhighlight-shape-on-click');
                var mapName = $(this).data('imp-image-map-name');
                if (!mapName) mapName = self.settings.general.name;

                if (mapName == self.settings.general.name) {
                    var i = $('[data-shape-title="' + shapeName + '"]').data('index');
                    var s = self.settings.spots[i];

                    // Remove the shape from the list of highlighted shapes by the API
                    if (instances[mapName].apiHighlightedShapes.indexOf(i) != -1) {
                        var arrayIndex = instances[mapName].apiHighlightedShapes.indexOf(i);
                        instances[mapName].apiHighlightedShapes.splice(arrayIndex, 1);
                    }

                    // If the shape is a master, then remove its slaves too, and unhighlight them
                    if (instances[mapName].connectedShapes[s.id]) {
                        for (var j = 0; j < instances[mapName].connectedShapes[s.id].length; j++) {
                            var index = instances[mapName].connectedShapes[s.id][j].index;
                            var index2 = instances[mapName].apiHighlightedShapes.indexOf(index);
                            instances[mapName].apiHighlightedShapes.splice(index2, 1);
                            instances[mapName].unhighlightShape(index);
                        }
                    }

                    self.unhighlightShape(i);
                }
            });

            // HTML API - TOOLTIP

            // [data-imp-open-tooltip-on-mouseover]
            $(document).on('mouseover', '[data-imp-open-tooltip-on-mouseover]', function () {
                var shapeName = $(this).data('imp-open-tooltip-on-mouseover');
                var mapName = $(this).data('imp-image-map-name');
                if (!mapName) mapName = self.settings.general.name;

                if (mapName == self.settings.general.name) {
                    var i = $('[data-shape-title="' + shapeName + '"]').data('index');

                    self.showTooltip(i);
                    self.updateTooltipPosition(i);
                }
            });
            $(document).on('mouseout', '[data-imp-open-tooltip-on-mouseover]', function () {
                var shapeName = $(this).data('imp-open-tooltip-on-mouseover');
                var mapName = $(this).data('imp-image-map-name');
                if (!mapName) mapName = self.settings.general.name;

                if (mapName == self.settings.general.name) {
                    self.hideAllTooltips();
                }
            });

            // [data-imp-open-tooltip-on-click]
            $(document).on('click', '[data-imp-open-tooltip-on-click]', function () {
                var shapeName = $(this).data('imp-open-tooltip-on-click');
                var mapName = $(this).data('imp-image-map-name');
                if (!mapName) mapName = self.settings.general.name;

                if (mapName == self.settings.general.name) {
                    var i = $('[data-shape-title="' + shapeName + '"]').data('index');
                    self.showTooltip(i);
                    self.updateTooltipPosition(i);

                    // Add the tooltip to the list of tooltips opened with the API
                    if (instances[mapName].apiOpenedTooltips.indexOf(i) == -1) {
                        instances[mapName].apiOpenedTooltips.push(i);
                    }
                }
            });

            // [data-imp-close-tooltip-on-mouseover]
            $(document).on('mouseover', '[data-imp-close-tooltip-on-mouseover]', function () {
                var shapeName = $(this).data('imp-close-tooltip-on-mouseover');
                var mapName = $(this).data('imp-image-map-name');
                if (!mapName) mapName = self.settings.general.name;

                if (mapName == self.settings.general.name) {
                    var i = $('[data-shape-title="' + shapeName + '"]').data('index');
                    // Remove the tooltip to the list of tooltips opened with the API
                    if (instances[mapName].apiOpenedTooltips.indexOf(i) != -1) {
                        var arrayIndex = instances[mapName].apiOpenedTooltips.indexOf(i);
                        instances[mapName].apiOpenedTooltips.splice(arrayIndex, 1);
                    }

                    self.hideTooltip(i);
                }
            });

            // [data-imp-close-tooltip-on-click]
            $(document).on('click', '[data-imp-close-tooltip-on-click]', function () {
                var shapeName = $(this).data('imp-close-tooltip-on-click');
                var mapName = $(this).data('imp-image-map-name');
                if (!mapName) mapName = self.settings.general.name;

                if (mapName == self.settings.general.name) {
                    var i = $('[data-shape-title="' + shapeName + '"]').data('index');
                    // Remove the tooltip to the list of tooltips opened with the API
                    if (instances[mapName].apiOpenedTooltips.indexOf(i) != -1) {
                        var arrayIndex = instances[mapName].apiOpenedTooltips.indexOf(i);
                        instances[mapName].apiOpenedTooltips.splice(arrayIndex, 1);
                    }

                    self.hideTooltip(i);
                }
            });

            // HTML API - TRIGGER

            // [data-imp-trigger-shape-on-mouseover]
            $(document).on('mouseover', '[data-imp-trigger-shape-on-mouseover]', function () {
                var shapeName = $(this).data('imp-trigger-shape-on-mouseover');
                var mapName = $(this).data('imp-image-map-name');
                if (!mapName) mapName = self.settings.general.name;

                if (mapName == self.settings.general.name) {
                    var i = $('[data-shape-title="' + shapeName + '"]').data('index');
                    self.highlightShape(i, true);
                    self.showTooltip(i);
                    self.updateTooltipPosition(i);
                }
            });
            $(document).on('mouseout', '[data-imp-trigger-shape-on-mouseover]', function () {
                var shapeName = $(this).data('imp-trigger-shape-on-mouseover');
                var mapName = $(this).data('imp-image-map-name');
                if (!mapName) mapName = self.settings.general.name;

                if (mapName == self.settings.general.name) {
                    self.unhighlightAllShapes();
                    self.hideAllTooltips();
                }
            });

            // [data-imp-trigger-shape-on-click]
            $(document).on('click', '[data-imp-trigger-shape-on-click]', function () {
                var shapeName = $(this).data('imp-trigger-shape-on-click');
                var mapName = $(this).data('imp-image-map-name');
                if (!mapName) mapName = self.settings.general.name;

                if (mapName == self.settings.general.name) {
                    var i = $('[data-shape-title="' + shapeName + '"]').data('index');
                    var s = self.settings.spots[i];

                    self.highlightShape(i, true);
                    self.showTooltip(i);
                    self.updateTooltipPosition(i);

                    // Add the tooltip to the list of tooltips opened with the API
                    if (instances[mapName].apiOpenedTooltips.indexOf(i) == -1) {
                        instances[mapName].apiOpenedTooltips.push(i);
                    }

                    // Add shape to the list of highlighted shapes by the API
                    if (instances[mapName].apiHighlightedShapes.indexOf(i) == -1) {
                        instances[mapName].apiHighlightedShapes.push(i);
                    }

                    // If the shape is a master, then add its slaves too
                    if (instances[mapName].connectedShapes[s.id]) {
                        for (var j = 0; j < instances[mapName].connectedShapes[s.id].length; j++) {
                            var index = instances[mapName].connectedShapes[s.id][j].index;
                            if (instances[mapName].apiHighlightedShapes.indexOf(index) == -1) {
                                instances[mapName].apiHighlightedShapes.push(index);
                            }
                        }
                    }
                }
            });

            // [data-imp-untrigger-shape-on-mouseover]
            $(document).on('mouseover', '[data-imp-untrigger-shape-on-mouseover]', function () {
                var shapeName = $(this).data('imp-untrigger-shape-on-mouseover');
                var mapName = $(this).data('imp-image-map-name');
                if (!mapName) mapName = self.settings.general.name;

                if (mapName == self.settings.general.name) {
                    var i = $('[data-shape-title="' + shapeName + '"]').data('index');
                    var s = self.settings.spots[i];

                    // Remove the shape from the list of highlighted shapes by the API
                    if (instances[mapName].apiHighlightedShapes.indexOf(i) != -1) {
                        var arrayIndex = instances[mapName].apiHighlightedShapes.indexOf(i);
                        instances[mapName].apiHighlightedShapes.splice(arrayIndex, 1);
                    }

                    // If the shape is a master, then remove its slaves too, and unhighlight them
                    if (instances[mapName].connectedShapes[s.id]) {
                        for (var j = 0; j < instances[mapName].connectedShapes[s.id].length; j++) {
                            var index = instances[mapName].connectedShapes[s.id][j].index;
                            var index2 = instances[mapName].apiHighlightedShapes.indexOf(index);
                            instances[mapName].apiHighlightedShapes.splice(index2, 1);
                            instances[mapName].unhighlightShape(index);
                        }
                    }

                    self.unhighlightShape(i);

                    // Remove the tooltip to the list of tooltips opened with the API
                    if (instances[mapName].apiOpenedTooltips.indexOf(i) != -1) {
                        var arrayIndex = instances[mapName].apiOpenedTooltips.indexOf(i);
                        instances[mapName].apiOpenedTooltips.splice(arrayIndex, 1);
                    }

                    self.hideTooltip(i);
                }
            });

            // [data-imp-untrigger-shape-on-click]
            $(document).on('click', '[data-imp-untrigger-shape-on-click]', function () {
                var shapeName = $(this).data('imp-untrigger-shape-on-click');
                var mapName = $(this).data('imp-image-map-name');
                if (!mapName) mapName = self.settings.general.name;

                if (mapName == self.settings.general.name) {
                    var i = $('[data-shape-title="' + shapeName + '"]').data('index');
                    var s = self.settings.spots[i];

                    // Remove the shape from the list of highlighted shapes by the API
                    if (instances[mapName].apiHighlightedShapes.indexOf(i) != -1) {
                        var arrayIndex = instances[mapName].apiHighlightedShapes.indexOf(i);
                        instances[mapName].apiHighlightedShapes.splice(arrayIndex, 1);
                    }

                    // If the shape is a master, then remove its slaves too, and unhighlight them
                    if (instances[mapName].connectedShapes[s.id]) {
                        for (var j = 0; j < instances[mapName].connectedShapes[s.id].length; j++) {
                            var index = instances[mapName].connectedShapes[s.id][j].index;
                            var index2 = instances[mapName].apiHighlightedShapes.indexOf(index);
                            instances[mapName].apiHighlightedShapes.splice(index2, 1);
                            instances[mapName].unhighlightShape(index);
                        }
                    }

                    self.unhighlightShape(i);

                    // Remove the tooltip to the list of tooltips opened with the API
                    if (instances[mapName].apiOpenedTooltips.indexOf(i) != -1) {
                        var arrayIndex = instances[mapName].apiOpenedTooltips.indexOf(i);
                        instances[mapName].apiOpenedTooltips.splice(arrayIndex, 1);
                    }

                    self.hideTooltip(i);
                }
            });
        },
        disableEvents: function () {
            this.wrap.off('mousemove');
            this.wrap.off('mouseup');

            // Touch events
            this.wrap.off('touchstart');
            this.wrap.off('touchmove');
            this.wrap.off('touchend');

            // Hide tooltips when mouse leaves the image map container
            $(document).off('mousemove.' + this.settings.id);
            $(document).off('touchstart.' + this.settings.id);

            // Tooltips close button
            $(document).off('click.' + this.settings.id, '.imp-tooltip-close-button');

            // Fullscreen button
            $(document).off('click.' + this.settings.id, '.imp-fullscreen-button');
        },
        handleEventMove: function (e) {
            // If there is a visible fullscreen tooltip, return
            if (this.fullscreenTooltipVisible) return;

            // If the mouse is over a tooltip AND sticky tooltips are OFF, return
            // if (($(e.target).closest('.imp-tooltip').length != 0 || $(e.target).hasClass('imp-tooltip')) && parseInt(this.settings.tooltips.sticky_tooltips, 10) == 0) return;

            // Get event data
            var c = this.getEventRelativeCoordinates(e);
            var i = this.matchShapeToCoords(c);

            // If there is a tooltip under the event, and sticky tooltips are turned off, then return
            if (this.isPointInsideVisibleTooltip(c) && parseInt(this.settings.tooltips.sticky_tooltips, 10) == 0) {
                return;
            }

            // There is a shape under the event
            if (i != -1) {
                // If the shape is not highlighted,
                // then highlight it, hide any visible tooltip and unhighlight all other shapes
                if (!this.isShapeHighlighted(i)) {
                    this.unhighlightAllShapes();
                    if (this.settings.tooltips.show_tooltips == 'mouseover') {
                        this.hideAllTooltips();
                    }

                    this.highlightShape(i, true);
                }

                // If tooltips are set to show on mouseover, show the tooltip for the shape under the event
                if (this.settings.tooltips.show_tooltips == 'mouseover' && parseInt(this.settings.tooltips.enable_tooltips, 10) == 1 && parseInt(this.settings.spots[i].tooltip_style.enable_tooltip, 10) == 1) {
                    this.showTooltip(i);
                }

                // If there is a visible tooltip and sticky tooltips is on, then update the position of the last opened tooltip
                if (this.openedTooltips.length > 0 && parseInt(this.settings.tooltips.sticky_tooltips, 10) == 1) {
                    // Tooltips must be set to show on mouseover
                    if (this.settings.tooltips.show_tooltips == 'mouseover') {
                        this.updateTooltipPosition(this.openedTooltips[this.openedTooltips.length - 1], e);
                    }
                }
            }

            // There is no shape under the event
            if (i == -1) {
                // Unhighlight all shapes and hide any visible tooltip
                this.unhighlightAllShapes();
                if (this.settings.tooltips.show_tooltips == 'mouseover') {
                    this.hideAllTooltips();
                }
            }
        },
        handleEventEnd: function (e) {
            // Did the user click on a tooltip?
            if ($(e.target).closest('.imp-tooltip').length != 0) {
                return;
            }

            // If there is a visible fullscreen tooltip, return
            if (this.fullscreenTooltipVisible) return;

            // Get event data
            var c = this.getEventRelativeCoordinates(e);
            var i = this.matchShapeToCoords(c);

            // There is a shape under the event
            if (i != -1) {
                // If tooltips are set to show on click, then show the tooltip for the shape
                if (this.settings.tooltips.show_tooltips == 'click' && parseInt(this.settings.tooltips.enable_tooltips, 10) == 1 && parseInt(this.settings.spots[i].tooltip_style.enable_tooltip, 10) == 1) {
                    this.showTooltip(i);
                }

                // Do click action for the shape
                this.performClickAction(i);
            }

            // There is no shape under the event
            if (i == -1) {
                // If tooltips are set to show on click, then hide the visible tooltip (if any)
                if (this.settings.tooltips.show_tooltips == 'click') {
                    this.hideAllTooltips();
                }
            }
        },

        getEventRelativeCoordinates: function (e) {
            var x, y;

            if (e.type == 'touchstart' || e.type == 'touchmove' || e.type == 'touchend' || e.type == 'touchcancel') {
                var touch = e.originalEvent.touches[0] || e.originalEvent.changedTouches[0];
                x = touch.pageX;
                y = touch.pageY;
            } else if (e.type == 'mousedown' || e.type == 'mouseup' || e.type == 'mousemove' || e.type == 'mouseover' || e.type == 'mouseout' || e.type == 'mouseenter' || e.type == 'mouseleave') {
                x = e.pageX;
                y = e.pageY;
            }

            // Make coordinates relative to the container
            x -= this.wrap.offset().left;
            y -= this.wrap.offset().top;

            // Take window scroll into account
            // x += $(window).scrollLeft();
            // y += $(window).scrollTop();

            // Convert coordinates to %
            x = (x / this.wrap.width()) * 100;
            y = (y / this.wrap.height()) * 100;

            return { x: x, y: y };
        },
        getEventCoordinates: function (e) {
            var x, y;

            if (e.type == 'touchstart' || e.type == 'touchmove' || e.type == 'touchend' || e.type == 'touchcancel') {
                var touch = e.originalEvent.touches[0] || e.originalEvent.changedTouches[0];
                x = touch.pageX;
                y = touch.pageY;
            } else if (e.type == 'mousedown' || e.type == 'mouseup' || e.type == 'mousemove' || e.type == 'mouseover' || e.type == 'mouseout' || e.type == 'mouseenter' || e.type == 'mouseleave') {
                x = e.pageX;
                y = e.pageY;
            }

            return { x: x, y: y };
        },
        matchShapeToCoords: function (c) {
            for (var i = this.settings.spots.length - 1; i >= 0; i--) {
                var s = this.settings.spots[i];

                if (s.type == 'poly') {
                    var x = (c.x / 100) * this.wrap.width();
                    var y = (c.y / 100) * this.wrap.height();

                    x = (x * this.settings.general.width) / this.wrap.width();
                    y = (y * this.settings.general.height) / this.wrap.height();

                    if (isPointInsidePolygon(x, y, s.vs)) {
                        return i;
                        break;
                    }
                }

                if (s.type == 'spot') {
                    var shapeWidth = (s.width < 44) ? 44 : s.width;
                    var shapeHeight = (s.height < 44) ? 44 : s.height;

                    var x = (c.x / 100) * this.wrap.width();
                    var y = (c.y / 100) * this.wrap.height();
                    var rx = (s.x / 100) * this.wrap.width() - shapeWidth / 2;
                    var ry = (s.y / 100) * this.wrap.height() - shapeHeight / 2;
                    var rw = shapeWidth;
                    var rh = shapeHeight;

                    if (parseInt(s.default_style.icon_is_pin, 10) == 1 && parseInt(s.default_style.use_icon, 10) == 1) {
                        ry -= shapeHeight / 2;

                        if (s.height < 44) {
                            ry += s.height / 2;
                        }
                    }

                    if (isPointInsideRect(x, y, rx, ry, rw, rh)) {
                        return i;
                        break;
                    }
                }

                if (s.type == 'rect') {
                    if (isPointInsideRect(c.x, c.y, s.x, s.y, s.width, s.height)) {
                        return i;
                        break;
                    }
                }

                if (s.type == 'oval') {
                    var x = c.x;
                    var y = c.y;
                    var ex = s.x + s.width / 2;
                    var ey = s.y + s.height / 2;
                    var rx = s.width / 2;
                    var ry = s.height / 2;

                    if (isPointInsideEllipse(x, y, ex, ey, rx, ry)) {
                        return i;
                        break;
                    }
                }
            }

            return -1;
        },
        isPointInsideVisibleTooltip: function (p) {
            for (var i = 0; i < this.openedTooltips.length; i++) {
                var t = this.wrap.find('.imp-tooltip[data-index="' + this.openedTooltips[i] + '"]');
                var index = this.openedTooltips[i];

                var buffer = this.settings.spots[index].tooltip_style.buffer;

                var tw = t.outerWidth();
                var th = t.outerHeight();
                var tx = t.offset().left - this.wrap.offset().left;
                var ty = t.offset().top - this.wrap.offset().top;

                // Convert tooltip x/y/w/h from px to %
                tx = (tx / this.wrap.width()) * 100;
                ty = (ty / this.wrap.height()) * 100;
                tw = (tw / this.wrap.width()) * 100;
                th = (th / this.wrap.height()) * 100;

                // Create a polygon, representing the buffer space
                var poly = [];

                if (this.settings.spots[index].tooltip_style.position == 'left') {
                    // Convert buffer from px to %
                    buffer = (buffer / this.wrap.width()) * 100;

                    var poly = [
                        [tx, ty],
                        [tx + tw, ty],
                        [tx + tw + buffer, ty + th - th / 3 - th / 3],
                        [tx + tw + buffer, ty + th - th / 3],
                        [tx + tw, ty + th],
                        [tx, ty + th]
                    ];
                }
                if (this.settings.spots[index].tooltip_style.position == 'right') {
                    // Convert buffer from px to %
                    buffer = (buffer / this.wrap.width()) * 100;

                    var poly = [
                        [tx, ty],
                        [tx + tw, ty],
                        [tx + tw, ty + th],
                        [tx, ty + th],
                        [tx - buffer, ty + th - th / 3],
                        [tx - buffer, ty + th - th / 3 - th / 3]
                    ];
                }
                if (this.settings.spots[index].tooltip_style.position == 'top') {
                    // Convert buffer from px to %
                    buffer = (buffer / this.wrap.height()) * 100;

                    var poly = [
                        [tx, ty],
                        [tx + tw, ty],
                        [tx + tw, ty + th],
                        [tx + tw - tw / 3, ty + th + buffer],
                        [tx + tw - tw / 3 - tw / 3, ty + th + buffer],
                        [tx, ty + th]
                    ];
                }
                if (this.settings.spots[index].tooltip_style.position == 'bottom') {
                    // Convert buffer from px to %
                    buffer = (buffer / this.wrap.height()) * 100;

                    var poly = [
                        [tx, ty],
                        [tx + tw - tw / 3 - tw / 3, ty - buffer],
                        [tx + tw - tw / 3, ty - buffer],
                        [tx + tw, ty],
                        [tx + tw, ty + th],
                        [tx, ty + th]
                    ];
                }

                if (isPointInsidePolygon(p.x, p.y, poly)) {
                    return true;
                }

                return false;
            }
        },
        getIndexOfShapeWithID: function (id) {
            for (var i = 0; i < this.settings.spots.length; i++) {
                if (this.settings.spots[i].id == id) return i;
            }
        },

        // Calculates style string for shape with index "i" and styles "styles"
        calcStyles: function (styles, i) {
            // The shape object
            var s = this.settings.spots[i];

            // The computed styles
            var style = '';

            // The shape is a Spot
            if (s.type == 'spot') {
                style += 'left: ' + s.x + '%;';
                style += 'top: ' + s.y + '%;';
                style += 'width: ' + s.width + 'px;';
                style += 'height: ' + s.height + 'px;';
                style += 'opacity: ' + styles.opacity + ';';
                var marginTop = -s.width / 2;
                var marginLeft = -s.height / 2;

                // The spot is not an icon
                if (parseInt(s.default_style.use_icon, 10) == 0) {
                    var color_bg = hexToRgb(styles.background_color) || { r: 0, b: 0, g: 0 };
                    var color_border = hexToRgb(styles.border_color) || { r: 0, b: 0, g: 0 };

                    style += 'border-radius: ' + styles.border_radius + 'px;';
                    style += 'background: rgba(' + color_bg.r + ', ' + color_bg.g + ', ' + color_bg.b + ', ' + styles.background_opacity + ');';
                    style += 'border-width: ' + styles.border_width + 'px;';
                    style += 'border-style: ' + styles.border_style + ';';
                    style += 'border-color: rgba(' + color_border.r + ', ' + color_border.g + ', ' + color_border.b + ', ' + styles.border_opacity + ');';
                }

                // The spot is an icon
                if (parseInt(s.default_style.use_icon, 10) == 1) {
                    // If the icon is a pin, center it on the bottom edge
                    if (parseInt(s.default_style.icon_is_pin, 10) == 1) {
                        marginTop = -s.height;
                    }
                }

                style += 'margin-left: ' + marginLeft + 'px;';
                style += 'margin-top: ' + marginTop + 'px;';
            }

            // The shape is a Rect or Oval
            if (s.type == 'rect' || s.type == 'oval') {
                // If the shape is an Oval, apply 50% 50% border radius
                var borderRadius = styles.border_radius + 'px';
                if (s.type == 'oval') {
                    borderRadius = '50% 50%';
                }

                var color_bg = hexToRgb(styles.background_color) || { r: 0, b: 0, g: 0 };
                var color_border = hexToRgb(styles.border_color) || { r: 0, b: 0, g: 0 };

                style += 'left: ' + s.x + '%;';
                style += 'top: ' + s.y + '%;';
                style += 'width: ' + s.width + '%;';
                style += 'height: ' + s.height + '%;';

                style += 'opacity: ' + styles.opacity + ';';
                style += 'background: rgba(' + color_bg.r + ', ' + color_bg.g + ', ' + color_bg.b + ', ' + styles.background_opacity + ');';
                style += 'border-width: ' + styles.border_width + 'px;';
                style += 'border-style: ' + styles.border_style + ';';
                style += 'border-color: rgba(' + color_border.r + ', ' + color_border.g + ', ' + color_border.b + ', ' + styles.border_opacity + ');';
                style += 'border-radius: ' + borderRadius + ';';
            }

            // The shape is a Poly
            if (s.type == 'poly') {
                var c_fill = hexToRgb(styles.fill) || { r: 0, b: 0, g: 0 };
                var c_stroke = hexToRgb(styles.stroke_color) || { r: 0, b: 0, g: 0 };

                style += 'opacity: ' + styles.opacity + ';';
                style += 'fill: rgba(' + c_fill.r + ', ' + c_fill.g + ', ' + c_fill.b + ', ' + styles.fill_opacity + ');';
                style += 'stroke: rgba(' + c_stroke.r + ', ' + c_stroke.g + ', ' + c_stroke.b + ', ' + styles.stroke_opacity + ');';
                style += 'stroke-width: ' + styles.stroke_width + 'px;';
                style += 'stroke-dasharray: ' + styles.stroke_dasharray + ';';
                style += 'stroke-linecap: ' + styles.stroke_linecap + ';';
            }

            return style;
        },
        // Applies the styles from the "styles" string variable to the shape with an index "i"
        applyStyles: function (styles, i) {
            // The shape object
            var s = this.settings.spots[i];

            // The shape HTML element
            var el = this.wrap.find('#' + s.id);

            // Get the calculated style string
            var style = this.calcStyles(styles, i);

            // Apply the styles to the HTML element
            el.attr('style', style);

            // EXCEPTION - If the shape is an SVG icon
            if (s.type == 'spot' && el.find('path').length > 0) {
                el.find('path').attr('style', 'fill:' + styles.icon_fill);
            }
        },

        highlightShape: function (i, recursive) {
            var s = this.settings.spots[i];

            // If the shape is connected to a master, start from the master and return
            if (recursive && s.connected_to != '') {
                var index = this.getIndexOfShapeWithID(s.connected_to);
                this.highlightShape(index, true);
                return;
            }

            // If the shape is a connected shape master, then highlight its slaves (if recursive is TRUE)
            if (this.connectedShapes[s.id]) {
                for (var j = 0; j < this.connectedShapes[s.id].length; j++) {
                    var index = this.connectedShapes[s.id][j].index;
                    this.highlightShape(index, false);
                }
            }

            // Apply mouseover styles
            this.applyStyles(this.settings.spots[i].mouseover_style, i);

            // Send API event
            $.imageMapProEventHighlightedShape(this.settings.general.name, s.title);

            // Add the shape to the array of highlighted shapes
            if (this.highlightedShapes.indexOf(i) == -1) {
                this.highlightedShapes.push(i);
            }
        },
        unhighlightShape: function (i) {
            var s = this.settings.spots[i];

            // If the shape is highlighted with the API, then return
            if (this.apiHighlightedShapes.indexOf(i) != -1) {
                return;
            }

            // Apply default styles
            this.applyStyles(s.default_style, i);

            // Send API event
            $.imageMapProEventUnhighlightedShape(this.settings.general.name, s.title);

            // Remove the shape from the array of highlighted shapes
            var indexInList = this.highlightedShapes.indexOf(i);
            this.highlightedShapes.splice(indexInList, 1);
        },
        unhighlightAllShapes: function () {
            var shapes = this.highlightedShapes.slice(0);

            for (var i = 0; i < shapes.length; i++) {
                this.unhighlightShape(shapes[i]);
            }
        },
        isShapeHighlighted: function (i) {
            for (var j = 0; j < this.highlightedShapes.length; j++) {
                if (this.highlightedShapes[j] == i) {
                    return true;
                }
            }

            return false;
        },

        performClickAction: function (i) {
            var s = this.settings.spots[i];

            if (s.actions.click == 'follow-link') {
                if ($('#imp-temp-link').length == 0) {
                    $('body').append('<a href="" id="imp-temp-link" target="_blank"></a>');
                }
                $('#imp-temp-link').attr('href', s.actions.link);

                if (parseInt(s.actions.open_link_in_new_window, 10) == 1) {
                    $('#imp-temp-link').attr('target', '_blank');
                } else {
                    $('#imp-temp-link').removeAttr('target');
                }

                $('#imp-temp-link')[0].click();
            }
            if (s.actions.click == 'run-script') {
                eval(s.actions.script);
            }

            $.imageMapProEventClickedShape(this.settings.general.name, this.settings.spots[i].id);
        },

        showTooltip: function (i, e) {
            // If the tooltip's shape is connected to a master and it's using its tooltip, show that tooltip instead
            var s = this.settings.spots[i];
            if (s.connected_to != '' && parseInt(s.use_connected_shape_tooltip, 10) == 1) {
                var masterShapeIndex = this.getIndexOfShapeWithID(s.connected_to);
                this.showTooltip(masterShapeIndex);
                return;
            }

            // If the tooltip is already visible, then return
            if (this.openedTooltips.indexOf(i) != -1) return;

            // If there is a visible tooltip, then hide it
            if (this.openedTooltips.length > 0) {
                this.hideAllTooltips();
            }

            // Add tooltip to the list of opened tooltips
            if (this.openedTooltips.indexOf(i) == -1) {
                this.openedTooltips.push(i);
            }

            // Show fullscreen or normal tooltips
            if ((this.settings.tooltips.fullscreen_tooltips == 'mobile-only' && isMobile()) || this.settings.tooltips.fullscreen_tooltips == 'always') {
                // Fullscreen tooltips
                this.visibleFullscreenTooltip = $('.imp-fullscreen-tooltip[data-index="' + i + '"]');
                this.visibleFullscreenTooltipIndex = i;
                this.fullscreenTooltipsContainer.show();

                var self = this;
                setTimeout(function () {
                    self.visibleFullscreenTooltip.addClass('imp-tooltip-visible');
                }, 20);

                this.fullscreenTooltipVisible = true;

                // Prevent scrolling of the body and store the original overflow attribute value
                this.bodyOverflow = $('body').css('overflow');
                $('body').css({
                    overflow: 'hidden'
                });
            } else {
                // Normal tooltips
                this.wrap.find('.imp-tooltip[data-index="' + i + '"]').addClass('imp-tooltip-visible');

                this.measureTooltipSize(i);
                this.updateTooltipPosition(i, e);
            }

            // Send event
            $.imageMapProEventOpenedTooltip(this.settings.general.name, this.settings.spots[i].title);
        },
        hideTooltip: function (i) {
            // If the tooltip has been opened with the API, then return
            if (this.apiOpenedTooltips.indexOf(i) != -1) {
                return;
            }

            // Remove from the list of opened tooltips
            var indexInList = this.openedTooltips.indexOf(i);
            this.openedTooltips.splice(indexInList, 1);

            // Hide mobile tooltip
            if ((this.settings.tooltips.fullscreen_tooltips == 'mobile-only' && isMobile()) || this.settings.tooltips.fullscreen_tooltips == 'always') {
                var self = this;

                setTimeout(function () {
                    self.fullscreenTooltipsContainer.hide();
                }, 200);

                this.fullscreenTooltipVisible = false;
                this.fullscreenTooltipsContainer.find('.imp-fullscreen-tooltip[data-index="' + i + '"]').removeClass('imp-tooltip-visible');

                // Restore the body overflow to allow scrolling
                $('body').css({
                    overflow: this.bodyOverflow
                });
            } else {
                // Hide normal tooltip
                this.wrap.find('.imp-tooltip[data-index="' + i + '"]').removeClass('imp-tooltip-visible');
            }

            // Send event
            $.imageMapProEventClosedTooltip(this.settings.general.name, this.settings.spots[i].title);
        },
        hideAllTooltips: function () {
            var tooltips = this.openedTooltips.slice(0);

            for (var i = 0; i < tooltips.length; i++) {
                this.hideTooltip(tooltips[i]);
            }
        },
        updateTooltipPosition: function (i, e) {
            // t = tooltip element
            // tw/th = tooltip width/height
            // sx/sy/sw/sh = spot x/y/width/height
            // p = padding
            // ex/ey = event x/y
            // s = target shape

            // If fullscreen tooltips are on, then do nothing
            if (this.fullscreenTooltipVisible) return;

            var t, tw, th, sx, sy, sw, sh, p = 20, ex, ey, s;

            t = this.wrap.find('.imp-tooltip[data-index="' + i + '"]');
            tw = t.data('imp-measured-width');
            th = t.data('imp-measured-height');
            s = this.settings.spots[i];
            if (parseInt(this.settings.tooltips.sticky_tooltips, 10) == 1 && e) {
                // Sticky tooltips
                // Set width/height of the spot to 0
                // and X and Y to the mouse coordinates
                // Get the event coordinates
                var c = this.getEventCoordinates(e);
                ex = c.x;
                ey = c.y;

                sx = ex - this.wrap.offset().left;
                sy = ey - this.wrap.offset().top;
                sw = 0;
                sh = 0;
            } else {
                // Calculate the position and dimentions of the spot
                if (s.type == 'spot') {
                    sw = s.width;
                    sh = s.height;
                    sx = ((Math.round(s.x * 10) / 10) / 100) * this.wrap.width() - sw / 2;
                    sy = ((Math.round(s.y * 10) / 10) / 100) * this.wrap.height() - sh / 2;
                } else {
                    sw = (s.width / 100) * this.wrap.width();
                    sh = (s.height / 100) * this.wrap.height();
                    sx = ((Math.round(s.x * 10) / 10) / 100) * this.wrap.width();
                    sy = ((Math.round(s.y * 10) / 10) / 100) * this.wrap.height();
                }
            }

            // Calculate and set the position
            var x, y;
            if (s.tooltip_style.position == 'left') {
                x = sx - tw - p;
                y = sy + sh / 2 - th / 2;
            }
            if (s.tooltip_style.position == 'right') {
                x = sx + sw + p;
                y = sy + sh / 2 - th / 2;
            }
            if (s.tooltip_style.position == 'top') {
                x = sx + sw / 2 - tw / 2
                y = sy - th - p;
            }
            if (s.tooltip_style.position == 'bottom') {
                x = sx + sw / 2 - tw / 2;
                y = sy + sh + p;
            }

            // If the spot is a pin, offset it to the top
            if (s.type == 'spot' && parseInt(s.default_style.icon_is_pin, 10) == 1 && s.type == 'spot' && parseInt(s.default_style.use_icon, 10) == 1) {
                y -= sh / 2;
            }

            var pos = { x: x, y: y };
            if (parseInt(this.settings.tooltips.constrain_tooltips, 10) == 1) {
                var wrapOffsetLeft = this.wrap.offset().left - $(window).scrollLeft();
                var wrapOffsetTop = this.wrap.offset().top - $(window).scrollTop();

                pos = fitRectToScreen(x + wrapOffsetLeft, y + wrapOffsetTop, tw, th);
                pos.x -= wrapOffsetLeft;
                pos.y -= wrapOffsetTop;
            }

            t.css({
                left: pos.x,
                top: pos.y
            });
        },

        toggleFullscreen: function () {
            if (parseInt(this.settings.runtime.is_fullscreen, 10) == 0) {
                // Go fullscreen
                $('body').addClass('imp-fullscreen-mode');

                var fullscreenSettings = $.extend(true, {}, this.settings);
                fullscreenSettings.runtime.is_fullscreen = 1;
                fullscreenSettings.id = '999999';
                fullscreenSettings.general.responsive = 0;

                var style = '';
                style += 'background: ' + this.settings.fullscreen.fullscreen_background;
                $('body').append('<div id="imp-fullscreen-wrap" style="' + style + '"><div id="image-map-pro-' + fullscreenSettings.id + '"></div></div>');

                $('#image-map-pro-' + fullscreenSettings.id).imageMapPro(fullscreenSettings);

                // Disable current image map
                this.disableEvents();

                fullscreenMap = this;
            } else {
                // Close fullscreen
                $('body').removeClass('imp-fullscreen-mode');
                $('#imp-fullscreen-wrap').remove();
                this.disableEvents();

                fullscreenMap.events();
            }
        },
    });

    // A really lightweight plugin wrapper around the constructor,
    // preventing against multiple instantiations
    $.fn[pluginName] = function (options) {
        return this.each(function () {
            $.data(this, "plugin_" + pluginName, new Plugin(this, options));
        });
    };

    function hexToRgb(hex) {
        var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
        return result ? {
            r: parseInt(result[1], 16),
            g: parseInt(result[2], 16),
            b: parseInt(result[3], 16)
        } : null;
    }
    function isPointInsideRect(x, y, rx, ry, rw, rh) {
        if (x >= rx && x <= rx + rw && y >= ry && y <= ry + rh) return true;
        return false;
    }
    function isPointInsidePolygon(x, y, vs) {
        // ray-casting algorithm based on
        // http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html

        var inside = false;
        for (var i = 0, j = vs.length - 1; i < vs.length; j = i++) {
            var xi = vs[i][0], yi = vs[i][1];
            var xj = vs[j][0], yj = vs[j][1];

            var intersect = ((yi > y) != (yj > y))
                && (x < (xj - xi) * (y - yi) / (yj - yi) + xi);
            if (intersect) inside = !inside;
        }

        return inside;
    }
    function isPointInsideEllipse(x, y, ex, ey, rx, ry) {
        var a = (x - ex) * (x - ex);
        var b = rx * rx;
        var c = (y - ey) * (y - ey);
        var d = ry * ry;

        if (a / b + c / d <= 1) return true;

        return false;
    }
    function fitRectToScreen(x, y, w, h) {
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > $(document).width() - w) x = $(document).width() - w;
        if (y > $(document).height() - h) y = $(document).height() - h;

        return { x: x, y: y };
    }
    function shuffle(array) {
        var currentIndex = array.length, temporaryValue, randomIndex;

        // While there remain elements to shuffle...
        while (0 !== currentIndex) {

            // Pick a remaining element...
            randomIndex = Math.floor(Math.random() * currentIndex);
            currentIndex -= 1;

            // And swap it with the current element.
            temporaryValue = array[currentIndex];
            array[currentIndex] = array[randomIndex];
            array[randomIndex] = temporaryValue;
        }

        return array;
    }
    function isMobile() {
        if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
            return true;
        }

        return false;
    }

})(jQuery, window, document);
