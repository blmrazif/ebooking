package my.benzourry.ebooking.core.controller;

import my.benzourry.ebooking.core.helper.Constant;
import my.benzourry.ebooking.core.dao.FacilityTypeRepository;
import my.benzourry.ebooking.core.model.lookup.FacilityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MohdRazif on 8/4/2015.
 */
@RestController
@RequestMapping(value="api/lookup")
public class LookupController {

    @Autowired
    FacilityTypeRepository facilityTypeRepository;

    @Transactional
    @RequestMapping(value="FacilityType/create", method= RequestMethod.POST)
    public FacilityType createFacilityType(@RequestBody FacilityType facilityType){
        return facilityTypeRepository.save(facilityType);
    }
    @RequestMapping(value="FacilityType/list", method= RequestMethod.GET)
    public List<FacilityType> findFacility(){
        return facilityTypeRepository.findAll();
    }


    @Transactional
    @RequestMapping(value = "FacilityType/delete", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    Map<String, Object> deleteFacilityTypeList(@RequestBody FacilityType im) {
        Map<String, Object> data = new HashMap<>();
        facilityTypeRepository.delete(im);
        data.put("message", "success");
        return data;
    }


//    @RequestMapping(value = "FacilityType/thumbnail/upload", method=RequestMethod.POST)
//    public String upload(@RequestParam("file") MultipartFile file, @RequestParam("username") String username) throws IOException {
//        // it is also possible to use @Request
//        byte[] bytes = null;
//
//        if (!file.isEmpty()) {
//            bytes = file.getBytes();
//            //store file in storage
//        }
//        System.out.println("files byte:"+ bytes);
//        return String.format("receive %s from %s", file.getOriginalFilename(), username);
//    }

    @RequestMapping(value = "FacilityType/thumbnail/upload", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file,
                                   Principal principal,
                                   HttpServletRequest request) throws Exception {


        Map<String, Object> data = new HashMap<>();
        String originalFilename = file.getOriginalFilename();

        File dir = new File(Constant.UPLOAD_ROOT_DIR + "/thumbnail");
        dir.mkdirs();

        String filePath = "thumbnail" + "_" + originalFilename;
        File dest = new File(Constant.UPLOAD_ROOT_DIR + "/thumbnail/" + filePath);

        try {
            file.transferTo(dest);
            data.put("fileUrl", filePath);
            data.put("message", "success");
        } catch (IllegalStateException e) {
            data.put("message", "failed");
        }

        return data;
    }

    @RequestMapping(value="FacilityType/thumbnail/{id}")
    public @ResponseBody
    FileSystemResource getAttachmentFile(@PathVariable("id") String id, HttpServletResponse response){
        FacilityType ra = facilityTypeRepository.findOne(id);
        response.setHeader("Content-Disposition", "attachment; filename="+ra.getThumbnail());
        return new FileSystemResource(Constant.UPLOAD_ROOT_DIR + "/thumbnail/" +ra.getThumbnail());
    }




}
