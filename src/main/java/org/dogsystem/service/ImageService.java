package org.dogsystem.service;

import org.apache.log4j.Logger;
import org.dogsystem.entity.ImageEntity;
import org.dogsystem.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
	
	private final Logger LOGGER = Logger.getLogger(this.getClass());
	
	@Autowired
	private ImageRepository imageRepository;
	
	public void delete(ImageEntity image){
		LOGGER.info(String.format("Excluindo o imagem [%s].", image));
		imageRepository.delete(image);
	}
}
