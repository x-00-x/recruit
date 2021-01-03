﻿<%@include file="../app.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>我的简历 - 锐聘网</title>
<link href="${appPath}/css/base.css" type="text/css" rel="stylesheet" />
<link href="${appPath}/css/my_resume.css" type="text/css" rel="stylesheet" />
<meta content="大学生求职,大学生就业,大学生招聘,IT人才,IT人才招聘,大学生名企招聘,,大学生找工作,IT名企招聘，IT行业招聘，IT企业快速入职" name="keywords">
<meta content="锐聘专注于为企业提供高效的人力资源解决方案，同时面向IT类技术人才推出快速一站式免费就业服务。秉承QST青软实训人才服务理念，为数千家企业量身定做个性化、全程化的人才培养体系，同时帮助中高级人才铺设成功之路，为人才和企业架设起沟通之桥。" name="description">

</head>

<body>
<iframe src="${appPath}/top.html" width="100%" height="100"  scrolling="no" frameborder="0" ></iframe>

<div class="resume_con">
	<!--tab设置-->
	<div class="user_operate">
		<ul style="float:left">
			<li><a href="resume.jsp" class="active">我的简历</a></li>
			<li><a href="jobApply.html">我的申请</a></li>
			<div class="clear"></div>
		</ul>
		<div class="clear"></div>
	</div>
    <!--主体部分-->
    <div class="resume_main">
    	<!--左边-->
	    <div class="resume_left">
			<div class="resume_title">
				<div style="float:left">基本信息</div>
			</div>
			<div class="all_resume">
				<div class="table_style">
					<table width="300" border="0" cellpadding="3" cellspacing="1" bgcolor="#EEEEEE">
					  <tr>
					    <th width="110" align="right" bgcolor="#F8F8F8">姓名：</th>
					    <td bgcolor="#F8F8F8"><input type="text" name="name" value="${resumeBasicInfo.name}"></td>
					  </tr>
					</table>
					<div class="he"></div>
					<table width="300" border="0" cellpadding="3" cellspacing="1" bgcolor="#EEEEEE">
					  <tr>
					    <th width="110" align="right" bgcolor="#F8F8F8">性别：</th>
					    <td bgcolor="#F8F8F8">
							<c:if test="${resumeBasicInfo.gender=='男'}">
								<input type="radio" name="gender" value="男" checked>男
								<input type="radio" name="gender" value="女">女
							</c:if>
							<c:if test="${resumeBasicInfo.gender=='女'}">
								<input type="radio" name="gender" value="男">男
								<input type="radio" name="gender" value="女" checked>女
							</c:if>
						</td>
					  </tr>
					</table>
					<div class="he"></div>
					<table width="300" border="0" cellpadding="3" cellspacing="1" bgcolor="#EEEEEE">
					  <tr>
					    <th width="110" align="right" bgcolor="#F8F8F8">出生日期：</th>
					    <td bgcolor="#F8F8F8"><input type="text" name="birthday" value="<fmt:formatDate value="${resumeBasicInfo.birthday}" pattern="yyyy-MM-dd"/>"></td>
					  </tr>
					</table>
					<div class="he"></div>
					<table width="300" border="0" cellpadding="3" cellspacing="1" bgcolor="#EEEEEE">
					  <tr>
					    <th width="110" align="right" bgcolor="#F8F8F8">手机：</th>
					    <td bgcolor="#F8F8F8"><input type="text" name="phone" value="${resumeBasicInfo.phone}"></td>
					  </tr>
					</table>
					<div class="he"></div>
					<table width="300" border="0" cellpadding="3" cellspacing="1" bgcolor="#EEEEEE">
					  <tr>
					    <th width="110" align="right" bgcolor="#F8F8F8">邮件：</th>
					    <td bgcolor="#F8F8F8"><input type="text" name="email" value="test@${resumeBasicInfo.email}.com"></td>
					  </tr>
					</table>
					<div class="he"></div>
					<table width="300" border="0" cellpadding="3" cellspacing="1" bgcolor="#EEEEEE">
					  <tr>
					    <th width="110" align="right" bgcolor="#F8F8F8">求职意向：</th>
					    <td bgcolor="#F8F8F8"><input type="text" name="intension" value="${resumeBasicInfo.intension}"></td>
					  </tr>
					</table>
					<div class="he"></div>
					<table width="300" border="0" cellpadding="3" cellspacing="1" bgcolor="#EEEEEE">
					  <tr>
					    <th width="110" align="right" bgcolor="#F8F8F8">工作经验：</th>
					    <td bgcolor="#F8F8F8"><input type="text" name="experience" value="${resumeBasicInfo.experience}"></td>
					  </tr>
					</table>
					<div class="he"></div>
					<div style="margin-left:100px;"><input name="" type="text" class="save1" value="保存"> 
					<input name="" type="text" class="cancel2" value="取消"></div>
				</div>
				<div style="float:right" class="uploade"><img style="width: 100px;height: 100px" src="${appPath}/applicant/images/${resumeBasicInfo.headShot}">
					<div align="center">
						<a href="${appPath}/applicant/resumeBasicInfoPicUpload.jsp" class="uploade_btn">更换照片</a>
					</div>
				</div>
				<div class="clear"></div>
			</div>
	    </div>
    	<!--右边-->
		<iframe src="${appPath}/applicant/resume_right.html" width="290" height="650"  scrolling="no" frameborder="0"></iframe>
		<div style="clear:both"></div>
	</div>
</div>

<iframe src="${appPath}/foot.html" width="100%" height="150"  scrolling="no" frameborder="0" ></iframe>
</body>
</html>
