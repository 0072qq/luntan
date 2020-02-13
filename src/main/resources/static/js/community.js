function post() {
    var questionId = $("#id-question").val();
    console.log(questionId);
    var desc = $("#id-text").val();
    console.log(desc);
    if(questionId!=null&&desc!=null || questionId!="" && desc!=""){
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
                if(result.code==2000){
                    window.location.reload()
                }else if(result.code==1001){
                    var b = confirm(result.message);
                    if(b){
                        window.open("https://github.com/login/oauth/authorize?client_id=b94a1b3cb05dbc213906&redirect_uri=http://localhost:8880/callback&scope=user&state=1");
                        window.localStorage.setItem("closeable",true);
                    }
                }
            },
            dataType:"json",//返回格式为json
        })
    }
}

function post1() {
    window.alert("未登录");
}

function sub_comment(e) {
    var commentId = e.getAttribute("data-id");
    var val = $("#reply-"+commentId).val();
    $.ajax({
        async:true,
        url:"/comment",
        contentType:'application/json',
        type:"POST",
        data:JSON.stringify({
            "parentId":commentId,
            "type":2,
            "desc":val
        }),
        success:function(result) {
            console.log(result)
            if(result.code==2000){
                window.location.reload()
            }else if(result.code==1001){
                var b = confirm(result.message);
                if(b){
                    window.open("https://github.com/login/oauth/authorize?client_id=b94a1b3cb05dbc213906&redirect_uri=http://localhost:8880/callback&scope=user&state=1");
                    window.localStorage.setItem("closeable",true);
                }
            }
        },
        dataType:"json",//返回格式为json
    })
}
//展开二级评论
function collapaseComment(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-"+id);

    var commentBody = $("#comment-"+id);
    if(commentBody.children().length==1){
        $.getJSON("/comment/"+id, function(data){

            console.log(data)
            $.each( data.data.reverse(), function(index,comment) {
                var c = $("<div/>",{
                    "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12",
                    html:comment.desc
                });
                commentBody.prepend(c);
            });
            for(var key in data){
                delete data[key];
            }
        })
    }
    comments.toggleClass("in");

    console.log(id);
}

