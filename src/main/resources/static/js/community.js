function post() {
    var questionId = $("#id-question").val();
    console.log(questionId);
    var desc = $("#id-text").val();
    console.log(desc);
    $.ajax({
        async:true,
        url:"/comment",
        contentType:'application/json',
        type:"POST",
        data:JSON.stringify({
           "parentId":questionId,
            "type":1,
            "desc":desc
        }),
        success:function(result) {
            console.log(result)
        },
        dataType:"json",//返回格式为json
    })
}