<div id="contentwrapper">
	<div class="main_content">
		<#include "top.ftl">
			<div class="row-fluid">
			<div class="span12"><div class="alert alert-error">
						<a class="close" data-dismiss="alert">×</a>
						<strong>操作信息:
							欢迎
						<#if username??>
						    ${username}
						<#else>
                            游客
						</#if>
						</strong>
			</div>



					

					<#--<div class="btn-group sepH_b" >-->
						<#--<button data-toggle="dropdown" class="btn dropdown-toggle">-->
							<#--行数 <span class="caret"></span>-->
						<#--</button>-->
						<#--<ul class="dropdown-menu">-->


							<#--<li><a href="">默认5行</a></li>-->
							<#--<li><a href="">每页10行</a></li>-->
							<#--<li><a href="">每页2行</a></li>-->
						<#--</ul>-->
					<#--</div>-->

	<div>
	<div>
        <#--<iframe name="sinaWeatherTool"-->
				<#--src="http://weather.news.sina.com.cn/chajian/iframe/weatherStyle40.html?city=%E5%93%88%E5%B0%94%E6%BB%A8"-->
				<#--width="266" height="113" marginwidth="0" marginheight="0" hspace="0" vspace="0"-->
				<#--frameborder="0" scrolling="no"></iframe>-->



            <#--<iframe width="200" scrolling="no" height="500"-->
					<#--frameborder="0" allowtransparency="true"-->
					<#--src="http://i.tianqi.com/index.php?c=code&id=12&bgc=%2300B0F0&icon=1&num=1&site=12">-->

			<#--</iframe>-->

            <#--<iframe width="420" scrolling="no"-->
					<#--height="60" frameborder="0" allowtransparency="true"-->
					<#--src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=5&site=12">-->

			<#--</iframe>-->
		${.now}


            <iframe width="520" scrolling="no" height="60" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&py=xiangfangqu&num=5&site=12"></iframe>
    </div>
	<#--<div style="height: 120px">-->
        <#--<img src="../static/upload/timg.jpg" alt=""  width="250">-->
	<#--</div>-->


    <#--<h1>公告</h1>-->
	</div>


	<div>

                <table class="table table-bordered table-striped table_vam"
					id="dt_gal">
					<thead>
						<tr>

							<th class="table_checkbox">序号</th>
							<th style="width: 50px">发布人</th>
							<th style="width: 100px">新闻标题</th>
							<th style="width: 420px">新闻内容</th>
							<th style="width: 60px">发布日期</th>


							<th style="width: 60px">操作</th>




					</thead>

					<tbody>

                 <#if pb??>
						<#list pb.data as a>
								<tr>

									<td>${a_index+1}</td>
									<td><a href="/upload/.jpg"
										
										title="" class="cbox_single thumbnail">

										<img src="/user/pic/${a.user.userid}"
											alt="" style="height: 50px; width: 50px" />

									</a>
									
									
									
									</td>
									<td><a href="">${a.title}</a></td>
									<td>${a.content}</td>
									<td>${a.datetime}</td>
									<td>
											
										<!-- 没登陆，游客 uid=0 -->
												
											<!-- 锚点传值 -->
											<#--<a href="" title="灌水" data-toggle="modal"-->
											<#--id="myp" data-backdrop="static"-->
											<#--onclick="rshow(${data.id},${uid},${data.user.id});">-->
												 <#--<i class="icon-eye-open"></i>-->
											<#---->
									<#--</a>-->

                                        <#--<a href="#rshow" title="灌水" data-toggle="modal"-->
                                           <#--id="myp" data-backdrop="static"-->
                                           <#--onclick="////rshow()">-->
                                            <#--<i class="icon-eye-open"></i>-->

                                        <#--</a>-->

											<!-- 是本人贴可以删除和修改 -->

							    <#if username??>
											<a
												href="/news/delz/${a.id}"
												title="删除本帖"><i class="icon-trash"></i></a>
							     </#if>


                                    </td>
								</tr>
						</#list>

				</#if>
					</tbody>

				</table>



		<#include "page.ftl">

	</div>
    </div>
		</div>
		





	</div>


</div>