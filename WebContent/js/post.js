$(document).ready(function() {
			
			var n = 0;
			
			var winHeight = $(window).height();
			var docHeight = $(document).height();
			 //alert(winHeight);
		        var nScrollTop = 0;   //滚动到的当前位置
		        $(document).scroll(function(){
		          nScrollTop = $(this).scrollTop();
		          if(nScrollTop==docHeight-winHeight)
		            alert("滚动条到底部了");
		          });
			

			$("div.bgfff").click(function() {
				var postId = $(this).attr("id");
				location.href = "ShowPostServlet?id=" + postId;
			});
			
			
			 var reader = new FileReader();
			    var picture = null;
			    $('input#upload').change(function(){
			    	 if (this.files && this.files[0]) {
			             //reader.readAsDataURL(this.files[0]);
			    	// }
			        
			    	// 也可以传入图片路径：lrz('../demo.jpg', ...
			    	      lrz(this.files[0], {
			    	    	  
			    	    	  //压缩率
			    	    	  //quality: 0.2,
			    	    	  width : 500,
			    	    	  
			    	        // 压缩开始
			    	        before: function() {
			    	        	$("img#pic").attr("src","images/wait.gif");
			    	            console.log('压缩开始');
			    	        },
			    	        // 压缩失败
			    	        fail: function(err) {
			    	            console.error(err);
			    	        },
			    	        // 压缩结束（不论成功失败）
			    	        always: function() {
			    	            console.log('压缩结束');
			    	        },
			    	        // 压缩成功
			    	        done: function (results) {
			    	              // 你需要的数据都在这里，可以以字符串的形式传送base64给服务端转存为图片。
			    	              console.log(results); 
			    	              $("img#pic").attr('src', results.base64).addClass("img-thumbnail");
			    	              picture =  results.base64;
			    	        }
			    	    });  
			         }
			    });
			/*     reader.onload = function(e){
			        $("img#pic").attr('src', e.target.result).addClass("img-thumbnail");
			    };  */
			    	 
			    	
			    
			    $("input#send").click(function() {
					var title = $("input#message-title").val();
					var content = $("textarea#message-text").val();
					if (title == "") {
						$("#form-title").addClass("has-error");
						$("#message-title").attr("placeholder", "请输入标题");
					}
					if (content == "") {
						$("#form-content").addClass("has-error");
						$("#message-text").attr("placeholder", "请输入内容");
					}
					if (title != "" && content != "") {
						
						$("div#progress").addClass("progress");
						$("div#progress-bar").addClass("progress-bar");
						$("div#progress-bar").html("10%");
						
						$.ajax({
							type : 'POST',
							url : "ReceivePostServlet",
							data : {
								"title" : title,
								"content" : content,
								"picture" : picture
							},
							success : function(msg) {
								$("div#progress-bar").html("100%").attr("aria-valuenow","100").attr("style","width:100%;");
								setTimeout(function() {
								$("#myModal").modal('hide');
								$("span.return_msg").html("<br><br>" + msg);
								$(".bs-example-modal-sm").modal('show');
								setTimeout(function() {
									$(".bs-example-modal-sm").modal('hide');
									location.href = "post_list.jsp";
								}, 2000);
								},500);
							}
						});
					}
				});
			
			
		});