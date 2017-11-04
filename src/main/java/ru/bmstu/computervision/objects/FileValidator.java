package ru.bmstu.computervision.objects;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import  ru.bmstu.computervision.objects.UploadedFile;

@Component
public class FileValidator implements Validator {

	@Override
	public void validate(Object uploadedFile, Errors errors) {

		UploadedFile file = (UploadedFile) uploadedFile;

		if (file.getFile().getSize() == 0) {
			errors.rejectValue("file", "uploadForm.selectFile", "Не выбран файл!");
		}

	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

}