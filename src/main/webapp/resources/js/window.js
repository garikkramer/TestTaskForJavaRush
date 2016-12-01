$("#btn-add").click(function() {
    $("#myform").attr("action", "/addDeal");
    $("#myform").submit();
});

$("#btn-update").click(function() {
    $("#myform").attr("action", "/updateDeal");
    $("#myform").submit();
});