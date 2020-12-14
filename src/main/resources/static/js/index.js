$(document).ready(function () {
	$(window).scroll(function () {
	var page1 = document.getElementById("page1").offsetTop;
		if (page1 >= $(window).scrollTop() && page1 < ($(window).scrollTop()+$(window).height())) {
			$('#page1Anim').addClass('rotateInUpRight')
		} else {
			$('#page1Anim').removeClass('rotateInUpRight')
		}
	var page2 = document.getElementById("page2").offsetTop;
		if (page2 >= $(window).scrollTop() && page2 < ($(window).scrollTop()+$(window).height())) {
			$('#page2Anim').addClass('tada')
		} else {
			$('#page2Anim').removeClass('tada')
		}
	var page3 = document.getElementById("page3").offsetTop;
		if (page3 >= $(window).scrollTop() && page3 < ($(window).scrollTop()+$(window).height())) {
			$('#page3Anim').addClass('wobble')
		} else {
			$('#page3Anim').removeClass('wobble')
		}
	var page4 = document.getElementById("page4").offsetTop;
		if (page4 >= $(window).scrollTop() && page4 < ($(window).scrollTop()+$(window).height())) {
			$('#page4Anim').addClass('bounceInLeft')
		} else {
			$('#page4Anim').removeClass('bounceInLeft')
		}
	var page5 = document.getElementById("page5").offsetTop;
		if (page5 >= $(window).scrollTop() && page5 < ($(window).scrollTop()+$(window).height())) {
			$('#page5Anim').addClass('jello')
		} else {
			$('#page5Anim').removeClass('jello')
		}
	var page6 = document.getElementById("page6").offsetTop;
		if (page6 >= $(window).scrollTop() && page6 < ($(window).scrollTop()+$(window).height())) {
			$('#page6Anim').addClass('fadeInDown')
		} else {
			$('#page6Anim').removeClass('fadeInDown')
		}
	var page7 = document.getElementById("page7").offsetTop;
		if (page7 >= $(window).scrollTop() && page7 < ($(window).scrollTop()+$(window).height())) {
			$('#page7Anim').addClass('rollIn')
		} else {
			$('#page7Anim').removeClass('rollIn')
		}
	});
});