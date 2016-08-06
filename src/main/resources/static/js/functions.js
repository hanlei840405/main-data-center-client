$(document).ready(function () {
	/* --------------------------------------------------------
	 Template Settings
	 -----------------------------------------------------------*/

	var settings = '<a id="settings" href="#changeSkin" data-toggle="modal">' +
			'<i class="fa fa-gear"></i> Change Skin' +
			'</a>' +
			'<div class="modal fade" id="changeSkin" tabindex="-1" role="dialog" aria-hidden="true">' +
			'<div class="modal-dialog modal-lg">' +
			'<div class="modal-content">' +
			'<div class="modal-header">' +
			'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' +
			'<h4 class="modal-title">Change Template Skin</h4>' +
			'</div>' +
			'<div class="modal-body">' +
			'<div class="row template-skins">' +
			'<a data-skin="skin-blur-violate" class="col-sm-2 col-xs-4" href="">' +
			'<img src="img/skin-violate.jpg" alt="">' +
			'</a>' +
			'<a data-skin="skin-blur-lights" class="col-sm-2 col-xs-4" href="">' +
			'<img src="img/skin-lights.jpg" alt="">' +
			'</a>' +
			'<a data-skin="skin-blur-city" class="col-sm-2 col-xs-4" href="">' +
			'<img src="img/skin-city.jpg" alt="">' +
			'</a>' +
			'<a data-skin="skin-blur-greenish" class="col-sm-2 col-xs-4" href="">' +
			'<img src="img/skin-greenish.jpg" alt="">' +
			'</a>' +
			'<a data-skin="skin-blur-night" class="col-sm-2 col-xs-4" href="">' +
			'<img src="img/skin-night.jpg" alt="">' +
			'</a>' +
			'<a data-skin="skin-blur-blue" class="col-sm-2 col-xs-4" href="">' +
			'<img src="img/skin-blue.jpg" alt="">' +
			'</a>' +
			'<a data-skin="skin-blur-sunny" class="col-sm-2 col-xs-4" href="">' +
			'<img src="img/skin-sunny.jpg" alt="">' +
			'</a>' +
			'<a data-skin="skin-cloth" class="col-sm-2 col-xs-4" href="">' +
			'<img src="img/skin-cloth.jpg" alt="">' +
			'</a>' +
			'<a data-skin="skin-tectile" class="col-sm-2 col-xs-4" href="">' +
			'<img src="img/skin-tectile.jpg" alt="">' +
			'</a>' +
			'<a data-skin="skin-blur-chrome" class="col-sm-2 col-xs-4" href="">' +
			'<img src="img/skin-chrome.jpg" alt="">' +
			'</a>' +
			'<a data-skin="skin-blur-ocean" class="col-sm-2 col-xs-4" href="">' +
			'<img src="img/skin-ocean.jpg" alt="">' +
			'</a>' +
			'<a data-skin="skin-blur-sunset" class="col-sm-2 col-xs-4" href="">' +
			'<img src="img/skin-sunset.jpg" alt="">' +
			'</a>' +
			'<a data-skin="skin-blur-yellow" class="col-sm-2 col-xs-4" href="">' +
			'<img src="img/skin-yellow.jpg" alt="">' +
			'</a>' +
			'<a  data-skin="skin-blur-kiwi"class="col-sm-2 col-xs-4" href="">' +
			'<img src="img/skin-kiwi.jpg" alt="">' +
			'</a>' +
			'</div>' +
			'</div>' +
			'</div>' +
			'</div>' +
			'</div>';
	$('#main').prepend(settings);

	$('body').on('click', '.template-skins > a', function (e) {
		e.preventDefault();
		var skin = $(this).data('skin');
		$('body').attr('id', skin);
		$('#changeSkin').modal('hide');
	});

	/* --------------------------------------------------------
	 Components
	 -----------------------------------------------------------*/
	flushComponents();

	/* --------------------------------------------------------
	 Sidebar + Menu
	 -----------------------------------------------------------*/
	flushSidebar();

	/* --------------------------------------------------------
	 Chart Info
	 -----------------------------------------------------------*/
	flushChart();

	/* --------------------------------------------------------
	 Todo List
	 -----------------------------------------------------------*/
	flushToList();

	/* --------------------------------------------------------
	 Custom Scrollbar
	 -----------------------------------------------------------*/
	flushCustomScrollbar();

	/* --------------------------------------------------------
	 Messages + Notifications
	 -----------------------------------------------------------*/
	flushMessages();

	/* --------------------------------------------------------
	 Calendar
	 -----------------------------------------------------------*/
	flushCalendar();

	/* --------------------------------------------------------
	 RSS Feed widget
	 -----------------------------------------------------------*/
	flushRss();

	/* --------------------------------------------------------
	 Chat
	 -----------------------------------------------------------*/
	flushChat();

	/* --------------------------------------------------------
	 Form Validation
	 -----------------------------------------------------------*/
	flushValidationForm();

	/* --------------------------------------------------------
	 `Color Picker
	 -----------------------------------------------------------*/
	flushColorPicker();

	/* --------------------------------------------------------
	 Date Time Picker
	 -----------------------------------------------------------*/
	flushDateTimePicker();

	/* --------------------------------------------------------
	 Input Slider
	 -----------------------------------------------------------*/
	flushInputSlider();

	/* --------------------------------------------------------
	 WYSIWYE Editor + Markedown
	 -----------------------------------------------------------*/
	flushWYSIWYEEditor();

	/* --------------------------------------------------------
	 Media Player
	 -----------------------------------------------------------*/
	flushMediaPlayer();

	/* ---------------------------
	 Image Popup [Pirobox]
	 --------------------------- */
	flushImagePopup();

	/* ---------------------------
	 Vertical tab
	 --------------------------- */
	flushVerticalTab();

	/* --------------------------------------------------------
	 MAC Hack
	 -----------------------------------------------------------*/
	flushMac();
	/* --------------------------------------------------------
	 Tooltips
	 -----------------------------------------------------------*/
	flushTooltips();

	/* --------------------------------------------------------
	 Animate numbers
	 -----------------------------------------------------------*/
	flushAnimateNumbers();

	/* --------------------------------------------------------
	 Date Time Widget
	 -----------------------------------------------------------*/
	flushDateTimeWidget();
});



