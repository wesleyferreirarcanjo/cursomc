package com.arcanjo.cursomc.services;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.arcanjo.cursomc.services.exceptions.FileException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3client;

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multipartFile) {

		try {
			String filename = multipartFile.getOriginalFilename();
			InputStream is;
			is = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uploadFile(is, filename, contentType);

		} catch (IOException e) {

			throw new FileException("Erro de IO " + e.getMessage());
		}

	}

	public URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			LOG.info("iniciando upload");
			s3client.putObject(bucketName, fileName, is, meta);
			LOG.info("upload");

			return s3client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Erro de conversao");
		}
	}

}
