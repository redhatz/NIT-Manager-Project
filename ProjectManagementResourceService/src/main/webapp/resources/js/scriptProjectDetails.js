
$(document).ready(function () {
  var counter = 2;

  $("#addButton").click(function () {
    if (counter > 10) {
      alert("Only 10 textboxes allow");
      return false;
    }

    var newTextBoxDiv = $(document.createElement("div")).attr(
      "id",
      "TextBoxDiv" + counter
    );

    newTextBoxDiv
      .after()
      .html(
        "<label>Database Name #" +
          counter +
          " : </label>" +
          '<input type="text" name="databaseName' +
          '" id="textbox' +
          counter +
          '" value="" >'
      );

    newTextBoxDiv.appendTo("#TextBoxesGroup");

    counter++;
  });

  $("#removeButton").click(function () {
    if (counter == 1) {
      alert("No more textbox to remove");
      return false;
    }

    counter--;

    $("#TextBoxDiv" + counter).remove();
  });

  $("#getButtonValue").click(function () {
    var msg = "";
    for (i = 1; i < counter; i++) {
      msg += "\n Textbox #" + i + " : " + $("#textbox" + i).val();
    }
    alert(msg);
  });
});



$(document).ready(function () {
  var current_fs, next_fs, previous_fs;
  var left, opacity, scale;
  var animating;
  $(".steps").validate({
    errorClass: "invalid",
    errorElement: "span",
    errorPlacement: function (error, element) {
      error.insertAfter(element.next("span").children());
    },
    highlight: function (element) {
      $(element).next("span").show();
    },
    unhighlight: function (element) {
      $(element).next("span").hide();
    },
  });
  $(".next").click(function () {
    $(".steps").validate({
      errorClass: "invalid",
      errorElement: "span",
      errorPlacement: function (error, element) {
        error.insertAfter(element.next("span").children());
      },
      highlight: function (element) {
        $(element).next("span").show();
      },
      unhighlight: function (element) {
        $(element).next("span").hide();
      },
    });
    if (!$(".steps").valid()) {
      return true;
    }
    if (animating) return false;
    animating = true;
    current_fs = $(this).parent();
    next_fs = $(this).parent().next();
    $("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
    next_fs.show();
    current_fs.animate(
      {
        opacity: 0,
      },
      {
        step: function (now, mx) {
          scale = 1 - (1 - now) * 0.2;
          left = now * 50 + "%";
          opacity = 1 - now;
          current_fs.css({
            transform: "scale(" + scale + ")",
          });
          next_fs.css({
            left: left,
            opacity: opacity,
          });
        },
        duration: 800,
        complete: function () {
          current_fs.hide();
          animating = false;
        },
        easing: "easeInOutExpo",
      }
    );
  });
  $(".submit").click(function () {
    $(".steps").validate({
      errorClass: "invalid",
      errorElement: "span",
      errorPlacement: function (error, element) {
        error.insertAfter(element.next("span").children());
      },
      highlight: function (element) {
        $(element).next("span").show();
      },
      unhighlight: function (element) {
        $(element).next("span").hide();
      },
    });
    if (!$(".steps").valid()) {
      return false;
    }
    if (animating) return false;
    animating = true;
    current_fs = $(this).parent();
    next_fs = $(this).parent().next();
    $("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
    next_fs.show();
    current_fs.animate(
      {
        opacity: 0,
      },
      {
        step: function (now, mx) {
          scale = 1 - (1 - now) * 0.2;
          left = now * 50 + "%";
          opacity = 1 - now;
          current_fs.css({
            transform: "scale(" + scale + ")",
          });
          next_fs.css({
            left: left,
            opacity: opacity,
          });
        },
        duration: 800,
        complete: function () {
          current_fs.hide();
          animating = false;
        },
        easing: "easeInOutExpo",
      }
    );
  });
  $(".previous").click(function () {
    if (animating) return false;
    animating = true;
    current_fs = $(this).parent();
    previous_fs = $(this).parent().prev();
    $("#progressbar li")
      .eq($("fieldset").index(current_fs))
      .removeClass("active");
    previous_fs.show();
    current_fs.animate(
      {
        opacity: 0,
      },
      {
        step: function (now, mx) {
          scale = 0.8 + (1 - now) * 0.2;
          left = (1 - now) * 50 + "%";
          opacity = 1 - now;
          current_fs.css({
            left: left,
          });
          previous_fs.css({
            transform: "scale(" + scale + ")",
            opacity: opacity,
          });
        },
        duration: 800,
        complete: function () {
          current_fs.hide();
          animating = false;
        },
        easing: "easeInOutExpo",
      }
    );
  });
});
jQuery(document).ready(function () {
  jQuery(
    "#edit-submitted-acquisition-amount-1,#edit-submitted-acquisition-amount-2,#edit-submitted-cultivation-amount-1,#edit-submitted-cultivation-amount-2,#edit-submitted-cultivation-amount-3,#edit-submitted-cultivation-amount-4,#edit-submitted-retention-amount-1,#edit-submitted-retention-amount-2,#edit-submitted-constituent-base-total-constituents"
  ).keyup(function () {
    calcTotal();
  });
});

function calcTotal() {
  var grade = 0;
  var donorTotal = Number(
    jQuery("#edit-submitted-constituent-base-total-constituents")
      .val()
      .replace(/,/g, "")
  );
  if (donorTotal) {
    donorTotal = parseFloat(donorTotal);
  } else {
    donorTotal = 0;
  }
  grade += getBonusDonorPoints(donorTotal);
  var acqAmount1 = Number(
    jQuery("#edit-submitted-acquisition-amount-1").val().replace(/,/g, "")
  );
  var acqAmount2 = Number(
    jQuery("#edit-submitted-acquisition-amount-2").val().replace(/,/g, "")
  );
  var acqTotal = 0;
  if (acqAmount1) {
    acqAmount1 = parseFloat(acqAmount1);
  } else {
    acqAmount1 = 0;
  }
  if (acqAmount2) {
    acqAmount2 = parseFloat(acqAmount2);
  } else {
    acqAmount2 = 0;
  }
  if (acqAmount1 > 0 && acqAmount2 > 0) {
    acqTotal = (((acqAmount2 - acqAmount1) / acqAmount1) * 100).toFixed(2);
  } else {
    acqTotal = 0;
  }
  jQuery("#edit-submitted-acquisition-percent-change").val(acqTotal + "%");
  grade += getAcquisitionPoints(acqTotal);
  console.log(grade);
  var cultAmount1 = Number(
    jQuery("#edit-submitted-cultivation-amount-1").val().replace(/,/g, "")
  );
  var cultAmount2 = Number(
    jQuery("#edit-submitted-cultivation-amount-2").val().replace(/,/g, "")
  );
  var cultTotal = 0;
  if (cultAmount1) {
    cultAmount1 = parseFloat(cultAmount1);
  } else {
    cultAmount1 = 0;
  }
  if (cultAmount2) {
    cultAmount2 = parseFloat(cultAmount2);
  } else {
    cultAmount2 = 0;
  }
  if (cultAmount1 > 0 && cultAmount2 > 0) {
    cultTotal = (((cultAmount2 - cultAmount1) / cultAmount1) * 100).toFixed(2);
  } else {
    cultTotal = 0;
  }
  jQuery("#edit-submitted-cultivation-percent-change1").val(cultTotal + "%");
  grade += getAcquisitionPoints(cultTotal);
  var cultAmount3 = Number(
    jQuery("#edit-submitted-cultivation-amount-3").val().replace(/,/g, "")
  );
  var cultAmount4 = Number(
    jQuery("#edit-submitted-cultivation-amount-4").val().replace(/,/g, "")
  );
  if (cultAmount3) {
    cultAmount3 = parseFloat(cultAmount3);
  } else {
    cultAmount3 = 0;
  }
  if (cultAmount4) {
    cultAmount4 = parseFloat(cultAmount4);
  } else {
    cultAmount4 = 0;
  }
  if (cultAmount3 > 0 && cultAmount4 > 0) {
    cultTotal2 = (((cultAmount4 - cultAmount3) / cultAmount3) * 100).toFixed(2);
  } else {
    cultTotal2 = 0;
  }
  jQuery("#edit-submitted-cultivation-percent-change2").val(cultTotal2 + "%");
  grade += getAcquisitionPoints(cultTotal2);
  var retAmount1 = Number(
    jQuery("#edit-submitted-retention-amount-1").val().replace(/,/g, "")
  );
  var retAmount2 = Number(
    jQuery("#edit-submitted-retention-amount-2").val().replace(/,/g, "")
  );
  var retTotal = 0;
  if (retAmount1) {
    retAmount1 = parseFloat(retAmount1);
  } else {
    retAmount1 = 0;
  }
  if (retAmount2) {
    retAmount2 = parseFloat(retAmount2);
  } else {
    retAmount2 = 0;
  }
  if (retAmount1 > 0 && retAmount2 > 0) {
    retTotal = ((retAmount2 / retAmount1) * 100).toFixed(2);
  } else {
    retTotal = 0;
  }
  jQuery("#edit-submitted-retention-percent-change").val(retTotal + "%");
  grade += getAcquisitionPoints(retTotal);
  jQuery("#edit-submitted-final-grade-grade").val(grade + " / 400");
}

function getAcquisitionPoints(val) {
  if (val < 1) {
    return 0;
  } else if (val >= 1 && val < 6) {
    return 50;
  } else if (val >= 6 && val < 11) {
    return 60;
  } else if (val >= 11 && val < 16) {
    return 70;
  } else if (val >= 16 && val < 21) {
    return 75;
  } else if (val >= 21 && val < 26) {
    return 80;
  } else if (val >= 26 && val < 31) {
    return 85;
  } else if (val >= 31 && val < 36) {
    return 90;
  } else if (val >= 36 && val < 41) {
    return 95;
  } else if (val >= 41) {
    return 100;
  }
}

function getCultivationGiftPoints(val) {
  if (val < 1) {
    return 0;
  } else if (val >= 1 && val < 4) {
    return 50;
  } else if (val >= 4 && val < 7) {
    return 60;
  } else if (val >= 7 && val < 10) {
    return 70;
  } else if (val >= 10 && val < 13) {
    return 75;
  } else if (val >= 13 && val < 16) {
    return 80;
  } else if (val >= 16 && val < 21) {
    return 85;
  } else if (val >= 21 && val < 26) {
    return 90;
  } else if (val >= 26 && val < 51) {
    return 95;
  } else if (val >= 51) {
    return 100;
  }
}

function getCultivationDonationPoints(val) {
  if (val < 1) {
    return 0;
  } else if (val >= 1 && val < 6) {
    return 50;
  } else if (val >= 6 && val < 11) {
    return 60;
  } else if (val >= 11 && val < 16) {
    return 70;
  } else if (val >= 16 && val < 21) {
    return 75;
  } else if (val >= 21 && val < 26) {
    return 80;
  } else if (val >= 26 && val < 31) {
    return 85;
  } else if (val >= 31 && val < 36) {
    return 90;
  } else if (val >= 36 && val < 41) {
    return 95;
  } else if (val >= 41) {
    return 100;
  }
}

function getRetentionPoints(val) {
  if (val < 1) {
    return 0;
  } else if (val >= 1 && val < 51) {
    return 50;
  } else if (val >= 51 && val < 56) {
    return 60;
  } else if (val >= 56 && val < 61) {
    return 70;
  } else if (val >= 61 && val < 66) {
    return 75;
  } else if (val >= 66 && val < 71) {
    return 80;
  } else if (val >= 71 && val < 76) {
    return 85;
  } else if (val >= 76 && val < 81) {
    return 90;
  } else if (val >= 81 && val < 91) {
    return 95;
  } else if (val >= 91) {
    return 100;
  }
}

function getBonusDonorPoints(val) {
  if (val < 10001) {
    return 0;
  } else if (val >= 10001 && val < 25001) {
    return 10;
  } else if (val >= 25001 && val < 50000) {
    return 15;
  } else if (val >= 50000) {
    return 20;
  }
}

//jQuery time
var current_fs, nextButton_fs, previousButton_fs; //fieldsets
var left, opacity, scale; //fieldset properties which we will animate
var animating; //flag to prevent quick multi-click glitches

$(".nextButton").click(function(){
	if(animating) return false;
	animating = true;
	
	current_fs = $(this).parent();
	nextButton_fs = $(this).parent().nextButton();
	
	//activate nextButton step on progressbarTeamMember using the index of nextButton_fs
	$("#progressbarTeamMember li").eq($("fieldset").index(nextButton_fs)).addClass("active");
	
	//show the nextButton fieldset
	nextButton_fs.show(); 
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale current_fs down to 80%
			scale = 1 - (1 - now) * 0.2;
			//2. bring nextButton_fs from the right(50%)
			left = (now * 50)+"%";
			//3. increase opacity of nextButton_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({
        'transform': 'scale('+scale+')',
        'position': 'absolute'
      });
			nextButton_fs.css({'left': left, 'opacity': opacity});
		}, 
		duration: 800, 
		complete: function(){
			current_fs.hide();
			animating = false;
		}, 
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
});

$(".previousButton").click(function(){
	if(animating) return false;
	animating = true;
	
	current_fs = $(this).parent();
	previousButton_fs = $(this).parent().prev();
	
	//de-activate current step on progressbarTeamMember
	$("#progressbarTeamMember li").eq($("fieldset").index(current_fs)).removeClass("active");
	
	//show the previousButton fieldset
	previousButton_fs.show(); 
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale previousButton_fs from 80% to 100%
			scale = 0.8 + (1 - now) * 0.2;
			//2. take current_fs to the right(50%) - from 0%
			left = ((1-now) * 50)+"%";
			//3. increase opacity of previousButton_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({'left': left});
			previousButton_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
		}, 
		duration: 800, 
		complete: function(){
			current_fs.hide();
			animating = false;
		}, 
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
});



$(function() {

    var newFields = $('');
    $('#teamSize').bind('blur keyup change', function() {
        var n = this.value || 0;
        if (n+1) {
            if (n > newFields.length) {
                addFields(n);
            } else {
                removeFields(n);
            }
        }
    });

    function addFields(n) {
        for (i = 1; i < n-1; i++) {
                var newel = $(".clone").clone(true);

            newFields = newFields.add(newel);
            newel.appendTo('#newFields');
            console.log(i);
        }
    }

    function removeFields(n) {
        var removeField = newFields.slice(n).remove();
        newFields = newFields.not(removeField);
    }
});



