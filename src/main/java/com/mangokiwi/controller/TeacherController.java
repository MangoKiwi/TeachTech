package com.mangokiwi.controller;

import com.mangokiwi.core.exception.StorageFileNotFoundException;
import com.mangokiwi.model.Teacher;
import com.mangokiwi.service.TeacherService;
import com.mangokiwi.service.storage.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tangmaolei on 5/9/17.
 */
@RestController
public class TeacherController extends BaseController {
	@Autowired
	private TeacherService teacherService;

	@Autowired
    private FileSystemStorageService fileSystemStorageService;

    //TODO: TEST
    @RequestMapping(value = TEACHER_PROFILE_PATH + "/{id}", method = RequestMethod.GET)
    public Teacher getTeacherWithId(@PathVariable Long id){
        return teacherService.getTeacherByUserId(id);
    }

//    @RequestMapping(value = TEACHER_PROFILE_PATH + "/{id}", method = RequestMethod.POST)
//    public Teacher postTeacherWithId(@PathVariable Long id,
//                                     @RequestBody Teacher teacher){
//
//    }

    @RequestMapping(value = TEACHER_PROFILE_PATH + "/{id}/files", method = RequestMethod.POST)
    public ResponseEntity handleFileUpload(@PathVariable Long id,
                                           @RequestParam("file") MultipartFile file,
                                           @RequestParam("type") String fileType) {
        fileSystemStorageService.store(file, id, fileType);
//        HashMap<String, String> responseJson = new HashMap<>();
//        responseJson.put("response", "upload successfully");
        return ResponseEntity
                .accepted()
                .body("upload successfully");
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException e) {
        return ResponseEntity
                .notFound()
                .build();
    }

}
