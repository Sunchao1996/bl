package com.critc.common.controller;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.critc.sys.vo.AvatarScale;
import com.critc.util.image.ImageCutUtil;
import com.critc.util.json.JsonUtil;
import com.critc.util.web.WebUtil;

/**
 * 
 * what: 文件上传Controller
 *
 * @author 孔垂云 created on 2017年7月6日
 */
@RequestMapping("/")
@Controller
public class UploadController {
	
	/**
	 * 
	 * what: 接收方法
	 * 
	 * @param formData 表单数据
	 * @param avatar_src 头像路径
	 * @param avatar_data 头像数据
	 * @param request request
	 * @param model model
	 * @return String
	 *
	 * @author 孔垂云 created on 2017年7月6日
	 */
	@RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String uploadAvatar(MultipartFile formData, String avatar_src, String avatar_data,
			HttpServletRequest request, Model model) {
		String dir = "D:\\upload\\image";
		// request.getSession().getServletContext().getRealPath(dir);
		String path = dir;
		String name = formData.getOriginalFilename();
		// 判断文件的MIMEtype
		String type = formData.getContentType();
		if (type == null || !type.toLowerCase().startsWith("image/")) {
			return JsonUtil.createOperaStr(false, "不支持的文件类型，仅支持图片！");
		}
		System.out.println("file type:" + type);
		String fileName = new Date().getTime() + "" + new Random().nextInt(10000) + "_"
				+ name.substring(name.lastIndexOf('.'));
		System.out.println("文件路径：" + path + ":" + fileName);
		/*
		 * Map<String,String> map=JsonUtil.toObject(avatar_data, HashMap.class);
		 * // 用户经过剪辑后的图片的大小 float x = Float.parseFloat(map.get("x")); float y
		 * =Float.parseFloat(map.get("y")); float w =
		 * Float.parseFloat(map.get("width")); float h =
		 * Float.parseFloat(map.get("height"));
		 */
		// 开始上传
		File targetFile = new File(path, fileName);
		// 保存
		try {
			if (!targetFile.exists()) {
				targetFile.mkdirs();
				formData.transferTo(targetFile);
				// InputStream is = formData.getInputStream();
				// ImageCutUtil.cut(is, targetFile, (int) x, (int) y, (int) w,
				// (int) h);
				// is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.createOperaStr(false, "上传失败，出现异常：" + e.getMessage());
		}
		// request.getSession().getServletContext().getContextPath()
		// + dir + fileName));
		return JsonUtil.createOperaStr(true, "上传成功!");
														
	}

	/*
	 * private int formartScale(Double d) { DecimalFormat df = new
	 * DecimalFormat("######0"); //四色五入转换成整数 return
	 * Integer.parseInt(df.format(d)); }
	 */

	/**
	 * 
	 * what:  图片裁剪
	 * 
	 * @param avatar_file 图片文件
	 * @param avatar_data 图片数据
	 * @param request request
	 * @param response response
	 *
	 * @author 孔垂云 created on 2017年7月6日
	 */
	@RequestMapping("/cutImg")
	public void cutImg(MultipartFile avatar_file, String avatar_data, HttpServletRequest request,
			HttpServletResponse response) {
		String dir = "D:\\upload\\image";
		// request.getSession().getServletContext().getRealPath(dir);
		String path = dir + "\\avatar.jpg";
		String fileName = new Date().getTime() + "" + new Random().nextInt(10000) + ".jpg";
		System.out.println("文件路径：" + path + ":" + fileName);
		AvatarScale avatarScale = JsonUtil.toObject(avatar_data, AvatarScale.class);
		// 用户经过剪辑后的图片的大小
		int x = (int) avatarScale.getX();
		int y = (int) avatarScale.getY();
		int w = (int) avatarScale.getWidth();
		int h = (int) avatarScale.getHeight();
		// 开始上传
		File targetFile = new File(dir, fileName);
		// 保存
		try {
			if (!targetFile.exists()) {
				targetFile.mkdirs();
				InputStream is = avatar_file.getInputStream();
				ImageCutUtil.cut(is, targetFile, x, y, w, h);
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			WebUtil.out(response, JsonUtil.createOperaStr(false, "上传失败，出现异常：" + e.getMessage()));
		}
		WebUtil.out(response, "{\"result\":" + true + ",\"message\":\"" + "上传成功" + "\",\"url\":\""
				+ "http://localhost:8080/manage/assets/cropper3.0/avatar.jpg" + "\"}");
	}

}
