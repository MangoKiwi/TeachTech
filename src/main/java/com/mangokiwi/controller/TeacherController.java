package com.mangokiwi.controller;

import com.mangokiwi.core.exception.StorageFileNotFoundException;
import com.mangokiwi.model.Teacher;
import com.mangokiwi.model.TeacherStatus;
import com.mangokiwi.service.TeacherService;
import com.mangokiwi.service.UserService;
import com.mangokiwi.service.storage.FileSystemStorageService;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by tangmaolei on 5/9/17.
 */
@RestController
public class TeacherController extends BaseController {
	@Autowired
	private TeacherService teacherService;

	@Autowired
    private UserService userService;

	@Autowired
    private FileSystemStorageService fileSystemStorageService;

    @RequestMapping(value = TEACHER_PROFILE_PATH + "/{id}", method = RequestMethod.GET)
    public Teacher getTeacherWithId(@PathVariable Long id){
        return teacherService.getTeacherByUserId(id);
    }

    @RequestMapping(value = TEACHER_PROFILE_PATH + "/{id}", method = RequestMethod.POST)
    public Teacher postTeacherWithId(@PathVariable Long id) {
        return teacherService.addTeacherByUserId(id);
    }

    @RequestMapping(value = TEACHER_PROFILE_PATH + "/{id}/files", method = RequestMethod.POST)
    public Teacher uploadFile(@PathVariable Long id,
                              @RequestParam("file") MultipartFile file,
                              @RequestParam("type") String fileType) {
        Teacher teacher = teacherService.getTeacherByUserId(id);
        fileSystemStorageService.store(file, id, fileType);
//        TODO: think about the association with change teacher status
//        change teacher status to "pending" as long as the file is upload
        userService.updateTeacherStatus(id, TeacherStatus.PENDING);
        return teacherService.update(teacher, fileType, file.getOriginalFilename());
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException e) {
        return ResponseEntity
                .notFound()
                .build();
    }

    @ExceptionHandler(FileUploadBase.FileSizeLimitExceededException.class)
    public ResponseEntity handleFileSizeLimitExceeded(FileUploadBase.FileSizeLimitExceededException e) {
        return ResponseEntity
                .badRequest()
                .build();
    }

}
