;(function ( $, window, document, undefined) {
    // Register Forms
    $.wcpEditorCreateForm({
        name: 'Image Map Settings',
        controlGroups: [
            {
                groupName: 'general',
                groupTitle: 'General',
                groupIcon: 'fa fa-cog',
                controls: [
                    {
                        type: 'text',
                        name: 'image_map_name',
                        title: 'Nombre del mapa de imagen',
                        value: $.imageMapProDefaultSettings.general.name
                    },
                    {
                        type: 'switch',
                        name: 'responsive',
                        title: 'Adaptativo',
                        value: $.imageMapProDefaultSettings.general.responsive,
                    },
                    {
                        type: 'switch',
                        name: 'preserve_quality',
                        title: 'Conservar calidad',
                        value: $.imageMapProDefaultSettings.general.preserve_quality,
                    },
                    {
                        type: 'int',
                        name: 'image_map_width',
                        title: 'Ancho',
                        value: $.imageMapProDefaultSettings.general.width,
                    },
                    {
                        type: 'int',
                        name: 'image_map_height',
                        title: 'Alto',
                        value: $.imageMapProDefaultSettings.general.height
                        },
                    {
                        type: 'button',
                        name: 'reset_size',
                        title: 'Reiniciar tamaño',
                        options: { event_name: 'button-reset-size-clicked' },
                        value: undefined
                    },
                    /*
                    {
                        type: 'select',
                        name: 'pageload_animation',
                        title: 'Page Load Animation',
                        options: [
                            { value: 'none', title: 'None' },
                            { value: 'grow', title: 'Grow' },
                            { value: 'fade', title: 'Fade' },
                        ],
                        value: $.imageMapProDefaultSettings.general.pageload_animation
                    },
                    */
                    {
                        type: 'switch',
                        name: 'center_image_map',
                        title: 'Centrar imagen',
                        value: $.imageMapProDefaultSettings.general.center_image_map,
                    },
                ]
            },

            {
                groupName: 'image',
                groupTitle: 'Imagen',
                groupIcon: 'fa fa-photo',
                controls: [
                    {
                        type: 'wp media upload',
                        name: 'image_url',
                        title: 'URL de la imagen',
                        value: $.imageMapProDefaultSettings.general.image_url
                    },
                ]
            },

            {
                groupName: 'tooltips',
                groupTitle: 'Tooltips',
                groupIcon: 'fa fa-comment',
                controls: [
                    {
                        type: 'switch',
                        name: 'enable_tooltips',
                        title: 'Habilitar Tooltips',
                        value: $.imageMapProDefaultSettings.general.enable_tooltips,
                    },
                    {
                        type: 'select',
                        name: 'show_tooltips',
                        title: 'Mostrar Tooltips al:',
                        options: [
                            { value: 'mouseover', title: 'Encimar mouse' },
                            { value: 'click', title: 'Hacer Click' },
                        ],
                        value: $.imageMapProDefaultSpotSettings.actions.click
                    },
                    {
                        type: 'switch',
                        name: 'sticky_tooltips',
                        title: 'Tooltips pegajosas',
                        value: $.imageMapProDefaultSettings.general.sticky_tooltips,
                    },
                    {
                        type: 'switch',
                        name: 'constrain_tooltips',
                        title: 'Contraer Tooltips',
                        value: $.imageMapProDefaultSettings.general.constrain_tooltips,
                    },
                    {
                        type: 'select',
                        name: 'tooltip_animation',
                        title: 'Tooltip animadas',
                        options: [
                            { value: 'none', title: 'No' },
                            { value: 'grow', title: 'Crecer' },
                            { value: 'fade', title: 'Aclarar' },
                        ],
                        value: $.imageMapProDefaultSettings.general.tooltip_animation
                    },
                    {
                        type: 'select',
                        name: 'fullscreen_tooltips',
                        title: 'Permitir pantalla completa',
                        options: [
                            { value: 'none', title: 'No' },
                            { value: 'mobile-only', title: 'Solo en mobil' },
                            { value: 'always', title: 'Siempre' },
                        ],
                        value: $.imageMapProDefaultSettings.general.fullscreen_tooltips
                    },
                ]
            },
            {
                groupName: 'fullscreen',
                groupTitle: 'Fullscreen',
                groupIcon: 'fa fa-arrows-alt',
                controls: [
                    {
                        type: 'switch',
                        name: 'enable_fullscreen_mode',
                        title: 'Pantalla completa',
                        value: $.imageMapProDefaultSettings.fullscreen.enable_fullscreen_mode,
                    },
                    {
                        type: 'switch',
                        name: 'start_in_fullscreen_mode',
                        title: 'Al iniciar',
                        value: $.imageMapProDefaultSettings.fullscreen.start_in_fullscreen_mode,
                    },
                    {
                        type: 'color',
                        name: 'fullscreen_background',
                        title: 'Color de fondo',
                        value: $.imageMapProDefaultSettings.fullscreen.fullscreen_background,
                    },
                    {
                        type: 'fullscreen button position',
                        name: 'fullscreen_button_position',
                        title: 'Posición del botón',
                        value: 1 // 0 = top left, 1 = top center, 2 = top right, 3 = bottom right, 4 = bottom center, 5 = bottom left
                    },
                    {
                        type: 'button group',
                        name: 'fullscreen_button_type',
                        title: 'Tipo de botón',
                        options: [
                            { value: 'icon', title: 'Icono' },
                            { value: 'text', title: 'Texto' },
                            { value: 'icon_and_text', title: 'Ambos' }
                        ],
                        value: $.imageMapProDefaultSettings.fullscreen.fullscreen_button_type,
                    },
                    {
                        type: 'color',
                        name: 'fullscreen_button_color',
                        title: 'Color del botón',
                        value: $.imageMapProDefaultSettings.fullscreen.fullscreen_button_color,
                    },
                    {
                        type: 'color',
                        name: 'fullscreen_button_text_color',
                        title: 'Color de botón Icono/Texto',
                        value: $.imageMapProDefaultSettings.fullscreen.fullscreen_button_text_color,
                    }
                ]
            }
        ]
    });
    $.wcpEditorCreateForm({
        name: 'Shape Settings',
        controlGroups: [
            {
                groupName: 'general',
                groupTitle: 'General',
                groupIcon: 'fa fa-cog',
                controls: [
                    {
                        type: 'float',
                        name: 'x',
                        title: 'X',
                        value: $.imageMapProDefaultSpotSettings.x
                    },
                    {
                        type: 'float',
                        name: 'y',
                        title: 'Y',
                        value: $.imageMapProDefaultSpotSettings.y
                    },
                    {
                        type: 'float',
                        name: 'width',
                        title: 'Ancho',
                        value: $.imageMapProDefaultSpotSettings.width
                    },
                    {
                        type: 'float',
                        name: 'height',
                        title: 'Alto',
                        value: $.imageMapProDefaultSpotSettings.height
                    },
                    {
                        type: 'select',
                        name: 'connected_to',
                        title: 'Conectar a la forma',
                        options: [
                            { value: '', title: '(Sin conectar)' },
                        ],
                        value: $.imageMapProDefaultSpotSettings.connected_to
                    },
                    {
                        type: 'switch',
                        name: 'use_connected_shape_tooltip',
                        title: 'Usar tooltip de la forma conectada',
                        value: $.imageMapProDefaultSpotSettings.use_connected_shape_tooltip
                    },
                ]
            },
            /*
            {
                groupName: 'actions',
                groupTitle: 'Actions',
                groupIcon: 'fa fa-bolt',
                controls: [
                    {
                        type: 'select',
                        name: 'click',
                        title: 'Click Action',
                        options: [
                            { value: 'no-action', title: 'No Action' },
                            { value: 'run-script', title: 'Run Script' },
                            { value: 'follow-link', title: 'Follow Link' },
                        ],
                        value: $.imageMapProDefaultSpotSettings.actions.click
                    },
                    {
                        type: 'text',
                        name: 'link',
                        title: 'Link URL',
                        value: $.imageMapProDefaultSpotSettings.actions.link
                    },
                    {
                        type: 'textarea',
                        name: 'script',
                        title: 'Script to Run',
                        value: $.imageMapProDefaultSpotSettings.actions.script
                    },
                    {
                        type: 'switch',
                        name: 'open_link_in_new_window',
                        title: 'Open Link in New Window',
                        value: $.imageMapProDefaultSpotSettings.actions.open_link_in_new_window
                    },
                ]
            },
            */
            {
                groupName: 'icon',
                groupTitle: 'Icono',
                groupIcon: 'fa fa-map-marker',
                controls: [
                    {
                        type: 'switch',
                        name: 'use_icon',
                        title: 'Usar ícono',
                        value: $.imageMapProDefaultSpotSettings.default_style.use_icon
                    },
                    {
                        type: 'button group',
                        name: 'icon_type',
                        title: 'Tipo de ícono',
                        options: [
                            { value: 'library', title: 'Librería' },
                            { value: 'custom', title: 'Personalizado' },
                        ],
                        value: $.imageMapProDefaultSpotSettings.default_style.icon_type
                    },
                    {
                        type: 'button',
                        name: 'choose_icon_from_library',
                        title: 'Elegir desde librería',
                        options: { event_name: 'button-choose-icon-clicked' },
                        value: undefined
                    },
                    {
                        type: 'text',
                        name: 'icon_svg_path',
                        title: 'Icono SVG',
                        value: $.imageMapProDefaultSpotSettings.default_style.icon_svg_path,
                        render: false
                    },
                    {
                        type: 'text',
                        name: 'icon_svg_viewbox',
                        title: 'Icono SVG Viewbox',
                        value: $.imageMapProDefaultSpotSettings.default_style.icon_svg_viewbox,
                        render: false
                    },
                    {
                        type: 'text',
                        name: 'icon_url',
                        title: 'URL del ícono',
                        value: $.imageMapProDefaultSpotSettings.default_style.icon_url
                    },
                    {
                        type: 'switch',
                        name: 'icon_is_pin',
                        title: 'El ícono es un Pin',
                        value: $.imageMapProDefaultSpotSettings.default_style.icon_is_pin
                    },
                    {
                        type: 'switch',
                        name: 'icon_shadow',
                        title: 'Icono con sombra',
                        value: $.imageMapProDefaultSpotSettings.default_style.icon_shadow
                    },
                ]
            },
            {
                groupName: 'default_style',
                groupTitle: 'Estilo',
                groupIcon: 'fa fa-paint-brush',
                controls: [
                    {
                        type: 'slider',
                        name: 'opacity',
                        title: 'Opacidad',
                        options: { min: 0, max: 1 },
                        value: $.imageMapProDefaultSpotSettings.default_style.opacity
                    },
                    {
                        type: 'color',
                        name: 'icon_fill',
                        title: 'Color de relleno en ícono SVG',
                        value: $.imageMapProDefaultSpotSettings.default_style.icon_fill
                    },
                    {
                        type: 'int',
                        name: 'border_radius',
                        title: 'Radio del borde',
                        value: $.imageMapProDefaultSpotSettings.default_style.border_radius
                    },
                    {
                        type: 'color',
                        name: 'background_color',
                        title: 'Color del fondor',
                        value: $.imageMapProDefaultSpotSettings.default_style.background_color
                    },
                    {
                        type: 'slider',
                        name: 'background_opacity',
                        title: 'Opacidad del fondo',
                        options: { min: 0, max: 1 },
                        value: $.imageMapProDefaultSpotSettings.default_style.background_opacity
                    },
                    {
                        type: 'slider',
                        name: 'border_width',
                        title: 'Ancho del borde',
                        options: { min: 0, max: 20, type: 'int' },
                        value: $.imageMapProDefaultSpotSettings.default_style.border_width
                    },
                    {
                        type: 'select',
                        name: 'border_style',
                        title: 'Estilo del borde',
                        options: [
                            { value: 'none', title: 'Ninguno' },
                            { value: 'hidden', title: 'Oculto' },
                            { value: 'solid', title: 'Sólido' },
                            { value: 'dotted', title: 'Punteado' },
                            { value: 'dashed', title: 'Rayado' },
                            { value: 'double', title: 'Doble' },
                            { value: 'groove', title: 'Ranurado' },
                            { value: 'inset', title: 'Adentro' },
                            { value: 'outset', title: 'Afuera' },
                        ],
                        value: $.imageMapProDefaultSpotSettings.default_style.border_style
                    },
                    {
                        type: 'color',
                        name: 'border_color',
                        title: 'Color del borde',
                        value: $.imageMapProDefaultSpotSettings.default_style.border_color
                    },
                    {
                        type: 'slider',
                        name: 'border_opacity',
                        title: 'Opacidad del borde',
                        options: { min: 0, max: 1 },
                        value: $.imageMapProDefaultSpotSettings.default_style.border_opacity
                    },
                    {
                        type: 'color',
                        name: 'fill',
                        title: 'Relleno',
                        value: $.imageMapProDefaultSpotSettings.default_style.fill
                    },
                    {
                        type: 'slider',
                        name: 'fill_opacity',
                        title: 'Opacidad del relleno',
                        options: { min: 0, max: 1 },
                        value: $.imageMapProDefaultSpotSettings.default_style.fill_opacity
                    },
                    {
                        type: 'color',
                        name: 'stroke_color',
                        title: 'Color del trazo',
                        value: $.imageMapProDefaultSpotSettings.default_style.stroke_color
                    },
                    {
                        type: 'slider',
                        name: 'stroke_opacity',
                        title: 'Opacidad del trazo',
                        options: { min: 0, max: 1 },
                        value: $.imageMapProDefaultSpotSettings.default_style.stroke_opacity
                    },
                    {
                        type: 'slider',
                        name: 'stroke_width',
                        title: 'Ancho del trazo',
                        options: { min: 0, max: 20, type: 'int' },
                        value: $.imageMapProDefaultSpotSettings.default_style.stroke_width
                    },
                    {
                        type: 'text',
                        name: 'stroke_dasharray',
                        title: 'Patron del trazo',
                        value: $.imageMapProDefaultSpotSettings.default_style.stroke_dasharray
                    },
                    {
                        type: 'select',
                        name: 'stroke_linecap',
                        title: 'Linecap del trazo',
                        options: [
                            { value: 'butt', title: 'Extremo' },
                            { value: 'round', title: 'Redondo' },
                            { value: 'square', title: 'Cuadrado' },
                        ],
                        value: $.imageMapProDefaultSpotSettings.default_style.stroke_linecap
                    },
                ]
            },
            {
                groupName: 'mouseover_style',
                groupTitle: 'Estilo con mouse',
                groupIcon: 'fa fa-paint-brush',
                controls: [
                    {
                        type: 'button',
                        name: 'copy_from_default_styles',
                        title: 'Estilo predeterminado',
                        options: { event_name: 'button-copy-from-default-styles-clicked' },
                        value: undefined
                    },
                    {
                        type: 'slider',
                        name: 'mouseover_opacity',
                        title: 'Opacidad',
                        options: { min: 0, max: 1 },
                        value: $.imageMapProDefaultSpotSettings.mouseover_style.opacity
                    },
                    {
                        type: 'color',
                        name: 'mouseover_icon_fill',
                        title: 'Color de relleno del icono SVG',
                        value: $.imageMapProDefaultSpotSettings.mouseover_style.icon_fill
                    },
                    {
                        type: 'int',
                        name: 'mouseover_border_radius',
                        title: 'Radio del borde',
                        value: $.imageMapProDefaultSpotSettings.mouseover_style.border_radius
                    },
                    {
                        type: 'color',
                        name: 'mouseover_background_color',
                        title: 'Color de fondo',
                        value: $.imageMapProDefaultSpotSettings.mouseover_style.background_color
                    },
                    {
                        type: 'slider',
                        name: 'mouseover_background_opacity',
                        title: 'Opacidad de fondo',
                        options: { min: 0, max: 1 },
                        value: $.imageMapProDefaultSpotSettings.mouseover_style.background_opacity
                    },
                    {
                        type: 'slider',
                        name: 'mouseover_border_width',
                        title: 'Ancho del borde',
                        options: { min: 0, max: 20, type: 'int' },
                        value: $.imageMapProDefaultSpotSettings.mouseover_style.border_width
                    },
                    {
                        type: 'select',
                        name: 'mouseover_border_style',
                        title: 'Estilo del borde',
                        options: [
                          { value: 'none', title: 'Ninguno' },
                          { value: 'hidden', title: 'Oculto' },
                          { value: 'solid', title: 'Sólido' },
                          { value: 'dotted', title: 'Punteado' },
                          { value: 'dashed', title: 'Rayado' },
                          { value: 'double', title: 'Doble' },
                          { value: 'groove', title: 'Ranurado' },
                          { value: 'inset', title: 'Adentro' },
                          { value: 'outset', title: 'Afuera' },
                        ],
                        value: $.imageMapProDefaultSpotSettings.mouseover_style.border_style
                    },
                    {
                        type: 'color',
                        name: 'mouseover_border_color',
                        title: 'Color del borde',
                        value: $.imageMapProDefaultSpotSettings.mouseover_style.border_color
                    },
                    {
                        type: 'slider',
                        name: 'mouseover_border_opacity',
                        title: 'Opacidad del borde',
                        options: { min: 0, max: 1 },
                        value: $.imageMapProDefaultSpotSettings.mouseover_style.border_opacity
                    },
                    {
                        type: 'color',
                        name: 'mouseover_fill',
                        title: 'Relleno',
                        value: $.imageMapProDefaultSpotSettings.mouseover_style.fill
                    },
                    {
                        type: 'slider',
                        name: 'mouseover_fill_opacity',
                        title: 'Opacidad del relleno',
                        options: { min: 0, max: 1 },
                        value: $.imageMapProDefaultSpotSettings.mouseover_style.fill_opacity
                    },
                    {
                        type: 'color',
                        name: 'mouseover_stroke_color',
                        title: 'Color del trazo',
                        value: $.imageMapProDefaultSpotSettings.mouseover_style.stroke_color
                    },
                    {
                        type: 'slider',
                        name: 'mouseover_stroke_opacity',
                        title: 'Opacidad del trazo',
                        options: { min: 0, max: 1 },
                        value: $.imageMapProDefaultSpotSettings.mouseover_style.stroke_opacity
                    },
                    {
                        type: 'slider',
                        name: 'mouseover_stroke_width',
                        title: 'Ancho del trazo',
                        options: { min: 0, max: 20, type: 'int' },
                        value: $.imageMapProDefaultSpotSettings.mouseover_style.stroke_width
                    },
                    {
                        type: 'text',
                        name: 'mouseover_stroke_dasharray',
                        title: 'Patron del trazo',
                        value: $.imageMapProDefaultSpotSettings.mouseover_style.stroke_dasharray
                    },
                    {
                        type: 'select',
                        name: 'mouseover_stroke_linecap',
                        title: 'Linecap del trazo',
                        options: [
                          { value: 'butt', title: 'Extremo' },
                          { value: 'round', title: 'Redondo' },
                          { value: 'square', title: 'Cuadrado' },
                        ],
                        value: $.imageMapProDefaultSpotSettings.mouseover_style.stroke_linecap
                    },
                ]
            },
            {
                groupName: 'tooltip_settings',
                groupTitle: 'Tooltip',
                groupIcon: 'fa fa-comment',
                controls: [
                    {
                        type: 'switch',
                        name: 'enable_tooltip',
                        title: 'Habilitar Tooltip',
                        value: $.imageMapProDefaultSpotSettings.tooltip_style.enable_tooltip,
                    },
                    {
                        type: 'int',
                        name: 'tooltip_border_radius',
                        title: 'Radio del borde',
                        value: $.imageMapProDefaultSpotSettings.tooltip_style.border_radius
                    },
                    {
                        type: 'int',
                        name: 'tooltip_padding',
                        title: 'Padding',
                        value: $.imageMapProDefaultSpotSettings.tooltip_style.padding
                    },
                    {
                        type: 'color',
                        name: 'tooltip_background_color',
                        title: 'Color de fondor',
                        value: $.imageMapProDefaultSpotSettings.tooltip_style.background_color
                    },
                    {
                        type: 'slider',
                        name: 'tooltip_background_opacity',
                        title: 'Opacidad del fondo',
                        options: { min: 0, max: 1 },
                        value: $.imageMapProDefaultSpotSettings.tooltip_style.background_opacity
                    },
                    {
                        type: 'select',
                        name: 'tooltip_position',
                        title: 'Posición',
                        options: [
                            { value: 'top', title: 'Arriba' },
                            { value: 'bottom', title: 'Abajo' },
                            { value: 'left', title: 'Izquierda' },
                            { value: 'right', title: 'Derecha' },
                        ],
                        value: $.imageMapProDefaultSpotSettings.tooltip_style.position
                    },
                    {
                        type: 'switch',
                        name: 'tooltip_auto_width',
                        title: 'Ancho automático',
                        value: $.imageMapProDefaultSpotSettings.tooltip_style.auto_width
                    },
                    {
                        type: 'int',
                        name: 'tooltip_width',
                        title: 'Ancho',
                        value: $.imageMapProDefaultSpotSettings.tooltip_style.width
                    },
                ]
            },
            {
                groupName: 'tooltip_content',
                groupTitle: 'Contenido',
                groupIcon: 'fa fa-paragraph',
                controls: [

                    {
                        type: 'button group',
                        name: 'tooltip_content_type',
                        title: '',
                        options: [
                            { value: 'plain-text', title: 'Plain Text' },
                            { value: 'content-builder', title: 'Content Builder' },
                        ]
                    },

                    {
                        type: 'textarea',
                        name: 'plain_text',
                        title: 'Contenido del Tooltip',
                        value: $.imageMapProDefaultSpotSettings.tooltip_content.plain_text
                    },
                    {
                        type: 'color',
                        name: 'plain_text_color',
                        title: 'Color del texto',
                        value: $.imageMapProDefaultSpotSettings.tooltip_content.plain_text_color
                    },
                    {
                        type: 'object',
                        name: 'squares_settings',
                        title: 'Configuración de la cuadricula',
                        value: $.imageMapProDefaultSpotSettings.tooltip_content.squares_settings,
						render: false
                    },

                    {
                        type: 'button',
                        name: 'launch_content_builder',
                        title: 'Launch Content Builder',
                        options: { event_name: 'button-launch-content-builder-clicked' },
                        value: undefined
                    },

                ]
            },
        ]
    });

    // Register Tour
    $.wcpTourRegister({
        name: 'Image Map Pro Editor Tour',
        welcomeScreen: {
            title: 'Welcome!',
            text: 'This is a guided tour to get you started quickly with Image Map Pro.<br>Click the button below to begin!',
            startButtonTitle: 'Take the Tour',
            cancelButtonTitle: 'Or skip this guide',
        },
        steps: [
            {
                title: 'Drawing Shapes',
                text: 'Use the toolbar on the left to draw different kinds of shapes.<br>Choose between Spots/Icons, Ellipses, Rectangles or polygons.',
                tip: {
                    title: 'Toolbar',
                    subtitle: 'Watch Video',
                    media: {
                        type: 'video',
                        url_mp4: 'https://webcraftplugins.com/uploads/image-map-pro/videos/01-drawing-shapes.mp4',
                        url_webm: 'https://webcraftplugins.com/uploads/image-map-pro/videos/01-drawing-shapes.mp4',
                        url_ogv: 'https://webcraftplugins.com/uploads/image-map-pro/videos/01-drawing-shapes.mp4',
                    },
                    position: 'bottom-left',
                    highlightRect: true
                },
            },
            {
                title: 'Customize Your Shapes',
                text: 'Change how the shapes look by selecting a shape <br>and clicking “Shape” on the left, and then “Default Style” or “Mouseover Style”.',
                tip: {
                    title: 'Shape Styles',
                    subtitle: 'Watch Video',
                    media: {
                        type: 'video',
                        url_mp4: 'https://webcraftplugins.com/uploads/image-map-pro/videos/02-customizing-shapes.mp4',
                        url_webm: 'https://webcraftplugins.com/uploads/image-map-pro/videos/02-customizing-shapes.mp4',
                        url_ogv: 'https://webcraftplugins.com/uploads/image-map-pro/videos/02-customizing-shapes.mp4',
                    },
                    position: 'bottom-right',
                    arrowStyle: 'transform: scaleX(-1);',
                    highlightRect: true
                }
            },
            {
                title: 'Edit and Delete Shapes',
                text: 'From the list on the right you can do various things with your shapes, like <br>copy-pasting styles, reordering them, or deleting the shapes.',
                tip: {
                    title: 'Shapes List',
                    subtitle: 'Watch Video',
                    media: {
                        type: 'video',
                        url_mp4: 'https://webcraftplugins.com/uploads/image-map-pro/videos/03-list.mp4',
                        url_webm: 'https://webcraftplugins.com/uploads/image-map-pro/videos/03-list.mp4',
                        url_ogv: 'https://webcraftplugins.com/uploads/image-map-pro/videos/03-list.mp4',
                    },
                    position: 'bottom-left',
                    highlightRect: true
                }
            },
            {
                title: 'Use Icons',
                text: 'To have an icon, place a Spot shape on the image, then open the “Icon” tab on the left to customize it.<br>Choose from 150 built-in SVG icons, or use your own!',
                tip: {
                    title: 'Icon Settings',
                    subtitle: 'Watch Video',
                    media: {
                        type: 'video',
                        url_mp4: 'https://webcraftplugins.com/uploads/image-map-pro/videos/04-icons.mp4',
                        url_webm: 'https://webcraftplugins.com/uploads/image-map-pro/videos/04-icons.mp4',
                        url_ogv: 'https://webcraftplugins.com/uploads/image-map-pro/videos/04-icons.mp4',
                    },
                    position: 'bottom-right',
                    highlightRect: true
                }
            },
            {
                title: 'Tooltip Content Builder',
                text: 'Use a fully featured content builder to add rich content to the tooltips. <br>You can launch the content builder by selecting a shape and opening the "Tooltip Content" settings tab.',
                tip: {
                    title: 'Tooltip Content Settings',
                    subtitle: 'Watch Video',
                    media: {
                        type: 'video',
                        url_mp4: 'https://webcraftplugins.com/uploads/image-map-pro/videos/05-content-builder.mp4',
                        url_webm: 'https://webcraftplugins.com/uploads/image-map-pro/videos/05-content-builder.mp4',
                        url_ogv: 'https://webcraftplugins.com/uploads/image-map-pro/videos/05-content-builder.mp4',
                    },
                    position: 'top-right',
                    highlightRect: true
                }
            },
            {
                title: 'Responsive &amp; Fullscreen Tooltips',
                text: 'Image Map Pro is fully optimized for mobile devices. It\'s responsive, <br>and you even have the option for fullscreen tooltips on mobile. To change these settings, open the "General" settings tab.',
                tip: {
                    title: 'Image Map Settings',
                    subtitle: 'Watch Video',
                    media: {
                        type: 'video',
                        url_mp4: 'https://webcraftplugins.com/uploads/image-map-pro/videos/06-responsive.mp4',
                        url_webm: 'https://webcraftplugins.com/uploads/image-map-pro/videos/06-responsive.mp4',
                        url_ogv: 'https://webcraftplugins.com/uploads/image-map-pro/videos/06-responsive.mp4',
                    },
                    position: 'bottom-right',
                    highlightRect: true
                }
            },
            {
                title: 'Preview Mode',
                text: 'See how your image map will look like by entering Preview Mode. <br>You can continue to tweak settings and see the changes live!',
                tip: {
                    title: 'Preview Mode Button',
                    subtitle: 'Watch Video',
                    media: {
                        type: 'video',
                        url_mp4: 'https://webcraftplugins.com/uploads/image-map-pro/videos/07-preview-mode.mp4',
                        url_webm: 'https://webcraftplugins.com/uploads/image-map-pro/videos/07-preview-mode.mp4',
                        url_ogv: 'https://webcraftplugins.com/uploads/image-map-pro/videos/07-preview-mode.mp4',
                    },
                    position: 'bottom-right',
                    highlightRect: true
                }
            },
            {
                title: 'Save and Load',
                text: 'This editor uses Local Storage to save your work. You can have <br>as many image maps as you want, and switch between them any time. No database needed!',
                tip: {
                    title: 'Save/Load Buttons',
                    subtitle: 'Watch Video',
                    media: {
                        type: 'video',
                        url_mp4: 'https://webcraftplugins.com/uploads/image-map-pro/videos/08-save-load.mp4',
                        url_webm: 'https://webcraftplugins.com/uploads/image-map-pro/videos/08-save-load.mp4',
                        url_ogv: 'https://webcraftplugins.com/uploads/image-map-pro/videos/08-save-load.mp4',
                    },
                    position: 'bottom-right',
                    highlightRect: true
                }
            },
            {
                title: 'Import and Export',
                text: 'You can also import and export your data, <br>in case you need to switch devices, or save your work somewhere else.',
                tip: {
                    title: 'Import/Export Buttons',
                    subtitle: 'Watch Video',
                    media: {
                        type: 'video',
                        url_mp4: 'https://webcraftplugins.com/uploads/image-map-pro/videos/09-import-export.mp4',
                        url_webm: 'https://webcraftplugins.com/uploads/image-map-pro/videos/09-import-export.mp4',
                        url_ogv: 'https://webcraftplugins.com/uploads/image-map-pro/videos/09-import-export.mp4',
                    },
                    position: 'bottom-right',
                    highlightRect: true
                }
            },
            {
                title: 'Easy Installation',
                text: 'When you are ready to add the image map to your site, simply click the <br>"Code" button and follow the instructions.',
                tip: {
                    title: 'Code Button',
                    subtitle: 'Watch Video',
                    media: {
                        type: 'video',
                        url_mp4: 'https://webcraftplugins.com/uploads/image-map-pro/videos/10-install.mp4',
                        url_webm: 'https://webcraftplugins.com/uploads/image-map-pro/videos/10-install.mp4',
                        url_ogv: 'https://webcraftplugins.com/uploads/image-map-pro/videos/10-install.mp4',
                    },
                    position: 'bottom-right',
                    highlightRect: true
                }
            },
        ]
    });

    var extraMainButtons = [
        {
            name: 'code',
            icon: 'fa fa-code',
            title: 'Code'
        },
        {
            name: 'import',
            icon: 'fa fa-arrow-down',
            title: 'Import'
        },
        {
            name: 'export',
            icon: 'fa fa-arrow-up',
            title: 'Export'
        }
    ];

    $.WCPEditorSettings = {
        mainTabs: [
            {
                name: 'Mapa de imagen',
                icon: 'fa fa-cog',
                title: 'Parametros de imagen'
            },
            {
                name: 'Forma',
                icon: 'fa fa-object-ungroup',
                title: 'Parametros de selección'
            }
        ],
        toolbarButtons: [
            [
                {
                    name: 'spot',
                    icon: 'fa fa-map-marker',
                    title: 'Icono'
                },
                {
                    name: 'oval',
                    customIcon: '<div style="width: 14px; height: 14px; border: 2px solid #222; border-radius: 50px;"></div>',
                    title: 'Elipse'
                },
                {
                    name: 'rect',
                    customIcon: '<div style="width: 20px; height: 14px; border: 2px solid #222; border-radius: 5px;"></div>',
                    title: 'Rectángulo'
                },
                {
                    name: 'poly',
                    customIcon: '<svg width="24px" height="24px" viewport="0 0 24 24" version="1.1" xmlns="http://www.w3.org/2000/svg"><polygon fill="none" style="stroke: black; stroke-width: 2px;" points="20,20 18,4 7,7 4,20"></polygon><ellipse cx="20" cy="20" rx="3" ry="3"></ellipse><ellipse cx="18" cy="4" rx="3" ry="3"></ellipse><ellipse cx="7" cy="7" rx="3" ry="3"></ellipse><ellipse cx="4" cy="20" rx="3" ry="3"></ellipse></svg>',
                    title: 'Polígono'
                },
            ],
            [
                {
                    name: 'select',
                    icon: 'fa fa-mouse-pointer',
                    title: 'Selector'
                },
                {
                    name: 'zoom-in',
                    icon: 'fa fa-search-plus',
                    title: 'Acercar (CTRL +)',
                },
                {
                    name: 'zoom-out',
                    icon: 'fa fa-search-minus',
                    title: 'Alejar (CTRL -)'
                },
                {
                    name: 'drag',
                    icon: 'fa fa-hand-paper-o',
                    title: 'Arrastrar imagen (Mantener la barra espaciadora y arrastrar)'
                },
                {
                    name: 'reset',
                    customIcon: '1:1',
                    title: 'Reinicializar tamaño (CTRL + 0)',
                    kind: 'button'
                },
            ]
        ],
        extraMainButtons: extraMainButtons,
        listItemButtons: [

        ],
        listItemTitle: 'Formas',
        listItemTitleButtons: [
            {
                name: 'rename',
                icon: 'fa fa-pencil',
                title: 'Renombrar'
            },
            {
                name: 'copy',
                icon: 'fa fa-files-o',
                title: 'Copiar estilo'
            },
            {
                name: 'paste',
                icon: 'fa fa-clipboard',
                title: 'Pegar estilo'
            },
            {
                name: 'duplicate',
                icon: 'fa fa-clone',
                title: 'Duplicar'
            },
            {
                name: 'delete',
                icon: 'fa fa-trash-o',
                title: 'Borrar'
            },
        ],
        fullscreenButton: false,
        newButton: true,
        helpButton: false,
        previewToggle: true
    };

    // Init Editor
    $(document).ready(function() {
        $.image_map_pro_init_editor(undefined, $.WCPEditorSettings);
    });
})( jQuery, window, document );
