package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.ConfigEntity;
import com.entity.EIException;
import com.service.ConfigService;
import com.utils.R;

@RestController
@RequestMapping("file")
@SuppressWarnings({"unchecked","rawtypes"})
public class FileController{
	@Autowired
    private ConfigService configService;

	@RequestMapping("/upload")
	public R upload(@RequestParam("file") MultipartFile file,String type) throws Exception {
		if (file.isEmpty()) {
			throw new EIException("上传文件不能为空");
		}

		String originalFilename = file.getOriginalFilename();
		if(StringUtils.isBlank(originalFilename) || originalFilename.lastIndexOf(".") < 0) {
			return R.error(400, "非法文件名");
		}

		String fileExt = originalFilename.substring(originalFilename.lastIndexOf(".")+1).toLowerCase();
		if(!fileExt.matches("^[a-z0-9]{1,10}$")) {
			return R.error(400, "非法文件类型");
		}

		File upload = new File("upload").getCanonicalFile();
		if(!upload.exists()) {
		    upload.mkdirs();
		}

		String fileName = new Date().getTime()+"."+fileExt;
		File dest = new File(upload, fileName).getCanonicalFile();
		if(!dest.getPath().startsWith(upload.getPath()+File.separator)) {
			return R.error(400, "非法文件路径");
		}

		file.transferTo(dest);
		if(StringUtils.isNotBlank(type) && type.equals("1")) {
			ConfigEntity configEntity = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "faceFile"));
			if(configEntity==null) {
				configEntity = new ConfigEntity();
				configEntity.setName("faceFile");
				configEntity.setValue(fileName);
			} else {
				configEntity.setValue(fileName);
			}
			configService.insertOrUpdate(configEntity);
		}
		return R.ok().put("file", fileName);
	}

	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(@RequestParam String fileName) {
		try {
			if(StringUtils.isBlank(fileName) || fileName.contains("/") || fileName.contains("\\") || fileName.contains("..")) {
				return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
			}

			File upload = new File("upload").getCanonicalFile();
			if(!upload.exists()) {
			    upload.mkdirs();
			}

			File file = new File(upload, fileName).getCanonicalFile();
			if(!file.getPath().startsWith(upload.getPath()+File.separator)) {
				return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
			}

			if(file.exists()){
				HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			    headers.setContentDispositionFormData("attachment", fileName);
			    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
	}
}
